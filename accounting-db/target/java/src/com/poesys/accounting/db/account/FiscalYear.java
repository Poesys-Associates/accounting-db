/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: DbDto.vsl

package com.poesys.accounting.db.account;


import com.poesys.db.pk.IPrimaryKey;


/**
 * <p>
 * A data-access layer data-transfer object for the FiscalYear. This class
 * is the concrete subclass of the generated abstract class. Make any changes
 * to DTO behavior by overriding methods here rather than changing the abstract
 * superclass; AndroMDA will overwrite that class when you run it but will
 * never overwrite this concrete subclass.
 * </p>
 * <p>
 * An accounting period, usually either coterminous with a calendar year with the
 * same name or varying over an annual period starting on a particular calendar
 * date within the year; identifies a complete accounting period for statements
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>NaturalKey</li>
 *     <li>Persistent</li>
 * </ul>
 * 
 * @author Poesys/DB Cartridge
 */
public class FiscalYear extends AbstractFiscalYear {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /**
   * <p>
   * Create a FiscalYear as a new object. This constructor calls the abstract 
   * superclass constructor.
   * </p>
   *
   */
  public FiscalYear() {
    super(); 
  }

  /**
   * <p>
   * Create a FiscalYear. This constructor calls the abstract superclass 
   * constructor.
   * </p>
   *
   * @param key the primary key of the FiscalYear
   * @param year the fiscal year, a year value corresponding to the calendar year of the last day
of an accounting period
   * @param startDate the calendar day of the first day of the accounting period
   * @param endDate the last calendar day of the accounting period; year corresponds to the fiscal
year number
   */
  public FiscalYear(IPrimaryKey key, java.lang.Integer year, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
    super(key, year, startDate, endDate); 
  }
}