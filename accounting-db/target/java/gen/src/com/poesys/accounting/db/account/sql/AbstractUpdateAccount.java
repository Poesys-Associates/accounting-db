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
 * SQL statement specification for updating a Account with read/write properties
 * 
 * @author Robert J. Muller
 */
public class AbstractUpdateAccount implements IUpdateSql<com.poesys.accounting.db.account.IAccount> {
  /** SQL UPDATE statement for Account */
  private static final String SQL =
    "UPDATE Account SET description = ?, debitDefault = ?, active = ? WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    StringBuilder builder = new StringBuilder(SQL);
    builder.append(key.getSqlWhereExpression(""));
    return builder.toString();
  }

  @Override
  public int setParams(PreparedStatement stmt, int index, com.poesys.accounting.db.account.IAccount object) {
    try{
      stmt.setString(index, object.getDescription());
    } catch (java.sql.SQLException e) {
      throw new com.poesys.db.DbErrorException("SQL error setting parameters", e);
    }
      index++;
    try{
      stmt.setInt(index, object.getDebitDefault() ? 1 : 0);
    } catch (java.sql.SQLException e) {
      throw new com.poesys.db.DbErrorException("SQL error setting parameters", e);
    }
      index++;
    try{
      stmt.setInt(index, object.getActive() ? 1 : 0);
    } catch (java.sql.SQLException e) {
      throw new com.poesys.db.DbErrorException("SQL error setting parameters", e);
    }
      index++;
    // sets primary key in where clause
    index = object.getPrimaryKey().setParams(stmt, index);
    return index;
  }

  @Override
  public String getParamString(com.poesys.accounting.db.account.IAccount dto) {
    StringBuilder builder = new StringBuilder("Parameters: \"");
    builder.append("\", ");
    builder.append(dto.getDescription());
    builder.append("\", ");
    builder.append(dto.getDebitDefault());
    builder.append("\", ");
    builder.append(dto.getActive());
    builder.append("\"");
    return builder.toString();
  }
}
