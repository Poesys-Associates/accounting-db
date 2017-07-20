/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.transaction;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the Transaction. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the fiscal
 * year accounts must balance (cancel each other out with respect to debits and
 * credits)
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>Persistent</li>
 *     <li>SequenceKey</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class Transaction extends AbstractTransaction {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a Transaction as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public Transaction() {
    super(); 
  }

  /**
   * <p>
   * Create a Transaction. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the Transaction
   * @param transactionId primary key attribute
   * @param description a text describing the nature of the transaction
   * @param transactionDate the calendar day on which the transaction occurred
   * @param checked whether the transaction is reconciled and validated
   * @param balance whether the transaction represents a balance transaction, the transfer of an
amount onto the balance sheet; balance transactions do not need to have
off-setting debits and credits and are ignored in balance checking for normal
transactions
   */
  public Transaction(IPrimaryKey key, java.math.BigInteger transactionId, java.lang.String description, java.sql.Timestamp transactionDate, java.lang.Boolean checked, java.lang.Boolean balance) {
    super(key, transactionId, description, transactionDate, checked, balance); 
  }
}