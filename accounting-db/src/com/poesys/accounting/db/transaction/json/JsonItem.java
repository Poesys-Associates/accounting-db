/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.poesys.db.AbstractJsonObject;
import com.poesys.db.pk.json.JsonPrimaryKey;
import org.apache.log4j.Logger;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * A JSON Data Transfer Object corresponding to the Item class
 */
public class JsonItem extends AbstractJsonObject {
  /** unique identifier for the parent transaction; part of the primary key with orderNumber */
  private String transactionId;
  /** position of this item in the parent transaction; part of primary key with transaction id */
  private Integer orderNumber;
  /** amount of the debit/credit */
  private Double amount;
  /** whether this is a debit item (true) or a credit item (false) */
  private Boolean debit = true;
  /** whether this item has been reconciled/validated */
  private Boolean checked = false;
  /** the primary key JSON data for the account to which the item applies */
  private JsonPrimaryKey accountKey;
  /** the name of the account to which the item applies */
  private String accountName;
  /** a list of the JSON primary keys for reimbursed receivable items if this is a reimbursing
   * item */
  private List<JsonPrimaryKey> receivableKeys;
  /** a list of the reimbursement objects if this is a reimbursing item */
  private List<JsonReimbursement> reimbursingItemsReimbursements;
  /** a list of the reimbursement objects if this is a receivable item */
  private List<JsonReimbursement> receivableReimbursements;
  /** a list of the JSON primary keys for reimbursing items if this is a receivable item */
  private List<JsonPrimaryKey> reimbursingItemKeys;

  /**
   * Create an empty JsonItem object for instantiation using GSON.
   */
  public JsonItem() {
    super(AbstractJsonObject.EXISTING);
  }

  /**
   * Create a JsonItem.
   *
   * @param transactionId                  the unique identifier for the parent transaction that
   *                                       owns this item; part of the composite primary key
   *                                       along with order number
   * @param orderNumber                    the order of this item within the transaction in
   *                                       reports; part of the composite primary key along
   *                                       with the transaction id
   * @param amount                         the monetary amount in dollars of the transaction item
   * @param debit                          whether the item is a debit (true) or credit
   *                                       (false); default value is the
   *                                       debit-default value of the account
   * @param checked                        whether the value and details of the transaction
   *                                       item have been verified
   *                                       and reconciled
   * @param accountName                    name of the account for the item
   * @param receivableKeys                 a list of the primary keys for receivable items for
   *                                       this reimbursement, if this is a reimbursement;
   *                                       otherwise, null
   * @param receivableReimbursements       a list of the primary keys for reimbursements for
   *                                       this receivable, if this is a receivable;
   *                                       otherwise, null
   * @param reimbursingItemKeys            a list of the primary keys for the association
   *                                       objects linked to this receivable item; null if not
   *                                       a receivable item
   * @param reimbursingItemsReimbursements a list of the primary keys for the association
   *                                       objects linked to this reimbursing item; null if
   *                                       not a reimbursing item
   */
  public JsonItem(String transactionId, Integer orderNumber, Double amount, Boolean debit,
                  Boolean checked, JsonPrimaryKey accountKey, String accountName,
                  List<JsonPrimaryKey> receivableKeys, List<JsonReimbursement>
                    receivableReimbursements, List<JsonPrimaryKey> reimbursingItemKeys,
                  List<JsonReimbursement> reimbursingItemsReimbursements) {
    super(AbstractJsonObject.EXISTING);
    // Test the inputs for required values.
    assert transactionId != null;
    assert orderNumber != null;
    assert amount != null;
    assert accountKey != null;
    assert accountName != null && !accountName.isEmpty();

    // Use flag defaults if null
    this.debit = debit == null ? true : debit;
    this.checked = checked == null ? false : checked;

    this.transactionId = transactionId;
    this.orderNumber = orderNumber;
    this.amount = amount;
    this.accountKey = accountKey;
    this.accountName = accountName;
    this.receivableKeys = receivableKeys;
    this.receivableReimbursements = receivableReimbursements;
    this.reimbursingItemKeys = reimbursingItemKeys;
    this.reimbursingItemsReimbursements = reimbursingItemsReimbursements;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonItem other = (JsonItem)o;

    // Check primary key

    return transactionId.equals(other.transactionId) && orderNumber.equals(other.orderNumber);
  }

  @Override
  public int hashCode() {
    // Hash the fully qualified class name and the primary key fields.
    int result = getClass().getName().hashCode();
    result = 31 * result + transactionId.hashCode();
    result = 31 * result + orderNumber.hashCode();
    return result;
  }

  /**
   * Get the debit flag; used in unit testing only.
   *
   * @return true if debit, false if credit
   */
  Boolean getDebit() {
    return debit;
  }

  /**
   * Get the checked flag; used in unit testing only.
   *
   * @return true if checked, false if not checked
   */
  public Boolean getChecked() {
    return checked;
  }
}
