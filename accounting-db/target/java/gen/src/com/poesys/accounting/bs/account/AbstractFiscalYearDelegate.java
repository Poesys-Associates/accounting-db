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
 * FiscalYear objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class FiscalYearDelegate, which
 * specializes this class.
 * </p>
 * <p>
 * An accounting period, usually either coterminous with a calendar year with the
 * same name or varying over an annual period starting on a particular calendar
 * date within the year; identifies a complete accounting period for statements
 * </p>
 * @see FiscalYearDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractFiscalYearDelegate
    extends AbstractDataDelegate<BsFiscalYear, com.poesys.accounting.db.account.IFiscalYear, com.poesys.db.pk.NaturalPrimaryKey> {
  
  @SuppressWarnings("unused")
  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AbstractFiscalYearDelegate.class);
  
  /**
   * Create an AbstractFiscalYearDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractFiscalYearDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractFiscalYearDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractFiscalYearDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.accounting.db.account.FiscalYear.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.accounting.db.account.IFiscalYear> getInsertSql() {
    return new com.poesys.accounting.db.account.sql.InsertFiscalYear();
  }

  @Override
  protected IDeleteSql<com.poesys.accounting.db.account.IFiscalYear> getDeleteSql() {
    return new com.poesys.accounting.db.account.sql.DeleteFiscalYear();
  }

  @Override
  protected IUpdateSql<com.poesys.accounting.db.account.IFiscalYear> getUpdateSql() {
    return new com.poesys.accounting.db.account.sql.UpdateFiscalYear();
  }

  @Override
  protected IKeyQuerySql<com.poesys.accounting.db.account.IFiscalYear> getQueryByKeySql() {
    return new com.poesys.accounting.db.account.sql.QueryFiscalYear();
  }

  @Override
  protected IQuerySql<com.poesys.accounting.db.account.IFiscalYear> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.accounting.db.account.sql.QueryAllFiscalYear();
  }

  @Override
  protected com.poesys.accounting.bs.account.BsFiscalYear wrapData(com.poesys.accounting.db.account.IFiscalYear dto) {
    return new com.poesys.accounting.bs.account.BsFiscalYear(dto);
  }

  /**
   * <p>
   * Create a new FiscalYear with data fields.
   * </p>
   * <p>
   * The FiscalYear class has a natural key; this method creates the primary
   * key from the appropriate input properties.
   * </p>
   * 
   * @param year the fiscal year, a year value corresponding to the calendar year of the last day
of an accounting period
   * @param startDate the calendar day of the first day of the accounting period
   * @param endDate the last calendar day of the accounting period; year corresponds to the fiscal
year number
   * @return the new FiscalYear object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.accounting.bs.account.BsFiscalYear createFiscalYear(java.lang.Integer year, java.sql.Timestamp startDate, java.sql.Timestamp endDate)
      throws DelegateException {
    com.poesys.db.pk.NaturalPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.AbstractColumnValue> list =
        new java.util.ArrayList<com.poesys.db.col.AbstractColumnValue>();
      list.add(new com.poesys.db.col.IntegerColumnValue("year", year));
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.account.FiscalYear");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a data-access DTO proxy (supports lazy loading).
    com.poesys.accounting.db.account.IFiscalYear dto =
      new com.poesys.accounting.db.account.FiscalYearProxy(new com.poesys.accounting.db.account.FiscalYear(key, year, startDate, endDate));

    // Create the business DTO.
    return new com.poesys.accounting.bs.account.BsFiscalYear(dto);
  }

  /**
   * Create a new FiscalYearAccount association class child of FiscalYear with 
   * an association key. This class links the input objects.
   * 
   * @param accountsObject associated Account object (part of the key)
   * @param yearsObject associated FiscalYear object (part of the key)
   * @param accountName Attribute that is part of the association key
   * @param entityName Attribute that is part of the association key
   * @param year Attribute that is part of the association key
   * @param orderNumber the integer rank of the account in the list of accounts
   * @return a new FiscalYearAccount business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.accounting.bs.account.BsFiscalYearAccount createFiscalYearAccount(com.poesys.accounting.bs.account.BsAccount accountsObject, com.poesys.accounting.bs.account.BsFiscalYear yearsObject, java.lang.String accountName, java.lang.String entityName, java.lang.Integer year, java.lang.Integer orderNumber) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.AssociationPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.pk.IPrimaryKey> list =
        new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
      list.add(accountsObject.getPrimaryKey());
      list.add(yearsObject.getPrimaryKey());
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.accounting.db.account.FiscalYearAccount");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access FiscalYearAccount DTO proxy (supports lazy loading).
    com.poesys.accounting.db.account.IFiscalYearAccount dto =
      new com.poesys.accounting.db.account.FiscalYearAccountProxy(new com.poesys.accounting.db.account.FiscalYearAccount(key, accountsObject.toDto(), yearsObject.toDto(), accountName, entityName, year, orderNumber));

    // Create the business DTO.
    return new com.poesys.accounting.bs.account.BsFiscalYearAccount(dto);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    try {
      // First truncate any child tables.
      sql = new com.poesys.db.dao.ddl.TruncateTableSql("FiscalYearAccount");
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
