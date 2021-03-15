SET NAMES UTF8MB4 COLLATE UTF8MB4_UNICODE_CI;
SET CHARACTER SET UTF8MB4;
SET SESSION collation_connection = utf8mb4_unicode_ci;
SET time_zone = '+7:00';

DROP DATABASE IF EXISTS cusc_shop;
CREATE DATABASE cusc_shop;

USE cusc_shop;

CREATE TABLE role (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE information (
    id INT NOT NULL AUTO_INCREMENT,
    full_name VARCHAR(100) NOT NULL,
    birthday DATETIME NOT NULL,
    gender BOOL NOT NULL, -- TRUE: Male, FALSE: Female
    email VARCHAR(100) NOT NULL,
    address VARCHAR(500) NOT NULL,
    phone VARCHAR(100) NOT NULL,
    
    PRIMARY KEY (id)
);

CREATE TABLE employee (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role INT NOT NULL,
    information INT NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (role)
     REFERENCES role (id)
     ON DELETE CASCADE,
    FOREIGN KEY (information)
     REFERENCES information (id)
     ON DELETE CASCADE
);

CREATE TABLE customer (
    id INT NOT NULL AUTO_INCREMENT,
    username VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    information INT NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (information)
     REFERENCES information (id)
     ON DELETE CASCADE
);

CREATE TABLE brand (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
);

CREATE TABLE category_group (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id)
);

CREATE TABLE category (
    id INT NOT NULL AUTO_INCREMENT,
    category_group INT NOT NULL,
    name VARCHAR(100) NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (category_group)
     REFERENCES category_group (id)
     ON DELETE CASCADE
);

CREATE TABLE product (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category INT NOT NULL,
    brand INT NOT NULL,
    price FLOAT NOT NULL,
    quantity INT NOT NULL,
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (category)
     REFERENCES category (id)
     ON DELETE CASCADE,
    FOREIGN KEY (brand)
     REFERENCES brand (id)
     ON DELETE CASCADE
);

CREATE TABLE bill (
    id INT NOT NULL AUTO_INCREMENT,
    customer INT NOT NULL,
    salesman INT NOT NULL,
    shipper INT NOT NULL,
    create_date DATETIME NOT NULL,
    pay_date DATETIME,
    status INT NOT NULL DEFAULT 1, -- 0: Canceled, 1: Pending, 2: Accepted, 3: Shipping, 4: Paid
    state BOOL NOT NULL DEFAULT TRUE, -- TRUE: Enabled, FALSE: Disabled
    
    PRIMARY KEY (id),
    FOREIGN KEY (customer)
     REFERENCES customer (id)
     ON DELETE CASCADE,
    FOREIGN KEY (salesman)
     REFERENCES employee (id)
     ON DELETE CASCADE,
    FOREIGN KEY (shipper)
     REFERENCES employee (id)
     ON DELETE CASCADE
);

CREATE TABLE bill_details (
    bill INT NOT NULL,
    product INT NOT NULL,
    price FLOAT NOT NULL,
    quantity INT NOT NULL,
    
    PRIMARY KEY (bill , product),
    FOREIGN KEY (bill)
     REFERENCES bill (id)
     ON DELETE CASCADE,
    FOREIGN KEY (product)
     REFERENCES product (id)
     ON DELETE CASCADE
);

INSERT INTO
	role (name)
VALUES 
	('administrator'), 
	('manager'), 
	('salesman'), 
	('shipper'), 
	('customer');
    
INSERT INTO
	information (full_name, birthday, gender, email, address, phone)
VALUES 
	('Dao Minh Trung Thuan', '1993-03-08', 0, 'dmtthuana18088@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '0292870758'), 
	('Nguyen Phuc Duy', '1976-11-21', 0, 'npduya18082@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '01886697561'), 
	('Dang Quang Nghi', '1981-02-20', 0, 'dqnghia18086@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '03205219336'), 
	('Ho Quang Tam', '1984-07-07', 1, 'hqtama16053@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '0586781301'),
	('Nguyen Hoang Lam', '1997-05-26', 0, 'daomtthuan.user@gmail.com', N'1 Tran Hung Dao Street, Ninh Kieu Ward, Can tho city', '02183697771'),
	('Quach Gia Bao', '1994-05-26', 1, 'daomtthuan.user@gmail.com', N'1 Tran Binh Trong Street, Ninh Kieu Ward, Can tho city', '02300532958');

INSERT INTO
	employee (username, password, role, information)
VALUES
	('administrator', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 1, 1),
	('manager', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 2, 2),
	('salesman', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 3, 3),
	('shipper', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 4, 4);
    
INSERT INTO
	customer (username, password, information)
VALUES
	('customer1', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 5),
	('customer2', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', 6);
    
INSERT INTO
	category_group (name)
VALUES
	('Device Defense'),
    ('Network'),
    ('Printer'),
    ('Game machine'),
    ('Computer'),
    ('Accessories');
    
INSERT INTO
	category (name, category_group)
VALUES
	('Central Processing Unit (CPU)', 1),
    ('Mainboard', 1),
    ('Graphics Processing Unit (GPU)', 1),
    ('Random Access Memory (RAM)', 1),
    ('Solid State Drive (SSD)', 1),
    ('Hard Disk Drive (HDD)', 1),
    ('Case computer', 6),
    ('Power', 6),
    ('Sound card', 6),
    ('Ups', 6),
    ('ITX', 6),
    ('LCD', 6),
    ('Keyboard', 6),
    ('Mouse', 6),
    ('Headphone', 6),
    ('Printer lajer', 3),
    ('Printer network', 3),
    ('VPS', 2),
    ('Play station', 4),
    ('Disk gaming', 4),
    ('Sony PS4', 4),
    ('Gaming Accs', 4),
    ('PC Desktop', 5),
    ('PC Laptop', 5),
    ('Bads', 6),
    ('Mic record', 6),
    ('Projectors', 6),
    ('Camera', 6),
    ('Hub-Cable-AIC', 6),
    ('Playing stream', 6);

INSERT INTO
	brand (name)
VALUES
	('Intel'),
    ('ARES'),
    ('ASUS'),
    ('Microsoft'),
    ('Logitech'),
    ('Dell'),
    ('Blaster'),
    ('MSI'),
    ('APC'),
    ('Marvel'),
    ('Slim'),
    ('CORSAIR');

INSERT INTO
	product (name, price, quantity, category, brand)
VALUES
    ('Intel 10th Gen Core i9-10850K Processor – Unlocked',12990,100,1,1),
    ('ASUS ROG MAXIMUS XII EXTREME Mainboard – Z490 Chipset',17900,100,2,3),
    ('ASUS TUF Gaming Geforce RTX 3090 24G Graphics Card',42800,100,3,3),
    ('CORSAIR VENGEANCE RGB PRO Memory Kit – Black, 32GB',4900,100,4,12),
    ('MSI Core Frozr S Air Cooler',1120,100,5,8),
    ('Intel Optane Memory M10 Series 16GB SSD – 16GB, M.2 80mm PCIe 3.0, 20nm, 3D XPoint',990,100,6,1),
    ('ASUS ROG Strix Helios Case – White',6690,100,7,3),
    ('ASUS ROG Thor 850P PSU – 850W, 80Plus Platium, Full Modular, Sleeve Cable, AURA Sync',5790,100,8,3),
    ('Creative Sound BlasterX AE-5 RGB Sound Card',3190,100,9,7),
    ('ARES AR630 UPS – 3000VA, 2400W',8290,100,10,2),
    ('ASUS ROG Strix Z490-I Gaming Mainboard',6890,100,11,3),
    ('ASUS ROG Strix XG49VQ SUPER Ultra-Wide Gaming Monitor – 49″, DFHD, 144Hz, 1ms, HDR400, FreeSync 2',26990,100,12,3),
    ('ASUS ROG Strix Scope Gaming Keyboard – Red Switch',3190,100,13,3),
    ('ASUS ROG Chakram RGB Gaming Mouse',3890,100,14,3),
    ('ASUS ROG Theta 7.1 Surround Gaming Headset',6890,100,15,3),
    ('ASUS ROG Rapture GT-AC2900 Gaming Router – 750+2167Mbps, AiMesh, AURA RGB',5490,100,16,3),
    ('Speed R1 Gaming Desk',2190,100,17,9),
    ('ASUS ROG Chariot SL300C RGB Gaming Chair',15490,100,18,3),
    ('Microsoft Xbox One S Controller – Phantom Black',2090,100,19,4),
    ('Marvel’s Spider-Man Game Disc',550,100,20,10),
    ('Gamming PS4 Pro & PS4 Slim',350,100,21,11),
    ('Logitech G29 Driving Force Racing Wheel',6979,100,22,5),
    ('Dell Vostro 3470ST (HXKWJ1) Desktop PC',7250,100,23,6),
    ('Laptop Dell G3 Inspiron 3579 70167040 (Black) Geforce GTX1050Ti 4GB Intel Core i7 8750H 128GB 8GB',23490,100,24,6),
    ('ASUS ROG Ranger BP3703 Gaming Backpack – Balo Gaming',6490,100,25,3);