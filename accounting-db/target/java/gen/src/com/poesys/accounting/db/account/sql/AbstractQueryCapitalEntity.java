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
 * CapitalEntity. This SQL specification contains a SQL statement that queries
 * a single CapitalEntity object from the database using the primary key.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractQueryCapitalEntity implements IKeyQuerySql<com.poesys.accounting.db.account.ICapitalEntity> {
  private static final Logger logger = Logger.getLogger(AbstractQueryCapitalEntity.class);
  /** SQL query statement for CapitalEntity */
  private static final String SQL =
    "SELECT capitalEntityName FROM CapitalEntity WHERE ";

  public com.poesys.accounting.db.account.ICapitalEntity getData(IPrimaryKey key, ResultSet rs) {
    try {
    return com.poesys.accounting.db.account.AccountFactory.getCapitalEntityData(key, rs);
    } catch (com.poesys.db.InvalidParametersException | SQLException e) {
      logger.error("Error getting data", e);
      throw new com.poesys.db.DbErrorException("Error getting data", e);
    }
  }

  @Override
  public String getSql(IPrimaryKey key) {
    return SQL + key.getSqlWhereExpression("");
  }
}