/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAll.vsl

package com.poesys.accounting.db.transaction.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.query.IQuerySql;
import com.poesys.db.pk.IPrimaryKey;

import org.apache.log4j.Logger;
import com.poesys.accounting.db.transaction.TransactionFactory;


/**
 * <p>
 * A query Command pattern object that implements a SQL query for all the 
 * Reimbursement objects in the database; use with caution
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryAllReimbursement implements IQuerySql<com.poesys.accounting.db.transaction.IReimbursement> {
  private static final Logger logger = Logger.getLogger(AbstractQueryAllReimbursement.class);
  /** SQL query statement for Reimbursement */
  private static final String SQL =
    "SELECT receivablesOrderNumber, reimbursingItemsOrderNumber, receivablesTransactionId, reimbursingItemsTransactionId, reimbursedAmount, allocatedAmount FROM Reimbursement";

  @Override
  public com.poesys.accounting.db.transaction.IReimbursement getData(ResultSet rs) {
    try {
      IPrimaryKey key = 
        TransactionFactory.getReimbursementPrimaryKey(rs, "");
      
      return TransactionFactory.getReimbursementData(key, rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      logger.error("Error getting data", e);
      throw new com.poesys.db.DbErrorException("Error getting data", e);
    }
  }
  
  @Override
  public IPrimaryKey getPrimaryKey(ResultSet rs) throws InvalidParametersException {
    try {
      return TransactionFactory.getReimbursementPrimaryKey(rs, "");
    } catch (SQLException e) {
      logger.error("SQL error getting primary key", e);
      throw new com.poesys.db.DbErrorException("SQL error getting primary key", e);
    }
  }

  @Override
  public String getSql() {
    return SQL;
  }
}