/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.account;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * FiscalYearAccount. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * An account during a particular accounting period; a chart of accounts for an
 * accounting period consists of all the accounts that are current and active
 * during the specific accounting period
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class FiscalYearAccountProxy extends AbstractFiscalYearAccountProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a FiscalYearAccountProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public FiscalYearAccountProxy(FiscalYearAccount dto) {
    super(dto); 
  }
}