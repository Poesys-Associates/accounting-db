select transactionId, count(*)
from Item
group by transactionId
having count(*) >= 10
order by 2;

select * from Item where transactionId = 25338;

select * from Transaction where transactionId = 25338;
