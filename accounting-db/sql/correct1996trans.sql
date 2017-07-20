REM Transactions to convert from 103.0 to 103.1 (348) in 1996.

REM 2814        100 SF Water Dept., rebate for toilets; transfer from SF Federal, cash back                              03-JAN-96        $2,035.00 DR
REM             103 SF Water Dept., rebate for toilets; transfer from SF Federal, cash back                              03-JAN-96        $2,000.00 CR
REM           145.1 SF Water Dept., rebate for toilets; transfer from SF Federal, cash back                              03-JAN-96           $25.00 CR
REM           144.1 SF Water Dept., rebate for toilets; transfer from SF Federal, cash back                              03-JAN-96           $50.00 CR
REM             109 SF Water Dept., rebate for toilets; transfer from SF Federal, cash back                              03-JAN-96           $40.00 DR
REM 7457        103 SF Federal, savings interest                                                                         31-JAN-96           $15.18 DR
REM           411.2 SF Federal, savings interest                                                                         31-JAN-96           $15.18 CR
REM 4321        103 Transfer from SF Federal to Wells Fargo                                                              14-FEB-96        $1,100.00 CR
REM             100 Transfer from SF Federal to Wells Fargo                                                              14-FEB-96        $1,100.00 DR
REM 1403        103 SF Federal, savings interest                                                                         29-FEB-96           $11.22 DR
REM           411.2 SF Federal, savings interest                                                                         29-FEB-96           $11.22 CR
          
UPDATE item
   SET accountid = 348
 WHERE accountid = 400 AND
       transactionid in (SELECT transactionid
                           FROM transaction
                          WHERE transactiondate < '3-MAR-1996' AND
                                transactionId IN (SELECT transactionid 
                                                    FROM item 
                                                   WHERE year = 1996 AND 
                                                         accountid = 400));

INSERT INTO Transaction VALUES (transactionId.NEXTVAL, 'Transfer balance from SF Fed to 1st Nationwide', '1-MAR-1996', 1, 0);
INSERT INTO Item VALUES (transactionId.CURRVAL, 348, 1996, 2526.80, 0, 1);
INSERT INTO Item VALUES (transactionId.CURRVAL, 400, 1996, 2526.80, 1, 1);
