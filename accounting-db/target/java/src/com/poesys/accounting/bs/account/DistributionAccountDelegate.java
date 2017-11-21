/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.connection.IConnectionFactory;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * DistributionAccount objects and their dependents. This class delegates to an abstract
 * class, AbstractDistributionAccountDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * 
 * 
 * @author Poesys/DB Cartridge
 */
public class DistributionAccountDelegate extends AbstractDistributionAccountDelegate {
  /**
   * Create a DistributionAccountDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   * 
   * @param subsystem the subsystem to use
   */
  public DistributionAccountDelegate(String subsystem) {
    super(subsystem);
  }
  
  /**
   * Create a DistributionAccountDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   * 
   * @param subsystem the subsystem to use
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public DistributionAccountDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }
  
}