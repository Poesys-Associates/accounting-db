/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.transaction;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Reimbursement. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
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
 * 
 * @author Poesys/DB Cartridge
 */
public class BsReimbursement extends com.poesys.accounting.bs.transaction.AbstractBsReimbursement {
  /**
   * Create a BsReimbursement object from a Reimbursement object.
   * 
   * @param dto the data-access layer Reimbursement DTO
   * @throws DelegateException when there is a problem creating the Reimbursement
   */
  public BsReimbursement(com.poesys.accounting.db.transaction.IReimbursement dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsReimbursement. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Reimbursement
   * @param receivablesObject ${key.paramTag}
   * @param reimbursedItemsObject ${key.paramTag}
   * @param receivablesOrderNumber Attribute that is part of the association key
   * @param reimbursedItemsOrderNumber Attribute that is part of the association key
   * @param receivablesTransactionId Attribute that is part of the association key
   * @param reimbursedItemsTransactionId Attribute that is part of the association key
   * @param reimbursedAmount the amount of the receivable item amount that is reimbursed by the reimbursement
item (must be less than or equal to the reimbursement amount)
   * @param allocatedAmount dollar amount not reimbursed by any reimbursement item; this "writes off" the
allocated amount when summing up amounts applied against the receivable
   */
  public BsReimbursement(IPrimaryKey key, com.poesys.accounting.db.transaction.IItem receivablesObject, com.poesys.accounting.db.transaction.IItem reimbursedItemsObject, java.lang.Integer receivablesOrderNumber, java.lang.Integer reimbursedItemsOrderNumber, java.math.BigInteger receivablesTransactionId, java.math.BigInteger reimbursedItemsTransactionId, java.lang.Double reimbursedAmount, java.lang.Double allocatedAmount) {
    super(key, receivablesObject, reimbursedItemsObject, receivablesOrderNumber, reimbursedItemsOrderNumber, receivablesTransactionId, reimbursedItemsTransactionId, reimbursedAmount, allocatedAmount); 
  }
}