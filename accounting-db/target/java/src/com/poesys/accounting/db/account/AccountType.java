/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the AccountType. This class
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
public class AccountType extends AbstractAccountType {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a AccountType as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public AccountType() {
    super(); 
  }

  /**
   * <p>
   * Create a AccountType. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the AccountType
   * @param name the account type name (Assets, Liabilities, Equity, Income, Expense)
   */
  public AccountType(IPrimaryKey key, java.lang.String name) {
    super(key, name); 
  }
}