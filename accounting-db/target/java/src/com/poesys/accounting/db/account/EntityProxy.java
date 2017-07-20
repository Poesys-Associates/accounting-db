/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.account;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Entity. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
 * </p>
 * <p>
 * The entity is the person or organization that owns all the accounts and the
 * transactions in them and is the basis for the accounting statements
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class EntityProxy extends AbstractEntityProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a EntityProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public EntityProxy(Entity dto) {
    super(dto); 
  }
}