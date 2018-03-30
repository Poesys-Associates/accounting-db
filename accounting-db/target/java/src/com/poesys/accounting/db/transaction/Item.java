/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.transaction;

import com.poesys.accounting.db.transaction.json.JsonItem;
import com.poesys.accounting.db.transaction.json.JsonReimbursement;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKeyFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * A data-access layer data-transfer object for the Item. This class
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
 * <li>CompositeKey</li>
 * <li>Persistent</li>
 * </ul>
 *
 * @author Poesys/DB Cartridge
 */
public class Item extends AbstractItem {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Item as a new object. This constructor calls the abstract
   * superclass constructor.
   * </p>
   */
  public Item() {
    super();
  }

  /**
   * <p>
   * Create a Item. This constructor calls the abstract superclass
   * constructor.
   * </p>
   *
   * @param key           the primary key of the Item
   * @param transactionId composite super-key attribute that uniquely identifies child combined
   *                      with child sub-key and any other parent super-keys
   * @param orderNumber
   * @param amount        the monetary amount in dollars of the transaction item
   * @param debit         whether the item is a debit (true) or credit (false); default value is the
   *                      debit-default value of the account
   * @param checked       whether the value and details of the transaction item have been
   *                      verified and
   *                      reconciled
   * @param accountName   foreign key used by setter to query associated object
   * @param entityName    foreign key used by setter to query associated object
   */
  public Item(IPrimaryKey key, java.math.BigInteger transactionId, java.lang.Integer orderNumber,
              java.lang.Double amount, java.lang.Boolean debit, java.lang.Boolean checked, java
                .lang.String accountName, java.lang.String entityName) {
    super(key, transactionId, orderNumber, amount, debit, checked, accountName, entityName);
  }

  @Override
  public JsonItem getJson() {
    // Test for required primary key in item
    if (this.getAccount() == null) {
      throw new RuntimeException("no item account while getting JSON string: " + getAccountName());
    }
    if (this.getAccount().getPrimaryKey() == null) {
      throw new RuntimeException("no item account primary key while getting JSON string: " + getAccount());
    }
    List<JsonReimbursement> jsonReceivablesReimbursements = new ArrayList<>();
    List<JsonReimbursement> jsonReimbursingItemReimbursements = new ArrayList<>();

    List<JsonPrimaryKey> jsonReceivableKeys = JsonPrimaryKeyFactory.getList(receivableKeys);
    List<JsonPrimaryKey> jsonReimbursingItemKeys =
      JsonPrimaryKeyFactory.getList(reimbursingItemKeys);
    if (receivablesReimbursement != null && !receivablesReimbursement.isEmpty()) {
      for (IReimbursement reimbursement : receivablesReimbursement) {
        jsonReceivablesReimbursements.add(reimbursement.getJson());
      }
    }

    if (reimbursingItemsReimbursement != null && !reimbursingItemsReimbursement.isEmpty()) {
      for (IReimbursement reimbursement : reimbursingItemsReimbursement) {
        jsonReceivablesReimbursements.add(reimbursement.getJson());
      }
    }

    return new JsonItem(key.getJsonPrimaryKey(), getAmount(), getDebit(), getChecked(),
                        getAccount().getPrimaryKey().getJsonPrimaryKey(), getAccountName(),
                        jsonReceivableKeys, jsonReceivablesReimbursements, jsonReimbursingItemKeys,
                        jsonReimbursingItemReimbursements);
  }
}