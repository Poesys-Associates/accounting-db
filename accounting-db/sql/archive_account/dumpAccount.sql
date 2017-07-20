set lines 200
set pagesize 0

col accountId heading "Id" format 9999
col defaultAccountNumber heading "Acct" format 9999
col defaultSubAccountNumber heading "Sub" format 999
col name heading "Name" format a100

select accountId, defaultAccountNumber, defaultSubAccountNumber, name
from account
order by 2,3;

select accountId, defaultAccountNumber, defaultSubAccountNumber, name
from tempaccount
order by 2,3;
