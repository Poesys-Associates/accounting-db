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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.poesys.accounting.db.account.IAccount;
import com.poesys.accounting.db.account.IAccountGroup;
import com.poesys.accounting.db.account.IFiscalYear;
import com.poesys.bs.delegate.DelegateException;
import com.poesys.db.InvalidParametersException;
import com.poesys.db.connection.IConnectionFactory.DBMS;
import com.poesys.db.connection.JdbcConnectionManager;


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

  @Override
  protected List<BsFiscalYearAccount> createAccountFiscalYearAccount(List<IAccount> accountsList,
                                                                     List<IAccountGroup> groupList,
                                                                     List<IFiscalYear> yearsList,
                                                                     int count)
      throws DelegateException, InvalidParametersException {
    List<BsFiscalYearAccount> objects = new ArrayList<BsFiscalYearAccount>();

    // Check the input requirements. The input lists must each have at
    // least count objects as they must map objects one-to-one.
    if (accountsList.size() < count) {
      org.junit.Assert.fail("createAccountFiscalYearAccount accountsList list has "
                            + accountsList.size()
                            + " elements but needs at least " + count);
    }
    if (groupList.size() < count) {
      org.junit.Assert.fail("createAccountFiscalYearAccount groupList list has "
                            + groupList.size()
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
      BsAccount accountsObject = new BsAccount(accountsList.get(i));
      BsAccountGroup groupObject = new BsAccountGroup(groupList.get(i));
      BsFiscalYear yearsObject = new BsFiscalYear(yearsList.get(i));

      BsFiscalYearAccount link =
        delegate.createFiscalYearAccount(accountsObject,
                                         groupObject,
                                         yearsObject,
                                         accountsObject.getAccountName(),
                                         accountsObject.getEntityName(),
                                         yearsObject.getYear(),
                                         i,
                                         i,
                                         groupObject.getAccountType(),
                                         groupObject.getGroupName(),
                                         groupObject.toDto());
      objects.add(link);
    }

    return objects;
  }

  @Override
  public void testTruncateTable() {
    // Truncate usually is hard; super.testTruncateTable() if desired
  }

  @Override
  public void testGetAllObjects() {
    super.testGetAllObjects();
  }

  // Override to correct validation/comparison code
  @Override
  @Test
  public void testUpdateBatch() throws InvalidParametersException,
      DelegateException {
    Connection connection = null;
    List<BsFiscalYear> objects = createFiscalYears(2);

    List<Timestamp> originalDates = new ArrayList<Timestamp>(2);

    try {
      connection =
        JdbcConnectionManager.getConnection(DBMS.MYSQL, getSubsystem());
      delegate.insert(objects);

      for (BsFiscalYear object : objects) {
        Timestamp endDate = object.getEndDate();

        // Save the current data value for later comparison.
        originalDates.add(endDate);

        // Set the end date to the day before its current date.
        LocalDateTime datetime = endDate.toLocalDateTime();
        object.setEndDate(Timestamp.valueOf(datetime.minusDays(1L)));
      }

      delegate.updateBatch(objects);

      int counter = 0;
      for (BsFiscalYear object : objects) {
        try {
          PreparedStatement stmt =
            connection.prepareStatement("SELECT endDate FROM FiscalYear WHERE year = ?");
          stmt.setInt(1, object.getYear());
          ResultSet rs = stmt.executeQuery();
          if (rs.next()) {
            Timestamp endDate = rs.getTimestamp("endDate");
            Timestamp originalEndDate = originalDates.get(counter);
            assertTrue("Fiscal year not updated: " + object.getYear()
                           + ", end date "
                           + object.getEndDate().toLocalDateTime().toString(),
                       !endDate.equals(originalEndDate));
          } else {
            fail("Fiscal year not in database: " + object.getYear());
          }
        } catch (Exception e) {
          logger.error("Exception querying batch update for comparison", e);
          fail("Exception during update comparison");
        }
        counter++;
      }
    } catch (SQLException | IOException e) {
      // log and ignore
      logger.error("Exception during batch update test", e);
    } finally {
      // Delete the inserted objects to clean up.
      if (objects != null) {
        // Mark all the objects for delete.
        for (BsFiscalYear object : objects) {
          object.delete();
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
  protected String getSubsystem() {
    // return explicit subsystem name
    return "com.poesys.accounting.db.account";
    // use super.getSubsystem() to get default subsystem name if needed
  }

  // Replace generated date code with more useful values using Java
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
}