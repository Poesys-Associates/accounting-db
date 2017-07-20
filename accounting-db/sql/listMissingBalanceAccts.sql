REM List the balance sheet accounts removed from the year following the 
REM specified year, listing the balances for the removed accounts in the 
REM specified year.

define year=&1

SELECT a1.year, a1.accountId, a1.accountNumber, a1.subAccountNumber, a1.name, bal.amount
  FROM FiscalYearAccount a1 LEFT OUTER JOIN
       FiscalYearAccount a2 ON a1.accountId = a2.accountId AND
                               a1.year + 1 = a2.year JOIN
       (SELECT accountId, SUM(CASE WHEN debit = 1 THEN amount*-1 ELSE amount END) amount         
                 FROM Item
                WHERE year <= &year
                GROUP BY accountId 
               HAVING SUM(CASE 
                            WHEN debit = 1 THEN amount*-1 
                            ELSE amount 
                   END) <> 0) bal ON a1.accountId = bal.accountId
 WHERE a1.accountNumber BETWEEN 0 AND 399 AND
       a2.accountId IS NULL AND
       a1.year = &year
 ORDER BY 1, 3, 4;
