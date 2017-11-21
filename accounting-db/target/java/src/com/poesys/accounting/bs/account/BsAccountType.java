/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the AccountType. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsAccountType extends com.poesys.accounting.bs.account.AbstractBsAccountType {
  /**
   * Create a BsAccountType object from a AccountType object.
   * 
   * @param dto the data-access layer AccountType DTO
   * @throws DelegateException when there is a problem creating the AccountType
   */
  public BsAccountType(com.poesys.accounting.db.account.IAccountType dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsAccountType. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the AccountType
   * @param name the account type name (Assets, Liabilities, Equity, Income, Expense)
   */
  public BsAccountType(IPrimaryKey key, java.lang.String name) {
    super(key, name); 
  }
}