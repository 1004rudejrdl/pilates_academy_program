DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent`(IN sID char(30), IN pho char(11), IN pw char(12), IN img varchar(100))
Begin
	
	update studenttbl set phone =pho where studentID=sID;
    update studenttbl set sPassword =pw where studentID=sID;
    update studenttbl set image =img where studentID=sID;
end$$
DELIMITER ;
