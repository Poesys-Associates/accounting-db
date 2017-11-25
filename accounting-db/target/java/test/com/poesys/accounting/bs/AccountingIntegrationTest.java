/**
 * Copyright 2017 Poesys Associates. All rights reserved.
 */

package com.poesys.accounting.bs;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.poesys.accounting.bs.account.AccountDelegateFactory;
import com.poesys.accounting.bs.account.AccountTypeDelegate;
import com.poesys.accounting.bs.account.BsAccount;
import com.poesys.accounting.bs.account.BsAccountGroup;
import com.poesys.accounting.bs.account.BsAccountType;
import com.poesys.accounting.bs.account.BsCapitalAccount;
import com.poesys.accounting.bs.account.BsCapitalEntity;
import com.poesys.accounting.bs.account.BsDistributionAccount;
import com.poesys.accounting.bs.account.BsEntity;
import com.poesys.accounting.bs.account.BsFiscalYear;
import com.poesys.accounting.bs.account.BsFiscalYearAccount;
import com.poesys.accounting.bs.account.BsSimpleAccount;
import com.poesys.accounting.bs.account.CapitalAccountDelegate;
import com.poesys.accounting.bs.account.CapitalEntityDelegate;
import com.poesys.accounting.bs.account.DistributionAccountDelegate;
import com.poesys.accounting.bs.account.EntityDelegate;
import com.poesys.accounting.bs.account.FiscalYearDelegate;
import com.poesys.accounting.bs.account.SimpleAccountDelegate;
import com.poesys.accounting.bs.transaction.BsItem;
import com.poesys.accounting.bs.transaction.BsReimbursement;
import com.poesys.accounting.bs.transaction.BsTransaction;
import com.poesys.accounting.bs.transaction.TransactionDelegate;
import com.poesys.accounting.bs.transaction.TransactionDelegateFactory;
import com.poesys.accounting.db.account.CapitalAccount;
import com.poesys.accounting.db.account.DistributionAccount;
import com.poesys.accounting.db.account.IAccount;
import com.poesys.accounting.db.account.ICapitalAccount;
import com.poesys.accounting.db.account.IDistributionAccount;
import com.poesys.accounting.db.account.ISimpleAccount;
import com.poesys.accounting.db.account.SimpleAccount;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.cartridges.db.utilities.StringUtilities;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.connection.JdbcConnectionManager;
import com.poesys.db.pk.NaturalPrimaryKey;


/**
 * <p>
 * A test class for a full integration system test of the accounting data access
 * library Accounting/DB; this is a custom file, not a generated one.
 * </p>
 * <p>
 * This class contains a full integration all the Account and Transaction
 * subsystem classes that comprise a complete accounting system database.
 * Running this test ensures that the database and its data access library work
 * as a whole. This is not a full system test in that it does not fully test all
 * the scenarios possible in constructing a database, just the obvious one. It
 * does test scenarios involving multiple fiscal years.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountingIntegrationTest {
  private static final Logger logger =
    Logger.getLogger(AccountingIntegrationTest.class);

  // Asset type strings corresponding to database enum entries; used for
  // lookup of accounts of a specific account type
  private static final String EXPENSES = "Expenses";
  private static final String INCOME = "Income";
  private static final String EQUITY = "Equity";
  private static final String LIABILITIES = "Liabilities";
  private static final String ASSETS = "Assets";

  // special account names
  private static final String AR_ACCOUNT_NAME = "Accounts Receivable";
  private static final String CAP_ACCOUNT_NAME = "Capital for ";
  private static final String DIST_ACCOUNT_NAME = "Distribution for ";

  // Overridden to replace date generation with more useful values using Java
  // 1.8 date/time classes.

  private List<BsFiscalYear> createFiscalYears(int count)
      throws DelegateException, InvalidParametersException {

    FiscalYearDelegate delegate =
      AccountDelegateFactory.getFiscalYearDelegate();
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

  /**
   * <p>
   * Create a specified number of groups for each account type. For each fiscal
   * year, create one account for each account group and link the account to the
   * fiscal year and account group. Create three special accounts:
   * </p>
   * <ul>
   * <li>a receivable account in an Assets group
   * <li>a capital account in an Equity group
   * <li>a distribution account in an Equity group
   * </ul>
   * <p>
   * The three accounts have a fixed name ("Accounts Receivable",
   * "Capital for <entity name>", and "Distributions for <entity name>". They
   * are shared over the fiscal years. All other accounts are unique to a given
   * fiscal year. Note that count must be at least 1. If you want both a capital
   * account and a distribution account, count must be at least 2.
   * </p>
   * 
   * @param entity the entity that owns the accounts
   * @param years the list of fiscal years
   * @return a collection of accounts
   */
  private Set<BsAccount> createAccountsGroupsAndLinks(BsEntity entity,
                                                      List<BsFiscalYear> years) {
    AccountTypeDelegate delegate =
      AccountDelegateFactory.getAccountTypeDelegate();
    Set<BsAccount> accounts = new HashSet<BsAccount>();

    // Track the first year.
    BsFiscalYear firstYear = years.get(0);

    // Query all the account types.
    List<BsAccountType> types = delegate.getAllObjects(5);

    // Build a collection of groups for all the account types.
    Collection<BsAccountGroup> groups = new ArrayList<BsAccountGroup>();
    for (BsAccountType type : types) {
      groups.addAll(createAccountTypeGroups(2, type));
    }

    // Track creation of receivable account across all fiscal years.
    boolean receivableCreated = false;
    boolean receivableCreatedThisYear = false;

    // Create two capital entities.
    List<BsCapitalEntity> capitalEntities = createCapitalEntities(entity, 2);
    assertTrue(capitalEntities != null && capitalEntities.size() == 2);

    // Build a collection of accounts: for each fiscal year, create an account
    // for each group in the collection of groups just created. Create special
    // accounts as appropriate to the account type and status of special account
    // creation. Create a link between the fiscal year and the account, setting
    // the group.

    SimpleAccountDelegate saDel =
      AccountDelegateFactory.getSimpleAccountDelegate();

    BsAccountGroup equityGroup = null;

    for (BsFiscalYear currentYear : years) {
      for (BsAccountGroup group : groups) {
        BsAccount account = null;
        String accountName = StringUtilities.generateString(50);
        String description = StringUtilities.generateString(100);

        // Create the account and link depending on account type and status.
        switch (group.getAccountType()) {
        case (ASSETS):
          if (!receivableCreatedThisYear) {
            account =
              createReceivableAccount(entity,
                                      firstYear,
                                      receivableCreated,
                                      currentYear,
                                      group,
                                      description);
            receivableCreated = true;
            receivableCreatedThisYear = true;
          } else {
            // Receivable account and link already created, create simple
            // account for this group.
            account =
              createSimpleAccount(entity,
                                  saDel,
                                  currentYear,
                                  group,
                                  1,
                                  accountName,
                                  description,
                                  2);
          }
          break;
        case (EQUITY):
          // Set the equity group for later use.
          equityGroup = group;
          break;
        default:
          account =
            createSimpleAccount(entity,
                                saDel,
                                currentYear,
                                group,
                                1,
                                accountName,
                                description,
                                1);
        }

        accounts.add(account);
      }

      // Create the required equity accounts for the current year.
      accounts.addAll(createEquityAccounts(entity,
                                           firstYear,
                                           currentYear,
                                           equityGroup,
                                           capitalEntities.get(0),
                                           capitalEntities.get(1)));
      // Set the accounts into the year.
      List<BsAccount> accountList = new ArrayList<BsAccount>(accounts);
      currentYear.setAccount(accountList);
    }

    return accounts;
  }

  /**
   * Create a simple account and link it to a year and group, then return it as
   * an Account object.
   * 
   * @param entity the entity that owns the account
   * @param saDel the simple account delegate to use
   * @param currentYear the current fiscal year
   * @param group the group to associate with the account
   * @param groupOrderNumber the rank order of the group in the type for the
   *          fiscal year
   * @param accountName the name of the account
   * @param description the account description
   * @param accountOrderNumber the rank order of the account in the group for
   *          the fiscal year
   * @return the BsAccount object
   */
  private BsAccount createSimpleAccount(BsEntity entity,
                                        SimpleAccountDelegate saDel,
                                        BsFiscalYear currentYear,
                                        BsAccountGroup group,
                                        Integer groupOrderNumber,
                                        String accountName, String description,
                                        Integer accountOrderNumber) {
    BsSimpleAccount sAccount =
      saDel.createSimpleAccount(accountName,
                                entity.getEntityName(),
                                description,
                                true,
                                true,
                                false);
    BsAccount account = new BsAccount(sAccount.toDto());
    sAccount.setEntity(entity);
    entity.addAccountsAccount(account);
    linkAccountToYearAndGroup(account,
                              currentYear,
                              group,
                              groupOrderNumber,
                              accountOrderNumber);
    return account;
  }

  /**
   * Create and/or link equity accounts.
   * 
   * @param entity the entity that owns the accounts
   * @param firstYear the first year of the range
   * @param currentYear the current year of the range
   * @param group an Equity account type group; this group contains all the
   *          capital and distribution accounts
   * @param capEntity1 the first capital entity
   * @param capEntity2 the second capital entity
   * @return the set of all created accounts
   */
  private Set<BsAccount> createEquityAccounts(BsEntity entity,
                                              BsFiscalYear firstYear,
                                              BsFiscalYear currentYear,
                                              BsAccountGroup group,
                                              BsCapitalEntity capEntity1,
                                              BsCapitalEntity capEntity2) {
    Set<BsAccount> accounts = new HashSet<BsAccount>();

    BsAccount cap1 = null;
    BsAccount cap2 = null;
    BsAccount dist1 = null;
    BsAccount dist2 = null;

    CapitalAccountDelegate caDel =
      AccountDelegateFactory.getCapitalAccountDelegate();
    DistributionAccountDelegate daDel =
      AccountDelegateFactory.getDistributionAccountDelegate();

    // For the first year, create the relevant equity accounts.
    if (firstYear.equals(currentYear)) {
      // Create and link the capital accounts.
      cap1 = createCapitalAccount(caDel, entity, 0.50D, capEntity1);
      cap2 = createCapitalAccount(caDel, entity, 0.50D, capEntity2);
      // Create and link the distribution accounts.
      dist1 = createDistributionAccount(daDel, entity, capEntity1);
      dist2 = createDistributionAccount(daDel, entity, capEntity2);
    } else {
      // For later years, get the four shared accounts from the entities.
      for (BsCapitalAccount capAccount : capEntity1.getCapitalAccount()) {
        BsAccount account = new BsAccount(capAccount.toDto());
        cap1 = account;
      }
      for (BsCapitalAccount capAccount : capEntity2.getCapitalAccount()) {
        BsAccount account = new BsAccount(capAccount.toDto());
        cap2 = account;
      }
      for (BsDistributionAccount distAccount : capEntity1.getDistributionAccount()) {
        BsAccount account = new BsAccount(distAccount.toDto());
        dist1 = account;
      }
      for (BsDistributionAccount distAccount : capEntity2.getDistributionAccount()) {
        BsAccount account = new BsAccount(distAccount.toDto());
        dist2 = account;
      }
    }

    // Link the capital and distribution accounts.

    logger.info("Linking entity accounts for year " + currentYear.getYear());

    linkAccountToYearAndGroup(cap1, currentYear, group, 1, 1);
    linkAccountToYearAndGroup(cap2, currentYear, group, 1, 2);
    linkAccountToYearAndGroup(dist1, currentYear, group, 1, 3);
    linkAccountToYearAndGroup(dist2, currentYear, group, 1, 4);

    logger.info("Finished linking entity accounts for year "
                + currentYear.getYear());

    // Add the accounts to the account list for return.
    accounts.add(cap1);
    accounts.add(cap2);
    accounts.add(dist1);
    accounts.add(dist2);

    return accounts;
  }

  /**
   * Link an account to a year and group, setting the order number of the
   * account within the year.
   * 
   * @param account the account to link
   * @param year the year to link to the account
   * @param group the group to associate with the link
   * @param orderNumber the order of the account within the year
   */
  private void linkAccountToYearAndGroup(BsAccount account, BsFiscalYear year,
                                         BsAccountGroup group,
                                         Integer groupOrderNumber,
                                         Integer accountOrderNumber) {
    EntityDelegate delegate = AccountDelegateFactory.getEntityDelegate();
    BsFiscalYearAccount link =
      delegate.createFiscalYearAccount(account,
                                       group,
                                       year,
                                       account.getAccountName(),
                                       account.getEntityName(),
                                       year.getYear(),
                                       groupOrderNumber,
                                       accountOrderNumber,
                                       group.getAccountType(),
                                       group.getGroupName(),
                                       group.toDto());
    try {
      account.addFiscalYearAccountFiscalYearAccount(link);
      year.addFiscalYearAccountFiscalYearAccount(link);
      group.addAccountsFiscalYearAccount(link);
    } catch (SQLException e) {
      String message =
        "SQL exception linking account to year and group: "
            + account.getPrimaryKey().getStringKey() + ", " + year.getYear()
            + ", " + group.getPrimaryKey().getStringKey();
      logger.error(message, e);
      fail(message);
    }
  }

  /**
   * Create a receivable account based on the year and creation status. This is
   * a special account created in the first year, then shared across all the
   * fiscal years. It has a fixed name rather than a randomly generated one.
   * 
   * @param entity the entity that owns the account
   * @param firstYear the first year of the fiscal year range
   * @param receivableCreated
   * @param currentYear
   * @param group
   * @param description
   * @return the new or shared receivable account
   */
  private BsAccount createReceivableAccount(BsEntity entity,
                                            BsFiscalYear firstYear,
                                            boolean receivableCreated,
                                            BsFiscalYear currentYear,
                                            BsAccountGroup group,
                                            String description) {
    BsAccount account = null;
    SimpleAccountDelegate saDel =
      AccountDelegateFactory.getSimpleAccountDelegate();

    if (!receivableCreated && firstYear.equals(currentYear)) {
      // This is the first of the fiscal years, and there is no receivable
      // account yet, so create it with an explicit name. For other years,
      // the account in the first year will get linked to the current
      // year.
      BsSimpleAccount sAccount =
        saDel.createSimpleAccount(AR_ACCOUNT_NAME,
                                  entity.getEntityName(),
                                  description,
                                  true,
                                  true,
                                  true);
      sAccount.setEntity(entity);
      account = new BsAccount(sAccount.toDto());
      entity.addAccountsAccount(account);
    } else if (receivableCreated) {
      // Look up the account already created in the first year.
      account = getSharedAccount(firstYear, AR_ACCOUNT_NAME);
      assertTrue("account " + AR_ACCOUNT_NAME + " not found in year "
                 + currentYear.getYear(), account != null);
    } else {
      // Problem: not the first year, but receivable account not created
      fail("No receivable account created in first year");
    }

    linkAccountToYearAndGroup(account, currentYear, group, 1, 1);

    return account;
  }

  /**
   * Find an account by name in the list of accounts in the year
   * 
   * @param year the fiscal year containing the accounts
   * @param accountName the name of the account to find
   * @return the account business object with the specified name, or null if
   *         there is no such account
   */
  private BsAccount getSharedAccount(BsFiscalYear year, String accountName) {
    BsAccount foundAccount = null;

    try {
      for (BsAccount account : year.getAccounts()) {
        if (account.getAccountName().equals(accountName)) {
          foundAccount = account;
          break;
        }
      }
    } catch (SQLException e) {
      String msg = "SQL exception finding account";
      logger.error(msg, e);
      fail(msg + ": " + e.getMessage());
    }

    return foundAccount;
  }

  /**
   * Create a collection of account groups containing a specified number of
   * groups for a specified account type.
   * 
   * @param count the number of groups to create
   * @param type the account type
   * @return the collection of account groups
   */
  private Collection<BsAccountGroup> createAccountTypeGroups(int count,
                                                             BsAccountType type) {
    Collection<BsAccountGroup> groups = new ArrayList<BsAccountGroup>(count);
    // Get the Assets account type object.
    AccountTypeDelegate atDel = AccountDelegateFactory.getAccountTypeDelegate();

    for (int i = 0; i < count; i++) {
      String groupName = StringUtilities.generateString(100);
      BsAccountGroup group =
        atDel.createAccountGroup(type, type.getAccountType(), groupName);
      groups.add(group);
      type.addGroupsAccountGroup(group);
    }

    return groups;
  }

  /**
   * Create a list of capital entities and return the list.
   * 
   * @param entity the accounting entity for the accounts
   * @param capitalCount the number of capital entities to create
   * @return the list of capital entities
   */
  private List<BsCapitalEntity> createCapitalEntities(BsEntity entity, int count) {
    List<BsCapitalEntity> entities = new ArrayList<BsCapitalEntity>();
    CapitalEntityDelegate delegate =
      AccountDelegateFactory.getCapitalEntityDelegate();

    for (int i = 0; i < count; i++) {
      BsCapitalEntity capitalEntity =
        delegate.createCapitalEntity(StringUtilities.generateString(100));
      entities.add(capitalEntity);
    }

    return entities;
  }

  /**
   * Create a new distribution account for an entity and capital entity.
   * 
   * @param delegate the business delegate to use to create the account
   * @param entity the entity that owns the account
   * @param capitalEntity the capital entity associated with the account
   * @return the account
   */
  private BsAccount createDistributionAccount(DistributionAccountDelegate delegate,
                                              BsEntity entity,
                                              BsCapitalEntity capitalEntity) {
    String accountName =
      DIST_ACCOUNT_NAME + capitalEntity.getCapitalEntityName();
    String description = StringUtilities.generateString(50);
    BsDistributionAccount distAccount =
      delegate.createDistributionAccount(accountName,
                                         entity.getEntityName(),
                                         description,
                                         false,
                                         true,
                                         capitalEntity.getCapitalEntityName());
    distAccount.setEntity(entity);
    distAccount.setCapitalEntity(capitalEntity);
    capitalEntity.addDistributionAccountDistributionAccount(distAccount);
    BsAccount account = new BsAccount(distAccount.toDto());
    entity.addAccountsAccount(account);
    return account;
  }

  /**
   * Create a new capital account for an entity and capital entity, setting the
   * ownership percentage as specified.
   * 
   * @param delegate the business delegate to use to create the account
   * @param entity the entity that owns the account
   * @param ownership
   * @param capitalEntity the capital entity associated with the account
   * @return the account
   */
  private BsAccount createCapitalAccount(CapitalAccountDelegate delegate,
                                         BsEntity entity, Double ownership,
                                         BsCapitalEntity capitalEntity) {
    String accountName =
      CAP_ACCOUNT_NAME + capitalEntity.getCapitalEntityName();
    String description = StringUtilities.generateString(50);
    BsCapitalAccount capitalAccount =
      delegate.createCapitalAccount(accountName,
                                    entity.getEntityName(),
                                    description,
                                    false,
                                    true,
                                    ownership,
                                    capitalEntity.getCapitalEntityName());
    capitalAccount.setEntity(entity);
    capitalAccount.setCapitalEntity(capitalEntity);
    capitalEntity.addCapitalAccountCapitalAccount(capitalAccount);
    BsAccount account = new BsAccount(capitalAccount.toDto());
    entity.addAccountsAccount(account);
    return account;
  }

  /**
   * Test method for process(). This overridden version of the test constructs a
   * complete accounting system (entity, years, groups, accounts, transactions,
   * items, receivables) and processes the whole tree into the database, then
   * tests to make sure everything made it. This provides a full-scale
   * integration test of the kind of processing that the data loader will do to
   * create the initial database. Note that the process does not happen in a
   * single transaction as there are limitations in Poesys/DB in processing the
   * dependency structure, so the methods store and commit chunks of the
   * database rather than processing all the objects in a single delegate call
   * (transaction).
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *           creation of an object
   * 
   * @throws DelegateException when a problem occurs during object creation
   * @throws InvalidParametersException when some parameter has an invalid value
   */
  @Test
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    Connection connection = null;

    deleteDatabase();

    // Set up the various delegates required.
    FiscalYearDelegate fyDel = AccountDelegateFactory.getFiscalYearDelegate();
    EntityDelegate enDel = AccountDelegateFactory.getEntityDelegate();
    TransactionDelegate transDel =
      TransactionDelegateFactory.getTransactionDelegate();

    // Create and store the fiscal years, account groups, and accounts.

    logger.info("Running full integration test of accounting system creation");

    List<BsFiscalYear> years = null;
    // set of unique account business objects for all years
    Collection<BsAccount> accounts = null;
    List<BsTransaction> transactions = null;
    BsEntity entity = null;

    try {
      // Create the fiscal year business objects and a list of the DTOs for
      // use in methods below.
      years = createFiscalYears(3);
      // Store the years.
      try {
        fyDel.process(years);
      } catch (DelegateException e) {
        logger.error("Error processing fiscal years", e);
        fail("Error processing fiscal years");
      }

      // Create the accounting entity.
      entity = enDel.createEntity(StringUtilities.generateString(100));

      accounts = createAccountsGroupsAndLinks(entity, years);

      // Store the entity and accounts.
      try {
        enDel.process(entity);
      } catch (DelegateException e) {
        logger.error("Error processing entity", e);
        fail("Error processing entity");
      }

      // Query the years and accounts to refresh the local copy.
      years = fyDel.getAllObjects(3);
      assertTrue("No years from cache", years != null && years.size() > 0);
      entity = enDel.getObject((NaturalPrimaryKey)entity.getPrimaryKey());
      assertTrue("No entity from cache", entity != null);
      accounts = entity.getAccounts();
      assertTrue("No accounts from entity", accounts != null
                                            && accounts.size() > 0);

      // Create transactions for the years using the accounts through the
      // initialized fields, then store transactions, items, accounts, and
      // groups.
      transactions = createTransactions(transDel, years);
      try {
        transDel.process(transactions);
      } catch (DelegateException e) {
        logger.error("Error processing transactions", e);
        fail("Error processing transactions");
      }

      // Validate the objects against the database.
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());

      validateAccountTypes(connection, accounts);
      validateFiscalYears(connection, years);
      validateAccounts(connection, accounts);
      validateTransactions(connection, transactions);

      // Commit the JDBC connection to free resources as required.
      connection.commit();
    } catch (AssertionError e) {
      throw e;
    } catch (Throwable e) {
      logger.error("Full integration test failed", e);
      fail("Full integration test failed: " + e.getMessage());
      if (connection != null) {
        try {
          connection.rollback();
        } catch (SQLException e1) {
          // ignore exception
          logger.error("Exception rolling back transaction");
        }
      }
    } finally {
      // Close the JDBC connection to free resources.
      if (connection != null) {
        try {
          connection.close();
        } catch (SQLException e) {
          // ignore exception
        }
      }

      deleteDatabase();
    }
  }

  /**
   * Delete all the stored data. Transactions get deleted first due to the
   * dependency structure, then groups, then entities and accounts, then fiscal
   * years. Each conditional marks the objects at the top of a composite or
   * inheritance hierarchy and relies on cascaded deletes to delete the
   * children. It then uses a delegate to process the deletions.
   */
  private void deleteDatabase() {
    FiscalYearDelegate fsYDel = AccountDelegateFactory.getFiscalYearDelegate();
    TransactionDelegate transDel =
      TransactionDelegateFactory.getJndiTransactionDelegate();
    EntityDelegate enDel = AccountDelegateFactory.getEntityDelegate();

    List<BsFiscalYear> years = fsYDel.getAllObjects(3);
    List<BsTransaction> transactions = transDel.getAllObjects(20);
    List<BsEntity> entities = enDel.getAllObjects(1);

    if (years != null) {
      if (transactions != null) {
        for (BsTransaction transaction : transactions) {
          transaction.delete();
        }
        logger.info("Processing transaction deletes");
        transDel.process(transactions);
      }

      // Mark the account groups for deletion before deleting entities.
      AccountTypeDelegate acTDel =
        AccountDelegateFactory.getAccountTypeDelegate();
      List<BsAccountType> types = acTDel.getAllObjects(5);
      if (types != null) {
        for (BsAccountType type : types) {
          for (BsAccountGroup group : type.getGroups()) {
            group.delete();
          }
        }
        logger.info("Processing account type deletes");
        acTDel.process(types);
      }

      // Delete the entities; delete cascades to accounts and links.
      for (BsEntity entity : entities) {
        // Requery the entity to ensure it's up-to-date
        entity = enDel.getObject((NaturalPrimaryKey)entity.getPrimaryKey());
        if (entity != null) {
          entity.delete();
        }
      }
      logger.info("Processing entity deletes");
      enDel.process(entities);

      // Delete the capital entities.
      CapitalEntityDelegate cpEDel =
        AccountDelegateFactory.getCapitalEntityDelegate();
      List<BsCapitalEntity> capEntities = cpEDel.getAllObjects(2);
      if (entities != null) {
        for (BsCapitalEntity capEntity : capEntities) {
          capEntity.delete();
        }
        cpEDel.process(capEntities);
      }

      // Mark fiscal years deleted, then process.
      if (years != null) {
        if (years != null) {
          for (BsFiscalYear object : years) {
            object.delete();
          }
          logger.info("Processing fiscal year deletes");
          fsYDel.process(years);
        }
      }
    }
  }

  /**
   * Validate the accounts. Validates the concrete accounts by querying and
   * comparing to the DTO data.
   * 
   * @param connection the JDBC connection to the database
   * @param accounts the collection of accounts to validate
   */
  private void validateAccounts(Connection connection,
                                Collection<BsAccount> accounts) {
    // Test for accounts to validate.
    if (accounts == null || accounts.size() == 0) {
      fail("No accounts created");
    }

    for (BsAccount account : accounts) {
      IAccount accountDto = account.toDto();
      if (accountDto instanceof SimpleAccount) {
        validateSimpleAccount(connection,
                              new BsSimpleAccount((ISimpleAccount)accountDto));
      } else if (accountDto instanceof CapitalAccount) {
        validateCapitalAccount(connection,
                               new BsCapitalAccount((ICapitalAccount)accountDto));
      } else if (accountDto instanceof DistributionAccount) {
        validateDistributionAccount(connection,
                                    new BsDistributionAccount((IDistributionAccount)accountDto));
      }
    }
  }

  /**
   * Validate a simple account by querying and comparing to the DTO data.
   * 
   * @param connection the JDBC connection to the database
   * @param account the simple account to validate
   */
  private void validateSimpleAccount(Connection connection,
                                     BsSimpleAccount account) {
    if (account == null) {
      fail("No simple account to validate");
    }
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt =
        connection.prepareStatement("SELECT receivable FROM SimpleAccount sa JOIN Account a ON sa.entityName = a.entityName AND sa.accountName = a.accountName WHERE sa.entityName = ? and sa.accountName = ?");
      stmt.setString(1, account.getEntityName());
      stmt.setString(2, account.getAccountName());
      rs = stmt.executeQuery();
      assertTrue("simple account not found: "
                 + account.getPrimaryKey().getStringKey(), rs.next());
      Boolean receivable = rs.getBoolean("receivable");
      assertTrue("simple account receivable flag not set correctly: "
                     + receivable + " should be " + account.getReceivable(),
                 account.getReceivable().equals(receivable));
    } catch (SQLException e) {
      String msg = "SQL error querying simple account";
      logger.error(msg, e);
      fail(msg);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // log and ignore
          logger.error("SQL Error closing statement for simple account", e);
        }
      }
    }
  }

  /**
   * Validate a capital account by querying and comparing to the DTO data.
   * 
   * @param connection the JDBC connection to the database
   * @param account the capital account to validate
   */
  private void validateCapitalAccount(Connection connection,
                                      BsCapitalAccount account) {
    if (account == null) {
      fail("No capital account to validate");
    }
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt =
        connection.prepareStatement("SELECT receivable FROM CapitalAccount ca JOIN Account a ON ca.entityName = a.entityName AND ca.accountName = a.accountName WHERE ca.entityName = ? and ca.accountName = ?");
      stmt.setString(1, account.getEntityName());
      stmt.setString(2, account.getAccountName());
      rs = stmt.executeQuery();
      assertTrue("capital account not found: "
                 + account.getPrimaryKey().getStringKey(), rs.next());
      Double ownership = rs.getDouble("ownership");
      assertTrue("capital account ownership not set correctly: " + ownership
                     + " should be " + account.getOwnership(),
                 account.getOwnership().equals(ownership));
    } catch (SQLException e) {
      String msg = "SQL error querying capital account";
      logger.error(msg, e);
      fail(msg);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // log and ignore
          logger.error("SQL Error closing statement for capital account", e);
        }
      }
    }

    // If the account validation succeeds, validate the capital entity.
    BsCapitalEntity entity = account.getCapitalEntity();
    validateCapitalEntity(connection, entity);
  }

  /**
   * Validate a capital entity against the data in a DTO.
   * 
   * @param connection the JDBC connection to use
   * @param entity the capital entity DTO to validate
   */
  private void validateCapitalEntity(Connection connection,
                                     BsCapitalEntity entity) {
    if (entity == null) {
      fail("No capital entity to validate");
    }
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt =
        connection.prepareStatement("SELECT 1 FROM CapitalEntity e WHERE e.capitalEntityName = ?");
      stmt.setString(1, entity.getCapitalEntityName());
      rs = stmt.executeQuery();
      assertTrue("capital entity not found: "
                 + entity.getPrimaryKey().getStringKey(), rs.next());
    } catch (SQLException e) {
      String msg = "SQL error querying capital entity";
      logger.error(msg, e);
      fail(msg);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // log and ignore
          logger.error("SQL Error closing statement for capital entity", e);
        }
      }
    }
  }

  /**
   * Validate a distribution account by querying and comparing to the DTO data.
   * 
   * @param connection the JDBC connection to the database
   * @param account the distribution account to validate
   */
  private void validateDistributionAccount(Connection connection,
                                           BsDistributionAccount account) {
    if (account == null) {
      fail("No distribution account to validate");
    }
    PreparedStatement stmt = null;
    ResultSet rs = null;
    try {
      stmt =
        connection.prepareStatement("SELECT receivable FROM CapitalAccount ca JOIN Account a ON ca.entityName = a.entityName AND ca.accountName = a.accountName WHERE ca.entityName = ? and ca.accountName = ?");
      stmt.setString(1, account.getEntityName());
      stmt.setString(2, account.getAccountName());
      rs = stmt.executeQuery();
      assertTrue("distribution account not found: "
                 + account.getPrimaryKey().getStringKey(), rs.next());
    } catch (SQLException e) {
      String msg = "SQL error querying distribution account";
      logger.error(msg, e);
      fail(msg);
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          // log and ignore
          logger.error("SQL Error closing statement for distribution account",
                       e);
        }
      }
    }

    // If the account validation succeeds, validate the capital entity.
    BsCapitalEntity entity = account.getCapitalEntity();
    validateCapitalEntity(connection, entity);
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
          // Query only if item debits a receivable account
          if (item.getAccount().toDto() instanceof ISimpleAccount) {
            BsSimpleAccount account =
              new BsSimpleAccount((ISimpleAccount)item.getAccount().toDto());
            if (!transaction.getBalance() && account.getReceivable()
                && item.getDebit()) {
              logger.info("Querying receivable item: " + item);
              pstmt.setBigDecimal(1, new BigDecimal(item.getTransactionId()));
              pstmt.setInt(2, item.getOrderNumber());
              rs = pstmt.executeQuery();
              // Expecting exactly one item; ignore item if nothing found, as
              // it's
              // not a receivable item.
              if (rs.next()) {
                // Found the receivable by matching transaction and order
                // number,
                // so success.
                found = true;
                logger.info("Found receivable item in database");
                break trans;
              }
              rs.close();
            }
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
   * Validate the account types and the account groups within them based on an
   * input collection of accounts linked to groups.
   * 
   * @param connection the JDBC connection
   * @param accounts the collection of accounts containing the types to validate
   */
  private void validateAccountTypes(Connection connection,
                                    Collection<BsAccount> accounts) {
    if (accounts == null || accounts.size() == 0) {
      fail("No account collection");
    }

    boolean found = true;
    Statement stmt = null;
    ResultSet rs = null;
    Map<String, Set<String>> queriedTypes = new HashMap<String, Set<String>>();

    try {
      stmt = connection.createStatement();

      // Query the types and groups to create a map of queried types.
      rs = stmt.executeQuery("SELECT accountType, groupName FROM AccountGroup");
      while (rs.next()) {
        String type = rs.getString("accountType");
        Set<String> groupNames = queriedTypes.get(type);
        if (groupNames == null) {
          // Type not yet in map, create set and add it
          groupNames = new HashSet<String>();
          queriedTypes.put(type, groupNames);
        }
        // Add the group to the group set.
        groupNames.add(rs.getString("groupName"));
      }

      // Iterate through the input types and groups to validate each one.
      Map<String, List<BsAccountGroup>> groupMap = getGroupMap(accounts);
      for (String type : groupMap.keySet()) {
        for (BsAccountGroup group : groupMap.get(type)) {
          Set<String> groupNames = queriedTypes.get(type);
          assertTrue("Asset type " + type + " does not contain group "
                         + group.getPrimaryKey().getStringKey(),
                     groupNames.contains(group.getGroupName()));
        }
      }
    } catch (SQLException e) {
      fail("SQL Exception validating account types and groups: "
           + e.getMessage());
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
   * Index a list of groups by account type, producing a map of lists of groups
   * keyed on account type.
   * 
   * @param accounts the collection of accounts linked to the groups to index
   * @return the map of groups indexed by account type
   */
  private Map<String, List<BsAccountGroup>> getGroupMap(Collection<BsAccount> accounts) {
    Map<String, List<BsAccountGroup>> groupMap =
      new HashMap<String, List<BsAccountGroup>>();

    // First extract a set of groups from the accounts.
    Set<BsAccountGroup> groups = getGroupsFromAccounts(accounts);

    // Then build a map of the groups indexed by account type.
    for (BsAccountGroup group : groups) {
      List<BsAccountGroup> list = groupMap.get(group.getAccountType());
      if (list == null) {
        // No map entry yet, create a new list.
        list = new ArrayList<BsAccountGroup>();
        // Put the list into the map.
        groupMap.put(group.getAccountType(), list);
      }
      list.add(group);
    }

    return groupMap;
  }

  /**
   * Get a set of unique groups from a collection of accounts.
   * 
   * @param accounts the collection of accounts
   * @return the set of unique groups linked to the accounts
   */
  private Set<BsAccountGroup> getGroupsFromAccounts(Collection<BsAccount> accounts) {
    Set<BsAccountGroup> groups = new HashSet<BsAccountGroup>();

    if (accounts == null) {
      fail("No accounts from which to get groups");
    }

    for (BsAccount account : accounts) {
      try {
        Collection<BsFiscalYearAccount> links = account.getFiscalYearAccount();
        if (links != null) {
          for (BsFiscalYearAccount link : links) {
            groups.add(link.getGroup());
          }
        }
      } catch (SQLException e) {
        String msg = "SQL Exception getting fiscal-year-account link";
        logger.error(msg, e);
        fail(msg);
      }
    }
    return groups;
  }

  /**
   * Validate the lists of generated fiscal years and year-account links against
   * the current state of the database using a JDBC query.
   * 
   * @param connection the JDBC connection
   * @param years the list of generated fiscal years
   * @param links the list of fiscal-year-account links
   * @throws SQLException when there is a problem querying years or links
   */
  private void validateFiscalYears(Connection connection,
                                   List<BsFiscalYear> years) {
    if (years == null || years.size() == 0) {
      fail("No fiscal years created");
    }
    boolean found = true;
    Integer missingYear = null;
    PreparedStatement stmt = null;
    ResultSet rs = null;

    try {
      // First, validate the fiscal years.
      stmt =
        connection.prepareStatement("SELECT year FROM FiscalYear WHERE year = ?");
      for (BsFiscalYear year : years) {
        stmt.setInt(1, year.getYear());
        rs = stmt.executeQuery();
        assertTrue("Fiscal year not in database: " + year.getYear(), rs.next());
        rs.close();
        rs = null;
      }
      stmt.close();
      stmt = null;

      // Then validate the links for each valid year.
      for (BsFiscalYear year : years) {
        stmt =
          connection.prepareStatement("SELECT accountOrderNumber, accountType, groupOrderNumber FROM FiscalYearAccount WHERE accountName = ? AND entityName = ? AND year = ?");
        for (BsFiscalYearAccount link : year.getFiscalYearAccount()) {
          stmt.setString(1, link.getAccountName());
          stmt.setString(2, link.getEntityName());
          stmt.setInt(3, link.getYear());
          rs = stmt.executeQuery();
          assertTrue("Fiscal year account link not in database: "
                     + link.getPrimaryKey().getStringKey(), rs.next());
          Integer accountOrderNumber = rs.getInt("accountOrderNumber");
          String accountType = rs.getString("accountType");
          Integer groupOrderNumber = rs.getInt("groupOrderNumber");
          assertTrue("Wrong account order number: " + accountOrderNumber
                         + ", should be " + link.getAccountOrderNumber(),
                     accountOrderNumber.equals(link.getAccountOrderNumber()));
          assertTrue("Wrong account type: " + accountType + ", should be "
                         + link.getAccountType(),
                     accountType.equals(link.getAccountType()));
          assertTrue("Wrong group order number: " + groupOrderNumber
                         + ", should be " + link.getGroupOrderNumber(),
                     groupOrderNumber.equals(link.getGroupOrderNumber()));
          rs.close();
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
    BsFiscalYear firstYear = null;
    for (BsFiscalYear currentYear : years) {
      if (yearCounter == 0) {
        firstYear = currentYear;
        createBalanceTransactions(transactions, transDel, currentYear);
      }
      createYearTransactions(transactions, transDel, firstYear, currentYear);
      yearCounter++;
    }

    return transactions;
  }

  /**
   * Create the set of transactions for a year. Create a receivable transaction
   * and a corresponding reimbursement transaction. Create a set of transactions
   * from the accounts in the year.
   * 
   * @param transactions list of transactions to contain transactions
   * @param transDel transaction delegate to use to create transactions and
   *          items
   * @param firstYear the first fiscal year
   * @param currentYear year containing date to use for transactions
   */
  private void createYearTransactions(List<BsTransaction> transactions,
                                      TransactionDelegate transDel,
                                      BsFiscalYear firstYear,
                                      BsFiscalYear currentYear) {
    BsItem arItem =
      createArTransaction(transactions, transDel, firstYear, currentYear);
    BsItem reimbursingItem =
      createReimbursementTransaction(transactions,
                                     transDel,
                                     firstYear,
                                     currentYear);
    linkReimbursement(arItem, reimbursingItem, transDel);
    createCreditTransaction(transactions, transDel, currentYear);
    createCapitalTransaction(transactions, transDel, currentYear);
  }

  /**
   * Get an asset account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return an asset account in the fiscal year
   */
  private BsAccount getAssetAccount(BsFiscalYear year) {
    BsAccount assetAccount = null;
    // Get the list of fiscal year accounts and find an asset account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          if (type.equals(ASSETS) && account.toDto() instanceof ISimpleAccount) {
            assetAccount = account;
            break; // take first one found
          }
        }
      }
    } catch (SQLException e) {
      logger.error("SQL Exception getting accounts for year", e);
      throw new RuntimeException("SQL Exception getting accounts for year", e);
    }
    return assetAccount;
  }

  /**
   * Get a receivable account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return a receivable account in the fiscal year
   */
  private BsAccount getReceivableAccount(BsFiscalYear year) {
    BsAccount assetAccount = null;
    // Get the list of fiscal year accounts and find an asset account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          if (type.equals(ASSETS) && account.toDto() instanceof ISimpleAccount) {
            ISimpleAccount simpleAccount = (ISimpleAccount)account.toDto();
            if (simpleAccount.getReceivable()) {
              assetAccount = account;
              break; // take first one found
            }
          }
        }
      }
    } catch (SQLException e) {
      String msg = "SQL Exception getting receivable account for year";
      logger.error(msg, e);
      throw new RuntimeException(msg, e);
    }
    return assetAccount;
  }

  /**
   * Get a liability account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return a liability account in the fiscal year
   */
  private BsAccount getLiabilityAccount(BsFiscalYear year) {
    BsAccount liabilityAccount = null;
    // Get the list of fiscal year accounts and find an asset account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          if (type.equals(LIABILITIES)
              && account.toDto() instanceof ISimpleAccount) {
            liabilityAccount = account;
            break; // take first one found
          }
        }
      }
    } catch (SQLException e) {
      logger.error("SQL Exception getting accounts for year", e);
      throw new RuntimeException("SQL Exception getting accounts for year", e);
    }
    return liabilityAccount;
  }

  /**
   * Get a capital account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return a capital account in the fiscal year
   */
  private BsAccount getCapitalAccount(BsFiscalYear year) {
    BsAccount capitalAccount = null;
    // Get the list of fiscal year accounts and find an asset account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          // Find an Equity account that is also a capital account.
          if (type.equals(EQUITY) && account.toDto() instanceof ICapitalAccount) {
            capitalAccount = account;
            break; // take first one found
          }
        }
      }
    } catch (SQLException e) {
      logger.error("SQL Exception getting accounts for year", e);
      throw new RuntimeException("SQL Exception getting accounts for year", e);
    }
    return capitalAccount;
  }

  /**
   * Get an income account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return an income account in the fiscal year
   */
  private BsAccount getIncomeAccount(BsFiscalYear year) {
    BsAccount assetAccount = null;
    // Get the list of fiscal year accounts and find an income account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          if (type.equals(INCOME) && account.toDto() instanceof ISimpleAccount) {
            assetAccount = account;
            break; // take first one found
          }
        }
      }
    } catch (SQLException e) {
      logger.error("SQL Exception getting income account for year", e);
      throw new RuntimeException("SQL Exception getting income account for year",
                                 e);
    }
    return assetAccount;
  }

  /**
   * Get an expense account for a given fiscal year.
   * 
   * @param year the fiscal year
   * @return an expense account in the fiscal year
   */
  private BsAccount getExpenseAccount(BsFiscalYear year) {
    BsAccount assetAccount = null;
    // Get the list of fiscal year accounts and find an income account.
    try {
      for (BsAccount account : year.getAccounts()) {
        for (BsFiscalYearAccount link : account.getFiscalYearAccount()) {
          String type = link.getAccountType();
          if (type.equals(EXPENSES)
              && account.toDto() instanceof ISimpleAccount) {
            assetAccount = account;
            break; // take first one found
          }
        }
      }
    } catch (SQLException e) {
      logger.error("SQL Exception getting expense account for year", e);
      throw new RuntimeException("SQL Exception getting expense account for year",
                                 e);
    }
    return assetAccount;
  }

  /**
   * Create a capital transaction against am equity account (credit) and a cash
   * account (debit). Put the transaction into the accumulating array of
   * transactions.
   * 
   * @param transactions the accumulating array of transactions
   * @param transDel the transaction delegate to use to create the transaction
   *          and items
   * @param year the fiscal year for the transaction
   */

  private void createCapitalTransaction(List<BsTransaction> transactions,
                                        TransactionDelegate transDel,
                                        BsFiscalYear year) {
    BsAccount capitalAccount = getCapitalAccount(year);
    BsAccount assetAccount = getAssetAccount(year);

    String description = StringUtilities.generateString(100);
    BsTransaction capitalTrans =
      transDel.createTransaction(null,
                                 description,
                                 year.getEndDate(),
                                 false,
                                 false);
    assertTrue("capital transaction not created", capitalTrans != null);

    BsItem equityItem =
      transDel.createItem(capitalTrans,
                          capitalTrans.getTransactionId(),
                          1,
                          200.00D,
                          false,
                          false,
                          capitalAccount.getAccountName(),
                          capitalAccount.getEntityName());
    assertTrue("equity item not created", equityItem != null);
    equityItem.setTransaction(capitalTrans);
    equityItem.setAccount(capitalAccount);
    capitalTrans.addItemsItem(equityItem);
    capitalAccount.addItemsItem(equityItem);

    BsItem checkingItem =
      transDel.createItem(capitalTrans,
                          capitalTrans.getTransactionId(),
                          2,
                          200.00D,
                          true,
                          false,
                          assetAccount.getAccountName(),
                          assetAccount.getEntityName());
    assertTrue("checking item not created", checkingItem != null);
    checkingItem.setAccount(assetAccount);
    capitalTrans.addItemsItem(checkingItem);
    assetAccount.addItemsItem(checkingItem);

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
   * @param year the fiscal year for the transaction
   */
  private void createCreditTransaction(List<BsTransaction> transactions,
                                       TransactionDelegate transDel,
                                       BsFiscalYear year) {
    BsTransaction creditTrans =
      transDel.createTransaction(null,
                                 StringUtilities.generateString(100),
                                 year.getStartDate(),
                                 false,
                                 false);
    assertTrue("credit transaction not created", creditTrans != null);

    BsAccount liabilityAccount = getLiabilityAccount(year);
    BsAccount expenseAccount = getExpenseAccount(year);

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
      arItem.addReimbursingItemsReimbursementReimbursement(reimbursement);
      arItem.addReimbursingItemsItem(reimbursingItem);
      ;
      reimbursingItem.addReimbursingItemsReimbursementReimbursement(reimbursement);
      ;
      reimbursingItem.addReimbursingItemsItem(arItem);
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
   * @param firstYear the first fiscal year in the range
   * @param currentYear the fiscal year of the transaction
   * @return the AR item from the transaction
   */
  private BsItem createReimbursementTransaction(List<BsTransaction> transactions,
                                                TransactionDelegate transDel,
                                                BsFiscalYear firstYear,
                                                BsFiscalYear currentYear) {
    BsTransaction reimbursementTransaction =
      transDel.createTransaction(null,
                                 StringUtilities.generateString(100),
                                 currentYear.getEndDate(),
                                 false,
                                 false);
    assertTrue("no reimbursement transaction created",
               reimbursementTransaction != null);

    BsAccount arAccount = getReceivableAccount(firstYear);
    assertTrue("no AR account for year " + currentYear.getYear(),
               arAccount != null);
    BsAccount assetAccount = getAssetAccount(currentYear);
    assertTrue("no asset account for year " + currentYear.getYear(),
               assetAccount != null);

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
                          assetAccount.getAccountName(),
                          assetAccount.getEntityName());
    assertTrue("no reimbursement asset item created", checkingItem != null);
    checkingItem.setAccount(assetAccount);
    reimbursementTransaction.addItemsItem(checkingItem);
    assetAccount.addItemsItem(checkingItem);

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
   * @param firstYear the first fiscal year in the range
   * @param currentYear the fiscal year of the transaction
   * @return the AR item from the transaction
   */
  private BsItem createArTransaction(List<BsTransaction> transactions,
                                     TransactionDelegate transDel,
                                     BsFiscalYear firstYear,
                                     BsFiscalYear currentYear) {
    // AR transaction--income and ar accounts
    BsTransaction arTrans =
      transDel.createTransaction(null,
                                 StringUtilities.generateString(100),
                                 currentYear.getStartDate(),
                                 false,
                                 false);
    assertTrue("no receivable transaction created", arTrans != null);

    BsAccount arAccount = getReceivableAccount(firstYear);
    BsAccount incomeAccount = getIncomeAccount(currentYear);

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
   * Create the single-item balance transactions for a year against the an
   * asset, receivable, liability, and capital accounts. Add the transactions to
   * the list of transactions. Use the client-created transaction delegate to
   * create the transactions and items.
   * 
   * @param transactions list of transactions to contain balance transactions
   * @param transDel transaction delegate to use to create transactions and
   *          items
   * @param date the transaction date for all transactions
   */
  private void createBalanceTransactions(List<BsTransaction> transactions,
                                         TransactionDelegate transDel,
                                         BsFiscalYear year) {
    Timestamp date = year.getStartDate();

    BsAccount assetAccount = getAssetAccount(year);
    assertTrue("No asset account for year " + year.getYear(),
               assetAccount != null);
    BsAccount receivableAccount = getReceivableAccount(year);
    assertTrue("No receivable account for year " + year.getYear(),
               receivableAccount != null);
    BsAccount liabilityAccount = getLiabilityAccount(year);
    assertTrue("No liability account for year " + year.getYear(),
               liabilityAccount != null);
    BsAccount capitalAccount = getCapitalAccount(year);
    assertTrue("No capital account for year " + year.getYear(),
               capitalAccount != null);

    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             100.00D,
                             true,
                             assetAccount);
    createBalanceTransaction(transactions,
                             transDel,
                             date,
                             0.00D,
                             true,
                             receivableAccount);
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
                             capitalAccount);
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

  private String getSubsystem() {
    return "com.poesys.accounting.db.account";
  }
}
