REM Transfer balance sheet amounts from 1993 into the appropriate 1994 accounts to combine accounts.

INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 'Adjusting entry to combine 1993 balance sheet accounts to 1994', '1-JAN-1994', 1, 1);

REM transfers from prior year

INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 3, 1993, 1715.02, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 13, 1993, 7944.74, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 20, 1993, 779.59, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 27, 1993, 1000, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 28, 1993, 342.65, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 32, 1993, 1576.55, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 38, 1993, 732.5, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 41, 1993, 13132.7, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 42, 1993, 4369.04, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 46, 1993, 2518.68, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 50, 1993, 913.99, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 56, 1993, 21591.94, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 60, 1993, 5005.82, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 94, 1993, 174.5, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 106, 1993, 7480.27, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 110, 1993, 4115.41, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 141, 1993, 15074.15, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 145, 1993, 21190.64, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 167, 1993, 938.93, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 175, 1993, 12.95, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 208, 1993, 4000, 1, 1);
REM transfers to next year

INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 148, 1994, 1715.02, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 331, 1994, 93835.41, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 332, 1994, 19046.69, 0, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 333, 1994, 12.95, 0, 1);

REM Close out MLS asset/liability from prior years to remove accounts from 1994 fiscal year.

INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 'Adjusting entry to erase MLS asset/liability', '31-DEC-1993', 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 1, 1993, 7000, 1, 1);
INSERT INTO Item (transactionId, accountId, year, amount, debit, checked) VALUES (transactionId.CURRVAL, 51, 1993, 7000, 0, 1);
