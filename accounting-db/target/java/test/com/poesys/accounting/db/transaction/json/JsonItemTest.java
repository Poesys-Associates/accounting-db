/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.accounting.db.transaction.TransactionFactory;
import com.poesys.db.AbstractJsonObject;
import com.poesys.db.col.json.BigIntegerJsonColumnValue;
import com.poesys.db.col.json.IntegerJsonColumnValue;
import com.poesys.db.col.json.JsonColumnValue;
import com.poesys.db.col.json.StringJsonColumnValue;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.json.*;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CUT: JsonItem
 */
public class JsonItemTest {
  private static final Logger logger = Logger.getLogger(JsonItemTest.class);

  private static final BigInteger TRANSACTION1 = new BigInteger("100");
  private static final BigInteger TRANSACTION2 = new BigInteger("200");

  private static final Integer ORDER = 1;
  private static final Integer ORDER2 = 2;
  private static final Double AMOUNT = 10.00D;

  private static final List<JsonColumnValue> ENTITY_LIST = new ArrayList<>();
  private static final String ENTITY = "Poesys Associates";
  private static final JsonColumnValue ENTITY_COLUMN_VALUE =
    new StringJsonColumnValue("entityName", "com.poesys.db.col.StringColumnValue", ENTITY);

  private static final String CHECKING = "Checking";
  private static final String RECEIVABLE = "Accounts Receivable";
  private static final JsonColumnValue CHECKING_COLUMN_VALUE =
    new StringJsonColumnValue("accountName", "com.poesys.db.col.StringColumnValue", CHECKING);
  private static final JsonColumnValue RECEIVABLE_COLUMN_VALUE =
    new StringJsonColumnValue("accountName", "com.poesys.db.col.StringColumnValue", RECEIVABLE);

  private static final List<JsonColumnValue> ACCOUNT_KEY_NAME_LIST = new ArrayList<>();

  private static final JsonPrimaryKey ACCOUNT_PARENT_KEY =
    new NaturalJsonPrimaryKey("com.poesys.accounting.db.account.Entity", ENTITY_LIST);
  private static final JsonPrimaryKey ACCOUNT_CHILD_KEY =
    new NaturalJsonPrimaryKey("com.poesys.accounting.db.account.Account", ACCOUNT_KEY_NAME_LIST);
  private static final JsonPrimaryKey ACCOUNT_KEY =
    new CompositeJsonPrimaryKey("com.poesys.accounting.db.account.Account", ACCOUNT_PARENT_KEY,
                                ACCOUNT_CHILD_KEY);

  private static final Double REIMBURSED_AMOUNT = 100.00D;
  private static final Double ALLOCATED_AMOUNT = 0.00D;

  /**
   * Test a JSON Item that is not a receivable or reimbursing item. Test default status.
   */
  @Test
  public void testRegularItem() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(item, JsonItem.class);
    // Pretty-print the JSON
    Gson prettyPrinter = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    logger.debug("Regular Item: " + prettyPrinter.toJson(item, JsonItem.class));
    assertTrue("invalid JSON string from item: " + json, json.equals(
      "{\"primaryKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.transaction.Item\",\"columnValueList\":null,\"value\":null," +
      "\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.SequencePrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.transaction.Transaction\"," +
      "\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col" +
      ".BigIntegerColumnValue\",\"value\":\"100\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Item\"," +
      "\"columnValueList\":[{\"name\":\"orderNumber\",\"type\":\"com.poesys.db.col" +
      ".IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},\"amount\":10.0,\"debit\":true,\"checked\":false," +
      "\"accountKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.account.Account\",\"columnValueList\":null,\"value\":null," +
      "\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.account.Entity\"," +
      "\"columnValueList\":[{\"name\":\"entityName\",\"type\":\"com.poesys.db.col" +
      ".StringColumnValue\",\"value\":\"Poesys Associates\"},{\"name\":\"entityName\"," +
      "\"type\":\"com.poesys.db.col.StringColumnValue\",\"value\":\"Poesys Associates\"}]," +
      "\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null}," +
      "\"childKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.account.Account\",\"columnValueList\":[{\"name\":\"accountName\"," +
      "\"type\":\"com.poesys.db.col.StringColumnValue\",\"value\":\"Checking\"}," +
      "{\"name\":\"accountName\",\"type\":\"com.poesys.db.col.StringColumnValue\"," +
      "\"value\":\"Checking\"}],\"value\":null,\"keyList\":null,\"parentKey\":null," +
      "\"childKey\":null}},\"accountName\":\"Checking\",\"receivableKeys\":null," +
      "\"reimbursingItemsReimbursements\":null,\"receivableReimbursements\":null," +
      "\"reimbursingItemKeys\":null,\"status\":\"EXISTING\"}"));
    // Create a new version of the Json item from the JSON string.
    JsonItem newItem = gson.fromJson(json, JsonItem.class);
    assertTrue("invalid JSON item from string", item.equals(newItem));
    assertTrue("status not EXISTING: " + item.getStatus(), "EXISTING".equals(item.getStatus()));
  }

  /**
   * Test a JSON Item that is a receivable item.
   */
  @Test
  public void testReceivableItem() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(RECEIVABLE_COLUMN_VALUE);

    List<JsonPrimaryKey> reimbursingItemKeys = getItemPrimaryKeys(TRANSACTION2, ORDER);

    IPrimaryKey reimbursementKey =
      TransactionFactory.getReimbursementPrimaryKey(ORDER, ORDER, TRANSACTION1, TRANSACTION2);
    List<JsonReimbursement> reimbursements =
      getReimbursements(reimbursementKey.getJsonPrimaryKey());

    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, RECEIVABLE, null,
                   null, reimbursingItemKeys, reimbursements);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(item, JsonItem.class);
    // Pretty-print the JSON
    Gson prettyPrinter = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    logger.debug("Receivable Item: " + prettyPrinter.toJson(item, JsonItem.class));
    assertNotNull("receivable item json is null", json);
    assertFalse("receivable item json is empty", json.isEmpty());
    // Create a new version of the Json item from the JSON string.
    JsonItem newItem = gson.fromJson(json, JsonItem.class);
    assertTrue("invalid JSON item from string", item.equals(newItem));
    assertTrue("status not EXISTING: " + item.getStatus(),
               newItem.getStatus().equals(AbstractJsonObject.EXISTING));
  }

  /**
   * Test a JSON Item that is a reimbursing item.
   */
  @Test
  public void testReimbursingItem() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(RECEIVABLE_COLUMN_VALUE);

    List<JsonPrimaryKey> receivableKeys = getItemPrimaryKeys(TRANSACTION1, ORDER);

    IPrimaryKey reimbursementKey =
      TransactionFactory.getReimbursementPrimaryKey(ORDER, ORDER, TRANSACTION1, TRANSACTION2);
    List<JsonReimbursement> reimbursements =
      getReimbursements(reimbursementKey.getJsonPrimaryKey());

    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, RECEIVABLE,
                   receivableKeys, reimbursements, null, null);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(item, JsonItem.class);
    // Pretty-print the JSON
    Gson prettyPrinter = new GsonBuilder().serializeNulls().setPrettyPrinting().create();
    logger.debug("Reimbursing Item: " + prettyPrinter.toJson(item, JsonItem.class));
    assertNotNull("reimbursing item json is null", json);
    assertFalse("reimbursing item json is empty", json.isEmpty());
    // Create a new version of the Json item from the JSON string.
    JsonItem newItem = gson.fromJson(json, JsonItem.class);
    assertTrue("invalid JSON item from string", item.equals(newItem));
    assertTrue("status not EXISTING: " + item.getStatus(),
               newItem.getStatus().equals(AbstractJsonObject.EXISTING));
  }

  /**
   * Get a test list of item primary keys.
   *
   * @param transactionId the BigInteger for the transaction identifier
   * @param orderNumber   the integer for the order number
   * @return a list of JSON primary keys with a single item key containing the transaction id and
   * order number
   */
  private List<JsonPrimaryKey> getItemPrimaryKeys(BigInteger transactionId, Integer orderNumber) {
    // Build list of reimbursement item keys with one composite item key
    List<JsonPrimaryKey> receivableReimbursementKeys = new ArrayList<>(1);
    List<JsonColumnValue> transactionColumnValueList = new ArrayList<>(1);
    List<JsonColumnValue> itemColumnValueList = new ArrayList<>(1);

    JsonColumnValue transactionColumnValue =
      new BigIntegerJsonColumnValue("transactionId", "com.poesys.db.col.BigIntegerColumnValue",
                                    transactionId.toString());
    transactionColumnValueList.add(transactionColumnValue);

    JsonPrimaryKey itemParentKey =
      new SequenceJsonPrimaryKey("com.poesys.accounting.db.transaction.Transaction",
                                 transactionColumnValueList);

    JsonColumnValue itemColumnValue =
      new IntegerJsonColumnValue("orderNumber", "com.poesys.db.col.IntegerColumnValue",
                                 orderNumber.toString());
    itemColumnValueList.add(itemColumnValue);
    JsonPrimaryKey reimbursementChildKey =
      new NaturalJsonPrimaryKey("com.poesys.accounting.db.transaction.Item", itemColumnValueList);

    JsonPrimaryKey reimbursementKey =
      new CompositeJsonPrimaryKey("com.poesys.accounting.db.transaction.Item", itemParentKey,
                                  reimbursementChildKey);
    receivableReimbursementKeys.add(reimbursementKey);
    return receivableReimbursementKeys;
  }

  /**
   * Get a test list of reimbursement link primary keys (receivable--reimbursement association)
   * with a single JSON reimbursement association object in the list.
   *
   * @param primaryKey the JSON primary key for the Reimbursement
   * @return a list of JSON primary keys with a single reimbursement association containing
   * the receivable and reimbursing-item keys
   */
  private List<JsonReimbursement> getReimbursements(JsonPrimaryKey primaryKey) {
    // Build the JSON reimbursement.
    JsonReimbursement reimbursement =
      new JsonReimbursement(primaryKey, REIMBURSED_AMOUNT, ALLOCATED_AMOUNT);

    // Build list of reimbursements with one association item key
    List<JsonReimbursement> reimbursements = new ArrayList<>(1);
    reimbursements.add(reimbursement);

    return reimbursements;
  }

  /**
   * Test error handling for the various required inputs to the JSON Item constructor: order
   * number, amount, account key, account name.
   */
  @Test
  public void testNullRequiredInputPrimaryKey() {
    try {
      new JsonItem(null, AMOUNT, true, false, ACCOUNT_KEY, RECEIVABLE, null, null, null, null);
      fail("no exception thrown for missing primary key");
    } catch (AssertionError e) {
      // success -- AssertionError thrown
    }
  }

  /**
   * Test error handling for the various required inputs to the JSON Item constructor: order
   * number, amount, account key, account name.
   */
  @Test
  public void testNullRequiredInputAmount() {
    try {
      IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
      new JsonItem(key.getJsonPrimaryKey(), null, true, false, ACCOUNT_KEY, RECEIVABLE, null, null,
                   null, null);
      fail("no exception thrown for missing amount");
    } catch (AssertionError e) {
      // success -- AssertionError thrown
    }
  }

  /**
   * Test error handling for the various required inputs to the JSON Item constructor: order
   * number, amount, account key, account name.
   */
  @Test
  public void testNullRequiredInputAccountKey() {
    try {
      IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, null, RECEIVABLE, null, null, null,
                   null);
      fail("no exception thrown for missing account key");
    } catch (AssertionError e) {
      // success -- AssertionError thrown
    }
  }

  /**
   * Test error handling for the various required inputs to the JSON Item constructor: order
   * number, amount, account key, account name.
   */
  @Test
  public void testNullRequiredInputAccountName() {
    try {
      IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, null, null, null,
                   null, null);
      fail("no exception thrown for missing account name");
    } catch (AssertionError e) {
      // success -- AssertionError thrown
    }
  }

  /**
   * Test a null debit flag input to the JSON Item constructor.
   */
  @Test
  public void testDefaultDebit() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, null, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    assertTrue("debit flag not set by default", item.getDebit());
  }

  /**
   * Test a null checked flag input to the JSON Item constructor.
   */
  @Test
  public void testDefaultChecked() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, null, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    assertFalse("checked flag not set by default", item.getChecked());
  }

  /**
   * Test equals() with the same object.
   */
  @Test
  public void testEqualsSameObject() {
    JsonItem object = new JsonItem();
    assertTrue("same object not equal", object.equals(object));
  }

  /**
   * Test equals() with a null comparison object.
   */
  @Test
  public void testEqualsNull() {
    JsonItem object = new JsonItem();
    assertFalse("null object equal", object.equals(null));
  }

  /**
   * Test equals with an object of a different class as comparison object.
   */
  @Test
  public void testEqualsDifferentClass() {
    JsonItem object = new JsonItem();
    Object other = new Object();
    assertFalse("different class of object equal", object.equals(other));
  }

  @Test
  public void testEqualsEquivalentObjects() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item1 =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    JsonItem item2 =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    assertTrue("equivalent items are not equal", item1.equals(item2));
  }

  @Test
  public void testNotEqualsTransactionId() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key1 = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item1 =
      new JsonItem(key1.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    IPrimaryKey key2 = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION2);
    JsonItem item2 =
      new JsonItem(key2.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, RECEIVABLE, null,
                   null, null, null);
    assertFalse("different items are equal (transaction id)", item1.equals(item2));
  }

  @Test
  public void testNotEqualsOrderNumber() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key1 = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item1 =
      new JsonItem(key1.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    IPrimaryKey key2 = TransactionFactory.getItemPrimaryKey(ORDER2, TRANSACTION1);
    JsonItem item2 =
      new JsonItem(key2.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    assertFalse("different items are equal (order number)", item1.equals(item2));
  }

  /**
   * Tests the hashcode() method, including at least one of the arrays to exercise the internal
   * code for generating deep array hashes
   */
  @Test
  public void testHashcode() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);

    List<JsonPrimaryKey> receivableKeys = getItemPrimaryKeys(TRANSACTION1, ORDER);
    IPrimaryKey reimbursementKey =
      TransactionFactory.getReimbursementPrimaryKey(ORDER, ORDER, TRANSACTION1, TRANSACTION2);
    List<JsonReimbursement> reimbursements =
      getReimbursements(reimbursementKey.getJsonPrimaryKey());

    IPrimaryKey key = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, RECEIVABLE,
                   receivableKeys, reimbursements, null, null);
    assertTrue("wrong hash code: " + item.hashCode(), item.hashCode() == 389924759);
  }

  @Test
  public void testDifferentObjectHashcodes() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    IPrimaryKey key1 = TransactionFactory.getItemPrimaryKey(ORDER, TRANSACTION1);
    JsonItem item =
      new JsonItem(key1.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    IPrimaryKey key2 = TransactionFactory.getItemPrimaryKey(ORDER2, TRANSACTION2);
    JsonItem item2 =
      new JsonItem(key2.getJsonPrimaryKey(), AMOUNT, true, false, ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    assertFalse("hash codes for different objects is the same",
                item.hashCode() == item2.hashCode());
  }
}