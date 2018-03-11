package com.poesys.accounting.db.transaction.json;/* Copyright (c) 2018 Poesys Associates. All
rights reserved. */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.db.AbstractJsonObject;
import com.poesys.db.col.json.JsonColumnValue;
import com.poesys.db.col.json.StringJsonColumnValue;
import com.poesys.db.pk.json.CompositeJsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKey;
import com.poesys.db.pk.json.NaturalJsonPrimaryKey;
import org.junit.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CUT: JsonTransaction
 */
public class JsonTransactionTest {

  private static final String ID1 = "100";
  private static final String ID2 = "200";
  private static final String DESCRIPTION = "transaction description";
  private static final String DATE = "2018-03-11 12:11:23.005";
  private static final String INVALID_DATE = "this is not a date";

  private static final Integer ORDER1 = 1;
  private static final Integer ORDER2 = 2;
  private static final Double AMOUNT = 10.00D;
  private static final String CHECKING = "Checking";
  private static final String FOOD = "Food";

  private static final List<JsonColumnValue> ENTITY_LIST = new ArrayList<>();
  private static final String ENTITY = "Poesys Associates";
  private static final JsonColumnValue ENTITY_COLUMN_VALUE =
    new StringJsonColumnValue("entityName", "com.poesys.db.col.StringColumnValue", ENTITY);
  private static final JsonColumnValue CHECKING_COLUMN_VALUE =
    new StringJsonColumnValue("accountName", "com.poesys.db.col.StringColumnValue", CHECKING);
  private static final JsonColumnValue FOOD_COLUMN_VALUE =
    new StringJsonColumnValue("accountName", "com.poesys.db.col.StringColumnValue", FOOD);

  private static final List<JsonColumnValue> CHECKING_ACCOUNT_KEY_NAME_LIST = new ArrayList<>();
  private static final List<JsonColumnValue> FOOD_ACCOUNT_KEY_NAME_LIST = new ArrayList<>();

  private static final JsonPrimaryKey ACCOUNT_PARENT_KEY =
    new NaturalJsonPrimaryKey("com.poesys.accounting.db.account.Entity", ENTITY_LIST);
  private static final JsonPrimaryKey CHECKING_ACCOUNT_CHILD_KEY =
    new NaturalJsonPrimaryKey("com.poesys.accounting.db.account.Account",
                              CHECKING_ACCOUNT_KEY_NAME_LIST);
  private static final JsonPrimaryKey FOOD_ACCOUNT_CHILD_KEY =
    new NaturalJsonPrimaryKey("com.poesys.accounting.db.account.Account",
                              FOOD_ACCOUNT_KEY_NAME_LIST);
  private static final JsonPrimaryKey CHECKING_ACCOUNT_KEY =
    new CompositeJsonPrimaryKey("com.poesys.accounting.db.account.Account", ACCOUNT_PARENT_KEY,
                                CHECKING_ACCOUNT_CHILD_KEY);
  private static final JsonPrimaryKey FOOD_ACCOUNT_KEY =
    new CompositeJsonPrimaryKey("com.poesys.accounting.db.account.Account", ACCOUNT_PARENT_KEY,
                                FOOD_ACCOUNT_CHILD_KEY);

  /**
   * Test instantiation of a JSON transaction with the default constructor.
   */
  @Test
  public void testDefaultConstructor() {
    JsonTransaction transaction = new JsonTransaction();
    assertTrue("default constructor status not EXISTING: " + transaction.getStatus(),
               transaction.getStatus().equals(AbstractJsonObject.EXISTING));
  }

  /**
   * Test instantiation of a JSON transaction with the field constructor.
   */
  @Test
  public void testFieldConstructor() {
    JsonTransaction transaction = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    assertTrue("default constructor status not EXISTING: " + transaction.getStatus(),
               transaction.getStatus().equals(AbstractJsonObject.EXISTING));
  }

  /**
   * Test the equals() method comparing a transaction to itself.
   */
  @Test
  public void testEqualsSameObject() {
    JsonTransaction transaction = new JsonTransaction();
    assertTrue("equals same object not equal", transaction.equals(transaction));
  }

  /**
   * Test the equals() method comparing a transaction to a different transaction with the same key.
   */
  @Test
  public void testEqualsSameKey() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    assertTrue("two transactions with same key not equal", transaction1.equals(transaction2));
  }

  /**
   * Test the equals() method comparing two transactions that have different keys.
   */
  @Test
  public void testEqualsDifferentKeys() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 = new JsonTransaction(ID2, DESCRIPTION, DATE, false, false, null);
    assertFalse("two transactions with different keys are equal",
                transaction1.equals(transaction2));
  }

  /**
   * Test the equals() method comparing a transaction to a null.
   */
  @Test
  public void testEqualsNull() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    assertFalse("transaction compared to null is equal", transaction1.equals(null));
  }

  /**
   * Test getting a hash code for a simple transaction.
   */
  @Test
  public void testHashCode() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    assertTrue("wrong hashcode: " + transaction1.hashCode(), transaction1.hashCode() == 496469344);
  }

  /**
   * Test hashcodes from the same key.
   */
  @Test
  public void testHashCodeSame() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    assertTrue("hashcodes for same transaction are not equal",
               transaction1.hashCode() == transaction2.hashCode());
  }

  /**
   * Test hashcodes from different keys.
   */
  @Test
  public void testHashCodeDifferent() {
    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 = new JsonTransaction(ID2, DESCRIPTION, DATE, false, false, null);
    assertFalse("hashcodes for different transactions are the same",
                transaction1.hashCode() == transaction2.hashCode());
  }

  /**
   * Test the getTransactionDate() method with a valid date.
   */
  @Test
  public void testGetTransactionDate() {
    Date date = null;
    try {
      date = AbstractJsonObject.FORMAT.parse(DATE);
    } catch (ParseException e) {
      throw new RuntimeException("Invalid date: " + DATE, e);
    }
    Timestamp originalTimestamp = new Timestamp(date.getTime());

    JsonTransaction transaction1 = new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, null);
    Timestamp timestamp = transaction1.getTransactionDate();

    assertTrue(timestamp.equals(originalTimestamp));
  }

  /**
   * Test the getTransactionDate() method with a valid date.
   */
  @Test
  public void testGetTransactionDateInvalid() {
    JsonTransaction transaction1 =
      new JsonTransaction(ID1, DESCRIPTION, INVALID_DATE, false, false, null);

    try {
      Timestamp timestamp = transaction1.getTransactionDate();
      fail("transaction with invalid date string did not throw exception");
    } catch (RuntimeException e) {
      assertTrue("wrong exception: " + e.getMessage(),
                 e.getMessage().contains("Invalid date in JSON data"));
    }
  }

  /**
   * Integration-test the GSON transformations for a realistic transaction with items.
   */
  @Test
  public void testGsonTransactionIntegration() {
    ENTITY_LIST.add(ENTITY_COLUMN_VALUE);
    CHECKING_ACCOUNT_KEY_NAME_LIST.add(CHECKING_COLUMN_VALUE);
    FOOD_ACCOUNT_KEY_NAME_LIST.add(FOOD_COLUMN_VALUE);
    JsonItem item1 =
      new JsonItem(ID1, ORDER1, AMOUNT, false, false, CHECKING_ACCOUNT_KEY, CHECKING, null, null,
                   null, null);
    JsonItem item2 =
      new JsonItem(ID2, ORDER2, AMOUNT, true, false, FOOD_ACCOUNT_KEY, FOOD, null, null, null,
                   null);

    List<JsonItem> itemList = new ArrayList<>();
    itemList.add(item1);
    itemList.add(item2);

    JsonTransaction transaction =
      new JsonTransaction(ID1, DESCRIPTION, DATE, false, false, itemList);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(transaction, JsonTransaction.class);
    assertTrue("wrong JSON: " + json, json.equals("{\"transactionId\":\"100\"," +
                                                  "\"description\":\"transaction description\"," +
                                                  "\"transactionDate\":\"2018-03-11 " +
                                                  "12:11:23.005\",\"balance\":false," +
                                                  "\"checked\":false," +
                                                  "\"items\":[{\"transactionId\":\"100\"," +
                                                  "\"orderNumber\":1,\"amount\":10.0," +
                                                  "\"debit\":false,\"checked\":false," +
                                                  "\"accountKey\":{\"keyType\":\"com.poesys.db.pk" +
                                                  ".CompositePrimaryKey\",\"className\":\"com" +
                                                  ".poesys.accounting.db.account.Account\"," +
                                                  "\"columnValueList\":null,\"value\":null," +
                                                  "\"keyList\":null," +
                                                  "\"parentKey\":{\"keyType\":\"com.poesys.db.pk" +
                                                  ".NaturalPrimaryKey\",\"className\":\"com" +
                                                  ".poesys.accounting.db.account.Entity\"," +
                                                  "\"columnValueList\":[{\"name\":\"entityName\"," +
                                                  "\"type\":\"com.poesys.db.col" +
                                                  ".StringColumnValue\",\"value\":\"Poesys " +
                                                  "Associates\"}],\"value\":null," +
                                                  "\"keyList\":null,\"parentKey\":null," +
                                                  "\"childKey\":null}," +
                                                  "\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
                                                  ".NaturalPrimaryKey\",\"className\":\"com" +
                                                  ".poesys.accounting.db.account.Account\"," +
                                                  "\"columnValueList\":[{\"name\":\"accountName\",\"type\":\"com.poesys.db.col.StringColumnValue\",\"value\":\"Checking\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null}},\"accountName\":\"Checking\",\"receivableKeys\":null,\"reimbursingItemsReimbursements\":null,\"receivableReimbursements\":null,\"reimbursingItemKeys\":null,\"status\":\"EXISTING\"},{\"transactionId\":\"200\",\"orderNumber\":2,\"amount\":10.0,\"debit\":true,\"checked\":false,\"accountKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com.poesys.accounting.db.account.Account\",\"columnValueList\":null,\"value\":null,\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.account.Entity\",\"columnValueList\":[{\"name\":\"entityName\",\"type\":\"com.poesys.db.col.StringColumnValue\",\"value\":\"Poesys Associates\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.account.Account\",\"columnValueList\":[{\"name\":\"accountName\",\"type\":\"com.poesys.db.col.StringColumnValue\",\"value\":\"Food\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null}},\"accountName\":\"Food\",\"receivableKeys\":null,\"reimbursingItemsReimbursements\":null,\"receivableReimbursements\":null,\"reimbursingItemKeys\":null,\"status\":\"EXISTING\"}],\"status\":\"EXISTING\"}"));
    JsonTransaction newTransaction = gson.fromJson(json, JsonTransaction.class);
    assertTrue("invalid JSON item from string", transaction.equals(newTransaction));
    assertTrue("status not EXISTING: " + transaction.getStatus(), "EXISTING".equals(transaction.getStatus()));
  }
}