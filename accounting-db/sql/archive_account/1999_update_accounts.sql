REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM Roth IRA: Money Market Fund', 'RJM Roth IRA: Money Market Fund', 121, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'MLS Roth IRA: Money Market Fund', 'MLS Roth IRA: Money Market Fund', 124, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 124 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'TCM Custodial Savings (Cal Fed 955-003749-4)', 'TCM Custodial Savings (Cal Fed 955-003749-4)', 127, 3, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Deferred Capital Loss', 'Deferred Capital Loss', 128, 3, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 128 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Hart/Howerton Cafeteria Plan Savings', 'Hart/Howerton Cafeteria Plan Savings', 190, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 190 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Target', 'Target', 213, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 213 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Express', 'Express', 214, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 214 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'ValueStar Medical Insurance Cafeteria Plan', 'ValueStar Medical Insurance Cafeteria Plan', 531, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 531 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Commuter Deduction', 'Commuter Deduction', 532, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 532 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Hart/Howerton Cafeteria Plan', 'Hart/Howerton Cafeteria Plan', 533, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 533 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Hart/Howerton Section 125 Medical Insurance', 'Hart/Howerton Section 125 Medical Insurance', 534, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 534 AND
       defaultSubAccountNumber = 0;

REM Renumbered accounts

UPDATE TempAccount
   SET accountId = 497
 WHERE defaultAccountNumber = 400 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 498
 WHERE defaultAccountNumber = 401 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 501
 WHERE defaultAccountNumber = 530 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 119
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 228
 WHERE defaultAccountNumber = 612 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 502
 WHERE defaultAccountNumber = 613 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 503
 WHERE defaultAccountNumber = 613 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 504
 WHERE defaultAccountNumber = 614 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 505
 WHERE defaultAccountNumber = 614 AND
       defaultSubAccountNumber = 1;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 445
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 3;

UPDATE TempAccount
   SET accountId = 232
 WHERE defaultAccountNumber = 122 AND
       defaultSubAccountNumber = 3;

UPDATE TempAccount
   SET accountId = 153
 WHERE defaultAccountNumber = 126 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 402
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 492
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 2;

UPDATE TempAccount
   SET accountId = 389
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 5;

UPDATE TempAccount
   SET accountId = 493
 WHERE defaultAccountNumber = 130 AND
       defaultSubAccountNumber = 2;

UPDATE TempAccount
   SET accountId = 494
 WHERE defaultAccountNumber = 130 AND
       defaultSubAccountNumber = 3;

UPDATE TempAccount
   SET accountId = 187
 WHERE defaultAccountNumber = 131 AND
       defaultSubAccountNumber = 0;

