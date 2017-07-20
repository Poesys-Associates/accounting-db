load data local infile '/Users/muller/dev/accounting/data/FiscalYear.csv'
replace into table FiscalYear
fields terminated by ','
lines terminated by '\r\n'
(year, @v1, @v2)
set startDate = str_to_date(@v1, '%d-%b-%y'), endDate = str_to_date(@v2, '%d-%b-%y')
