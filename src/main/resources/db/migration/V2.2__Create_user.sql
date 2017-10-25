CREATE USER 'lib_admin'@'localhost' IDENTIFIED BY 'admin';
GRANT ALL PRIVILEGES ON library.* TO 'lib_admin'@'localhost';