REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Schwab One Checking (7141-3897)', 'Schwab One Checking (7141-3897)', 101, 0, 0, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 101 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Schwab One Dividends', 'Schwab One Dividends', 440, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 101 AND
       defaultSubAccountNumber = 0;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 23
 WHERE defaultAccountNumber = 100 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 76
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 0;

REM Renumbered accounts replacing existing ones

UPDATE TempAccount
   SET accountId = 29
 WHERE defaultAccountNumber = 400 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 84
 WHERE defaultAccountNumber = 405 AND
       defaultSubAccountNumber = 1;
