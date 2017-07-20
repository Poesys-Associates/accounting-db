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
 * SQL statement specification for inserting a Reimbursement
 * 
 * @author Robert J. Muller
 */
public class AbstractInsertReimbursement implements IInsertSql<com.poesys.accounting.db.transaction.IReimbursement> {
  private static final String SQL =
    "INSERT INTO Reimbursement (receivablesOrderNumber, reimbursingItemsOrderNumber, receivablesTransactionId, reimbursingItemsTransactionId, reimbursedAmount, allocatedAmount) VALUES (?,?,?,?,?,?)";

  @Override
  public String getSql(IPrimaryKey key) {
    return SQL;
  }

  @Override
  public void setParams(PreparedStatement stmt, int index, 
                        com.poesys.accounting.db.transaction.IReimbursement object) {
    try {
      if (object.getReimbursedAmount() != null) 
        stmt.setDouble(index, object.getReimbursedAmount());
      else
              stmt.setNull(index, java.sql.Types.DOUBLE);
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
    try {
      if (object.getAllocatedAmount() != null) 
        stmt.setDouble(index, object.getAllocatedAmount());
      else
              stmt.setNull(index, java.sql.Types.DOUBLE);
    } catch (java.sql.SQLException e) {
      String message = com.poesys.db.Message.getMessage("com.poesys.db.sql.msg.parameter", null);
      throw new com.poesys.db.DbErrorException(message, e);
    }
    index++;
  }
  
  @Override
  public String getParamString(com.poesys.accounting.db.transaction.IReimbursement object) {
    StringBuilder builder = new StringBuilder();

    // Get the primary key string
    builder.append(object.getPrimaryKey().getStringKey());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("reimbursedAmount: ");
    builder.append(object.getReimbursedAmount());
    // Get the non-key attributes.
    builder.append(", ");
    builder.append("allocatedAmount: ");
    builder.append(object.getAllocatedAmount());
    return builder.toString();
  }
}
