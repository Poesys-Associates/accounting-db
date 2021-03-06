/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractInsert.vsl

package com.poesys.accounting.db.transaction.sql;



import java.sql.PreparedStatement;

import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for inserting a Item
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertItem implements IInsertSql<com.poesys.accounting.db.transaction.IItem> {
  private static final String SQL =
    "INSERT INTO Item (orderNumber, transactionId, amount, debit, checked, accountName, entityName) VALUES (?,?,?,?,?,?,?)";

  @Override
  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  @Override
  public void setParams(PreparedStatement stmt, int index, 
                        com.poesys.accounting.db.transaction.IItem object) {
    try {
      if (object.getAmount() != null) 
        stmt.setDouble(index, object.getAmount());
      else
              stmt.setNull(index, java.sql.Types.DOUBLE);
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
    try {
      stmt.setInt(index, object.getDebit() ? 1 : 0);
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
    try {
      stmt.setInt(index, object.getChecked() ? 1 : 0);
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
    try {
      stmt.setString(index, object.getAccountName());
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
    try {
      stmt.setString(index, object.getEntityName());
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
  }
  
  @Override
  public String getParamString(com.poesys.accounting.db.transaction.IItem object) {
    StringBuilder builder = new StringBuilder();

    // Get the primary key string
    builder.append(object.getPrimaryKey().getStringKey());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("amount: ");
    builder.append(object.getAmount());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("debit: ");
    builder.append(object.getDebit());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("checked: ");
    builder.append(object.getChecked());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("accountName: ");
    builder.append(object.getAccountName());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("entityName: ");
    builder.append(object.getEntityName());
    return builder.toString();
  }
}
