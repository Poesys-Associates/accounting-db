/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractSubsystemDelegateFactory.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.connection.IConnectionFactory.DBMS;


/**
 * <p>
 * A set of factory methods for the instantiable business delegates in the 
 * Account subsystem. This class is abstract and has a single concrete 
 * subclass, AccountDelegateFactory, that you can modify to override
 * the default behavior in the abstract class or implement an as-yet 
 * unimplemented delegate factory. You can, for example, override all the
 * factory methods with a different connection strategy.
 * </p>
 * 
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountDelegateFactory {
  /** 
   * The fully qualified name of the Account subsystem properties file,
   * not including the locale information. This comes from the UML subsystem
   * as the package name concatenated to the subsystem name (the fully qualified
   * subsystem name). The actual file is then in the package with the subsystem 
   * name concatenated to the locale name followed by the .properties extension.
   * For example, the subsystem account in the com.poesys.accounting.db 
   * package has the property file com.poesys.accounting.db.account_en_US.properties
   * for the default locale english (US).
   */
  private static final String PROPERTY_FILE = "com.poesys.accounting.db.account";
    
  /** Resource bundle for subsystem property file */
  private static final java.util.ResourceBundle rb =
    java.util.ResourceBundle.getBundle(PROPERTY_FILE);

  /** Resource bundle for database property file */
  private static final java.util.ResourceBundle dbrb =
    java.util.ResourceBundle.getBundle("com.poesys.db.database");

  /** Initialize the subsystem name from the subsystem property file. */
  protected static final String subsystem = rb.getString("subsystem");
  
  /** Initialize the JDBC DBMS type from the DBMS property file. */
  protected static final DBMS JDBC_DBMS = DBMS.stringValue(dbrb.getString("com.poesys.accounting.db.account.dbms"));

  /** Initialize the JNDI DBMS type from the property file. */
  protected static final DBMS JNDI_DBMS = DBMS.stringValue(rb.getString("jndi_dbms"));
  
  /** Initialize the cached DAO manager for the subsystem. */
  protected static final com.poesys.db.dao.IDaoManager manager = 
    com.poesys.db.dao.DaoManagerFactory.initCacheManager(subsystem);

  /**
   * Get the FiscalYearDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static FiscalYearDelegate getFiscalYearDelegate() {
    return new FiscalYearDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the FiscalYearDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static FiscalYearDelegate getJndiFiscalYearDelegate() {
    return new FiscalYearDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the EntityDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static EntityDelegate getEntityDelegate() {
    return new EntityDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the EntityDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static EntityDelegate getJndiEntityDelegate() {
    return new EntityDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the CapitalEntityDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static CapitalEntityDelegate getCapitalEntityDelegate() {
    return new CapitalEntityDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the CapitalEntityDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static CapitalEntityDelegate getJndiCapitalEntityDelegate() {
    return new CapitalEntityDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the AccountTypeDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static AccountTypeDelegate getAccountTypeDelegate() {
    return new AccountTypeDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the AccountTypeDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static AccountTypeDelegate getJndiAccountTypeDelegate() {
    return new AccountTypeDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the CapitalAccountDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static CapitalAccountDelegate getCapitalAccountDelegate() {
    return new CapitalAccountDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the CapitalAccountDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static CapitalAccountDelegate getJndiCapitalAccountDelegate() {
    return new CapitalAccountDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the DistributionAccountDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static DistributionAccountDelegate getDistributionAccountDelegate() {
    return new DistributionAccountDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the DistributionAccountDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static DistributionAccountDelegate getJndiDistributionAccountDelegate() {
    return new DistributionAccountDelegate(subsystem, JNDI_DBMS);
  }

  /**
   * Get the SimpleAccountDelegate. The file com.poesys.accounting.db.account_en_US.properties supplies the
   * database subsystem for the delegate to use. The method enables caching and
   * constructs the Delegate with the subsystem from the property file. The
   * database and password are set from the database properties file entries
   * for the subsystem. Use this method in contexts where you don't know 
   * whether a JNDI server will be available, such as lazy loading contexts or
   * JUnit test cases.
   * 
   * @return the delegate
   */
  public static SimpleAccountDelegate getSimpleAccountDelegate() {
    return new SimpleAccountDelegate(subsystem, JDBC_DBMS);
  }
  
  /**
   * Get the SimpleAccountDelegate using the JNDI_DBMS static member initialized
   * from the Account.properties file. The system uses the JNDI name to
   * access the data source. Use this method in contexts where you know that
   * the JNDI server will always be available.
   * 
   * @return the JNDI delegate
   */
  public static SimpleAccountDelegate getJndiSimpleAccountDelegate() {
    return new SimpleAccountDelegate(subsystem, JNDI_DBMS);
  }
}