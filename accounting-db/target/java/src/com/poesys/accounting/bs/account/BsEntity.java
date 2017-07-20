/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Entity. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * The entity is the person or organization that owns all the accounts and the
 * transactions in them and is the basis for the accounting statements
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
public class BsEntity extends com.poesys.accounting.bs.account.AbstractBsEntity {
  /**
   * Create a BsEntity object from a Entity object.
   * 
   * @param dto the data-access layer Entity DTO
   * @throws DelegateException when there is a problem creating the Entity
   */
  public BsEntity(com.poesys.accounting.db.account.IEntity dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsEntity. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Entity
   * @param entityName the name of the accounting entity; unique among all entities
   */
  public BsEntity(IPrimaryKey key, java.lang.String entityName) {
    super(key, entityName); 
  }
}