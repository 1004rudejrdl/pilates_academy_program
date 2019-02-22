DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertClassData`(IN tID char(30), IN course char(10),
IN cType char(10), IN classDate DATE, IN classTime char(14), IN classRoom char(3)
)
Begin
	DECLARE limitNum int; -- 변수선언
	DECLARE cInfo char(20);
    DECLARE result char(10) default '성공';
    DECLARE exist_same_teacher int;
	if course = '그룹' then
		set limitNum=10;
	ELSE
		set limitNum=1;
	end if;
    set cInfo = concat(concat((select replace(classDate, '-','')), (select substring(classTime,1,2))),(select substring(classRoom,1,2)));
	SET exist_same_teacher = (SELECT count(*)
                               FROM classtbl
                              WHERE substring(classInfo,1,10) = substring(cInfo,1,10) and teacherid = tID);
 	IF exist_same_teacher !=0 
    THEN set result='중복';
    END IF;
    
    if result='성공' then
     insert into classTbl
		values(cInfo,tID,course,cType,classDate,classTime,classRoom, limitNum, 0);    
	end if;
   select result;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataFromCurDate`()
Begin
	
select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum 
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.classdate>=curdate()
                order by c.classDate;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectTotalClassData`()
Begin
	
select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum 
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID 
                order by c.classDate;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataByTID`(IN tID char(30))
Begin
DECLARE result int DEFAULT 0;
	set result =(select count(*) from classtbl where teacherID=tID);
	select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum , result
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.teacherID=tID order by c.classInfo;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataBySID`(IN sID char(30))
Begin
DECLARE result int DEFAULT 0;
	set result =(select count(*) from cmembertbl where studentID=sID);
	select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum , result
        from teachertbl t 
			inner join classtbl c 
				on c.teacherID = t.teacherID 
			inner join cmembertbl cm
				on cm.classInfo = c.classInfo
             where cm.studentID=sID order by c.classDate;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectCMemberByClassData`(IN cIf char(20))
Begin
SELECT cm.classInfo, s.studentID, s.sName from cmembertbl cm 
	inner join studenttbl s on cm.studentID = s.studentID
    where classInfo= cIf;
    
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataByDate`(IN clsDate DATE)
Begin
DECLARE result int DEFAULT 0;
	set result =(select count(*) from classtbl where classDate=clsDate);
	select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum , result
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.classDate=clsDate order by c.classdate;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateClass`(IN cInfo char(30),
 IN nCourse char(12), IN nType char(4), IN tID char(30))
Begin
	declare result char(6) default '성공';
    declare flag int default 0;
    
    
    select count(*) into flag from classtbl where classInfo = cInfo and course = nCourse and cType = nType and teacherID=tID;
    
   IF flag <>0  then    
		set result ='중복';
    else		
		
		update classTbl set course =nCourse where classinfo=cInfo;
        update classTbl set cType =nType where classinfo=cInfo;	
		update classTbl set teacherID =tID where classinfo=cInfo;
		
	end if;
    
    select result, cInfo;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertNewClsMember2`(IN clsInfo char(20), IN sss char(30))
Begin
    DECLARE exist_flag char(5) default '성공';
    DECLARE sccd_or_fl char(5) default '성공';
    DECLARE date_flag char(5) default '성공';
    DECLARE num_flag char(5) default '성공';
	DECLARE crs_flag char(5) default '성공';
	Declare rDate char(15) default null;
    Declare clsDate char(15) default null;
    DECLARE total int default 0;
    declare lefttt1 int default 0;
    DECLARE sameDT int default 0;
    DECLARE nowN int default 0;
    DECLARE limitN int default 0;
    DECLARE crs_count int default 0;
    
	#같은 날짜인지 확인 
   SELECT count(*) INTO sameDT from cmembertbl 
        where classInfo like concat(substring(clsInfo,1,10),'%') and studentID=sss;
    
	set rDate = (select max(expiDate) from paymenttbl where studentID=sss having max(expiDate)-curdate() >= 0);    
	set @aa:=0;
    select classDate INTO clsdate from classtbl where classInfo = clsInfo; 
    #결제 내역의 코스 그룹수업 or 개인수업
    SELECT count(expidate) INTO crs_count from paymenttbl where expiDate>=curdate() and studentID=sss and 
	course=(SELECT course from classtbl where classinfo = clsInfo);
    
    if crs_count > 0 then
		set crs_flag ='성공';
	else 
		set crs_flag ='코스불일치';
	end if;
    
    #지난 날짜 확인 
    if  clsDate >=curdate() then     
     
     set date_flag = '성공';
	
	else 
     set date_flag = '지난날짜';
	end if;    
    
    select limitNum INTO limitN from classtbl where classInfo = clsInfo;    
    select nowNum INTO nowN from classtbl where classInfo = clsInfo;
    
    if limitN > nowN then
		set num_flag ='성공';     
	else        
        set num_flag = '정원초과';
        
	end if;
    
	if sameDT = 0  then    
    
		insert into cmembertbl values(clsInfo,sss);
	
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) where classInfo = clsInfo;
		
		set exist_flag = '성공';
	ELSE
    
		set exist_flag = '중복';
        
	end if;        	
    
	if num_flag='성공' and crs_flag ='성공' and exist_flag='성공' and rDate is not null and date_flag = '성공' then
	
		select sum(leftLes) INTO @aa from paymenttbl where studentID=sss and paymenttbl.expiDate-curdate() > 0 ;
		set total=@aa;
        set lefttt1 = total - 1;
		update paymenttbl set leftles = lefttt1 where expiDate =rDate and studentID=sss;
		set sccd_or_fl='성공';
                        
	ELSE
		set sccd_or_fl ='실패';      
       
	end if;
	select num_flag, crs_flag, date_flag,exist_flag, sccd_or_fl, lefttt1;
end$$
DELIMITER ;

DELIMITER $$
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_delClsMemberData`(IN clsInfo char(20), IN sID char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE cnt int default 0;
	DECLARE sccd_or_fl char(5) default '성공';
    declare lefttt1 int default 0;
    declare left_cnt int default 0;
	Declare rDate char(15) default null;
	set @dd:=0;
    set @lf:=0;
	select count(*) INTO @dd from classtbl where classinfo=clsInfo 
    GROUP BY classtbl.classdate 
    having classtbl.classDate- curdate() >0 ;
	
    if @dd = 0 then
		set result ='지난날짜';
	else
		set result ='성공';
	end if;
  
   SELECT count(*) INTO cnt from cmembertbl 
        where classInfo =clsInfo and studentID=sID;
	set rDate = (select max(expiDate) from paymenttbl where studentID=sID having max(expiDate)-curdate() >= 0);    
	if cnt > 0 and result='성공' then    
		#기간 내 총 잔여 횟수 
		select sum(leftLes) INTO @lf from paymenttbl where studentID=sID and paymenttbl.expiDate-curdate() > 0 ;        
    
		DELETE from cmembertbl where classInfo = clsInfo and studentID=sID;
        
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo and studentID=sID) 
        where classInfo = clsInfo;
        
        
        select count(classtbl.classDate) INTO lefttt1 from classtbl 
					inner join cmembertbl 
					on cmembertbl.classinfo = classtbl.classInfo
					where classtbl.classdate >= curdate()
					and cmembertbl.studentID = sID;
		
        
        set left_cnt = @lf +1;
        
		update paymenttbl set leftles = left_cnt where expiDate =rDate and studentID=sID;        
        
		set sccd_or_fl = '성공';	    
        
	ELSE
			
        set sccd_or_fl= '없음';
	end if;    
    
	
    select result, sccd_or_fl, @lf,lefttt1,left_cnt;
end$$
DELIMITER ;

