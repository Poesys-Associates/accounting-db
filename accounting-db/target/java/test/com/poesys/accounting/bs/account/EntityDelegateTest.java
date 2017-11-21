/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;


import com.poesys.accounting.db.account.AccountFactory;
import com.poesys.accounting.db.account.IAccount;
import com.poesys.accounting.db.account.SimpleAccount;
import com.poesys.cartridges.db.utilities.StringUtilities;
import com.poesys.db.pk.IPrimaryKey;


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

  @Override
  protected java.util.List<com.poesys.accounting.bs.account.BsFiscalYearAccount> createAccountFiscalYearAccount(java.util.List<com.poesys.accounting.db.account.IAccount> accountsList,
                                                                                                                java.util.List<com.poesys.accounting.db.account.IAccountGroup> groupList,
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

    EntityDelegate delegate = AccountDelegateFactory.getEntityDelegate();

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
                                         groupObject.getAccountType(),
                                         groupObject.getOrderNumber(),
                                         groupList.get(i));
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
    java.util.List<com.poesys.accounting.bs.account.BsAccount> objects =
      new java.util.concurrent.CopyOnWriteArrayList<com.poesys.accounting.bs.account.BsAccount>();

    for (int i = 0; i < count; i++) {
      String accountName = StringUtilities.generateString(100);
      IPrimaryKey key =
        AccountFactory.getAccountPrimaryKey(accountName, parent.getEntityName());
      String description = StringUtilities.generateString(100);
      IAccount account =
        new SimpleAccount(key,
                          parent.getEntityName(),
                          accountName,
                          description,
                          false,
                          false,
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
}