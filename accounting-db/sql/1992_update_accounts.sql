INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Flex Spending Receivable', 'Symantec Flex Spending Receivable', 113, 0, 1, 'Asset', 'Receivable', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 113 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'EDAW Flex Spending Receivable', 'EDAW Flex Spending Receivable', 113, 1, 1, 'Asset', 'Receivable', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 113 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA:  Vanguard Index Extended Market', 'RJM IRA:  Vanguard Index Extended Market', 120, 4, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 120 AND
       defaultSubAccountNumber = 4;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM 401K:  Symantec', 'RJM 401K:  Symantec', 121, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 121 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Objectivity Stock', 'Objectivity Stock', 125, 2, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Stock Purchase Plan', 'Symantec Stock Purchase Plan', 132, 0, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 132 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Stock', 'Symantec Stock', 132, 1, 1, 'Asset', 'Asset', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 132 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'I. Magnin (MLS)', 'I. Magnin (MLS)', 210, 2, 0, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 210 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Computer Loan', 'Symantec Computer Loan', 233, 0, 1, 'Liability', 'Liability', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 233 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Corporation Taxable Income', 'Symantec Corporation Taxable Income', 402, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 402 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Royalties--ORACLE7:  The Complete Reference', 'Royalties--ORACLE7:  The Complete Reference', 450, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 450 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Flex Income', 'Symantec Flex Income', 501, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 501 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'EDAW Flex Income', 'EDAW Flex Income', 501, 1, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 501 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec 401K Excludable Income', 'Symantec 401K Excludable Income', 502, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 502 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'EDAW 401K Excludable Income', 'EDAW 401K Excludable Income', 502, 1, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 502 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Disability Insurance Income', 'State Disability Insurance Income', 503, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 503 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Excludable Section 125 Plan Income', 'Symantec Excludable Section 125 Plan Income', 504, 0, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 504 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'EDAW Excludable Section 125 Plan Income', 'EDAW Excludable Section 125 Plan Income', 504, 1, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 504 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA:  Vanguard Dividends', 'RJM IRA:  Vanguard Dividends', 510, 1, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 510 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'RJM IRA:  Vanguard Capital Gains or Losses', 'RJM IRA:  Vanguard Capital Gains or Losses', 520, 1, 0, 'Income', 'Income', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 520 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Symantec Flex Spending Expense', 'Symantec Flex Spending Expense', 604, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 604 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'EDAW Flex Spending Expense', 'EDAW Flex Spending Expense', 604, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 604 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Income Tax (EDAW)', 'State Income Tax (EDAW)', 610, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 610 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Income Tax (Symantec)', 'State Income Tax (Symantec)', 610, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 610 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Disability Insurance (EDAW)', 'State Disability Insurance (EDAW)', 611, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'State Disability Insurance (Symantec)', 'State Disability Insurance (Symantec)', 611, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Federal Income Tax (EDAW)', 'Federal Income Tax (EDAW)', 740, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 740 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Federal Income Tax (Symantec)', 'Federal Income Tax (Symantec)', 740, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 740 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'FICA (EDAW)', 'FICA (EDAW)', 741, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 741 AND
       defaultSubAccountNumber = 1;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'FICA (Symantec)', 'FICA (Symantec)', 741, 2, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 741 AND
       defaultSubAccountNumber = 2;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Haircut', 'Haircut', 763, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 763 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Sundries', 'Sundries', 764, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 764 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Over-the-Counter Nostrums', 'Over-the-Counter Nostrums', 765, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 765 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Exercise Fees and Expenses', 'Exercise Fees and Expenses', 766, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 766 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Baby-Related Expenses', 'Baby-Related Expenses', 767, 0, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 767 AND
       defaultSubAccountNumber = 0;

INSERT INTO Account (ACCOUNTID, NAME, DESCRIPTION, DEFAULTACCOUNTNUMBER, DEFAULTSUBACCOUNTNUMBER, DEBITDEFAULT, ACCOUNTTYPE, LEAFTYPE, ACTIVE)
VALUES (accountId.NEXTVAL, 'Li Rong Xiao, day care', 'Li Rong Xiao, day care', 767, 1, 1, 'Expense', 'Expense', 1);
UPDATE TempAccount
   SET accountId = accountId.CURRVAL
 WHERE defaultAccountNumber = 767 AND
       defaultSubAccountNumber = 1;

REM Renumbered accounts

UPDATE TempAccount
   SET accountId = 216
 WHERE defaultAccountNumber = 411 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 36
 WHERE defaultAccountNumber = 412 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 64
 WHERE defaultAccountNumber = 725 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 15
 WHERE defaultAccountNumber = 734 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 125
 WHERE defaultAccountNumber = 780 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 205
 WHERE defaultAccountNumber = 780 AND
       defaultSubAccountNumber = 2;

REM Renamed Accounts

UPDATE TempAccount
   SET accountId = 59
 WHERE defaultAccountNumber = 122 AND
       defaultSubAccountNumber = 4;

UPDATE TempAccount
   SET accountId = 26
 WHERE defaultAccountNumber = 122 AND
       defaultSubAccountNumber = 5;

UPDATE TempAccount
   SET accountId = 196
 WHERE defaultAccountNumber = 125 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 153
 WHERE defaultAccountNumber = 126 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 188
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 204
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 18
 WHERE defaultAccountNumber = 400 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 237
 WHERE defaultAccountNumber = 401 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 71
 WHERE defaultAccountNumber = 420 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 233
 WHERE defaultAccountNumber = 421 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 53
 WHERE defaultAccountNumber = 440 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 154
 WHERE defaultAccountNumber = 441 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 225
 WHERE defaultAccountNumber = 442 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 160
 WHERE defaultAccountNumber = 443 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 225
 WHERE defaultAccountNumber = 442 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 170
 WHERE defaultAccountNumber = 510 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 131
 WHERE defaultAccountNumber = 511 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 176
 WHERE defaultAccountNumber = 513 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 169
 WHERE defaultAccountNumber = 514 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 174
 WHERE defaultAccountNumber = 520 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 39
 WHERE defaultAccountNumber = 521 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 25
 WHERE defaultAccountNumber = 531 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 139
 WHERE defaultAccountNumber = 610 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 98
 WHERE defaultAccountNumber = 611 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 117
 WHERE defaultAccountNumber = 710 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 107
 WHERE defaultAccountNumber = 720 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 84
 WHERE defaultAccountNumber = 740 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 108
 WHERE defaultAccountNumber = 741 AND
       defaultSubAccountNumber = 0;

REM Moved accounts (existing accounts with new numbers)

UPDATE TempAccount
   SET accountId = 21
 WHERE defaultAccountNumber = 200 AND
       defaultSubAccountNumber = 1;

UPDATE TempAccount
   SET accountId = 90
 WHERE defaultAccountNumber = 721 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 113
 WHERE defaultAccountNumber = 722 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 120
 WHERE defaultAccountNumber = 723 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 171
 WHERE defaultAccountNumber = 724 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 112
 WHERE defaultAccountNumber = 729 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 184
 WHERE defaultAccountNumber = 730 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 19
 WHERE defaultAccountNumber = 731 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 209
 WHERE defaultAccountNumber = 732 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 133
 WHERE defaultAccountNumber = 733 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 121
 WHERE defaultAccountNumber = 739 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 177
 WHERE defaultAccountNumber = 780 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 164
 WHERE defaultAccountNumber = 781 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 16
 WHERE defaultAccountNumber = 782 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 89
 WHERE defaultAccountNumber = 783 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 11
 WHERE defaultAccountNumber = 791 AND
       defaultSubAccountNumber = 0;

UPDATE TempAccount
   SET accountId = 165
 WHERE defaultAccountNumber = 792 AND
       defaultSubAccountNumber = 0;
