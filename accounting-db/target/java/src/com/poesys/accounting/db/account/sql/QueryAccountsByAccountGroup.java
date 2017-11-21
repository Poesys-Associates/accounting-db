/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: QueryAssociatedOneToManyObjects.vsl

package com.poesys.accounting.db.account.sql;


/**
 * <p>
 * A query of a collection of FiscalYearAccount objects using the primary key of 
 * an associated AccountGroup object. This class is the concrete 
 * subclass of the generated abstract class AbstractQueryAccountsBy${query.foriegnTypeName}.
 *</p>
 * <p>
 * Make any changes to query behavior by overriding methods here rather than 
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class QueryAccountsByAccountGroup extends AbstractQueryAccountsByAccountGroup {
  /** SQL query statement for FiscalYearAccount */
  private static final String SQL =
    "SELECT FiscalYearAccount.accountName, FiscalYearAccount.entityName, FiscalYearAccount.year, FiscalYearAccount.orderNumber, FiscalYearAccount.accountType, FiscalYearAccount.groupOrderNumber FROM FiscalYearAccount WHERE FiscalYearAccount.accountType = ? AND FiscalYearAccount.groupOrderNumber = ?";

  @Override
  public String getSql() {
    return SQL;
  }
}