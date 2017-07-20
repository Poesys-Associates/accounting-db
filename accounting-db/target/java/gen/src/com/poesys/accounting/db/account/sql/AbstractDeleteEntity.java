/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDelete.vsl

package com.poesys.accounting.db.account.sql;



import com.poesys.db.dao.delete.AbstractDeleteSql;
import com.poesys.db.pk.IPrimaryKey;


/**
 * SQL statement specification for deleting a Entity
 * 
 * @author Robert J. Muller
 */
public class AbstractDeleteEntity extends AbstractDeleteSql<com.poesys.accounting.db.account.IEntity> {
  private static final String SQL =
    "DELETE FROM Entity WHERE ";

  @Override
  public String getSql(IPrimaryKey key) {
    return super.getSql(key, SQL);
  }

  @Override
  public String getParamString(com.poesys.accounting.db.account.IEntity dto) {
    return null;
  }
}
