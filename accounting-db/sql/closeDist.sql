REM Close an equity distribution account into corresponding contributed-equity account.
REM For Poesys, the distribution accounts are 68 and 75, and the corresponding 
REM contributed-equity accounts are 25 and 47. This approach assumes that the
REM balances in the distribution account accurately reflects the actual 
REM distribution to the owner, and hence no attempt is made to allocate
REM amounts in any way. If the amount is zero, nothing is done.
REM
REM The distribution balances can be either credit or debit, though mostly they
REM will be debit balances. A credit balance is a transfer of a liability or
REM something equivalent, distributing the liability (or equity of contra asset)
REM to the partners. In this case, the distribution adds to equity rather than
REM debiting it.

REM Poesys usage:
REM
REM @closeDist <year> 25 68 (RJM)
REM @closeDist <year> 47 75 (MLS)
REM
REM where <year> is the specified year.

define year=&1
define acct=&2
define dist=&3

DECLARE
  vDist NUMBER;
  vDistDr NUMBER := 0; -- default to credit distribution account
  vEqDr NUMBER := 1;   -- default to debit contributed-equity account
BEGIN

  -- Query the distribution.
  SELECT SUM(CASE WHEN debit = 1 THEN i.amount*-1 ELSE i.amount END) amount
    INTO vDist
    FROM Item i
   WHERE i.year = &year AND
         i.accountId = &dist;
         
  IF vDist <> 0 THEN
    IF vDist > 0 THEN
      -- If the amount is positive, the function reverses the debit/credit.
      vDistDr := 1;
      vEqDr := 0;
    END IF;
    
    -- Create the transaction to write the distribution accounts into the equity accounts.
    INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 
                                    'Closing year &year distribution account &dist and to equity account &acct', 
                                    '31-DEC-' || &year, 1, 1);

    -- Insert the credit item for the distribution account.
    INSERT INTO Item VALUES (transactionId.CURRVAL, &dist, &year, ABS(vDist), vDistDr, 1);
    -- Insert the debit item for the contributed-equity account.
    INSERT INTO Item VALUES (transactionId.CURRVAL, &acct, &year, ABS(vDist), vEqDr, 1);
  END IF;
END;
/
