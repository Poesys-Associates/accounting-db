REM *** Account Subsystem ***

/* Account type is the class name of the child of Account; leafType is the actual class of the object */

CREATE TABLE Sequence (
  name varchar(30) NOT NULL PRIMARY KEY,
  sequence BIGINT NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

INSERT INTO Sequence (name, sequence) VALUES ('accountId', 0);
INSERT INTO Sequence (name, sequence) VALUES ('transactionId', 0);

CREATE TABLE Account (
  accountId BIGINT NOT NULL PRIMARY KEY,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  defaultAccountNumber INTEGER(3) NOT NULL,
  defaultSubAccountNumber INTEGER(3) DEFAULT 0 NOT NULL,
  debitDefault SMALLINT DEFAULT 1 NOT NULL,
  accountType VARCHAR(100) NOT NULL,
  leafType VARCHAR(100) NOT NULL,
  active SMALLINT DEFAULT 1 NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE FiscalYear (
  year INTEGER(4) NOT NULL PRIMARY KEY,
  startDate DATE NOT NULL,
  endDate DATE NOT NULL
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE FiscalYearAccount (
  accountId BIGINT NOT NULL,
  CONSTRAINT FsYA_Ac_FK FOREIGN KEY (accountId) REFERENCES Account (accountId) ON DELETE CASCADE,
  year INTEGER(4) NOT NULL,
  CONSTRAINT FsYA_FsY_FK FOREIGN KEY (year) REFERENCES FiscalYear (year) ON DELETE CASCADE,
  CONSTRAINT FsYA_PK PRIMARY KEY (accountId, year),
  accountNumber INTEGER(3) NOT NULL,
  subaccountNumber INTEGER(3) NOT NULL,
  name VARCHAR(100) NOT NULL,
  description VARCHAR(500) NOT NULL,
  transferBalanceTargetAccountId BIGINT NULL,
  transferBalanceTargetYear INTEGER(4) NULL,
  CONSTRAINT FsYA_FsYA_TrB_FK FOREIGN KEY (transferBalanceTargetAccountId, transferBalanceTargetYear)
    REFERENCES FiscalYearAccount (accountId, year)
) ENGINE=InnoDB CHARSET=utf8;

/*** Transaction Subsystem ***/

CREATE TABLE Transaction (
  transactionId BIGINT NOT NULL PRIMARY KEY,
  description VARCHAR(4000) NOT NULL,
  transactionDate DATE NOT NULL,
  checked SMALLINT DEFAULT 0 NOT NULL CHECK (checked IN (1, 0)),
  balance SMALLINT DEFAULT 0 NOT NULL CHECK (balance IN (1, 0))
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE Item (
  transactionId BIGINT NOT NULL,
  CONSTRAINT It_Tr_FK FOREIGN KEY (transactionId) 
    REFERENCES Transaction (transactionId) ON DELETE CASCADE,
  accountId BIGINT NOT NULL,
  year INTEGER(4) NOT NULL,
  CONSTRAINT It_FsYA_FK FOREIGN KEY (accountId, year) 
    REFERENCES FiscalYearAccount(accountId, year) ON DELETE CASCADE,
  amount NUMERIC(38,2) DEFAULT 0 NOT NULL,
  debit SMALLINT DEFAULT 1 NOT NULL,
  CONSTRAINT ItDb_CK CHECK (debit IN (1, 0)),
  checked SMALLINT DEFAULT 0 NOT NULL,
  CONSTRAINT ItCh_CK CHECK (checked IN (1, 0)),
  CONSTRAINT It_PK PRIMARY KEY (transactionId, accountId, year)
) ENGINE=InnoDB CHARSET=utf8;

CREATE TABLE ReceivableTransactionItem (
  transactionId BIGINT NOT NULL,
  accountId BIGINT NOT NULL,
  year INTEGER(4) NOT NULL,
  CONSTRAINT RcTI_It_FK FOREIGN KEY (transactionId, accountId, year) 
    REFERENCES Item(transactionId, accountId, year) ON DELETE CASCADE,
  CONSTRAINT RcTI_PK PRIMARY KEY (transactionId, accountId, year),
  receivableAmount NUMERIC(38,2) NOT NULL,
  writeoffAmount NUMERIC(38,2) DEFAULT 0 NOT NULL
) ENGINE=InnoDB CHARSET=utf8;
