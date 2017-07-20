/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;


import java.util.ArrayList;
import java.util.List;

import org.junit.AfterClass;
import org.junit.BeforeClass;

import com.poesys.cartridges.db.utilities.StringUtilities;


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
public class EntityDelegateTest extends
    com.poesys.accounting.bs.account.AbstractEntityDelegateTest {
  private static final String INC_GROUP_NAME = "Service Revenue";
  private static final String INCOME_ACCOUNT_TYPE = "Income";
  private static BsAccountGroup incomeGroup = null;
  private static AccountGroupDelegate groupDel =
      AccountDelegateFactory.getAccountGroupDelegate();

  /**
   * Before running all the tests in the class, set up an income account group
   * for use in creating accounts.
   * 
   * @throws Exception when there is a problem setting up the group
   */
  @BeforeClass
  public static void setUpBeforeClass() throws Exception {
    incomeGroup = groupDel.createAccountGroup(INC_GROUP_NAME);
    groupDel.insert(incomeGroup);
  }

  /**
   * After the last test method runs, delete the account group set up in the
   * setUpBeforeClass() method.
   * 
   * @throws Exception when there is a problem deleting the account group
   */
  @AfterClass
  public static void tearDownAfterClass() throws Exception {
    incomeGroup.delete();
    groupDel.delete(incomeGroup);
    groupDel = null;
  }

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

    EntityDelegate delegate = AccountDelegateFactory.getEntityDelegate();

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

  @Override
  protected java.util.List<com.poesys.accounting.bs.account.BsAccount> createAccountAccount(com.poesys.accounting.db.account.IEntity parent,
                                                                                            int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    List<BsAccount> objects =
      new ArrayList<com.poesys.accounting.bs.account.BsAccount>();

    for (int i = 0; i < count; i++) {
      String accountName = StringUtilities.generateString(50);
      String description = StringUtilities.generateString(50);
      BsAccount account =
        delegate.createAccount(new BsEntity(parent),
                               parent.getEntityName(),
                               accountName,
                               description,
                               false,
                               INCOME_ACCOUNT_TYPE,
                               false,
                               true,
                               INC_GROUP_NAME);
      account.setGroup(incomeGroup);
      objects.add(account);
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
}