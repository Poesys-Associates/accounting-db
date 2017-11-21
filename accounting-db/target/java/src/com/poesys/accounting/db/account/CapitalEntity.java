/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the CapitalEntity. This class
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
public class CapitalEntity extends AbstractCapitalEntity {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a CapitalEntity as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public CapitalEntity() {
    super(); 
  }

  /**
   * <p>
   * Create a CapitalEntity. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the CapitalEntity
   * @param name 
   */
  public CapitalEntity(IPrimaryKey key, java.lang.String name) {
    super(key, name); 
  }
}