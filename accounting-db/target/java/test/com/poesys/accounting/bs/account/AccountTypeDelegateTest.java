/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DelegateTest.vsl

package com.poesys.accounting.bs.account;


import java.util.ArrayList;
import java.util.List;

import com.poesys.accounting.db.account.AccountFactory;
import com.poesys.accounting.db.account.AccountGroup;
import com.poesys.accounting.db.account.IAccountGroup;
import com.poesys.accounting.db.account.IAccountType;
import com.poesys.cartridges.db.utilities.StringUtilities;
import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A test class for the AccountTypeDelegate class. This class is the concrete
 * subclass of the generated abstract class. Make any changes to DTO behavior by
 * overriding methods here rather than changing the abstract superclass;
 * AndroMDA will overwrite that class when you run it but will never overwrite
 * this concrete subclass.
 * </p>
 * 
 * @author Poesys/DB Cartridge
 */
public class AccountTypeDelegateTest extends
    com.poesys.accounting.bs.account.AbstractAccountTypeDelegateTest {

  protected java.util.List<BsAccountGroup> createAccountAccountGroup(IAccountType parent,
                                                                     int count)
      throws com.poesys.bs.delegate.DelegateException,
      com.poesys.db.InvalidParametersException,
      com.poesys.db.dto.DtoStatusException {
    List<BsAccountGroup> objects = new ArrayList<BsAccountGroup>();

    for (int i = 0; i < count; i++) {
      String groupName = StringUtilities.generateString();
      IPrimaryKey key =
        AccountFactory.getAccountGroupPrimaryKey(parent.getAccountType(), i);
      IAccountGroup group =
        new AccountGroup(key, parent.getAccountType(), i, groupName);
      objects.add(new BsAccountGroup(group));
    }

    return objects;
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

  protected String getTestKeyAccountType(int objectNumber) {
    switch (objectNumber) {
    case 0:
      return "Assets";
    case 1:
      return "Liabilities";
    case 2:
      return "Equity";
    case 3:
      return "Income";
    case 4:
      return "Expenses";
    default:
      return "Assets";
    }
  }
}