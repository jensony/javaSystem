/*
SQLyog Enterprise Trial - MySQL GUI v8.05 Beta1 
MySQL - 5.0.67-community-nt : Database - paper
*********************************************************************
*/


/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`paper` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `paper`;

/*Table structure for table `application` */

DROP TABLE IF EXISTS `application`;

CREATE TABLE `application` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` text NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY  (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `application` */

insert  into `application`(`Qno`,`Question`,`Answer`,`Difficulty`) values (51,'为什么1+1=?','2',5),(53,'从前有只鹿，它越跑越快，最后它变成了什么？','高速公鹿。。',1),(54,'11','11',1);

/*Table structure for table `blank` */

DROP TABLE IF EXISTS `blank`;

CREATE TABLE `blank` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` tinytext NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY  (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `blank` */

insert  into `blank`(`Qno`,`Question`,`Answer`,`Difficulty`) values (21,'1+4=?','5',1),(22,'1+5=?','6',1),(23,'多少分','这',2),(24,'      123','dgf',2),(26,'11','22',1);

/*Table structure for table `brief` */

DROP TABLE IF EXISTS `brief`;

CREATE TABLE `brief` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` text NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY  (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `brief` */

insert  into `brief`(`Qno`,`Question`,`Answer`,`Difficulty`) values (41,'why1+1=2?','I do not know',5),(42,'why1+2=3?','I do not know',1),(43,'why1+3=4?','I do not know',4),(44,'njknj','sretr',1),(45,'222','111',1);

/*Table structure for table `judge` */

DROP TABLE IF EXISTS `judge`;

CREATE TABLE `judge` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `Answer` tinytext NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY  (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `judge` */

insert  into `judge`(`Qno`,`Question`,`Answer`,`Difficulty`) values (31,'1+1=3','NO',1),(32,'2+2=4','YES',2),(33,'2+3=6','NO',3);

/*Table structure for table `paper` */

DROP TABLE IF EXISTS `paper`;

CREATE TABLE `paper` (
  `Pno` int(10) NOT NULL,
  `Qno` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `paper` */

insert  into `paper`(`Pno`,`Qno`) values (3,13),(3,11),(3,12),(5,12),(5,14),(5,11),(5,21),(5,32),(5,42),(5,53),(2,14),(2,11),(2,32),(2,33),(2,51),(3,14),(3,11),(3,12),(3,44),(3,53),(3,54),(7,15),(7,31),(7,33),(7,51),(1,13),(1,14),(1,16),(1,21),(1,32),(1,31),(1,41),(1,43),(1,54),(1,51);

/*Table structure for table `paperinfo` */

DROP TABLE IF EXISTS `paperinfo`;

CREATE TABLE `paperinfo` (
  `Pno` int(10) NOT NULL,
  `Qtype` int(10) NOT NULL,
  `score` int(10) NOT NULL,
  `quantity` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `paperinfo` */

insert  into `paperinfo`(`Pno`,`Qtype`,`score`,`quantity`) values (3,1,1,3),(5,1,1,3),(5,2,2,1),(5,3,1,1),(5,4,1,1),(5,5,5,1),(2,1,1,2),(2,3,2,2),(2,5,10,1),(3,1,2,3),(3,4,5,1),(3,5,10,2),(7,1,2,1),(7,3,1,2),(7,5,5,1),(1,1,5,3),(1,2,2,1),(1,3,2,2),(1,4,2,2),(1,5,22,2);

/*Table structure for table `selection` */

DROP TABLE IF EXISTS `selection`;

CREATE TABLE `selection` (
  `Qno` int(10) NOT NULL,
  `Question` text NOT NULL,
  `ItemA` tinytext NOT NULL,
  `ItemB` tinytext NOT NULL,
  `ItemC` tinytext NOT NULL,
  `ItemD` tinytext NOT NULL,
  `Answer` varchar(10) NOT NULL,
  `Difficulty` int(10) NOT NULL,
  PRIMARY KEY  (`Qno`)
) ENGINE=InnoDB DEFAULT CHARSET=gbk;

/*Data for the table `selection` */

insert  into `selection`(`Qno`,`Question`,`ItemA`,`ItemB`,`ItemC`,`ItemD`,`Answer`,`Difficulty`) values (11,'1+2=?','6','2','3','4','C',1),(12,'2','s','df','g','h','B',1),(13,'2+2=?','1','2','3','4','D',1),(14,'2+3=?','1','3','5','7','C',2),(15,'为什么1加1等于2','rf','wef','we','ergt','C',1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
paper