/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.account;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Account. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * A division of the accounting system representing an individual accounting
 * element; divided into assets, liabilities, and equity on the balance sheet or
 * income or expense on the income statement; owned by an accounting entity
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountProxy extends AbstractAccountProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a AccountProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public AccountProxy(Account dto) {
    super(dto); 
  }
}