REM New accounts

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA: Williams-Sonoma (WSM)', 'RJM IRA: Williams-Sonoma (WSM)', 120, 22, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 22;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG 401K Plan', 'DAG 401K Plan', 123, 4, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 123 AND
       defaultSubAccountNumber = 4;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Cytokinetics Dep Care Plan Savings', 'Cytokinetics Dep Care Plan Savings', 190, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 190 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Cytokinetics Medical Flex Plan Savings', 'Cytokinetics Medical Flex Plan Savings', 191, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 191 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Working Assets VISA (4124 2801 2101 9203)', 'Working Assets VISA (4124 2801 2101 9203)', 202, 0, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 202 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Data Advantage Group', 'Data Advantage Group', 402, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 402 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Cytokinetics Dep Care Plan', 'Cytokinetics Dep Care Plan', 532, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 532 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Cytokinetics Medical Flex Plan', 'Cytokinetics Medical Flex Plan', 533, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 533 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG 401K', 'DAG 401K', 534, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 534 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG State Income Tax Withholding', 'DAG State Income Tax Withholding', 615, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 615 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG CA SDI', 'DAG CA SDI', 615, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 615 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG Federal Income Tax Withholding', 'DAG Federal Income Tax Withholding', 715, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 715 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG FICA', 'DAG FICA', 715, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 715 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'DAG Medicare', 'DAG Medicare', 715, 1, 2, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 715 AND
       defaultSubAccountNumber = 2;

REM Renamed accounts

UPDATE TempAccount
   SET accountId = 549
 WHERE defaultAccountNumber = 192 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 549
 WHERE defaultAccountNumber = 192 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 137
 WHERE defaultAccountNumber = 201 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 550
 WHERE defaultAccountNumber = 231 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 552
 WHERE defaultAccountNumber = 622 AND
       defaultSubAccountNumber = 0;
