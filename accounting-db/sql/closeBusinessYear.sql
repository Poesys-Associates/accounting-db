REM Close the fiscal year by writing the income and expense accounts to a specified equity account,
REM For poesys, equity accounts will be 25 and 47.

SET SERVEROUTPUT ON

define year=&1
define acct1=&2
define acct2=&3

DECLARE
  vSum NUMBER := 0;
  vAcct1 NUMBER := 0;
  vBal1 NUMBER := 0;
  vAcct2 NUMBER := 0;
  vBal2 NUMBER := 0;
  vTransactionId NUMBER := 0;
BEGIN
  -- Insert the closing transaction dated Dec. 31 of the specified year.
  INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 
                                  'Closing year &year to accounts &acct1 and &acct2', 
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
  
  -- Get the balances for the two equity accounts.
  SELECT SUM(CASE WHEN i.debit = 1 THEN i.amount*-1 ELSE i.amount END) amount
    INTO vBal1
    FROM FiscalYearAccount fya JOIN
         Item i ON fya.year = i.year AND fya.accountId = i.accountId JOIN
         Transaction t ON i.transactionId = t.transactionId
   WHERE fya.year <= &year AND
         EXTRACT(YEAR FROM t.transactionDate) <= &year AND -- exclude account combination transactions
         fya.accountId = &acct1;
  
  SELECT SUM(CASE WHEN i.debit = 1 THEN i.amount*-1 ELSE i.amount END) amount
    INTO vBal2
    FROM FiscalYearAccount fya JOIN
         Item i ON fya.year = i.year AND fya.accountId = i.accountId JOIN
         Transaction t ON i.transactionId = t.transactionId
   WHERE fya.year <= &year AND
         EXTRACT(YEAR FROM t.transactionDate) <= &year AND -- exclude account combination transactions
         fya.accountId = &acct2;
  
  -- Credit the specified equity accounts with the total. Divide the amounts to create
  -- equal equity accounts where possible.
  
  -- Dump the amounts for debugging.
  DBMS_OUTPUT.PUT_LINE('Net income = ' || vSum || ', RJM Balance = ' || vBal1 || ', MLS Balance = ' || vBal2 || '.');
  
  IF MOD(ABS(vSum)*100, 2) = 1 AND vBal1 <= vBal2 THEN
    -- Odd amounts but equal balances or first acct lower, give extra penny to first account.
    DBMS_OUTPUT.PUT_LINE('Odd amounts, RJM <= MLS.');
    vAcct1 := (vSum - .01)/2 + .01;
    vAcct2 := (vSum - .01)/2;
  ELSIF MOD(ABS(vSum)*100, 2) = 1 AND vBal1 > vBal2 THEN
    -- Odd amounts but acct 1 more than acct2, give extra penny to second account.
    DBMS_OUTPUT.PUT_LINE('Odd amounts, RJM > MLS.');
    vAcct1 := (vSum - .01)/2;
    vAcct2 := (vSum - .01)/2 + .01;
  ELSIF MOD(ABS(vSum)*100, 2) = 0 AND vBal1 > vBal2 THEN
    -- Equal amounts but acct 1 more than acct2, give extra penny to second account.
    DBMS_OUTPUT.PUT_LINE('Equal amounts, RJM > MLS.');
    vAcct1 := (vSum - .01)/2;
    vAcct2 := (vSum - .01)/2 + .01;
  ELSIF MOD(ABS(vSum)*100, 2) = 0 AND vBal1 < vBal2 THEN
    -- Equal amounts but acct 1 less than acct2, tough: divide equally.
    DBMS_OUTPUT.PUT_LINE('Equal amounts, RJM < MLS.');
    vAcct1 := vSum/2;
    vAcct2 := vAcct1;
  ELSE
    -- Equal amounts and balances, divide equally.
    DBMS_OUTPUT.PUT_LINE('Equal amounts, RJM = MLS.');
    vAcct1 := vSum/2;
    vAcct2 := vAcct1;
  END IF;
  
  -- Dump computed amounts for debugging.
  DBMS_OUTPUT.PUT_LINE('RJM amount = ' || vAcct1 || ', MLS amount = ' || vAcct2 || '.');
  
  INSERT INTO Item VALUES (vTransactionId, &acct1, &year, vAcct1, 0, 1);
  INSERT INTO Item VALUES (vTransactionId, &acct2, &year, vAcct2, 0, 1);
  
END;
/
