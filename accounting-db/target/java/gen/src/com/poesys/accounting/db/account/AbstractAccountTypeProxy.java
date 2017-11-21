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
 * AccountType. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * A named group of account groups, grouping accounts within basic accounting types
 * for a fiscal year. The types are fixed and include: Assets, Liabilities, Equity,
 * Income, and Expense.
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountTypeProxy extends AbstractLazyLoadingDtoProxy implements IAccountType {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractAccountTypeProxy.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractAccountTypeProxy> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractAccountTypeProxy>();
  


  /**
   * Read-Object setter for de-serializing nested groups list
   *
   * Source: AddToManyChildCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryAccountGroup
   */
  private class ReadGroupsSetter 
      extends com.poesys.db.dto.AbstractListReadSetter<com.poesys.accounting.db.account.IAccountGroup> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadGroupsSetter object to read the groups list.
     */
    public ReadGroupsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.AccountGroup.class.getName();
    }

    @Override
    protected java.util.List<com.poesys.accounting.db.account.IAccountGroup> getObjectList() {
      java.util.List<com.poesys.accounting.db.account.IAccountGroup> groups =  ((com.poesys.accounting.db.account.AccountType)dto).getGroups();
      return groups;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.accounting.db.account.AccountType)dto).groupsKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IAccountGroup> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryAccountGroup();
    }

    @Override
    protected void set(java.util.List<com.poesys.accounting.db.account.IAccountGroup> list) {
       setGroups(list);
    }
  }

  /**
   * Add a com.poesys.accounting.db.account.IAccountGroup object to the Groups collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: AddToManyChildCollectionProperties
   * 
   * @param object the com.poesys.accounting.db.account.IAccountGroup object to add to the collection
   */
  public void addGroupsAccountGroup(com.poesys.accounting.db.account.IAccountGroup object) {
    ((AccountType)dto).addGroupsAccountGroup(object);
  }

  /**
   * Create a AccountTypeProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractAccountTypeProxy(AccountType dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadGroupsSetter());

    // Add query setters for single-object deserialization.
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
                 + " with readObject in AbstractAccountTypeProxy");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }

  @Override
  public boolean equals(Object obj) {
    AbstractAccountTypeProxy otherProxy = (AbstractAccountTypeProxy)obj;
    return ((AccountType)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((AccountType)dto).hashCode();
  }

  // Local properties (attributes, associations, and association classes)

  /**
   * Get an object of java.lang.String
   *
   * Source: AddNaturalKeyProperties
   * Lazy: false
   * 
   * @return a java.lang.String
   */
  public java.lang.String getAccountType() {
    return ((AccountType)dto).getAccountType();
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
    ((AccountType)dto).setAccountType(accountType);
  }

  /**
   * Get a list of com.poesys.accounting.db.account.IAccountGroup
   *
   * Source: AddToManyChildCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.List<com.poesys.accounting.db.account.IAccountGroup>
   */
  public java.util.List<com.poesys.accounting.db.account.IAccountGroup> getGroups() {
    return ((AccountType)dto).getGroups();
  }

  /**
   * Set the groups from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param groups the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  public void setGroups(java.util.List<com.poesys.accounting.db.account.IAccountGroup> groups)
      throws com.poesys.db.InvalidParametersException {
    ((AccountType)dto).setGroups(groups);
  }

  public void markChildrenDeleted() {
  	((AccountType)dto).markChildrenDeleted();
  }
}