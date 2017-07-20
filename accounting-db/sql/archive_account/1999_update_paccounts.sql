REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Computer Supplies', 'Computer Supplies', 614, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 614 AND
       defaultSubAccountNumber = 0;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 23
 WHERE defaultAccountNumber = 100 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 87
 WHERE defaultAccountNumber = 101 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 54
 WHERE defaultAccountNumber = 501 AND
       defaultSubAccountNumber = 0;
