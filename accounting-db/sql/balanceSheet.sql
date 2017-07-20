REM Select a balance sheet for a given fiscal year.

define year=&1

set lines 100

break on report on accountType
compute sum of amount on report
compute sum of amount on accountType

col accountType heading "Type" format a10
col name heading "Account Name" format a50
col account heading "#" format a6
col accountId heading "Id" format 99999
col amount heading "Amount" format $999,999,999.99

SELECT a.accountType, min(fya.accountNumber || '.' || fya.subAccountNumber) account, fya.accountId,
       min((SELECT name
              FROM FiscalYearAccount
             WHERE accountId = fya.accountId AND
                   year = &year)) name,
       SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) amount         
  FROM FiscalYearAccount fya JOIN
       Item i ON fya.year = i.year AND fya.accountId = i.accountId JOIN
       Transaction t ON i.transactionId = t.transactionId JOIN
       Account a ON fya.accountId = a.accountId
 WHERE fya.year <= &year AND
       EXTRACT(YEAR FROM t.transactionDate) <= &year AND -- exclude account combination transactions
       fya.accountNumber BETWEEN 100 AND 399
 GROUP BY a.accountType, fya.accountId
HAVING SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) != 0 -- exclude empty accounts
 ORDER BY 2, 1;
