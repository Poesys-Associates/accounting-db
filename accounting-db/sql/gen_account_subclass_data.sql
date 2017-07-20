truncate table DirectAsset;
truncate table Receivable;
truncate table Asset;
truncate table Liability;
truncate table Equity;
truncate table Income;
truncate table Expense;

insert into Asset
select accountId
  from Account
 where accountType = 'Asset';
 
insert into DirectAsset
select accountId
  from Account
 where accountType = 'Asset' AND
       leafType = 'DirectAsset';
       
insert into Receivable
select accountId
  from Account
 where accountType = 'Asset' AND
       leafType = 'Receivable';
       
insert into Liability
select accountId
  from Account
 where accountType = 'Liability';
       
insert into Equity
select accountId
  from Account
 where accountType = 'Equity';

insert into Income
select accountId
  from Account
 where accountType = 'Income';

insert into Expense
select accountId
  from Account
 where accountType = 'Expense';

commit;

       