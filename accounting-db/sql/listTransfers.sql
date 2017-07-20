REM List the transfer amounts in a specified year for balance sheet accounts combined into a new account for the next year.

define year = &1

set lines 200

col accountId heading "Id" format 99999
col accountNumber heading "Acct" format 9999
col subAccountNumber heading "Sub" format 999
col year heading "Year" format 9999
col transferbalancetargetyear heading "Transfer Year" format 9999999999999
col transferbalancetargetaccountid heading "Transfer Id" format 99999999999
col amount heading "Amount" format $999,999,999.99
col debit heading "DR" format 99

SELECT a.year, a.accountid, a.accountNumber, a.subAccountNumber, 
       ABS(bal.amount) amount,
       CASE
         WHEN bal.amount > 0 THEN 0
         ELSE 1
       END debit,
       a.transferbalancetargetyear, a.transferbalancetargetaccountid, b.accountId, b.accountNumber, b.subAccountNumber
  FROM fiscalyearaccount a JOIN 
       fiscalyearaccount b ON a.transferbalancetargetyear = b.year AND 
                              a.transferbalancetargetaccountid = b.accountId JOIN
       (SELECT accountId, SUM(CASE WHEN debit = 1 THEN amount*-1 ELSE amount END) amount         
          FROM Item
         WHERE year <= &year
         GROUP BY accountId 
        HAVING SUM(CASE 
                     WHEN debit = 1 THEN amount*-1 
                     ELSE amount 
                   END) <> 0) bal ON a.accountId = bal.accountId
 WHERE a.transferbalancetargetaccountid IS NOT NULL and a.year = &year
 ORDER BY 7, 8, 3, 4;

prompt transfers from prior year

set heading off

SELECT 'INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, ' || 
        a.accountid || ', ' || a.year || ', ' || ABS(bal.amount) || ', ' ||
       CASE
         WHEN bal.amount > 0 THEN 1
         ELSE 0
       END || ', 1);'
  FROM fiscalyearaccount a JOIN 
       fiscalyearaccount b ON a.transferbalancetargetyear = b.year AND 
                              a.transferbalancetargetaccountid = b.accountId JOIN
       (SELECT accountId, SUM(CASE WHEN debit = 1 THEN amount*-1 ELSE amount END) amount         
          FROM Item
         WHERE year <= &year
         GROUP BY accountId 
        HAVING SUM(CASE 
                     WHEN debit = 1 THEN amount*-1 
                     ELSE amount 
                   END) <> 0) bal ON a.accountId = bal.accountId
 WHERE a.transferbalancetargetaccountid IS NOT NULL and a.year = &year;

prompt transfers to next year

SELECT 'INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, ' || 
        b.accountid || ', ' || b.year || ', ' || ABS(SUM(bal.amount)) || ', ' ||
       CASE
         WHEN SUM(bal.amount) > 0 THEN 0
         ELSE 1
       END || ', 1);'
  FROM fiscalyearaccount a JOIN 
       fiscalyearaccount b ON a.transferbalancetargetyear = b.year AND 
                              a.transferbalancetargetaccountid = b.accountId JOIN
       (SELECT accountId, SUM(CASE WHEN debit = 1 THEN amount*-1 ELSE amount END) amount         
          FROM Item
         WHERE year <= &year
         GROUP BY accountId 
        HAVING SUM(CASE 
                     WHEN debit = 1 THEN amount*-1 
                     ELSE amount 
                   END) <> 0) bal ON a.accountId = bal.accountId
 WHERE a.transferbalancetargetaccountid IS NOT NULL and a.year = &year
 GROUP BY b.accountId, b.year;

set heading on
