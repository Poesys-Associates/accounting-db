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
 * CapitalEntity. This class is an abstract class that contains AndroMDA 
 * generated code; change nothing in this class. Instead, override any methods 
 * in the concrete subclass generated in the same package. AndroMDA will 
 * overwrite this class each time you run it but will never overwrite the concrete subclass.
 * </p>
 * 
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractCapitalEntityProxy extends AbstractLazyLoadingDtoProxy implements ICapitalEntity {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractCapitalEntityProxy.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractCapitalEntityProxy> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractCapitalEntityProxy>();
  


  /**
   * Read-Object setter for de-serializing nested capitalAccount collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryCapitalAccount
   */
  private class ReadCapitalAccountSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.accounting.db.account.ICapitalAccount> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadCapitalAccountSetter object to read the capitalAccount collection.
     */
    public ReadCapitalAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.CapitalAccount.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> getObjectCollection() {
      java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount =  ((com.poesys.accounting.db.account.CapitalEntity)dto).getCapitalAccount();
      return capitalAccount;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.accounting.db.account.CapitalEntity)dto).capitalAccountKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.ICapitalAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryCapitalAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> collection) {
      setCapitalAccount(collection);
    }
  }

  /**
   * Add a com.poesys.accounting.db.account.ICapitalAccount object to the CapitalAccount collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @param object the com.poesys.accounting.db.account.ICapitalAccount object to add to the collection
   */
  public void addCapitalAccountCapitalAccount(com.poesys.accounting.db.account.ICapitalAccount object) {
    ((CapitalEntity)dto).addCapitalAccountCapitalAccount(object);
  }



  /**
   * Read-Object setter for de-serializing nested distributionAccount collection
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryDistributionAccount
   */
  private class ReadDistributionAccountSetter 
      extends com.poesys.db.dto.AbstractCollectionReadSetter<com.poesys.accounting.db.account.IDistributionAccount> {
    private static final long serialVersionUID = 1L;

    /**
     * Create a ReadDistributionAccountSetter object to read the distributionAccount collection.
     */
    public ReadDistributionAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.DistributionAccount.class.getName();
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> getObjectCollection() {
      java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount =  ((com.poesys.accounting.db.account.CapitalEntity)dto).getDistributionAccount();
      return distributionAccount;
    }

    @Override
    protected java.util.List<com.poesys.db.pk.IPrimaryKey> getPrimaryKeys() {
      return ((com.poesys.accounting.db.account.CapitalEntity)dto).distributionAccountKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IDistributionAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryDistributionAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> collection) {
      setDistributionAccount(collection);
    }
  }

  /**
   * Add a com.poesys.accounting.db.account.IDistributionAccount object to the DistributionAccount collection. The method
   * loads the collection if it is not already in memory.
   *
   * add method #1 (collection property)
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @param object the com.poesys.accounting.db.account.IDistributionAccount object to add to the collection
   */
  public void addDistributionAccountDistributionAccount(com.poesys.accounting.db.account.IDistributionAccount object) {
    ((CapitalEntity)dto).addDistributionAccountDistributionAccount(object);
  }

  /**
   * Create a CapitalEntityProxy. The concrete subclass must call this constructor.
   *
   * @param dto the DTO to proxy
   */
  public AbstractCapitalEntityProxy(CapitalEntity dto) {
    super(dto);

    // Setter arrays
    readObjectSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    readObjectSetters.add(new ReadCapitalAccountSetter());
    readObjectSetters.add(new ReadDistributionAccountSetter());

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
                 + " with readObject in AbstractCapitalEntityProxy");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }

  @Override
  public boolean equals(Object obj) {
    AbstractCapitalEntityProxy otherProxy = (AbstractCapitalEntityProxy)obj;
    return ((CapitalEntity)dto).equals(otherProxy.dto);
  }

  @Override
  public int hashCode() {
    return ((CapitalEntity)dto).hashCode();
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
  public java.lang.String getCapitalEntityName() {
    return ((CapitalEntity)dto).getCapitalEntityName();
  }

  /**
   * Set the capitalEntityName from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param capitalEntityName the lazily loaded value to assign
   * @throws com.poesys.db.InvalidParametersException when the property value is null
   */
  void setCapitalEntityName(java.lang.String capitalEntityName)
      throws com.poesys.db.InvalidParametersException {
    ((CapitalEntity)dto).setCapitalEntityName(capitalEntityName);
  }

  /**
   * Get a collection of com.poesys.accounting.db.account.ICapitalAccount
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount>
   */
  public java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> getCapitalAccount() {
    return ((CapitalEntity)dto).getCapitalAccount();
  }

  /**
   * Set the capitalAccount from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param capitalAccount the lazily loaded value to assign
   */
  public void setCapitalAccount(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount)
      {
    ((CapitalEntity)dto).setCapitalAccount(capitalAccount);
  }

  /**
   * Get a collection of com.poesys.accounting.db.account.IDistributionAccount
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * Lazy: false
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount>
   */
  public java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> getDistributionAccount() {
    return ((CapitalEntity)dto).getDistributionAccount();
  }

  /**
   * Set the distributionAccount from a lazy-loading proxy, either for lazily 
   * loading the data or deserializing nested objects. The IDbDto-derived 
   * interface does not contain this method.
   *
   * @param distributionAccount the lazily loaded value to assign
   */
  public void setDistributionAccount(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount)
      {
    ((CapitalEntity)dto).setDistributionAccount(distributionAccount);
  }

  public void markChildrenDeleted() {
  	((CapitalEntity)dto).markChildrenDeleted();
  }
}