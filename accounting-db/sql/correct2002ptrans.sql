REM Correct any Poesys transactions in 2002 that have a 2003 transaction date. Should be 1 transaction.

UPDATE Transaction
   SET transactionDate = ADD_MONTHS(transactionDate, -12)
 WHERE EXTRACT(YEAR FROM transactiondate) = 2003;
