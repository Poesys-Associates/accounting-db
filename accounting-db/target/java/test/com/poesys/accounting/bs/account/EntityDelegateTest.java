/* Copyright 2018 Poesys Associates. All rights reserved. */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;

import com.poesys.accounting.db.account.AccountFactory;
import com.poesys.accounting.db.account.IAccount;
import com.poesys.accounting.db.account.SimpleAccount;
import com.poesys.cartridges.db.utilities.StringUtilities;
import com.poesys.db.connection.IConnectionFactory;
import com.poesys.db.connection.JdbcConnectionManager;
import com.poesys.db.pk.IPrimaryKey;
import org.apache.log4j.Logger;
import org.junit.Test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * <p>
 * A test class for the EntityDelegate class. This class is the concrete
 * subclass of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public class EntityDelegateTest extends com.poesys.accounting.bs.account
  .AbstractEntityDelegateTest {
  private static final Logger logger = Logger.getLogger(EntityDelegateTest.class);

  @Override
  protected java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount>
  createAccountFiscalYearAccount(java.util.List<com.poesys.accounting.db.account.IAccount>
                                   accountsList, java.util.List<com.poesys.accounting.db.account
    .IAccountGroup> groupList, java.util.List<com.poesys.accounting.db.account.IFiscalYear>
    yearsList, int count) throws com.poesys.bs.delegate.DelegateException, com.poesys.db
    .InvalidParametersException, com.poesys.db.dto.DtoStatusException {
    java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> objects =
      new java.util.concurrent.CopyOnWriteArrayList<>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (accountsList.size() < count) {
      org.junit.Assert.fail(
        "createAccountFiscalYearAccount accountsList list has " + accountsList.size() +
        " elements but needs at least " + count);
    }
    if (groupList.size() < count) {
      org.junit.Assert.fail(
        "createAccountFiscalYearAccount groupList list has " + groupList.size() +
        " elements but needs at least " + count);
    }
    if (yearsList.size() < count) {
      org.junit.Assert.fail(
        "createAccountFiscalYearAccount yearsList list has " + yearsList.size() +
        " elements but needs at least " + count);
    }

    // implement child creation by mapping the objects in the input lists
    // one-to-one

    EntityDelegate delegate = AccountDelegateFactory.getEntityDelegate();

    for (int i = 0; i < count; i++) {
      BsAccount accountsObject = new BsAccount(accountsList.get(i));
      BsAccountGroup groupObject = new BsAccountGroup(groupList.get(i));
      BsFiscalYear yearsObject = new BsFiscalYear(yearsList.get(i));

      BsFiscalYearAccount link =
        delegate.createFiscalYearAccount(accountsObject, groupObject, yearsObject,
                                         accountsObject.getAccountName(),
                                         accountsObject.getEntityName(), yearsObject.getYear(), i,
                                         i, groupObject.getAccountType(),
                                         groupObject.getGroupName(), groupList.get(i));
      objects.add(link);
    }

    return objects;
  }

  @Override
  protected java.util.List<com.poesys.accounting.bs.account.BsAccount> createAccountAccount(com.poesys.accounting.db.account.IEntity parent, int count) throws com.poesys.bs.delegate.DelegateException, com.poesys.db.InvalidParametersException, com.poesys.db.dto.DtoStatusException {
    java.util.List<com.poesys.accounting.bs.account.BsAccount> objects =
      new java.util.concurrent.CopyOnWriteArrayList<>();

    for (int i = 0; i < count; i++) {
      String accountName = StringUtilities.generateString(100);
      IPrimaryKey key = AccountFactory.getAccountPrimaryKey(accountName, parent.getEntityName());
      String description = StringUtilities.generateString(100);
      IAccount account =
        new SimpleAccount(key, accountName, parent.getEntityName(), description, false, false,
                          true);
      objects.add(new BsAccount(account));
    }
    return objects;
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

  /**
   * Test the scenario where you build a new entity, add an account to it, then insert the entity
   * . The delegate should also insert the account as a nested object.
   */
  @Test
  public void testInsertAccountWithEntity() {
    Connection connection = null;
    String entityName = null;
    try {
      connection =
        JdbcConnectionManager.getConnection(IConnectionFactory.DBMS.MYSQL, getSubsystem());

      // Create a new Entity object to perform the test.
      List<BsEntity> entities = createAccountEntity(1);
      assertTrue("No entity created", !entities.isEmpty());
      BsEntity entity = entities.get(0);
      // Set the entity name for the finally clause.
      entityName = entity.getEntityName();

      // Create a new Account and add it to the entity.
      List<BsAccount> accounts = createAccountAccount(entity.toDto(), 1);
      assertTrue("No account created", !accounts.isEmpty());
      BsAccount account = accounts.get(0);
      entity.addAccountsAccount(account);

      // Insert the entity and the account.
      delegate.insert(entities);

      // Verify the entity and account creation.
      assertTrue("entity not inserted properly", verifyEntity(connection, entity));
      assertTrue("simple account not inserted properly",
                 verifyEntityAccount(connection, entity, account));
    } catch (SQLException | IOException e1) {
      fail("Cannot get JDBC connection for test validation");
    }
    finally {
      // Remove the entity, should also cascade to remove the account.
      String sql = "DELETE FROM Entity WHERE entityName = ?";
      PreparedStatement stmt = null;
      if (entityName != null) {
        try {
          stmt = connection.prepareStatement(sql);
          stmt.setString(1, entityName);
          logger.info("cleanup: " + sql);
          logger.info("parameter: " + entityName);
          stmt.execute();
        } catch (SQLException e) {
          fail("could not delete entity: " + e.getMessage());
        } finally {
          try {
            stmt.close();
          } catch (SQLException e) {
            fail("could not close statement for entity delete: " + e.getMessage());
          }
        }
      }

      // Close the connection.
      try {
        if (connection != null) {
          connection.close();
        }
      } catch (SQLException e) {
        // log and ignore
        logger.error("Couldn't close JDBC connection");
      }
    }
  }

  /**
   * Verify that an entity business object is stored in the database.
   *
   * @param connection the JDBC connection to the database
   * @param entity     the entity business object
   * @return true if the entity exists in the database based on the primary key of the entity
   */
  private boolean verifyEntity(Connection connection, BsEntity entity) {
    boolean verified = false;
    String sql = "SELECT 1 FROM Entity WHERE entityName = ?";
    PreparedStatement stmt = null;
    try {
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, entity.getEntityName());
      logger.debug("verification query: " + sql);
      logger.debug("parameters: " + entity.getEntityName());
      ResultSet rs = stmt.executeQuery();
      verified = rs.next();
    } catch (SQLException e) {
      fail("SQL Exception verifying entity: " + e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          fail("Could not close entity query: " + e.getMessage());
        }
      }
    }

    return verified;
  }

  /**
   * Verify that an account business object is stored in the database and belongs to a specified
   * entity object there.
   *
   * @param connection the JDBC connection to the database
   * @param entity     the entity business object
   * @param account    the account business object
   * @return true if the entity exists in the database based on the primary key of the entity
   */
  private boolean verifyEntityAccount(Connection connection, BsEntity entity, BsAccount account) {
    boolean verified = false;
    String sql =
      "SELECT 1 FROM Account a JOIN SimpleAccount sa ON a.entityName = sa.entityName AND a.accountName = sa.accountName WHERE a.entityName = ? AND a.accountName = ?";
    PreparedStatement stmt = null;
    try {
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, entity.getEntityName());
      stmt.setString(2, account.getAccountName());
      logger.debug("verification query: " + sql);
      logger.debug("parameters: " + entity.getEntityName() + ", " + account.getAccountName());
      ResultSet rs = stmt.executeQuery();
      verified = rs.next();
    } catch (SQLException e) {
      fail("SQL Exception verifying account: " + e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (SQLException e) {
          fail("Could not close entity query: " + e.getMessage());
        }
      }
    }

    return verified;
  }
}