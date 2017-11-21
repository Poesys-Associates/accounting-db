/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the AccountGroup. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
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
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountGroup extends AbstractAccountGroup {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a AccountGroup as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public AccountGroup() {
    super(); 
  }

  /**
   * <p>
   * Create a AccountGroup. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the AccountGroup
   * @param name composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param orderNumber the relative position of the account group in the ordered list of groups
belonging to the account type
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   */
  public AccountGroup(IPrimaryKey key, java.lang.String name, java.lang.Integer orderNumber, java.lang.String groupName) {
    super(key, name, orderNumber, groupName); 
  }
}