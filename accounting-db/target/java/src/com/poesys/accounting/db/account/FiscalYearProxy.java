/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.account;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * FiscalYear. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * An accounting period, usually either coterminous with a calendar year with the
 * same name or varying over an annual period starting on a particular calendar
 * date within the year; identifies a complete accounting period for statements
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class FiscalYearProxy extends AbstractFiscalYearProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a FiscalYearProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public FiscalYearProxy(FiscalYear dto) {
    super(dto); 
  }
}