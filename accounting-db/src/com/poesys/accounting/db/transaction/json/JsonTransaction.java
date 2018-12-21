/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.poesys.db.AbstractJsonObject;
import com.poesys.db.pk.json.JsonPrimaryKey;
import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

public class JsonTransaction extends AbstractJsonObject {
  /** logger for this class */
  private static final Logger logger = Logger.getLogger(JsonTransaction.class);
  /** unique integer identifier for the transaction */
  private JsonPrimaryKey primaryKey;
  /** description of the transaction */
  private String description;
  /** date of the transaction */
  private String transactionDate;
  /**
   * whether this is a balance transaction (one item per transaction) (true) or a regular
   * transaction (at least 2 items) (false)
   */
  private Boolean balance = true;
  /** whether this item has been reconciled/validated */
  private Boolean checked = false;
  /** the list of transaction items */
  private List<JsonItem> items = null;

  // messages
  private static final String INVALID_DATE_ERROR = "Invalid date in JSON data: ";

  /**
   * Create an empty JSON transaction object for use by GSON.
   */
  public JsonTransaction() {
    super(AbstractJsonObject.EXISTING);
  }

  /**
   * Create a JSON transaction object by
   *
   * @param primaryKey      unique identifier for the transaction
   * @param description     description of the transaction
   * @param transactionDate date of the transaction
   * @param balance         whether this is a balance transaction
   * @param checked         whether this item has been reconciled
   * @param items           the list of JSON items for the transaction
   */
  public JsonTransaction(JsonPrimaryKey primaryKey, String description, String transactionDate,
                         Boolean balance, Boolean checked, List<JsonItem> items) {
    super(AbstractJsonObject.EXISTING);
    this.primaryKey = primaryKey;
    this.description = description;
    this.transactionDate = transactionDate;
    this.balance = balance;
    this.checked = checked;
    this.items = items;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonTransaction that = (JsonTransaction)o;

    return primaryKey.equals(that.primaryKey);
  }

  @Override
  public int hashCode() {
    int result = getClass().getName().hashCode();
    return 31 * result + primaryKey.hashCode();
  }

  /**
   * Get the transaction date as a SQL timestamp, throwing a runtime exception if the date string
   * is not in the correct format.
   *
   * @return a SQL timestamp
   */
  public Timestamp getTransactionDate() {
    Date date;
    try {
      date = FORMAT.parse(transactionDate);
    } catch (ParseException e) {
      String message = INVALID_DATE_ERROR + transactionDate + "; format should be " + PATTERN;
      logger.error(message, e);
      throw new RuntimeException(message, e);
    }

    return new Timestamp(date.getTime());
  }

  /**
   * Get the JSON primary key object.
   *
   * @return a JSON primary key
   */
  public JsonPrimaryKey getPrimaryKey() {
    return primaryKey;
  }

  /**
   * Set the primary key of the JSON object. Primarily for use in unit testing to be able to set the primary key to a specific value or to make it null.
   *
   * @param key the key to set (possibly null)
   */
  public void setPrimaryKey(JsonPrimaryKey key) {
    this.primaryKey = key;
  }

  /**
   * Get the transaction id of the transaction.
   *
   * @return a BigInteger transaction id
   */
  public BigInteger getTransactionId() {
    return new BigInteger(primaryKey.getColumnValueList().get(0).getValue());
  }

  /**
   * Get the description.
   *
   * @return a text description
   */
  public String getDescription() {
    return description;
  }

  /**
   * Get the balance flag.
   *
   * @return the balance flag value
   */
  public Boolean getBalance() {
    return balance;
  }

  /**
   * Get the checked flag.
   *
   * @return the checked flag value
   */
  public Boolean getChecked() {
    return checked;
  }

  /**
   * Get the list of JSON items belonging to the transaction.
   *
   * @return a list of JSON items
   */
  public List<JsonItem> getItems() {
    return items;
  }
}
