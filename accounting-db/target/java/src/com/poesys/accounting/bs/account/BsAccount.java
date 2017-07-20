/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Account. This class
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
public class BsAccount extends com.poesys.accounting.bs.account.AbstractBsAccount {
  /**
   * Create a BsAccount object from a Account object.
   * 
   * @param dto the data-access layer Account DTO
   * @throws DelegateException when there is a problem creating the Account
   */
  public BsAccount(com.poesys.accounting.db.account.IAccount dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsAccount. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Account
   * @param entityName composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param accountName the account name; unique within the entity
   * @param description text description of the nature of the account
   * @param debitDefault whether the account transaction items default to a debit or credit item; chosen
as the "usual" value for items in this account
   * @param accountType the kind of account:
<ul>
<li>Asset: a kind of property with a value owned by the accounting entity</li>
<li>Liability: a kind of debt owed by the accounting entity to another
entity</li>
<li>Equity: a kind of fund invested by the accounting entity in the business;
the difference between value of assets and value of liabilities</li>
<li>Income: revenues paid to the accounting entity</li>
<li>Expense: money paid by the accounting entity to another entity</li>
</ul>
   * @param receivable whether this account is a receivable account, representing an asset that is a
debt owed to the accounting entity
   * @param active whether the account is active at the present time.
   * @param groupName foreign key used by setter to query associated object
   */
  public BsAccount(IPrimaryKey key, java.lang.String entityName, java.lang.String accountName, java.lang.String description, java.lang.Boolean debitDefault, java.lang.String accountType, java.lang.Boolean receivable, java.lang.Boolean active, java.lang.String groupName) {
    super(key, entityName, accountName, description, debitDefault, accountType, receivable, active, groupName); 
  }
}