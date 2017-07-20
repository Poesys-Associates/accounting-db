/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractBsDto.vsl


package com.poesys.accounting.bs.account;


import com.poesys.bs.delegate.DelegateException;
import com.poesys.bs.dto.IDto;
import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.dto.AbstractDto;


import com.poesys.accounting.db.account.IEntity;
import com.poesys.accounting.db.account.EntityProxy;
import com.poesys.accounting.db.account.Entity;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the Entity. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
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
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsEntity 
    extends AbstractDto<com.poesys.accounting.db.account.IEntity> {

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsAccount objects from a Collection accounts of data-access-layer 
   * com.poesys.accounting.db.account.IAccount objects
   */
  private class BsAccountsCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.accounting.db.account.IAccount, com.poesys.accounting.bs.account.BsAccount> {
    @Override
    public com.poesys.accounting.bs.account.BsAccount get(com.poesys.accounting.db.account.IAccount dto) {
      return new com.poesys.accounting.bs.account.BsAccount(dto);
    }
  }

  /**
   * A Collection builder helper class for building a data-access-layer Collection 
   * of Account objects from an input Collection accounts of 
   * business-layer BsAccount objects
   */
  private class AccountsCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.accounting.bs.account.BsAccount, com.poesys.accounting.db.account.IAccount> {
    @Override
    public com.poesys.accounting.db.account.IAccount get(com.poesys.accounting.bs.account.BsAccount dto) {
      return dto.toDto();
    }
  }

  /**
   * Create a BsEntity object from a Entity object.
   * 
   * @param dto the data-access layer Entity DTO
   * @throws DelegateException when there is a problem creating the Entity
   */
  public AbstractBsEntity(IEntity dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a Entity from new data.
   *
   * @param key the primary key of the Entity
   * @param entityName the name of the accounting entity; unique among all entities
   */
  public AbstractBsEntity(IPrimaryKey key, java.lang.String entityName) {
    super(new EntityProxy(new Entity(key, entityName)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IEntity> other = (IDto<IEntity>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IEntity> o) {
    return dto.compareTo(o.toDto());
  }

  public void markChildrenDeleted() throws DtoStatusException {
    dto.markChildrenDeleted();
  }
  
  public IPrimaryKey getPrimaryKey() {
    return dto.getPrimaryKey();
  }

  // Data member properties

  /**
   * <p>
   * the name of the accounting entity; unique among all entities
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object Entity</li>
   * </ul>
   * @return a java.lang.String entityName
   */
  public java.lang.String getEntityName() {
    return dto.getEntityName();
  }

  /**
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Entity</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsAccount entityName
   */
  public java.util.Collection<com.poesys.accounting.bs.account.BsAccount> getAccounts() {
    BsAccountsCollectionBuilder builder = new BsAccountsCollectionBuilder();
    return builder.getCollection(dto.getAccounts());
  }

  /**
   * <p>
   * Set the accounts.
   * </p>
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object Entity</li>
   * </ul>
   * @param entityName the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setAccounts(java.util.Collection<com.poesys.accounting.bs.account.BsAccount> entityName) 
      throws com.poesys.db.dto.DtoStatusException{
    AccountsCollectionBuilder builder = new AccountsCollectionBuilder();
      dto.setAccounts(builder.getCollection(entityName));
  }

  /**
   * Add a Account object to the accounts collection.
   *
   * @param object the object to add to the collection
   */
  public void addAccountsAccount(com.poesys.accounting.bs.account.BsAccount object) {
    if (object == null) {
      throw new com.poesys.db.InvalidParametersException(com.poesys.db.Message.getMessage("com.poesys.db.dao.msg.no_dto", null));
    }
    
    dto.addAccountsAccount(object.toDto());
  }
}