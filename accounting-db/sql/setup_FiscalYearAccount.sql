define year=&1

INSERT INTO FiscalYearAccount (accountId, year, accountNumber, subAccountNumber, name, description)
SELECT accountId, &year, defaultAccountNumber, defaultSubAccountNumber, name, description
  FROM Account
 WHERE active = 1;
 