/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;

import com.poesys.accounting.db.account.IAccount;
import com.poesys.accounting.db.account.ISimpleAccount;
import com.poesys.accounting.db.account.sql.DeleteAccount;
import com.poesys.accounting.db.account.sql.InsertAccount;
import com.poesys.accounting.db.account.sql.UpdateAccount;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.dao.IDaoFactory;
import com.poesys.db.dao.delete.IDeleteBatch;
import com.poesys.db.dao.insert.IInsertBatch;
import com.poesys.db.dao.update.IUpdateBatch;
import com.poesys.db.dto.IDbDto;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * A business delegate that provides an application programming interface for
 * SimpleAccount objects and their dependents. This class delegates to an abstract
 * class, AbstractSimpleAccountDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public class SimpleAccountDelegate extends AbstractSimpleAccountDelegate {

  public static final String ACCOUNT_CLASS = "com.poesys.accounting.db.account.Account";
  public static final String SUBSYSTEM = "com.poesys.accounting.db.account";

  /**
   * Create a SimpleAccountDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   *
   * @param subsystem the subsystem to use
   */
  public SimpleAccountDelegate(String subsystem) {
    super(subsystem);
  }

  /**
   * Create a SimpleAccountDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   *
   * @param subsystem the subsystem to use
   * @param dbms      the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public SimpleAccountDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }

  // Rewrite the process method to correct bug with insert and status setting.
  @Override
  public void process(List<BsSimpleAccount> list) throws DelegateException {
    final IDaoFactory<IAccount> accountFactory =
      manager.getFactory(ACCOUNT_CLASS, SUBSYSTEM, Integer.MAX_VALUE);

    // Create the insert and update DAOs for the SimpleAccount concrete leaf objects.
    IInsertBatch<ISimpleAccount> inserter = factory.getInsertBatch(getInsertSql());
    IUpdateBatch<ISimpleAccount> updater = factory.getUpdateBatch(getUpdateSql());

    // Create the insert, update, and delete DAOs for the Account superclass objects.
    IInsertBatch<IAccount> accountInserter = accountFactory.getInsertBatch(new InsertAccount());
    IUpdateBatch<IAccount> accountUpdater = accountFactory.getUpdateBatch(new UpdateAccount());
    IDeleteBatch<IAccount> deleter = accountFactory.getDeleteBatch(new DeleteAccount());

    // Create list of DB DTOs extracted from the list of business objects.
    Collection<ISimpleAccount> dtos = convertDtoList(list);
    Collection<IAccount> accountDtos = convertDtoList(list);

    try {
      // The delete will cascade to the subclass rows through foreign key constraints.
      if (deleter != null) {
        deleter.delete(accountDtos, accountDtos.size() / 2);
      }

      // Process the Account and SimpleAccount lists separately as batches.

      // Turn off inserts for nested objects, making sure pre-inserts are enabled. Only the leaf
      // can handle the nested object inserts.
      for (IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(true);
        dto.setSuppressNestedPreInserts(false);
      }

      // Process Account inserts in a batch.
      if (accountInserter != null) {
        accountInserter.insert(accountDtos, accountDtos.size() / 2);
      }

      // Process Account updates in a batch.
      if (accountUpdater != null) {
        accountUpdater.update(accountDtos, accountDtos.size() / 2);
      }

      if (inserter != null) {
        // Turn on nested-object inserts for the concrete leaf objects and undo any status changes.
        for (IDbDto dto : dtos) {
          dto.setSuppressNestedInserts(false);
          // Only undo non-DELETED changes.
          IDbDto.Status status = dto.getStatus();
          if (!(status == IDbDto.Status.DELETED || status == IDbDto.Status.CASCADE_DELETED)) {
            dto.undoStatus();
          }
        }
        // Process SimpleAccount inserts in a batch.
        inserter.insert(dtos, dtos.size() / 2);
      }

      // Process SimpleAccount updates in a batch.
      if (updater != null) {
        updater.update(dtos, dtos.size() / 2);
      }
    }
    finally {
      // After all processing, set the status to EXISTING or DELETED, as appropriate.
      updateChangedToExisting(dtos);
      finalizeStatus(dtos, com.poesys.db.dto.IDbDto.Status.EXISTING);
      finalizeStatus(dtos, com.poesys.db.dto.IDbDto.Status.DELETED);
    }
  }
}