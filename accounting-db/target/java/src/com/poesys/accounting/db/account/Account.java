/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Account. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * A division of the accounting system representing an individual accounting
 * element; divided into assets, liabilities, and equity on the balance sheet or
 * income or expense on the income statement; owned by an accounting entity
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>CompositeKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class Account extends AbstractAccount {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Account as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Account() {
    super(); 
  }

  /**
   * <p>
   * Create a Account. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Account
   * @param entityName composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param accountName the account name; unique within the entity
   * @param description text description of the nature of the account
   * @param debitDefault whether the account transaction items default to a debit or credit item; chosen
as the "usual" value for items in this account
   * @param active whether the account is active at the present time; an inactive account does not
appear in lists of accounts available through the user interface but does appear
in reports where referenced by items
   */
  public Account(IPrimaryKey key, java.lang.String entityName, java.lang.String accountName, java.lang.String description, java.lang.Boolean debitDefault, java.lang.Boolean active) {
    super(key, entityName, accountName, description, debitDefault, active); 
  }
}