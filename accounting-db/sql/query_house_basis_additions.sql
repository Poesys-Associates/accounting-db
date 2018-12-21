select t.transactionId, t.description, t.transactionDate, i.amount 
from Transaction t JOIN Item i ON t.transactionId = i.transactionId 
where i.debit = 0 and
t.transactionId IN (select t1.transactionId
from Transaction t1 JOIN Item i1 ON t1.transactionId = i1.transactionId
where i1.accountName = 'Adjustment for Basis for 36 Whitney St., SF (MLS)' and 
t1.transactionDate between '2013-01-01' and '2013-12-31' and i1.debit = 1);

