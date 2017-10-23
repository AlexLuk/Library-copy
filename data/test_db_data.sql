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
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'user1','password','Madonna','Malone','Edward','1111 1111111','2014-03-16',5,1),(2,
                                                                                                               'user2','password','Kellie','Suarez','Zeus','1111 1111111','2002-11-00',20,0),(3,'user3','password','Kevin','Mcclure','Carter','1111 1111111','2010-04-14',10,0),(4,'user4','password','Alana','Hurst','Tiger','1111 1111111','2006-02-09',15,0),(5,'user5','password','Lillian','Nelson','Dillon','1111 1111111','2015-11-12',0,0),(6,'user6','password','Sopoline','Rush','Uriah','1111 1111111','2016-03-04',0,0),(7,'user7','password','Helen','Berg','Hayden','1111 1111111','2014-02-04',0,0),(8,'user8','password','Xanthus','Fletcher','Merrill','1111 1111111','2011-04-10',0,0),(9,'user9','password','Kellie','Carney','Judah','1111 1111111','2005-01-14',0,0),(10,'user10','password','Tanner','Montoya','Ashton','1111 1111111','2003-07-12',0,0);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'comedy'),(2,'drama'),(3,'romance'),(4,'satire'),(5,'tragedy'),(6,'fantasy'),(7,'novel');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (101,'Денис','Фонвизин','Иванович',1745),(102,'Николай','Гоголь','Васильевич',1809),(103,'Джейн','Остин','',1775),(104,'Теодор','Драйзер','',1871),(105,'Джек','Лондон','',1876),(106,'Сергей','Сергеев','Сергеевич',1950),(107,'Иван ','Иванов','Иванович',1960),(108,'Петр','Петров','Петрович',1950);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('a','a1',1,'Недоросль','RU',1782,3,0),('b','b1',7,'Мартин Иден','RU',1909,2,0),('c','c1',7,'Martin Eden','EN',1909,1,1),('d','d1',7,'Сестра Керри ','RU',1900,2,0),('e','e1',7,'Финансист','RU',1912,5,0),('g','g1',7,'Титан','RU',1914,1,0),('h','h1',7,'Стоик','RU',1947,4,0),('i','i1',6,'Несколько авторов','RU',2000,3,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES (1,101,'a'),(2,105,'b'),(3,105,'c'),(4,104,'d'),(5,104,'e'),(6,104,'g'),(7,104,'h'),(8,106,'i'),(9,107,'i'),(10,108,'i');
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `delivery`
--

LOCK TABLES `delivery` WRITE;
/*!40000 ALTER TABLE `delivery` DISABLE KEYS */;
INSERT INTO `delivery` VALUES (1,1,2,'e','2017-09-08 21:00:00',1),(2,1,2,'d','2017-09-08 21:00:00',1),(3,6,1,'g','2017-10-09 21:00:00',1),(4,6,1,'h','2017-10-09 21:00:00',1),(5,6,1,'a','2017-10-09 21:00:00',1);
/*!40000 ALTER TABLE `delivery` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'a',1),(2,5,'b',1),(3,6,'c',1),(4,6,'d',1),(5,6,'e',1),(6,7,'g',0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
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
