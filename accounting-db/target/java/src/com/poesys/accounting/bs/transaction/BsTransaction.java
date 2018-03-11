/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl
// Modified by Poesys

package com.poesys.accounting.bs.transaction;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.log4j.Logger;

import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the Transaction. This class is the
 * concrete subclass of the generated abstract class. Make any changes to DTO
 * behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will never
 * overwrite this concrete subclass.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the
 * fiscal year accounts must balance (cancel each other out with respect to
 * debits and credits)
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 * <li>Persistent</li>
 * <li>SequenceKey</li>
 * </ul>
 * <p>
 * The custom method isValid() provides basic validation for transactions.
 * </p>
 * @author Poesys/DB Cartridge
 */
public class BsTransaction extends AbstractBsTransaction {
  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(BsTransaction.class);

  /**
   * Create a BsTransaction object from a Transaction object.
   * 
   * @param dto the data-access layer Transaction DTO
   * @throws DelegateException when there is a problem creating the Transaction
   */
  public BsTransaction(com.poesys.accounting.db.transaction.ITransaction dto)
      throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsTransaction. This constructor calls the abstract superclass
   * constructor.
   * </p>
   *
   * @param key the primary key of the Transaction
   * @param transactionId primary key attribute
   * @param description a text describing the nature of the transaction
   * @param transactionDate the calendar day on which the transaction occurred
   * @param checked whether the transaction is reconciled and validated
   * @param balance whether the transaction represents a balance transaction,
   *          the transfer of an amount onto the balance sheet; balance
   *          transactions do not need to have off-setting debits and credits
   *          and are ignored in balance checking for normal transactions
   */
  public BsTransaction(IPrimaryKey key,
                       java.math.BigInteger transactionId,
                       java.lang.String description,
                       java.sql.Timestamp transactionDate,
                       java.lang.Boolean checked,
                       java.lang.Boolean balance) {
    super(key, transactionId, description, transactionDate, checked, balance);
  }

  /**
   * Is the transaction valid? To be valid, a transaction must have balanced
   * transaction items (debits and credits sum to zero) or must be a balance
   * transaction with a single item.
   * 
   * @return true if valid, false if invalid
   */
  public Boolean isValid() {
    Boolean valid = Boolean.FALSE;

    if (dto.getBalance() && dto.getItems().size() == 1) {
      valid = true;
    } else if (getBalance() && getItems().size() > 1) {
      logger.error("Balance transaction has more than one item: " + this);
    } else if (getBalance() && getItems().size() == 0) {
      logger.error("Balance transaction has no balance item: " + this);
    } else if (getItems().size() < 2) {
      logger.error("Transaction has less than 2 items: " + this);
    } else {
      // Sum the items, taking debits as negative values.
      Double sum = 0.00D;
      for (BsItem item : getItems()) {
        sum += item.getDebit() ? -item.getAmount() : item.getAmount();
      }
      if (sum != 0.00D) {
        logger.error("Transaction does not balance (" + sum + "): " + this);
      } else {
        valid = Boolean.TRUE;
      }
    }
    return valid;
  }

  @Override
  public String toString() {
    // TODO create JSON DTO from DB DTO, serialize into String
    Gson gson = new GsonBuilder().serializeNulls().create();
    return gson.toJson(dto, dto.getClass());
  }
}