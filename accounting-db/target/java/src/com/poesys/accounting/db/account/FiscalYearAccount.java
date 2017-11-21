/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the FiscalYearAccount. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * An account during a particular accounting period; a chart of accounts for an
 * accounting period consists of all the accounts that are current and active
 * during the specific accounting period
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class FiscalYearAccount extends AbstractFiscalYearAccount {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a FiscalYearAccount as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public FiscalYearAccount() {
    super(); 
  }

  /**
   * <p>
   * Create a FiscalYearAccount. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the FiscalYearAccount
   * @param accountsObject wraps an associated IAccount object
   * @param groupObject wraps an associated IAccountGroup object
   * @param yearsObject wraps an associated IFiscalYear object
   * @param accountName Attribute that is part of the association key
   * @param entityName Attribute that is part of the association key
   * @param year Attribute that is part of the association key
   * @param orderNumber the integer rank of the account within the associated account group; there will
be duplicates for the set of accounts in a fiscal year as there are multiple
account groups for the fiscal year
   * @param name foreign key used by setter to query associated object
   * @param groupOrderNumber foreign key used by setter to query associated object
   * @param group the group into which the account is aggregated
   */
  public FiscalYearAccount(IPrimaryKey key, com.poesys.accounting.db.account.IAccount accountsObject, com.poesys.accounting.db.account.IAccountGroup groupObject, com.poesys.accounting.db.account.IFiscalYear yearsObject, java.lang.String accountName, java.lang.String entityName, java.lang.Integer year, java.lang.Integer orderNumber, java.lang.String name, java.lang.Integer groupOrderNumber, com.poesys.accounting.db.account.IAccountGroup group) {
    super(key, accountsObject, groupObject, yearsObject, accountName, entityName, year, orderNumber, name, groupOrderNumber, group); 
  }
}