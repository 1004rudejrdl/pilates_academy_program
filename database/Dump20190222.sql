-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: pilatesdb
-- ------------------------------------------------------
-- Server version	5.7.10-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classtbl`
--

DROP TABLE IF EXISTS `classtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `classtbl` (
  `classInfo` char(20) NOT NULL,
  `teacherID` char(30) NOT NULL,
  `course` char(10) NOT NULL,
  `cType` char(10) NOT NULL,
  `classDate` date NOT NULL,
  `classTime` char(14) NOT NULL,
  `classRoom` char(10) NOT NULL,
  `limitNum` int(11) NOT NULL DEFAULT '1',
  `nowNum` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`classInfo`),
  KEY `fk_classtbl_teacherID` (`teacherID`),
  CONSTRAINT `fk_classtbl_teacherID` FOREIGN KEY (`teacherID`) REFERENCES `teachertbl` (`teacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classtbl`
--

LOCK TABLES `classtbl` WRITE;
/*!40000 ALTER TABLE `classtbl` DISABLE KEYS */;
INSERT INTO `classtbl` VALUES ('2019021408A','jomj@nate.com','그룹','매트','2019-02-14','08:00-08:50','A',10,5),('2019021411A','leeyl@nate.com','그룹','리포머','2019-02-14','11:00-11:50','A',10,3),('2019021507A','kimdj@nate.com','그룹','리포머','2019-02-15','07:00-07:50','A',10,4),('2019021507B','jomj@nate.com','개인','매트','2019-02-15','07:00-07:50','B',1,1),('2019021508C','jomj@nate.com','그룹','리포머','2019-02-15','08:00-08:50','C',10,1),('2019021808B','cho@nate.com','그룹','매트','2019-02-18','08:00-08:50','B',10,2),('2019021908A','jomj@nate.com','그룹','리포머','2019-02-19','08:00-08:50','A',10,2),('2019021910A','kimdj@nate.com','그룹','리포머','2019-02-19','10:00-10:50','A',10,0),('2019022008C','minjk@nate.com','그룹','리포머','2019-02-20','08:00-08:50','C',10,3),('2019022009A','leeyl@nate.com','그룹','리포머','2019-02-20','09:00-09:50','A',10,0),('2019022019C','yunhs@nate.com','그룹','리포머','2019-02-20','19:00-19:50','C',10,2),('2019022022D','lejmi@nate.com','그룹','매트','2019-02-20','22:00-22:50','D',10,0),('2019022110A','kimdj@nate.com','그룹','리포머','2019-02-21','10:00-10:50','A',10,1),('2019022207A','kimdj@nate.com','그룹','리포머','2019-02-22','07:00-07:50','A',10,0),('2019022222B','kimdj@nate.com','그룹','리포머','2019-02-22','22:00-22:50','B',10,0),('2019022320A','jomj@nate.com','개인','리포머','2019-02-23','20:00-20:50','A',1,1),('2019022610A','jomj@nate.com','개인','리포머','2019-02-26','10:00-10:50','A',1,1),('2019022622A','kimdj@nate.com','개인','매트','2019-02-26','22:00-22:50','A',1,1),('2019022709B','kimdj@nate.com','그룹','리포머','2019-02-27','09:00-09:50','B',10,1),('2019030110A','cho@nate.com','그룹','리포머','2019-03-01','10:00-10:50','A',10,0),('﻿2019022109A','그룹','그룹','리포머','2019-02-21','09:00-09:50','A',10,1),('﻿2019022209A','cho@nate.com','그룹','리포머','2019-02-22','09:00-09:50','A',10,0);
/*!40000 ALTER TABLE `classtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cmembertbl`
--

DROP TABLE IF EXISTS `cmembertbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cmembertbl` (
  `classInfo` char(20) NOT NULL,
  `studentID` char(30) NOT NULL,
  KEY `fk_cMemberTbl_classInfo` (`classInfo`),
  KEY `fk_cMemberTbl_studentID` (`studentID`),
  CONSTRAINT `fk_cMemberTbl_classInfo` FOREIGN KEY (`classInfo`) REFERENCES `classtbl` (`classInfo`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_cMemberTbl_studentID` FOREIGN KEY (`studentID`) REFERENCES `studenttbl` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cmembertbl`
--

LOCK TABLES `cmembertbl` WRITE;
/*!40000 ALTER TABLE `cmembertbl` DISABLE KEYS */;
INSERT INTO `cmembertbl` VALUES ('2019021408A','kmj@nate.com'),('2019022709B','kmj@nate.com'),('2019021411A','kmj@nate.com'),('2019021507A','kmj@nate.com'),('2019022019C','kmj@nate.com'),('2019021408A','jomj@nate.com'),('2019021507A','jomj@nate.com'),('2019021808B','jomj@nate.com'),('2019021908A','jomj@nate.com'),('2019022008C','jomj@nate.com'),('2019021408A','jogh@nate.com'),('2019021411A','jogh@nate.com'),('2019021507A','jogh@nate.com'),('2019021808B','jogh@nate.com'),('2019021908A','jogh@nate.com'),('2019021411A','eunyj@nate.com'),('2019021408A','eunyj@nate.com'),('2019022008C','eunyj@nate.com'),('2019022019C','eunyj@nate.com'),('2019021408A','parkbm@nate.com'),('2019021507A','parkbm@nate.com'),('2019021508C','parkbm@nate.com'),('2019022008C','parkbm@nate.com'),('2019022622A','kmj@nate.com'),('﻿2019022109A','kmj@nate.com'),('2019022110A','kmj@nate.com');
/*!40000 ALTER TABLE `cmembertbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `healthtbl`
--

DROP TABLE IF EXISTS `healthtbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `healthtbl` (
  `studentID` char(30) NOT NULL,
  `measureDate` date NOT NULL,
  `weight` double NOT NULL,
  `bodyfat` double NOT NULL,
  `muscleMass` double NOT NULL,
  `bmi` double NOT NULL,
  KEY `fk_weighttbl_studentID` (`studentID`),
  CONSTRAINT `fk_weighttbl_studentID` FOREIGN KEY (`studentID`) REFERENCES `studenttbl` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `healthtbl`
--

LOCK TABLES `healthtbl` WRITE;
/*!40000 ALTER TABLE `healthtbl` DISABLE KEYS */;
INSERT INTO `healthtbl` VALUES ('kmj@nate.com','2019-02-13',45,19,20,19),('kmj@nate.com','2018-01-10',46,20,25,18.2),('kmj@nate.com','2018-03-22',49,20.2,20,18.2),('kmj@nate.com','2018-04-25',50,22,29,18.2),('kmj@nate.com','2018-06-14',46.8,18,25,20),('kmj@nate.com','2018-07-19',47.94,21.9,23.51,47.67),('kmj@nate.com','2018-09-14',48.25,18.36,19.89,46.47),('kmj@nate.com','2018-10-14',46.69,18.46,19.41,49.4),('kmj@nate.com','2017-02-14',46.83,20,25,18.2),('kmj@nate.com','2017-01-14',47.75,20,25,18.2),('kmj@nate.com','2017-03-14',47.16,20,25,18.2),('kmj@nate.com','2017-04-01',49.16,20,25,18.2),('kmj@nate.com','2017-05-14',46.32,20,25,18.2),('kmj@nate.com','2017-06-01',48.71,20,25,18.2),('kmj@nate.com','2019-02-14',48.58,20,25,18.2),('kmj@nate.com','2017-07-01',47.02,20,25,18.2),('kmj@nate.com','2017-08-01',46.12,18.97,22.65,46.91),('kmj@nate.com','2017-09-19',49.26,20.46,22.75,48.74),('kmj@nate.com','2017-10-01',46.64,20.67,23.21,49.16),('kmj@nate.com','2017-11-01',46.25,20.6,20.11,46.24),('kmj@nate.com','2017-12-02',47.32,18.69,21.55,48.64),('kmj@nate.com','2018-11-01',49.25,19.56,21.28,46.55),('kmj@nate.com','2018-12-01',48.62,21.09,20.45,47.65),('kmj@nate.com','2019-01-12',49.75,18.88,22.02,49.92),('kmj@nate.com','2019-02-16',46,20,10,20),('kmj@nate.com','2018-08-01',46.76,19.47,20.59,46.24),('kmj@nate.com','2018-02-15',48.93,19.89,19.25,47.32),('kmj@nate.com','2018-02-22',46.85,20.49,21.47,49.04),('kmj@nate.com','2018-02-21',47.46,18.98,21.69,48.22),('kmj@nate.com','2019-02-21',47.21,18.97,22.69,49.42),('kmj@nate.com','2019-02-28',49.33,18.73,19.79,47.4);
/*!40000 ALTER TABLE `healthtbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paymenttbl`
--

DROP TABLE IF EXISTS `paymenttbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `paymenttbl` (
  `studentID` char(30) NOT NULL,
  `teacherID` char(30) DEFAULT NULL,
  `course` char(4) NOT NULL,
  `period` char(8) NOT NULL,
  `lessons` char(8) NOT NULL,
  `tuition` char(15) NOT NULL,
  `pOption` char(6) NOT NULL,
  `leftLes` int(11) DEFAULT NULL,
  `regiDate` date NOT NULL,
  `expiDate` date NOT NULL,
  KEY `fk_paymenttbl_studentID` (`studentID`),
  KEY `fk_paymenttbl_teacherID` (`teacherID`),
  KEY `idx_paymenttbl_regiDate` (`regiDate`),
  CONSTRAINT `fk_paymenttbl_studentID` FOREIGN KEY (`studentID`) REFERENCES `studenttbl` (`studentID`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_paymenttbl_teacherID` FOREIGN KEY (`teacherID`) REFERENCES `teachertbl` (`teacherID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paymenttbl`
--

LOCK TABLES `paymenttbl` WRITE;
/*!40000 ALTER TABLE `paymenttbl` DISABLE KEYS */;
INSERT INTO `paymenttbl` VALUES ('eunyj@nate.com','그룹','그룹','3','36','600000','카드',36,'2019-02-13','2019-05-13'),('jogh@nate.com','cho@nate.com','개인','3','36','900000','카드',36,'2019-02-13','2019-05-13'),('jomj@nate.com','cho@nate.com','개인','3','36','900000','카드',36,'2019-02-13','2019-05-13'),('jsh@nate.com','jangsk@nate.com','개인','3','36','900000','카드',36,'2019-02-13','2019-05-13'),('leewj@nate.com','kimdj@nate.com','개인','3','36','900000','카드',36,'2019-02-13','2019-05-13'),('osh@nate.com','kimkd@nate.com','개인','3','36','900000','카드',36,'2019-02-13','2019-05-13'),('parkbm@nate.com','그룹','그룹','3','36','600000','카드',32,'2019-02-13','2019-05-13'),('songmj@nate.com','그룹','그룹','3','36','600000','카드',36,'2019-02-13','2019-05-13'),('songys@nate.com','그룹','그룹','3','36','600000','카드',36,'2019-02-13','2019-05-13'),('eunyj@nate.com','그룹','그룹','3','36','600000','카드',211,'2019-05-14','2019-08-13'),('jogh@nate.com','그룹','그룹','3','36','600000','카드',38984,'2019-05-14','2019-08-13'),('jomj@nate.com','cho@nate.com','개인','3','36','900000','카드',38984,'2019-05-14','2019-08-13'),('jsh@nate.com','jangsk@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('jungsh@nate.com','kimdj@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('kimjr@nate.com','jomj@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('kimmj@nate.com','yunhs@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('leewj@nate.com','lejmi@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('ljs@nate.com','﻿kimhy@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('osh@nate.com','leeyl@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('jomj@nate.com','그룹','그룹','3','36','900000','카드',38984,'2019-05-14','2019-08-13'),('jogh@nate.com','minjk@nate.com','개인','3','36','900000','카드',38984,'2019-05-14','2019-08-13'),('jungsh@nate.com','minjk@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('jungsh@nate.com','minjk@nate.com','개인','3','36','900000','카드',36,'2019-05-14','2019-08-13'),('jogh@nate.com','minjk@nate.com','개인','3','36','900000','카드',38984,'2019-05-14','2019-08-13'),('jomj@nate.com','그룹','그룹','3','36','900000','카드',38984,'2019-05-14','2019-08-13'),('eunyj@nate.com','그룹','그룹','1','12','600000','카드',0,'2019-01-01','2019-02-01'),('jsh@nate.com','그룹','그룹','1','12','600000','카드',0,'2019-01-01','2019-02-01'),('jsh@nate.com','그룹','그룹','1','12','600000','카드',0,'2019-01-01','2019-02-01'),('sym@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('ljs@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('osh@nate.com','jangsk@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('jomj@nate.com','lejmi@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('kimjr@nate.com','oheey@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('songys@nate.com','jomj@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('songmj@nate.com','lejmi@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('yoonbm@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('parkbm@nate.com','그룹','그룹','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('leewj@nate.com','minjk@nate.com','개인','1','12','900000','카드',0,'2019-01-01','2019-02-01'),('jsh@nate.com','그룹','그룹','1','12','600000','카드',0,'2018-12-01','2018-12-31'),('sym@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('ljs@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('osh@nate.com','jangsk@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('jomj@nate.com','lejmi@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('kimjr@nate.com','oheey@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('songys@nate.com','jomj@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('songmj@nate.com','lejmi@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('yoonbm@nate.com','cho@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('parkbm@nate.com','그룹','그룹','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('leewj@nate.com','minjk@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('eunyj@nate.com','minjk@nate.com','개인','1','12','900000','카드',0,'2018-12-01','2018-12-31'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-11-01','2018-11-30'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-11-01','2018-11-30'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-09-30','2018-10-22'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-09-30','2018-10-22'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-07-18','2018-08-18'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-07-18','2018-08-18'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-06-18','2018-07-18'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-06-18','2018-07-18'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-05-18','2018-06-18'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-05-18','2018-06-18'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-04-18','2018-05-18'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-04-18','2018-05-18'),('jsh@nate.com','그룹','그룹','1','12','200000','카드',0,'2018-03-18','2018-04-18'),('sym@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('ljs@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('songys@nate.com','jomj@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('parkbm@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','300000','카드',0,'2018-03-18','2018-04-18'),('jsh@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-02-18','2018-03-18'),('sym@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('ljs@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('songys@nate.com','jomj@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('parkbm@nate.com','그룹','그룹','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','400000','카드',0,'2018-02-18','2018-03-18'),('jsh@nate.com','그룹','그룹','1','12','300000','카드',0,'2018-01-18','2018-02-18'),('sym@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('ljs@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('osh@nate.com','jangsk@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('jomj@nate.com','lejmi@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('jogh@nate.com','﻿kimhy@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('jungsh@nate.com','leeyl@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('kimjr@nate.com','oheey@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('songys@nate.com','jomj@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('songmj@nate.com','lejmi@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('kimmj@nate.com','yunhs@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('yoonbm@nate.com','cho@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('parkbm@nate.com','그룹','그룹','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('leewj@nate.com','minjk@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('eunyj@nate.com','minjk@nate.com','개인','1','12','400000','카드',0,'2018-01-18','2018-02-18'),('kmj@nate.com','그룹','그룹','3','36','900000','카드',29,'2019-05-14','2019-08-13'),('jsh@nate.com','그룹','그룹','1','12','300000','카드',12,'2018-10-23','2018-10-31'),('sym@nate.com','cho@nate.com','개인','1','12','400000','카드',12,'2018-10-23','2018-10-31'),('ljs@nate.com','cho@nate.com','개인','1','12','400000','카드',12,'2018-10-23','2018-10-31'),('osh@nate.com','jangsk@nate.com','개인','1','12','400000','카드',12,'2018-10-23','2018-10-31');
/*!40000 ALTER TABLE `paymenttbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `studenttbl`
--

DROP TABLE IF EXISTS `studenttbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `studenttbl` (
  `studentID` char(30) NOT NULL,
  `sPassword` char(12) NOT NULL,
  `sName` char(12) NOT NULL,
  `phone` char(11) NOT NULL,
  `gender` char(4) NOT NULL,
  `birthDate` date NOT NULL,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`studentID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `studenttbl`
--

LOCK TABLES `studenttbl` WRITE;
/*!40000 ALTER TABLE `studenttbl` DISABLE KEYS */;
INSERT INTO `studenttbl` VALUES ('cherry09@cherish.com','111111','김체리','01055882345','여성','2007-09-21','stu1550205956332_fe07.jpg'),('eunyj@nate.com','222241','은영재','01012343990','남성','1994-01-17','man13.jpg'),('jogh@nate.com','222232','조건희','01072343367','남성','1988-01-08','man10.jpg'),('jomj@nate.com','222231','조민수','01044343399','남성','1996-01-07','조민수.jpg'),('jsh@nate.com','222222','전승현','01012343113','남성','1993-04-02','man02.jpg'),('jungsh@nate.com','222233','정승환','01062343333','남성','1990-01-09','man11.jpg'),('kimjr@nate.com','222234','김재록','01012343666','남성','1989-01-10','김재록.jpg'),('kimmin@nate.com','111111','김민','01055555555','여성','1993-03-03','stu1550091429600_fe14.jpg'),('kimmj@nate.com','222237','김민주','01012343997','여성','1991-01-13','fe04.jpg'),('kmj@nate.com','222222','김민지','01011883300','여성','1994-01-06','stu1550455777288_fe26.jpg'),('leewj@nate.com','222240','이우주','01012343996','여성','1995-01-16','fe07.jpg'),('ljs@nate.com','222224','이주석','01042343333','남성','1995-04-04','man04.jpg'),('osh@nate.com','222225','오성훈','01002343123','남성','1995-04-05','오성훈.jpg'),('parkbm@nate.com','222239','박보미','01012343995','여성','1993-01-15','fe06.jpg'),('songmj@nate.com','222236','송민주','01012343998','여성','1991-01-12','fe03.jpg'),('songys@nate.com','222235','송용수','01012343999','남성','1991-01-11','man12.jpg'),('sym@nate.com','222223','손영민','01012243333','남성','1990-04-03','man03.jpg'),('yoonbm@nate.com','222238','윤보미','01012343996','여성','1993-01-14','fe05.jpg'),('﻿scw@nate.com','222221','성찬우','01012343000','남성','1997-04-01','man01.jpg');
/*!40000 ALTER TABLE `studenttbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teachertbl`
--

DROP TABLE IF EXISTS `teachertbl`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teachertbl` (
  `teacherID` char(30) NOT NULL,
  `tPassword` char(12) NOT NULL,
  `tName` char(12) NOT NULL,
  `phone` char(16) NOT NULL,
  `gender` char(4) NOT NULL,
  `birthDate` date NOT NULL,
  `image` varchar(100) NOT NULL,
  PRIMARY KEY (`teacherID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teachertbl`
--

LOCK TABLES `teachertbl` WRITE;
/*!40000 ALTER TABLE `teachertbl` DISABLE KEYS */;
INSERT INTO `teachertbl` VALUES ('123herry@cherish.com','5555','김체리','01099885511','여성','2007-09-21','tch1550101559748_fe11.jpg'),('cho@nate.com','111122','조민지','01076678895','여성','1985-05-12','fe16.jpg'),('jangsk@nate.com','111115','장승국','01077778888','남성','1993-05-05','man16.jpg'),('jomj@nate.com','111119','조명지','01073378892','여성','1992-05-09','fe13.jpg'),('kimdj@nate.com','111116','김동진','01077878889','남성','1966-05-06','tch1550453312778_man04.jpg'),('kimkd@nate.com','111112','김경덕','01066668888','남성','1993-05-30','man13.jpg'),('leeyl@nate.com','111117','이율','01071118890','여성','1989-05-07','fe08.jpg'),('lejmi@nate.com','111120','이주미','01074478893','여성','1992-05-10','fe19.jpg'),('minjk@nate.com','111114','민제경','01066660000','남성','1992-05-04','man15.jpg'),('oheey@nate.com','111118','오희연','01072278891','여성','1991-05-08','fe12.jpg'),('yunhs@nate.com','111121','윤희수','01075578894','여성','1987-05-11','fe15.jpg'),('그룹','111123','그룹','01078978896','여성','1989-05-13','fe17.jpg'),('﻿kimhy@nate.com','111111','김하영','01066667777','남성','1989-05-01','man12.jpg');
/*!40000 ALTER TABLE `teachertbl` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'pilatesdb'
--

--
-- Dumping routines for database 'pilatesdb'
--
/*!50003 DROP PROCEDURE IF EXISTS `delClsMemberData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delClsMemberData`(IN clsInfo char(20), IN sID char(30))
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
    
	
    select result, sccd_or_fl,left_cnt;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delClsMemberData11` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delClsMemberData11`(IN clsInfo char(20), IN sID char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE cnt int default 0;
	DECLARE sccd_or_fl char(5) default '성공';
	DECLARE total int default 0;
    declare lefttt1 int default 0;
	Declare rDate char(15) default null;
	set @dd:=0;
    set @lf:=0;
	select @dd:=count(*) from classtbl where classinfo=clsInfo 
    GROUP BY classtbl.classdate 
    having classtbl.classDate- curdate() >0 ;
	
    if @dd = 0 then
		set result ='지난날짜';
	else
		set result ='성공';
	end if;
  
    set cnt = (
		SELECT count(*) from cmembertbl 
        where classInfo =clsInfo and studentID=sID	);
	set rDate = (select max(expiDate) from paymenttbl where studentID=sID having max(expiDate)-curdate() >= 0);    
	if cnt > 0 and result='성공' then    
		DELETE from cmembertbl where classInfo = clsInfo and studentID=sID;
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) 
        where classInfo = clsInfo;
        
        set lefttt1 = (select count(classtbl.classDate) from classtbl 
					inner join cmembertbl 
					on cmembertbl.classinfo = classtbl.classInfo
					where classtbl.classdate >= curdate()
					and cmembertbl.studentID = '11@nate.com');
		select @lf:=sum(leftLes), expiDate from paymenttbl where studentID=sID having paymenttbl.expiDate-curdate() > 0 ;
		set total=@lf;
        set lefttt1 = total - lefttt1;
		update paymenttbl set leftles = lefttt1 where expiDate =rDate and studentID=sID;        
        
		set sccd_or_fl = '성공';	
    
        
	ELSE
			
        set sccd_or_fl= '없음';
	end if;    
    
	select result, sccd_or_fl;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `delClsMemberData_past` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `delClsMemberData_past`(IN clsInfo char(20), IN sID char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE cnt int default 0;
  
    set cnt = (
		SELECT count(*) from cmembertbl 
        where classInfo =clsInfo and studentID=sID	);

	if cnt = 0 then    
		set result = '없음';
	ELSE
		DELETE from cmembertbl where classInfo = clsInfo and studentID=sID;
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) where classInfo = clsInfo;

		set result = '성공';		
        
	end if;    
    
	select result;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `exerciseClassData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `exerciseClassData`(IN tID char(30), IN course char(10),
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSales` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSales`(IN yy char(4), IN qq char(1))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE qSales int default 0;

	
    select sum(tuition) INTO qSales from paymenttbl where quarter(regiDate) =qq and regiDate like concat(yy,'%');

    select qSales;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSalesRevenue` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesRevenue`(IN yy char(4), IN qq char(1))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE qSales int default 0;	
    select sum(tuition) INTO qSales from paymenttbl where quarter(regiDate) =qq and regiDate like concat(yy,'%');

    select qSales;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSalesRevenueByMM` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesRevenueByMM`(IN yyyy char(4),IN mm char(2))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE mSales int default 0;
	select sum(tuition) into mSales from paymenttbl where regiDate like concat(concat(concat(yyyy,'-'),mm),'%');
    select mSales;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSalesRevenueByT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `getSalesRevenueByT`(IN tID char(30))
Begin
    #DECLARE result char(5) default '성공';
    DECLARE tSales int default 0;	
    select sum(tuition) INTO tSales from paymenttbl where teacherid = tid;

    select tSales;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `getSalesYearlyByT` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertClassData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertClsMemberData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertClsMemberData`(IN clsInfo char(20), IN sID char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE sameDT int default 0;
  
    set sameDT = 
    (
		SELECT count(*) from cmembertbl 
        where classInfo like concat(substring(clsInfo,1,10),'%') and studentID=sID
	);

	if sameDT = 0 then    
		insert into cmembertbl values(clsInfo,sID);
	
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) where classInfo = clsInfo;

		set result = '성공';
	ELSE
    
		set result = '중복';
        
	end if;    
    
	select result;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertHealthData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertHealthData`(IN sID char(30), 
IN measureDate DATE, IN weight DOUBLE
, IN bodyfat Double, IN muscleMass Double, IN bmi Double
)
Begin
	
     insert into healthtbl
		values(sID,measureDate,weight,bodyfat,muscleMass,bmi);
	
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertNewClsMember` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertNewClsMember`(IN clsInfo char(20), IN sss char(30))
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
		 set lefttt1 = (select count(classtbl.classDate) from classtbl 
					inner join cmembertbl 
					on cmembertbl.classinfo = classtbl.classInfo
					where classtbl.classdate >= curdate()
					and cmembertbl.studentID = sss);
		select sum(leftLes) INTO @aa from paymenttbl where studentID=sss and paymenttbl.expiDate-curdate() > 0 ;
		set total=@aa;
        set lefttt1 = total - lefttt1;
		update paymenttbl set leftles = lefttt1 where expiDate =rDate and studentID=sss;
		set sccd_or_fl='성공';
                        
	ELSE
		set sccd_or_fl ='실패';      
       
	end if;
	select num_flag, crs_flag, date_flag,exist_flag, sccd_or_fl;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertNewClsMember2` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertPayment` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPayment`(IN sID char(30), IN tID char(30),
IN cour char(4), IN peri int, IN lssns int,IN tui char(15), IN pOpt char(6), 
IN lftls int, IN rDate DATE, IN eDate DATE
)
Begin
  
    insert into paymenttbl values(sID,tID,cour,peri,lssns,tui,pOpt,lftls, rDate, eDate);
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `insertPaymentData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `insertPaymentData`(IN sID char(30), IN tID char(30),
IN course char(4), IN peri int, IN pOption char(6), IN cDate DATE
)
Begin
	DECLARE ls int DEFAULT 0; -- 변수선언
	DECLARE tuition int;
    DECLARE tuitionString char(15);
	DECLARE leftls int;
    DECLARE teachID char(30) default null;
    DECLARE rDate Date;
    DECLARE eDate Date;
    DECLARE result char(6) default "성공";
    
    set ls = (select peri*@TIMES);
    set leftls =(select peri*@TIMES);
	
    set rDate = cDate;
    set eDate =  date_add(cDate, INTERVAL peri Month);
    
	if course = '그룹' then
		set teachID = "그룹";
        set tuition = (select peri*@GROUPTUITION);
	ELSE
		set teachID = tID;
          set tuition = (select peri*@PERSONALTUITION);
	end if;    
    
    set tuitionString= ( select format(tuition,0));
    
    
    
    insert into paymenttbl values(sID,teachID,course,peri,44,tuitionString,pOption,leftls, rDate, eDate);
    
    select sID,teachID,course,peri,44,tuitionString,pOption,leftls, rDate, eDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new2insertClsMemberData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new2insertClsMemberData`(IN clsInfo char(20), IN sss char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE sccd_or_fl char(5) default '성공';
    DECLARE date_check char(5) default '성공';
    DECLARE num_check char(5) default '성공';
	DECLARE crs_check char(5) default '성공';
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
		set crs_check ='코스일치';
	else 
		set crs_check ='코스불일치';
	end if;
    
    #지난 날짜 확인 
    if  clsDate >=curdate() then     
     
     set date_check = '성공';
	
	else 
     set date_check = '지난날짜';
	end if;    
    
    select limitNum INTO limitN from classtbl where classInfo = clsInfo;    
    select nowNum INTO nowN from classtbl where classInfo = clsInfo;
    
    if limitN > nowN then
		set num_check ='성공';     
	else        
        set num_check = '정원초과';
        
	end if;
    
	if sameDT = 0  then    
    
		insert into cmembertbl values(clsInfo,sss);
	
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) where classInfo = clsInfo;
		
		set result = '성공';
	ELSE
    
		set result = '중복';
        
	end if;        	
    
	if num_check='성공' and crs_check ='코스일치' and result='성공' and rDate is not null and date_check = '성공' then
		 set lefttt1 = (select count(classtbl.classDate) from classtbl 
					inner join cmembertbl 
					on cmembertbl.classinfo = classtbl.classInfo
					where classtbl.classdate >= curdate()
					and cmembertbl.studentID = sss);
		select sum(leftLes) INTO @aa from paymenttbl where studentID=sss and paymenttbl.expiDate-curdate() > 0 ;
		set total=@aa;
        set lefttt1 = total - lefttt1;
		update paymenttbl set leftles = lefttt1 where expiDate =rDate and studentID=sss;
		set sccd_or_fl='성공';
                        
	ELSE
		set sccd_or_fl ='실패';      
       
	end if;
	select result, crs_check, date_check,num_check, sccd_or_fl;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_delClsMemberData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `new_insertNewClsMember` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `new_insertNewClsMember`(IN clsInfo char(20),IN sss char(30))
Begin
    DECLARE result char(5) default '성공';
    DECLARE sccd_or_fl char(5) default '성공';
    DECLARE date_check char(5) default '성공';
	Declare rDate char(15) default null;
    Declare clsDate char(15) default null;
    DECLARE total int default 0;
    declare lefttt1 int default 0;
    DECLARE sameDT int default 0;
  
   SELECT count(*) INTO sameDT from cmembertbl 
        where classInfo like concat(substring(clsInfo,1,10),'%') and studentID=sss;
    
	set rDate = (select max(expiDate) from paymenttbl where studentID=sss having max(expiDate)-curdate() >= 0);    
	set @aa:=0;
    select classDate INTO clsdate from classtbl where classInfo = clsInfo; 
    
    if  clsDate >=curdate() then 
     set date_check = '성공';
	else 
     set date_check = '지난날짜';
	end if;    
    
    
    
	if sameDT = 0 then    
		insert into cmembertbl values(clsInfo,sss);
	
		update classtbl set nowNum= (select count(*) from cmembertbl where classinfo = clsInfo) where classInfo = clsInfo;
		
		set result = '성공';
	ELSE
    
		set result = '중복';
        
	end if;        	
    
	if result='성공' and rDate is not null and date_check = '성공' then
		 set lefttt1 = (select count(classtbl.classDate) from classtbl 
					inner join cmembertbl 
					on cmembertbl.classinfo = classtbl.classInfo
					where classtbl.classdate >= curdate()
					and cmembertbl.studentID = sss);
		select sum(leftLes) INTO @aa from paymenttbl where studentID=sss and paymenttbl.expiDate-curdate() > 0 ;
		set total=@aa;
        set lefttt1 = total - lefttt1;
		update paymenttbl set leftles = lefttt1 where expiDate =rDate and studentID=sss;
		set sccd_or_fl='성공';
                        
	ELSE
		set sccd_or_fl ='실패';      
        
       
	end if;
	select date_check,result, sccd_or_fl;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectClassData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassData`()
Begin
	
select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum 
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.classdate>=curdate()
                order by c.classDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectClassDataByDate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataByDate`(IN clsDate DATE)
Begin
DECLARE result int DEFAULT 0;
	set result =(select count(*) from classtbl where classDate=clsDate);
	select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum , result
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.classDate=clsDate order by c.classdate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectClassDataBySID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectClassDataByTID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataByTID`(IN tID char(30))
Begin
DECLARE result int DEFAULT 0;
	set result =(select count(*) from classtbl where teacherID=tID);
	select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum , result
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.teacherID=tID order by c.classInfo;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectClassDataFromCurDate` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectClassDataFromCurDate`()
Begin
	
select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum 
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID where c.classdate>=curdate()
                order by c.classDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectCMemberByClassData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectCMemberByClassData`(IN cIf char(20))
Begin
SELECT cm.classInfo, s.studentID, s.sName from cmembertbl cm 
	inner join studenttbl s on cm.studentID = s.studentID
    where classInfo= cIf;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectCMemberData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectCMemberData`()
Begin
	SELECT cm.classInfo, s.studentID, s.sName from cmembertbl cm 
	inner join studenttbl s on cm.studentID = s.studentID;
    
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectHealthData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectHealthData`()
Begin
	select s.studentID, s.sName, h.measureDate, h.weight, h.bodyfat, h.muscleMass, h.bmi 
        from healthtbl h 
			inner join studenttbl s 
				on h.studentID = s.studentID order by h.measureDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectHealthDataByID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectHealthDataByID`(IN sID char(30))
Begin
DECLARE result int default 0;
set result=(select count(*) from healthtbl where studentID = sID);
	select s.studentID, s.sName, h.measureDate, h.weight, h.bodyfat, h.muscleMass, h.bmi ,result
        from healthtbl h 
			inner join studenttbl s 
				on h.studentID = s.studentID  where h.studentID=sID order by h.measureDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectPaymentDataByID` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectPaymentDataByID`(IN sID char(30))
Begin
	DECLARE result int default 0;
	set result=(select count(*) from paymenttbl where studentID = sID);
    
	select s.studentID, s.sName, t.teacherID, t.tName, p.course, p.period,p.lessons, p.leftLes, p.pOption, p.tuition, p.regidate, p.expidate, result
		from studenttbl s 
			inner join paymenttbl p on s.studentID = p.studentID 
            inner join teachertbl t on t.teacherID = p.teacherID 
            where p.studentID=sID order by s.studentID and p.regiDate;		
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `selectTotalClassData` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `selectTotalClassData`()
Begin
	
select c.classInfo, t.teacherID, t.tName, t.image, c.course, c.cType, c.classDate,
		c.classTime,c.classRoom,c.limitNum, c.nowNum 
        from classtbl c 
			inner join teachertbl t 
				on c.teacherID = t.teacherID 
                order by c.classDate;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateClass` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
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
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateStudent` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateStudent`(IN sID char(30), IN pho char(11), IN pw char(12), IN img varchar(100))
Begin
	
	update studenttbl set phone =pho where studentID=sID;
    update studenttbl set sPassword =pw where studentID=sID;
    update studenttbl set image =img where studentID=sID;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `updateTeacher` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `updateTeacher`(IN tID char(30), IN pho char(11), IN pw char(12), IN img varchar(100))
Begin	
	update teachertbl set phone =pho where teacherID=tID;
    update teachertbl set tPassword =pw where teacherID=tID;
    update teachertbl set image =img where teacherID=tID;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-02-22 16:51:47
