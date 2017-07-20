/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractUpdate.vsl

package com.poesys.accounting.db.account.sql;


import java.sql.PreparedStatement;

import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for updating a FiscalYearAccount with read/write properties
 * 
 * @author Robert J. Muller
 */
public class AbstractUpdateFiscalYearAccount implements IUpdateSql<com.poesys.accounting.db.account.IFiscalYearAccount> {
  /** SQL UPDATE statement for FiscalYearAccount */
  private static final String SQL =
    "UPDATE FiscalYearAccount SET orderNumber = ? WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    StringBuilder builder = new StringBuilder(SQL);
    builder.append(key.getSqlWhereExpression(""));
    return builder.toString();
  }

  @Override
  public int setParams(PreparedStatement stmt, int index, com.poesys.accounting.db.account.IFiscalYearAccount object) {
    try{
      stmt.setInt(index, object.getOrderNumber());
    } catch (java.sql.SQLException e) {
      throw new com.poesys.db.DbErrorException("SQL error setting parameters", e);
    }
      index++;
    // sets primary key in where clause
    index = object.getPrimaryKey().setParams(stmt, index);
    return index;
  }

  @Override
  public String getParamString(com.poesys.accounting.db.account.IFiscalYearAccount dto) {
    StringBuilder builder = new StringBuilder("Parameters: \"");
    builder.append("\", ");
    builder.append(dto.getOrderNumber());
    builder.append("\"");
    return builder.toString();
  }
}
