-- Create schema
-- DROP DATABASE IF EXISTS cusc_shop;
-- CREATE DATABASE cusc_shop;

USE cusc_shop;

-- Settings
SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
SET CHARACTER SET utf8mb4;
SET SESSION collation_connection = utf8mb4_unicode_ci;
SET time_zone = '+7:00';

-- Create table Account
CREATE TABLE account (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    full_name NVARCHAR(100) NOT NULL,
    birthday DATE NOT NULL,
    gender BIT NOT NULL, -- TRUE: Male, FALSE: Female
    email VARCHAR(100) NOT NULL,
    address NVARCHAR(500) NOT NULL,
    phone VARCHAR(15) NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
)  ENGINE=INNODB;

-- Create table Role
CREATE TABLE role (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
)  ENGINE=INNODB;

-- Create table Permission
CREATE TABLE permission (
    id INT NOT NULL AUTO_INCREMENT,
    id_account INT NOT NULL,
    id_role INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_account)
        REFERENCES account (id)
        ON DELETE CASCADE,
    FOREIGN KEY (id_role)
        REFERENCES role (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table Brand
CREATE TABLE brand (
    id INT NOT NULL AUTO_INCREMENT,
    name NVARCHAR(100) NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
)  ENGINE=INNODB;

-- Create table Category Group
CREATE TABLE category_group (
    id INT NOT NULL AUTO_INCREMENT,
    name NVARCHAR(100) NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
)  ENGINE=INNODB;

-- Create table Category
CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    name NVARCHAR(100) NOT NULL,
    id_category_group INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_category_group)
        REFERENCES category_group (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table Product
CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT,
    name NVARCHAR(100) NOT NULL,
    price FLOAT NOT NULL,
    quantity INT NOT NULL,
    id_category INT NOT NULL,
    id_brand INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_category)
        REFERENCES category (id)
        ON DELETE CASCADE,
    FOREIGN KEY (id_brand)
        REFERENCES brand (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table Review
CREATE TABLE review (
    id INT NOT NULL AUTO_INCREMENT,
    star TINYINT NOT NULL,
    content NVARCHAR(500),
    id_account INT NOT NULL,
    id_product INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_account)
        REFERENCES account (id)
        ON DELETE CASCADE,
    FOREIGN KEY (id_product)
        REFERENCES product (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table Event
CREATE TABLE event (
    id INT NOT NULL AUTO_INCREMENT,
    title NVARCHAR(100) NOT NULL,
    post DATE NOT NULL DEFAULT NOW(),
    id_account INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_account)
        REFERENCES account (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table Bill
CREATE TABLE bill (
    id INT NOT NULL AUTO_INCREMENT,
    id_account INT NOT NULL,
    create_date DATE NOT NULL DEFAULT NOW(),
    pay_date DATE,
    status INT NOT NULL DEFAULT 0, -- 0: Pending, 1: Shipping, 2: Paid, 3: Cancel
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_account)
        REFERENCES account (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;

-- Create table ProductCart
CREATE TABLE bill_details (
	id INT NOT NULL AUTO_INCREMENT,
    id_bill INT NOT NULL,
    id_product INT NOT NULL,
    quantity INT NOT NULL,
    state BIT NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (id_bill)
        REFERENCES bill (id)
        ON DELETE CASCADE,
	FOREIGN KEY (id_product)
        REFERENCES product (id)
        ON DELETE CASCADE
)  ENGINE=INNODB;