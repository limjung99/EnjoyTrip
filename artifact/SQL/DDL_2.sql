-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema ssafytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `ssafytrip` DEFAULT CHARACTER SET utf8 ;
USE `ssafytrip` ;


-- -----------------------------------------------------
-- Table `ssafytrip`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`user` (
  `id` VARCHAR(100) NOT NULL,
  `password` VARCHAR(255) NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `token` VARCHAR(1055) NULL,
  `is_admin` TINYINT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafytrip`.`post`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`post` (
  `post_id` INT NOT NULL AUTO_INCREMENT,
  `post_text` TEXT NULL DEFAULT NULL,
  `post_title` VARCHAR(255) NOT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `created_at` DATE NULL DEFAULT curdate(),
  `is_active` TINYINT NULL DEFAULT NULL,
  `hated` INT NULL DEFAULT '0',
  `liked` INT NULL DEFAULT '0',
  `views` INT NULL DEFAULT '0',
  PRIMARY KEY (`post_id`),
  INDEX `fk_post_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_post_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafytrip`.`user` (`id`)
    ON UPDATE CASCADE)
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci


-- -----------------------------------------------------
-- Table `ssafytrip`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`comment` (
  `comment_id` INT NOT NULL AUTO_INCREMENT,
  `comment_parent` INT NULL,
  `user_id` VARCHAR(100) NOT NULL,
  `created_at` DATE NULL DEFAULT (CURRENT_DATE),
  `post_id` INT NOT NULL,
  `comment_text` TEXT NULL,
  PRIMARY KEY (`comment_id`),
  INDEX `fk_comment_user_id_idx` (`user_id` ASC) VISIBLE,
  INDEX `fk_commnet_post_id_idx` (`post_id` ASC) VISIBLE,
  INDEX `fk_comment_comment_id_idx` (`comment_parent` ASC) VISIBLE,
  CONSTRAINT `fk_comment_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafytrip`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_commnet_post_id`
    FOREIGN KEY (`post_id`)
    REFERENCES `ssafytrip`.`post` (`post_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE,
  CONSTRAINT `fk_comment_comment_id`
    FOREIGN KEY (`comment_parent`)
    REFERENCES `ssafytrip`.`comment` (`comment_id`)
    ON DELETE NO ACTION
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafytrip`.`plan`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`plan` (
  `plan_id` INT NOT NULL AUTO_INCREMENT,
  `user_id` VARCHAR(100) NOT NULL,
  `plan_name` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`plan_id`, `plan_name`),
  INDEX `fk_plan_user_id_idx` (`user_id` ASC) VISIBLE,
  CONSTRAINT `fk_plan_user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `ssafytrip`.`user` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `ssafytrip`.`plan_attraction`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `ssafytrip`.`plan_attraction` (
  `plan_id` INT NOT NULL,
  `content_id` INT NOT NULL,
  PRIMARY KEY (`plan_id`, `content_id`),
  CONSTRAINT `fk_planattraction_plan_id`
    FOREIGN KEY (`plan_id`)
    REFERENCES `ssafytrip`.`plan` (`plan_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_planattraction_attractioninfo_id`
    FOREIGN KEY (`content_id`)
    REFERENCES `ssafytrip`.`attraction_info`(`content_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
