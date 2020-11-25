-- MySQL Workbench Forward Engineering

-- SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
-- SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
-- SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `iam`;
USE `iam` ;

DROP TABLE IF EXISTS iam.user;
DROP TABLE IF EXISTS iam.role;
DROP TABLE IF EXISTS iam.rules;

-- -----------------------------------------------------
-- Table `iam`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `iam`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`username`)
);

CREATE TABLE IF NOT EXISTS `iam`.`role` (
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `owner` VARCHAR(45) NULL,
  PRIMARY KEY (`name`),
  FOREIGN KEY (`owner`) REFERENCES `iam`.`user` (`username`) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS `iam`.`rules` (
  `user` VARCHAR(45) NOT NULL,
  `role` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`user`, `role`),
  FOREIGN KEY (`user`) REFERENCES `iam`.`user` (`username`) ON DELETE CASCADE,
  FOREIGN KEY (`role`) REFERENCES `iam`.`role` (`name`) ON DELETE CASCADE
);


-- SET SQL_MODE=@OLD_SQL_MODE;
-- SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
-- SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
