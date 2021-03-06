use library;

CREATE TABLE IF NOT EXISTS `genre`
(
  `id` int AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `author`
(
  `id` int AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `patronymic` varchar(255),
  `yearOfBirth` int(4) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `book`
(
  `ISBN` varchar(30) NOT NULL,
  `shelf_id` varchar(30) NOT NULL,
  `genre_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `language` char(2) NOT NULL,
  `year` int(4) NOT NULL,
  `amount` smallint NOT NULL,
  `is_rare` boolean NOT NULL COMMENT 'For old books',

  PRIMARY KEY (`ISBN`),
  FOREIGN KEY( `genre_id` ) REFERENCES genre( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `reader`
(
  `id` int AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `patronymic` varchar(255),
  `passport` varchar(255) NOT NULL,
  `registration_date` date NOT NULL,
  `fines` double NOT NULL,
  `isLibrarian` BOOLEAN NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `author_book`
(
  `id` int AUTO_INCREMENT,
  `author_id` int NOT NULL,
  `book_id` varchar(30) NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `author_id` ) REFERENCES author( `id` ),
  FOREIGN KEY( `book_id` ) REFERENCES book( `ISBN` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `delivery`
(
  `id` int AUTO_INCREMENT,
  `reader_id` int NOT NULL,
  `librarian_id` int NOT NULL,
  `book_id` varchar(30) NOT NULL,
  `time` timestamp NOT NULL,
  `on_hands` boolean NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `reader_id` ) REFERENCES reader( `id` ),
  FOREIGN KEY( `librarian_id` ) REFERENCES reader( `id` ),
  FOREIGN KEY( `book_id` ) REFERENCES book( `ISBN` )

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `order`
(
  `id` int AUTO_INCREMENT,
  `reader_id` int NOT NULL,
  `book_id` varchar(30) NOT NULL,
  `on_hands` boolean NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `reader_id` ) REFERENCES reader( `id` ),
  FOREIGN KEY( `book_id` ) REFERENCES book( `ISBN` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
