col transaction heading "Id" format 99999
col receivableTransaction heading "AR Id" format 99999
col transactionDate heading "Date" format a9
col receivableAccount heading "Acct" format 999.99
col description heading "Description"
col amountReceived heading "Received" format $999,999,999.99
col amountAllocated heading "Alloc" format $999,999,999.99
col receivableAmount heading "AR Amount" format $999,999,999.99

set lines 500

SELECT rt.transaction, rt.receivableTransaction, t.transactionDate, t.description, rt.amountReceived, rt.amountAllocated
  FROM ReceivableTransaction rt JOIN 
       Transaction t ON rt.transaction = t.transaction
ORDER BY 2;
