load data local infile '/Users/muller/dev/accounting/data/ReceivableTransactionItem.csv'
replace into table ReceivableTransactionItem
fields terminated by ','
lines terminated by '\r\n'
(transactionId, accountId, year, receivableAmount, writeoffAmount)
