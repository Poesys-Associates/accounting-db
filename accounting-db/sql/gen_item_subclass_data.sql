truncate table Payment;
truncate table Reimbursement;

insert into Payment
select transactionId, accountId, year
  from Item
 where accountId not in (select accountId from Receivable);
 
insert into Payment
select transactionId, accountId, year
  from Item
 where accountId in (select accountId from Receivable) AND
       debit = 1;
       
insert into Reimbursement (transactionId, accountId, year, receivableAmount, 
                           writeoffAmount, receivableTransactionId, 
                           receivableAccountId, receivableYear)
select transactionId, accountId, year, 0.0, 0.0,
       (select min(transactionId) 
         from Item i2
        where i2.accountId = i.accountId AND
              i2.year = i.year AND
              debit = 1) AS receivableTransactionId,
       accountId, 
       year
  from Item i
 where accountId in (select accountId from Receivable) AND
       debit = 0 AND
       (select min(transactionId) 
          from Item i2
         where i2.accountId = i.accountId AND
               i2.year = i.year AND
               debit = 1) IS NOT NULL;

commit;
