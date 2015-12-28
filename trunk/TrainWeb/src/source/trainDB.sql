/*
SQLyog Enterprise - MySQL GUI v8.05 
MySQL - 5.5.31 : Database - trainapp
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

CREATE DATABASE /*!32312 IF NOT EXISTS*/`trainapp` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `trainapp`;

/*Table structure for table `train_history` */

DROP TABLE IF EXISTS `train_history`;

CREATE TABLE `train_history` (
  `HISTORY_ID` bigint(20) NOT NULL,
  `STUDENT_ID` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `FILEIMAGE_URL` varchar(2000) COLLATE utf8_unicode_ci NOT NULL,
  `LOCATION_DISK` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `STATUS` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  `CRATE_BY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `UPDATE_DATE` datetime DEFAULT NULL,
  `UPDATE_BY` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  PRIMARY KEY (`HISTORY_ID`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `train_history` */

/*Table structure for table `user_profile` */

DROP TABLE IF EXISTS `user_profile`;

CREATE TABLE `user_profile` (
  `ID` bigint(3) NOT NULL AUTO_INCREMENT,
  `USERNAME` varchar(200) CHARACTER SET utf8 NOT NULL,
  `PASSWORD` varchar(200) CHARACTER SET utf8 NOT NULL,
  `SALUTATION` varchar(12) CHARACTER SET utf8 NOT NULL,
  `FIRST_NAME` varchar(120) CHARACTER SET utf8 NOT NULL,
  `LAST_NAME` varchar(120) CHARACTER SET utf8 NOT NULL,
  `IS_ADMIN` char(1) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`ID`,`USERNAME`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `user_profile` */

insert  into `user_profile`(`ID`,`USERNAME`,`PASSWORD`,`SALUTATION`,`FIRST_NAME`,`LAST_NAME`,`IS_ADMIN`) values (1,'admin','admin','Mr','Arucha','Hainchasri','Y');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
