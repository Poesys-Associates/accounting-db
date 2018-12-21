/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl
// Modified by Poesys

package com.poesys.accounting.bs.transaction;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poesys.accounting.bs.account.AccountDelegate;
import com.poesys.accounting.bs.account.AccountDelegateFactory;
import com.poesys.accounting.bs.account.BsAccount;
import com.poesys.accounting.bs.account.BsSimpleAccount;
import com.poesys.accounting.db.account.ISimpleAccount;
import com.poesys.accounting.db.account.SimpleAccount;
import com.poesys.accounting.db.account.SimpleAccountProxy;
import com.poesys.accounting.db.transaction.*;
import com.poesys.accounting.db.transaction.json.JsonItem;
import com.poesys.accounting.db.transaction.json.JsonTransaction;
import com.poesys.db.AbstractJsonObject;
import com.poesys.db.pk.CompositePrimaryKey;
import com.poesys.db.pk.SequencePrimaryKey;
import com.poesys.db.pk.json.IJsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKey;
import com.poesys.db.pk.json.JsonPrimaryKeyFactory;
import org.apache.log4j.Logger;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.DuplicateKeyNameException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.Message;
import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.pk.IPrimaryKey;

/**
 * <p>
 * A business delegate that provides an application programming interface for
 * Transaction objects and their dependents. This class delegates to an abstract
 * class, AbstractTransactionDelegate. You can modify this class to override
 * methods in that class or to add operations to the API.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the
 * fiscal year accounts must balance (cancel each other out with respect to
 * debits and credits)
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public class TransactionDelegate extends AbstractTransactionDelegate {
  private static final Logger logger = Logger.getLogger(TransactionDelegate.class);

  /**
   * Create a TransactionDelegate object with a supplied subsystem, using the
   * default database properties (usually for testing).
   *
   * @param subsystem the subsystem to use
   */
  public TransactionDelegate(String subsystem) {
    super(subsystem);
  }

  /**
   * Create a TransactionDelegate object with a supplied subsystem and DBMS,
   * usually JNDI for production/test usage with an application server.
   *
   * @param subsystem the subsystem to use
   * @param dbms      the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public TransactionDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }

  @Override
  public BsTransaction createTransaction(BigInteger transactionId, String description, Timestamp
    transactionDate, Boolean checked, Boolean balance) throws DelegateException {
    BsTransaction transaction =
      super.createTransaction(transactionId, description, transactionDate, checked, balance);
    logger.debug("Created transaction " + transaction.getTransactionId());
    return transaction;
  }

  // Correct key creation to use appropriate factory method.
  @Override
  public BsReimbursement createReimbursement(BsItem receivablesObject, BsItem
    reimbursingItemsObject, Integer receivablesOrderNumber, Integer reimbursingItemsOrderNumber,
                                             BigInteger receivablesTransactionId, BigInteger
                                                 reimbursingItemsTransactionId, Double
                                                 reimbursedAmount, Double allocatedAmount) throws
    DelegateException {
    // Create the key.
    IPrimaryKey key = null;
    try {
      key = TransactionFactory.getReimbursementPrimaryKey(receivablesObject.getOrderNumber(),
                                                          reimbursingItemsObject.getOrderNumber(),
                                                          receivablesObject.getTransactionId(),
                                                          reimbursingItemsObject.getTransactionId
                                                            ());
    } catch (InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access Reimbursement DTO proxy
    // (supports lazy loading).
    IReimbursement dto = new ReimbursementProxy(
      new Reimbursement(key, receivablesObject.toDto(), reimbursingItemsObject.toDto(),
                        receivablesOrderNumber, reimbursingItemsOrderNumber,
                        receivablesTransactionId, reimbursingItemsTransactionId, reimbursedAmount,
                        allocatedAmount));

    // Create the business DTO.
    return new BsReimbursement(dto);
  }

  /**
   * Get a Transaction business object, either by creating a new one or by querying an existing
   * one based on a primary key.
   *
   * @param json the JSON string
   * @return a transaction business object
   */
  public BsTransaction createTransaction(String json) {
    BsTransaction object = null;
    // Create the Gson object.
    Gson gson = new GsonBuilder().serializeNulls().create();
    // Get the JSON object from the string.
    JsonTransaction jsonTransaction = gson.fromJson(json, JsonTransaction.class);
    // Generate the new DTO object from the JSON object.
    JsonPrimaryKey jsonKey = jsonTransaction.getPrimaryKey();
    if (jsonKey != null && !jsonTransaction.getStatus().equals(AbstractJsonObject.NEW)) {
      // Convert the generic JSON key to a specific type, then extract the Poesys primary key.
      IPrimaryKey transactionKey =
        JsonPrimaryKeyFactory.getJsonPrimaryKey(jsonTransaction.getPrimaryKey()).getPrimaryKey();
      object = getObject((SequencePrimaryKey)transactionKey);
      // Handle transaction changes or deletion
      switch (jsonTransaction.getStatus()) {
        case (AbstractJsonObject.CHANGED):
          // JSON represents changes to object, copy the changes
          object.setDescription(jsonTransaction.getDescription());
          object.setBalance(jsonTransaction.getBalance());
          object.setChecked(jsonTransaction.getChecked());
          break;
        case (AbstractJsonObject.DELETED):
          object.delete();
        default:
          // Nothing to do
      }
      // Handle item changes or deletion.
      setItems(object.getItems(), jsonTransaction.getItems());
    } else if (jsonKey == null && jsonTransaction.getStatus().equals(AbstractJsonObject.NEW)) {
      object = createTransaction(null, jsonTransaction.getDescription(),
                                 jsonTransaction.getTransactionDate(), jsonTransaction.getChecked(),
                                 jsonTransaction.getBalance());
      createItems(object, jsonTransaction);
    }
    return object;
  }

  /**
   * Copy any changes to a set of item business objects from a set of item JSON objects.
   *
   * @param bsItems the item business objects
   * @param jsonItems the item JSON objects
   */
  private void setItems(List<BsItem> bsItems, List<JsonItem> jsonItems) {
    for (BsItem bsItem : bsItems) {
      for (JsonItem jsonItem : jsonItems) {
        if (jsonItem.getStatus().equals(AbstractJsonObject.CHANGED)) {
          // Set the updateable fields: account, amount, checked, and debit.
          if (!jsonItem.getAccountName().equals(bsItem.getAccountName())) {
            // Get the BsAccount business object.
            BsAccount account = getBsAccount(jsonItem.getAccountName(), jsonItem.getAccountKey());
            // Change the account in the business object.
            bsItem.setAccount(account);
          }
          if (!jsonItem.getAmount().equals(bsItem.getAmount())) {
            bsItem.setAmount(jsonItem.getAmount());
          }
          if (!jsonItem.getChecked().equals(bsItem.getChecked())) {
            bsItem.setChecked(jsonItem.getChecked());
          }
          if (!jsonItem.getDebit().equals(bsItem.getDebit())) {
            bsItem.setDebit(jsonItem.getDebit());
          }
          synchReceivableItems(bsItem, jsonItem);
        } else if (jsonItem.getStatus().equals(AbstractJsonObject.DELETED)) {
          // Mark the item business object as deleted.
          bsItem.delete();
        }
      }
    }
  }

  /**
   * Synchronize the changes in lists relating to receivables in an item business object from a JSON object.
   * @param bsItem the item business object to set
   * @param jsonItem the JSON object containing changes
   */
  private void synchReceivableItems(BsItem bsItem, JsonItem jsonItem) {
    if (bsItem.getAccount().toDto() instanceof SimpleAccountProxy) {
      BsSimpleAccount simpleAccount = new BsSimpleAccount((ISimpleAccount)bsItem.getAccount().toDto());
      if (simpleAccount.getReceivable() && bsItem.getDebit()) {
        // TODO Receivable item, check reimbursing item lists
      } else if (simpleAccount.getReceivable()) {
        // TODO Reimbursing item, check receivable lists
      }
    }
  }

  /**
   * Create a list of item business objects from a JSON transaction and set the parent
   * transaction with the list.
   *
   * @param object          the transaction business object with which to associate the list of
   *                        items
   * @param jsonTransaction the JSON transaction containing a list of JSON items
   */
  private void createItems(BsTransaction object, JsonTransaction jsonTransaction) {
    List<JsonItem> jsonItems = jsonTransaction.getItems();
    List<BsItem> items = new ArrayList<>();
    if (jsonItems != null && !jsonItems.isEmpty()) {
      for (JsonItem jsonItem : jsonItems) {
        items.add(createItemFromJsonItem(object, jsonItem));
      }
    }
    object.setItems(items);
  }

  /**
   * Create an item business object from a JSON item.
   *
   * @param object   the parent transaction object (part of the primary key)
   * @param jsonItem the JSON item
   * @return the item business object
   */
  private BsItem createItemFromJsonItem(BsTransaction object, JsonItem jsonItem) {
    // Extract the entity name from the Account primary key.
    JsonPrimaryKey jsonAccountKey = jsonItem.getAccountKey();
    String entityName = jsonAccountKey.getParentKey().getColumnValueList().get(0).getValue();
    BsItem item =
      createItem(object, object.getTransactionId(), jsonItem.getOrderNumber(), jsonItem.getAmount(),
                 jsonItem.getDebit(), jsonItem.getChecked(), jsonItem.getAccountName(), entityName);

    BsAccount account = getBsAccount(jsonItem.getAccountClassName(), jsonAccountKey);
    item.setAccount(account);

    // TODO Add receivable/reimbursement lists if this is a receivable or reimbursing item.

    return item;
  }

  /**
   * Query an account business object using the generic AccountDelegate and the account key.
   *
   * @param accountName the account name with which to initialize the generic delegate
   * @param jsonAccountKey the JSON primary key with which to query the account
   * @return the account business object
   */
  private BsAccount getBsAccount(String accountName, JsonPrimaryKey jsonAccountKey) {
    // Query the account and set the item account object with the result.
    IJsonPrimaryKey realJsonAccountKey = JsonPrimaryKeyFactory.getJsonPrimaryKey(jsonAccountKey);
    IPrimaryKey accountKey = realJsonAccountKey.getPrimaryKey();
    AccountDelegate delegate =
      AccountDelegateFactory.getAccountDelegate(accountName);
    return delegate.getObject((CompositePrimaryKey)accountKey);
  }
}