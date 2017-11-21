/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the CapitalAccount. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * 
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class BsCapitalAccount extends com.poesys.accounting.bs.account.AbstractBsCapitalAccount {
  /**
   * Create a BsCapitalAccount object from a CapitalAccount object.
   * 
   * @param dto the data-access layer CapitalAccount DTO
   * @throws DelegateException when there is a problem creating the CapitalAccount
   */
  public BsCapitalAccount(com.poesys.accounting.db.account.ICapitalAccount dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsCapitalAccount. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the CapitalAccount
   * @param accountName the account name; unique within the entity
   * @param entityName composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param description text description of the nature of the account
   * @param debitDefault whether the account transaction items default to a debit or credit item; chosen
as the "usual" value for items in this account
   * @param active whether the account is active at the present time; an inactive account does not
appear in lists of accounts available through the user interface but does appear
in reports where referenced by items
   * @param ownership the percentage ownership of the entity expressed as a decimal number (.1 == 10%)
   * @param capitalEntityName foreign key used by setter to query associated object
   */
  public BsCapitalAccount(IPrimaryKey key, java.lang.String accountName, java.lang.String entityName, java.lang.String description, java.lang.Boolean debitDefault, java.lang.Boolean active, java.lang.Double ownership, java.lang.String capitalEntityName) {
    super(key, accountName, entityName, description, debitDefault, active, ownership, capitalEntityName); 
  }
}