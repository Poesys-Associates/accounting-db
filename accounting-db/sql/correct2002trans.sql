REM Correct any transactions in 2002 that have a 2003 transaction date. Should be 3 transactions from various accounts.

UPDATE Transaction
   SET transactionDate = ADD_MONTHS(transactionDate, -12)
 WHERE EXTRACT(YEAR FROM transactiondate) = 2003;
