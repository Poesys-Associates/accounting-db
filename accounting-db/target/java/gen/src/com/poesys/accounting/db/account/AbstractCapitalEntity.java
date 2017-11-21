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
 * A data-access layer data-transfer object for the CapitalEntity. This class
 * is an abstract class that contains AndroMDA generated code; change nothing
 * in this class. Instead, override any methods in the concrete subclass
 * generated in the same package. AndroMDA will overwrite this class each time
 * you run it but will never overwrite the concrete subclass.
 * </p>
 * 
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
public abstract class AbstractCapitalEntity extends AbstractDto implements ICapitalEntity {
  /** Default serial version UID for the Serializable DTO */
  private static final long serialVersionUID = 1L;

  /** Logger for this class */
  private static final Logger logger = Logger.getLogger(AbstractCapitalEntity.class);

  /** the deserializer used by the readObject method */
  private static final com.poesys.db.dto.Deserializer<AbstractCapitalEntity> deserializer =
    new com.poesys.db.dto.Deserializer<AbstractCapitalEntity>();

  
  // Setter strategy nested classes for multiple-object associations

  /**
   * Query setter for querying nested capitalAccount
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryCapitalAccountByCapitalEntity
   */
  private class QueryCapitalAccountSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.accounting.db.account.ICapitalAccount, ICapitalEntity, java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryCapitalAccountSetter object.
     */
    public QueryCapitalAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.CapitalAccount.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ICapitalEntity getParametersDto() {
      return AbstractCapitalEntity.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.accounting.db.account.ICapitalAccount, ICapitalEntity> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryCapitalAccountByCapitalEntity();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> list) {
      // No status change; this is just filling in the object data.
      capitalAccount = list;
      // Add the primary keys to the serialized key list if there are any.
      if (capitalAccount != null) {
        if (capitalAccountKeys != null) {
          capitalAccountKeys.clear();
        } else {
          capitalAccountKeys = new java.util.ArrayList<IPrimaryKey>();
        }
        for (com.poesys.db.dto.IDbDto object : capitalAccount) {
          capitalAccountKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated capitalAccount list is not null
      return capitalAccount != null;
    }
  }

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
      return capitalAccount;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return capitalAccountKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.ICapitalAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryCapitalAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> collection) {
     capitalAccount = collection;
    }
  }

  /**
   * Post-processing setter for post-processing nested to-many association capitalAccount.
   */
  private class PostProcessCapitalAccountSetter extends com.poesys.db.dto.AbstractPostProcessSetter {
    private static final long serialVersionUID = 1L;
    
    // Association capitalAccount source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an PostProcessCapitalAccountSetter object.
     */
    public PostProcessCapitalAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.CapitalAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = capitalAccount;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }
  }

  /**
   * Insert setter for inserting nested to-many association capitalAccount.
   *
   * @see com.poesys.accounting.db.account.sql.InsertCapitalAccount
   */
  private class InsertCapitalAccountSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association capitalAccount source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an InsertCapitalAccountSetter object.
     */
    public InsertCapitalAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.CapitalAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = capitalAccount;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added capitalAccount, updated capitalAccount, and 
   * deleted capitalAccount. 
   */

  private class UpdateCapitalAccountSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.accounting.db.account.ICapitalAccount, java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateCapitalAccountSetter object.
     */
    public UpdateCapitalAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected void doChanged(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> dtos) {
      // capitalAccount source: TransformToProperty + AddToManyAssociationCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      // Create superclass updaters.

      // Account
      final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccount> accountFactory =
        manager.getFactory("com.poesys.accounting.db.account.Account", "com.poesys.accounting.db.account", 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.accounting.db.account.IAccount> accountUpdater =
        accountFactory.getUpdateBatch(new com.poesys.accounting.db.account.sql.UpdateAccount());
      java.util.Collection<com.poesys.accounting.db.account.IAccount> accountDtos = convertDtoList(dtos);
    

      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.ICapitalAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.CapitalAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.accounting.db.account.ICapitalAccount> updater =
        factory.getUpdateBatch(new com.poesys.accounting.db.account.sql.UpdateCapitalAccount());

      // Update the superclass objects from the root down.
      accountUpdater.update(accountDtos, accountDtos.size() / 2);

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
    protected void doDeleted(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.ICapitalAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.CapitalAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.accounting.db.account.ICapitalAccount> dao = 
        factory.getDeleteBatch(new com.poesys.accounting.db.account.sql.DeleteCapitalAccount());
      dao.delete(dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

    // Create superclass inserters.

    // Account
    final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccount> accountFactory =
      manager.getFactory("com.poesys.accounting.db.account.Account", "com.poesys.accounting.db.account", 2147483647);
    // Key type: CompositeKey, Inherited Key type: None
    com.poesys.db.dao.insert.IInsertBatch<com.poesys.accounting.db.account.IAccount> accountInserter =
      accountFactory.getInsertBatch(new com.poesys.accounting.db.account.sql.InsertAccount());
    java.util.Collection<com.poesys.accounting.db.account.IAccount> accountDtos = convertDtoList(dtos);
    

      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.ICapitalAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.CapitalAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.accounting.db.account.ICapitalAccount> inserter =
        factory.getInsertBatch(new com.poesys.accounting.db.account.sql.InsertCapitalAccount());


      // Insert the superclass objects from the root down. Suppress nested
      // inserts for the superclasses, wait until the concrete class. Also set 
      // pre-insert suppression off to have the root insert linked, to-one class
      // objects.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(true);
        dto.setSuppressNestedPreInserts(false);
      }
      accountInserter.insert(accountDtos, accountDtos.size() / 2);
      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> getDtos() {
      return capitalAccount;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.CapitalAccount.class.getName();
    }
  }

  /**
   * Add com.poesys.accounting.db.account.ICapitalAccount object to capitalAccount collection.
   * 
   * @param object the com.poesys.accounting.db.account.ICapitalAccount object
   */
  public void addCapitalAccountCapitalAccount(com.poesys.accounting.db.account.ICapitalAccount object) {
    if (capitalAccount == null) {
      // Association not yet created, create it.
      capitalAccount = new java.util.ArrayList<com.poesys.accounting.db.account.ICapitalAccount>();
    }
    capitalAccount.add(object);
    // Add the primary key to the primary key array.
    if (capitalAccountKeys != null) {
      capitalAccountKeys.clear();
    } else {
      capitalAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    capitalAccountKeys.add(object.getPrimaryKey());
  }
   
  /**
   * Query setter for querying nested distributionAccount
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   *
   * @see com.poesys.accounting.db.account.sql.QueryDistributionAccountByCapitalEntity
   */
  private class QueryDistributionAccountSetter 
      extends com.poesys.db.dto.AbstractListSetter<com.poesys.accounting.db.account.IDistributionAccount, ICapitalEntity, java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int FETCH_SIZE = 10;

    /**
     * Create a QueryDistributionAccountSetter object.
     */
    public QueryDistributionAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.DistributionAccount.class.getName();
    }

    @Override
    protected int getFetchSize() {
      return FETCH_SIZE;
    }

    @Override
    protected ICapitalEntity getParametersDto() {
      return AbstractCapitalEntity.this;
    }

    @Override
    protected com.poesys.db.dao.query.IParameterizedQuerySql<com.poesys.accounting.db.account.IDistributionAccount, ICapitalEntity> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryDistributionAccountByCapitalEntity();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> list) {
      // No status change; this is just filling in the object data.
      distributionAccount = list;
      // Add the primary keys to the serialized key list if there are any.
      if (distributionAccount != null) {
        if (distributionAccountKeys != null) {
          distributionAccountKeys.clear();
        } else {
          distributionAccountKeys = new java.util.ArrayList<IPrimaryKey>();
        }
        for (com.poesys.db.dto.IDbDto object : distributionAccount) {
          distributionAccountKeys.add(object.getPrimaryKey());
        }
      }
    }

    @Override
    public boolean isSet() {
      // Object is set if the associated distributionAccount list is not null
      return distributionAccount != null;
    }
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
      return distributionAccount;
    }

    @Override
    protected java.util.List<IPrimaryKey> getPrimaryKeys() {
      return distributionAccountKeys;
    }

    @Override
    protected com.poesys.db.dao.query.IKeyQuerySql<com.poesys.accounting.db.account.IDistributionAccount> getSql() {
      return new com.poesys.accounting.db.account.sql.QueryDistributionAccount();
    }

    @Override
    protected void set(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> collection) {
     distributionAccount = collection;
    }
  }

  /**
   * Post-processing setter for post-processing nested to-many association distributionAccount.
   */
  private class PostProcessDistributionAccountSetter extends com.poesys.db.dto.AbstractPostProcessSetter {
    private static final long serialVersionUID = 1L;
    
    // Association distributionAccount source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an PostProcessDistributionAccountSetter object.
     */
    public PostProcessDistributionAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.DistributionAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = distributionAccount;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }
  }

  /**
   * Insert setter for inserting nested to-many association distributionAccount.
   *
   * @see com.poesys.accounting.db.account.sql.InsertDistributionAccount
   */
  private class InsertDistributionAccountSetter extends com.poesys.db.dto.AbstractInsertSetter {
    private static final long serialVersionUID = 1L;
    
    // Association distributionAccount source: TransformToProperty + AddToManyAssociationCollectionProperties

    /**
     * Create an InsertDistributionAccountSetter object.
     */
    public InsertDistributionAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.DistributionAccount.class.getName();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected java.util.Collection<com.poesys.db.dto.IDbDto> getDtos() {
      java.util.Collection<? extends com.poesys.db.dto.IDbDto> dtos = distributionAccount;
      return (java.util.Collection<com.poesys.db.dto.IDbDto>)dtos;
    }

    @Override
    protected boolean createKey() {
      return true;
    }
  }

  /**
   * Setter for processing added distributionAccount, updated distributionAccount, and 
   * deleted distributionAccount. 
   */

  private class UpdateDistributionAccountSetter 
      extends com.poesys.db.dto.AbstractProcessNestedObjects<com.poesys.accounting.db.account.IDistributionAccount, java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount>> {
    private static final long serialVersionUID = 1L;
    private static final int BATCH_SIZE = 100;

    /**
     * Create an UpdateDistributionAccountSetter object.
     */
    public UpdateDistributionAccountSetter() {
      super("com.poesys.accounting.db.account", 2147483647);
    }

    @Override
    protected void doChanged(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> dtos) {
      // distributionAccount source: TransformToProperty + AddToManyAssociationCollectionProperties
      // Immutable: false
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

      // Create superclass updaters.

      // Account
      final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccount> accountFactory =
        manager.getFactory("com.poesys.accounting.db.account.Account", "com.poesys.accounting.db.account", 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.accounting.db.account.IAccount> accountUpdater =
        accountFactory.getUpdateBatch(new com.poesys.accounting.db.account.sql.UpdateAccount());
      java.util.Collection<com.poesys.accounting.db.account.IAccount> accountDtos = convertDtoList(dtos);
    

      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IDistributionAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.DistributionAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.update.IUpdateBatch<com.poesys.accounting.db.account.IDistributionAccount> updater =
        factory.getUpdateBatch(new com.poesys.accounting.db.account.sql.UpdateDistributionAccount());

      // Update the superclass objects from the root down.
      accountUpdater.update(accountDtos, accountDtos.size() / 2);

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
    protected void doDeleted(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);
      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IDistributionAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.DistributionAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.delete.IDeleteBatch<com.poesys.accounting.db.account.IDistributionAccount> dao = 
        factory.getDeleteBatch(new com.poesys.accounting.db.account.sql.DeleteDistributionAccount());
      dao.delete(dtos, BATCH_SIZE);
    }

    @Override
    protected void doNew(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> dtos) {
      com.poesys.db.dao.IDaoManager manager = 
        com.poesys.db.dao.DaoManagerFactory.getManager(subsystem);

    // Create superclass inserters.

    // Account
    final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IAccount> accountFactory =
      manager.getFactory("com.poesys.accounting.db.account.Account", "com.poesys.accounting.db.account", 2147483647);
    // Key type: CompositeKey, Inherited Key type: None
    com.poesys.db.dao.insert.IInsertBatch<com.poesys.accounting.db.account.IAccount> accountInserter =
      accountFactory.getInsertBatch(new com.poesys.accounting.db.account.sql.InsertAccount());
    java.util.Collection<com.poesys.accounting.db.account.IAccount> accountDtos = convertDtoList(dtos);
    

      com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.IDistributionAccount> factory = 
        manager.getFactory(com.poesys.accounting.db.account.DistributionAccount.class.getName(), subsystem, 2147483647);
      com.poesys.db.dao.insert.IInsertBatch<com.poesys.accounting.db.account.IDistributionAccount> inserter =
        factory.getInsertBatch(new com.poesys.accounting.db.account.sql.InsertDistributionAccount());


      // Insert the superclass objects from the root down. Suppress nested
      // inserts for the superclasses, wait until the concrete class. Also set 
      // pre-insert suppression off to have the root insert linked, to-one class
      // objects.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(true);
        dto.setSuppressNestedPreInserts(false);
      }
      accountInserter.insert(accountDtos, accountDtos.size() / 2);
      // Insert the object of the current class after enabling nested inserts,
      // which will allow connecting up linked objects to any of the inserted
      // classes.
      for (com.poesys.db.dto.IDbDto dto : dtos) {
        dto.setSuppressNestedInserts(false);
      }
      inserter.insert(dtos, dtos.size() / 2);
    }

    @Override
    protected java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> getDtos() {
      return distributionAccount;
    }
    
    @Override
    protected String getClassName() {
      return com.poesys.accounting.db.account.DistributionAccount.class.getName();
    }
  }

  /**
   * Add com.poesys.accounting.db.account.IDistributionAccount object to distributionAccount collection.
   * 
   * @param object the com.poesys.accounting.db.account.IDistributionAccount object
   */
  public void addDistributionAccountDistributionAccount(com.poesys.accounting.db.account.IDistributionAccount object) {
    if (distributionAccount == null) {
      // Association not yet created, create it.
      distributionAccount = new java.util.ArrayList<com.poesys.accounting.db.account.IDistributionAccount>();
    }
    distributionAccount.add(object);
    // Add the primary key to the primary key array.
    if (distributionAccountKeys != null) {
      distributionAccountKeys.clear();
    } else {
      distributionAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    distributionAccountKeys.add(object.getPrimaryKey());
  }
   
  /**
   * Create an empty CapitalEntity for use in building a new object. The 
   * concrete subclass must call this constructor.
   */
  public AbstractCapitalEntity() {
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
    
    // Add the many-to-many collection setters for the capitalAccount property.
    querySetters.add(new QueryCapitalAccountSetter());
    readObjectSetters.add(new ReadCapitalAccountSetter());
    insertSetters.add(new InsertCapitalAccountSetter());
    postSetters.add(new UpdateCapitalAccountSetter());
    postProcessSetters.add(new PostProcessCapitalAccountSetter());
    
    // Add the many-to-many collection setters for the distributionAccount property.
    querySetters.add(new QueryDistributionAccountSetter());
    readObjectSetters.add(new ReadDistributionAccountSetter());
    insertSetters.add(new InsertDistributionAccountSetter());
    postSetters.add(new UpdateDistributionAccountSetter());
    postProcessSetters.add(new PostProcessDistributionAccountSetter());
  }

  /**
   * Create a CapitalEntity. The concrete subclass must call this constructor.
   *
   * @param key the primary key of the CapitalEntity
   * @param capitalEntityName 
   */
  public AbstractCapitalEntity(IPrimaryKey key, java.lang.String capitalEntityName) {
    this.key = key;

    this.capitalEntityName = capitalEntityName;

    if (capitalEntityName == null) {
      throw new com.poesys.db.InvalidParametersException("capitalEntityName is required for " + key.getValueList());
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
    
    // Add the many-to-many collection setters for the capitalAccount property.
    querySetters.add(new QueryCapitalAccountSetter());
    readObjectSetters.add(new ReadCapitalAccountSetter());
    insertSetters.add(new InsertCapitalAccountSetter());
    postSetters.add(new UpdateCapitalAccountSetter());
    postProcessSetters.add(new PostProcessCapitalAccountSetter());
    
    // Add the many-to-many collection setters for the distributionAccount property.
    querySetters.add(new QueryDistributionAccountSetter());
    readObjectSetters.add(new ReadDistributionAccountSetter());
    insertSetters.add(new InsertDistributionAccountSetter());
    postSetters.add(new UpdateDistributionAccountSetter());
    postProcessSetters.add(new PostProcessDistributionAccountSetter());
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
                 + " with readObject in AbstractCapitalEntity");
    // Do the read-object deserialization.
    deserializer.doReadObject(in, this);
  }
   
  @Override
  public boolean equals(Object arg0) {
    ICapitalEntity other = (ICapitalEntity)arg0;
    return other.getPrimaryKey().equals(key);
  }

  @Override
  public int hashCode() {
    return key.hashCode();
  }

  @Override
  public int compareTo(com.poesys.db.dto.IDbDto o) {
    ICapitalEntity other = (ICapitalEntity)o;
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
   * Nested property capitalEntityName
   *
   * 
   *
   * Added by AddNaturalKeyProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: false
   * Property is lazy: false
   */
  private java.lang.String capitalEntityName;
  
  /**
   * Get an object of java.lang.String.
   *
   * Source: AddNaturalKeyProperties
   * 
   * @return a java.lang.String
   */

  public java.lang.String getCapitalEntityName() {
    return capitalEntityName;
  }

  /**
   * Clear the capitalEntityName data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearCapitalEntityName() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the capitalEntityName.
   * </p>
   * <ul>
   * <li>Read/Write DTO: true</li>
   * <li>Immutable DTO: false</li>
   * <li>Read/Write property: false</li>
   * <li>Immutable property: false</li>
   * <li>Lazy property: false (if true, proxy calls this method)</li>
   * </ul>
   * 
   *
   * @param capitalEntityName the value with which to set the property
   */
  void setCapitalEntityName(java.lang.String capitalEntityName)
      throws com.poesys.db.InvalidParametersException {
    if (capitalEntityName == null) {
      throw new com.poesys.db.InvalidParametersException("capitalEntityName is required");
    }
    
    this.capitalEntityName = capitalEntityName;
    setChanged();
  }
  /**
   * Nested property capitalAccount
   *
   * 
   *
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount;
  // Ordered list of keys of the objects in the capitalAccount list
  transient java.util.List<com.poesys.db.pk.IPrimaryKey> capitalAccountKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.accounting.db.account.ICapitalAccount.
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount>
   */

  public java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> getCapitalAccount() {
    return capitalAccount;
  }

  /**
   * Clear the capitalAccount data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearCapitalAccount() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the capitalAccount.
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
   * @param capitalAccount the value with which to set the property
   */
  public  void setCapitalAccount(java.util.Collection<com.poesys.accounting.db.account.ICapitalAccount> capitalAccount) {
    this.capitalAccount = capitalAccount;
    // Add the primary keys of the new collection to the serialized key list.
    if (capitalAccountKeys != null) {
      capitalAccountKeys.clear();
    } else {
      // Allocate a new, empty list of primary keys.
      capitalAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    if (capitalAccount != null) {
    if (capitalAccountKeys != null) {
      capitalAccountKeys.clear();
    } else {
      capitalAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
      for (com.poesys.db.dto.IDbDto object : capitalAccount) {
        capitalAccountKeys.add(object.getPrimaryKey());
      }
    }
    setChanged();
  }
  /**
   * Nested property distributionAccount
   *
   * 
   *
   * Added by TransformToProperty + AddToManyAssociationCollectionProperties
   * Class is read/write: true
   * Class is immutable: false
   * Property is read/write: true
   * Property is lazy: false
   */
  // Doesn't serialize; package access allows proxy to set on readObject()
  transient java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount;
  // Ordered list of keys of the objects in the distributionAccount list
  transient java.util.List<com.poesys.db.pk.IPrimaryKey> distributionAccountKeys = 
    new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
  
  /**
   * Get a collection of com.poesys.accounting.db.account.IDistributionAccount.
   *
   * Source: TransformToProperty + AddToManyAssociationCollectionProperties
   * 
   * @return a java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount>
   */

  public java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> getDistributionAccount() {
    return distributionAccount;
  }

  /**
   * Clear the distributionAccount data member; override in proxy if lazily loaded,
   * otherwise this method does nothing.
   */
  public void clearDistributionAccount() {
    // Override in proxy if lazily loaded; otherwise does nothing
  }

  /**
   * <p>
   * Set the distributionAccount.
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
   * @param distributionAccount the value with which to set the property
   */
  public  void setDistributionAccount(java.util.Collection<com.poesys.accounting.db.account.IDistributionAccount> distributionAccount) {
    this.distributionAccount = distributionAccount;
    // Add the primary keys of the new collection to the serialized key list.
    if (distributionAccountKeys != null) {
      distributionAccountKeys.clear();
    } else {
      // Allocate a new, empty list of primary keys.
      distributionAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
    if (distributionAccount != null) {
    if (distributionAccountKeys != null) {
      distributionAccountKeys.clear();
    } else {
      distributionAccountKeys = new java.util.ArrayList<com.poesys.db.pk.IPrimaryKey>();
    }
      for (com.poesys.db.dto.IDbDto object : distributionAccount) {
        distributionAccountKeys.add(object.getPrimaryKey());
      }
    }
    setChanged();
  }

  @Override
  public void update(com.poesys.db.dto.ISubject subject,
                     com.poesys.db.dao.DataEvent event) {
  }

  /**
   * Create the inserters for the CapitalEntity and its superclasses.
   */
  private void createInserters() {
    com.poesys.db.dao.IDaoManager manager =
      com.poesys.db.dao.DaoManagerFactory.getManager(getSubsystem());
    final com.poesys.db.dao.IDaoFactory<com.poesys.accounting.db.account.ICapitalEntity> capitalEntityFactory =
      manager.getFactory("com.poesys.accounting.db.account.CapitalEntity",
                         getSubsystem(),
                         2147483647);
    com.poesys.db.dao.insert.IInsertSql<ICapitalEntity> sql =
      new com.poesys.accounting.db.account.sql.InsertCapitalEntity();
    com.poesys.db.dao.insert.IInsert<ICapitalEntity> inserter =
      capitalEntityFactory.getInsert(sql, true);
    inserters.add(inserter);
  }
}