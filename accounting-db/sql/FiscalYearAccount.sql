load data local infile '/Users/muller/dev/accounting/data/FiscalYearAccount.csv'
replace into table FiscalYearAccount
fields terminated by ','
optionally enclosed by '"'
lines terminated by '\r\n'
(accountId, year, accountNumber, subAccountNumber, name, description, transferBalanceTargetAccountId, transferBalanceTargetYear)
