-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: library
-- ------------------------------------------------------
-- Server version	5.7.20-log

-- соответствие книг авторам
-- SELECT author.surname, book.title
-- FROM author JOIN book JOIN author_book
-- WHERE author_book.book_id = book.id AND author_book.author_id = author.id;

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
  (0, 'admin', md5('password'), 'admin', 'admin', '', '0000 000000', '2000-01-01', 0, 1),
  (1,'user1', md5('password'), 'Madonna','Malone','Edward','1111 1111111','2014-03-16',5,0),
  (2,'user2',md5('password'),'Kellie','Suarez','Zeus','1111 1111111','2002-11-00',20,0),
  (3,'user3',md5('password'),'Kevin','Mcclure','Carter','1111 1111111','2010-04-14',10,0),
  (4,'user4',md5('password'),'Alana','Hurst','Tiger','1111 1111111','2006-02-09',15,0),
  (5,'user5',md5('password'),'Lillian','Nelson','Dillon','1111 1111111','2015-11-12',0,0),
  (6,'user6',md5('password'),'Sopoline','Rush','Uriah','1111 1111111','2016-03-04',0,0),
  (7,'user7',md5('password'),'Helen','Berg','Hayden','1111 1111111','2014-02-04',0,0),
  (8,'user8',md5('password'),'Xanthus','Fletcher','Merrill','1111 1111111','2011-04-10',0,0),
  (9,'user9',md5('password'),'Kellie','Carney','Judah','1111 1111111','2005-01-14',0,0),
  (10,'user10',md5('password'),'Tanner','Montoya','Ashton','1111 1111111','2003-07-12',0,0);
-- /*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;


--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
-- /*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'comedy'),(2,'drama'),(3,'romance'),(4,'satire'),(5,'tragedy'),(6,'fantasy'),(7,'novel');
-- /*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
-- /*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (101,'Денис','Фонвизин','Иванович',1745),(102,'Николай','Гоголь','Васильевич',1809),(103,'Джейн','Остин','',1775),(104,'Теодор','Драйзер','',1871),(105,'Джек','Лондон','',1876),(106,'Сергей','Сергеев','Сергеевич',1950),(107,'Иван ','Иванов','Иванович',1960),(108,'Петр','Петров','Петрович',1950);
-- /*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
-- /*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES
  (1,'a1',1,'Недоросль','RU',1782,3,0),
  (2,'b1',7,'Мартин Иден','RU',1909,2,0),
  (3,'c1',7,'Martin Eden','EN',1909,1,1),
  (4,'d1',7,'Сестра Керри ','RU',1900,2,0),
  (5,'e1',7,'Финансист','RU',1912,5,0),
  (6,'g1',7,'Титан','RU',1914,1,0),
  (7,'h1',7,'Стоик','RU',1947,4,0),
  (8,'i1',6,'Несколько авторов','RU',2000,1,0);
-- /*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book_item` WRITE;
-- /*!40000 ALTER TABLE `book_item` DISABLE KEYS */;
INSERT INTO `book_item` VALUES
  (1, 1,1, 'on_hands'),
  (2, 1,2, 'on_hands'),
  (3, 1,3, 'in_order'),
  (4, 2,1, 'on_shelf'),
  (5, 2,2, 'on_shelf'),
  (6, 3,1, 'in_order'),
  (7, 4,1, 'on_hands'),
  (8, 4,2, 'on_shelf'),
  (9, 5,1, 'on_shelf'),
  (10,5,2, 'on_hands'),
  (11,5,3, 'in_order'),
  (12,5,4, 'on_shelf'),
  (13,5,5, 'on_shelf'),
  (14,6,1, 'on_shelf'),
  (15,7,1, 'on_hands'),
  (16,7,2, 'on_shelf'),
  (17,7,3, 'in_order'),
  (18,7,4, 'on_shelf'),
  (19,8,1, 'on_hands');
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
  (1,1,1,'2017-09-08 14:00:00',1),
  (2,1,7,'2017-10-23 16:00:00',7),
  (3,6,10,'2017-10-09 15:00:00',1),
  (4,6,15,'2017-10-09 10:00:00',1),
  (5,6,19,'2017-10-09 9:00:00',1);
-- /*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
-- /*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES
  (1,1,11,1),
  (2,5,17,1),
  (3,6,16,0),
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


