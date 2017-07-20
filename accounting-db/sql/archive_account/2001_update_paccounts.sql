REM New accounts

UPDATE TempAccount
   SET accountId = 45
 WHERE defaultAccountNumber = 411 AND
       defaultSubAccountNumber = 0;
