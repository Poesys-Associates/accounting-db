REM Correct any transactions in 2001 that have a bad date 14-DEC-01 meaning 1901 not 2001.

update transaction set transactionDate = '14-DEC-2001' where transactionid in (21431, 21432) and transactionDate <> '14-DEC-2001';