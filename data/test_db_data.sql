
--
-- Dumping data for table `author`
--

LOCK TABLES `author` WRITE;
/*!40000 ALTER TABLE `author` DISABLE KEYS */;
INSERT INTO `author` VALUES (101,'Денис','Фонвизин','Иванович',1745),(102,'Николай','Гоголь','Васильевич',1809),(103,'Джейн','Остин','-',1775),(104,'Теодор','Драйзер','-',1871),(105,'Джек','Лондон','-',1876);
/*!40000 ALTER TABLE `author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `author_book`
--

LOCK TABLES `author_book` WRITE;
/*!40000 ALTER TABLE `author_book` DISABLE KEYS */;
INSERT INTO `author_book` VALUES (1,101,'a'),(2,105,'b'),(3,105,'c'),(4,104,'d'),(5,104,'e'),(6,104,'g'),(7,104,'h');
/*!40000 ALTER TABLE `author_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `book`
--

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;
INSERT INTO `book` VALUES ('a','a1',1,'Недоросль','RU',1782,3,0),('b','b1',7,'Мартин Иден','RU',1909,2,0),('c','c1',7,'Martin Eden','EN',1909,1,1),('d','d1',7,'Сестра Керри ','RU',1900,2,0),('e','e1',7,'Финансист','RU',1912,5,0),('g','g1',7,'Титан','RU',1914,1,0),('h','h1',7,'Стоик','RU',1947,4,0);
/*!40000 ALTER TABLE `book` ENABLE KEYS */;
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
-- Dumping data for table `genre`
--

LOCK TABLES `genre` WRITE;
/*!40000 ALTER TABLE `genre` DISABLE KEYS */;
INSERT INTO `genre` VALUES (1,'comedy'),(2,'drama'),(3,'romance'),(4,'satire'),(5,'tragedy'),(6,'fantasy'),(7,'novel');
/*!40000 ALTER TABLE `genre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `librarian`
--

LOCK TABLES `librarian` WRITE;
/*!40000 ALTER TABLE `librarian` DISABLE KEYS */;
INSERT INTO `librarian` VALUES (1,'admin1','password','Анна','Миронова','Сергеевна'),(2,'admin2','password','Николай','Иванов','Петрович');
/*!40000 ALTER TABLE `librarian` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `order`
--

LOCK TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
INSERT INTO `order` VALUES (1,1,'a',1),(2,5,'b',1),(3,6,'c',1),(4,6,'d',1),(5,6,'e',1),(6,7,'g',0);
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `reader`
--

LOCK TABLES `reader` WRITE;
/*!40000 ALTER TABLE `reader` DISABLE KEYS */;
INSERT INTO `reader` VALUES (1,'user1','password','Madonna','Malone','Edward','1111 1111111','2014-03-16',5),(2,'user2','password','Kellie','Suarez','Zeus','1111 1111111','2002-11-00',20),(3,'user3','password','Kevin','Mcclure','Carter','1111 1111111','2010-04-14',10),(4,'user4','password','Alana','Hurst','Tiger','1111 1111111','2006-02-09',15),(5,'user5','password','Lillian','Nelson','Dillon','1111 1111111','2015-11-12',0),(6,'user6','password','Sopoline','Rush','Uriah','1111 1111111','2016-03-04',0),(7,'user7','password','Helen','Berg','Hayden','1111 1111111','2014-02-04',0),(8,'user8','password','Xanthus','Fletcher','Merrill','1111 1111111','2011-04-10',0),(9,'user9','password','Kellie','Carney','Judah','1111 1111111','2005-01-14',0),(10,'user10','password','Tanner','Montoya','Ashton','1111 1111111','2003-07-12',0);
/*!40000 ALTER TABLE `reader` ENABLE KEYS */;
UNLOCK TABLES;
