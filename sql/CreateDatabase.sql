-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema iam
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `iam`;
USE `iam`;

-- -----------------------------------------------------
-- Table `iam`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS USER (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  `create_time` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`username`));


-- -----------------------------------------------------
-- Table `iam`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS ROLE (
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `owner` VARCHAR(45) NULL,
  PRIMARY KEY (`name`),
  INDEX `user_idx` (`owner` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`owner`)
    REFERENCES `iam`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `iam`.`rules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS RULES (
  `user` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  INDEX `username_idx` (`user` ASC) VISIBLE,
  INDEX `role_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `username`
    FOREIGN KEY (`user`)
    REFERENCES `iam`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rolename`
    FOREIGN KEY (`role`)
    REFERENCES `iam`.`role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `username` VARCHAR(16) NOT NULL,
  `email` VARCHAR(255) NULL,
  `password` VARCHAR(32) NOT NULL,
  PRIMARY KEY (`username`));


-- -----------------------------------------------------
-- Table `mydb`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`role` (
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(150) NULL,
  `owner` VARCHAR(45) NULL,
  PRIMARY KEY (`name`),
  INDEX `user_idx` (`owner` ASC) VISIBLE,
  CONSTRAINT `user`
    FOREIGN KEY (`owner`)
    REFERENCES `mydb`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`rules`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`rules` (
  `user` VARCHAR(45) NULL,
  `role` VARCHAR(45) NULL,
  INDEX `username_idx` (`user` ASC) VISIBLE,
  INDEX `role_idx` (`role` ASC) VISIBLE,
  CONSTRAINT `username`
    FOREIGN KEY (`user`)
    REFERENCES `mydb`.`user` (`username`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `rolename`
    FOREIGN KEY (`role`)
    REFERENCES `mydb`.`role` (`name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
