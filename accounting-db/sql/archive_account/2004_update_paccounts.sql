REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Ariel Fund (ARGFX)', 'Ariel Fund (ARGFX)', 120, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Vanguard Total Market Extended Index (VEXMX)', 'Vanguard Total Market Extended Index (VEXMX)', 121, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Clapper', 'Clapper', 412, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 412 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Arterra', 'Arterra', 413, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 413 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Schwab One Capital Gains', 'Schwab One Capital Gains', 441, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 441 AND
       defaultSubAccountNumber = 0;

REM Renumbered accounts

UPDATE TempAccount
   SET accountId = 95
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 0;
