CREATE TABLE `company_master` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `company_name` varchar(255) NOT NULL,
  `categories` varchar(4000) DEFAULT NULL,
  PRIMARY KEY (`id`));
  
  CREATE TABLE `company_likes` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_id` INT NOT NULL,
  `count` INT NULL DEFAULT 0,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `company_id_UNIQUE` (`company_id` ASC));
  
  CREATE TABLE `company_products` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `company_id` INT NOT NULL,
  `product_name` VARCHAR(1000) NOT NULL,
  `product_description` VARCHAR(1000) NULL,
  `product_keywords` VARCHAR(1000) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `product_name_UNIQUE` (`product_name` ASC));