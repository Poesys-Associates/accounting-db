/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: IDbDto.vsl

package com.poesys.accounting.db.transaction;

import com.poesys.accounting.db.transaction.json.JsonReimbursement;
import com.poesys.db.dto.IDbDto;

import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer-object interface for the Reimbursement. 
 * The data-transfer-object class and proxy class implement this interface, as
 * does the business-layer data-transfer-object class.
 * </p>
 * <p>
 * A link between a receivable item and another receivable item that reimburses the
 * first one; the first one is a debit against a receivable account, the second one
 * is a credit against the same account. The accounts associated with related items
 * must be the same. One receivable item can be reimbursed by several reimbursement
 * items, and one reimbursement item can reimburse several receivables. For
 * example, you take a business trip and have 15 separate items that you submit as
 * receivables from the company. The company pays you with a single expense check:
 * that creates 15 links to the various receivables. If it pays you with a couple
 * of different checks, there would still be 15 links, but there would be multiple
 * reimbursement items in the set as well as multiple receivable items. A link has
 * a reimbursement amount that must be less than or equal to the reimbursement
 * item's amount (part or all of the amount constitutes the reimbursement); the set
 * of links for one reimbursement item must have reimbursement amounts that sum to
 * less than the item amount. There can also be allocated amounts that aren't
 * reimbursed; those are not part of the summation validation, but they do
 * participate in sums of amounts applied to the receivable--the sum of all link
 * reimbursement and allocated amounts for a specific receivable item should be
 * less than or equal to the receivable amount; less than means the receivable was
 * not entirely reimbursed and requires additional payments (or writing off). You
 * can have a reimbursement link with a zero reimbursement amount and a positive
 * allocated amount.
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
public interface IReimbursement extends IDbDto {

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
   * Get a JSON DTO for the Reimbursement.
   *
   * @return a JSON DTO
   */
  JsonReimbursement getJson();

  /**
   * <p>
   * the amount of the receivable item amount that is reimbursed by the reimbursement
   * item (must be less than or equal to the reimbursement amount)
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: reimbursedAmount
   * </p>
   * @return a java.lang.Double reimbursedAmount
   */
  public java.lang.Double getReimbursedAmount();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the reimbursedAmount.
   * </p>
   *
   * @param reimbursedAmount the value to set into the reimbursedAmount
   * @throws com.poesys.db.InvalidParametersException when the reimbursedAmount 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setReimbursedAmount(java.lang.Double reimbursedAmount) throws com.poesys.db.InvalidParametersException;


  /**
   * <p>
   * dollar amount not reimbursed by any reimbursement item; this "writes off" the
   * allocated amount when summing up amounts applied against the receivable
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * Owning DTO: ${property.dto.name}
   * Owning package: ${property.dto.packageName}
   * Property prefix: allocatedAmount
   * </p>
   * @return a java.lang.Double allocatedAmount
   */
  public java.lang.Double getAllocatedAmount();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not false
// Read/Write property: true

  /**
   * <p>
   * Set the allocatedAmount.
   * </p>
   *
   * @param allocatedAmount the value to set into the allocatedAmount
   * @throws com.poesys.db.InvalidParametersException when the allocatedAmount 
   *       value is null
   * @throws com.poesys.db.dto.DtoStatusException when the status cannot be set to CHANGED
   */
  public void setAllocatedAmount(java.lang.Double allocatedAmount) throws com.poesys.db.InvalidParametersException;


  /**
   * <p>
   * Associated object
   * </p>
   * <p>
   * Added by AddAssociationKeyObjectProperties
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: reimbursingItems
   * </p>
   * <p>
   * This property loads lazily on demand rather than when you first query the 
   * IReimbursement object.
   * </p>
   * @return a com.poesys.accounting.db.transaction.IItem reimbursingItem
   */
  public com.poesys.accounting.db.transaction.IItem getReimbursingItem();

  /**
   * Clear the reimbursingItem so that it will lazily reload.
   */
  public void clearReimbursingItem();
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
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: receivables
   * </p>
   * <p>
   * This property loads lazily on demand rather than when you first query the 
   * IReimbursement object.
   * </p>
   * @return a com.poesys.accounting.db.transaction.IItem receivable
   */
  public com.poesys.accounting.db.transaction.IItem getReceivable();

  /**
   * Clear the receivable so that it will lazily reload.
   */
  public void clearReceivable();
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
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: receivables
   * </p>
   * @return a java.lang.Integer receivablesOrderNumber
   */
  public java.lang.Integer getReceivablesOrderNumber();
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
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass + AddAssociationKeyAttributeProperties
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: reimbursingItems
   * </p>
   * @return a java.lang.Integer reimbursingItemsOrderNumber
   */
  public java.lang.Integer getReimbursingItemsOrderNumber();
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
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: receivables
   * </p>
   * @return a java.math.BigInteger receivablesTransactionId
   */
  public java.math.BigInteger getReceivablesTransactionId();
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
   * Added by AddGeneratedKeyProperties + AddParentKeyAttributes + AddAssociationKeyAttributeProperties
   * Owning DTO: Item
   * Owning package: com.poesys.accounting.db.transaction
   * Property prefix: reimbursingItems
   * </p>
   * @return a java.math.BigInteger reimbursingItemsTransactionId
   */
  public java.math.BigInteger getReimbursingItemsTransactionId();
// Setter here if the four conditions below are all true or not false
// Read/Write DTO: true
// Mutable DTO: not false
// Mutable property: not true
// Read/Write property: false
}