/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Delegate.vsl
// Modified by Poesys

package com.poesys.accounting.bs.transaction;


import java.math.BigInteger;
import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.poesys.accounting.db.transaction.IReimbursement;
import com.poesys.accounting.db.transaction.Reimbursement;
import com.poesys.accounting.db.transaction.ReimbursementProxy;
import com.poesys.accounting.db.transaction.TransactionFactory;
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
  private static final Logger logger =
    Logger.getLogger(TransactionDelegate.class);

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
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public TransactionDelegate(String subsystem, IConnectionFactory.DBMS dbms) {
    super(subsystem, dbms);
  }

  @Override
  public BsTransaction createTransaction(BigInteger transactionId,
                                         String description,
                                         Timestamp transactionDate,
                                         Boolean checked, Boolean balance)
      throws DelegateException {
    BsTransaction transaction =
      super.createTransaction(transactionId,
                              description,
                              transactionDate,
                              checked,
                              balance);
    logger.debug("Created transaction " + transaction.getTransactionId());
    return transaction;
  }

  // Correct key creation to use appropriate factory method.
  @Override
  public BsReimbursement createReimbursement(BsItem receivablesObject,
                                             BsItem reimbursingItemsObject,
                                             Integer receivablesOrderNumber,
                                             Integer reimbursingItemsOrderNumber,
                                             BigInteger receivablesTransactionId,
                                             BigInteger reimbursingItemsTransactionId,
                                             Double reimbursedAmount,
                                             Double allocatedAmount)
      throws DelegateException {
    // Create the key.
    IPrimaryKey key = null;
    try {
      key =
        TransactionFactory.getReimbursementPrimaryKey(receivablesObject.getOrderNumber(),
                                                      reimbursingItemsObject.getOrderNumber(),
                                                      receivablesObject.getTransactionId(),
                                                      reimbursingItemsObject.getTransactionId());
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
    IReimbursement dto =
      new ReimbursementProxy(new Reimbursement(key,
                                               receivablesObject.toDto(),
                                               reimbursingItemsObject.toDto(),
                                               receivablesOrderNumber,
                                               reimbursingItemsOrderNumber,
                                               receivablesTransactionId,
                                               reimbursingItemsTransactionId,
                                               reimbursedAmount,
                                               allocatedAmount));

    // Create the business DTO.
    return new BsReimbursement(dto);
  }
}