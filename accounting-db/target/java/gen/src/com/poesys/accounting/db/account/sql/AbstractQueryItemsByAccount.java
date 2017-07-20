/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQueryAssociatedOneToManyObjects.vsl

package com.poesys.accounting.db.account.sql;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.poesys.db.dao.query.IParameterizedQuerySql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A query Command pattern object that implements a SQL query of a collection of 
 * Item objects using the primary key of an associated 
 * Account object through the association Items. This
 * is a many-to-one association from Item to Account.
 * </p>
 * <p>
 * This SQL specification contains a SQL statement that queries a collection of
 * Item objects from the database using the foreign key type
 * Account.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryItemsByAccount 
    implements IParameterizedQuerySql<com.poesys.accounting.db.transaction.IItem, com.poesys.accounting.db.account.IAccount> {
  /** SQL query statement for Item */
  private static final String SQL =
    "SELECT Item.transactionId, Item.orderNumber, Item.amount, Item.debit, Item.checked, Item.accountName, Item.entityName FROM Item WHERE Item.accountName = ? AND Item.entityName = ?";

  @Override
  public void bindParameters(PreparedStatement stmt, com.poesys.accounting.db.account.IAccount parameters) {
    // Set the parameters starting with the first parameter.
    parameters.getPrimaryKey().setParams(stmt, 1);
  }

  @Override
  public String getParameterValues(com.poesys.accounting.db.account.IAccount parameters) {
    // Create the output string with the key parameters.
    return parameters.getPrimaryKey().getValueList();
  }

  @Override
  public com.poesys.accounting.db.transaction.IItem getData(ResultSet rs) {
    try {
      return com.poesys.accounting.db.transaction.TransactionFactory.getItemData(getPrimaryKey(rs), rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting data", e);
    }
  }

  @Override
  public IPrimaryKey getPrimaryKey(ResultSet rs) {
    try {
      return com.poesys.accounting.db.transaction.TransactionFactory.getItemPrimaryKey(rs, "");
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      throw new com.poesys.db.DbErrorException("Error getting primary key", e);
    }
  }

  @Override
  public String getSql() {
    return SQL;
  }
}