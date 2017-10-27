-- create schema library;
-- CREATE USER 'be4c06dde099c6'@'localhost' IDENTIFIED BY '58672378';
-- GRANT ALL PRIVILEGES ON library.* TO 'be4c06dde099c6'@'localhost';

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
  `patronymic` varchar(255) NOT NULL DEFAULT '',
  `yearOfBirth` int(4) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `book`
(
  `id` int AUTO_INCREMENT,
  `shelf_id` varchar(30) NOT NULL,
  `genre_id` int NOT NULL,
  `title` varchar(255) NOT NULL,
  `language` char(2) NOT NULL,
  `year` int(4) NOT NULL,
  `amount` smallint NOT NULL,
  `is_rare` boolean NOT NULL COMMENT 'For old books',

  PRIMARY KEY (`id`),
  FOREIGN KEY( `genre_id` ) REFERENCES genre( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `item_status`
(
  `id` int AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `book_item`
(
  `id` int AUTO_INCREMENT,
  `item_id` int NOT NULL,
  `book_id` int NOT NULL,
  `status_id` int NOT NULL,

  PRIMARY KEY (`id`),
  UNIQUE (`book_id`, `item_id`),
  FOREIGN KEY( `book_id` ) REFERENCES book( `id` ),
  FOREIGN KEY( `status_id` ) REFERENCES item_status( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `reader`
(
  `id` int AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` CHAR(32) NOT NULL,
  `name` varchar(255) NOT NULL,
  `surname` varchar(255) NOT NULL,
  `patronymic` varchar(255) NOT NULL DEFAULT '',
  `passport` varchar(255) NOT NULL,
  `registration_date` date NOT NULL,
  `fines` double NOT NULL,
  `is_admin` boolean NOT NULL,

  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `author_book`
(
  `id` int AUTO_INCREMENT,
  `author_id` int NOT NULL,
  `book_id` int NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `author_id` ) REFERENCES author( `id` ),
  FOREIGN KEY( `book_id` ) REFERENCES book( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;


CREATE TABLE IF NOT EXISTS `delivery`
(
  `id` int AUTO_INCREMENT,
  `reader_id` int NOT NULL,
  `book_item_id` int NOT NULL,
  `time` timestamp NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `reader_id` ) REFERENCES reader( `id` ),
  FOREIGN KEY( `book_item_id` ) REFERENCES book_item( `id` )

) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE IF NOT EXISTS `book_order`
(
  `id` int AUTO_INCREMENT,
  `reader_id` int NOT NULL,
  `book_id` int NOT NULL,
  `on_hands` boolean NOT NULL,

  PRIMARY KEY (`id`),
  FOREIGN KEY( `reader_id` ) REFERENCES reader( `id` ),
  FOREIGN KEY( `book_id` ) REFERENCES book( `id` )
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
