/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.transaction;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.accounting.db.transaction.json.JsonItem;
import com.poesys.accounting.db.transaction.json.JsonReimbursement;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Item. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * A specific amount of value associated with a specific account within a
 * transaction parent; the item debits and credits must cancel each other out for
 * the set of all items belonging to the transaction (the transaction must
 * "balance"); the transaction orders the set of items according to an integer
 * order number, which is part of the primary key
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
public class BsItem extends com.poesys.accounting.bs.transaction.AbstractBsItem {
  /**
   * Create a BsItem object from a Item object.
   * 
   * @param dto the data-access layer Item DTO
   * @throws DelegateException when there is a problem creating the Item
   */
  public BsItem(com.poesys.accounting.db.transaction.IItem dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsItem. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Item
   * @param transactionId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param orderNumber 
   * @param amount the monetary amount in dollars of the transaction item
   * @param debit whether the item is a debit (true) or credit (false); default value is the
debit-default value of the account
   * @param checked whether the value and details of the transaction item have been verified and
reconciled
   * @param accountName foreign key used by setter to query associated object
   * @param entityName foreign key used by setter to query associated object
   */
  public BsItem(IPrimaryKey key, java.math.BigInteger transactionId, java.lang.Integer orderNumber, java.lang.Double amount, java.lang.Boolean debit, java.lang.Boolean checked, java.lang.String accountName, java.lang.String entityName) {
    super(key, transactionId, orderNumber, amount, debit, checked, accountName, entityName); 
  }

  @Override
  public String toString() {
    // Get the JSON object.
    JsonItem jsonItem = dto.getJson();
    // Set the JSON status from the current object status.
    jsonItem.setStatus(dto.getStatus());
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    return gson.toJson(jsonItem, JsonItem.class);
  }
}