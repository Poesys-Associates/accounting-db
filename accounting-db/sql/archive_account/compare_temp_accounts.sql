col accountId heading "Id" format 9999
col acc1 heading "Acc" format 999
col sub1 heading "Sub" format 99
col acc2 heading "Acc" format 999
col sub2 heading "Sub" format 99
col name format a50 heading "Name"

SELECT a.accountId, a.defaultAccountNumber acc1, a.defaultSubAccountNumber sub1,
       t.defaultAccountNumber acc2, t.defaultSubAccountNumber sub2,
       a.name, t.name
  FROM Account a JOIN
       TempAccount t ON a.accountId = t.accountId
 ORDER BY 2, 3;
 
 SELECT defaultAccountNumber acc1, defaultSubAccountNumber sub1, name
   FROM TempAccount
  WHERE accountId IS NULL
  ORDER BY 1, 2;
 