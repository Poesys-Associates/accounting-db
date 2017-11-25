/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the AccountGroup. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * A named group of fiscal-year accounts, grouping the accounts for presentation
 * and aggregation in financial statements for the fiscal year
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>CompositeKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsAccountGroup extends com.poesys.accounting.bs.account.AbstractBsAccountGroup {
  /**
   * Create a BsAccountGroup object from a AccountGroup object.
   * 
   * @param dto the data-access layer AccountGroup DTO
   * @throws DelegateException when there is a problem creating the AccountGroup
   */
  public BsAccountGroup(com.poesys.accounting.db.account.IAccountGroup dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsAccountGroup. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the AccountGroup
   * @param accountType composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   */
  public BsAccountGroup(IPrimaryKey key, java.lang.String accountType, java.lang.String groupName) {
    super(key, accountType, groupName); 
  }
}