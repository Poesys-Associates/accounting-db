REM Transfer the contents of the TempAccount table into the FiscalYearAccount table for a given year.

define year=&1

INSERT INTO FiscalYearAccount (accountId, year, accountNumber, subAccountNumber, name, description)
SELECT accountId, &year, defaultAccountNumber, defaultSubAccountNumber, name, description
  FROM TempAccount;