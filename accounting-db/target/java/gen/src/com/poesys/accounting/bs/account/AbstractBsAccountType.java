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


import com.poesys.accounting.db.account.IAccountType;
import com.poesys.accounting.db.account.AccountTypeProxy;
import com.poesys.accounting.db.account.AccountType;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the AccountType. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
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
public abstract class AbstractBsAccountType 
    extends AbstractDto<com.poesys.accounting.db.account.IAccountType> {

  /**
   * A List builder helper class for building a business-layer List 
   * of BsAccountGroup objects from a List groups of data-access-layer 
   * com.poesys.accounting.db.account.IAccountGroup objects
   */
  private class BsGroupsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.db.account.IAccountGroup, com.poesys.accounting.bs.account.BsAccountGroup> {
    @Override
    public com.poesys.accounting.bs.account.BsAccountGroup get(com.poesys.accounting.db.account.IAccountGroup dto) {
      return new com.poesys.accounting.bs.account.BsAccountGroup(dto);
    }
  }

  /**
   * A List builder helper class for building a data-access-layer List 
   * of AccountGroup objects from an input List groups of 
   * business-layer BsAccountGroup objects
   */
  private class GroupsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.bs.account.BsAccountGroup, com.poesys.accounting.db.account.IAccountGroup> {
    @Override
    public com.poesys.accounting.db.account.IAccountGroup get(com.poesys.accounting.bs.account.BsAccountGroup dto) {
      return dto.toDto();
    }
  }

  /**
   * Create a BsAccountType object from a AccountType object.
   * 
   * @param dto the data-access layer AccountType DTO
   * @throws DelegateException when there is a problem creating the AccountType
   */
  public AbstractBsAccountType(IAccountType dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a AccountType from new data.
   *
   * @param key the primary key of the AccountType
   * @param accountType the account type name (Assets, Liabilities, Equity, Income, Expense)
   */
  public AbstractBsAccountType(IPrimaryKey key, java.lang.String accountType) {
    super(new AccountTypeProxy(new AccountType(key, accountType)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IAccountType> other = (IDto<IAccountType>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IAccountType> o) {
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
   * the account type name (Assets, Liabilities, Equity, Income, Expense)
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object AccountType</li>
   * </ul>
   * @return a java.lang.String accountType
   */
  public java.lang.String getAccountType() {
    return dto.getAccountType();
  }

  /**
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountType</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsAccountGroup accountType
   */
  public java.util.List<com.poesys.accounting.bs.account.BsAccountGroup> getGroups() {
    BsGroupsListBuilder builder = new BsGroupsListBuilder();
    return builder.getList(dto.getGroups());
  }

  /**
   * <p>
   * Set the groups.
   * </p>
   * 
   * <p>
   * Added by AddToManyChildCollectionProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountType</li>
   * </ul>
   * @param accountType the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter accountType is null
   */
  public void setGroups(java.util.List<com.poesys.accounting.bs.account.BsAccountGroup> accountType) 
      throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException {
    GroupsListBuilder builder = new GroupsListBuilder();
      dto.setGroups(builder.getList(accountType));
  }

  /**
   * Add a AccountGroup object to the groups collection.
   *
   * @param object the object to add to the collection
   */
  public void addGroupsAccountGroup(com.poesys.accounting.bs.account.BsAccountGroup object) {
    if (object == null) {
      throw new com.poesys.db.InvalidParametersException(com.poesys.db.Message.getMessage("com.poesys.db.dao.msg.no_dto", null));
    }
    
    dto.addGroupsAccountGroup(object.toDto());
  }
}