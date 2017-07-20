load data local infile '/Users/muller/dev/accounting/data/Item.csv'
replace into table Item
fields terminated by ','
lines terminated by '\r\n'
(transactionId, accountId, year, amount, debit, checked)
