/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * AccountGroup objects and their dependents. This class delegates to an abstract
 * class, AbstractAccountGroupDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * A named group of accounts, grouping the accounts for presentation and
 * aggregation in financial statements
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountGroupDelegate extends AbstractAccountGroupDelegate {
  /**
   * Create a AccountGroupDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public AccountGroupDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a AccountGroupDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AccountGroupDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}