/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.poesys.db.AbstractJsonObject;
import com.poesys.db.col.json.JsonColumnValue;
import com.poesys.db.pk.json.JsonPrimaryKey;

import java.math.BigInteger;
import java.util.List;

/**
 * A JSON Data Transfer Object corresponding to the Item class
 */
public class JsonItem extends AbstractJsonObject {
  /** JSON primary key for the object */
  private JsonPrimaryKey primaryKey;
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
  /**
   * a list of the JSON primary keys for reimbursed receivable items if this is a reimbursing
   * item
   */
  private List<JsonPrimaryKey> receivableKeys;
  /** a list of the reimbursement objects if this is a reimbursing item */
  private List<JsonReimbursement> reimbursingItemsReimbursements;
  /** a list of the reimbursement objects if this is a receivable item */
  private List<JsonReimbursement> receivableReimbursements;
  /** a list of the JSON primary keys for reimbursing items if this is a receivable item */
  private List<JsonPrimaryKey> reimbursingItemKeys;
  /**
   * the fully qualified class name for the account DB DTO concrete subclass to instantiate in
   * the item DB DTO
   */
  private String accountClassName;

  /**
   * Create an empty JsonItem object for instantiation using GSON.
   */
  public JsonItem() {
    super(AbstractJsonObject.EXISTING);
  }

  /**
   * Create a JsonItem.
   *
   * @param primaryKey                     the JSON primary key for the item
   * @param amount                         the monetary amount in dollars of the transaction item
   * @param debit                          whether the item is a debit (true) or credit
   *                                       (false); default value is the
   *                                       debit-default value of the account
   * @param checked                        whether the value and details of the transaction
   *                                       item have been verified
   *                                       and reconciled
   * @param accountKey                     the JSON primary key for the account for the item
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
   * @param accountClassName               the fully qualified class name for the account
   *                                       DB DTO concrete subclass to instantiate for
   *                                       the item DB DTO
   */
  public JsonItem(JsonPrimaryKey primaryKey, Double amount, Boolean debit, Boolean checked,
                  JsonPrimaryKey accountKey, String accountName, List<JsonPrimaryKey>
                    receivableKeys, List<JsonReimbursement> receivableReimbursements,
                  List<JsonPrimaryKey> reimbursingItemKeys, List<JsonReimbursement>
                    reimbursingItemsReimbursements, String accountClassName) {
    super(AbstractJsonObject.EXISTING);
    // Test the inputs for required values.
    assert amount != null;
    assert accountKey != null;
    assert accountName != null && !accountName.isEmpty();

    // Use flag defaults if null
    this.debit = debit == null ? true : debit;
    this.checked = checked == null ? false : checked;

    this.primaryKey = primaryKey;
    this.amount = amount;
    this.accountKey = accountKey;
    this.accountName = accountName;
    this.accountClassName = accountClassName;
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

    // Compare primary keys.
    return primaryKey.equals(other.primaryKey);
  }

  @Override
  public int hashCode() {
    // Hash the fully qualified class name and the primary key.
    int result = getClass().getName().hashCode();
    result = 31 * result + primaryKey.hashCode();
    return result;
  }

  /**
   * Get the debit flag
   *
   * @return true if debit, false if credit
   */
  public Boolean getDebit() {
    return debit;
  }

  /**
   * Get the checked flag
   *
   * @return true if checked, false if not checked
   */
  public Boolean getChecked() {
    return checked;
  }

  /**
   * Get the transaction id for the parent transaction.
   *
   * @return a transaction id
   */
  public BigInteger getTransactionId() {
    // Extract the transaction id from the primary key.
    JsonPrimaryKey parentKey = primaryKey.getParentKey();
    return new BigInteger(parentKey.getColumnValueList().get(0).getValue());
  }

  /**
   * Get the item amount.
   *
   * @return a dollar amount
   */
  public Double getAmount() {
    return amount;
  }

  /**
   * Get the primary key of the item account.
   *
   * @return a primary key
   */
  public JsonPrimaryKey getAccountKey() {
    return accountKey;
  }

  /**
   * Get the fully qualified class name of the account DB DTO subclass to instantiate for the
   * item DB DTO
   */
  public String getAccountClassName() {
    return accountClassName;
  }

  /**
   * Get the account name.
   *
   * @return a name
   */
  public String getAccountName() {
    return accountName;
  }

  /**
   * Get the list of receivable keys for a reimbursing item.
   *
   * @return a list of primary keys or null if this item is not a reimbursing item
   */
  public List<JsonPrimaryKey> getReceivableKeys() {
    return receivableKeys;
  }

  /**
   * Get the list of Reimbursement objects for a reimbursing item.
   *
   * @return a list of Reimbursement objects or null if this item is not a reimbursing item
   */
  public List<JsonReimbursement> getReimbursingItemsReimbursements() {
    return reimbursingItemsReimbursements;
  }

  /**
   * Get the list of Reimbursement objects for a receivable item.
   *
   * @return a list of Reimbursement objects or null if this item is not a receivable item
   */
  public List<JsonReimbursement> getReceivableReimbursements() {
    return receivableReimbursements;
  }

  /**
   * Get the list of reimbursing item keys for a receivable item.
   *
   * @return a list of primary keys for reimbursing items or null if this is not a receivable item
   */
  public List<JsonPrimaryKey> getReimbursingItemKeys() {
    return reimbursingItemKeys;
  }

  /**
   * Get the order number for the child item within the parent transaction.
   *
   * @return an integer order number
   */
  public Integer getOrderNumber() {
    // Extract the order number from the primary key.
    JsonPrimaryKey childKey = primaryKey.getChildKey();
    List<JsonColumnValue> columnValueList = childKey.getColumnValueList();

    return new Integer(columnValueList.get(0).getValue());
  }
}
