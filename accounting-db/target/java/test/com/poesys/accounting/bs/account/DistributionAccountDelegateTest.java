/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;


import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.cartridges.db.utilities.StringUtilities;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.connection.JdbcConnectionManager;


/**
 * <p>
 * A test class for the DistributionAccountDelegate class. This class is the
 * concrete subclass of the generated abstract class. Make any changes to DTO
 * behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will never
 * overwrite this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class DistributionAccountDelegateTest extends
    com.poesys.accounting.bs.account.AbstractDistributionAccountDelegateTest {

  private static BsEntity entity = null;
  private static EntityDelegate enDel =
    AccountDelegateFactory.getEntityDelegate();

  /**
   * Before running all the tests in the class, set up an entity.
   * 
   * @throws Exception when there is a problem setting up the group
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    entity = enDel.createEntity(StringUtilities.generateString(25));
    enDel.insert(entity);
  }

  /**
   * After the last test method runs, delete the entity set up in the
   * setUpBeforeClass() method.
   * 
   * @throws Exception when there is a problem deleting the account group
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    entity.delete();
    enDel.delete(entity);
    enDel = null;
  }

  // Override to correct code to use separately created Entity object.
  @Override
  protected List<BsDistributionAccount> createAccountDistributionAccount(int count)
      throws DelegateException, InvalidParametersException {
    List<BsDistributionAccount> objects =
      new ArrayList<BsDistributionAccount>();
    Random r = new Random();

    // Create the required CapitalEntityAccountCapitalEntity objects.
    List<BsCapitalEntity> capEntities =
      createCapitalEntityAccountCapitalEntity(count);

    for (int i = 0; i < count; i++) {
      java.lang.String accountName = StringUtilities.generateString(100);
      java.lang.String description = StringUtilities.generateString(500);
      java.lang.Boolean debitDefault = r.nextBoolean();
      java.lang.Boolean active = r.nextBoolean();

      // Create the object.
      BsDistributionAccount object = null;
      try {
        object =
          delegate.createDistributionAccount(accountName,
                                             entity.getEntityName(),
                                             description,
                                             debitDefault,
                                             active,
                                             capEntities.get(i).getCapitalEntityName());
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

  @Override
  @Test
  public void testUpdate() {
    Connection connection = null;
    List<BsDistributionAccount> objects = null;

    try {
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());

      // Create a new DistributionAccount object to perform the test.
      objects = createAccountDistributionAccount(1);
      assertTrue("No object created", objects.get(0) != null);
      delegate.insert(objects);
      com.poesys.db.pk.CompositePrimaryKey key =
        (com.poesys.db.pk.CompositePrimaryKey)objects.get(0).getPrimaryKey();
      assertTrue("No key for inserted object", key != null);
      BsDistributionAccount updateAccount = objects.get(0);
      assertTrue("No created object to update", updateAccount != null);
      String originalDescription = updateAccount.getDescription();

      // Update the inserted object and query it to test.
      updateAccount.setDescription(StringUtilities.generateString(100));

      delegate.update(updateAccount);

      // Verify the update.
      assertTrue("DistributionAccount not updated",
                 verifyUpdate(connection,
                              updateAccount.getEntityName(),
                              updateAccount.getAccountName(),
                              originalDescription));
    } catch (SQLException | IOException e1) {
      fail("Cannot get JDBC connection for test validation");
    } finally {
      // Delete the inserted objects to clean up.
      if (objects != null) {
        // Mark all the objects for delete.
        for (BsDistributionAccount object : objects) {
          object.delete();
          object.getCapitalEntity().delete();
        }

        delegate.deleteBatch(objects);
      }

      // Close the connection.
      try {
        connection.close();
      } catch (SQLException e) {
        // log and ignore
        logger.error("Couldn't close JDBC connection");
      }
    }
  }

  @Override
  @Test
  public void testUpdateBatch() throws InvalidParametersException,
      DelegateException {
    Connection connection = null;
    List<BsDistributionAccount> objects = null;

    try {
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());
      objects = createAccountDistributionAccount(2);
      delegate.insert(objects);

      // Save the original descriptions.
      List<String> originalDescriptions = new ArrayList<String>(2);
      originalDescriptions.add(objects.get(0).getDescription());
      originalDescriptions.add(objects.get(1).getDescription());

      // Update the descriptions.
      for (BsDistributionAccount object : objects) {
        object.setDescription(StringUtilities.generateString(100));
      }

      delegate.updateBatch(objects);

      int counter = 0;
      for (BsDistributionAccount object : objects) {
        // Verify the update.
        assertTrue("DistributionAccount not updated",
                   verifyUpdate(connection,
                                object.getEntityName(),
                                object.getAccountName(),
                                originalDescriptions.get(counter)));
        counter++;
      }
    } catch (SQLException | IOException e1) {
      fail("Cannot get JDBC connection for test validation");
    } finally {
      // Delete the inserted objects to clean up.
      if (objects != null) {
        // Mark all the objects for delete.
        for (BsDistributionAccount object : objects) {
          object.delete();
          object.getCapitalEntity().delete();
        }

        delegate.deleteBatch(objects);
      }

      // Close the connection.
      try {
        connection.close();
      } catch (SQLException e) {
        // log and ignore
        logger.error("Couldn't close JDBC connection");
      }
    }
  }

  @Override
  @Test
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    Connection connection = null;
    List<BsDistributionAccount> allObjects = null;

    try {
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());

      // Create 3 rows--one to insert, one to update, one to delete.
      allObjects = createAccountDistributionAccount(3);

      // Insert the first two rows.
      List<BsDistributionAccount> existingObjects =
        new ArrayList<BsDistributionAccount>();
      existingObjects.add(allObjects.get(0));
      existingObjects.add(allObjects.get(1));

      delegate.insert(existingObjects);

      // Update the description of the first object in the array.
      BsDistributionAccount updateAccount = existingObjects.get(0);
      String originalDescription = updateAccount.getDescription();
      updateAccount.setDescription(StringUtilities.generateString(100));

      // Delete the second object in the array.
      BsDistributionAccount deletedAccount = existingObjects.get(1);
      deletedAccount.delete();

      // Track the inserted object (third in the array).
      BsDistributionAccount insertedAccount = allObjects.get(2);

      // Test process().
      delegate.process(allObjects);

      // Verify the update.
      assertTrue("DistributionAccount not updated",
                 verifyUpdate(connection,
                              updateAccount.getEntityName(),
                              updateAccount.getAccountName(),
                              originalDescription));

      // Verify the delete.
      assertTrue("DistributionAccount not deleted",
                 verifyDelete(connection,
                              deletedAccount.getEntityName(),
                              deletedAccount.getAccountName()));

      // Verify the insert.
      assertTrue("object not inserted",
                 verifyInsert(connection,
                              insertedAccount.getEntityName(),
                              insertedAccount.getAccountName()));

    } catch (SQLException | IOException e1) {
      fail("Cannot get JDBC connection for test validation");
    } finally {
      // Delete the inserted objects to clean up.
      if (allObjects != null) {
        // Mark all the objects for delete.
        for (BsDistributionAccount object : allObjects) {
          object.delete();
          object.getCapitalEntity().delete();
        }

        delegate.deleteBatch(allObjects);
      }

      // Close the connection.
      try {
        connection.close();
      } catch (SQLException e) {
        // log and ignore
        logger.error("Couldn't close JDBC connection");
      }
    }
  }

  /**
   * Verify the insertion of a DistributionAccount.
   * 
   * @param connection the JDBC connection
   * @param entityName the entity containing the account
   * @param accountName the name of the account
   * @return true if the account was inserted, false if not
   */
  public boolean verifyInsert(Connection connection, String entityName,
                              String accountName) {
    boolean inserted = false;
    PreparedStatement stmt = null;
    String sql =
      "SELECT 1 FROM Account WHERE entityName = ? AND accountName = ?";

    try {
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, entityName);
      stmt.setString(2, accountName);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        inserted = true;
      } else {
        fail("No DistributionAccount created for update");
      }
    } catch (SQLException e) {
      logger.error("SQL exception getting inserted account", e);
      fail("SQL exception getting inserted account: " + e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          logger.error("SQL exception closing insert verification statement", e);
          // log and ignore
        }
      }
    }

    return inserted;
  }

  /**
   * Verify the updated description for a DistributionAccount.
   * 
   * @param connection the JDBC connection
   * @param entityName the entity containing the account
   * @param accountName the name of the account
   * @param originalDescription the original description
   * @return true if updated, false if not
   */
  public boolean verifyUpdate(Connection connection, String entityName,
                              String accountName, String originalDescription) {
    boolean updated = false;
    PreparedStatement stmt = null;
    String sql =
      "SELECT description FROM Account WHERE entityName = ? AND accountName = ?";
    try {
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, entityName);
      stmt.setString(2, accountName);
      ResultSet rs = stmt.executeQuery();
      if (rs.next()) {
        String updatedDescription = rs.getString("description");
        updated = !updatedDescription.equals(originalDescription);
      } else {
        fail("No DistributionAccount created for update");
      }
    } catch (SQLException e) {
      logger.error("SQL exception getting updated description", e);
      fail("SQL exception getting updated description: " + e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          logger.error("SQL exception closing update verification statement", e);
          // log and ignore
        }
      }
    }

    return updated;
  }

  /**
   * Verify the deletion of a DistributionAccount.
   * 
   * @param connection the JDBC connection
   * @param entityName the entity containing the account
   * @param accountName the name of the account
   * @return true if the account was deleted, false if not
   */
  public boolean verifyDelete(Connection connection, String entityName,
                              String accountName) {
    boolean deleted = false;
    PreparedStatement stmt = null;
    String sql =
      "SELECT 1 FROM Account WHERE entityName = ? AND accountName = ?";

    try {
      stmt = connection.prepareStatement(sql);
      stmt.setString(1, entityName);
      stmt.setString(2, accountName);
      ResultSet rs = stmt.executeQuery();
      if (!rs.next()) {
        deleted = true;
      }
    } catch (SQLException e) {
      logger.error("SQL exception getting deleted account", e);
      fail("SQL exception getting deleted account: " + e.getMessage());
    } finally {
      if (stmt != null) {
        try {
          stmt.close();
        } catch (Exception e) {
          logger.error("SQL exception closing deletion verification statement",
                       e);
          // log and ignore
        }
      }
    }

    return deleted;
  }

  @Override
  public void testTruncateTable() {
    // Truncate usually is hard; super.testTruncateTable() if desired
  }

  @Override
  public void testGetAllObjects() {
    super.testGetAllObjects();
  }

  @Override
  protected String getSubsystem() {
    // return explicit subsystem name
    return "com.poesys.accounting.db.account";
    // use super.getSubsystem() to get default subsystem name if needed
  }

  @Override
  protected List<BsCapitalEntity> createCapitalEntityAccountCapitalEntity(int count)
      throws DelegateException, InvalidParametersException {
    List<BsCapitalEntity> objects = new ArrayList<BsCapitalEntity>();
    CapitalEntityDelegate cpEDel =
      AccountDelegateFactory.getCapitalEntityDelegate();

    for (int i = 0; i < count; i++) {
      BsCapitalEntity capitalEntity =
        cpEDel.createCapitalEntity(StringUtilities.generateString(500));
      objects.add(capitalEntity);
    }

    // Insert the objects here.
    cpEDel.process(objects);

    return objects;
  }
}