/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * AccountType objects and their dependents. This class delegates to an abstract
 * class, AbstractAccountTypeDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountTypeDelegate extends AbstractAccountTypeDelegate {
  /**
   * Create a AccountTypeDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public AccountTypeDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a AccountTypeDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AccountTypeDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}