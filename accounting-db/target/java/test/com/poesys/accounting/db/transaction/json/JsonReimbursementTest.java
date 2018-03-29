
/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.accounting.db.transaction.TransactionFactory;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.pk.IPrimaryKey;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.*;

/**
 * CUT: JsonReimbursement
 */
public class JsonReimbursementTest {

  private static final Double RECEIVABLE_AMOUNT = 100.00D;
  private static final Double ALLOCATED_AMOUNT = 0.00D;
  private static final BigInteger ID1 = new BigInteger("100");
  private static final BigInteger ID2 = new BigInteger("200");
  private static final BigInteger ID3 = new BigInteger("300");
  private static final Integer ORDER_NUMBER = 1;

  /**
   * Test the GSON integration for the JSON Reimbursement DTO.
   */
  @Test
  public void testJsonReimbursementIntegration() {
    IPrimaryKey key =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    JsonReimbursement reimbursement =
      new JsonReimbursement(key.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Produce the JSON string.
    String json = gson.toJson(reimbursement, JsonReimbursement.class);
    assertTrue("invalid JSON string from item: " + json, json.equals(
      "{\"primaryKey\":{\"keyType\":\"com.poesys.db.pk.AssociationPrimaryKey\"," +
      "\"className\":\"com.poesys.accounting.db.transaction.Reimbursement\"," +
      "\"columnValueList\":null,\"value\":null,\"keyList\":[{\"keyType\":\"com.poesys.db.pk" +
      ".CompositePrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Item\"," +
      "\"columnValueList\":null,\"value\":null,\"keyList\":null,\"parentKey\":{\"keyType\":\"com" +
      ".poesys.db.pk.SequencePrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction" +
      ".Transaction\",\"columnValueList\":[{\"name\":\"receivablesTransactionId\",\"type\":\"com" +
      ".poesys.db.col.BigIntegerColumnValue\",\"value\":\"100\"}],\"value\":null," +
      "\"keyList\":null,\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com" +
      ".poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction" +
      ".Item\",\"columnValueList\":[{\"name\":\"receivablesOrderNumber\",\"type\":\"com.poesys.db" +
      ".col.IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}},{\"keyType\":\"com.poesys.db.pk" +
      ".CompositePrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction.Item\"," +
      "\"columnValueList\":null,\"value\":null,\"keyList\":null,\"parentKey\":{\"keyType\":\"com" +
      ".poesys.db.pk.SequencePrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction" +
      ".Transaction\",\"columnValueList\":[{\"name\":\"reimbursingItemsTransactionId\"," +
      "\"type\":\"com.poesys.db.col.BigIntegerColumnValue\",\"value\":\"200\"}],\"value\":null," +
      "\"keyList\":null,\"parentKey\":null,\"childKey\":null},\"childKey\":{\"keyType\":\"com" +
      ".poesys.db.pk.NaturalPrimaryKey\",\"className\":\"com.poesys.accounting.db.transaction" +
      ".Item\",\"columnValueList\":[{\"name\":\"reimbursingItemsOrderNumber\",\"type\":\"com" +
      ".poesys.db.col.IntegerColumnValue\",\"value\":\"1\"}],\"value\":null,\"keyList\":null," +
      "\"parentKey\":null,\"childKey\":null}}],\"parentKey\":null,\"childKey\":null}," +
      "\"reimbursedAmount\":100.0,\"allocatedAmount\":0.0,\"status\":\"EXISTING\"}"));
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
    IPrimaryKey key =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    JsonReimbursement reimbursement =
      new JsonReimbursement(key.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    assertTrue("wrong reimbursed amount",
               RECEIVABLE_AMOUNT.equals(reimbursement.getReimbursedAmount()));
    assertTrue("wrong allocation amount",
               ALLOCATED_AMOUNT.equals(reimbursement.getAllocatedAmount()));
    assertTrue("wrong status", "EXISTING".equals(reimbursement.getStatus()));
  }

  /**
   * Test the status setter with a valid status.
   */
  @Test
  public void testSetStatusValid() {
    IPrimaryKey key =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    JsonReimbursement reimbursement =
      new JsonReimbursement(key.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
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
    IPrimaryKey key =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    JsonReimbursement reimbursement =
      new JsonReimbursement(key.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    try {
      reimbursement.setStatus("INVALID");
      fail("invalid status setting did not throw exception");
    } catch (InvalidParametersException e) {
      assertTrue("wrong exception: " + e.getMessage(),
                 e.getMessage().contains("invalid JSON object status "));
    }
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
    IPrimaryKey key1 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    IPrimaryKey key2 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID3, ID2);
    assertFalse("different objects (on receivable item) are equal",
                new JsonReimbursement(key1.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                      ALLOCATED_AMOUNT).equals(
                  new JsonReimbursement(key2.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                        ALLOCATED_AMOUNT)));
  }

  /**
   * Test equals with keys having different reimbursing item keys.
   */
  @Test
  public void testEqualsDifferentReimbursingItem() {
    IPrimaryKey key1 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    IPrimaryKey key2 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID3);
    assertFalse("different objects (on reimbursing item) are equal",
                new JsonReimbursement(key1.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                      ALLOCATED_AMOUNT).equals(
                  new JsonReimbursement(key2.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                        ALLOCATED_AMOUNT)));
  }

  /**
   * Test equals with two equivalent objects.
   */
  @Test
  public void testEquals() {
    IPrimaryKey key1 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    IPrimaryKey key2 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    assertTrue("equivalent objects not equal",
               new JsonReimbursement(key1.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                     ALLOCATED_AMOUNT).equals(
                 new JsonReimbursement(key2.getJsonPrimaryKey(), RECEIVABLE_AMOUNT,
                                       ALLOCATED_AMOUNT)));
  }

  @Test
  public void testHashCode() {
    IPrimaryKey key1 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID2);
    IPrimaryKey key2 =
      TransactionFactory.getReimbursementPrimaryKey(ORDER_NUMBER, ORDER_NUMBER, ID1, ID3);
    JsonReimbursement object1 =
      new JsonReimbursement(key1.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    JsonReimbursement object2 =
      new JsonReimbursement(key2.getJsonPrimaryKey(), RECEIVABLE_AMOUNT, ALLOCATED_AMOUNT);
    assertTrue("wrong hash code: " + object1.hashCode(), object1.hashCode() == -1397744865);
    assertTrue("different objects have same hash code", object1.hashCode() != object2.hashCode());
  }
}