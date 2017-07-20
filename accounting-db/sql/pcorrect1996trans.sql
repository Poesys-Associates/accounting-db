set lines 500

SELECT * 
  FROM poesys1996.transaction t JOIN 
       poesys1996.transactionitem ti on t.transaction = ti.transaction 
 WHERE t.description IN (SELECT description FROM transaction WHERE transactionid IN (61, 66, 101, 110, 225))
 ORDER BY transactionDate, account;

SELECT t.* , i.*, a.accountNumber || '.' || a.subAccountNumber account
  FROM transaction t JOIN
       item i on t.transactionId = i.transactionId JOIN
       FiscalYearAccount a ON i.accountId = a.accountId
 WHERE i.transactionid in (61, 66, 101, 110, 225) AND
       a.year = 1996
 ORDER BY t.transactionDate, a.accountNumber, a.subAccountNumber;