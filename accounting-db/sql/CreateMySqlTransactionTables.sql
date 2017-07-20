--
-- Copyright 2017 Poesys Associates. All rights reserved.
--
-- Generated by AndroMDA Poesys/DB cartridge: DO NOT EDIT DIRECTLY.
-- Template: SubsystemMySqlSchemaTables.vsl

-- SQL schema for transaction subsystem

-- Table to serve as sequence generator

CREATE TABLE Sequence (
  name varchar(30) NOT NULL PRIMARY KEY,
  sequence BIGINT NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO Sequence (name, sequence) VALUES ('transactionId', 0);

CREATE TABLE Transaction (
  transactionId BIGINT NOT NULL, -- generated by sequence
  CONSTRAINT Tr_PK PRIMARY KEY (transactionId),
  description VARCHAR(4000) NOT NULL,
  transactionDate TIMESTAMP NOT NULL,
  checked BOOLEAN NOT NULL DEFAULT 0,
  balance BOOLEAN NOT NULL DEFAULT 0
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Item (
  transactionId BIGINT NOT NULL,  
  orderNumber INT NOT NULL,  
  accountName VARCHAR(100) NOT NULL,  
  entityName VARCHAR(500) NOT NULL,  
  amount NUMERIC(10, 2) NOT NULL,  
  debit BOOLEAN NOT NULL DEFAULT 1,  
  checked BOOLEAN NOT NULL DEFAULT 0,  
  CONSTRAINT It_PK PRIMARY KEY (transactionId, orderNumber),
  CONSTRAINT ItAc_FK FOREIGN KEY (accountName, entityName) 
    REFERENCES Account(accountName, entityName) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Reimbursement (
  receivablesTransactionId BIGINT NOT NULL,  
  receivablesOrderNumber INT NOT NULL,  
  reimbursingItemsTransactionId BIGINT NOT NULL,  
  reimbursingItemsOrderNumber INT NOT NULL,  
  reimbursedAmount NUMERIC NOT NULL,  
  allocatedAmount NUMERIC NOT NULL,  
  CONSTRAINT ReimbursementPK PRIMARY KEY (receivablesTransactionId, receivablesOrderNumber, reimbursingItemsTransactionId, reimbursingItemsOrderNumber),
  CONSTRAINT Rm_It_Rc_FK FOREIGN KEY (receivablesTransactionId, receivablesOrderNumber) REFERENCES Item (transactionId, orderNumber) ON UPDATE CASCADE ON DELETE CASCADE,
  CONSTRAINT Rm_It_Rm_FK FOREIGN KEY (reimbursingItemsTransactionId, reimbursingItemsOrderNumber) REFERENCES Item (transactionId, orderNumber) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8;

-- Map old ids to new ids; not part of Accounting/DB per se
CREATE TABLE IdMap (
  year INTEGER NOT NULL,
  oldId NUMERIC(40) NOT NULL,
  newId BIGINT NOT NULL,
  CONSTRAINT IdM_PK PRIMARY KEY (year, oldId, newId),
  CONSTRAINT IdM_Tr_FK FOREIGN KEY (newId) REFERENCES Transaction(transactionId) ON UPDATE CASCADE ON DELETE CASCADE
) ENGINE=InnoDB CHARSET=utf8;

