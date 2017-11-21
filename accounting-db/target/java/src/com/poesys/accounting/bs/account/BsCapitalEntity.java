/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the CapitalEntity. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
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
public class BsCapitalEntity extends com.poesys.accounting.bs.account.AbstractBsCapitalEntity {
  /**
   * Create a BsCapitalEntity object from a CapitalEntity object.
   * 
   * @param dto the data-access layer CapitalEntity DTO
   * @throws DelegateException when there is a problem creating the CapitalEntity
   */
  public BsCapitalEntity(com.poesys.accounting.db.account.ICapitalEntity dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsCapitalEntity. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the CapitalEntity
   * @param name 
   */
  public BsCapitalEntity(IPrimaryKey key, java.lang.String name) {
    super(key, name); 
  }
}