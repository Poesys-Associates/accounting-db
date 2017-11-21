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
 * DistributionAccount. This SQL specification contains a SQL statement that queries
 * a single DistributionAccount object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryDistributionAccount implements IKeyQuerySql<com.poesys.accounting.db.account.IDistributionAccount> {
  private static final Logger logger = Logger.getLogger(AbstractQueryDistributionAccount.class);
  /** SQL query statement for DistributionAccount */
  private static final String SQL =
    "SELECT Account.accountName, Account.entityName, Account.description, Account.debitDefault, Account.active, DistributionAccount.capitalEntityName FROM DistributionAccount DistributionAccount JOIN Account Account ON DistributionAccount.accountName = Account.accountName AND DistributionAccount.entityName = Account.entityName WHERE ";

  public com.poesys.accounting.db.account.IDistributionAccount getData(IPrimaryKey key, ResultSet rs) {
    try {
    return com.poesys.accounting.db.account.AccountFactory.getDistributionAccountData(key, rs);
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