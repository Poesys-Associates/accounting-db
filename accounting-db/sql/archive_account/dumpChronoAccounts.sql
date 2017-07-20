 col accountId heading "Id" format 9999
 col year heading "Year" format 9999
 col accountNumber heading "Acct" format 999
 col subAccountNumber heading "Sub" format 99
 col name heading "Name" format a50
 col original_name heading "Old Name" format a50
 
 set lines 150
 set pagesize 10000
 
 break on accountId
 
 SELECT fya.accountid, fya.year, fya.accountNumber, fya.subAccountNumber, fya.name, a.name as original_name
   FROM FiscalYearAccount fya JOIN 
        Account a ON fya.accountId = a.accountId
  ORDER BY 1,2,3,4;
  