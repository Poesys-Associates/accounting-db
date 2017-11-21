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
 * SQL statement specification for updating a AccountGroup with read/write properties
 * 
 * @author Robert J. Muller
 */
public class AbstractUpdateAccountGroup implements IUpdateSql<com.poesys.accounting.db.account.IAccountGroup> {
  /** SQL UPDATE statement for AccountGroup */
  private static final String SQL =
    "UPDATE AccountGroup SET groupName = ? WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    StringBuilder builder = new StringBuilder(SQL);
    builder.append(key.getSqlWhereExpression(""));
    return builder.toString();
  }

  @Override
  public int setParams(PreparedStatement stmt, int index, com.poesys.accounting.db.account.IAccountGroup object) {
    try{
      stmt.setString(index, object.getGroupName());
    } catch (java.sql.SQLException e) {
      throw new com.poesys.db.DbErrorException("SQL error setting parameters", e);
    }
      index++;
    // sets primary key in where clause
    index = object.getPrimaryKey().setParams(stmt, index);
    return index;
  }

  @Override
  public String getParamString(com.poesys.accounting.db.account.IAccountGroup dto) {
    StringBuilder builder = new StringBuilder("Parameters: \"");
    builder.append("\", ");
    builder.append(dto.getGroupName());
    builder.append("\"");
    return builder.toString();
  }
}
