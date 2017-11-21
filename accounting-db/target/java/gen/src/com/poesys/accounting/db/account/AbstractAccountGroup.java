/**
 * Copyright 2016 Poesys Associates. All rights reserved.
 */
// Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
// Template: AbstractDbDto.vsl

package com.poesys.accounting.db.account;


import org.apache.log4j.Logger;

import com.poesys.db.pk.IPrimaryKey;

import com.poesys.db.dto.AbstractDto;


/**
 * <p>
 * A data-access layer data-transfer object for the AccountGroup. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * <p>
 * A named group of fiscal-year accounts, grouping the accounts for presentation
 * and aggregation in financial statements for the fiscal year
 * </p>
 * <p>
 * Stereotypes:
 * </p>
 * <ul>
 *     <li>CompositeKey</li>
 *     <li>Persistent</li>
 * </ul>
 * <p>
 * Class is read/write: true
 * </p>
 *
 * @author Poesys/DB Cartridge
 */
public abstract class AbstractAccountGroup extends AbstractDto implements IAccountGroup {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractAccountGroup.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractAccountGroup> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractAccountGroup>();

  // Setter strategy nested classes for single-object associations
  
  /**
   * Nested class that manages the type association data
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   *
   * @author Poesys/DB Cartridge
   */
  private class QueryTypeSetter extends com.poesys.db.dto.AbstractObjectSetter<com.poesys.accounting.db.account.IAccountType> {
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
    protected IPrimaryKey getKey() {
      return typeKey;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IAccountType> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryAccountType();
    }

    @Override
    protected void set(com.poesys.accounting.db.account.IAccountType dto) {
      // No status change, this is just filling in the object data.
      type = dto;
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated type is not null
      return type != null;
    }
  }

  /**
   * Post-process setter for post-processing nested object property type.
   */
  private class PostProcessTypeSetter 
      extends com.poesys.db.dto.AbstractPostProcessSetter {
    // Property type source: AddToOneAssociationRequiredObjectProperties
    private static final long serialVersionUID = 1L;

    /**
     * Create a PostProcessTypeSetter object.
     */
    public PostProcessTypeSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.AccountType.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.ArrayList<com.poesys.db.dto.IDbDto> array =
        new java.util.ArrayList<com.poesys.db.dto.IDbDto>(1);
      if (type != null) {
        array.add(type);
      }
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = array;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }
  }

  /**
   * Insert setter for inserting nested object property type.
   */
  private class InsertTypeSetter 
      extends com.poesys.db.dto.AbstractInsertSetter {
    // Property type source: AddToOneAssociationRequiredObjectProperties
    private static final long serialVersionUID = 1L;

    /**
     * Create an InsertTypeSetter object.
     */
    public InsertTypeSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.AccountType.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.ArrayList<com.poesys.db.dto.IDbDto> array =
        new java.util.ArrayList<com.poesys.db.dto.IDbDto>(1);
      array.add(type);
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = array;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      // Key type: NaturalKey
      return true;
    }
  }

  /**
   * Setter for processing added type, updated type, and 
   * deleted type. 
   */
   
  private class UpdateTypeSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObject<com.poesys.accounting.db.account.IAccountType> {
    private static final long serialVersionUID = 1L;

    /**
     * Create an UpdateTypeSetter object.
     */
    public UpdateTypeSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected void doChanged(com.poesys.accounting.db.account.IAccountType dto) {
        // type source: AddToOneAssociationRequiredObjectProperties
        // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccountType> factory = 
        manager.getFactory(com.poesys.accounting.db.account.AccountType.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdate<com.poesys.accounting.db.account.IAccountType> updater = 
        factory.getUpdate(new com.poesys.accounting.db.account.sql.UpdateAccountType());

      updater.update(dto);
      // Complete the update by setting the DTO to EXISTING status.
      dto.setExisting();
    }
    
    @Override
    protected void doDeleted(com.poesys.accounting.db.account.IAccountType dto) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccountType> factory = 
        manager.getFactory(com.poesys.accounting.db.account.AccountType.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDelete<com.poesys.accounting.db.account.IAccountType> dao = 
        factory.getDelete(new com.poesys.accounting.db.account.sql.DeleteAccountType());
      dao.delete(dto);
    }

    @Override
    protected void doNew(com.poesys.accounting.db.account.IAccountType dto) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccountType> factory = 
        manager.getFactory(com.poesys.accounting.db.account.AccountType.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsert<com.poesys.accounting.db.account.IAccountType> inserter =
        factory.getInsert(new com.poesys.accounting.db.account.sql.InsertAccountType(), createKey());


      // Insert the superclass objects from the root down. Suppress nested
      // inserts for the superclasses, wait until the concrete class. Also set 
      // pre-insert suppression off to have the root insert linked, to-one class
      // objects.
      dto.setSuppressNestedInserts(true);
      dto.setSuppressNestedPreInserts(false);

      // Suppress inserts in concrete class.
      dto.setSuppressNestedPreInserts(true);
      
      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      dto.setSuppressNestedInserts(false);
      inserter.insert(dto);
    }

    @Override
    protected com.poesys.accounting.db.account.IAccountType getDto() {
      return type;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.AccountType.class.getName();
    }

    @Override
    protected boolean createKey() {
      // Key type: NaturalKey
      return true;
    }
  }

  /**
   * Foreign key object used by QueryTypeSetter nested class to query object
   */
  private IPrimaryKey typeKey;
  
  /**
   * Set the foreign key typeKey. This has package access to enable
   * the subsystem factory getData method to call this method to set the key
   * by creating it from the queried result set.
   *
   * @param typeKey the foreign key
   */
  void setTypeKey(IPrimaryKey typeKey) {
    this.typeKey = typeKey;
  }

  // Setter strategy nested classes for multiple-object associations

  /**
   * Query setter for querying nested accounts
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryAccountsByAccountGroup
   */
  private class QueryAccountsSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.accounting.db.account.IFiscalYearAccount, IAccountGroup, java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryAccountsSetter object.
     */
    public QueryAccountsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.FiscalYearAccount.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected IAccountGroup getParametersDto() {
      return AbstractAccountGroup.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.accounting.db.account.IFiscalYearAccount, IAccountGroup> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryAccountsByAccountGroup();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> list) {
      // No status change; this is just filling in the object data.
      accounts = list;
      // Add the primary keys to the serialized key list if there are any.
      if (accounts != null) {
        if (accountsKeys != null) {
          accountsKeys.clear();
        } else {
          accountsKeys = new java.util.ArrayList<IPrimaryKey>();
        }
        for (com.poesys.db.dto.IDbDto object : accounts) {
          accountsKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated accounts list is not null
      return accounts != null;
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
      return accounts;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return accountsKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IFiscalYearAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryFiscalYearAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> collection) {
     accounts = collection;
    }
  }

  /**
   * Post-processing setter for post-processing nested to-many association accounts.
   */
  private class PostProcessAccountsSetter extends com.poesys.db.dto.AbstractPostProcessSetter {
    private static final long serialVersionUID = 1L;
    
    // Association accounts source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an PostProcessAccountsSetter object.
     */
    public PostProcessAccountsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.FiscalYearAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = accounts;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }
  }

  /**
   * Insert setter for inserting nested to-many association accounts.
   *
   * @see com.poesys.accounting.db.account.sql.InsertFiscalYearAccount
   */
  private class InsertAccountsSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association accounts source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an InsertAccountsSetter object.
     */
    public InsertAccountsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.FiscalYearAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = accounts;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added accounts, updated accounts, and 
   * deleted accounts. 
   */

  private class UpdateAccountsSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.accounting.db.account.IFiscalYearAccount, java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateAccountsSetter object.
     */
    public UpdateAccountsSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected void doChanged(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> dtos) {
      // accounts source: TransformToProperty + AddToManyAssociationCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IFiscalYearAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.FiscalYearAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.accounting.db.account.IFiscalYearAccount> updater =
        factory.getUpdateBatch(new com.poesys.accounting.db.account.sql.UpdateFiscalYearAccount());

      // Update the object of the leaf class.
      updater.update(dtos, dtos.size() / 2);
      // Complete the update by setting the DTOs to EXISTING status.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        if (dto.getStatus() == Status.CHANGED) {
          dto.setExisting();
        }
      }
    }
    
    @Override
    protected void doDeleted(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IFiscalYearAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.FiscalYearAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.accounting.db.account.IFiscalYearAccount> dao = 
        factory.getDeleteBatch(new com.poesys.accounting.db.account.sql.DeleteFiscalYearAccount());
      dao.delete(dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);


      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IFiscalYearAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.FiscalYearAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.accounting.db.account.IFiscalYearAccount> inserter =
        factory.getInsertBatch(new com.poesys.accounting.db.account.sql.InsertFiscalYearAccount());


      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> getDtos() {
      return accounts;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.FiscalYearAccount.class.getName();
    }
  }

  /**
   * Add com.poesys.accounting.db.account.IFiscalYearAccount object to accounts collection.
   * 
   * @param object the com.poesys.accounting.db.account.IFiscalYearAccount object
   */
  public void addAccountsFiscalYearAccount(com.poesys.accounting.db.account.IFiscalYearAccount object) {
    if (accounts == null) {
      // Association not yet created, create it.
      accounts = new java.util.ArrayList<com.poesys.accounting.db.account.IFiscalYearAccount>();
    }
    accounts.add(object);
    // Add the primary key to the primary key array.
    if (accountsKeys != null) {
      accountsKeys.clear();
    } else {
      accountsKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    accountsKeys.add(object.getPrimaryKey());
  }
   
  /**
   * Create an empty AccountGroup for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractAccountGroup() {
    abstractClass = false;
    createInserters();

    // Setter arrays (create if null)
    if (querySetters == null) {
      querySetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertSetters == null) {
      insertSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (preSetters == null) {
      preSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (postSetters == null) {
      postSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (postProcessSetters == null) {
      postProcessSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (readObjectSetters == null) {
      readObjectSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }

    // Add the setters for the type property.
    querySetters.add(new QueryTypeSetter());
    preSetters.add(new InsertTypeSetter());
    postSetters.add(new UpdateTypeSetter());
    postProcessSetters.add(new PostProcessTypeSetter());
    
    // Add the many-to-many collection setters for the accounts property.
    querySetters.add(new QueryAccountsSetter());
    readObjectSetters.add(new ReadAccountsSetter());
    insertSetters.add(new InsertAccountsSetter());
    postSetters.add(new UpdateAccountsSetter());
    postProcessSetters.add(new PostProcessAccountsSetter());
  }

  /**
   * Create a AccountGroup. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the AccountGroup
   * @param accountType composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * @param orderNumber the relative position of the account group in the ordered list of groups
belonging to the account type
   * @param groupName the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
Payable, Tax-Related Expenses
   */
  public AbstractAccountGroup(IPrimaryKey key, java.lang.String accountType, java.lang.Integer orderNumber, java.lang.String groupName) {
    this.key = key;

    this.accountType = accountType;

    if (accountType == null) {
      throw new com.poesys.db.InvalidParametersException("accountType is required for " + key.getValueList());
    }
    
    this.orderNumber = orderNumber;

    if (orderNumber == null) {
      throw new com.poesys.db.InvalidParametersException("orderNumber is required for " + key.getValueList());
    }
    
    this.groupName = groupName;

    if (groupName == null) {
      throw new com.poesys.db.InvalidParametersException("groupName is required for " + key.getValueList());
    }
    
    // Setter arrays (create if null)
    if (querySetters == null) {
      querySetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertQuerySetters == null) {
      insertQuerySetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (insertSetters == null) {
      insertSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (preSetters == null) {
      preSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (postSetters == null) {
      postSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (postProcessSetters == null) {
      postProcessSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    if (readObjectSetters == null) {
      readObjectSetters = new java.util.ArrayList<com.poesys.db.dto.ISet>();
    }
    
    // Add the setters for the type property.
    querySetters.add(new QueryTypeSetter());
    // Set the object property primary key with a factory method.
    typeKey = com.poesys.accounting.db.account.AccountFactory.getAccountTypePrimaryKey(accountType);
    insertSetters.add(new InsertTypeSetter());
    preSetters.add(new InsertTypeSetter());
    postSetters.add(new UpdateTypeSetter());
    postProcessSetters.add(new PostProcessTypeSetter());
    
    // Add a setter to instantiate the required type object before insert.
    insertQuerySetters.add(new QueryTypeSetter());
    
    // Add the many-to-many collection setters for the accounts property.
    querySetters.add(new QueryAccountsSetter());
    readObjectSetters.add(new ReadAccountsSetter());
    insertSetters.add(new InsertAccountsSetter());
    postSetters.add(new UpdateAccountsSetter());
    postProcessSetters.add(new PostProcessAccountsSetter());
    abstractClass = false;
    createInserters();
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
                 + " with readObject in AbstractAccountGroup");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }
   
  @Override
  public boolean equals(Object arg0) {
    IAccountGroup other = (IAccountGroup)arg0;
    return other.getPrimaryKey().equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    IAccountGroup other = (IAccountGroup)o;
    // Sort on the key. Same semantics as equals and hashCode().
    return other.getPrimaryKey().compareTo(key);
  }
  
  @Override
  public String getSubsystem() {
    return "com.poesys.accounting.db.account";
  }

  @Override
  public void markChildrenDeleted() {
  }

  @Override
  public IPrimaryKey getPrimaryKey() {
    return key;
  }
  
  /**
   * Nested property accountType
   *
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   *
   * Added by AddNaturalKeyProperties + AddParentKeyAttributes
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.String accountType;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddNaturalKeyProperties + AddParentKeyAttributes
   * 
   * @return a java.lang.String
   */

  public java.lang.String getAccountType() {
    return accountType;
  }

  /**
   * Clear the accountType data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearAccountType() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the accountType.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: false</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * Composite super-key attribute that uniquely identifies child combined with child sub-key and any other parent super-keys
   * </p>
   *
   * @param accountType the value with which to set the property
   */
  void setAccountType(java.lang.String accountType)
      throws com.poesys.db.InvalidParametersException {
    if (accountType == null) {
      throw new com.poesys.db.InvalidParametersException("accountType is required");
    }
    
    this.accountType = accountType;
    setChanged();
  }
  /**
   * Nested property orderNumber
   *
   * <p>
   * the relative position of the account group in the ordered list of groups
   * belonging to the account type
   * </p>
   *
   * Added by AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.Integer orderNumber;
  
  /**
   * Get an object of java.lang.Integer.
   *
   * Source: AddExplicitSubKeyProperties + addNaturalSubkeyOnClass
   * 
   * @return a java.lang.Integer
   */

  public java.lang.Integer getOrderNumber() {
    return orderNumber;
  }

  /**
   * Clear the orderNumber data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearOrderNumber() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the orderNumber.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: false</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * the relative position of the account group in the ordered list of groups
   * belonging to the account type
   * </p>
   *
   * @param orderNumber the value with which to set the property
   */
  void setOrderNumber(java.lang.Integer orderNumber)
      throws com.poesys.db.InvalidParametersException {
    if (orderNumber == null) {
      throw new com.poesys.db.InvalidParametersException("orderNumber is required");
    }
    
    this.orderNumber = orderNumber;
    setChanged();
  }
  /**
   * Nested property groupName
   *
   * <p>
   * the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
   * Payable, Tax-Related Expenses
   * </p>
   *
   * Added by AddLocalAttributeProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private java.lang.String groupName;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddLocalAttributeProperties
   * 
   * @return a java.lang.String
   */

  public java.lang.String getGroupName() {
    return groupName;
  }

  /**
   * Clear the groupName data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearGroupName() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the groupName.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * the name of the group of accounts; examples: Cash, Fixed Assets, Accounts
   * Payable, Tax-Related Expenses
   * </p>
   *
   * @param groupName the value with which to set the property
   */
  public void setGroupName(java.lang.String groupName)
      throws com.poesys.db.InvalidParametersException {
    if (groupName == null) {
      throw new com.poesys.db.InvalidParametersException("groupName is required");
    }
    
    this.groupName = groupName;
    setChanged();
  }
  /**
   * Nested property type
   *
   * 
   *
   * Added by AddToOneAssociationRequiredObjectProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  private com.poesys.accounting.db.account.IAccountType type;
  
  /**
   * Get an object of com.poesys.accounting.db.account.IAccountType.
   *
   * Source: AddToOneAssociationRequiredObjectProperties
   * 
   * @return a com.poesys.accounting.db.account.IAccountType
   */

  public com.poesys.accounting.db.account.IAccountType getType() {
    return type;
  }

  /**
   * Clear the type data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearType() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the type.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * 
   *
   * @param type the value with which to set the property
   */
  public void setType(com.poesys.accounting.db.account.IAccountType type)
      throws com.poesys.db.InvalidParametersException {
    if (type == null) {
      throw new com.poesys.db.InvalidParametersException("type is required");
    }
    
    this.type = type;
    setChanged();
  }
  /**
   * Nested property accounts
   *
   * <p>
   * the set of accounts in the group
   * </p>
   *
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> accounts;
  // Ordered list of keys of the objects in the accounts list
  transient java.util.List<com.poesys.db.pk.IPrimaryKey> accountsKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.accounting.db.account.IFiscalYearAccount.
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount>
   */

  public java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> getAccounts() {
    return accounts;
  }

  /**
   * Clear the accounts data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearAccounts() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the accounts.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: true</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * <p>
   * the set of accounts in the group
   * </p>
   *
   * @param accounts the value with which to set the property
   */
  public  void setAccounts(java.util.Collection<com.poesys.accounting.db.account.IFiscalYearAccount> accounts) {
    this.accounts = accounts;
    // Add the primary keys of the new collection to the serialized key list.
    if (accountsKeys != null) {
      accountsKeys.clear();
    } else {
      // Allocate a new, empty list of primary keys.
      accountsKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    if (accounts != null) {
    if (accountsKeys != null) {
      accountsKeys.clear();
    } else {
      accountsKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
      for (com.poesys.db.dto.IDbDto object : accounts) {
        accountsKeys.add(object.getPrimaryKey());
      }
    }
    setChanged();
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event) {

    // Cascade delete to accounts.
    if (event == com.poesys.db.dao.DataEvent.MARKED_DELETED &&
        accounts != null) {
      // Mark accounts association object cascade-deleted.
      outer: for (com.poesys.accounting.db.account.IFiscalYearAccount value : accounts) {
        com.poesys.db.pk.AssociationPrimaryKey keys = 
          (com.poesys.db.pk.AssociationPrimaryKey) value.getPrimaryKey();
        for (com.poesys.db.pk.IPrimaryKey key : keys.getKeyListCopy()) {
          if (key.equals(subject.getPrimaryKey())) {
            value.cascadeDelete();
            break outer;
          }
        }
      }
    }
  }

  /**
   * Create the inserters for the AccountGroup and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccountGroup> accountGroupFactory =
      manager.getFactory("com.poesys.accounting.db.account.AccountGroup",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<IAccountGroup> sql =
      new com.poesys.accounting.db.account.sql.InsertAccountGroup();
    com.poesys.db.dao.insert.IInsert<IAccountGroup> inserter =
      accountGroupFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}