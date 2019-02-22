DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertHealthData`(IN sID char(30), 
IN measureDate DATE, IN weight DOUBLE
, IN bodyfat Double, IN muscleMass Double, IN bmi Double
)
Begin
	
     insert into healthtbl
		values(sID,measureDate,weight,bodyfat,muscleMass,bmi);
	
end$$
DELIMITER ;
DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectHealthData`()
Begin
	select s.studentID, s.sName, h.measureDate, h.weight, h.bodyfat, h.muscleMass, h.bmi 
        from healthtbl h 
			inner join studenttbl s 
				on h.studentID = s.studentID order by h.measureDate;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectHealthDataByID`(IN sID char(30))
Begin
DECLARE result int default 0;
set result=(select count(*) from healthtbl where studentID = sID);
	select s.studentID, s.sName, h.measureDate, h.weight, h.bodyfat, h.muscleMass, h.bmi ,result
        from healthtbl h 
			inner join studenttbl s 
				on h.studentID = s.studentID  where h.studentID=sID order by h.measureDate;
end$$
DELIMITER ;
