REM *** Account Subsystem ***

/* Account type is the class name of the child of Account; leafType is the actual class of the object */

CREATE SEQUENCE AccountId NOCACHE;

CREATE CLUSTER AccountCluster
   (accountId NUMBER(38)) SIZE 512 PCTFREE 5 PCTUSED 95;
CREATE INDEX AccountClusterIndex ON CLUSTER AccountCluster;

CREATE TABLE Account (
  accountId NUMBER(38) NOT NULL CONSTRAINT Ac_PK PRIMARY KEY,
  name VARCHAR2(100) NOT NULL,
  description VARCHAR2(500) NOT NULL,
  defaultAccountNumber NUMBER(3) NOT NULL,
  defaultSubAccountNumber NUMBER(3) DEFAULT 0 NOT NULL,
  debitDefault NUMBER(1) DEFAULT 1 NOT NULL,
  accountType VARCHAR2(100) NOT NULL,
  leafType VARCHAR2(100) NOT NULL,
  active NUMBER(1) DEFAULT 1 NOT NULL
) CLUSTER AccountCluster(accountId);

CREATE TABLE FiscalYear (
  year NUMBER(4) NOT NULL CONSTRAINT FsY_PK PRIMARY KEY,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL
);

CREATE TABLE FiscalYearAccount (
  accountId NUMBER(38) NOT NULL
    CONSTRAINT FsYA_Ac_FK REFERENCES Account ON DELETE CASCADE,
  year NUMBER(4) NOT NULL
    CONSTRAINT FsYA_FsY_FK REFERENCES FiscalYear ON DELETE CASCADE,
  CONSTRAINT FsYA_PK PRIMARY KEY (accountId, year),
  accountNumber NUMBER(3) NOT NULL,
  subaccountNumber NUMBER(3) NOT NULL,
  name VARCHAR2(100) NOT NULL,
  description VARCHAR2(500) NOT NULL,
  transferBalanceTargetAccountId NUMBER(38),
  transferBalanceTargetYear NUMBER(4),
  CONSTRAINT FsYA_FsYA_TrB_FK FOREIGN KEY (transferBalanceTargetAccountId, transferBalanceTargetYear)
    REFERENCES FiscalYearAccount (accountId, year)
) CLUSTER AccountCluster(accountId);

REM *** Transaction Subsystem ***

CREATE SEQUENCE TransactionId NOCACHE;

CREATE CLUSTER TransactionCluster
   (transactionId NUMBER(38)) SIZE 256 PCTFREE 5 PCTUSED 95;
CREATE INDEX TransactionClusterIndex ON CLUSTER TransactionCluster;

CREATE TABLE Transaction (
  transactionId NUMBER(38) NOT NULL CONSTRAINT Tr_PK PRIMARY KEY,
  description VARCHAR2(4000) NOT NULL,
  transactionDate DATE NOT NULL,
  checked NUMBER(1) DEFAULT 0 NOT NULL CONSTRAINT TrCh_CK CHECK (checked IN (1, 0)),
  balance NUMBER(1) DEFAULT 0 NOT NULL CONSTRAINT TrBl_CK CHECK (balance IN (1, 0))
) CLUSTER TransactionCluster(transactionId);

CREATE TABLE Item (
  transactionId NUMBER(38) NOT NULL
    CONSTRAINT It_Tr_FK REFERENCES Transaction ON DELETE CASCADE,
  accountId NUMBER(38) NOT NULL,
  year NUMBER(4) NOT NULL,
  CONSTRAINT It_FsYA_FK FOREIGN KEY (accountId, year) REFERENCES FiscalYearAccount(accountId, year),
  amount NUMBER(38,2) DEFAULT 0 NOT NULL,
  debit NUMBER(1) DEFAULT 1 NOT NULL CONSTRAINT ItDb_CK CHECK (debit IN (1, 0)),
  checked NUMBER(1) DEFAULT 0 NOT NULL CONSTRAINT ItCh_CK CHECK (checked IN (1, 0)),
  CONSTRAINT It_PK PRIMARY KEY (transactionId, accountId, year)
) CLUSTER TransactionCluster(transactionId);

CREATE TABLE ReceivableTransactionItem (
  transactionId NUMBER(38) NOT NULL,
  accountId NUMBER(38) NOT NULL,
  year NUMBER(4) NOT NULL,
  CONSTRAINT RcTI_It_FK FOREIGN KEY (transactionId, accountId, year) 
    REFERENCES Item(transactionId, accountId, year) ON DELETE CASCADE,
  CONSTRAINT RcTI_PK PRIMARY KEY (transactionId, accountId, year),
  receivableAmount NUMBER(38,2) NOT NULL,
  writeoffAmount NUMBER(38,2) DEFAULT 0 NOT NULL
) CLUSTER TransactionCluster(transactionId);
