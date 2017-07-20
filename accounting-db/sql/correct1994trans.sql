REM Correct any transactions in 1994 that have a 1995 transaction date. Should be 3 transactions from account 202.

UPDATE Transaction
   SET transactionDate = ADD_MONTHS(transactionDate, -12)
 WHERE EXTRACT(YEAR FROM transactiondate) = 1995;
