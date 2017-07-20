col transaction heading "Id" format 99999
col transactionDate heading "Date" format a9
col receivableAccount heading "Acct" format 999.99
col description heading "Description"
col amount heading "Item Amount" format $999,999,999.99
col receivableAmount heading "AR Amount" format $999,999,999.99

SELECT ar.transaction, t.transactionDate, ar.receivableAccount, t.description, i.amount, ar.receivableAmount
  FROM accountReceivable ar JOIN 
       transaction t ON ar.transaction = t.transaction JOIN
       transactionItem i ON ar.transaction = i.transaction AND
                            ar.receivableAccount = i.account
ORDER BY 2;
