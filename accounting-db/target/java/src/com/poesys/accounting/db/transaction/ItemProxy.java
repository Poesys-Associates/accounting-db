/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.transaction;

import com.poesys.accounting.db.transaction.json.JsonItem;

/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Item. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * A specific amount of value associated with a specific account within a
 * transaction parent; the item debits and credits must cancel each other out for
 * the set of all items belonging to the transaction (the transaction must
 * "balance"); the transaction orders the set of items according to an integer
 * order number, which is part of the primary key
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class ItemProxy extends AbstractItemProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a ItemProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public ItemProxy(Item dto) {
    super(dto); 
  }

  @Override
  public JsonItem getJson() {
    return ((Item)dto).getJson();
  }
}