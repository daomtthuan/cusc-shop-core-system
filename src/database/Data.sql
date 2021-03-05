USE cusc_shop;

-- Settings
SET NAMES utf8mb4 COLLATE utf8mb4_unicode_ci;
SET CHARACTER SET utf8mb4;
SET SESSION collation_connection = utf8mb4_unicode_ci;
SET time_zone = '+7:00';

-- Insert Role
INSERT INTO role(name)
VALUES ('Admin'),
       ('User');

-- Insert User
INSERT INTO account(username, password, full_name, birthday, gender, email, address, phone)
VALUES ('admin', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Dao Minh Trung Thuan', '1993-03-08', 0, 'dmtthuana18088@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '0292870758');
INSERT INTO account(username, password, full_name, birthday, gender, email, address, phone)
VALUES ('user1', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Nguyen Phuc Duy', '1976-11-21', 0, 'npduya18082@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '01886697561'),
       ('user2', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Dang Quang Nghi', '1981-02-20', 0, 'dqnghia18086@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '03205219336'),
       ('user3', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Ho Quang Tam', '1984-07-07', 1, 'hqtama16053@cusc.ctu.edu.vn', N'1 Ly Tu Trong Street, Ninh Kieu Ward, Can tho city', '0586781301'),
       ('user4', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Joskye Dang', '1997-05-26', 0, 'daomtthuan.user@gmail.com', N'1 Tran Hung Dao Street, Ninh Kieu Ward, Can tho city', '02183697771'),
       ('user5', '$2a$10$KfD1rAu6rQDfMYswpUKJn.l2HnU3FrxUDOcg9W16ENcRLEWTSnsNG', N'Henry Dao', '1994-05-26', 1, 'daomtthuan.user@gmail.com', N'1 Tran Binh Trong Street, Ninh Kieu Ward, Can tho city', '02300532958');

-- Insert Permission
INSERT INTO permission(id_account, id_role)
VALUES (1, 1);
INSERT INTO permission(id_account, id_role)
VALUES (1, 2),
       (2, 2),
       (3, 2),
       (4, 2),
       (5, 2),
       (6, 2);

-- Insert data Category Group
INSERT INTO category_group(name)
VALUES (N'Device Defense'),
       (N'Network'),
       (N'Printer'),
       (N'Game machine'),
       (N'Computer'),
       (N'Accessories');

-- Insert data Category
INSERT INTO category(name, id_category_group)
VALUES (N'Central Processing Unit (CPU)', 1),
       (N'Mainboard', 1),
       (N'Graphics Processing Unit (GPU)', 1),
       (N'Random Access Memory (RAM)', 1),
       (N'Solid State Drive (SSD)', 1),
       (N'Hard Disk Drive (HDD)', 1),
       (N'Case computer', 6),
       (N'Power', 6),
       (N'Sound card', 6),
       (N'Bộ lưu điện', 6),
       (N'ITX', 6),
       (N'LCD', 6),
       (N'Keyboard', 6),
       (N'Mouse', 6),
       (N'Headphone', 6),
       (N'Printer lajer', 3),
       (N'Printer network', 3),
       (N'VPS', 2),
       (N'Play station', 4),
       (N'Disk gaming', 4),
       (N'Sony PS4', 4),
       (N'Gaming Accs', 4),
       (N'PC Desktop', 5),
       (N'PC Laptop', 5),
       (N'Bads', 6),
       (N'Mic record', 6),
       (N'Projectors', 6),
       (N'Camera', 6),
       (N'Hub-Cable-AIC', 6),
       (N'Playing stream', 6);

-- Insert Brand
INSERT INTO brand(name)
VALUES ('Intel'),
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

-- Insert Product
INSERT INTO product(name, price, quantity, id_category, id_brand)
VALUES 	(N'Intel 10th Gen Core i9-10850K Processor – Unlocked',12990,100,1,1),
       (N'ASUS ROG MAXIMUS XII EXTREME Mainboard – Z490 Chipset',17900,100,2,3),
       (N'ASUS TUF Gaming Geforce RTX 3090 24G Graphics Card',42800,100,3,3),
       (N'CORSAIR VENGEANCE RGB PRO Memory Kit – Black, 32GB',4900,100,4,12),
       (N'MSI Core Frozr S Air Cooler',1120,100,5,8),
       (N'Intel Optane Memory M10 Series 16GB SSD – 16GB, M.2 80mm PCIe 3.0, 20nm, 3D XPoint',990,100,6,1),
       (N'ASUS ROG Strix Helios Case – White',6690,100,7,3),
       (N'ASUS ROG Thor 850P PSU – 850W, 80Plus Platium, Full Modular, Sleeve Cable, AURA Sync',5790,100,8,3),
       (N'Creative Sound BlasterX AE-5 RGB Sound Card',3190,100,9,7),
       (N'ARES AR630 UPS – 3000VA, 2400W',8290,100,10,2),
       (N'ASUS ROG Strix Z490-I Gaming Mainboard',6890,100,11,3),
       (N'ASUS ROG Strix XG49VQ SUPER Ultra-Wide Gaming Monitor – 49″, DFHD, 144Hz, 1ms, HDR400, FreeSync 2',26990,100,12,3),
       (N'ASUS ROG Strix Scope Gaming Keyboard – Red Switch',3190,100,13,3),
       (N'ASUS ROG Chakram RGB Gaming Mouse',3890,100,14,3),
       (N'ASUS ROG Theta 7.1 Surround Gaming Headset',6890,100,15,3),
       (N'ASUS ROG Rapture GT-AC2900 Gaming Router – 750+2167Mbps, AiMesh, AURA RGB',5490,100,16,3),
       (N'Speed R1 Gaming Desk',2190,100,17,9),
       (N'ASUS ROG Chariot SL300C RGB Gaming Chair',15490,100,18,3),
       (N'Microsoft Xbox One S Controller – Phantom Black',2090,100,19,4),
       (N'Marvel’s Spider-Man Game Disc',550,100,20,10),
       (N'Gamming PS4 Pro & PS4 Slim',350,100,21,11),
       (N'Logitech G29 Driving Force Racing Wheel',6979,100,22,5),
       (N'Dell Vostro 3470ST (HXKWJ1) Desktop PC',7250,100,23,6),
       (N'Laptop Dell G3 Inspiron 3579 70167040 (Black) Geforce GTX1050Ti 4GB Intel Core i7 8750H 128GB 8GB',23490,100,24,6),
       (N'ASUS ROG Ranger BP3703 Gaming Backpack – Balo Gaming',6490,100,25,3);

-- Insert Event
INSERT INTO event(title, post, id_account)
VALUES (N'Buy ASUS NVIDIA Geforce gets gift Combo', '2020-11-19', 1),
       (N'How many money?', '2020-11-18', 1),
       (N'ASUS Combo ROG HOT 11th', '2020-11-16', 1),
       (N'Due Combo AORUS PC', '2020-11-15', 1),
       (N'Take PC ITX get Combo Wireless', '2020-11-14', 1);

-- Insert Review
INSERT INTO review(star, content, id_account, id_product)
VALUES (5, 'Good', 2, 1),
       (5, 'Good, no sale, up sale', 3, 1),
       (5, 'the best service', 4, 1),
       (4, 'No idia, meal to, got it no place', 5, 1),
       (3, 'OK!!', 6, 1);

-- Insert Bill
INSERT INTO bill(id_account, create_date, status)
VALUES (1, '2020-12-9', 0),
       (2, '2020-12-9', 1),
       (3, '2020-12-8', 3),
       (1, '2020-12-10', 0);
INSERT INTO bill(id_account, create_date, pay_date, status)
VALUES (2, '2020-12-7', '2020-12-11', 2),
       (2, '2020-12-20', '2020-12-21', 2),
       (2, '2020-10-31', '2020-11-02', 2),
       (2, '2020-09-01', '2020-09-08', 2),
       (2, '2020-08-10', '2020-08-15', 2);
       
INSERT INTO bill_details(id_bill, id_product, quantity)
VALUES (1, 1, 1),
       (1, 4, 1),
       (2, 5, 2),
       (2, 10, 1),
       (2, 20, 1),
       (3, 1, 1),
       (3, 2, 1),
       (3, 8, 1),
       (4, 11, 2),
       (4, 19, 1),
       (5, 25, 1),
       (5, 7, 1),
       (5, 2, 1),
       (6, 5, 2),
       (6, 12, 1),
       (7, 11, 1),
       (7, 5, 1),
       (7, 6, 1),
       (8, 19, 1),
       (9, 7, 2),
       (9, 1, 1);