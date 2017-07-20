REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM Accounts Receivable:  Poesys Associates', 'RJM Accounts Receivable:  Poesys Associates', 111, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 111 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'MLS Accounts Receivable:  Poesys Associates', 'MLS Accounts Receivable:  Poesys Associates', 112, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 112 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA:  Adobe Systems (ADBE)', 'RJM IRA:  Adobe Systems (ADBE)', 120, 2, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM UC Retirement Plan', 'RJM UC Retirement Plan', 121, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, '20th Century Gifttrust Investors (TWGTX)', '20th Century Gifttrust Investors (TWGTX)', 127, 5, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 5;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Orchard Supply Hardware', 'Orchard Supply Hardware', 210, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 210 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'First Nationwide Interest (9040014608)', 'First Nationwide Interest (9040014608)', 410, 3, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 410 AND
       defaultSubAccountNumber = 3;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'TCM:  20th Century Custodial Investment Dividends', 'TCM:  20th Century Custodial Investment Dividends', 423, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 423 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Distributions from Poesys Associates', 'Distributions from Poesys Associates', 460, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 460 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Income Tax Refunds', 'State Income Tax Refunds', 490, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 490 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Medical Insurance', 'Medical Insurance', 601, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 601 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM Utilities', 'RJM Utilities', 664, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 664 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'MLS Utilities', 'MLS Utilities', 674, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 674 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Royalty Production Expenses', 'Royalty Production Expenses', 680, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 680 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Contribution to Poesys Associates', 'Contribution to Poesys Associates', 730, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 730 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'First Nationwide Select Savings (9040014608)', 'First Nationwide Select Savings (9040014608)', 103, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 103 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'First Nationwide Checking (937-4002518)', 'First Nationwide Checking (937-4002518)', 104, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 104 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'TCM Custodial, ADBE', 'TCM Custodial, ADBE', 127, 1, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Charles Scwhab VISA (4431 2210 1980 8149)', 'Charles Scwhab VISA (4431 2210 1980 8149)', 203, 0, 2, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 203 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'University of California Extension', 'University of California Extension', 400, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 400 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Trust Gifts', 'Trust Gifts', 501, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 501 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Income Tax (Estimated Tax Payments)', 'State Income Tax (Estimated Tax Payments)', 610, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 610 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Income Tax (U. C. Extension)', 'State Income Tax (U. C. Extension)', 610, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 610 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Medicare (U. C. Extension)', 'Medicare (U. C. Extension)', 611, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Theo Child Care', 'Theo Child Care', 704, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 704 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Federal Income Tax (Estimated Payments)', 'Federal Income Tax (Estimated Payments)', 710, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 710 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Federal Income Tax (UC Extension Withholding)', 'Federal Income Tax (UC Extension Withholding)', 710, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 710 AND
       defaultSubAccountNumber = 1;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 283
 WHERE defaultAccountNumber = 127 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 172
 WHERE defaultAccountNumber = 128 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 201
 WHERE defaultAccountNumber = 128 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 350
 WHERE defaultAccountNumber = 128 AND
       defaultSubAccountNumber = 2;

UPDATE TempAccount
   SET accountId = 168
 WHERE defaultAccountNumber = 138 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 140
 WHERE defaultAccountNumber = 144 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 132
 WHERE defaultAccountNumber = 144 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 8
 WHERE defaultAccountNumber = 145 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 4
 WHERE defaultAccountNumber = 145 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 188
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 301
 WHERE defaultAccountNumber = 204 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 354
 WHERE defaultAccountNumber = 411 AND
       defaultSubAccountNumber = 2;

UPDATE TempAccount
   SET accountId = 288
 WHERE defaultAccountNumber = 422 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 256
 WHERE defaultAccountNumber = 450 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 357
 WHERE defaultAccountNumber = 452 AND
       defaultSubAccountNumber = 0;

REM Renumbered accounts replacing existing ones

UPDATE TempAccount
   SET accountId = 326
 WHERE defaultAccountNumber = 704 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 224
 WHERE defaultAccountNumber = 711 AND
       defaultSubAccountNumber = 0;
