UPDATE TempAccount t 
   SET accountId = (SELECT accountId 
                      FROM Account a 
                     WHERE t.defaultAccountNumber = a.defaultAccountNumber AND 
                           t.defaultSubAccountNumber = a.defaultSubAccountNumber AND 
                           t.name = a.name);
