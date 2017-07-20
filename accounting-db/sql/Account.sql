load data local infile '/Users/muller/dev/accounting/data/Account.csv'
replace into table Account
fields terminated by ','
optionally enclosed by '"'
lines terminated by '\r\n'
(accountId, name, description, defaultAccountNumber, defaultSubAccountNumber, debitDefault, accountType, leafType, active)
