/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDtoProxy.vsl

package com.poesys.accounting.db.transaction;


/**
 * <p>
 * A data-access layer proxy for the data-transfer object (DTO) for the 
 * Reimbursement. This class is the concrete subclass of the generated abstract 
 * class. Make any changes to proxy behavior by overriding methods here rather 
 * than changing the abstract superclass; AndroMDA will overwrite that class 
 * when you run it but will never overwrite this concrete subclass.
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
 * 
 * @author Poesys/DB Cartridge
 */
public class ReimbursementProxy extends AbstractReimbursementProxy {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a ReimbursementProxy as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   * @param dto the DTO to proxy
   */
  public ReimbursementProxy(Reimbursement dto) {
    super(dto); 
  }
}