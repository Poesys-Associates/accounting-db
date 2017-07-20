/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: Insert.vsl

package com.poesys.accounting.db.transaction.sql;

import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * An insert of a Reimbursement. This class is the concrete subclass of the 
 * generated abstract class AbstractInsertReimbursement. 
 * </p>
 * <p>
 * Make any changes to insert behavior by overriding methods here rather than 
 * changing the abstract superclass; AndroMDA will overwrite that class when you
 * run it but will never overwrite this concrete subclass.
 * </p>
${dto.getDocumentation}
 * 
 * @author Poesys/DB Cartridge
 */
public class InsertReimbursement extends AbstractInsertReimbursement {
  private static final String SQL =
      "INSERT INTO Reimbursement (receivablesOrderNumber, receivablesTransactionId, reimbursingItemsOrderNumber, reimbursingItemsTransactionId, reimbursedAmount, allocatedAmount) VALUES (?,?,?,?,?,?)";

    @Override
    public String getSql(IPrimaryKey key) {
      return SQL;
    }
}