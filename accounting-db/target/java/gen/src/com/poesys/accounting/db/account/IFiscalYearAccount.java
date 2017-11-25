/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: IDbDto.vsl

package com.poesys.accounting.db.account;

import com.poesys.db.dto.IDbDto;

import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer-object interface for the FiscalYearAccount. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * <p>
 * An account during a particular accounting period; a chart of accounts for an
 * accounting period consists of all the accounts that are current and active
 * during the specific accounting period
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>AssociationKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public interface IFiscalYearAccount extends IDbDto {

  boolean equals(Object arg0);

  int hashCode();

  /**
   * <p>
   * Mark any children of this DTO as deleted.
   * </p>
   */
  void markChildrenDeleted();

  IPrimaryKey getPrimaryKey();
  

  /**
   * <p>
   * the integer rank order of the account within the associated account group for
   * this fiscal year; the numbers form an ordering of all the objects with the same
   * fiscal year, and different fiscal years start from one, so there are duplicate
   * order numbers across fiscal years
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: accountOrderNumber
   * </p>
   * @return a java.lang.Integer accountOrderNumber
   */
  public java.lang.Integer getAccountOrderNumber();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the accountOrderNumber.
   * </p>
   *
   * @param accountOrderNumber the value to set into the accountOrderNumber
   * @throws com.poesys.db.InvalidParametersException when the accountOrderNumber 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setAccountOrderNumber(java.lang.Integer accountOrderNumber) throws com.poesys.db.InvalidParametersException;


  /**
   * <p>
   * the integer rank order of the account group within the account type associated
   * with the group for this fiscal year; the numbers form a rank order for groups
   * within types for each fiscal year, and the rank order repeats for different
   * fiscal years, resulting in duplicate order numbers in different fiscal years
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: groupOrderNumber
   * </p>
   * @return a java.lang.Integer groupOrderNumber
   */
  public java.lang.Integer getGroupOrderNumber();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the groupOrderNumber.
   * </p>
   *
   * @param groupOrderNumber the value to set into the groupOrderNumber
   * @throws com.poesys.db.InvalidParametersException when the groupOrderNumber 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setGroupOrderNumber(java.lang.Integer groupOrderNumber) throws com.poesys.db.InvalidParametersException;


  /**
   * <p>
   * the group into which the account is aggregated
   * </p>
   * <p>
   * Added by AddToOneAssociationRequiredObjectProperties
   * Owning DTO: AccountGroup
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: group
   * </p>
   * @return a com.poesys.accounting.db.account.IAccountGroup group
   */
  public com.poesys.accounting.db.account.IAccountGroup getGroup();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the group.
   * </p>
   *
   * @param group the value to set into the group
   * @throws com.poesys.db.InvalidParametersException when the group 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setGroup(com.poesys.accounting.db.account.IAccountGroup group) throws com.poesys.db.InvalidParametersException;


  /**
   * <p>
   * Foreign key used by setter to query associated object
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes + AddToOneAssociationAttributeProperties
   * Owning DTO: AccountGroup
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: group
   * </p>
   * @return a java.lang.String accountType
   */
  public java.lang.String getAccountType();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false

  /**
   * <p>
   * Foreign key used by setter to query associated object
   * </p>
   * <p>
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass + AddToOneAssociationAttributeProperties
   * Owning DTO: AccountGroup
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: group
   * </p>
   * @return a java.lang.String groupName
   */
  public java.lang.String getGroupName();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false

  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: FiscalYear
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: years
   * </p>
   * <p>
   * This property loads lazily on demand rather than when you first query the 
   * IFiscalYearAccount object.
   * </p>
   * @return a com.poesys.accounting.db.account.IFiscalYear fiscalYear
   */
  public com.poesys.accounting.db.account.IFiscalYear getFiscalYear();

  /**
   * Clear the fiscalYear so that it will lazily reload.
   */
  public void clearFiscalYear();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: Account
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: accounts
   * </p>
   * <p>
   * This property loads lazily on demand rather than when you first query the 
   * IFiscalYearAccount object.
   * </p>
   * @return a com.poesys.accounting.db.account.IAccount account
   */
  public com.poesys.accounting.db.account.IAccount getAccount();

  /**
   * Clear the account so that it will lazily reload.
   */
  public void clearAccount();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not true
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass + AddAssociationKeyAttributeProperties
   * Owning DTO: Account
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: accounts
   * </p>
   * @return a java.lang.String accountName
   */
  public java.lang.String getAccountName();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties
   * Owning DTO: Account
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: accounts
   * </p>
   * @return a java.lang.String entityName
   */
  public java.lang.String getEntityName();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false

  /**
   * <p>
   * Attribute that is part of the association key
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties + AddAssociationKeyAttributeProperties
   * Owning DTO: FiscalYear
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: years
   * </p>
   * @return a java.lang.Integer year
   */
  public java.lang.Integer getYear();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false
}