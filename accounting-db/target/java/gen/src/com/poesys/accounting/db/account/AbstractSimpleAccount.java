/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDto.vsl

package com.poesys.accounting.db.account;


import org.apache.log4j.Logger;

import com.poesys.db.pk.IPrimaryKey;

/**
 * <p>
 * A data-access layer data-transfer object for the SimpleAccount. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractSimpleAccount extends com.poesys.accounting.db.account.Account implements ISimpleAccount {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractSimpleAccount.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractSimpleAccount> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractSimpleAccount>();

  

  /**
   * Create an empty SimpleAccount for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractSimpleAccount() {
    super();
    abstractClass = false;
    createInserters();
  }

  /**
   * Create a SimpleAccount. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the SimpleAccount
   * @param accountName the account name; unique within the entity
   * @param entityName composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param description text description of the nature of the account
   * @param debitDefault whether the account transaction items default to a debit or credit item; chosen
as the "usual" value for items in this account
   * @param active whether the account is active at the present time; an inactive account does not
appear in lists of accounts available through the user interface but does appear
in reports where referenced by items
   * @param receivable whether this account is a receivable account, representing an asset that is a
debt owed to the accounting entity
   */
  public AbstractSimpleAccount(IPrimaryKey key, java.lang.String accountName, java.lang.String entityName, java.lang.String description, java.lang.Boolean debitDefault, java.lang.Boolean active, java.lang.Boolean receivable) {
    super(key, entityName, accountName, description, debitDefault, active);

    this.receivable = receivable;

    if (receivable == null) {
      throw new com.poesys.db.InvalidParametersException("receivable is required for " + key.getValueList());
    }
    
    abstractClass = false;
    createInserters();
  }

  /**
   * Read an object from an input stream, de-serializing it. Each generated
   * class must have this private method, which the deserialize method calls
   * through Java reflection on the specific class. The class calls a shared
   * code method to run the readObjectSetters.
   * 
   * @param in the object input stream
   * @throws ClassNotFoundException when a nested object class can't be found
   * @throws IOException when there is an IO problem reading the stream
   */
  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException,
      ClassNotFoundException {
    logger.debug("Deserializing object of class " + this.getClass().getName()
                 + " with readObject in AbstractSimpleAccount");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }
   
  /**
   * Nested property receivable
   *
   * <p>
   * whether this account is a receivable account, representing an asset that is a
   * debt owed to the accounting entity
   * </p>
   *
   * Added by AddLocalAttributeProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private java.lang.Boolean receivable;
  
  /**
   * Get an object of java.lang.Boolean.
   *
   * Source: AddLocalAttributeProperties
   * 
   * @return a java.lang.Boolean
   */

  public java.lang.Boolean getReceivable() {
    return receivable;
  }

  /**
   * Clear the receivable data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearReceivable() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the receivable.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * whether this account is a receivable account, representing an asset that is a
   * debt owed to the accounting entity
   * </p>
   *
   * @param receivable the value with which to set the property
   */
  public void setReceivable(java.lang.Boolean receivable)
      throws com.poesys.db.InvalidParametersException {
    if (receivable == null) {
      throw new com.poesys.db.InvalidParametersException("receivable is required");
    }
    
    this.receivable = receivable;
    setChanged();
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event) {
  }

  /**
   * Create the inserters for the SimpleAccount and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.ISimpleAccount> simpleAccountFactory =
      manager.getFactory("com.poesys.accounting.db.account.SimpleAccount",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ISimpleAccount> sql =
      new com.poesys.accounting.db.account.sql.InsertSimpleAccount();
    com.poesys.db.dao.insert.IInsert<ISimpleAccount> inserter =
      simpleAccountFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}