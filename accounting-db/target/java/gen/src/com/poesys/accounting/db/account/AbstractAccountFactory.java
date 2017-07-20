/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractSubsystemFactory.vsl

package com.poesys.accounting.db.account;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.InvalidParametersException;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A separate, shareable set of factory methods for all the account
 * classes, including JDBC data-setting, parameter-setting, and
 * primary-key-generation methods. This class is abstract and has a single
 * concrete subclass, AccountFactory, that you can modify to override
 * the default behavior in the abstract class or implement an as-yet 
 * unimplemented method.
 * </p>
 * 
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountFactory {
  /**
   * Retrieve the FiscalYear data from the result set's current row and create
   * a FiscalYear object.
   * 
   * @param key the primary key for the Account
   * @param rs the query result set
   * @return a FiscalYear instance based on the result set data
   * @throws SQLException when there is a problem getting data from the results
   * @throws InvalidParametersException when a required value is null
   */
  public static IFiscalYear getFiscalYearData(IPrimaryKey key, ResultSet rs)
      throws SQLException, InvalidParametersException {
    // Constructor argument year gets the JDBC value with a function call.
    java.lang.Integer yearValue = rs.getInt("year");
    // Constructor argument startDate gets the JDBC value with a function call.
    java.sql.Timestamp startDateValue = rs.getTimestamp("startDate");
    // Constructor argument endDate gets the JDBC value with a function call.
    java.sql.Timestamp endDateValue = rs.getTimestamp("endDate");
    // FiscalYear has lazily loaded members or is a lazily loaded association class, so create a Proxy.
    IFiscalYear newObject = 
      new FiscalYearProxy(new FiscalYear(key, yearValue, startDateValue, endDateValue));
    return newObject;
  }
  
  /**
   * <p>
   * Get a primary key for a FiscalYear based on a result set that must contain
   * the FiscalYear primary key columns. The method creates a primary key of
   * a type specified by the primary key stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param rs a JDBC result set with primary key columns
   * @param prefix an optional prefix string for derived column names in 
   *               associations
   * @return a FiscalYear NaturalKey primary key
   * @throws SQLException when there is a problem getting data from the result
   *             set
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getFiscalYearPrimaryKey(ResultSet rs, String prefix)
      throws SQLException, InvalidParametersException {
    IPrimaryKey key = null;
    if (prefix == null) {
      prefix = "";
    }
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    java.lang.Integer yearValue = rs.getInt("year");

    list.add(new com.poesys.db.col.IntegerColumnValue(prefix + "year", yearValue));
    key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.FiscalYear");
    return key;
  }

  /**
   * <p>
   * Get a primary key for a FiscalYear based on input key attributes. 
   * The method creates a primary key of a type specified by the primary key 
   * stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param year the fiscal year, a year value corresponding to the calendar year of the last day
of an accounting period
   * @return a FiscalYear NaturalKey primary key
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getFiscalYearPrimaryKey(java.lang.Integer year)
      throws InvalidParametersException {
    IPrimaryKey key = null;
    // Track generated inputs for nullity.
    boolean noNulls = true;
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    if (year != null && noNulls) {
      list.add(new com.poesys.db.col.IntegerColumnValue("year", year));
    } else {
      noNulls = false;
    }
    if (noNulls) {
      key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.FiscalYear");
    }
    return key;
  }

  /**
   * Retrieve the FiscalYearAccount data from the result set's current row and create
   * a FiscalYearAccount object.
   * 
   * @param key the primary key for the Account
   * @param rs the query result set
   * @return a FiscalYearAccount instance based on the result set data
   * @throws SQLException when there is a problem getting data from the results
   * @throws InvalidParametersException when a required value is null
   */
  public static IFiscalYearAccount getFiscalYearAccountData(IPrimaryKey key, ResultSet rs)
      throws SQLException, InvalidParametersException {
    // Assign null to association key object, as this is set by QuerySetter
    com.poesys.accounting.db.account.IAccount accountsObject = null;
    // Assign null to association key object, as this is set by QuerySetter
    com.poesys.accounting.db.account.IFiscalYear yearsObject = null;
    // Constructor argument accountName gets the JDBC value with a function call.
    java.lang.String accountNameValue = rs.getString("accountName");
    // Constructor argument entityName gets the JDBC value with a function call.
    java.lang.String entityNameValue = rs.getString("entityName");
    // Constructor argument year gets the JDBC value with a function call.
    java.lang.Integer yearValue = rs.getInt("year");
    // Constructor argument orderNumber gets the JDBC value with a function call.
    java.lang.Integer orderNumberValue = rs.getInt("orderNumber");
    if (rs.wasNull()) {
      orderNumberValue = null;
    };
    // FiscalYearAccount has lazily loaded members or is a lazily loaded association class, so create a Proxy.
    IFiscalYearAccount newObject = 
      new FiscalYearAccountProxy(new FiscalYearAccount(key, accountsObject, yearsObject, accountNameValue, entityNameValue, yearValue, orderNumberValue));
    return newObject;
  }
  
  /**
   * <p>
   * Get a primary key for a FiscalYearAccount based on a result set that must contain
   * the FiscalYearAccount primary key columns. The method creates a primary key of
   * a type specified by the primary key stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>AssociationKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param rs a JDBC result set with primary key columns
   * @param prefix an optional prefix string for derived column names in 
   *               associations
   * @return a FiscalYearAccount AssociationKey primary key
   * @throws SQLException when there is a problem getting data from the result
   *             set
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getFiscalYearAccountPrimaryKey(ResultSet rs, String prefix)
      throws SQLException, InvalidParametersException {
    IPrimaryKey key = null;
    if (prefix == null) {
      prefix = "";
    }
    java.util.ArrayList<IPrimaryKey> list =
        new java.util.ArrayList<IPrimaryKey>();
    // Associated key type CompositeKey
    IPrimaryKey accountsKey = getEntityPrimaryKey(rs, prefix + "accounts");
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> accountsKeys =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    // Property source: AddExplicitSubKeyProperties + addNaturalSubkeyOnClass + getAssociatedKeys
    java.lang.String accountNameValue = rs.getString("accountName");
    
    accountsKeys.add(new com.poesys.db.col.StringColumnValue(prefix + "accountName", accountNameValue));
    // Property source: AddNaturalKeyProperties + AddParentKeyAttributes + getAssociatedKeys
    java.lang.String entityNameValue = rs.getString("entityName");
    
    accountsKeys.add(new com.poesys.db.col.StringColumnValue(prefix + "entityName", entityNameValue));
	IPrimaryKey accountsSubKey = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(accountsKeys, "com.poesys.accounting.db.account.FiscalYearAccount");
    list.add(com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(accountsKey, accountsSubKey, "com.poesys.accounting.db.account.FiscalYearAccount"));
    // Associated key type NaturalKey
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> yearsKeys =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    // Property source: AddNaturalKeyProperties + getAssociatedKeys
    java.lang.Integer yearValue = rs.getInt("year");
    yearsKeys.add(new com.poesys.db.col.IntegerColumnValue(prefix + "year", yearValue));
    list.add(com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(yearsKeys, "com.poesys.accounting.db.account.FiscalYearAccount"));
	key = com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.accounting.db.account.FiscalYearAccount");
    return key;
  }

  /**
   * <p>
   * Get a primary key for a FiscalYearAccount based on input key attributes. 
   * The method creates a primary key of a type specified by the primary key 
   * stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>AssociationKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param accountName Attribute that is part of the association key
   * @param entityName Attribute that is part of the association key
   * @param year Attribute that is part of the association key
   * @return a FiscalYearAccount AssociationKey primary key
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getFiscalYearAccountPrimaryKey(java.lang.String accountName, java.lang.String entityName, java.lang.Integer year)
      throws InvalidParametersException {
    IPrimaryKey key = null;
    // Track whether any input keys are null.
    boolean noNulls = true;
    java.util.ArrayList<IPrimaryKey> list =
        new java.util.ArrayList<IPrimaryKey>();
    // Associated key: accounts with type CompositeKey
    IPrimaryKey accountsParentKey = getEntityPrimaryKey(entityName);
    if (accountsParentKey != null) {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> accountsAccountList =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
	  IPrimaryKey accountsSubKey = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(accountsAccountList, "com.poesys.accounting.db.account.FiscalYearAccount");
      key = com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(accountsParentKey, accountsSubKey, "com.poesys.accounting.db.account.FiscalYearAccount");
    } else {
      noNulls = false;
    }
    // Associated key: years with type NaturalKey
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> yearsKeys =
      new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    if (year != null && noNulls) {
      yearsKeys.add(new com.poesys.db.col.IntegerColumnValue("year", year));
    } else {
      noNulls = false;
    }
    list.add(com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(yearsKeys, "com.poesys.accounting.db.account.FiscalYearAccount"));
    if (noNulls) {
	  key = com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.accounting.db.account.FiscalYearAccount");
	}
    return key;
  }

  /**
   * Retrieve the Account data from the result set's current row and create
   * a Account object.
   * 
   * @param key the primary key for the Account
   * @param rs the query result set
   * @return a Account instance based on the result set data
   * @throws SQLException when there is a problem getting data from the results
   * @throws InvalidParametersException when a required value is null
   */
  public static IAccount getAccountData(IPrimaryKey key, ResultSet rs)
      throws SQLException, InvalidParametersException {
    // Constructor argument entityName gets the JDBC value with a function call.
    java.lang.String entityNameValue = rs.getString("entityName");
    // Constructor argument accountName gets the JDBC value with a function call.
    java.lang.String accountNameValue = rs.getString("accountName");
    // Constructor argument description gets the JDBC value with a function call.
    java.lang.String descriptionValue = rs.getString("description");
    // Constructor argument debitDefault gets the JDBC value with a function call.
    java.lang.Boolean debitDefaultValue = rs.getBoolean("debitDefault");
    // Constructor argument accountType gets the JDBC value with a function call.
    java.lang.String accountTypeValue = rs.getString("accountType");
    // Constructor argument receivable gets the JDBC value with a function call.
    java.lang.Boolean receivableValue = rs.getBoolean("receivable");
    // Constructor argument active gets the JDBC value with a function call.
    java.lang.Boolean activeValue = rs.getBoolean("active");
    // Constructor argument groupName gets the JDBC value with a function call.
    java.lang.String groupNameValue = rs.getString("groupName");
    // Account has lazily loaded members or is a lazily loaded association class, so create a Proxy.
    IAccount newObject = 
      new AccountProxy(new Account(key, entityNameValue, accountNameValue, descriptionValue, debitDefaultValue, accountTypeValue, receivableValue, activeValue, groupNameValue));
    return newObject;
  }
  
  /**
   * <p>
   * Get a primary key for a Account based on a result set that must contain
   * the Account primary key columns. The method creates a primary key of
   * a type specified by the primary key stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>CompositeKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param rs a JDBC result set with primary key columns
   * @param prefix an optional prefix string for derived column names in 
   *               associations
   * @return a Account CompositeKey primary key
   * @throws SQLException when there is a problem getting data from the result
   *             set
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getAccountPrimaryKey(ResultSet rs, String prefix)
      throws SQLException, InvalidParametersException {
    IPrimaryKey key = null;
    if (prefix == null) {
      prefix = "";
    }
    IPrimaryKey parentKey = getEntityPrimaryKey(rs, "");
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    java.lang.String accountNameValue = rs.getString("accountName");
    list.add(new com.poesys.db.col.StringColumnValue(prefix + "accountName", accountNameValue));
	IPrimaryKey subKey = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.Account");
    key = com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parentKey, subKey, "com.poesys.accounting.db.account.Account");
    return key;
  }

  /**
   * <p>
   * Get a primary key for a Account based on input key attributes. 
   * The method creates a primary key of a type specified by the primary key 
   * stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>CompositeKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param accountName the account name; unique within the entity
   * @param entityName composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @return a Account CompositeKey primary key
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getAccountPrimaryKey(java.lang.String accountName, java.lang.String entityName)
      throws InvalidParametersException {
    IPrimaryKey key = null;
    IPrimaryKey parentKey = getEntityPrimaryKey(entityName);

    // Check the parent key; if it is null, the return key should be null.
    if (parentKey != null) {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.StringColumnValue("accountName", accountName));
	  IPrimaryKey subKey = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.Account");
      key = com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parentKey, subKey, "com.poesys.accounting.db.account.Account");
    }
    return key;
  }

  /**
   * Retrieve the Entity data from the result set's current row and create
   * a Entity object.
   * 
   * @param key the primary key for the Account
   * @param rs the query result set
   * @return a Entity instance based on the result set data
   * @throws SQLException when there is a problem getting data from the results
   * @throws InvalidParametersException when a required value is null
   */
  public static IEntity getEntityData(IPrimaryKey key, ResultSet rs)
      throws SQLException, InvalidParametersException {
    // Constructor argument entityName gets the JDBC value with a function call.
    java.lang.String entityNameValue = rs.getString("entityName");
    // Entity has no lazily loaded members, so there is no need for Proxy.
    IEntity newObject = 
      new Entity(key, entityNameValue);
    return newObject;
  }
  
  /**
   * <p>
   * Get a primary key for a Entity based on a result set that must contain
   * the Entity primary key columns. The method creates a primary key of
   * a type specified by the primary key stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param rs a JDBC result set with primary key columns
   * @param prefix an optional prefix string for derived column names in 
   *               associations
   * @return a Entity NaturalKey primary key
   * @throws SQLException when there is a problem getting data from the result
   *             set
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getEntityPrimaryKey(ResultSet rs, String prefix)
      throws SQLException, InvalidParametersException {
    IPrimaryKey key = null;
    if (prefix == null) {
      prefix = "";
    }
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    java.lang.String entityNameValue = rs.getString("entityName");

    list.add(new com.poesys.db.col.StringColumnValue(prefix + "entityName", entityNameValue));
    key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.Entity");
    return key;
  }

  /**
   * <p>
   * Get a primary key for a Entity based on input key attributes. 
   * The method creates a primary key of a type specified by the primary key 
   * stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param entityName the name of the accounting entity; unique among all entities
   * @return a Entity NaturalKey primary key
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getEntityPrimaryKey(java.lang.String entityName)
      throws InvalidParametersException {
    IPrimaryKey key = null;
    // Track generated inputs for nullity.
    boolean noNulls = true;
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    if (entityName != null && noNulls) {
      list.add(new com.poesys.db.col.StringColumnValue("entityName", entityName));
    } else {
      noNulls = false;
    }
    if (noNulls) {
      key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.Entity");
    }
    return key;
  }

  /**
   * Retrieve the AccountGroup data from the result set's current row and create
   * a AccountGroup object.
   * 
   * @param key the primary key for the Account
   * @param rs the query result set
   * @return a AccountGroup instance based on the result set data
   * @throws SQLException when there is a problem getting data from the results
   * @throws InvalidParametersException when a required value is null
   */
  public static IAccountGroup getAccountGroupData(IPrimaryKey key, ResultSet rs)
      throws SQLException, InvalidParametersException {
    // Constructor argument groupName gets the JDBC value with a function call.
    java.lang.String groupNameValue = rs.getString("groupName");
    // AccountGroup has no lazily loaded members, so there is no need for Proxy.
    IAccountGroup newObject = 
      new AccountGroup(key, groupNameValue);
    return newObject;
  }
  
  /**
   * <p>
   * Get a primary key for a AccountGroup based on a result set that must contain
   * the AccountGroup primary key columns. The method creates a primary key of
   * a type specified by the primary key stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param rs a JDBC result set with primary key columns
   * @param prefix an optional prefix string for derived column names in 
   *               associations
   * @return a AccountGroup NaturalKey primary key
   * @throws SQLException when there is a problem getting data from the result
   *             set
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getAccountGroupPrimaryKey(ResultSet rs, String prefix)
      throws SQLException, InvalidParametersException {
    IPrimaryKey key = null;
    if (prefix == null) {
      prefix = "";
    }
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    java.lang.String groupNameValue = rs.getString("groupName");

    list.add(new com.poesys.db.col.StringColumnValue(prefix + "groupName", groupNameValue));
    key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.AccountGroup");
    return key;
  }

  /**
   * <p>
   * Get a primary key for a AccountGroup based on input key attributes. 
   * The method creates a primary key of a type specified by the primary key 
   * stereotype on the persistent class.
   * </p>
   * <p>
   * Stereotypes:
   * </p>
   * <ul>
   *     <li>NaturalKey</li>
   *     <li>Persistent</li>
   * </ul>
   * 
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   * @return a AccountGroup NaturalKey primary key
   * @throws InvalidParametersException when there is a problem creating a key
   */
  public static IPrimaryKey getAccountGroupPrimaryKey(java.lang.String groupName)
      throws InvalidParametersException {
    IPrimaryKey key = null;
    // Track generated inputs for nullity.
    boolean noNulls = true;
    java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
    if (groupName != null && noNulls) {
      list.add(new com.poesys.db.col.StringColumnValue("groupName", groupName));
    } else {
      noNulls = false;
    }
    if (noNulls) {
      key = com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.AccountGroup");
    }
    return key;
  }

}