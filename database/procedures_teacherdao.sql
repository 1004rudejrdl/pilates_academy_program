DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTeacher`(IN tID char(30), IN pho char(11), IN pw char(12), IN img varchar(100))
Begin	
	update teachertbl set phone =pho where teacherID=tID;
    update teachertbl set tPassword =pw where teacherID=tID;
    update teachertbl set image =img where teacherID=tID;
end$$
DELIMITER ;
