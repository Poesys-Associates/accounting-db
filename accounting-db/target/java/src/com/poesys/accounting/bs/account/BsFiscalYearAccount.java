/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the FiscalYearAccount. This class
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
public class BsFiscalYearAccount extends com.poesys.accounting.bs.account.AbstractBsFiscalYearAccount {
  /**
   * Create a BsFiscalYearAccount object from a FiscalYearAccount object.
   * 
   * @param dto the data-access layer FiscalYearAccount DTO
   * @throws DelegateException when there is a problem creating the FiscalYearAccount
   */
  public BsFiscalYearAccount(com.poesys.accounting.db.account.IFiscalYearAccount dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsFiscalYearAccount. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the FiscalYearAccount
   * @param accountsObject ${key.paramTag}
   * @param groupObject ${key.paramTag}
   * @param yearsObject ${key.paramTag}
   * @param accountName Attribute that is part of the association key
   * @param entityName Attribute that is part of the association key
   * @param year Attribute that is part of the association key
   * @param accountOrderNumber the integer rank order of the account within the associated account group for
this fiscal year; the numbers form an ordering of all the objects with the same
fiscal year, and different fiscal years start from one, so there are duplicate
order numbers across fiscal years
   * @param groupOrderNumber the integer rank order of the account group within the account type associated
with the group for this fiscal year; the numbers form a rank order for groups
within types for each fiscal year, and the rank order repeats for different
fiscal years, resulting in duplicate order numbers in different fiscal years
   * @param accountType foreign key used by setter to query associated object
   * @param groupName foreign key used by setter to query associated object
   * @param group the group into which the account is aggregated
   */
  public BsFiscalYearAccount(IPrimaryKey key, com.poesys.accounting.db.account.IAccount accountsObject, com.poesys.accounting.db.account.IAccountGroup groupObject, com.poesys.accounting.db.account.IFiscalYear yearsObject, java.lang.String accountName, java.lang.String entityName, java.lang.Integer year, java.lang.Integer accountOrderNumber, java.lang.Integer groupOrderNumber, java.lang.String accountType, java.lang.String groupName, com.poesys.accounting.db.account.IAccountGroup group) {
    super(key, accountsObject, groupObject, yearsObject, accountName, entityName, year, accountOrderNumber, groupOrderNumber, accountType, groupName, group); 
  }
}