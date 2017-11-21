--
-- Copyright 2016 Poesys Associates. All rights reserved.
--
-- Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
-- Template: SubsystemMySqlSchemaTables.vsl

-- SQL schema for account subsystem

CREATE TABLE FiscalYear (
  year INT(4) NOT NULL,  
  startDate TIMESTAMP NOT NULL,  
  endDate TIMESTAMP NOT NULL,  
  CONSTRAINT FiscalYearPK PRIMARY KEY (year)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE FiscalYearAccount (
  accountName VARCHAR(100) NOT NULL,  
  entityName VARCHAR(500) NOT NULL,  
  year INT(4) NOT NULL,  
  orderNumber INT NOT NULL,  
  accountType VARCHAR(15) NOT NULL,  
  groupOrderNumber INT NOT NULL,  
  CONSTRAINT FiscalYearAccountPK PRIMARY KEY (accountName, entityName, year)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Account (
  entityName VARCHAR(500) NOT NULL,  
  accountName VARCHAR(100) NOT NULL,  
  description VARCHAR(500) NOT NULL,  
  debitDefault BOOLEAN NOT NULL,  
  active BOOLEAN NOT NULL,  
  CONSTRAINT AccountPK PRIMARY KEY (accountName, entityName)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE AccountGroup (
  accountType VARCHAR(15) NOT NULL,  
  orderNumber INT NOT NULL,  
  groupName VARCHAR(100) NOT NULL,  
  CONSTRAINT AccountGroupPK PRIMARY KEY (accountType, orderNumber)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Entity (
  entityName VARCHAR(500) NOT NULL,  
  CONSTRAINT EntityPK PRIMARY KEY (entityName)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE CapitalEntity (
  capitalEntityName VARCHAR(500) NOT NULL,  
  CONSTRAINT CapitalEntityPK PRIMARY KEY (capitalEntityName)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE AccountType (
  accountType VARCHAR(15) NOT NULL,  
  CONSTRAINT AccountTypePK PRIMARY KEY (accountType)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE CapitalAccount (
  accountName VARCHAR(100) NOT NULL,  
  entityName VARCHAR(500) NOT NULL,  
  ownership NUMERIC NOT NULL,  
  capitalEntityName VARCHAR(500) NOT NULL,  
  CONSTRAINT CapitalAccountPK PRIMARY KEY (accountName, entityName)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE DistributionAccount (
  accountName VARCHAR(100) NOT NULL,  
  entityName VARCHAR(500) NOT NULL,  
  capitalEntityName VARCHAR(500) NOT NULL,  
  CONSTRAINT DistributionAccountPK PRIMARY KEY (accountName, entityName)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE SimpleAccount (
  accountName VARCHAR(100) NOT NULL,  
  entityName VARCHAR(500) NOT NULL,  
  receivable BOOLEAN NOT NULL,  
  CONSTRAINT SimpleAccountPK PRIMARY KEY (accountName, entityName)
) ENGINE=InnoDB CHARSET=utf8;

