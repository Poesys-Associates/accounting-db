/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelegateTest.vsl

package com.poesys.accounting.bs.account;


import static org.junit.Assert.assertTrue;


import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;

import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.dao.DaoManagerFactory;
import com.poesys.db.dao.IDaoManager;


/**
 * <p>
 * Test the CapitalEntityDelegate class.
 * </p>
 * <p>
 * Note that all the test cases defined here run independently, because JUnit
 * does not guarantee the execution order of the test cases in any way.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractCapitalEntityDelegateTest {
  /** Define a class logger. */
  protected static Logger logger = Logger.getLogger(AbstractCapitalEntityDelegateTest.class);
  /** Subsystem name in database.properties file */
  protected String subsystem = "com.poesys.accounting.db.account";
  /** Boolean saved off for later comparison in update tests */
  protected Boolean originalBooleanValue = null;
  /** Delegate created at class level for sharing between methods */
  protected CapitalEntityDelegate delegate = null;

  /**
   * Set up the test by creating the class under test (CUT).
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Before
  public void setUp() {
    delegate = AccountDelegateFactory.getCapitalEntityDelegate();
    // Clear any existing caches of objects using the memory manager set in
    // the creation of the delegate.
    clearCaches();
    assertTrue("No delegate created", delegate != null);
  }
  
  /**
   * Helper method to clear caches for any objects used in the test
   */
  protected void clearCaches() {
    // Primary class CapitalEntity cache
    IDaoManager manager = DaoManagerFactory.getManager(getSubsystem());
    if (manager != null) {
      manager.clearCache(com.poesys.accounting.db.account.CapitalEntity.class.getName());
    }
  }

  /**
   * Create some number of new CapitalEntity objects and return them in a list.  
   * This is a helper method for writable children DTOs.
   * 
   * @param count the number of objects to create
   * @return the stored object
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  protected List<com.poesys.accounting.bs.account.BsCapitalEntity> createAccountCapitalEntity(int count) 
      throws DelegateException, InvalidParametersException {
    List<com.poesys.accounting.bs.account.BsCapitalEntity> objects = 
      new CopyOnWriteArrayList<com.poesys.accounting.bs.account.BsCapitalEntity>();
    @SuppressWarnings("unused")
    java.util.Random r = new java.util.Random();
    
    for (int i = 0; i < count; i++) {
      java.lang.String capitalEntityName = 
        com.poesys.cartridges.db.utilities.StringUtilities.generateString(500);

      // Create the object.
      BsCapitalEntity object = null;
      try {
        object = delegate.createCapitalEntity(capitalEntityName);
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
   * Test method for delegate insert
   */
  @Test
  public void testInsert() {
    // Create a new CapitalEntity object to perform the test.
    List<BsCapitalEntity> objects = createAccountCapitalEntity(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);

    BsCapitalEntity queriedObject = queryStoredObject(key);
    assertTrue("No queried inserted object", queriedObject != null);
    assertTrue("Wrong object", objects.get(0).equals(queriedObject));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * Test method for getObject
   * 
   * @throws java.sql.SQLException when there is a problem querying a collection 
   *                               property
   */
  @Test
  public void testGetObject() throws java.sql.SQLException {
    // Create a new CapitalEntity object to perform the test.
    List<BsCapitalEntity> objects = createAccountCapitalEntity(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key generated from concrete implementation", key != null);
    BsCapitalEntity insertedObject = objects.get(0);
    assertTrue("No comparison object for object query", insertedObject != null);
    
    // Query the object.
    BsCapitalEntity object = delegate.getObject(key);
    assertTrue("Couldn't get object", object != null);
    assertTrue("Wrong object", insertedObject.equals(object));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity o : objects) {
      o.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * <p>
   * Test method for getAllObjects
   * </p>
   * <p>
   * For this read/write class, the method ensures that there are objects to
   * query by inserting an object, querying, then deleting the object.
   * </p>
   *
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testGetAllObjects() 
       throws InvalidParametersException, DelegateException {
    // Create a new CapitalEntity object to perform the test.
    List<BsCapitalEntity> insertedObjects = createAccountCapitalEntity(1);
    assertTrue("No object created", insertedObjects.get(0) != null);
    delegate.insert(insertedObjects);
    
    // Query all the objects.
    List<BsCapitalEntity> objects = delegate.getAllObjects(100);
    assertTrue("Couldn't create list", objects != null);
    // The list should not be empty.
    assertTrue("List of all objects empty", objects.size() != 0);
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity o : insertedObjects) {
      o.delete();
    }

    delegate.deleteBatch(insertedObjects);
  }

  /**
   * A helper method that queried a previously stored object identified by its
   * primary key. The method returns the queried object for further testing
   * after performing structural tests on the object.
   * 
   * @param key the primary key of the object to retrieve
   * @return the object
   * @throws DelegateException when there is a problem querying the object
   */
  protected BsCapitalEntity queryStoredObject(com.poesys.db.pk.NaturalPrimaryKey key)
      throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsCapitalEntity queriedObject = delegate.getDatabaseObject(key);
    assertTrue("Object not found", queriedObject != null);
    return queriedObject;
  }

  /**
   * A helper method that tests the stored existence of the object identified
   * by the pre-existing, saved primary key.
   * 
   * @param key the primary key (com.poesys.db.pk.NaturalPrimaryKey) of the object to check
   * @return true if the object is in the database, false if not
   * @throws DelegateException when there is a problem querying the object
   */
  protected boolean exists(com.poesys.db.pk.NaturalPrimaryKey key) throws DelegateException {
    // Clear the caches, then query the saved object and test it.
    clearCaches();

    BsCapitalEntity queriedObject = delegate.getObject(key);
    return queriedObject != null;
  }

  /**
   * Test the updating of the inserted object.
   */
  @Test
  public void testUpdate() {
    // Create a new CapitalEntity object to perform the test.
    List<BsCapitalEntity> objects = createAccountCapitalEntity(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);
    BsCapitalEntity insertedObject = objects.get(0);
    assertTrue("No created object to update", insertedObject != null);
    
    // Update the inserted object and query it to test.
    updateColumn(insertedObject);
    delegate.update(insertedObject);
    BsCapitalEntity queriedObject = queryStoredObject(key);
    assertTrue("Object not updated", isColumnUpdated(queriedObject));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }
  
  /**
   * Update the first mutable column with a new value.
   *
   * @param object the BsCapitalEntity object to update
   */
  protected void updateColumn(BsCapitalEntity object) {
  }

  /**
   * Is the appropriate column in the input object set to the update value?
   *
   * @param object the BsCapitalEntity object to update
   * @return true if the column is updated, false if not
   * @see #updateColumn
   */
  protected boolean isColumnUpdated(BsCapitalEntity object) {
    boolean retVal = false;
    // No update possible, set return to true.
    retVal = true;
    return retVal;
  }

  /**
   * Is the appropriate column in the input object set to the updated value in 
   * the original object? This method is a helper for the batchUpdate test.
   *
   * @param original the original, updated BsCapitalEntity object
   * @param queried the queried BsCapitalEntity object
   * @return true if the column is updated, false if not
   */
  protected boolean isColumnUpdated(BsCapitalEntity original, BsCapitalEntity queried) {
    boolean retVal = true;
    return retVal;
  }

  /**
   * Test method for batch update
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testUpdateBatch() throws InvalidParametersException,
      DelegateException {
    List<BsCapitalEntity> objects = createAccountCapitalEntity(2);
    delegate.insert(objects);
    // Allocate a map to hold the updated objects for later comparison.
    java.util.Map<com.poesys.db.pk.IPrimaryKey, BsCapitalEntity> map = 
      new java.util.TreeMap<com.poesys.db.pk.IPrimaryKey, BsCapitalEntity>();
    for (BsCapitalEntity object : objects) {
      updateColumn(object);
      // Add the object to the storage map.
      map.put(object.getPrimaryKey(), object);
    }
    delegate.updateBatch(objects);
    
    clearCaches();
    
    for (BsCapitalEntity object : objects) {
      BsCapitalEntity queriedObject = 
        queryStoredObject((com.poesys.db.pk.NaturalPrimaryKey)object.getPrimaryKey());
      BsCapitalEntity originalObject = map.get(object.getPrimaryKey());
      assertTrue("Object not updated", 
                 isColumnUpdated(originalObject, queriedObject));
    }
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * Test method for delete
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testDelete() throws InvalidParametersException,
      DelegateException {
    // Create a new CapitalEntity object to perform the test.
    List<BsCapitalEntity> objects = createAccountCapitalEntity(1);
    assertTrue("No object created", objects.get(0) != null);
    delegate.insert(objects);
    com.poesys.db.pk.NaturalPrimaryKey key = 
      (com.poesys.db.pk.NaturalPrimaryKey)objects.get(0).getPrimaryKey();
    assertTrue("No key for inserted object", key != null);
    BsCapitalEntity insertedObject = objects.get(0);
    assertTrue("No inserted object to delete", insertedObject != null);
    
    insertedObject.delete();
    delegate.delete(insertedObject);
    assertTrue("object not deleted", !exists(key));
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);
  }

  /**
   * Test method for deleteBatch
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testDeleteBatch() throws InvalidParametersException,
      DelegateException {
    List<BsCapitalEntity> objects = createAccountCapitalEntity(2);
    delegate.insert(objects);
    
    // Mark all the objects for delete.
    for (BsCapitalEntity object : objects) {
      object.delete();
    }

    delegate.deleteBatch(objects);

    for (BsCapitalEntity object : objects) {
      assertTrue("object not deleted", !exists((com.poesys.db.pk.NaturalPrimaryKey)object.getPrimaryKey()));
    }
  }

  /**
   * Test method for process()
   * 
   * @throws InvalidParametersException when an invalid parameter prevents the
   *       creation of an object
   * @throws DelegateException when a problem occurs during object creation
   */
  @Test
  public void testProcess() throws InvalidParametersException,
      DelegateException {
    // Create 3 rows--one to insert, one to update, one to delete.
    List<BsCapitalEntity> allObjects = createAccountCapitalEntity(3);
    // Insert the first two rows to later update and delete.
    List<BsCapitalEntity> existingObjects = 
      new CopyOnWriteArrayList<BsCapitalEntity>();
    existingObjects.add(allObjects.get(0));
    existingObjects.add(allObjects.get(1));
    delegate.insert(existingObjects);
    updateColumn(existingObjects.get(0));
    existingObjects.get(1).delete();

    // Set the third object as the object to insert.
    List<BsCapitalEntity> insertObject = 
      new CopyOnWriteArrayList<BsCapitalEntity>();
    insertObject.add(allObjects.get(2));
    
    // Put it all together.
    List<BsCapitalEntity> objects = 
      new CopyOnWriteArrayList<BsCapitalEntity>(existingObjects);
    objects.addAll(insertObject);

    // Test the process method
    delegate.process(objects);

    // Verify the update
    BsCapitalEntity queriedObject = 
      queryStoredObject((com.poesys.db.pk.NaturalPrimaryKey)existingObjects.get(0).getPrimaryKey());
    assertTrue("Update not processed", isColumnUpdated(queriedObject));

    // Verify the delete
    assertTrue("object not deleted", !exists((com.poesys.db.pk.NaturalPrimaryKey)existingObjects.get(1).getPrimaryKey()));

    // Verify the insert
    assertTrue("object not inserted", exists((com.poesys.db.pk.NaturalPrimaryKey)insertObject.get(0).getPrimaryKey()));
    
    // Delete the inserted objects to clean up.
    // Mark all the objects for delete.
    for (BsCapitalEntity object : allObjects) {
      object.delete();
    }

    delegate.deleteBatch(allObjects);
  }

  /**
   * Test method for truncateTable
   */
  @Test
  public void testTruncateTable() {
    delegate.truncateTable("CapitalEntity");
    List<BsCapitalEntity> objects = delegate.getAllObjects(100);
    assertTrue("Couldn't create list", objects != null);
    // The list should be empty.
    assertTrue("Table not truncated", objects.size() == 0);
  }

  /**
   * Return the database access subsystem name to use to look up access
   * properties in the database.properties file. The concrete implementation
   * should override this method to provide a different subsystem name if
   * needed.
   * 
   * @return the database access subsystem name
   */
  protected String getSubsystem() {
    return subsystem;
  }
}