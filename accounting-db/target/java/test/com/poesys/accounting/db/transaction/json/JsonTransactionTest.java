package com.poesys.accounting.db.transaction.json;/* Copyright (c) 2018 Poesys Associates. All
rights reserved. */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.accounting.db.account.SimpleAccount;
import com.poesys.accounting.db.transaction.TransactionFactory;
import com.poesys.db.AbstractJsonObject;
import com.poesys.db.col.json.JsonColumnValue;
import com.poesys.db.col.json.StringJsonColumnValue;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.json.CompositeJsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKey;
import com.poesys.db.pk.json.NaturalJsonPrimaryKey;
import org.junit.Test;

import java.math.BigInteger;
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

  private static final BigInteger ID1 = new BigInteger("100");
  private static final BigInteger ID2 = new BigInteger("200");
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
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
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
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertTrue("two transactions with same key not equal", transaction1.equals(transaction2));
  }

  /**
   * Test the equals() method comparing two transactions that have different keys.
   */
  @Test
  public void testEqualsDifferentKeys() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    IPrimaryKey key2 = TransactionFactory.getTransactionPrimaryKey(ID2);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 =
      new JsonTransaction(key2.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertFalse("two transactions with different keys are equal",
                transaction1.equals(transaction2));
  }

  /**
   * Test the equals() method comparing a transaction to a null.
   */
  @Test
  public void testEqualsNull() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertFalse("transaction compared to null is equal", transaction1.equals(null));
  }

  /**
   * Test getting a hash code for a simple transaction.
   */
  @Test
  public void testHashCode() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertTrue("wrong hashcode: " + transaction1.hashCode(), transaction1.hashCode() == 503386044);
  }

  /**
   * Test hashcodes from the same key.
   */
  @Test
  public void testHashCodeSame() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertTrue("hashcodes for same transaction are not equal",
               transaction1.hashCode() == transaction2.hashCode());
  }

  /**
   * Test hashcodes from different keys.
   */
  @Test
  public void testHashCodeDifferent() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    IPrimaryKey key2 = TransactionFactory.getTransactionPrimaryKey(ID2);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    JsonTransaction transaction2 =
      new JsonTransaction(key2.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    assertFalse("hashcodes for different transactions are the same",
                transaction1.hashCode() == transaction2.hashCode());
  }

  /**
   * Test the getTransactionId() method.
   */
  @Test
  public void testGetTransactionId() {
    IPrimaryKey key = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction =
      new JsonTransaction(key.getJsonPrimaryKey(), DESCRIPTION, INVALID_DATE, false, false, null);
    BigInteger transactionId = transaction.getTransactionId();
    assertTrue("wrong transaction id: " + transactionId, transactionId.equals(ID1));
  }

  /**
   * Test the getTransactionDate() method with a valid date.
   */
  @Test
  public void testGetTransactionDate() {
    Date date;
    try {
      date = AbstractJsonObject.FORMAT.parse(DATE);
    } catch (ParseException e) {
      throw new RuntimeException("Invalid date: " + DATE, e);
    }
    Timestamp originalTimestamp = new Timestamp(date.getTime());

    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false, null);
    Timestamp timestamp = transaction1.getTransactionDate();

    assertTrue(timestamp.equals(originalTimestamp));
  }

  /**
   * Test the getTransactionDate() method with a valid date.
   */
  @Test
  public void testGetTransactionDateInvalid() {
    IPrimaryKey key1 = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction1 =
      new JsonTransaction(key1.getJsonPrimaryKey(), DESCRIPTION, INVALID_DATE, false, false, null);

    try {
      transaction1.getTransactionDate();
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
    IPrimaryKey key1 = TransactionFactory.getItemPrimaryKey(ORDER1, ID1);
    IPrimaryKey key2 = TransactionFactory.getItemPrimaryKey(ORDER2, ID2);
    JsonItem item1 =
      new JsonItem(key1.getJsonPrimaryKey(), AMOUNT, false, false, CHECKING_ACCOUNT_KEY, CHECKING,
                   null, null, null, null, SimpleAccount.class.getName());
    JsonItem item2 =
      new JsonItem(key2.getJsonPrimaryKey(), AMOUNT, true, false, FOOD_ACCOUNT_KEY, FOOD, null,
                   null, null, null, SimpleAccount.class.getName());

    List<JsonItem> itemList = new ArrayList<>();
    itemList.add(item1);
    itemList.add(item2);

    IPrimaryKey transactionKey = TransactionFactory.getTransactionPrimaryKey(ID1);
    JsonTransaction transaction =
      new JsonTransaction(transactionKey.getJsonPrimaryKey(), DESCRIPTION, DATE, false, false,
                          itemList);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(transaction, JsonTransaction.class);
    assertTrue("wrong JSON: " + json, json.equals(
      "{\"primaryKey\":{\"keyType\":\"com.poesys.db.pk.SequencePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.transaction.Transaction\"," +
      "\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col" +
      ".BigIntegerColumnValue\",\"value\":\"100\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"description\":\"transaction description\"," +
      "\"transactionDate\":\"2018-03-11 12:11:23.005\",\"balance\":false,\"checked\":false," +
      "\"items\":[{\"primaryKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.transaction.Item\",\"columnValueList\":null," +
      "\"value\":null,\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".SequencePrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Transaction\"," +
      "\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col" +
      ".BigIntegerColumnValue\",\"value\":\"100\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Item\"," +
      "\"columnValueList\":[{\"name\":\"orderNumber\",\"type\":\"com.poesys.db.col" +
      ".IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},\"amount\":10.0,\"debit\":false,\"checked\":false," +
      "\"accountKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.account.Account\",\"columnValueList\":null,\"value\":null," +
      "\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.account.Entity\"," +
      "\"columnValueList\":[{\"name\":\"entityName\",\"type\":\"com.poesys.db.col" +
      ".StringColumnValue\",\"value\":\"Poesys Associates\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.account.Account\"," +
      "\"columnValueList\":[{\"name\":\"accountName\",\"type\":\"com.poesys.db.col" +
      ".StringColumnValue\",\"value\":\"Checking\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},\"accountName\":\"Checking\"," +
      "\"receivableKeys\":null,\"reimbursingItemsReimbursements\":null," +
      "\"receivableReimbursements\":null,\"reimbursingItemKeys\":null,\"status\":\"EXISTING\"}," +
      "{\"primaryKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.transaction.Item\",\"columnValueList\":null,\"value\":null," +
      "\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.SequencePrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.transaction.Transaction\"," +
      "\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col" +
      ".BigIntegerColumnValue\",\"value\":\"200\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Item\"," +
      "\"columnValueList\":[{\"name\":\"orderNumber\",\"type\":\"com.poesys.db.col" +
      ".IntegerColumnValue\",\"value\":\"2\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},\"amount\":10.0,\"debit\":true,\"checked\":false," +
      "\"accountKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com" +
      ".poesys.accounting.db.account.Account\",\"columnValueList\":null,\"value\":null," +
      "\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.account.Entity\"," +
      "\"columnValueList\":[{\"name\":\"entityName\",\"type\":\"com.poesys.db.col" +
      ".StringColumnValue\",\"value\":\"Poesys Associates\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk" +
      ".NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.account.Account\"," +
      "\"columnValueList\":[{\"name\":\"accountName\",\"type\":\"com.poesys.db.col" +
      ".StringColumnValue\",\"value\":\"Food\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},\"accountName\":\"Food\",\"receivableKeys\":null," +
      "\"reimbursingItemsReimbursements\":null,\"receivableReimbursements\":null," +
      "\"reimbursingItemKeys\":null,\"status\":\"EXISTING\"}],\"status\":\"EXISTING\"}"));
    JsonTransaction newTransaction = gson.fromJson(json, JsonTransaction.class);
    assertTrue("invalid JSON item from string", transaction.equals(newTransaction));
    assertTrue("status not EXISTING: " + transaction.getStatus(),
               "EXISTING".equals(transaction.getStatus()));
  }
}