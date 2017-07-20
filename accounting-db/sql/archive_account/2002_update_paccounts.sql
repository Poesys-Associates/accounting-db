REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Bank One VISA (4246311918477768)', 'Bank One VISA (4246311918477768)', 201, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 201 AND
       defaultSubAccountNumber = 0;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 27
 WHERE defaultAccountNumber = 631 AND
       defaultSubAccountNumber = 0;
