/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.dao.PoesysTrackingThread;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Entity. This class
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
public class Entity extends AbstractEntity {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Entity as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Entity() {
    super();
  }

  /**
   * <p>
   * Create a Entity. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Entity
   * @param entityName the name of the accounting entity; unique among all entities
   */
  public Entity(IPrimaryKey key, java.lang.String entityName) {
    super(key, entityName);
  }
}