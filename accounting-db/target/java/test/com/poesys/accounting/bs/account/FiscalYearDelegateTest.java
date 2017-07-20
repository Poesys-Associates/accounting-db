/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import com.poesys.accounting.bs.transaction.BsItem;
import com.poesys.accounting.bs.transaction.BsReimbursement;
import com.poesys.accounting.bs.transaction.BsTransaction;
import com.poesys.accounting.bs.transaction.TransactionDelegate;
import com.poesys.accounting.bs.transaction.TransactionDelegateFactory;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.connection.JdbcConnectionManager;
import com.poesys.db.pk.NaturalPrimaryKey;


/**
 * <p>
 * A test class for the FiscalYearDelegate class. This class is the concrete
 * subclass of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class FiscalYearDelegateTest extends
    com.poesys.accounting.bs.account.AbstractFiscalYearDelegateTest {

  private static final String DESCRIPTION = "description";
  private BsAccount checkingAccount;
  private BsAccount arAccount;
  private BsAccount liabilityAccount;
  private BsAccount equityAccount;
  private BsAccount expenseAccount;
  private BsAccount incomeAccount;

  @Override
  protected java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> createAccountFiscalYearAccount(java.util.List<com.poesys.accounting.db.account.IAccount> accountsList,
                                                                                                                java.util.List<com.poesys.accounting.db.account.IFiscalYear> yearsList,
                                                                                                                int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> objects =
      new java.util.concurrent.CopyOnWriteArrayList<com.poesys.accounting.bs.account.BsFiscalYearAccount>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (accountsList.size() < count) {
      org.junit.Assert.fail("createAccountFiscalYearAccount accountsList list has "
                            + accountsList.size()
                            + " elements but needs at least " + count);
    }
    if (yearsList.size() < count) {
      org.junit.Assert.fail("createAccountFiscalYearAccount yearsList list has "
                            + yearsList.size()
                            + " elements but needs at least " + count);
    }

    // implement child creation by mapping the objects in the input lists
    // one-to-one

    FiscalYearDelegate delegate =
      AccountDelegateFactory.getFiscalYearDelegate();

    for (int i = 0; i < count; i++) {
      BsAccount account = new BsAccount(accountsList.get(i));
      BsFiscalYear year = new BsFiscalYear(yearsList.get(i));

      BsFiscalYearAccount link =
        delegate.createFiscalYearAccount(account,
                                         year,
                                         account.getAccountName(),
                                         account.getEntityName(),
                                         year.getYear(),
                                         i);
      objects.add(link);
    }

    return objects;
  }

  // Overridden to replace date generation with more useful values using Java
  // 1.8 date/time classes.

  @Override
  protected List<com.poesys.accounting.bs.account.BsFiscalYear> createAccountFiscalYear(int count)
      throws DelegateException, InvalidParametersException {

    List<BsFiscalYear> objects = new ArrayList<BsFiscalYear>();

    for (int i = 0; i < count; i++) {
      // Make year sequential starting with 2010.
      Integer year = 2010 + i;

      // Make start and end timestamps Jan 1 to Dec 31 of the year.
      Timestamp startDate =
        Timestamp.valueOf(LocalDateTime.of(year, Month.JANUARY, 1, 0, 0));
      Timestamp endDate =
        Timestamp.valueOf(LocalDateTime.of(year, Month.DECEMBER, 31, 0, 0));

      // Create the object.
      BsFiscalYear object = null;
      try {
        object = delegate.createFiscalYear(year, startDate, endDate);
      } catch (InvalidParametersException e) {
        Object[] args = e.getParameters().toArray();
        String message = com.poesys.db.Message.getMessage(e.getMessage(), args);
        throw new RuntimeException(message, e);
      } catch (DelegateException e) {
        throw new RuntimeException(e.getMessage(), e);
      }

      objects.add(object);
    }

    return objects;
  }

  // Overridden to do update test differently using Java 1.8 date/time classes.

  @Override
  @Test
  public void testUpdateBatch() throws InvalidParametersException,
      DelegateException {
    List<BsFiscalYear> objects = createAccountFiscalYear(2);
    delegate.insert(objects);
    // Allocate a map to hold the updated objects for later comparison.
    java.util.Map<com.poesys.db.pk.IPrimaryKey, BsFiscalYear> map =
      new java.util.TreeMap<com.poesys.db.pk.IPrimaryKey, BsFiscalYear>();
    for (BsFiscalYear object : objects) {
      // Update the start date to a day more than the current value.
      Instant instant = object.getStartDate().toInstant();
      ZonedDateTime datetime = ZonedDateTime.ofInstant(instant, ZoneOffset.UTC);
      instant = datetime.plus(1, ChronoUnit.DAYS).toInstant();
      object.setStartDate(Timestamp.from(instant));
      // Add the object to the storage map.
      map.put(object.getPrimaryKey(), object);
    }
    delegate.updateBatch(objects);

    clearCaches();

    for (BsFiscalYear object : objects) {
      BsFiscalYear queriedObject =
        delegate.getObject((NaturalPrimaryKey)object.getPrimaryKey());
      BsFiscalYear updatedObject = map.get(object.getPrimaryKey());
      assertTrue("Object not updated: " + queriedObject + " vs. "
                     + updatedObject,
                 queriedObject.getStartDate().equals(updatedObject.getStartDate()));
    }
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsFiscalYear object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  @Override
  public void testTruncateTable() {
    // Truncate usually is hard; super.testTruncateTable() if desired
  }

  @Override
  public void testGetAllObjects() {
    // Too many objects to get, super.testGetAllObjects() if desired
  }

  @Override
  protected String getSubsystem() {
    // return explicit subsystem name
    return "com.poesys.accounting.db.account";
    // use super.getSubsystem() to get default subsystem name if needed
  }

  /*
   * Test method for process(). This overridden version of the test constructs a
   * complete accounting system (entity, years, groups, accounts, transactions,
   * items, receivables) and processes the whole tree into the database, then
   * tests to make sure everything made it. This provides a full-scale
   * integration test of the kind of processing that the data loader will do to
   * create the initial database.
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   * creation of an object
   * 
   * @throws DelegateException when a problem occurs during object creation
   */
  @Override
  @Test
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    Connection connection = null;
    try {
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());
    } catch (SQLException | IOException e1) {
      fail("Cannot get JDBC connection for test validation");
    }
    // Set up the various delegates required.
    AccountGroupDelegate agDel =
      AccountDelegateFactory.getAccountGroupDelegate();
    EntityDelegate enDel = AccountDelegateFactory.getEntityDelegate();
    TransactionDelegate transDel =
      TransactionDelegateFactory.getTransactionDelegate();

    // First, run the superclass to do the standard test. This will delete all
    // the data at the end of the super-test.
    logger.info("Running generated test of FiscalYear process()");
    try {
      super.testProcess();
    } catch (Exception e1) {
      logger.error("Process test exception", e1);
      fail("Exception running standard process() test: " + e1.getMessage());
    } finally {
      // Clean out the entire test database.
      try {
        Statement stmt = connection.createStatement();
        stmt.execute("delete from AccountGroup");
        stmt.execute("delete from Transaction");
        stmt.execute("delete from Account");
        stmt.execute("delete from Entity");
        stmt.execute("delete from FiscalYear");
        stmt.close();
        connection.commit();
      } catch (SQLException e) {
        String message = "SQL error cleaning up after standard process test";
        logger.error(message, e);
        fail(message + ": " + e.getMessage());
      }
    }

    // Create and store the fiscal years, account groups, and accounts.

    logger.info("Running full integration test of accounting system");
    List<BsFiscalYear> years = null;
    List<BsAccountGroup> groups = new ArrayList<BsAccountGroup>(6);
    List<BsTransaction> transactions = null;
    BsEntity entity = null;

    try {
      // Create the fiscal years.
      years = createAccountFiscalYear(3);

      // Create the account groups.
      BsAccountGroup cashGroup = agDel.createAccountGroup("Cash");
      groups.add(cashGroup);
      BsAccountGroup incomeGroup = agDel.createAccountGroup("Earned Income");
      groups.add(incomeGroup);
      BsAccountGroup expenseGroup =
        agDel.createAccountGroup("Household Expenses");
      groups.add(expenseGroup);
      BsAccountGroup liabilityGroup =
        agDel.createAccountGroup("Credit Accounts");
      groups.add(liabilityGroup);
      BsAccountGroup capitalGroup =
        agDel.createAccountGroup("Personal Capital");
      groups.add(capitalGroup);
      BsAccountGroup arGroup = agDel.createAccountGroup("Accounts Receivable");
      groups.add(arGroup);

      // Create the entity.

      entity = enDel.createEntity("Poesys Associates");

      // Initialize account fields.
      checkingAccount =
        createAccount(enDel,
                      entity,
                      "Checking",
                      "Asset",
                      false,
                      cashGroup,
                      years);
      arAccount =
        createAccount(enDel,
                      entity,
                      "Accounts Receivable",
                      "Asset",
                      true,
                      arGroup,
                      years);
      liabilityAccount =
        createAccount(enDel,
                      entity,
                      "Credit Card",
                      "Liability",
                      false,
                      liabilityGroup,
                      years);
      equityAccount =
        createAccount(enDel,
                      entity,
                      "Personal Capital",
                      "Equity",
                      false,
                      capitalGroup,
                      years);
      expenseAccount =
        createAccount(enDel,
                      entity,
                      "Household Expenses",
                      "Expense",
                      false,
                      expenseGroup,
                      years);
      incomeAccount =
        createAccount(enDel,
                      entity,
                      "Company Salary",
                      "Income",
                      false,
                      incomeGroup,
                      years);
      
      // Store the entity and accounts. Note: the process is required here to
      // work around a problem with the order of nested object processing; it
      // doesn't insert the entity before attempting inserting accounts.
      enDel.process(entity);

      // Create transactions for the years using the accounts through the
      // initialized fields, then store transactions, items, accounts, and
      // groups.
      transactions = createTransactions(transDel, years);
      transDel.process(transactions);

      // Validate the objects against the database.
      validateFiscalYears(connection, years);
      validateAccountGroups(connection, groups);
      validateTransactions(connection, transactions);

      // Commit the JDBC connection to free resources.
      connection.commit();
    } catch (Exception e) {
      logger.error("Creating full integration test failed", e);
      fail(e.getMessage());
    } finally {
      // Close the JDBC connection to free resources.
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }

      // Delete all the stored data. Transactions get deleted first due to the
      // dependency structure, then groups, then entities and accounts, then
      // fiscal years. Each conditional marks the objects at the top of a
      // composite or inheritance hierarchy and relies on cascaded deletes to
      // delete the children. It then uses a delegate to process the deletions.

      if (years != null) {
        if (transactions != null) {
          for (BsTransaction transaction : transactions) {
            transaction.delete();
          }
          transDel.process(transactions);
        }

        if (groups != null) {
          for (BsAccountGroup group : groups) {
            group.delete();
          }
          agDel.process(groups);
        }

        if (entity != null) {
          entity.delete();
          enDel.process(entity);
        }

        if (years != null) {
          for (BsFiscalYear object : years) {
            object.delete();
          }
          delegate.process(years);
        }
      }
    }
  }

  /**
   * <p>
   * Validate the list of generated transactions against the current state of
   * the database using JDBC queries. The transaction set is complex and
   * requires a number of different validations:
   * </p>
   * <ul>
   * <li>each created transaction exists</li>
   * <li>for each specific transaction, check the relevant data in the
   * transaction and items</li>
   * <li>for the receivable transaction, test reimbursement link</li>
   * <li>for the reimbursement transaction, test reimbursement link</li>
   * </ul>
   * 
   * @param connection the JDBC connection
   * @param transactions the list of transactions to validate
   */
  private void validateTransactions(Connection connection,
                                    List<BsTransaction> transactions) {
    if (transactions == null || transactions.size() == 0) {
      fail("No transactions created");
    }

    // First test for existence of transactions.
    boolean found = true;
    boolean atLeastOne = false;
    Statement stmt = null;
    PreparedStatement pstmt = null;
    ResultSet rs = null;
    Set<BigInteger> queriedTransactions = new HashSet<BigInteger>();
    String message = "transaction existence: ";
    try {
      stmt = connection.createStatement();
      rs = stmt.executeQuery("SELECT transactionId FROM Transaction");
      while (rs.next()) {
        atLeastOne = true;
        BigDecimal decimalId = rs.getBigDecimal("transactionId");
        queriedTransactions.add(decimalId.toBigInteger());
      }

      // Check whether there is at least one transaction coming back.
      if (!atLeastOne) {
        fail("No transactions queried from database");
      }

      // There is at least one, make sure all the transactions came back.
      for (BsTransaction transaction : transactions) {
        if (!queriedTransactions.contains(transaction.getTransactionId())) {
          found = false;
          break;
        }
      }

      message = "transaction data and items: ";
      pstmt =
        connection.prepareStatement("SELECT description, transactionDate, t.checked AS transChecked, t.balance, i.accountName, i.entityName, i.amount, i.debit, i.checked AS itemChecked FROM Transaction t JOIN Item i ON t.transactionId = i.transactionId WHERE t.transactionId = ? AND i.orderNumber = ?");
      for (BsTransaction transaction : transactions) {
        for (BsItem item : transaction.getItems()) {
          // Query item with full transaction data
          pstmt.setBigDecimal(1, new BigDecimal(item.getTransactionId()));
          pstmt.setInt(2, item.getOrderNumber());
          rs = pstmt.executeQuery();
          // Expecting exactly one item; check for empty result set.
          if (rs.next()) {
            String description = rs.getString("description");
            Timestamp transactionDate = rs.getTimestamp("transactionDate");
            Boolean transChecked = rs.getBoolean("transChecked");
            Boolean balance = rs.getBoolean("balance");
            String account = rs.getString("accountName");
            String entity = rs.getString("entityName");
            Double amount = rs.getDouble("amount");
            Boolean debit = rs.getBoolean("debit");
            Boolean itemChecked = rs.getBoolean("itemChecked");
            assertTrue("Wrong description: " + description + " for "
                           + transaction,
                       description.equals(transaction.getDescription()));
            assertTrue("Wrong date: " + transactionDate + " for " + transaction,
                       transactionDate.equals(transaction.getTransactionDate()));
            assertTrue("Wrong transaction checked flag: " + transChecked
                           + " for " + transaction,
                       transChecked == transaction.getChecked());
            assertTrue("Wrong balance flag: " + balance + " for " + transaction,
                       balance == transaction.getBalance());
            assertTrue("Wrong account: " + account + " for " + item,
                       account.equals(item.getAccountName()));
            assertTrue("Wrong entity: " + entity + " for " + item,
                       entity.equals(item.getEntityName()));
            assertTrue("Wrong amount: " + amount + " for " + item,
                       amount.equals(item.getAmount()));
            assertTrue("Wrong item checked flag: " + itemChecked + " for "
                       + item, itemChecked == item.getChecked());
            assertTrue("Wrong debit flag: " + debit + " for " + item,
                       debit == item.getDebit());
          } else {
            fail("No item for transaction " + transaction + ": " + item);
          }
          rs.close();
        }
      }
      pstmt.close();

      message = "receivable item: ";
      found = false;
      pstmt =
        connection.prepareStatement("SELECT 1 FROM Item rc JOIN Reimbursement rm ON rc.transactionId = rm.receivablesTransactionId AND rc.orderNumber = rm.receivablesOrderNumber WHERE rc.transactionId = ? AND rc.orderNumber = ?");
      trans: for (BsTransaction transaction : transactions) {
        for (BsItem item : transaction.getItems()) {
          // Query only if item is debits a receivable account
          if (!transaction.getBalance() && item.getAccount().getReceivable()
              && item.getDebit()) {
            logger.info("Querying receivable item: " + item);
            pstmt.setBigDecimal(1, new BigDecimal(item.getTransactionId()));
            pstmt.setInt(2, item.getOrderNumber());
            rs = pstmt.executeQuery();
            // Expecting exactly one item; ignore item if nothing found, as it's
            // not a receivable item.
            if (rs.next()) {
              // Found the receivable by matching transaction and order number,
              // so success.
              found = true;
              logger.info("Found receivable item in database");
              break trans;
            }
            rs.close();
          }
        }
      }
      pstmt.close();

      assertTrue("No receivable item found", found);

      message = "reimbursement item: ";
      // query and validate reimbursement item
      pstmt =
        connection.prepareStatement("SELECT reimbursedAmount, allocatedAmount FROM Reimbursement");
      rs = pstmt.executeQuery();
      if (rs.next()) {
        // validate the data
        Double reimbursedAmount = rs.getDouble(1);
        Double allocatedAmount = rs.getDouble(2);
        assertTrue("wrong reimbursed amount expecting 100.00: "
                   + reimbursedAmount, reimbursedAmount.equals(100.00D));
        assertTrue("wrong allocated amount expecting 0.00: " + allocatedAmount,
                   allocatedAmount.equals(0.00D));
      } else {
        fail("no reimbursement found in database");
      }
    } catch (SQLException e) {
      fail("SQL Exception validating " + message + e.getMessage());
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
      if (pstmt != null) {
        try {
          pstmt.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
    }

    assertTrue("Fiscal year created but not in database", found);
  }

  /**
   * Validate the list of generated account groups against the current state of
   * the database using a JDBC query.
   * 
   * @param connection the JDBC connection
   * @param groups the list of groups to validate
   */
  private void validateAccountGroups(Connection connection,
                                     List<BsAccountGroup> groups) {
    if (groups == null || groups.size() == 0) {
      fail("No account groups created");
    }

    boolean found = true;
    Statement stmt = null;
    ResultSet rs = null;
    Set<String> queriedGroups = new HashSet<String>();
    try {
      stmt = connection.createStatement();
      rs = stmt.executeQuery("SELECT groupName FROM AccountGroup");
      while (rs.next()) {
        queriedGroups.add(rs.getString("groupName"));
      }
      for (BsAccountGroup group : groups) {
        if (!queriedGroups.contains(group.getGroupName())) {
          found = false;
          break;
        }
      }
    } catch (SQLException e) {
      fail("SQL Exception validating account groups: " + e.getMessage());
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
    }

    assertTrue("Fiscal year created but not in database", found);
  }

  /**
   * Validate the list of generated fiscal years against the current state of
   * the database using a JDBC query.
   * 
   * @param connection the JDBC connection
   * @param years the list of generated fiscal years
   * @throws SQLException when there is a
   */
  private void validateFiscalYears(Connection connection,
                                   List<BsFiscalYear> years) {
    if (years == null || years.size() == 0) {
      fail("No fiscal years created");
    }
    boolean found = true;
    Integer missingYear = null;
    Statement stmt = null;
    ResultSet rs = null;
    Set<Integer> queriedYears = new HashSet<Integer>();
    try {
      stmt = connection.createStatement();
      rs = stmt.executeQuery("SELECT year FROM FiscalYear");
      while (rs.next()) {
        queriedYears.add(rs.getInt("year"));
      }
      for (BsFiscalYear year : years) {
        if (!queriedYears.contains(year.getYear())) {
          found = false;
          missingYear = year.getYear();
          break;
        }
      }
    } catch (SQLException e) {
      fail("SQL Exception validating fiscal years: " + e.getMessage());
    } finally {
      if (rs != null) {
        try {
          rs.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }
    }

    assertTrue("Fiscal year created but not in database: " + missingYear, found);
  }

  /**
   * For each year in a list, create a set of transactions representing a
   * complete range of balance sheet and income statement transactions. For the
   * first year, add a set of balance transactions against the balance sheet
   * accounts. Note that the design of the database does not directly connect
   * the transactions with the fiscal year; that is done by mapping the date to
   * the fiscal year date range. So this method just uses the year start date to
   * date the transactions for each year.
   * 
   * @param transDel the existing transaction delegate
   * @param years the list of years to which to add transactions
   */
  private List<BsTransaction> createTransactions(TransactionDelegate transDel,
                                                 List<BsFiscalYear> years) {
    List<BsTransaction> transactions = new ArrayList<BsTransaction>();
    int yearCounter = 0;
    for (BsFiscalYear year : years) {
      if (yearCounter == 0) {
        createBalanceTransactions(transactions, transDel, year.getStartDate());
      }
      createYearTransactions(transactions, transDel, year.getStartDate());
      yearCounter++;
    }

    return transactions;
  }

  /**
   * Create the set of transactions for a year. The transactions use all of the
   * accounts and comprise a complete set of transactions for the year. Add the
   * transactions to the input list of transactions. Use a client-created
   * transaction delegate to create the transactions and items.
   * 
   * @param transactions list of transactions to contain transactions
   * @param transDel transaction delegate to use to create transactions and
   *          items
   * @param year year containing date to use for transactions
   */
  private void createYearTransactions(List<BsTransaction> transactions,
                                      TransactionDelegate transDel,
                                      Timestamp date) {
    BsItem arItem = createArTransaction(transactions, transDel, date);
    BsItem reimbursingItem =
      createReimbursementTransaction(transactions, transDel, date);
    // TODO process transactions because of dependency ordering bug?
    linkReimbursement(arItem, reimbursingItem, transDel);
    createCreditTransaction(transactions, transDel, date);
    createCapitalTransaction(transactions, transDel, date);
  }

  /**
   * Create a capital transaction against am equity account (credit) and an cash
   * account (debit). Put the transaction into the accumulating array of
   * transactions.
   * 
   * @param transactions the accumulating array of transactions
   * @param transDel the transaction delegate to use to create the transaction
   *          and items
   * @param date the transaction date
   */

  private void createCapitalTransaction(List<BsTransaction> transactions,
                                        TransactionDelegate transDel,
                                        Timestamp date) {
    BsTransaction capitalTrans =
      transDel.createTransaction(null, DESCRIPTION, date, false, false);
    assertTrue("capital transaction not created", capitalTrans != null);

    BsItem equityItem =
      transDel.createItem(capitalTrans,
                          capitalTrans.getTransactionId(),
                          1,
                          200.00D,
                          false,
                          false,
                          equityAccount.getAccountName(),
                          equityAccount.getEntityName());
    assertTrue("equity item not created", equityItem != null);
    equityItem.setTransaction(capitalTrans);
    equityItem.setAccount(equityAccount);
    capitalTrans.addItemsItem(equityItem);
    equityAccount.addItemsItem(equityItem);

    BsItem checkingItem =
      transDel.createItem(capitalTrans,
                          capitalTrans.getTransactionId(),
                          2,
                          200.00D,
                          true,
                          false,
                          checkingAccount.getAccountName(),
                          checkingAccount.getEntityName());
    assertTrue("checking item not created", checkingItem != null);
    checkingItem.setAccount(checkingAccount);
    capitalTrans.addItemsItem(checkingItem);
    checkingAccount.addItemsItem(checkingItem);

    // Validate the transaction and add it to the accumulating list of
    // transactions.
    assertTrue("capital transaction is not valid" + capitalTrans,
               capitalTrans.isValid());
    transactions.add(capitalTrans);
  }

  /**
   * Create a credit transaction against a liability account (credit) and an
   * expense account (debit). Put the transaction into the accumulating array of
   * transactions.
   * 
   * @param transactions the accumulating array of transactions
   * @param transDel the transaction delegate to use to create the transaction
   *          and items
   * @param date the transaction date
   */
  private void createCreditTransaction(List<BsTransaction> transactions,
                                       TransactionDelegate transDel,
                                       Timestamp date) {
    BsTransaction creditTrans =
      transDel.createTransaction(null, DESCRIPTION, date, false, false);
    assertTrue("credit transaction not created", creditTrans != null);

    BsItem creditItem =
      transDel.createItem(creditTrans,
                          creditTrans.getTransactionId(),
                          1,
                          50.00D,
                          false,
                          false,
                          liabilityAccount.getAccountName(),
                          liabilityAccount.getEntityName());
    assertTrue("credit item not created", creditItem != null);
    creditItem.setTransaction(creditTrans);
    creditItem.setAccount(liabilityAccount);
    creditTrans.addItemsItem(creditItem);
    liabilityAccount.addItemsItem(creditItem);

    BsItem checkingItem =
      transDel.createItem(creditTrans,
                          creditTrans.getTransactionId(),
                          2,
                          50.00D,
                          true,
                          false,
                          expenseAccount.getAccountName(),
                          expenseAccount.getEntityName());
    assertTrue("checking item not created", checkingItem != null);
    checkingItem.setAccount(expenseAccount);
    creditTrans.addItemsItem(checkingItem);
    expenseAccount.addItemsItem(checkingItem);

    // Validate the transaction and add it to the list of transactions.
    assertTrue("credit transaction is not valid" + creditTrans,
               creditTrans.isValid());
    transactions.add(creditTrans);
  }

  /**
   * Create a reimbursement link and set up the AR and reimbursing item links to
   * each other and to the link. After calling this method, the transactions
   * that contain the items will be linked in memory but not in the database.
   * 
   * @param arItem the AR item being reimbursed
   * @param reimbursingItem the item reimbursing the AR item
   * @param transDel the transaction delegate to use to create the reimbursement
   *          link object
   */
  private void linkReimbursement(BsItem arItem, BsItem reimbursingItem,
                                 TransactionDelegate transDel) {
    BsReimbursement reimbursement =
      transDel.createReimbursement(arItem,
                                   reimbursingItem,
                                   arItem.getOrderNumber(),
                                   reimbursingItem.getOrderNumber(),
                                   arItem.getTransactionId(),
                                   reimbursingItem.getTransactionId(),
                                   100.00D,
                                   0.00D);
    try {
      arItem.addReimbursement(reimbursement);
      arItem.addReimbursingItem(reimbursingItem);
      reimbursingItem.addReimbursement(reimbursement);
      reimbursingItem.addReceivableItem(arItem);
    } catch (SQLException e) {
      String message = "SQL Exception adding a reimbursement link";
      logger.error(message, e);
      fail(message + ": " + e.getMessage());
    }
  }

  /**
   * Create an reimbursing transaction, a transaction against a receivable
   * account (credit) and a cash account (debit). Return the AR reimbursing item
   * for later use in a reimbursement link. Add the created transaction to the
   * input list.
   * 
   * @param transactions the accumulating list of transactions
   * @param transDel the transaction delegate with which to create the
   *          transaction and items
   * @param date the transaction date
   * @return the AR item from the transaction
   */
  private BsItem createReimbursementTransaction(List<BsTransaction> transactions,
                                                TransactionDelegate transDel,
                                                Timestamp date) {
    BsTransaction reimbursementTransaction =
      transDel.createTransaction(null, DESCRIPTION, date, false, false);
    assertTrue("no reimbursement transaction created",
               reimbursementTransaction != null);

    BsItem reimbursingItem =
      transDel.createItem(reimbursementTransaction,
                          reimbursementTransaction.getTransactionId(),
                          1,
                          100.00D,
                          false,
                          false,
                          arAccount.getAccountName(),
                          arAccount.getEntityName());
    assertTrue("no reimbursement AR item created", reimbursingItem != null);
    reimbursingItem.setAccount(arAccount);
    reimbursementTransaction.addItemsItem(reimbursingItem);
    arAccount.addItemsItem(reimbursingItem);

    BsItem checkingItem =
      transDel.createItem(reimbursementTransaction,
                          reimbursementTransaction.getTransactionId(),
                          2,
                          100.00D,
                          true,
                          false,
                          checkingAccount.getAccountName(),
                          checkingAccount.getEntityName());
    assertTrue("no reimbursement checking item created", checkingItem != null);
    checkingItem.setAccount(checkingAccount);
    reimbursementTransaction.addItemsItem(checkingItem);
    checkingAccount.addItemsItem(checkingItem);

    // Validate the transaction and add it to the array of transactions.
    assertTrue("reimbursement transaction is not valid"
               + reimbursementTransaction, reimbursementTransaction.isValid());
    transactions.add(reimbursementTransaction);

    return reimbursingItem;
  }

  /**
   * Create an accounts-receivable transaction, a transaction against a
   * receivable account (debit) and an income account (credit). Return the AR
   * item for later use in a reimbursement Link. Add the created transaction to
   * the input list.
   * 
   * @param transactions the accumulating list of transactions
   * @param transDel the transaction delegate with which to create the
   *          transaction and items
   * @param date the transaction date
   * @return the AR item from the transaction
   */
  private BsItem createArTransaction(List<BsTransaction> transactions,
                                     TransactionDelegate transDel,
                                     Timestamp date) {
    // AR transaction--income and ar accounts
    BsTransaction arTrans =
      transDel.createTransaction(null, DESCRIPTION, date, false, false);
    assertTrue("no receivable transaction created", arTrans != null);
    BsItem arItem =
      transDel.createItem(arTrans,
                          arTrans.getTransactionId(),
                          1,
                          100.00D,
                          true,
                          false,
                          arAccount.getAccountName(),
                          arAccount.getEntityName());
    assertTrue("no receivable item created", arItem != null);
    arItem.setAccount(arAccount);
    arTrans.addItemsItem(arItem);
    arAccount.addItemsItem(arItem);

    BsItem incomeItem =
      transDel.createItem(arTrans,
                          arTrans.getTransactionId(),
                          2,
                          100.00D,
                          false,
                          false,
                          incomeAccount.getAccountName(),
                          incomeAccount.getEntityName());
    incomeItem.setAccount(incomeAccount);
    arTrans.addItemsItem(incomeItem);
    incomeAccount.addItemsItem(incomeItem);

    // Validate the AR transaction and add it to the list of transactions.
    assertTrue("AR transaction is not valid" + arTrans, arTrans.isValid());
    transactions.add(arTrans);
    return arItem;
  }

  /**
   * Create the single-item balance transactions for a year against the
   * checking, ar, liability, and equity accounts. Add the transactions to the
   * list of transactions. Use the client-created transaction delegate to create
   * the transactions and items.
   * 
   * @param transactions list of transactions to contain balance transactions
   * @param transDel transaction delegate to use to create transactions and
   *          items
   * @param date the transaction date for all transactions
   */
  private void createBalanceTransactions(List<BsTransaction> transactions,
                                         TransactionDelegate transDel,
                                         Timestamp date) {
    // First year, add balance transactions--checking, ar, liability, equity
    // Equity = Assets - Liabilities
    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             100.00D,
                             true,
                             checkingAccount);
    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             0.00D,
                             true,
                             arAccount);
    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             20.00D,
                             false,
                             liabilityAccount);
    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             80.00D,
                             false,
                             equityAccount);
  }

  /**
   * Create a balance transaction. Set up the item correctly and validate the
   * transaction, then add it to the accumulating list of transactions.
   * 
   * @param transactions the accumulating list of transactions.
   * @param transDel the transaction delegate to use to create the transaction
   *          and item
   * @param date the transaction date
   * @param amount the balance amount
   * @param debit whether the balance is a debit (true) or credit (false)
   * @param account the account object
   */
  private void createBalanceTransaction(List<BsTransaction> transactions,
                                        TransactionDelegate transDel,
                                        Timestamp date, Double amount,
                                        Boolean debit, BsAccount account) {
    String name = account.getAccountName();
    BsTransaction transaction =
      transDel.createTransaction(null, name + " balance", date, false, true);
    assertTrue(name + " balance not created", transaction != null);

    BsItem item =
      transDel.createItem(transaction,
                          transaction.getTransactionId(),
                          1,
                          amount,
                          debit,
                          false,
                          account.getAccountName(),
                          account.getEntityName());
    assertTrue(name + " balance item not created", item != null);
    item.setAccount(account);
    transaction.addItemsItem(item);
    account.addItemsItem(item);

    // Validate transaction and add to accumulating array of transactions
    assertTrue(name + " balance transaction not valid", transaction.isValid());
    transactions.add(transaction);
  }

  /**
   * Create a set of links from an account to a set of fiscal years. The method
   * sets the link arrays to associate the links with the fiscal year and
   * account objects. It does not add the account to the year or the year to the
   * account; this results in an infinite loop.
   * 
   * @param delegate the fiscal year delegate that creates the links
   * @param account the linking account
   * @param orderNumber the order number of the account in the list of accounts
   *          associated with the fiscal year (the same for all years)
   * @param years the linked years
   */
  private void createFiscalYearAccountLinks(FiscalYearDelegate delegate,
                                            BsAccount account,
                                            Integer orderNumber,
                                            List<BsFiscalYear> years) {
    for (BsFiscalYear year : years) {
      BsFiscalYearAccount link =
        delegate.createFiscalYearAccount(account,
                                         year,
                                         account.getAccountName(),
                                         account.getEntityName(),
                                         year.getYear(),
                                         orderNumber);

      // Set the link variables.
      try {
        year.addFiscalYearAccountFiscalYearAccount(link);
        account.addFiscalYearAccountFiscalYearAccount(link);
      } catch (SQLException e) {
        String message = "SQL exception creating year-account links";
        logger.error(message, e);
        fail(message + ": " + e.getMessage());
      }
    }
  }

  /**
   * Create an account object.
   * 
   * @param enDel the entity delegate to use to create the account
   * @param entity the entity object that owns the account
   * @param name the account name
   * @param type the account type (Asset, Liability, Equity, Income, Expense)
   * @param receivable whether the account is a receivable account
   * @param group the account group for the account
   * @param years the list of fiscal years to which to link the account
   * @return the account object
   */
  private BsAccount createAccount(EntityDelegate enDel, BsEntity entity,
                                  String name, String type, Boolean receivable,
                                  BsAccountGroup group, List<BsFiscalYear> years) {
    BsAccount account =
      enDel.createAccount(entity,
                          entity.getEntityName(),
                          name,
                          "description",
                          false,
                          type,
                          receivable,
                          true,
                          group.getGroupName());
    account.setGroup(group);
    account.setFiscalYear(years);
    entity.addAccountsAccount(account);
    createFiscalYearAccountLinks(delegate, account, 1, years);
    return account;
  }
}