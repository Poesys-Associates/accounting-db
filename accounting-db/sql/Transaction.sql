load data local infile '/Users/muller/dev/accounting/data/Transaction.csv'
replace into table Transaction
fields terminated by '|'
lines terminated by '\r\n'
(transactionId, description, @v1, checked, balance)
set transactionDate = str_to_date(@v1, '%d-%b-%y')
