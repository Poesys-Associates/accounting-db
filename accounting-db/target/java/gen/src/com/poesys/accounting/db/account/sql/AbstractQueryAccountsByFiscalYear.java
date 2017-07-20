/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedManyToManyObjects.vsl


package com.poesys.accounting.db.account.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;



/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * Account objects using the primary key of an associated 
 * FiscalYear object through the association Accounts using the 
 * association class FiscalYearAccount. This association is a many-to-many 
 * association from Account to FiscalYear.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * Account objects from the database table Account by
 * joining to the database table FiscalYearAccount and using the 
 * primary key from FiscalYear to query the collection.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAccountsByFiscalYear 
    implements IParameterizedQuerySql<com.poesys.accounting.db.account.IAccount, com.poesys.accounting.db.account.IFiscalYear> {
  /** SQL query statement for Account */
  private static final String SQL =
    "SELECT a.entityName, a.accountName, a.description, a.debitDefault, a.accountType, a.receivable, a.active, a.groupName FROM Account a JOIN FiscalYearAccount b ON a.accountName = b.accountName AND a.entityName = b.entityName WHERE b.year = ?";

  @Override
  public void bindParameters(PreparedStatement stmt, com.poesys.accounting.db.account.IFiscalYear parameters) {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  @Override
  public String getParameterValues(com.poesys.accounting.db.account.IFiscalYear parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  @Override
  public com.poesys.accounting.db.account.IAccount getData(ResultSet rs) {
    try {
      IPrimaryKey key = 
        com.poesys.accounting.db.account.AccountFactory.getAccountPrimaryKey(rs, "");
      return com.poesys.accounting.db.account.AccountFactory.getAccountData(key, rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting primary key", e);
    }
  }

  @Override
  public IPrimaryKey getPrimaryKey(ResultSet rs) {
    try {
      return com.poesys.accounting.db.account.AccountFactory.getAccountPrimaryKey(rs, "");
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting primary key", e);
    }
  }

  @Override
  public String getSql() {
    return SQL;
  }
}