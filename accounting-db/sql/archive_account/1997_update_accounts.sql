REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM SEP-IRA:  MMP Money Market Fund (Schwab)', 'RJM SEP-IRA:  MMP Money Market Fund (Schwab)', 120, 3, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents 401K Plan', 'SmartPatents 401K Plan', 123, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 123 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Deferred Poesys Contribution', 'Deferred Poesys Contribution', 137, 1, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 137 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Prepaid Refinancing Interest', 'Prepaid Refinancing Interest', 146, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 146 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'People''s Bank VISA (4116 0700 1123 0618)', 'People''s Bank VISA (4116 0700 1123 0618)', 207, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 207 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Working Assets VISA (4124 2801 2101 9203)', 'Working Assets VISA (4124 2801 2101 9203)', 209, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 209 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'First Mortgage (Countrywide Home Loans)', 'First Mortgage (Countrywide Home Loans)', 233, 0, 1, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 233 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents, Inc.', 'SmartPatents, Inc.', 401, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 401 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Macmillan Reviews', 'Macmillan Reviews', 430, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 430 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Royalties--Macmillan', 'Royalties--Macmillan', 452, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 452 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents 401K Contribution', 'SmartPatents 401K Contribution', 530, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 530 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents State Income Tax Withholding', 'SmartPatents State Income Tax Withholding', 611, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents CA SDI', 'SmartPatents CA SDI', 611, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Countrywide Mortgage Interest', 'Countrywide Mortgage Interest', 620, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 620 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Provident Funding Interest', 'Provident Funding Interest', 620, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 620 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Refinancing Interest/Points', 'Refinancing Interest/Points', 620, 3, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 620 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents Federal Income Tax Withholding', 'SmartPatents Federal Income Tax Withholding', 712, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 712 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents FICA', 'SmartPatents FICA', 712, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 712 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'SmartPatents Medicare', 'SmartPatents Medicare', 712, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 712 AND
       defaultSubAccountNumber = 2;

REM Renumbered accounts

UPDATE TempAccount
   SET accountId = 391
 WHERE defaultAccountNumber = 410 AND
       defaultSubAccountNumber = 0;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 357
 WHERE defaultAccountNumber = 451 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 401
 WHERE defaultAccountNumber = 100 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 400
 WHERE defaultAccountNumber = 101 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 196
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 140
 WHERE defaultAccountNumber = 144 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 8
 WHERE defaultAccountNumber = 145 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 403
 WHERE defaultAccountNumber = 203 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 256
 WHERE defaultAccountNumber = 450 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 130
 WHERE defaultAccountNumber = 620 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 206
 WHERE defaultAccountNumber = 621 AND
       defaultSubAccountNumber = 0;
