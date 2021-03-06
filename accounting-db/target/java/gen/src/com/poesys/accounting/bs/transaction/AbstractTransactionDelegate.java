/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegate.vsl

package com.poesys.accounting.bs.transaction;


import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.bs.delegate.AbstractDataDelegate;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.dao.insert.IInsertSql;
import com.poesys.db.dao.update.IUpdateSql;
import com.poesys.db.dao.delete.IDeleteSql;
import com.poesys.db.dao.query.IKeyQuerySql;
import com.poesys.db.dao.query.IQuerySql;


/**
 * <p>
 * A business delegate that provides an application programming interface for
 * Transaction objects and their dependents. You should not modify this class;
 * instead, override or add methods in the class TransactionDelegate, which
 * specializes this class.
 * </p>
 * <p>
 * An exchange of value between accounts; the items that correspond to the fiscal
 * year accounts must balance (cancel each other out with respect to debits and
 * credits)
 * </p>
 * @see TransactionDelegate
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractTransactionDelegate
    extends AbstractDataDelegate<BsTransaction, com.poesys.accounting.db.transaction.ITransaction, com.poesys.db.pk.SequencePrimaryKey> {
  
  @SuppressWarnings("unused")
  private static final org.apache.log4j.Logger logger = org.apache.log4j.Logger.getLogger(AbstractTransactionDelegate.class);
  
  /**
   * Create an AbstractTransactionDelegate object by supplying the database
   * subsystem in the database.properties file.
   * 
   * @param subsystem the database subsystem to use in database.properties
   */
  public AbstractTransactionDelegate(String subsystem) {
    super(subsystem, 2147483647);
  }

  /**
   * Create an AbstractTransactionDelegate object by supplying the database
   * subsystem in the database.properties file and a specific DBMS, usually
   * IConnectionFactory.DBMS.JNDI.
   * 
   * @param subsystem the database subsystem to use in database.properties
   * @param dbms the DBMS to use (usually IConnectionFactory.DBMS.JNDI)
   */
  public AbstractTransactionDelegate(String subsystem, DBMS dbms) {
    super(subsystem, dbms, 2147483647);
  }

  @Override
  protected String getClassName() {
    return com.poesys.accounting.db.transaction.Transaction.class.getName();
  }

  @Override
  protected IInsertSql<com.poesys.accounting.db.transaction.ITransaction> getInsertSql() {
    return new com.poesys.accounting.db.transaction.sql.InsertTransaction();
  }

  @Override
  protected IDeleteSql<com.poesys.accounting.db.transaction.ITransaction> getDeleteSql() {
    return new com.poesys.accounting.db.transaction.sql.DeleteTransaction();
  }

  @Override
  protected IUpdateSql<com.poesys.accounting.db.transaction.ITransaction> getUpdateSql() {
    return new com.poesys.accounting.db.transaction.sql.UpdateTransaction();
  }

  @Override
  protected IKeyQuerySql<com.poesys.accounting.db.transaction.ITransaction> getQueryByKeySql() {
    return new com.poesys.accounting.db.transaction.sql.QueryTransaction();
  }

  @Override
  protected IQuerySql<com.poesys.accounting.db.transaction.ITransaction> getQueryListSql() {
    // Query-All method not required
    return new com.poesys.accounting.db.transaction.sql.QueryAllTransaction();
  }

  @Override
  protected com.poesys.accounting.bs.transaction.BsTransaction wrapData(com.poesys.accounting.db.transaction.ITransaction dto) {
    return new com.poesys.accounting.bs.transaction.BsTransaction(dto);
  }

  /**
   * <p>
   * Create a new Transaction with data fields.
   * </p>
   * <p>
   * The Transaction class has a sequence key; this method generates the
   * sequence for later insertion into the database.
   * </p>
   * 
   * @param transactionId primary key attribute
   * @param description a text describing the nature of the transaction
   * @param transactionDate the calendar day on which the transaction occurred
   * @param checked whether the transaction is reconciled and validated
   * @param balance whether the transaction represents a balance transaction, the transfer of an
amount onto the balance sheet; balance transactions do not need to have
off-setting debits and credits and are ignored in balance checking for normal
transactions
   * @return the new Transaction object
   * @throws DelegateException when there is a problem generating the key or
   *             creating the object
   */
  public com.poesys.accounting.bs.transaction.BsTransaction createTransaction(java.math.BigInteger transactionId, java.lang.String description, java.sql.Timestamp transactionDate, java.lang.Boolean checked, java.lang.Boolean balance)
      throws DelegateException {
      com.poesys.db.pk.SequencePrimaryKey key = null;

    // Generate a new Transaction id if the input key is null.
    if (transactionId == null) {
      try {
        if (dbms.equals(DBMS.MYSQL) || dbms.equals(DBMS.JNDI_MYSQL)) {
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createMySqlSequenceKey("transactionId",
                                                                      "transactionId",
                                                                      "com.poesys.accounting.db.transaction.Transaction",
                                                                      "com.poesys.accounting.db.transaction");
        } else if (dbms.equals(DBMS.ORACLE) || dbms.equals(DBMS.JNDI_ORACLE)) {
          // Create key with sequence transactionId
          key =
            com.poesys.db.pk.PrimaryKeyFactory.createOracleSequenceKey("transactionId",
                                                                       "transactionId",
                                                                       "com.poesys.accounting.db.transaction.Transaction",
                                                                      "com.poesys.accounting.db.transaction");
        } else {
          throw new DelegateException("com.poesys.bs.delegate.msg.noDbms");
        }
        // Get the sequence number for use as an attribute value.
        transactionId = key.getValue();
      } catch (com.poesys.db.InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      } catch (com.poesys.db.NoPrimaryKeyException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new DelegateException(message, e);
      }
    } else {
      key = com.poesys.db.pk.PrimaryKeyFactory.createSequenceKey("transactionId", transactionId, "com.poesys.accounting.db.transaction.Transaction");
    }

    // Create a data-access DTO for direct access, no proxy required.
    com.poesys.accounting.db.transaction.ITransaction dto =
      new com.poesys.accounting.db.transaction.Transaction(key, transactionId, description, transactionDate, checked, balance);

    // Create the business DTO.
    return new com.poesys.accounting.bs.transaction.BsTransaction(dto);
  }

  /**
   * Create a new Reimbursement association class child of Transaction with 
   * an association key. This class links the input objects.
   * 
   * @param receivablesObject associated Item object (part of the key)
   * @param reimbursingItemsObject associated Item object (part of the key)
   * @param receivablesOrderNumber Attribute that is part of the association key
   * @param reimbursingItemsOrderNumber Attribute that is part of the association key
   * @param receivablesTransactionId Attribute that is part of the association key
   * @param reimbursingItemsTransactionId Attribute that is part of the association key
   * @param reimbursedAmount the amount of the receivable item amount that is reimbursed by the reimbursement
item (must be less than or equal to the reimbursement amount)
   * @param allocatedAmount dollar amount not reimbursed by any reimbursement item; this "writes off" the
allocated amount when summing up amounts applied against the receivable
   * @return a new Reimbursement business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.accounting.bs.transaction.BsReimbursement createReimbursement(com.poesys.accounting.bs.transaction.BsItem receivablesObject, com.poesys.accounting.bs.transaction.BsItem reimbursingItemsObject, java.lang.Integer receivablesOrderNumber, java.lang.Integer reimbursingItemsOrderNumber, java.math.BigInteger receivablesTransactionId, java.math.BigInteger reimbursingItemsTransactionId, java.lang.Double reimbursedAmount, java.lang.Double allocatedAmount) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.AssociationPrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.pk.IPrimaryKey> list =
        new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
      list.add(receivablesObject.getPrimaryKey());
      list.add(reimbursingItemsObject.getPrimaryKey());
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createAssociationKey(list, "com.poesys.accounting.db.transaction.Reimbursement");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create an association-key child data-access Reimbursement DTO proxy (supports lazy loading).
    com.poesys.accounting.db.transaction.IReimbursement dto =
      new com.poesys.accounting.db.transaction.ReimbursementProxy(new com.poesys.accounting.db.transaction.Reimbursement(key, receivablesObject.toDto(), reimbursingItemsObject.toDto(), receivablesOrderNumber, reimbursingItemsOrderNumber, receivablesTransactionId, reimbursingItemsTransactionId, reimbursedAmount, allocatedAmount));

    // Create the business DTO.
    return new com.poesys.accounting.bs.transaction.BsReimbursement(dto);
  }
  /**
   * Create a new Item child of Transaction with a composite key.
   * 
   * @param parent the parent of the child object to create
   * @param transactionId composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param orderNumber 
   * @param amount the monetary amount in dollars of the transaction item
   * @param debit whether the item is a debit (true) or credit (false); default value is the
debit-default value of the account
   * @param checked whether the value and details of the transaction item have been verified and
reconciled
   * @param accountName foreign key used by setter to query associated object
   * @param entityName foreign key used by setter to query associated object
   * @return a new Item business layer DTO
   * @throws DelegateException when a parameter causes a problem
   */
  public com.poesys.accounting.bs.transaction.BsItem createItem(com.poesys.accounting.bs.transaction.BsTransaction parent, java.math.BigInteger transactionId, java.lang.Integer orderNumber, java.lang.Double amount, java.lang.Boolean debit, java.lang.Boolean checked, java.lang.String accountName, java.lang.String entityName) throws DelegateException {
    // Create the key.
    com.poesys.db.pk.CompositePrimaryKey key = null;
    try {
      java.util.ArrayList<com.poesys.db.col.IColumnValue> list = new java.util.ArrayList<>();
      list.add(new com.poesys.db.col.IntegerColumnValue("orderNumber", orderNumber));
	  com.poesys.db.pk.IPrimaryKey subKey = 
	    com.poesys.db.pk.PrimaryKeyFactory.createNaturalKey(list, "com.poesys.accounting.db.transaction.Item");
      key = 
        com.poesys.db.pk.PrimaryKeyFactory.createCompositeKey(parent.getPrimaryKey(), 
                                                              subKey,
                                                              "com.poesys.accounting.db.transaction.Item");
    } catch (com.poesys.db.InvalidParametersException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    } catch (com.poesys.db.DuplicateKeyNameException e) {
      Object[] args = e.getParameters().toArray();
      String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
      throw new DelegateException(message, e);
    }

    // Create a composite-key child data-access Item DTO proxy (supports lazy loading).
    com.poesys.accounting.db.transaction.IItem dto =
      new com.poesys.accounting.db.transaction.ItemProxy(new com.poesys.accounting.db.transaction.Item(key, transactionId, orderNumber, amount, debit, checked, accountName, entityName));

    // Create the business DTO.
    return new com.poesys.accounting.bs.transaction.BsItem(dto);
  }

  @Override
  public void truncateTable(String tableName) throws DelegateException {
    com.poesys.db.dao.ddl.ISql sql = null;
    com.poesys.db.dao.ddl.IExecuteSql executive = null;

    try {
      // First truncate any child tables.
      sql = new com.poesys.db.dao.ddl.TruncateTableSql("Reimbursement");
      executive = new com.poesys.db.dao.ddl.ExecuteSql(sql, subsystem);
      executive.execute();

      sql = new com.poesys.db.dao.ddl.TruncateTableSql("Item");
      executive = new com.poesys.db.dao.ddl.ExecuteSql(sql, subsystem);
      executive.execute();

      // Now truncate the current table.
      sql = new com.poesys.db.dao.ddl.TruncateTableSql(tableName);
      executive = new com.poesys.db.dao.ddl.ExecuteSql(sql, subsystem);
      executive.execute();
    } catch (Throwable e) {
      throw new DelegateException("Error truncating table " + tableName, e);
    }
  }
}
