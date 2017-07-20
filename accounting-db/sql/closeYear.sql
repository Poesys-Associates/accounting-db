REM Close the fiscal year by writing the income and expense accounts to a specified equity account.
REM For muller, this account will usually be acct 217.

define year=&1
define acct=&2

DECLARE
  vSum NUMBER := 0;
  vTransactionId NUMBER := 0;
BEGIN
  -- Insert the closing transaction dated Dec. 31 of the specified year.
  INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 
                                  'Closing year &year to account &acct', 
                                  '31-DEC-' || &year, 1, 1);
  SELECT transactionId.CURRVAL
    INTO vTransactionId
    FROM Dual;

  -- Insert an item into the transaction for each income/expense account with a non-zero balance.
  FOR vItem IN (SELECT fya.accountId, 
                       SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) amount,
                       CASE 
                         WHEN SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) < 0 THEN 0
                         ELSE 1
                       END debit
                  FROM FiscalYearAccount fya JOIN
                       Item i ON fya.year = i.year AND fya.accountId = i.accountId JOIN
                       Transaction t ON i.transactionId = t.transactionId
                 WHERE fya.year = &year AND
                       fya.accountNumber BETWEEN 400 AND 900 AND
                       t.balance = 0
                 GROUP BY fya.accountId
                HAVING SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) <> 0) LOOP
    INSERT INTO Item VALUES (vTransactionId, vItem.accountId, &year, ABS(vItem.amount), vItem.debit, 1);
    -- Accumulate the total amount for later use.
    vSum := vSum + vItem.amount;
  END LOOP;
  
  -- Credit the specified equity account with the total.
  INSERT INTO Item VALUES (vTransactionId, &acct, &year, vSum, 0, 1);
END;
/
