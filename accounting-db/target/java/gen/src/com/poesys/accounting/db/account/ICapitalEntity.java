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
 * A data-access layer data-transfer-object interface for the CapitalEntity. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * 
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
public interface ICapitalEntity extends IDbDto {

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
   * 
   * <p>
   * Added by AddNaturalKeyProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: capitalEntityName
   * </p>
   * @return a java.lang.String capitalEntityName
   */
  public java.lang.String getCapitalEntityName();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: false

  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: CapitalAccount
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: capitalAccount
   * </p>
   * @return a java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount
   */
  public java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> getCapitalAccount();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the capitalAccount.
   * </p>
   *
   * @param capitalAccount the value to set into the capitalAccount
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setCapitalAccount(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount) ;


  /**
   * 
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Owning DTO: DistributionAccount
   * Owning package: com.poesys.accounting.db.account
   * Property prefix: distributionAccount
   * </p>
   * @return a java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount
   */
  public java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> getDistributionAccount();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the distributionAccount.
   * </p>
   *
   * @param distributionAccount the value to set into the distributionAccount
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setDistributionAccount(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount) ;


  /**
   * Add a CapitalAccount object to the capitalAccount collection.
   *
   * @param object the object to add to the collection
   */
  public void addCapitalAccountCapitalAccount(com.poesys.accounting.db.account.ICapitalAccount object);

  /**
   * Add a DistributionAccount object to the distributionAccount collection.
   *
   * @param object the object to add to the collection
   */
  public void addDistributionAccountDistributionAccount(com.poesys.accounting.db.account.IDistributionAccount object);
}