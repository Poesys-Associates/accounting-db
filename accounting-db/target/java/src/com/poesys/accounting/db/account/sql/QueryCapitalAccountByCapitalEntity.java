/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: QueryAssociatedOneToManyObjects.vsl

package com.poesys.accounting.db.account.sql;


/**
 * <p>
 * A query of a collection of CapitalAccount objects using the primary key of 
 * an associated CapitalEntity object. This class is the concrete 
 * subclass of the generated abstract class AbstractQueryCapitalAccountBy${query.foriegnTypeName}.
 *</p>
 * <p>
 * Make any changes to query behavior by overriding methods here rather than 
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class QueryCapitalAccountByCapitalEntity extends AbstractQueryCapitalAccountByCapitalEntity {
  /** SQL query statement for CapitalAccount */
  private static final String SQL =
    "SELECT CapitalAccount.accountName, CapitalAccount.entityName, Account.description, Account.debitDefault, Account.active, CapitalAccount.ownership, CapitalAccount.capitalEntityName FROM CapitalAccount CapitalAccount JOIN Account Account ON CapitalAccount.accountName = Account.accountName AND CapitalAccount.entityName = Account.entityName WHERE CapitalAccount.capitalEntityName = ?";

  @Override
  public String getSql() {
    return SQL;
  }
}