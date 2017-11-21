/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedLinks.vsl

package com.poesys.accounting.db.account.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import com.poesys.accounting.db.account.AccountFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * FiscalYearAccount objects using the primary key of the associated
 * class Account. These objects are the linking objects from the SQL 
 * table FiscalYearAccount rather than the objects associated through
 * the link (FiscalYear), which have a separate representation.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryFiscalYearAccountByAccount 
    implements IParameterizedQuerySql<com.poesys.accounting.db.account.IFiscalYearAccount, com.poesys.accounting.db.account.IAccount> {
  /** SQL query statement for FiscalYearAccount */
  private static final String SQL =
    "SELECT accountName, entityName, year, orderNumber, accountType, groupOrderNumber FROM FiscalYearAccount WHERE accountName = ? AND entityName = ?";

  public void bindParameters(PreparedStatement stmt, com.poesys.accounting.db.account.IAccount parameters) {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  public String getParameterValues(com.poesys.accounting.db.account.IAccount parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  public com.poesys.accounting.db.account.IFiscalYearAccount getData(ResultSet rs) {
    try {
      return AccountFactory.getFiscalYearAccountData(getPrimaryKey(rs), rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting data", e);
    }
  }

  public IPrimaryKey getPrimaryKey(ResultSet rs) {
    try {
      return AccountFactory.getFiscalYearAccountPrimaryKey(rs, "");
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting primary key", e);
    }
  }

  public String getSql() {
    return SQL;
  }
}