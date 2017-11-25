/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;


import com.poesys.accounting.db.account.AccountFactory;
import com.poesys.accounting.db.account.FiscalYearAccount;
import com.poesys.accounting.db.account.FiscalYearAccountProxy;
import com.poesys.accounting.db.account.IAccountGroup;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * Entity objects and their dependents. This class delegates to an abstract
 * class, AbstractEntityDelegate. You can modify this class to override methods
 * in that class or to add operations to the API.
 * </p>
 * <p>
 * The entity is the person or organization that owns all the accounts and the
 * transactions in them and is the basis for the accounting statements
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class EntityDelegate extends AbstractEntityDelegate {
  /**
   * Create a EntityDelegate object with a supplied subsystem, using the default
   * database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public EntityDelegate(String subsystem) {
    super(subsystem);
  }

  /**
   * Create a EntityDelegate object with a supplied subsystem and DBMS, usually
   * JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public EntityDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }

  // Override to correct bad key generated code, group is not part of key
  @Override
  public BsFiscalYearAccount createFiscalYearAccount(BsAccount accountsObject,
                                                     BsAccountGroup groupObject,
                                                     BsFiscalYear yearsObject,
                                                     String accountName,
                                                     String entityName,
                                                     Integer year,
                                                     Integer accountOrderNumber,
                                                     Integer groupOrderNumber,
                                                     String accountType,
                                                     String groupName,
                                                     IAccountGroup group)
      throws DelegateException {
    // Create the key.
    IPrimaryKey key =
      AccountFactory.getFiscalYearAccountPrimaryKey(accountName,
                                                    entityName,
                                                    year);
    // Create an association-key child data-access FiscalYearAccount DTO proxy
    // (supports lazy loading).
    com.poesys.accounting.db.account.IFiscalYearAccount dto =
      new FiscalYearAccountProxy(new FiscalYearAccount(key,
                                                       accountsObject.toDto(),
                                                       groupObject.toDto(),
                                                       yearsObject.toDto(),
                                                       accountName,
                                                       entityName,
                                                       year,
                                                       accountOrderNumber,
                                                       groupOrderNumber,
                                                       accountType,
                                                       groupName,
                                                       group));

    // Create the business DTO.
    return new BsFiscalYearAccount(dto);
  }
}