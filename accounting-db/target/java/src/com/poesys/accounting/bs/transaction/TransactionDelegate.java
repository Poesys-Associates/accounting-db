/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.transaction;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * Transaction objects and their dependents. This class delegates to an abstract
 * class, AbstractTransactionDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the fiscal
 * year accounts must balance (cancel each other out with respect to debits and
 * credits)
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TransactionDelegate extends AbstractTransactionDelegate {
  /**
   * Create a TransactionDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public TransactionDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a TransactionDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public TransactionDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}