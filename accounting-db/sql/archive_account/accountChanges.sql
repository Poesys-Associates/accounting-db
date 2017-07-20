REM Lists changes to accounts. Use this to identify specific changes that need 
REM to be made to TempAccount before running the temporary accounts into the
REM FiscalYearAccount table. The objective is to add the new accounts to the
REM Account table, setting the temp account ids, then set the remaining account
REM ids for accounts that have changed by inspection.

REM TODO: Remove this script after system conversion is complete.

define old_year=&1

set lines 300
set pagesize 10000
col name format a50

PROMPT Accounts renumbered

col newAcct heading "New" format a6
col oldAcct heading "Old" format a6

SELECT n.acct || '.' || n.sub newAcct, o.acct || '.' || o.sub oldAcct, o.accountId, n.name
  FROM (SELECT accountNumber acct, subAccountNumber sub, name, accountId
          FROM FiscalYearAccount
         WHERE year = &old_year) o JOIN
       (SELECT defaultAccountNumber acct, defaultSubAccountNumber sub, name 
          FROM TempAccount) n ON o.name = n.name AND (o.acct <> n.acct OR o.sub <> n.sub)
 ORDER BY 1;

PROMPT Accounts renamed or replaced

col oldName heading 'Old Name' format a50
col newName heading 'New Name' format a50
col accountId heading 'Id' format 999

SELECT n.acct || '.' || n.sub newAcct, o.accountId, o.name oldName, n.name newName
  FROM (SELECT accountNumber acct, subAccountNumber sub, name, accountId
          FROM FiscalYearAccount
         WHERE year = &old_year) o JOIN
       (SELECT defaultAccountNumber acct, defaultSubAccountNumber sub, name 
          FROM TempAccount) n ON o.name <> n.name AND o.acct = n.acct AND o.sub = n.sub
 WHERE (n.acct, n.sub, n.name) NOT IN (SELECT n.acct, n.sub, n.name
                                         FROM (SELECT accountNumber acct, subAccountNumber sub, name, accountId
                                                 FROM FiscalYearAccount
                                                WHERE year = &old_year) o JOIN
                                              (SELECT defaultAccountNumber acct, defaultSubAccountNumber sub, name 
                                                 FROM TempAccount) n ON o.name = n.name AND (o.acct <> n.acct OR o.sub <> n.sub)) AND
        o.name <> n.name
 ORDER BY 1;

PROMPT Accounts deleted

col acct heading 'Acct' format 9999
col sub heading 'Sub' format 999

SELECT a.accountNumber acct, a.subAccountNumber sub, a.name, a.accountId
  FROM FiscalYearAccount a LEFT OUTER JOIN 
       TempAccount t ON a.accountNumber = t.defaultAccountNumber AND
                        a.subAccountNumber = t.defaultSubAccountNumber
 WHERE t.defaultAccountNumber IS NULL AND
       a.year = &old_year
 ORDER BY 1, 2;

PROMPT Accounts added
 
SELECT t.defaultAccountNumber acct, t.defaultSubAccountNumber sub, t.name, t.debitDefault
  FROM (SELECT accountNumber, subAccountNumber
          FROM FiscalYearAccount 
         WHERE year = &old_year) a RIGHT OUTER JOIN 
       TempAccount t ON a.accountNumber = t.defaultAccountNumber AND
                        a.subAccountNumber = t.defaultSubAccountNumber
 WHERE a.accountNumber IS NULL AND
       (t.defaultAccountNumber, t.defaultSubAccountNumber, t.name) NOT IN (SELECT n.acct, n.sub, n.name
                                                                             FROM (SELECT accountNumber acct, subAccountNumber sub, name
                                                                                     FROM FiscalYearAccount
                                                                                    WHERE year = &old_year) o JOIN
                                                                                  (SELECT defaultAccountNumber acct, defaultSubAccountNumber sub, name 
                                                                                     FROM TempAccount) n ON o.name = n.name AND (o.acct <> n.acct OR o.sub <> n.sub))
 ORDER BY 1, 2;
 