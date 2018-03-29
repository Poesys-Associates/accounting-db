/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.transaction;

import com.poesys.accounting.db.transaction.json.JsonTransaction;

/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Transaction. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the fiscal
 * year accounts must balance (cancel each other out with respect to debits and
 * credits)
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TransactionProxy extends AbstractTransactionProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a TransactionProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public TransactionProxy(Transaction dto) {
    super(dto); 
  }

  @Override
  public JsonTransaction getJson() {
    return ((Transaction)dto).getJson();
  }
}