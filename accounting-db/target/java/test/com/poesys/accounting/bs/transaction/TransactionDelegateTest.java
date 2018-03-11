/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.transaction;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.CopyOnWriteArrayList;

import org.junit.Test;

import com.poesys.accounting.bs.account.AccountDelegateFactory;
import com.poesys.accounting.bs.account.BsAccount;
import com.poesys.accounting.bs.account.BsEntity;
import com.poesys.accounting.bs.account.BsSimpleAccount;
import com.poesys.accounting.bs.account.EntityDelegate;
import com.poesys.accounting.bs.account.SimpleAccountDelegate;
import com.poesys.accounting.db.account.ISimpleAccount;
import com.poesys.accounting.db.transaction.IItem;
import com.poesys.accounting.db.transaction.ITransaction;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.Message;
import com.poesys.db.StringUtilities;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.connection.JdbcConnectionManager;
import com.poesys.db.dao.DaoManagerFactory;
import com.poesys.db.dao.IDaoManager;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.db.pk.SequencePrimaryKey;


/**
 * <p>
 * A test class for the TransactionDelegate class. This class is the concrete
 * subclass of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 * <p>
 * The transaction object is quite involved structurally, owning the "items"
 * constructed from the various concrete classes in the Item class hierarchy.
 * This test class contains tests that exercise all the classes in this complex,
 * even though the Item classes have separate business delegates. The test
 * methods for the item classes contain tests of all operations (query, insert,
 * update, delete) to minimize the amount of code and complexity in the test
 * case by operating on a single object sequentially with multiple operations
 * rather than by separating them out into separate test methods, as for
 * Transaction. This class overrides all of the superclass methods, completely
 * replacing the Abstract superclass, to handle the structural requirement that
 * a transaction needs at least two items that balance in credit/debit amount.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class TransactionDelegateTest extends AbstractTransactionDelegateTest {

  private static final Boolean CHECKED = Boolean.TRUE;
  private static final Boolean BALANCE = Boolean.TRUE;
  private static final String REC_ACCOUNT_NAME = "AR";
  private static final String INC_ACCOUNT_NAME = "Revenue";
  private static final String CHK_ACCOUNT_NAME = "Checking";
  private static final String REC_DESC = "Receivables account";
  private static final String INC_ACCOUNT_DESC = "Revenue from customers";
  private static final String CHK_ACCOUNT_DESC = "Bank checking account";
  private static final Boolean DEBIT = Boolean.TRUE;
  private static final Boolean RECEIVABLE = Boolean.TRUE;
  private static final Boolean ACTIVE = Boolean.TRUE;
  private static final Double AMOUNT = 100.00D;
  private static final Double ALLOCATED_AMOUNT = 0.00D;

  private BsEntity entity = null;

  // Accounts as superclass Account
  private BsAccount receivableAccount = null;
  private BsAccount incomeAccount = null;
  private BsAccount checkingAccount = null;

  /**
   * Set up the Transaction parent Entity and three accounts to use for
   * transaction items of various kinds. These objects get created and inserted
   * at the beginning of the test run and deleted after the last test method
   * runs.
   * 
   * @throws java.lang.Exception when there is a problem creating or inserting
   *           objects
   */
  private void createAccounts() {
    // Create entity and store it.
    EntityDelegate entDel = AccountDelegateFactory.getEntityDelegate();
    entity = entDel.createEntity(StringUtilities.generateString(100));
    entDel.insert(entity);

    // Create simple accounts.
    SimpleAccountDelegate accDel =
      AccountDelegateFactory.getSimpleAccountDelegate();

    receivableAccount =
      new BsAccount(accDel.createSimpleAccount(REC_ACCOUNT_NAME,
                                               entity.getEntityName(),
                                               REC_DESC,
                                               DEBIT,
                                               ACTIVE,
                                               RECEIVABLE).toDto());
    receivableAccount.setEntity(entity);
    incomeAccount =
      new BsAccount(accDel.createSimpleAccount(INC_ACCOUNT_NAME,
                                               entity.getEntityName(),
                                               INC_ACCOUNT_DESC,
                                               !DEBIT,
                                               ACTIVE,
                                               !RECEIVABLE).toDto());
    incomeAccount.setEntity(entity);
    checkingAccount =
      new BsAccount(accDel.createSimpleAccount(CHK_ACCOUNT_NAME,
                                               entity.getEntityName(),
                                               CHK_ACCOUNT_DESC,
                                               DEBIT,
                                               ACTIVE,
                                               !RECEIVABLE).toDto());
    checkingAccount.setEntity(entity);

    // Add the accounts to the entity's list of accounts, then insert all.
    entity.addAccountsAccount(incomeAccount);
    entity.addAccountsAccount(receivableAccount);
    entity.addAccountsAccount(checkingAccount);

    entDel.process(entity);
  }

  /**
   * Delete the Entity object and its dependents. Free the static business
   * delegates.
   * 
   * @throws java.lang.Exception
   */
  private void deleteAccounts() {
    EntityDelegate entDel = AccountDelegateFactory.getEntityDelegate();

    // Mark the entity for deletion and delete it.

    entity.delete();
    entDel.process(entity);
    entDel = null;
  }

  // Create a specified number of Transaction objects with Item children, all
  // RegularItem items (revenue credit, checking debit).

  @Override
  protected List<com.poesys.accounting.bs.transaction.BsTransaction> createTransactionTransaction(int count)
      throws DelegateException, InvalidParametersException {
    List<BsTransaction> objects = new ArrayList<BsTransaction>();

    for (int i = 0; i < count; i++) {
      String description = StringUtilities.generateString(4000);
      // Generate different dates relative to current date based on current
      // count.
      Timestamp transactionDate =
        new Timestamp(System.currentTimeMillis() - (10000 * i));

      // Create the object.
      BsTransaction transaction = null;
      try {
        // tests createTransaction and BsTransaction constructor
        transaction =
          delegate.createTransaction(null,
                                     description,
                                     transactionDate,
                                     !CHECKED,
                                     !BALANCE);
        // Verify object creation
        assertTrue("no transaction object created", transaction != null);
        // Validate object with description comparison
        assertTrue("wrong description",
                   description.equals(transaction.getDescription()));
        // Create the income credit item.
        BsItem incomeItem =
          delegate.createItem(transaction,
                              transaction.getTransactionId(),
                              1,
                              AMOUNT,
                              !DEBIT,
                              !CHECKED,
                              INC_ACCOUNT_NAME,
                              entity.getEntityName());
        BsAccount account = new BsAccount(incomeAccount.toDto());
        incomeItem.setAccount(account);
        account.addItemsItem(incomeItem);
        // tests addItemsItem()
        int itemCount = transaction.getItems().size();
        transaction.addItemsItem(incomeItem);
        // verify add by item count
        assertTrue("wrong number of items: " + transaction.getItems().size()
                       + ", expected " + (itemCount + 1),
                   transaction.getItems().size() == (itemCount + 1));
        // Create the asset debit item.
        BsItem assetItem =
          delegate.createItem(transaction,
                              transaction.getTransactionId(),
                              2,
                              AMOUNT,
                              DEBIT,
                              !CHECKED,
                              CHK_ACCOUNT_NAME,
                              entity.getEntityName());
        incomeItem.setAccount(checkingAccount);
        checkingAccount.addItemsItem(incomeItem);
        // tests addItemsItem()
        itemCount = transaction.getItems().size();
        transaction.addItemsItem(assetItem);
        // verify add by item count
        assertTrue("wrong number of items: " + transaction.getItems().size()
                       + ", expected " + (itemCount + 1),
                   transaction.getItems().size() == (itemCount + 1));
        // Validate the transaction, should be complete now
        // tests base isValid() function branch on BsTransaction
        assertTrue("transaction not valid, see log", transaction.isValid());
      } catch (InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = Message.getMessage(e.getMessage(), args);
        throw new RuntimeException(message, e);
      } catch (DelegateException e) {
        throw new RuntimeException(e.getMessage(), e);
      }

      objects.add(transaction);
    }

    return objects;
  }

  /**
   * Create a Receivable transaction with a debit Receivable item against a
   * receivable account and a credit RegularItem against an income account. This
   * test also tests the createItem() method on the ItemDelegate.
   * 
   * @return a receivable transaction
   */
  protected BsTransaction createReceivableTransaction() {
    String description = StringUtilities.generateString(4000);
    Timestamp transactionDate = new Timestamp(System.currentTimeMillis());

    // Create the object.
    BsTransaction transaction = null;
    try {
      // tests createTransaction and BsTransaction constructor
      transaction =
        delegate.createTransaction(null,
                                   description,
                                   transactionDate,
                                   !CHECKED,
                                   !BALANCE);
      // Verify object creation
      assertTrue("no transaction object created", transaction != null);
      // Validate object with description comparison
      assertTrue("wrong description",
                 description.equals(transaction.getDescription()));
      // Create the income credit item.
      BsItem incomeItem =
        delegate.createItem(transaction,
                            transaction.getTransactionId(),
                            1,
                            AMOUNT,
                            !DEBIT,
                            !CHECKED,
                            INC_ACCOUNT_NAME,
                            entity.getEntityName());
      incomeItem.setAccount(incomeAccount);
      // tests addItemsItem()
      int itemCount = transaction.getItems().size();
      transaction.addItemsItem(incomeItem);
      // verify add by item count
      assertTrue("wrong number of items: " + transaction.getItems().size()
                     + ", expected " + (itemCount + 1),
                 transaction.getItems().size() == (itemCount + 1));
      // Create the receivable debit item.
      BsItem receivableItem =
        delegate.createItem(transaction,
                            transaction.getTransactionId(),
                            2,
                            AMOUNT,
                            DEBIT,
                            !CHECKED,
                            REC_ACCOUNT_NAME,
                            entity.getEntityName());
      incomeItem.setAccount(receivableAccount);
      // tests addItemsItem()
      itemCount = transaction.getItems().size();
      transaction.addItemsItem(receivableItem);
      // verify add by item count
      assertTrue("wrong number of items: " + transaction.getItems().size()
                     + ", expected " + (itemCount + 1),
                 transaction.getItems().size() == (itemCount + 1));
      // Validate the transaction, should be complete now
      // tests base isValid() function branch on BsTransaction
      assertTrue("transaction not valid, see log", transaction.isValid());
      logger.debug("Processing valid receivable transaction with two items");
    } catch (InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = Message.getMessage(e.getMessage(), args);
      throw new RuntimeException(message, e);
    } catch (DelegateException e) {
      throw new RuntimeException(e.getMessage(), e);
    }

    return transaction;
  }

  /**
   * Create a Reimbursement transaction against the account of a receivable
   * item.
   * 
   * @return a reimbursement transaction
   */
  protected BsTransaction createReimbursementTransaction() {
    String description = StringUtilities.generateString(4000);
    Timestamp transactionDate = new Timestamp(System.currentTimeMillis());

    // Create the transaction.
    BsTransaction transaction = null;
    try {
      // tests createTransaction and BsTransaction constructor
      transaction =
        delegate.createTransaction(null,
                                   description,
                                   transactionDate,
                                   !CHECKED,
                                   !BALANCE);
      // Verify object creation
      assertTrue("no transaction object created", transaction != null);
      // Validate object with description comparison
      assertTrue("wrong description",
                 description.equals(transaction.getDescription()));
      // Create the receivable credit item.
      BsItem receivableReimbursement =
        delegate.createItem(transaction,
                            transaction.getTransactionId(),
                            1,
                            AMOUNT,
                            !DEBIT,
                            !CHECKED,
                            REC_ACCOUNT_NAME,
                            entity.getEntityName());
      receivableReimbursement.setAccount(receivableAccount);
      // tests addItemsItem()
      int itemCount = transaction.getItems().size();
      transaction.addItemsItem(receivableReimbursement);
      // verify add by item count
      assertTrue("wrong number of items: " + transaction.getItems().size()
                     + ", expected " + (itemCount + 1),
                 transaction.getItems().size() == (itemCount + 1));
      // Create the reimbursement debit item. No item list required.
      BsItem checkingReimbursement =
        delegate.createItem(transaction,
                            transaction.getTransactionId(),
                            2,
                            AMOUNT,
                            DEBIT,
                            !CHECKED,
                            CHK_ACCOUNT_NAME,
                            entity.getEntityName());
      checkingReimbursement.setAccount(checkingAccount);
      // tests addItemsItem()
      itemCount = transaction.getItems().size();
      transaction.addItemsItem(checkingReimbursement);
      // verify add by item count
      assertTrue("wrong number of items: " + transaction.getItems().size()
                     + ", expected " + (itemCount + 1),
                 transaction.getItems().size() == (itemCount + 1));
      // Validate the transaction, should be complete now
      // tests base isValid() function branch on BsTransaction
      assertTrue("transaction not valid, see log", transaction.isValid());
    } catch (InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = Message.getMessage(e.getMessage(), args);
      throw new RuntimeException(message, e);
    } catch (DelegateException e) {
      logger.error("Delegate exception from reimbursement transaction process()");
      throw new RuntimeException(e.getMessage(), e);
    }

    return transaction;
  }

  @Override
  protected List<BsReimbursement> createTransactionReimbursement(List<IItem> receivablesList,
                                                                 List<IItem> reimbursingItemsList,
                                                                 int count)
      throws DelegateException, InvalidParametersException {
    List<BsReimbursement> reimbursements =
      new ArrayList<BsReimbursement>(count);

    // Check the input array sizes, should be count.
    assertTrue("Receivables list has incorrect size " + receivablesList.size(),
               receivablesList.size() == count);
    assertTrue("Reimbursing items list has incorrect size "
                   + reimbursingItemsList.size(),
               reimbursingItemsList.size() == count);

    for (int i = 0; i < count; i++) {
      BsItem receivable = new BsItem(receivablesList.get(i));
      BsItem reimbursingItem = new BsItem(reimbursingItemsList.get(i));
      BsReimbursement reimbursement =
        delegate.createReimbursement(receivable,
                                     reimbursingItem,
                                     receivable.getOrderNumber(),
                                     reimbursingItem.getOrderNumber(),
                                     receivable.getTransactionId(),
                                     reimbursingItem.getTransactionId(),
                                     AMOUNT,
                                     ALLOCATED_AMOUNT);
      logger.debug("Created reimbursement object "
                   + reimbursement.getPrimaryKey().getStringKey());
      reimbursements.add(reimbursement);
      try {
        receivable.addReimbursingItemsItem(reimbursingItem);
        receivable.addReimbursingItemsReimbursementReimbursement(reimbursement);
      } catch (SQLException e) {
        fail("SQL exception adding reimbursement to receivable item");
      }
      try {
        reimbursingItem.addReceivablesItem(receivable);
        reimbursingItem.addReceivablesReimbursementReimbursement(reimbursement);
      } catch (SQLException e) {
        fail("SQL exception adding reimbursement to reimbursing item");
      }
    }
    return reimbursements;
  }

  @Override
  public void testInsert() {
    // Create a new Transaction object to perform the test.
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      objects = createTransactionTransaction(1);
      delegate.insert(objects);
      SequencePrimaryKey key =
        (SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key for inserted object", key != null);

      BsTransaction queriedObject = queryStoredObject(key);
      assertTrue("No queried inserted object", queriedObject != null);
      assertTrue("Wrong object", objects.get(0).equals(queriedObject));
    } catch (DelegateException e) {
      fail(e.getMessage());
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction object : objects) {
        object.delete();
      }
      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }

  @Override
  public void testGetObject() throws SQLException {
    // Create a new Transaction object to perform the test.
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      objects = createTransactionTransaction(1);
      delegate.insert(objects);
      SequencePrimaryKey key =
        (SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key generated from concrete implementation", key != null);
      BsTransaction insertedObject = objects.get(0);
      assertTrue("No comparison object for object query",
                 insertedObject != null);

      // Query the object from the database.
      IDaoManager manager = DaoManagerFactory.getManager(getSubsystem());
      if (manager != null) {
        manager.clearCache(com.poesys.accounting.db.transaction.Transaction.class.getName());
      }
      BsTransaction object = delegate.getObject(key);
      assertTrue("Couldn't get object", object != null);
      assertTrue("Wrong object", insertedObject.equals(object));
    } catch (DelegateException e) {
      fail(e.getMessage());
    } catch (Exception e) {
      logger.error("getObject failed", e);
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction o : objects) {
        o.delete();
      }
      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }

  @Override
  public void testGetAllObjects() {
    // Create 3 new Transaction objects to perform the test.
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      objects = createTransactionTransaction(3);
      delegate.insert(objects);
      SequencePrimaryKey key =
        (SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key generated from concrete implementation", key != null);
      BsTransaction insertedObject = objects.get(0);
      assertTrue("No comparison object for object query",
                 insertedObject != null);

      // Query the objects from the database.
      clearCaches();
      objects = delegate.getAllObjects(3);
      assertTrue("Couldn't get list of transactions", objects != null
                                                      && objects.size() > 0);
    } catch (DelegateException e) {
      fail(e.getMessage());
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction o : objects) {
        o.delete();
      }
      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }

  /**
   * Test the updating of the inserted object.
   */
  @Override
  public void testUpdate() {
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      // Create a new Transaction object to perform the test.
      objects = createTransactionTransaction(1);
      assertTrue("No object created", objects.get(0) != null);
      delegate.insert(objects);
      com.poesys.db.pk.SequencePrimaryKey key =
        (com.poesys.db.pk.SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key for inserted object", key != null);
      BsTransaction insertedObject = objects.get(0);
      assertTrue("No created object to update", insertedObject != null);

      // Update the inserted object and query it to test.
      updateColumn(insertedObject);
      delegate.update(insertedObject);
      BsTransaction queriedObject = queryStoredObject(key);
      assertTrue("Object not updated", isColumnUpdated(queriedObject));
    } catch (InvalidParametersException e) {
      fail("Invalid parameters: " + e.getMessage());
    } catch (DelegateException e) {
      fail("Delegate exception: " + e.getMessage());
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction object : objects) {
        object.delete();
      }

      delegate.deleteBatch(objects);
    }
  }

  @Override
  public void testUpdateBatch() throws InvalidParametersException,
      DelegateException {
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      objects = createTransactionTransaction(2);
      delegate.insert(objects);
      // Allocate a map to hold the updated objects for later comparison.
      Map<IPrimaryKey, BsTransaction> map =
        new TreeMap<IPrimaryKey, BsTransaction>();
      for (BsTransaction object : objects) {
        object.setDescription(StringUtilities.generateString(100));
        // Add the object to the holding map.
        map.put(object.getPrimaryKey(), object);
      }

      delegate.updateBatch(objects);

      // Query the objects from the database.
      clearCaches();

      for (BsTransaction object : objects) {
        BsTransaction queriedObject =
          delegate.getObject((SequencePrimaryKey)object.getPrimaryKey());
        BsTransaction originalObject = map.get(object.getPrimaryKey());
        assertTrue("Object not updated",
                   originalObject.getDescription().equals(queriedObject.getDescription()));
      }
    } catch (Exception e) {
      fail(e.getMessage());
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction object : objects) {
        object.delete();
      }

      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }

  @Override
  public void testDelete() throws InvalidParametersException, DelegateException {
    // Create a new Transaction object to perform the test.
    try {
      createAccounts();
      List<BsTransaction> objects = createTransactionTransaction(1);
      delegate.insert(objects);
      SequencePrimaryKey key =
        (SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key for inserted object", key != null);
      BsTransaction insertedObject = objects.get(0);
      assertTrue("No inserted object to delete", insertedObject != null);

      insertedObject.delete();
      delegate.delete(insertedObject);
      assertTrue("object not deleted", !exists(key));
    } catch (Exception e) {
      fail(e.getMessage());
    } finally {
      // object already deleted if it can be
      deleteAccounts();
    }
  }

  @Override
  public void testDeleteBatch() throws InvalidParametersException,
      DelegateException {
    try {
      createAccounts();
      List<BsTransaction> objects = createTransactionTransaction(2);
      delegate.insert(objects);

      // Mark all the objects for delete.
      for (BsTransaction object : objects) {
        object.delete();
      }

      delegate.deleteBatch(objects);

      for (BsTransaction object : objects) {
        assertTrue("object not deleted",
                   !exists((com.poesys.db.pk.SequencePrimaryKey)object.getPrimaryKey()));
      }
    } catch (Exception e) {
      fail(e.getMessage());
    } finally {
      deleteAccounts();
    }
  }

  @Override
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    // Create 3 rows--one to insert, one to update, one to delete.
    List<BsTransaction> allObjects = null;
    try {
      createAccounts();
      allObjects = createTransactionTransaction(3);
      // Insert the first two rows to later update and delete.
      List<BsTransaction> existingObjects = new ArrayList<BsTransaction>();
      existingObjects.add(allObjects.get(0));
      existingObjects.add(allObjects.get(1));
      delegate.insert(existingObjects);
      String updatedDescription = StringUtilities.generateString(100);
      existingObjects.get(0).setDescription(updatedDescription);
      existingObjects.get(1).delete();

      // Set the third object as the object to insert.
      List<BsTransaction> insertObject =
        new CopyOnWriteArrayList<BsTransaction>();
      insertObject.add(allObjects.get(2));

      // Put it all together.
      List<BsTransaction> objects =
        new ArrayList<BsTransaction>(existingObjects);
      objects.addAll(insertObject);

      // Test the process method
      delegate.process(objects);

      // Verify the update
      BsTransaction queriedObject =
        queryStoredObject((SequencePrimaryKey)existingObjects.get(0).getPrimaryKey());
      assertTrue("Update not processed",
                 updatedDescription.equals(queriedObject.getDescription()));

      // Verify the delete
      assertTrue("object not deleted",
                 !exists((SequencePrimaryKey)existingObjects.get(1).getPrimaryKey()));

      // Verify the insert
      assertTrue("object not inserted",
                 exists((SequencePrimaryKey)insertObject.get(0).getPrimaryKey()));
    } catch (Exception e) {
      fail(e.getMessage());
    } finally {

      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction object : allObjects) {
        object.delete();
      }

      delegate.deleteBatch(allObjects);
      deleteAccounts();
    }
  }

  @Override
  public void testUpdateAddItemsItem() {
    // Not used; already tested through creation of transactions with items
  }

  @Override
  public void testTruncateTable() {
    // Truncate usually is hard; super.testTruncateTable() if desired
  }

  @Override
  protected String getSubsystem() {
    // return explicit subsystem name
    return "com.poesys.accounting.db.transaction";
    // use super.getSubsystem() to get default subsystem name if needed
  }

  @Override
  protected List<BsItem> createTransactionItem(ITransaction parent, int count)
      throws DelegateException, InvalidParametersException {
    // not used
    return null;
  }

  /**
   * <p>
   * Test the ReceivableItem and ReimbursementItem scenarios within the
   * Transaction. This tests by creating a transaction, then managing the set of
   * Item objects in the transaction to test the various functions in sequence,
   * as opposed to the usual strategy of separating out the tests into separate
   * test methods. This makes the complex object construction simpler and
   * faster.
   * </p>
   * <p>
   * The test happens in two parts: first, we create a Receivable transaction,
   * testing the various process operations. Then we add a second Reimbursement
   * transaction, testing the linking features, then we test the various process
   * operations.
   * </p>
   * <ul>
   * <li>getObject</li>
   * <li>process (insert/update/delete)</li>
   * </ul>
   * <p>
   * Note that the internal workings of a parent-child complex are such that
   * only certain DAO methods apply, so this test tests only the operative
   * methods, not all methods. Also note that the independent Item delegates are
   * disabled; all item operations should happen within a Transaction and
   * processed by the TransactionDelegate.
   * </p>
   */
  @Test
  public void testReimbursement() {
    Connection connection = null;
    createAccounts();

    // Create the receivable transaction.
    BsTransaction receivableTransaction = createReceivableTransaction();
    // Extract the receivable item from the receivable transaction.
    BsItem receivableItem = null;
    for (BsItem item : receivableTransaction.getItems()) {
      // Test whether the account is a SimpleAccount, then test for receivable.
      if (item.getAccount().toDto() instanceof ISimpleAccount) {
        BsSimpleAccount account =
          new BsSimpleAccount((ISimpleAccount)item.getAccount().toDto());
        if (account.getReceivable()) {
          receivableItem = item;
          break;
        }
      }
    }
    assertTrue("no receivable item in receivable transaction",
               receivableItem != null);
    BsTransaction reimbursementTransaction = createReimbursementTransaction();
    // Extract the reimbursing item from the reimbursement transaction.
    BsItem reimbursingItem = null;
    for (BsItem item : reimbursementTransaction.getItems()) {
      if (item.getAccount().toDto() instanceof ISimpleAccount) {
        BsSimpleAccount account =
          new BsSimpleAccount((ISimpleAccount)item.getAccount().toDto());
        if (account.getReceivable()) {
          reimbursingItem = item;
          break;
        }
      }
    }

    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      List<BsTransaction> transactions = new ArrayList<BsTransaction>(2);
      transactions.add(receivableTransaction);
      transactions.add(reimbursementTransaction);
      delegate.process(transactions);

      // Now create and link the reimbursement.
      BsReimbursement reimbursement =
        delegate.createReimbursement(receivableItem,
                                     reimbursingItem,
                                     receivableItem.getOrderNumber(),
                                     reimbursingItem.getOrderNumber(),
                                     receivableItem.getTransactionId(),
                                     reimbursingItem.getTransactionId(),
                                     receivableItem.getAmount(),
                                     0.00D);

      reimbursingItem.addReceivablesReimbursementReimbursement(reimbursement);

      // Process the change.
      logger.info("Processing reimbursement transaction");
      delegate.process(reimbursementTransaction);

      // check whether the reimbursement is there.
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());
      stmt =
        connection.prepareStatement("SELECT 1 FROM Reimbursement WHERE receivablesOrderNumber = ? AND reimbursingItemsOrderNumber = ? AND receivablesTransactionId = ? AND reimbursingItemsTransactionId = ?");
      stmt.setInt(1, receivableItem.getOrderNumber());
      stmt.setInt(2, reimbursingItem.getOrderNumber());
      stmt.setBigDecimal(3,
                         new BigDecimal(receivableTransaction.getTransactionId()));
      stmt.setBigDecimal(4,
                         new BigDecimal(reimbursementTransaction.getTransactionId()));
      rs = stmt.executeQuery();
      if (!rs.next()) {
        fail("No reimbursement object found in database");
      }
    } catch (Exception e) {
      fail(e.getMessage());
    } finally {
      // Delete the transactions.
      List<BsTransaction> objects = new ArrayList<BsTransaction>();
      if (receivableTransaction != null) {
        objects.add(receivableTransaction);
      }
      if (reimbursementTransaction != null) {
        objects.add(reimbursementTransaction);
      }
      // Mark the transactions for delete.
      for (BsTransaction object : objects) {
        object.delete();
      }
      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }

  /**
   * Test the JSON serialization and deserialization capabilities for a transaction object.
   */
  @Test
  public void testJson() {
    // Create a new Transaction object to perform the test.
    List<BsTransaction> objects = null;
    try {
      createAccounts();
      objects = createTransactionTransaction(1);
      delegate.insert(objects);
      SequencePrimaryKey key =
        (SequencePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key generated from concrete implementation", key != null);
      BsTransaction insertedObject = objects.get(0);
      assertTrue("No comparison object for object query",
                 insertedObject != null);

      // Query the object from the database.
      IDaoManager manager = DaoManagerFactory.getManager(getSubsystem());
      if (manager != null) {
        manager.clearCache(com.poesys.accounting.db.transaction.Transaction.class.getName());
      }
      BsTransaction object = delegate.getObject(key);
      assertTrue("Couldn't get object", object != null);
      assertTrue("Wrong object", insertedObject.equals(object));
      String jsonTransaction = object.toString();
      logger.debug("JSON transaction: " + jsonTransaction);
    } catch (DelegateException e) {
      fail(e.getMessage());
    } catch (Exception e) {
      logger.error("getObject failed", e);
    } finally {
      // Delete the inserted objects to clean up.
      // Mark all the objects for delete.
      for (BsTransaction o : objects) {
        o.delete();
      }
      delegate.deleteBatch(objects);
      deleteAccounts();
    }
  }
}
