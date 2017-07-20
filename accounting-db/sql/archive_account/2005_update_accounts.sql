REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA: UMB Scout Worldwide (UMBWX)', 'RJM IRA: UMB Scout Worldwide (UMBWX)', 120, 16, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 16;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA: American Century Equity Income (TWEIX)', 'RJM IRA: American Century Equity Income (TWEIX)', 120, 17, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 17;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM Roth IRA: Ariel (ARGFX)', 'RJM Roth IRA: Ariel (ARGFX)', 121, 3, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM Roth IRA: Vanguard S&P 500 Index (VFINX)', 'RJM Roth IRA: Vanguard S&P 500 Index (VFINX)', 121, 4, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 4;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'MLS IRA: Ariel Fund (ARGFX)', 'MLS IRA: Ariel Fund (ARGFX)', 122, 8, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 122 AND
       defaultSubAccountNumber = 8;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'MLS IRA: Parnassus Equity Income Fund (PRBLX)', 'MLS IRA: Parnassus Equity Income Fund (PRBLX)', 122, 9, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 122 AND
       defaultSubAccountNumber = 9;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Stanford University SCRP Plan', 'Stanford University SCRP Plan', 123, 2, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 123 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Stanford SCRP Income', 'Stanford SCRP Income', 536, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 536 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Stanford Medical Insurance Income', 'Stanford Medical Insurance Income', 537, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 537 AND
       defaultSubAccountNumber = 0;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 49
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 248
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 4;

UPDATE TempAccount
   SET accountId = 485
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 9;

UPDATE TempAccount
   SET accountId = 586
 WHERE defaultAccountNumber = 123 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 588
 WHERE defaultAccountNumber = 124 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 574
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 575
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 3;

UPDATE TempAccount
   SET accountId = 576
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 4;

UPDATE TempAccount
   SET accountId = 577
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 5;

UPDATE TempAccount
   SET accountId = 578
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 6;

UPDATE TempAccount
   SET accountId = 593
 WHERE defaultAccountNumber = 531 AND
       defaultSubAccountNumber = 0;
