/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDtoProxy.vsl

package com.poesys.accounting.db.account;


import org.apache.log4j.Logger;

import com.poesys.db.dto.AbstractLazyLoadingDtoProxy;


/**
 * <p>
 * A data-access layer data-transfer object (DTO) lazy-loading proxy for the 
 * AccountGroup. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * A named group of fiscal-year accounts, grouping the accounts for presentation
 * and aggregation in financial statements for the fiscal year
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountGroupProxy extends AbstractLazyLoadingDtoProxy implements IAccountGroup {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractAccountGroupProxy.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractAccountGroupProxy> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractAccountGroupProxy>();

  // Lazy-loading/deserialization query setter strategy nested classes for 
  // single-object associations
  
  /**
   * Query setter for lazily querying nested type object
   * (object property)
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryAccountType
   */
  private class QueryTypeSetter 
      extends com.poesys.db.dto.AbstractLazyObjectSetter<com.poesys.accounting.db.account.IAccountType> {
    /** Serial version UID for Serializable object */
    private static final long serialVersionUID = 1L;

    /**
     * Create a QueryTypeSetter object.
     */
    public QueryTypeSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.AccountType.class.getName();
    }

    @Override
    protected com.poesys.db.pk.IPrimaryKey getKey() {
      // Generate an com.poesys.accounting.db.account.IAccountType primary key with the value 
      // from the com.poesys.accounting.db.account.IAccountGroup object
      return com.poesys.accounting.db.account.AccountFactory.getAccountTypePrimaryKey(((IAccountGroup)dto).getAccountType());
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IAccountType> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryAccountType();
    }

    @Override
    protected void set(com.poesys.accounting.db.account.IAccountType dto) {
      setType(dto);
    }

    @Override
    public boolean isSet() {
      // Set if proxied DTO type exists
      return (((AccountGroup)dto).getType() != null);
    }
  }



  /**
   * Read-Object setter for de-serializing nested accounts collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryFiscalYearAccount
   */
  private class ReadAccountsSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.accounting.db.account.IFiscalYearAccount> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadAccountsSetter object to read the accounts collection.
     */
    public ReadAccountsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.FiscalYearAccount.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> getObjectCollection() {
      java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> accounts =  ((com.poesys.accounting.db.account.AccountGroup)dto).getAccounts();
      return accounts;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.accounting.db.account.AccountGroup)dto).accountsKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IFiscalYearAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryFiscalYearAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> collection) {
      setAccounts(collection);
    }
  }

  /**
   * Add a com.poesys.accounting.db.account.IFiscalYearAccount object to the Accounts collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @param object the com.poesys.accounting.db.account.IFiscalYearAccount object to add to the collection
   */
  public void addAccountsFiscalYearAccount(com.poesys.accounting.db.account.IFiscalYearAccount object) {
    ((AccountGroup)dto).addAccountsFiscalYearAccount(object);
  }

  /**
   * Create a AccountGroupProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractAccountGroupProxy(AccountGroup dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadAccountsSetter());

    // Add query setters for single-object deserialization.
    readObjectSetters.add(new QueryTypeSetter());
  }

  /**
   * Read an object from an input stream, de-serializing it. Each generated
   * class must have this private method, which the deserialize method calls
   * through Java reflection on the specific class. The class calls a shared
   * code method to run the readObjectSetters.
   * 
   * @param in the object input stream
   * @throws ClassNotFoundException when a nested object class can't be found
   * @throws IOException when there is an IO problem reading the stream
   */
  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException,
    ClassNotFoundException {
    logger.debug("Deserializing object of class " + this.getClass().getName()
                 + " with readObject in AbstractAccountGroupProxy");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }

  @Override
  public boolean equals(Object obj) {
    AbstractAccountGroupProxy otherProxy = (AbstractAccountGroupProxy)obj;
    return ((AccountGroup)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((AccountGroup)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.lang.String
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getAccountType() {
    return ((AccountGroup)dto).getAccountType();
  }

  /**
   * Set the accountType from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param accountType the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  void setAccountType(java.lang.String accountType)
      throws com.poesys.db.InvalidParametersException {
    ((AccountGroup)dto).setAccountType(accountType);
  }

  /**
   * Get an object of java.lang.Integer
   *
   * Source: AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * Lazy: false
   * 
   * @return a java.lang.Integer
   */
  public java.lang.Integer getOrderNumber() {
    return ((AccountGroup)dto).getOrderNumber();
  }

  /**
   * Set the orderNumber from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param orderNumber the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  void setOrderNumber(java.lang.Integer orderNumber)
      throws com.poesys.db.InvalidParametersException {
    ((AccountGroup)dto).setOrderNumber(orderNumber);
  }

  /**
   * Get an object of java.lang.String
   *
   * Source: AddLocalAttributeProperties
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getGroupName() {
    return ((AccountGroup)dto).getGroupName();
  }

  /**
   * Set the groupName from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param groupName the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  public void setGroupName(java.lang.String groupName)
      throws com.poesys.db.InvalidParametersException {
    ((AccountGroup)dto).setGroupName(groupName);
  }

  /**
   * Get an object of com.poesys.accounting.db.account.IAccountType
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * Lazy: false
   * 
   * @return a com.poesys.accounting.db.account.IAccountType
   */
  public com.poesys.accounting.db.account.IAccountType getType() {
    return ((AccountGroup)dto).getType();
  }

  /**
   * Set the type from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param type the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  public void setType(com.poesys.accounting.db.account.IAccountType type)
      throws com.poesys.db.InvalidParametersException {
    ((AccountGroup)dto).setType(type);
  }

  /**
   * Get a collection of com.poesys.accounting.db.account.IFiscalYearAccount
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount>
   */
  public java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> getAccounts() {
    return ((AccountGroup)dto).getAccounts();
  }

  /**
   * Set the accounts from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param accounts the lazily loaded value to assign
   */
  public void setAccounts(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> accounts)
      {
    ((AccountGroup)dto).setAccounts(accounts);
  }

  public void markChildrenDeleted() {
  	((AccountGroup)dto).markChildrenDeleted();
  }
}