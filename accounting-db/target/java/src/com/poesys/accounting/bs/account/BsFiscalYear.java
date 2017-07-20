/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Template: BsDto.vsl

package com.poesys.accounting.bs.account;


import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.delegate.DelegateException;


/**
 * <p>
 * A business layer data-transfer object for the FiscalYear. This class
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
public class BsFiscalYear extends com.poesys.accounting.bs.account.AbstractBsFiscalYear {
  /**
   * Create a BsFiscalYear object from a FiscalYear object.
   * 
   * @param dto the data-access layer FiscalYear DTO
   * @throws DelegateException when there is a problem creating the FiscalYear
   */
  public BsFiscalYear(com.poesys.accounting.db.account.IFiscalYear dto) throws DelegateException {
    super(dto);
  }

  /**
   * <p>
   * Create a BsFiscalYear. This constructor calls the abstract superclass 
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
  public BsFiscalYear(IPrimaryKey key, java.lang.Integer year, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
    super(key, year, startDate, endDate); 
  }
}