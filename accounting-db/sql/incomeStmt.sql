REM Select the income statement for a given fiscal year.

define year=&1

set lines 120

break on report on accountType on accountSubType
compute sum of amount on report
compute sum of amount on accountType
compute sum of amount on accountSubType

col accountType heading "Type" format a10
col accountSubType heading "SubType" format a15
col name heading "Account Name" format a50
col account heading "#" format a6
col amount heading "Amount" format $999,999,999.99

SELECT a.accountType,
       CASE
          WHEN fya.accountNumber < 500 THEN 'Taxable'
          WHEN fya.accountNumber < 600 THEN 'Non-Taxable'
          WHEN fya.accountNumber < 700 THEN 'Tax-Related'
          WHEN fya.accountNumber < 800 THEN 'Non-Tax-Related'
          ELSE 'Business'
       END accountSubType,
       fya.accountNumber || '.' || fya.subAccountNumber account, 
       MIN(fya.name) name,
       SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) amount
  FROM FiscalYearAccount fya JOIN
       Item i ON fya.year = i.year AND fya.accountId = i.accountId JOIN
       Transaction t ON i.transactionId = t.transactionId JOIN
       Account a ON fya.accountId = a.accountId
 WHERE fya.year = &year AND
       fya.accountNumber BETWEEN 400 AND 900 AND
       t.balance = 0
 GROUP BY a.accountType, fya.accountNumber, fya.accountNumber || '.' || fya.subAccountNumber
 ORDER BY 3, 1;
