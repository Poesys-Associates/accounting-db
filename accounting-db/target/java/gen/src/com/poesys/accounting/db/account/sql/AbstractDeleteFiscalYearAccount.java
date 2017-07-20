/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelete.vsl

package com.poesys.accounting.db.account.sql;



import com.poesys.db.dao.delete.AbstractDeleteSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for deleting a FiscalYearAccount
 * 
 * @author Robert J. Muller
 */
public class AbstractDeleteFiscalYearAccount extends AbstractDeleteSql<com.poesys.accounting.db.account.IFiscalYearAccount> {
  private static final String SQL =
    "DELETE FROM FiscalYearAccount WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    return super.getSql(key, SQL);
  }

  @Override
  public String getParamString(com.poesys.accounting.db.account.IFiscalYearAccount dto) {
    return null;
  }
}
