define year = &1

SELECT a.accountType, fya.accountNumber || '.' || fya.subAccountNumber account, fya.accountId, fya.name
  FROM Account a JOIN
       FiscalYearAccount fya ON a.accountId = fya.accountId
 WHERE fya.year = &year
 ORDER BY a.accountType, fya.accountNumber, fya.subAccountNumber;
