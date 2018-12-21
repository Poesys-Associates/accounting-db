/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: SubsystemDelegateFactory.vsl

package com.poesys.accounting.bs.account;

/**
 * <p>
 * A separate, shareable set of factory methods for all the account
 * business delegate classes
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public class AccountDelegateFactory extends AbstractAccountDelegateFactory {
  /**
   * Get an account delegate, which can query an existing BsAccount business object of any
   * concrete subclass.
   *
   * @param className the name of the actual concrete subclass to query
   * @return an account delegate
   */
  public static AccountDelegate getAccountDelegate(String className) {
    return new AccountDelegate(className, subsystem, JDBC_DBMS);
  }
}