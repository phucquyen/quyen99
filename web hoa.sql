DROP DATABASE IF EXISTS ProductManagement1;
CREATE DATABASE ProductManagement1;
USE ProductManagement1;

DROP TABLE IF EXISTS Category;
CREATE TABLE Category(
    CategoryId    SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    CategoryName  VARCHAR(30) NOT NULL UNIQUE KEY
);

DROP TABLE IF EXISTS Product;
CREATE TABLE Product(
    ProductId        SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    ProductName      VARCHAR(50) NOT NULL UNIQUE KEY,
	ProductPrice     VARCHAR(50) NOT NULL,
	ProductInfo      VARCHAR(200) NOT NULL,
    ProductDetail    VARCHAR(500),
    RatingStar       TINYINT UNSIGNED, 
	ProductImageName VARCHAR(50) NOT NULL,
    CategoryId       SMALLINT UNSIGNED NOT NULL,

    FOREIGN KEY (CategoryId)     REFERENCES Category(CategoryId)
);

DROP TABLE IF EXISTS `Account`;
CREATE TABLE `Account`(
    AccountID            SMALLINT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
    Email                VARCHAR(50) NOT NULL UNIQUE KEY,
    Username             VARCHAR(50) NOT NULL UNIQUE KEY,
	FullName             VARCHAR(50) NOT NULL,
	AvatarImageName      VARCHAR(50) UNIQUE KEY,
	Mobile               VARCHAR(50) UNIQUE KEY NOT NULL,
	Address              VARCHAR(50) UNIQUE KEY NOT NULL,
    CreateDate           DATETIME DEFAULT NOW(),
    `Password`           VARCHAR(100) ,
	`Status`             TINYINT UNSIGNED DEFAULT 0  -- 0: Not Active, 1: Active
);
                  
/*============================== INSERT DATABASE =======================================*/
/*======================================================================================*/
-- Add data Manufacturer

                           
-- Add data Category
INSERT INTO Category(CategoryName) 
VALUES
						('HOA TÌNH YÊU'	),
						('HOA SINH NHẬT'),
						('HOA CƯỚI'	);      
                        
-- Add data Product
INSERT INTO Product (ProductName, 					ProductPrice,		 ProductInfo,																 ProductDetail, 	RatingStar,  ProductImageName,  CategoryId)			
VALUES 				('Only rose1 ',     '3.990.000₫',	'Bó hoa hồng nhập khẩu mang phong cách Hàn Quốc ',	                                      'ProductDetail1',        5,	       'Img1.png',                '1'),			
				    ('bink',            '1.900.000 ₫',	'Bó hoa đẹp tặng sinh nhật, Số lượng hoa gồm: 24-26 hoa hồng kem, cúc ping pong,',	      'ProductDetail2',        4,	       'Img2.png',                '1'),
                    ('Proud of you',     '1.100.000 ₫',	'Dịch vụ giao hoa tận nhà- miễn phí giao hàng trong nội thành, ',	                      'ProductDetail3',        3,	       'Img3.png',                '1'),
					('only rose4',       '1.690.000 ₫',	'Nhận giao hoa tận nơi chu đáo, Hotline: 0975.019.091 (zalo)',	                           'ProductDetail4',        4,	       'Img4.png',                '1'),
                    ('tươi tắn',         '2.690.000 ₫',	'Shop hoa Hải Hà nhận bán Giỏ hoa chúc mừng hồng tím Tinh Khôi hoa đẹp',	               'ProductDetail5',        5,	       'Img5.png',                '1'),
                    ('điều bất ngờ',     '1.990.000 ₫',	'Nguyên liệu: Cẩm tú cầu, hoa hồng, thuỷ tiên, cát tường, cúc pingpong, scabiosa,',	       'ProductDetail6',        4,	       'Img6.png',               '2'),
                    ('tân baby',        '1.899.000 ₫',	'Bó "Tình" là sự kết hợp tinh tế của hoa hồng da cao cấp',	                                'ProductDetail7',        5,	       'Img7.png',               '2');

                    
-- Add data Account
INSERT INTO `Account`(Email							, Username			, FullName				, AvatarImageName	     , Mobile	           , Address	       , CreateDate  				,`password`												,`status`)			
VALUES 				                                                                                                 
					('admin@gmail.com'				, 'admin'		    ,'admin'				    ,'admin.jpg'	    ,'0336030999'	,'Nam Từ Liêm - Hà Nội'		,'2022-05-19' ,			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi' 	,	1	),	
					('Email1@gmail.com'				, 'Username1'		,'Fullname1'				,'Avatar1.jpg'	 	,'0336984888'   ,'Bắc Từ Liêm - Hà Nội'		,'2021-03-05' ,			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi' 	,	1	),			
					('Email2@gmail.com'				, 'Username2'		,'Fullname2'				, 'Avatar2.jpg'	 	,'0988888888'	,'Cầu Giấy - Hà Nội'		,'2020-06-05' ,			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	1	),			
                    ('Email3@gmail.com'				, 'Username3'		,'Fullname3'				, 'Avatar3.jpg'	 	,'0945555666'	,'Nam Trực - Nam Định'		,'2019-06-07' ,			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	0	),			
                    ('Email4@gmail.com'				, 'Username4'		,'Fullname4'				, 'Avatar4.jpg'	 	,'0922222222'	,'Quận 1 - TP.HCM'		    ,'2018-03-04' ,			'$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	0	),			
                    ('Email5@gmail.com'				, 'Username5'		,'Fullname5'				, 'Avatar5.jpg'	 	,'0943456789'	,'Đà Lạt'	               ,'2020-02-10' ,		    '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	 0	),			
                    ('Email6@gmail.com'				, 'Username6'		,'Fullname6'				, 'Avatar6.jpg'	 	,'0988818188'	,'Quy Nhơn - Bình Định'	    ,'2017-02-11' ,		    '$2a$10$W2neF9.6Agi6kAKVq8q3fec5dHW8KUA.b0VSIGdIZyUravfLpyIFi'	,	0	);
                    