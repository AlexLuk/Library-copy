-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.20-log

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
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
-- /*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES
  (1, 'admin', md5('password'), 'admin', 'admin', '', '0000 000000', '2000-01-01 14:00:00', 0, 1),
  (2,'user1', md5('password'), 'Madonna','Malone','Edward','1111 1111111','2014-03-16 15:01:00',5,0),
  (3,'user2',md5('password'),'Kellie','Suarez','Zeus','1111 1111111','2002-11-00 10:04:00',20,0),
  (4,'user3',md5('password'),'Kevin','Mcclure','Carter','1111 1111111','2010-04-14 14:00:00',10,0),
  (5,'user4',md5('password'),'Alana','Hurst','Tiger','1111 1111111','2006-02-09 14:00:00',15,0),
  (6,'user5',md5('password'),'Lillian','Nelson','Dillon','1111 1111111','2015-11-12 03:07:00',0,0),
  (7,'user6',md5('password'),'Sopoline','Rush','Uriah','1111 1111111','2016-03-04 04:00:00',0,0),
  (8,'user7',md5('password'),'Helen','Berg','','1111 1111111','2014-02-04 12:20:00',0,0),
  (9,'user8',md5('password'),'Xanthus','Fletcher','Merrill','1111 1111111','2011-04-10 14:00:00',0,0),
  (10,'user9',md5('password'),'Kellie','Carney','Judah','1111 1111111','2005-01-14 10:00:04',0,0),
  (11,'user10',md5('password'),'Tanner','Montoya','Ashton','1111 1111111','2003-07-12 16:00:40',0,0);
-- /*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
-- /*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES
  (1,'comedy'),
  (2,'drama'),
  (3,'romance'),
  (4,'satire'),
  (5,'tragedy'),
  (6,'fantasy'),
  (7,'novel');
-- /*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genre`
--

LOCK TABLES `item_status` WRITE;
-- /*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `item_status` VALUES
  (1,'free'),
  (2,'on_hands'),
  (3,'in_study');
-- /*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
-- /*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES
  (101,'Денис','Фонвизин','Иванович',1745),
  (102,'Николай','Гоголь','Васильевич',1809),
  (103,'Джейн','Остин','',1775),
  (104,'Теодор','Драйзер','',1871),
  (105,'Джек','Лондон','',1876),
  (106,'Сергей','Сергеев','Сергеевич',1950),
  (107,'Иван ','Иванов','Иванович',1960),
  (108,'Петр','Петров','Петрович',1950);
-- /*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
-- /*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES
  (1,'f1',1,'Недоросль','RU',1782,3,0),
  (2,'l1',7,'Мартин Иден','RU',1909,2,0),
  (3,'l2',7,'Martin Eden','EN',1909,1,1),
  (4,'d1',7,'Сестра Керри ','RU',1900,2,0),
  (5,'d2',7,'Финансист','RU',1912,5,0),
  (6,'d3',7,'Титан','RU',1914,1,0),
  (7,'d4',7,'Стоик','RU',1947,4,0),
  (8,'i1',6,'Несколько авторов','RU',2000,1,0);
-- /*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book_item` WRITE;
-- /*!40000 ALTER TABLE `book_item` DISABLE KEYS */;
INSERT INTO `book_item` VALUES
  (1, 1,1, 2),
  (2, 1,2, 2),
  (3, 1,3, 1),
  (4, 2,1, 1),
  (5, 2,2, 1),
  (6, 3,1, 1),
  (7, 4,1, 2),
  (8, 4,2, 1),
  (9, 5,1, 1),
  (10,5,2, 2),
  (11,5,3, 1),
  (12,5,4, 1),
  (13,5,5, 1),
  (14,6,1, 1),
  (15,7,1, 2),
  (16,7,2, 1),
  (17,7,3, 1),
  (18,7,4, 1),
  (19,8,1, 2);

-- /*!40000 ALTER TABLE `book_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
-- /*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES
  (1,101,1),
  (2,105,2),
  (3,105,3),
  (4,104,4),
  (5,104,5),
  (6,104,6),
  (7,104,7),
  (8,106,8),
  (9,107,8),
  (10,108,8);
-- /*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
-- /*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES
  (1,1,1,'2017-09-08 14:00:00'),
  (2,1,7,'2017-10-23 16:00:00'),
  (3,6,10,'2017-10-09 15:00:00'),
  (4,6,15,'2017-10-09 10:00:00'),
  (5,6,19,'2017-10-09 9:00:00');
-- /*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `book_order` WRITE;
-- /*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `book_order` VALUES
  (1,2,4,1),
  (2,5,2,1),
  (3,6,7,0),
  (4,7,3,1);
-- /*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-23 14:54:25
