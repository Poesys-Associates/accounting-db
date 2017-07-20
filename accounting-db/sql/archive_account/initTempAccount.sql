REM Insert into the temporary account table a complete set of accounts from a given accounting year.
REM     @initTempAccount <year> <old>
REM where <year> is the Oracle user name for the year and <old> is the 4-digit year for the previous year.
REM TODO: Delete this after system conversion.

define year=&1
define old=&2

DELETE FROM TempAccount;

INSERT INTO TempAccount (name, description, defaultAccountNumber, defaultSubAccountNumber, debitDefault, accountType, leafType, active, accountId)
SELECT trim(description) name, 
       trim(description) description,
       floor(account) defaultAccountNumber,
       decode(instr(to_char(account), '.'), 0, 0, to_number(substr (to_char(account), instr(to_char(account), '.') + 1))) defaultSubAccountNumber,
       decode(defaultDebitOrCredit, 'DR', 1, 'CR', 0, 0) debitDefault,
       CASE 
         WHEN account >= 0 AND account <= 199.99 THEN 'Asset'
         WHEN account >= 200 AND account <= 299.99 THEN 'Liability'
         WHEN account >= 300 AND account <= 399.99 THEN 'Equity'
         WHEN account >= 400 AND account <= 599.99 THEN 'Income'
         WHEN account >= 600 AND account <= 899.99 THEN 'Expense'
         ELSE 'Other'
       END accountType,
       CASE 
         WHEN account >= 0 AND account <= 109.99 THEN 'Asset'
         WHEN account >= 110 AND account <= 119.99 THEN 'Receivable'
         WHEN account >= 129 AND account <= 199.99 THEN 'Asset'
         WHEN account >= 200 AND account <= 299.99 THEN 'Liability'
         WHEN account >= 300 AND account <= 399.99 THEN 'Equity'
         WHEN account >= 400 AND account <= 599.99 THEN 'Income'
         WHEN account >= 600 AND account <= 899.99 THEN 'Expense'
         ELSE 'Other'
       END leafType,
       1 active,
       null accountId
  FROM &year..account;
         
REM Update the accountId field by comparing name, defaultAccountNumber, and defaultSubAccountNumber for the previous year.

UPDATE TempAccount t
   SET accountId = (SELECT accountId
                      FROM FiscalYearAccount
                     WHERE year = &old AND
                           name = t.name AND
                           accountNumber = t.defaultAccountNumber AND
                           subAccountNumber = t.defaultSubAccountNumber);
