/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.bs.delegate.AbstractDataDelegate;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.dao.delete.IDeleteSql;
import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.dao.query.IQuerySql;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * AccountType objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class AccountTypeDelegate, which
 * specializes this class.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
 * </p>
 * @see AccountTypeDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountTypeDelegate
    extends AbstractDataDelegate<BsAccountType, com.poesys.accounting.db.account.IAccountType, com.poesys.db.pk.NaturalPrimaryKey> {
  
  @SuppressWarnings("unused")
  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AbstractAccountTypeDelegate.class);
  
  /**
   * Create an AbstractAccountTypeDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractAccountTypeDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractAccountTypeDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractAccountTypeDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.accounting.db.account.AccountType.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.accounting.db.account.IAccountType> getInsertSql() {
    return new com.poesys.accounting.db.account.sql.InsertAccountType();
  }

  @Override
  protected IDeleteSql<com.poesys.accounting.db.account.IAccountType> getDeleteSql() {
    return new com.poesys.accounting.db.account.sql.DeleteAccountType();
  }

  @Override
  protected IUpdateSql<com.poesys.accounting.db.account.IAccountType> getUpdateSql() {
    return new com.poesys.accounting.db.account.sql.UpdateAccountType();
  }

  @Override
  protected IKeyQuerySql<com.poesys.accounting.db.account.IAccountType> getQueryByKeySql() {
    return new com.poesys.accounting.db.account.sql.QueryAccountType();
  }

  @Override
  protected IQuerySql<com.poesys.accounting.db.account.IAccountType> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.accounting.db.account.sql.QueryAllAccountType();
  }

  @Override
  protected com.poesys.accounting.bs.account.BsAccountType wrapData(com.poesys.accounting.db.account.IAccountType dto) {
    return new com.poesys.accounting.bs.account.BsAccountType(dto);
  }

  /**
   * <p>
   * Create a new AccountType with data fields.
   * </p>
   * <p>
   * The AccountType class has a natural key; this method creates the primary
   * key from the appropriate input properties.
   * </p>
   * 
   * @param accountType the account type name (Assets, Liabilities, Equity, Income, Expense)
   * @return the new AccountType object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.accounting.bs.account.BsAccountType createAccountType(java.lang.String accountType)
      throws DelegateException {
    com.poesys.db.pk.NaturalPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.IColumnValue> list = new java.util.ArrayList<>();
      list.add(new com.poesys.db.col.StringColumnValue("accountType", accountType));
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.AccountType");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.accounting.db.account.IAccountType dto =
      new com.poesys.accounting.db.account.AccountType(key, accountType);

    // Create the business DTO.
    return new com.poesys.accounting.bs.account.BsAccountType(dto);
  }

  /**
   * Create a new AccountGroup child of AccountType with a composite key.
   * 
   * @param parent the parent of the child object to create
   * @param accountType composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   * @return a new AccountGroup business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.accounting.bs.account.BsAccountGroup createAccountGroup(com.poesys.accounting.bs.account.BsAccountType parent, java.lang.String accountType, java.lang.String groupName) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.CompositePrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.IColumnValue> list = new java.util.ArrayList<>();
      list.add(new com.poesys.db.col.StringColumnValue("groupName", groupName));
	  com.poesys.db.pk.IPrimaryKey subKey = 
	    com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.AccountGroup");
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parent.getPrimaryKey(), 
                                                              subKey,
                                                              "com.poesys.accounting.db.account.AccountGroup");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a composite-key child data-access AccountGroup DTO for direct access, no proxy required.
    com.poesys.accounting.db.account.IAccountGroup dto =
      new com.poesys.accounting.db.account.AccountGroup(key, accountType, groupName);

    // Create the business DTO.
    return new com.poesys.accounting.bs.account.BsAccountGroup(dto);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    try {
      // First truncate any child tables.
      sql = new com.poesys.db.dao.ddl.TruncateTableSql("AccountGroup");
      executive = new com.poesys.db.dao.ddl.ExecuteSql(sql, subsystem);
      executive.execute();

      // Now truncate the current table.
      sql = new com.poesys.db.dao.ddl.TruncateTableSql(tableName);
      executive = new com.poesys.db.dao.ddl.ExecuteSql(sql, subsystem);
      executive.execute();
    } catch (Throwable e) {
      throw new DelegateException("Error truncating table " + tableName, e);
    }
  }
}
