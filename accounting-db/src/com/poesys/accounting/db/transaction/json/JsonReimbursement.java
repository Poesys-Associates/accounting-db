/* Copyright (c) 2018 Poesys Associates. All rights reserved. */
package com.poesys.accounting.db.transaction.json;

import com.poesys.db.AbstractJsonObject;
import com.poesys.db.pk.json.JsonPrimaryKey;

public class JsonReimbursement extends AbstractJsonObject {
  /** JSON primary key for the receivable item; part of the primary key */
  private JsonPrimaryKey receivableItemKey;
  /** JSON primary key for the reimbursing item; part of the primary key */
  private JsonPrimaryKey reimbursingItemKey;
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
   * Create a JSON Reimbursement by specifying the primary key (receivable and reimbursing item
   * keys) and the two amounts. Status defaults to EXISTING.
   *
   * @param receivableItemKey  the key for the receivable item (part of primary key)
   * @param reimbursingItemKey the key for the reimbursing item (part of primary key)
   * @param reimbursedAmount   the amount of the receivable reimbursed by the reimbursing item
   * @param allocatedAmount    the amount of the receivable allocated by the reimbursing item
   *                           transaction
   */
  public JsonReimbursement(JsonPrimaryKey receivableItemKey, JsonPrimaryKey reimbursingItemKey,
                           Double reimbursedAmount, Double allocatedAmount) {
    super(AbstractJsonObject.EXISTING);
    this.receivableItemKey = receivableItemKey;
    this.reimbursingItemKey = reimbursingItemKey;
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

    // Compare the association key components.
    return receivableItemKey.equals(that.receivableItemKey) &&
           reimbursingItemKey.equals(that.reimbursingItemKey);
  }

  @Override
  public int hashCode() {
    // hash the fully qualified class name and the primary key to minimize hashcode overlaps
    int result = this.getClass().getName().hashCode();
    result = 31 * result + receivableItemKey.hashCode();
    return 31 * result + reimbursingItemKey.hashCode();
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
