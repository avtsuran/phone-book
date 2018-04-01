# Phone-book app
## Prerequisites
You will need the following things properly installed on your computer:

* [Maven](https://maven.apache.org/)
* [Java](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
* [MySQL](https://dev.mysql.com/downloads/mysql/)

Before using app you must create database on MySQL server. To create db use next commands:
````
$ CREATE DATABASE `phone_book`;
````
````
$ USE `phone_book`;
````
````
$ CREATE TABLE `role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````
````
$ CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `active` int(11) DEFAULT NULL,
  `login` varchar(255) NOT NULL UNIQUE,
  `first_name` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````
````
$ CREATE TABLE `user_role` (
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`),
  CONSTRAINT `FK859n2jvi8ivhui0rl0esws6o` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`),
  CONSTRAINT `FKa68196081fvovjhkek5m97n3y` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````
````
$ CREATE TABLE `contact` (
  `contact_id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) NOT NULL,
  `second_name` varchar(255) NOT NULL,
  `middle_name` varchar(255) NOT NULL,
  `phone` varchar(255) NOT NULL,
  `home_phone` varchar(255),
  `address` varchar(255),
  `email` varchar(255),
  `user_id` int NOT NULL,
  PRIMARY KEY (`contact_id`),
  FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
````
````   
$ INSERT INTO `role` VALUES (1,'ADMIN');
````

## Run app
To run app use next command:
````
$ mvn spring-boot:run -Dlardi.conf=/path/to/file.properties
````
Your file.properties must include:
````
spring.datasource.url=jdbc:mysql://localhost/phone_book
spring.datasource.username=username
spring.datasource.password=password
````
### Go to [http://localhost:8080/](http://localhost:8080/)
