REM Correct any transactions in 1998 that have a 1999 transaction date. Should be 3 transactions from account 34.

UPDATE Transaction
   SET transactionDate = ADD_MONTHS(transactionDate, -12)
 WHERE EXTRACT(YEAR FROM transactiondate) = 1999;
