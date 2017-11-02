ALTER TABLE `author` DROP COLUMN `year_of_birth`;
ALTER TABLE `author` CHANGE COLUMN `yearOfBirth` `year_of_birth` int(4) NOT NULL;