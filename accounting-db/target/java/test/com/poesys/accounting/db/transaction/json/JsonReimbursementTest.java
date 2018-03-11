
/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.col.json.BigIntegerJsonColumnValue;
import com.poesys.db.col.json.IntegerJsonColumnValue;
import com.poesys.db.col.json.JsonColumnValue;
import com.poesys.db.pk.json.CompositeJsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKey;
import com.poesys.db.pk.json.NaturalJsonPrimaryKey;
import com.poesys.db.pk.json.SequenceJsonPrimaryKey;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * CUT: JsonReimbursement
 */
public class JsonReimbursementTest {

  private static final String TRANSACTION_COLUMN = "transactionId";
  private static final String ITEM_COLUMN = "orderNumber";
  private static final String BIG_INTEGER_COLUMN_VALUE_CLASS =
    "com.poesys.db.col.BigIntegerColumnValue";
  private static final String INTEGER_COLUMN_VALUE_CLASS = "com.poesys.db.col.IntegerColumnValue";
  private static final String TRANSACTION_CLASS =
    "com.poesys.db.accounting.transaction.Transaction";
  private static final String ITEM_CLASS = "com.poesys.db.accounting.transaction.Item";
  private static final Double RECEIVABLE_AMOUNT = 100.00D;
  private static final Double ALLOCATED_AMOUNT = 0.00D;
  private static final String ID1 = "100";
  private static final String ID2 = "200";
  private static final String ID3 = "300";
  private static final String ORDER_NUMBER = "1";

  /**
   * Test the GSON integration for the JSON Reimbursement DTO.
   */
  @Test
  public void testJsonReimbursementIntegration() {
    JsonReimbursement reimbursement =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(reimbursement, JsonReimbursement.class);
    assertTrue("invalid JSON string from item: " + json, json.equals("{\"receivableItemKey" +
                                                                     "\":{\"keyType\":\"com" +
                                                                     ".poesys.db.pk" +
                                                                     ".CompositePrimaryKey\"," +
                                                                     "\"className\":\"com.poesys" +
                                                                     ".db.accounting.transaction" +
                                                                     ".Item\"," +
                                                                     "\"columnValueList\":null," +
                                                                     "\"value\":null," +
                                                                     "\"keyList\":null," +
                                                                     "\"parentKey\":{\"keyType\":\"com.poesys.db.pk.SequencePrimaryKey\",\"className\":\"com.poesys.db.accounting.transaction.Transaction\",\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col.BigIntegerColumnValue\",\"value\":\"100\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.db.accounting.transaction.Item\",\"columnValueList\":[{\"name\":\"orderNumber\",\"type\":\"com.poesys.db.col.IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null}},\"reimbursingItemKey\":{\"keyType\":\"com.poesys.db.pk.CompositePrimaryKey\",\"className\":\"com.poesys.db.accounting.transaction.Item\",\"columnValueList\":null,\"value\":null,\"keyList\":null,\"parentKey\":{\"keyType\":\"com.poesys.db.pk.SequencePrimaryKey\",\"className\":\"com.poesys.db.accounting.transaction.Transaction\",\"columnValueList\":[{\"name\":\"transactionId\",\"type\":\"com.poesys.db.col.BigIntegerColumnValue\",\"value\":\"200\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com.poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.db.accounting.transaction.Item\",\"columnValueList\":[{\"name\":\"orderNumber\",\"type\":\"com.poesys.db.col.IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null,\"parentKey\":null,\"childKey\":null}},\"reimbursedAmount\":100.0,\"allocatedAmount\":0.0,\"status\":\"EXISTING\"}"));
    // Create a new version of the Json reimbursement from the JSON string.
    JsonReimbursement newReimbursement = gson.fromJson(json, JsonReimbursement.class);
    assertTrue("invalid JSON item from string", reimbursement.equals(newReimbursement));
  }

  /**
   * Test the default constructor.
   */
  @Test
  public void testJsonReimbursementDefault() {
    new JsonReimbursement();
  }

  /**
   * Test the data-field constructor and the getters.
   */
  @Test
  public void testJsonReimbursementData() {
    JsonReimbursement reimbursement =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    assertTrue("wrong reimbursed amount", RECEIVABLE_AMOUNT == reimbursement.getReimbursedAmount());
    assertTrue("wrong allocatiion amount", ALLOCATED_AMOUNT == reimbursement.getAllocatedAmount());
    assertTrue("wrong status", "EXISTING".equals(reimbursement.getStatus()));
  }

  /**
   * Test the status setter with a valid status.
   */
  @Test
  public void testSetStatusValid() {
    JsonReimbursement reimbursement =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    reimbursement.setStatus("EXISTING");
    assertTrue("wrong status: " + reimbursement.getStatus(),
               "EXISTING".equals(reimbursement.getStatus()));
    reimbursement.setStatus("NEW");
    assertTrue("wrong status" + reimbursement.getStatus(), "NEW".equals(reimbursement.getStatus()));
    reimbursement.setStatus("CHANGED");
    assertTrue("wrong status" + reimbursement.getStatus(),
               "CHANGED".equals(reimbursement.getStatus()));
    reimbursement.setStatus("DELETED");
    assertTrue("wrong status" + reimbursement.getStatus(),
               "DELETED".equals(reimbursement.getStatus()));
  }

  /**
   * Test the status setter with a valid status.
   */
  @Test
  public void testSetStatusInvalid() {
    JsonReimbursement reimbursement =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    try {
      reimbursement.setStatus("INVALID");
      fail("invalid status setting did not throw exception");
    } catch (InvalidParametersException e) {
      assertTrue("wrong exception: " + e.getMessage(),
                 e.getMessage().contains("invalid JSON object status "));
    }
  }

  /**
   * Get an Item JSON primary key, specifying the transaction id and order number.
   *
   * @param id          the transaction identifier
   * @param orderNumber the order of the item within the transaction
   * @return the JSON primary key for the item
   */
  private JsonPrimaryKey getItemKey(String id, String orderNumber) {
    return new CompositeJsonPrimaryKey(ITEM_CLASS, getTransactionKey(id),
                                       getItemChildKey(orderNumber));
  }

  /**
   * Get a Transaction JSON primary key, specifying the transaction id.
   *
   * @param id the transaction identifier
   * @return the Transaction JSON primary key
   */
  private JsonPrimaryKey getTransactionKey(String id) {
    List<JsonColumnValue> transactionColumnValueList1 = new ArrayList<>(1);

    JsonColumnValue transactionColumnValue1 =
      new BigIntegerJsonColumnValue(TRANSACTION_COLUMN, BIG_INTEGER_COLUMN_VALUE_CLASS, id);
    transactionColumnValueList1.add(transactionColumnValue1);

    return new SequenceJsonPrimaryKey(TRANSACTION_CLASS, transactionColumnValueList1);
  }

  /**
   * Get an Item child key JSON primary key, part of a Composite JSON primary key.
   *
   * @param orderNumber the order number to use for the key
   * @return the JSON primary key for the child key
   */
  private JsonPrimaryKey getItemChildKey(String orderNumber) {
    List<JsonColumnValue> itemColumnValueList = new ArrayList<>(1);
    JsonColumnValue itemColumnValue =
      new IntegerJsonColumnValue(ITEM_COLUMN, INTEGER_COLUMN_VALUE_CLASS, orderNumber);
    itemColumnValueList.add(itemColumnValue);

    return new NaturalJsonPrimaryKey(ITEM_CLASS, itemColumnValueList);
  }

  /**
   * Test equals() with the same object.
   */
  @Test
  public void testEqualsSameObject() {
    JsonReimbursement object = new JsonReimbursement();
    assertTrue("same object not equal", object.equals(object));
  }

  /**
   * Test equals() with a null comparison object.
   */
  @Test
  public void testEqualsNull() {
    JsonReimbursement object = new JsonReimbursement();
    assertFalse("null object equal", object.equals(null));
  }

  /**
   * Test equals with an object of a different class as comparison object.
   */
  @Test
  public void testEqualsDifferentClass() {
    JsonReimbursement object = new JsonReimbursement();
    Object other = new Object();
    assertFalse("different class of object equal", object.equals(other));
  }

  /**
   * Test equals with keys having different receivable item keys.
   */
  @Test
  public void testEqualsDifferentReceivableItem() {
    assertFalse("different objects (on receivable item) are equal",
                new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                                      RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT).equals(
                  new JsonReimbursement(getItemKey(ID3, ORDER_NUMBER),
                                        getItemKey(ID2, ORDER_NUMBER), RECEIVABLE_AMOUNT,
                                        ALLOCATED_AMOUNT)));
  }

  /**
   * Test equals with keys having different reimbursing item keys.
   */
  @Test
  public void testEqualsDifferentReimbursingItem() {
    assertFalse("different objects (on reimbursing item) are equal",
                new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                                      RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT).equals(
                  new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER),
                                        getItemKey(ID3, ORDER_NUMBER), RECEIVABLE_AMOUNT,
                                        ALLOCATED_AMOUNT)));
  }

  /**
   * Test equals with two equivalent objects.
   */
  @Test
  public void testEquals() {
    assertTrue("equivalent objects not equal",
               new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                                     RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT).equals(
                 new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                                       RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT)));
  }

  @Test
  public void testHashCode() {
    JsonReimbursement object1 =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID2, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    JsonReimbursement object2 =
      new JsonReimbursement(getItemKey(ID1, ORDER_NUMBER), getItemKey(ID3, ORDER_NUMBER),
                            RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    assertTrue("wrong hash code: " + object1.hashCode(), object1.hashCode() == -1384342401);
    assertTrue("different objects have same hash code", object1.hashCode() != object2.hashCode());
  }
}