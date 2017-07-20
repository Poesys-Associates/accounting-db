REM Check that all transactions are in the correct account year.

PROMPT Non-balance transactions outside of the stated fiscal year for the transaction items

SELECT t.transactionId, t.transactionDate, i.year, i.accountId, i.amount, i.debit
  FROM Transaction t JOIN Item i ON t.transactionId = i.transactionId
 WHERE EXTRACT(YEAR FROM t.transactionDate) <> i.year AND
       balance = 0
 ORDER BY 1;
 
PROMPT Transaction items that don't sum to zero across a transaction

REM Check that non-balance transaction item debits and credits for a transaction sum to zero.

SELECT i.transactionId, SUM (DECODE (i.debit, 1, -1, 0, 1, 0) * i.amount)
  FROM Transaction t JOIN Item i ON t.transactionId = i.transactionId
 WHERE t.balance = 0
 GROUP BY i.transactionId
HAVING SUM (DECODE (i.debit, 1, -1, 0, 1, 0) * i.amount) != 0;

PROMPT Transactions with no items

SELECT t.transactionId
  FROM Transaction t LEFT OUTER JOIN Item i ON t.transactionId = i.transactionId
 WHERE i.transactionId IS NULL;

REM TODO Add accounts receivable checks here.
