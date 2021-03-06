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


import com.poesys.accounting.db.account.IAccountGroup;
import com.poesys.accounting.db.account.AccountGroupProxy;
import com.poesys.accounting.db.account.AccountGroup;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the AccountGroup. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * A named group of fiscal-year accounts, grouping the accounts for presentation
 * and aggregation in financial statements for the fiscal year
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>CompositeKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsAccountGroup 
    extends AbstractDto<com.poesys.accounting.db.account.IAccountGroup> {

  /**
   * A List builder helper class for building a business-layer List 
   * of BsFiscalYearAccount objects from a List accounts of data-access-layer 
   * com.poesys.accounting.db.account.IFiscalYearAccount objects
   */
  private class BsAccountsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.db.account.IFiscalYearAccount, com.poesys.accounting.bs.account.BsFiscalYearAccount> {
    @Override
    public com.poesys.accounting.bs.account.BsFiscalYearAccount get(com.poesys.accounting.db.account.IFiscalYearAccount dto) {
      return new com.poesys.accounting.bs.account.BsFiscalYearAccount(dto);
    }
  }

  /**
   * A List builder helper class for building a data-access-layer List 
   * of FiscalYearAccount objects from an input List accounts of 
   * business-layer BsFiscalYearAccount objects
   */
  private class AccountsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.bs.account.BsFiscalYearAccount, com.poesys.accounting.db.account.IFiscalYearAccount> {
    @Override
    public com.poesys.accounting.db.account.IFiscalYearAccount get(com.poesys.accounting.bs.account.BsFiscalYearAccount dto) {
      return dto.toDto();
    }
  }

  /**
   * Create a BsAccountGroup object from a AccountGroup object.
   * 
   * @param dto the data-access layer AccountGroup DTO
   * @throws DelegateException when there is a problem creating the AccountGroup
   */
  public AbstractBsAccountGroup(IAccountGroup dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a AccountGroup from new data.
   *
   * @param key the primary key of the AccountGroup
   * @param accountType composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   */
  public AbstractBsAccountGroup(IPrimaryKey key, java.lang.String accountType, java.lang.String groupName) {
    super(new AccountGroupProxy(new AccountGroup(key, accountType, groupName)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IAccountGroup> other = (IDto<IAccountGroup>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IAccountGroup> o) {
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
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes as data member
   * </p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @return a java.lang.String accountType
   */
  public java.lang.String getAccountType() {
    return dto.getAccountType();
  }

  /**
   * <p>
   * the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
   * Payable, Tax-Related Expenses
   * </p>
   * <p>
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass as data member
   * </p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @return a java.lang.String groupName
   */
  public java.lang.String getGroupName() {
    return dto.getGroupName();
  }

  /**
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsAccountType type
   */
  public com.poesys.accounting.bs.account.BsAccountType getType() {
    // Return 4
    return new com.poesys.accounting.bs.account.BsAccountType(dto.getType());
  }

  /**
   * <p>
   * Set the type.
   * </p>
   * 
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @param type the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter type is null
   */
  public void setType(com.poesys.accounting.bs.account.BsAccountType type) 
      throws com.poesys.db.dto.DtoStatusException , com.poesys.db.InvalidParametersException {
    dto.setType(type == null ? null : type.toDto());
  }

  /**
   * <p>
   * the set of accounts in the group
   * </p>
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsFiscalYearAccount type
   */
  public java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> getAccounts() {
    BsAccountsListBuilder builder = new BsAccountsListBuilder();
    return builder.getList(dto.getAccounts());
  }

  /**
   * <p>
   * Set the accounts.
   * </p>
   * <p>
   * the set of accounts in the group
   * </p>
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object AccountGroup</li>
   * </ul>
   * @param type the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   */
  public void setAccounts(java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> type) 
      throws com.poesys.db.dto.DtoStatusException{
    AccountsListBuilder builder = new AccountsListBuilder();
      dto.setAccounts(builder.getList(type));
  }

  /**
   * Add a FiscalYearAccount object to the accounts collection.
   *
   * @param object the object to add to the collection
   */
  public void addAccountsFiscalYearAccount(com.poesys.accounting.bs.account.BsFiscalYearAccount object) {
    if (object == null) {
      throw new com.poesys.db.InvalidParametersException(com.poesys.db.Message.getMessage("com.poesys.db.dao.msg.no_dto", null));
    }
    
    dto.addAccountsFiscalYearAccount(object.toDto());
  }
}