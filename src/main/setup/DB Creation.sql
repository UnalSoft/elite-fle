-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema elitefle
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema elitefle
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `elitefle` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `elitefle` ;

-- -----------------------------------------------------
-- Table `elitefle`.`Activity`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Activity` (
  `idActivity` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `nameText` VARCHAR(50) NOT NULL,
  `urlText` VARCHAR(50) NOT NULL,
  `type` VARCHAR(50) NOT NULL,
  PRIMARY KEY (`idActivity`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`User` (
  `idUser` INT NOT NULL,
  `username` VARCHAR(255) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `mail` VARCHAR(255) NOT NULL,
  `name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`idUser`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Teacher` (
  `idTeacher` INT NOT NULL,
  PRIMARY KEY (`idTeacher`),
  CONSTRAINT `fk_Teacher_User1`
    FOREIGN KEY (`idTeacher`)
    REFERENCES `elitefle`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Sequence` (
  `idSequence` INT NOT NULL,
  `nameSequence` VARCHAR(60) NOT NULL,
  `notion` VARCHAR(60) NOT NULL,
  `subNotion` VARCHAR(60) NOT NULL,
  `level` VARCHAR(15) NOT NULL,
  `supports` TINYINT NOT NULL,
  `spottingActivity` INT UNSIGNED NOT NULL,
  `systematizationActivity` INT UNSIGNED NOT NULL,
  `applicationActivity` VARCHAR(500) NULL,
  `idAuthor` INT NOT NULL,
  `urlExplication` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`idSequence`),
  INDEX `fk_Sequence_Activity1_idx` (`spottingActivity` ASC),
  INDEX `fk_Sequence_Activity2_idx` (`systematizationActivity` ASC),
  INDEX `fk_Sequence_Teacher1_idx` (`idAuthor` ASC),
  CONSTRAINT `fk_Sequence_Activity1`
    FOREIGN KEY (`spottingActivity`)
    REFERENCES `elitefle`.`Activity` (`idActivity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sequence_Activity2`
    FOREIGN KEY (`systematizationActivity`)
    REFERENCES `elitefle`.`Activity` (`idActivity`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sequence_Teacher1`
    FOREIGN KEY (`idAuthor`)
    REFERENCES `elitefle`.`Teacher` (`idTeacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Support`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Support` (
  `urlSupport` VARCHAR(100) NOT NULL,
  `title` VARCHAR(45) NOT NULL,
  `type` VARCHAR(45) NOT NULL,
  `notion` VARCHAR(60) NOT NULL,
  `subNotion` VARCHAR(60) NOT NULL,
  `idAuthor` INT NOT NULL,
  PRIMARY KEY (`urlSupport`),
  INDEX `fk_Support_Teacher1_idx` (`idAuthor` ASC),
  CONSTRAINT `fk_Support_Teacher1`
    FOREIGN KEY (`idAuthor`)
    REFERENCES `elitefle`.`Teacher` (`idTeacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Student` (
  `idStudent` INT NOT NULL,
  PRIMARY KEY (`idStudent`),
  CONSTRAINT `fk_Student_User1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `elitefle`.`User` (`idUser`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Teacher_has_Student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Teacher_has_Student` (
  `idTeacher` INT NOT NULL,
  `idStudent` INT NOT NULL,
  `Group` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idTeacher`, `idStudent`),
  INDEX `fk_Teacher_has_Student_Student1_idx` (`idStudent` ASC),
  INDEX `fk_Teacher_has_Student_Teacher1_idx` (`idTeacher` ASC),
  CONSTRAINT `fk_Teacher_has_Student_Teacher1`
    FOREIGN KEY (`idTeacher`)
    REFERENCES `elitefle`.`Teacher` (`idTeacher`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Teacher_has_Student_Student1`
    FOREIGN KEY (`idStudent`)
    REFERENCES `elitefle`.`Student` (`idStudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Student_has_Sequence`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Student_has_Sequence` (
  `idSequence` INT NOT NULL,
  `idUser` INT NOT NULL,
  `urlApplicationActivity` VARCHAR(45) NULL,
  PRIMARY KEY (`idSequence`, `idUser`),
  INDEX `fk_Sequence_has_Student_Student1_idx` (`idUser` ASC),
  INDEX `fk_Sequence_has_Student_Sequence1_idx` (`idSequence` ASC),
  CONSTRAINT `fk_Sequence_has_Student_Sequence1`
    FOREIGN KEY (`idSequence`)
    REFERENCES `elitefle`.`Sequence` (`idSequence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sequence_has_Student_Student1`
    FOREIGN KEY (`idUser`)
    REFERENCES `elitefle`.`Student` (`idStudent`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `elitefle`.`Sequence_has_Support`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `elitefle`.`Sequence_has_Support` (
  `Sequence_idSequence` INT NOT NULL,
  `Support_urlSupport` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`Sequence_idSequence`, `Support_urlSupport`),
  INDEX `fk_Sequence_has_Support_Support1_idx` (`Support_urlSupport` ASC),
  INDEX `fk_Sequence_has_Support_Sequence1_idx` (`Sequence_idSequence` ASC),
  CONSTRAINT `fk_Sequence_has_Support_Sequence1`
    FOREIGN KEY (`Sequence_idSequence`)
    REFERENCES `elitefle`.`Sequence` (`idSequence`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Sequence_has_Support_Support1`
    FOREIGN KEY (`Support_urlSupport`)
    REFERENCES `elitefle`.`Support` (`urlSupport`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
