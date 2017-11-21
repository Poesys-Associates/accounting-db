/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractQuery.vsl

package com.poesys.accounting.db.account.sql;


import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A query Command pattern object that implements a SQL key query for the 
 * CapitalAccount. This SQL specification contains a SQL statement that queries
 * a single CapitalAccount object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryCapitalAccount implements IKeyQuerySql<com.poesys.accounting.db.account.ICapitalAccount> {
  private static final Logger logger = Logger.getLogger(AbstractQueryCapitalAccount.class);
  /** SQL query statement for CapitalAccount */
  private static final String SQL =
    "SELECT Account.accountName, Account.entityName, Account.description, Account.debitDefault, Account.active, CapitalAccount.ownership, CapitalAccount.capitalEntityName FROM CapitalAccount CapitalAccount JOIN Account Account ON CapitalAccount.accountName = Account.accountName AND CapitalAccount.entityName = Account.entityName WHERE ";

  public com.poesys.accounting.db.account.ICapitalAccount getData(IPrimaryKey key, ResultSet rs) {
    try {
    return com.poesys.accounting.db.account.AccountFactory.getCapitalAccountData(key, rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      logger.error("Error getting data", e);
      throw new com.poesys.db.DbErrorException("Error getting data", e);
    }
  }

  @Override
  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("Account");
  }
}