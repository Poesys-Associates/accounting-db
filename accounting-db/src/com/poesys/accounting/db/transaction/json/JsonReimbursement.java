/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.poesys.db.AbstractJsonObject;
import com.poesys.db.pk.json.JsonPrimaryKey;

public class JsonReimbursement extends AbstractJsonObject {
  /** JSON primary key for the object */
  private JsonPrimaryKey primaryKey;
  /** the amount of the receivable reimbursed by the reimbursing item */
  private Double reimbursedAmount;
  /** the amount of the receivable allocated by the reimbursing item transaction */
  private Double allocatedAmount;

  /**
   * Create an empty Reimbursement for use by GSON.
   */
  public JsonReimbursement() {
    super(AbstractJsonObject.EXISTING);
  }

  /**
   * Create a JSON Reimbursement with the association primary key and the two amounts. Status defaults to EXISTING.
   *
   * @param primaryKey  the JSON association key for the Reimbursement object
   * @param reimbursedAmount   the amount of the receivable reimbursed by the reimbursing item
   * @param allocatedAmount    the amount of the receivable allocated by the reimbursing item
   *                           transaction
   */
  public JsonReimbursement(JsonPrimaryKey primaryKey,
                           Double reimbursedAmount, Double allocatedAmount) {
    super(AbstractJsonObject.EXISTING);
    this.primaryKey = primaryKey;
    this.reimbursedAmount = reimbursedAmount;
    this.allocatedAmount = allocatedAmount;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonReimbursement that = (JsonReimbursement)o;

    // Compare the primary keys.
    return primaryKey.equals(that.primaryKey);
  }

  @Override
  public int hashCode() {
    // hash the fully qualified class name and the primary key to minimize hashcode overlaps
    int result = this.getClass().getName().hashCode();
    return 31 * result + primaryKey.hashCode();
  }

  /**
   * Get the allocated amount.
   *
   * @return an amount
   */
  public Double getAllocatedAmount() {
    return allocatedAmount;
  }

  /**
   * Get the reimbursed amount.
   *
   * @return an amount
   */
  public Double getReimbursedAmount() {
    return reimbursedAmount;
  }
}
