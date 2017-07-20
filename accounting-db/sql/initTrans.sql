REM Initialize all the transactions and items for a given year from an old accounting user.

define acc_user=&1
define year=&2

SET SERVEROUTPUT ON

DECLARE
  vFirst BOOLEAN := TRUE;
  vTrans NUMBER := NULL;
BEGIN

  FOR item IN (SELECT t.transaction, a.accountId, NVL(t.description, 'No description') description, 
                      t.transactionDate, 
                      CASE
                        WHEN t.checked = 'Y' THEN 1
                        WHEN t.checked = 'N' THEN 0
                        ELSE 0
                      END checked, 
                      CASE
                        WHEN ti.checked = 'Y' THEN 1
                        WHEN ti.checked = 'N' THEN 0
                        ELSE 0
                      END item_checked,
                      decode(ti.debitorcredit, 'DR', 1, 'CR', 0) debit, ti.amount
                 FROM &acc_user..Transaction t JOIN
                      &acc_user..TransactionItem ti ON t.transaction = ti.transaction JOIN 
                      FiscalYearAccount a ON floor(ti.account) = a.accountNumber AND
                                             decode(instr(to_char(ti.account), '.'), 0, 0, to_number(substr (to_char(ti.account), instr(to_char(ti.account), '.') + 1))) = a.subAccountNumber
                WHERE year = &year
                ORDER BY 1, 2) LOOP
    IF (vTrans IS NULL OR vTrans <> item.transaction) THEN
      vFirst := TRUE;
    ELSE
      vFirst := FALSE;
    END IF;
    
    vTrans := item.transaction;
    
    BEGIN
      IF (vFirst) THEN
        INSERT INTO Transaction
        VALUES (transactionId.NEXTVAL, item.description, item.transactionDate, item.checked, 0);
      END IF;

      INSERT INTO Item
      VALUES (transactionId.CURRVAL, item.accountId, &year, item.amount, item.debit, item.item_checked);
    EXCEPTION
      WHEN OTHERS THEN
        DBMS_OUTPUT.PUT_LINE('Bad row:  ' || item.transaction || ', ' ||
        item.description || ', ' ||
        item.transactionDate || ', ' ||
        item.checked || ', ' ||
        item.accountId || ', ' ||
        item.amount || ', ' ||
        item.debit || ', ' ||
        item.item_checked);
        RAISE;
    END;
  END LOOP;
END;
/
