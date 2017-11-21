/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.account;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * AccountType. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountTypeProxy extends AbstractAccountTypeProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a AccountTypeProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public AccountTypeProxy(AccountType dto) {
    super(dto); 
  }
}