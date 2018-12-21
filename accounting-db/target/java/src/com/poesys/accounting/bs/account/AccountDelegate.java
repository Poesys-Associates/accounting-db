/* Copyright 2016 Poesys Associates. All rights reserved. */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;

import com.poesys.accounting.bs.transaction.BsItem;
import com.poesys.accounting.db.account.*;
import com.poesys.bs.delegate.AbstractDataDelegate;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.dao.PoesysTrackingThread;
import com.poesys.db.dao.delete.IDeleteSql;
import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.dao.query.IQuerySql;
import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.pk.CompositePrimaryKey;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * A business delegate that provides an application programming interface for querying any kind
 * of concrete account object as a BsAccount. The class implements tests for subclasses and uses
 * the correct business delegate to query the object.
 * </p>
 * <p>
 * <strong>
 * Note that this delegate does not support data manipulation functions, just query.
 * </strong>
 * </p>
 *
 * @author Robert J. Muller
 */
public class AccountDelegate extends AbstractDataDelegate<BsAccount, IAccount,
  CompositePrimaryKey> {

  // Supported class name strings
  private static final String SIMPLE_ACCOUNT = SimpleAccount.class.getName();
  private static final String SIMPLE_ACCOUNT_PROXY = SimpleAccountProxy.class.getName();
  private static final String CAPITAL_ACCOUNT = CapitalAccount.class.getName();
  private static final String CAPITAL_ACCOUNT_PROXY = CapitalAccountProxy.class.getName();
  private static final String DIST_ACCOUNT = DistributionAccount.class.getName();
  private static final String DIST_ACCOUNT_PROXY = DistributionAccountProxy.class.getName();

  // messages
  private static final String UNSUPPORTED_ACCOUNT_TYPE_ERROR = "unsupported account DB DTO type ";
  private static final String UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR =
    "unsupported method for Account";
  private static final String NO_SIMPLE_QUERIED_ERROR = "no simple account queried for key ";
  private static final String NO_CAP_QUERIED_ERROR = "no capital account queried for key ";
  private static final String NO_DIST_QUERIED_ERROR = "no distribution account queried for key ";

  /**
   * class name for the concrete account subclass of the item; required to be able to get the
   * class name for instantiation from JSON
   */
  private String className;

  /**
   * Create a SimpleAccountDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   *
   * @param className the concrete account subclass of the item
   * @param subsystem the subsystem to use
   */
  public AccountDelegate(String className, String subsystem) {
    super(subsystem, 2147483647);
    this.className = className;
  }

  /**
   * Create a SimpleAccountDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   *
   * @param subsystem the subsystem to use
   * @param dbms      the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AccountDelegate(String className, String subsystem, IConnectionFactory.DBMS dbms) {
    super(className, subsystem, dbms, 2147483647);
    this.className = className;
  }

  @Override
  protected String getClassName() {
    return className;
  }

  @Override
  protected IKeyQuerySql<IAccount> getQueryByKeySql() {
    return null;
  }

  @Override
  protected BsAccount wrapData(IAccount dto) {
    return null;
  }

  @Override
  protected IQuerySql<IAccount> getQueryListSql() {
    return null;
  }

  @Override
  protected IInsertSql<IAccount> getInsertSql() {
    return null;
  }

  @Override
  protected IUpdateSql<IAccount> getUpdateSql() {
    return null;
  }

  @Override
  protected IDeleteSql<IAccount> getDeleteSql() {
    return null;
  }

  // Override the getObject() methods to enable querying the appropriate item object.
  @Override
  public BsAccount getObject(CompositePrimaryKey key, int expiration) throws DelegateException {
    BsAccount account = null;
    // Use the class name to get the correct business delegate object and query. Each block
    // instantiates the proper delegate, queries the concrete object, then creates a new
    // superclass object by extracting the DTO.
    if (className.equals(SIMPLE_ACCOUNT) || className.equals(SIMPLE_ACCOUNT_PROXY)) {
      SimpleAccountDelegate del = AccountDelegateFactory.getSimpleAccountDelegate();
      BsSimpleAccount simpleAccount = del.getObject(key);
      if (simpleAccount == null) {
        throw new RuntimeException(NO_SIMPLE_QUERIED_ERROR + key.getStringKey());
      }
      account = new BsAccount(simpleAccount.toDto());
    } else if (className.equals(CAPITAL_ACCOUNT) || className.equals(CAPITAL_ACCOUNT_PROXY)) {
      CapitalAccountDelegate del = AccountDelegateFactory.getCapitalAccountDelegate();
      BsCapitalAccount capitalAccount = del.getObject(key);
      if (capitalAccount == null) {
        throw new RuntimeException(NO_CAP_QUERIED_ERROR + key.getStringKey());
      }
      account = new BsAccount(capitalAccount.toDto());
    } else if (className.equals(DIST_ACCOUNT) || className.equals(DIST_ACCOUNT_PROXY)) {
      DistributionAccountDelegate del = AccountDelegateFactory.getDistributionAccountDelegate();
      BsDistributionAccount distAccount = del.getObject(key);
      if (distAccount == null) {
        throw new RuntimeException(NO_DIST_QUERIED_ERROR + key.getStringKey());
      }
      account = new BsAccount(distAccount.toDto());
    } else {
      throw new InvalidParametersException(UNSUPPORTED_ACCOUNT_TYPE_ERROR + className);
    }

    return account;
  }

  @Override
  public BsAccount getObject(CompositePrimaryKey key) throws DelegateException {
    return getObject(key, 2147483647);
  }

  @Override
  public List<BsAccount> getAllObjects(int rows) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public List<BsAccount> getAllObjects(int rows, int expiration) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void insert(List<BsAccount> list) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void insert(BsAccount object) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void update(BsAccount object) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void updateBatch(List<BsAccount> list) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void delete(BsAccount object) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void deleteBatch(List<BsAccount> list) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void process(List<BsAccount> list) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  protected void postprocess(Collection<IAccount> dtos, PoesysTrackingThread thread) {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void process(BsAccount object) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    throw new RuntimeException(UNSUPPORTED_METHOD_FOR_ACCOUNT_ERROR);
  }
}