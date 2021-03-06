/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractInsert.vsl

package com.poesys.accounting.db.account.sql;



import java.sql.PreparedStatement;

import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for inserting a DistributionAccount
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertDistributionAccount implements IInsertSql<com.poesys.accounting.db.account.IDistributionAccount> {
  private static final String SQL =
    "INSERT INTO DistributionAccount (accountName, entityName, capitalEntityName) VALUES (?,?,?)";

  @Override
  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  @Override
  public void setParams(PreparedStatement stmt, int index, 
                        com.poesys.accounting.db.account.IDistributionAccount object) {
    try {
      stmt.setString(index, object.getCapitalEntityName());
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
  }
  
  @Override
  public String getParamString(com.poesys.accounting.db.account.IDistributionAccount object) {
    StringBuilder builder = new StringBuilder();

    // Get the primary key string
    builder.append(object.getPrimaryKey().getStringKey());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("capitalEntityName: ");
    builder.append(object.getCapitalEntityName());
    return builder.toString();
  }
}
