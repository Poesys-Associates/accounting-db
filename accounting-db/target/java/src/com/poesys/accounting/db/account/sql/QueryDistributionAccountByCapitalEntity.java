/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: QueryAssociatedOneToManyObjects.vsl

package com.poesys.accounting.db.account.sql;

/**
 * <p>
 * A query of a collection of DistributionAccount objects using the primary key
 * of an associated CapitalEntity object. This class is the concrete subclass of
 * the generated abstract class
 * AbstractQueryDistributionAccountBy${query.foriegnTypeName}.
 * </p>
 * <p>
 * Make any changes to query behavior by overriding methods here rather than
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class QueryDistributionAccountByCapitalEntity extends
    AbstractQueryDistributionAccountByCapitalEntity {
  /** SQL query statement for DistributionAccount */
  private static final String SQL =
    "SELECT DistributionAccount.accountName, DistributionAccount.entityName, Account.description, Account.debitDefault, Account.active, DistributionAccount.capitalEntityName FROM DistributionAccount DistributionAccount JOIN Account Account ON DistributionAccount.accountName = Account.accountName AND DistributionAccount.entityName = Account.entityName WHERE DistributionAccount.capitalEntityName = ?";

  @Override
  public String getSql() {
    return SQL;
  }
}