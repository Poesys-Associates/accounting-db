REM Create the initial balance transaction for a new accounting system.

define acc_user=&1
define trdate=&2

begin

  INSERT INTO Transaction
  VALUES (transactionId.NEXTVAL, 'Initial Balance', '&trdate', 1, 1);
  
  FOR item IN (SELECT a.accountId,
                      decode(debitorcredit, 'DR', 1, 'CR', 0) debit, amount
                 FROM &acc_user..balance b JOIN FiscalYearAccount a ON floor(b.account) = a.accountNumber AND
                                                        decode(instr(to_char(b.account), '.'), 0, 0, to_number(substr (to_char(b.account), instr(to_char(b.account), '.') + 1))) = a.subAccountNumber
                WHERE year = EXTRACT(YEAR FROM TO_DATE('&trdate'))) LOOP
    INSERT INTO Item
    VALUES (transactionId.CURRVAL, item.accountId, EXTRACT(YEAR FROM TO_DATE('&trdate')), item.amount, item.debit, 1);
  END LOOP;
end;
/
