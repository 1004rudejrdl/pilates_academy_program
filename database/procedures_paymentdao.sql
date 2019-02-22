DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment`(IN sID char(30), IN tID char(30),
IN cour char(4), IN peri int, IN lssns int,IN tui char(15), IN pOpt char(6), 
IN lftls int, IN rDate DATE, IN eDate DATE
)
Begin
  
    insert into paymenttbl values(sID,tID,cour,peri,lssns,tui,pOpt,lftls, rDate, eDate);
    
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectPaymentDataByID`(IN sID char(30))
Begin
	DECLARE result int default 0;
	set result=(select count(*) from paymenttbl where studentID = sID);
    
	select s.studentID, s.sName, t.teacherID, t.tName, p.course, p.period,p.lessons, p.leftLes, p.pOption, p.tuition, p.regidate, p.expidate, result
		from studenttbl s 
			inner join paymenttbl p on s.studentID = p.studentID 
            inner join teachertbl t on t.teacherID = p.teacherID 
            where p.studentID=sID order by s.studentID and p.regiDate;		
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesRevenueByMM`(IN yyyy char(4),IN mm char(2))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE mSales int default 0;
	select sum(tuition) into mSales from paymenttbl where regiDate like concat(concat(concat(yyyy,'-'),mm),'%');
    select mSales;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesRevenueByT`(IN tID char(30))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE tSales int default 0;	
    select sum(tuition) INTO tSales from paymenttbl where teacherid = tid;

    select tSales;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesYearlyByT`(IN tID char(30), IN yyyy char(4), IN qq char(1))
Begin

	DECLARE tSales int default 0;	
      DECLARE tt char(20);
		
	
    
    select concat(tName,substring(phone,8,4)) INTO tt from teachertbl where teacherid = tID;

	select sum(tuition) INTO tSales from paymenttbl where quarter(regiDate) = qq and 
	teacherID=tID and regiDate like concat(yyyy,'%');
   
   if tSales is null then
	select concat(tName,substring(phone,8,4)) INTO tt 
    from teachertbl where teacherID=tID;
    end if;
   
    select tt, tSales;
end$$
DELIMITER ;
