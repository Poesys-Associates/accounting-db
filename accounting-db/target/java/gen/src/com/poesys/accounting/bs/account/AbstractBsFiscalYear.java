/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractBsDto.vsl


package com.poesys.accounting.bs.account;


import com.poesys.bs.delegate.DelegateException;
import com.poesys.bs.dto.IDto;
import com.poesys.db.dto.DtoStatusException;
import com.poesys.db.pk.IPrimaryKey;
import com.poesys.bs.dto.AbstractDto;


import com.poesys.accounting.db.account.IFiscalYear;
import com.poesys.accounting.db.account.FiscalYearProxy;
import com.poesys.accounting.db.account.FiscalYear;


/**
 * <p>
 * A business layer data-transfer object (DTO) for the FiscalYear. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
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
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractBsFiscalYear 
    extends AbstractDto<com.poesys.accounting.db.account.IFiscalYear> {

  /**
   * A List builder helper class for building a business-layer List 
   * of BsAccount objects from a List accounts of data-access-layer 
   * com.poesys.accounting.db.account.IAccount objects
   */
  private class BsAccountsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.db.account.IAccount, com.poesys.accounting.bs.account.BsAccount> {
    @Override
    public com.poesys.accounting.bs.account.BsAccount get(com.poesys.accounting.db.account.IAccount dto) {
      return new com.poesys.accounting.bs.account.BsAccount(dto);
    }
  }

  /**
   * A List builder helper class for building a data-access-layer List 
   * of Account objects from an input List accounts of 
   * business-layer BsAccount objects
   */
  private class AccountsListBuilder 
      extends com.poesys.bs.dto.ListBuilder<com.poesys.accounting.bs.account.BsAccount, com.poesys.accounting.db.account.IAccount> {
    @Override
    public com.poesys.accounting.db.account.IAccount get(com.poesys.accounting.bs.account.BsAccount dto) {
      return dto.toDto();
    }
  }

  /**
   * A Collection builder helper class for building a business-layer Collection 
   * of BsFiscalYearAccount objects from a Collection fiscalYearAccount of data-access-layer 
   * com.poesys.accounting.db.account.IFiscalYearAccount objects
   */
  private class BsFiscalYearAccountCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.accounting.db.account.IFiscalYearAccount, com.poesys.accounting.bs.account.BsFiscalYearAccount> {
    @Override
    public com.poesys.accounting.bs.account.BsFiscalYearAccount get(com.poesys.accounting.db.account.IFiscalYearAccount dto) {
      return new com.poesys.accounting.bs.account.BsFiscalYearAccount(dto);
    }
  }

  /**
   * A Collection builder helper class for building a data-access-layer Collection 
   * of FiscalYearAccount objects from an input Collection fiscalYearAccount of 
   * business-layer BsFiscalYearAccount objects
   */
  private class FiscalYearAccountCollectionBuilder 
      extends com.poesys.bs.dto.CollectionBuilder<com.poesys.accounting.bs.account.BsFiscalYearAccount, com.poesys.accounting.db.account.IFiscalYearAccount> {
    @Override
    public com.poesys.accounting.db.account.IFiscalYearAccount get(com.poesys.accounting.bs.account.BsFiscalYearAccount dto) {
      return dto.toDto();
    }
  }

  /**
   * Create a BsFiscalYear object from a FiscalYear object.
   * 
   * @param dto the data-access layer FiscalYear DTO
   * @throws DelegateException when there is a problem creating the FiscalYear
   */
  public AbstractBsFiscalYear(IFiscalYear dto) throws DelegateException {
    super(dto);
  }

  /**
   * Create a FiscalYear from new data.
   *
   * @param key the primary key of the FiscalYear
   * @param year the fiscal year, a year value corresponding to the calendar year of the last day
of an accounting period
   * @param startDate the calendar day of the first day of the accounting period
   * @param endDate the last calendar day of the accounting period; year corresponds to the fiscal
year number
   */
  public AbstractBsFiscalYear(IPrimaryKey key, java.lang.Integer year, java.sql.Timestamp startDate, java.sql.Timestamp endDate) {
    super(new FiscalYearProxy(new FiscalYear(key, year, startDate, endDate)));
  }

  @SuppressWarnings("unchecked")
  @Override
  public boolean equals(Object arg0) {
    // Unchecked cast here
    IDto<IFiscalYear> other = (IDto<IFiscalYear>)arg0;
    return dto.equals(other.toDto());
  }

  @Override
  public int hashCode() {
    return dto.hashCode();
  }

  public int compareTo(IDto<IFiscalYear> o) {
    return dto.compareTo(o.toDto());
  }

  public void markChildrenDeleted() throws DtoStatusException {
    dto.markChildrenDeleted();
  }
  
  public IPrimaryKey getPrimaryKey() {
    return dto.getPrimaryKey();
  }

  // Data member properties

  /**
   * <p>
   * the fiscal year, a year value corresponding to the calendar year of the last day
   * of an accounting period
   * </p>
   * <p>
   * Added by AddNaturalKeyProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: false</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @return a java.lang.Integer year
   */
  public java.lang.Integer getYear() {
    return dto.getYear();
  }

  /**
   * <p>
   * the calendar day of the first day of the accounting period
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @return a java.sql.Timestamp startDate
   */
  public java.sql.Timestamp getStartDate() {
    return dto.getStartDate();
  }

  /**
   * <p>
   * Set the startDate.
   * </p>
   * <p>
   * the calendar day of the first day of the accounting period
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @param startDate the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter startDate is null
   */
  public void setStartDate(java.sql.Timestamp startDate) 
      throws com.poesys.db.dto.DtoStatusException , com.poesys.db.InvalidParametersException {
    dto.setStartDate(startDate);
  }

  /**
   * <p>
   * the last calendar day of the accounting period; year corresponds to the fiscal
   * year number
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties as data member
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @return a java.sql.Timestamp endDate
   */
  public java.sql.Timestamp getEndDate() {
    return dto.getEndDate();
  }

  /**
   * <p>
   * Set the endDate.
   * </p>
   * <p>
   * the last calendar day of the accounting period; year corresponds to the fiscal
   * year number
   * </p>
   * <p>
   * Added by AddLocalAttributeProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @param endDate the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter endDate is null
   */
  public void setEndDate(java.sql.Timestamp endDate) 
      throws com.poesys.db.dto.DtoStatusException , com.poesys.db.InvalidParametersException {
    dto.setEndDate(endDate);
  }

  /**
   * <p>
   * the set of accounts active during the given fiscal year, ordered by an order
   * number; used in constructing financial statements
   * </p>
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties as data member
   * </p>
   * <p>
   * This property loads lazily on demand the first time you call this method,
   * not when you instantiate the BsFiscalYear object.
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsAccount endDate
   * @throws java.sql.SQLException when the method can't get a SQL connection to 
   *                               load the property lazily
   */
  public java.util.List<com.poesys.accounting.bs.account.BsAccount> getAccounts() throws java.sql.SQLException {
    BsAccountsListBuilder builder = new BsAccountsListBuilder();
    return builder.getList(dto.getAccounts());
  }

  /**
   * <p>
   * Set the account.
   * </p>
   * <p>
   * the set of accounts active during the given fiscal year, ordered by an order
   * number; used in constructing financial statements
   * </p>
   * <p>
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @param endDate the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter endDate is null
   */
  public void setAccount(java.util.List<com.poesys.accounting.bs.account.BsAccount> endDate) 
      throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException {
    AccountsListBuilder builder = new AccountsListBuilder();
      dto.setAccount(builder.getList(endDate));
  }

  /**
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties as data member
   * </p>
   * <p>
   * This property loads lazily on demand the first time you call this method,
   * not when you instantiate the BsFiscalYear object.
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @return a com.poesys.accounting.bs.account.BsFiscalYearAccount endDate
   * @throws java.sql.SQLException when the method can't get a SQL connection to 
   *                               load the property lazily
   */
  public java.util.Collection<com.poesys.accounting.bs.account.BsFiscalYearAccount> getFiscalYearAccount() throws java.sql.SQLException {
    BsFiscalYearAccountCollectionBuilder builder = new BsFiscalYearAccountCollectionBuilder();
    return builder.getCollection(dto.getFiscalYearAccount());
  }

  /**
   * <p>
   * Set the fiscalYearAccount.
   * </p>
   * <p>
   * Collection of association class objects (not the associated objects)
   * </p>
   * <p>
   * Added by AddAssociationClassCollectionProperties
   * </p>
   * <ul>
   * <li>Property is read/write: true</li>
   * <li>Property is defined in the data-access object FiscalYear</li>
   * </ul>
   * @param endDate the associated business object
   * @throws com.poesys.db.dto.DtoStatusException when the system can't set
   *                 the data-access status to CHANGED
   * @throws com.poesys.db.InvalidParametersException when the property is
   *                 required but the input parameter endDate is null
   */
  public void setFiscalYearAccount(java.util.Collection<com.poesys.accounting.bs.account.BsFiscalYearAccount> endDate) 
      throws com.poesys.db.dto.DtoStatusException, com.poesys.db.InvalidParametersException {
    FiscalYearAccountCollectionBuilder builder = new FiscalYearAccountCollectionBuilder();
      dto.setFiscalYearAccount(builder.getCollection(endDate));
  }

  /**
   * Add a Account object to the accounts collection.
   *
   * @param object the object to add to the collection
   * @throws java.sql.SQLException when the method can't get a SQL connection to 
   *                               load the property lazily
   */
  public void addAccountsAccount(com.poesys.accounting.bs.account.BsAccount object) throws java.sql.SQLException {
    if (object == null) {
      throw new com.poesys.db.InvalidParametersException(com.poesys.db.Message.getMessage("com.poesys.db.dao.msg.no_dto", null));
    }
    
    dto.addAccountsAccount(object.toDto());
  }

  /**
   * Add a FiscalYearAccount object to the fiscalYearAccount collection.
   *
   * @param object the object to add to the collection
   * @throws java.sql.SQLException when the method can't get a SQL connection to 
   *                               load the property lazily
   */
  public void addFiscalYearAccountFiscalYearAccount(com.poesys.accounting.bs.account.BsFiscalYearAccount object) throws java.sql.SQLException {
    if (object == null) {
      throw new com.poesys.db.InvalidParametersException(com.poesys.db.Message.getMessage("com.poesys.db.dao.msg.no_dto", null));
    }
    
    dto.addFiscalYearAccountFiscalYearAccount(object.toDto());
  }
}