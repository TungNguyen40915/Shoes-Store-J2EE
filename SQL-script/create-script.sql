drop database if exists ShoesStore;
create database ShoesStore;
ALTER DATABASE ShoesStore CHARACTER SET utf8 COLLATE utf8_general_ci;

use ShoesStore;

create table shoes(
id int not null auto_increment,
code char(255) not null unique,
name char(100) not null,
price int(11) not null,
remark text,
img1 text,
img2 text,
img3 text,
img4 text,
quantity int,
typeID int not null,
genderID int not null,
deleteFlag bit default 0,
primary key(id)
);

create table gender(
id int not null,
name char(50) not null,
primary key(id)
);


create table shoes_detail(
id int not null unique auto_increment,
size int not null, 
quantity int not null default 0,
shoesID int not null,
deleteFlag bit default 0,
primary key(id)
);

create table shoes_type(
id int not null auto_increment,
name char(100) not null,
brandID int not null,
deleteFlag bit default 0,
primary key(id)
);

create table brand(
id int not null auto_increment,
name char(100),
deleteFlag bit default 0,
primary key(id)
);

create table import(
id int not null auto_increment,
importDate datetime not null,
quantity int not null default 0,
brandID int not null,
primary key(id)
);

create table import_detail(
id int not null auto_increment,
importID int not null,
shoesDetailID int not null, 
quantity int not null default 0,
originalPrice int(11) default 0,
primary key(id)
);

create table customer(
id int not null auto_increment,
name char(100) not null,
address text not null,
phoneNumber int(10) not null,
userID int,
primary key(id)
);

create table shoes_order(
id int not null auto_increment,
orderDate datetime  not null,
deliveryDate datetime  not null,
statusID int not null,
customerID int not null,
totalCost int(11) not null default 0,
primary key(id)
);

create table status(
id int not null auto_increment,
name char(50),
primary key(id)
);

create table order_detail(
id int not null auto_increment,
orderID int not null,
shoesDetailID int not null,
quantity int not null,
totalCost int(11) not null,
primary key(id)
);
CREATE TABLE roles (
  id bigint(20) PRIMARY KEY  NOT NULL AUTO_INCREMENT,
  name varchar(60) NOT NULL UNIQUE KEY
);

create table user_roles(
userID int,
roleID int
);
create table users(
id int auto_increment primary key,
email varchar(45) unique,
username varchar(45) unique,
password varchar(256) not null
);


select count(id)
from shoes_order
where DATE(deliveryDate)= DATE(NOW());

select sum(totalCost)
from shoes_order
where WEEK(deliveryDate)= WEEK(NOW()) and statusID =3;

select sum(totalCost),MONTH(deliveryDate) as thang
from shoes_order
where YEAR(deliveryDate) = 2018
group by MONTH(deliveryDate)
order by thang;

select distinct year(deliveryDate)
from shoes_order;

select sum(ctdh.totalCost), hsx.name
from shoes_order dh, order_detail ctdh, 
shoes_detail ctg, shoes g,
shoes_type lg, brand hsx
where dh.id = ctdh.orderID 
and ctg.id = ctdh.shoesDetailID
and g.id = ctg.shoesID
and g.typeID = lg.id
and hsx.id = lg.brandID
and year(dh.deliveryDate) = 2018 and Month(dh.deliveryDate) = 12
group by hsx.id, hsx.name 
order by hsx.name;

select sum(quantity)
from shoes_order dh, order_detail ctdh
where dh.id = ctdh.orderID
and WEEK(deliveryDate)= WEEK(NOW()) and statusID =3;

-- Foreign key
alter table shoes add foreign key(genderID) references gender(id);
alter table shoes add foreign key(typeID) references shoes_type(id);
alter table shoes_type add foreign key(brandID) references brand(id);
alter table import add foreign key(brandID) references brand(id);
alter table import_detail add foreign key(shoesDetailID) references shoes_detail(id);
alter table import_detail add foreign key(importID) references import(id);
alter table shoes_order add foreign key(statusID) references status(id);
alter table shoes_order add foreign key(customerID) references customer(id);
alter table order_detail add foreign key(orderID) references shoes_order(id);
alter table order_detail add foreign key(shoesDetailID) references shoes_detail(id);
alter table shoes_detail add foreign key(shoesID) references shoes(id);
alter table customer add foreign key(userID) references users(id);

INSERT INTO `gender` (`id`, `name`) 
VALUES (1,'nam'),
       (2,'nữ');


INSERT INTO `brand` (`id`, `name`, `deleteFlag`) 
VALUES (1,'Nike',_binary '\0'),
	   (2,'Adidas',_binary '\0'),
	   (3,'Vans',_binary '\0'),
	   (4,'Converse',_binary '\0');

INSERT INTO `shoes_type` (`id`, `name`, `brandID`, `deleteFlag`) 
VALUES (1,'Adidas NMD',2,_binary '\0'),
	   (2,'Adidas Yeezy Boost 350',2,_binary '\0'),
	   (3,'Vans Old Skool',3,_binary '\0'),
	   (4,'Adidas Superstar',2,_binary '\0'),
	   (5,'Converse Chuck Taylor All Star',4,_binary '\0'),
	   (6,'Adidas Stan Smith',2,_binary '\0'),
	   (7,'Nike Air Max 90',1,_binary '\0'),
	   (8,'Nike Air Huarache',1,_binary '\0'),
	   (9,'Adidas Ultra Boost',2,_binary '\0'),
	   (10,'Vans Authentic',3,_binary '\0'),
	   (11,'Nike Air Presto',1,_binary '\0'),
	   (12,'Vans Era',3,_binary '\0');



INSERT INTO `users` (`id`, `email`, `username`, `password`) 
VALUES (1,'tung@abc','admin','$2a$10$W3knKX955yfvfLUW3gbJ..ge2jTXva/Pbp9J.h3RYXjahdWSIoCuO');

INSERT INTO `roles` (`id`, `name`) 
VALUES (3,'ROLE_ADMIN'),
	   (2,'ROLE_PM'),
	   (1,'ROLE_USER');

INSERT INTO `customer` (`id`, `name`, `address`, `phoneNumber`, `userID`) 
VALUES (1,'Tung','Long An',797727895,1);


INSERT INTO `shoes` (`id`, `code`, `name`, `price`, `remark`, `img1`, `img2`, `img3`, `img4`, `quantity`, `typeID`, `genderID`, `deleteFlag`) VALUES 
(1,'AJ01CourtPurple','Retro \"Court Purple\"',350,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804910_001.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804910_002.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804910_003.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804910_004.jpg',109,1,1,_binary '\0'),
(2,'AJ01PineGreen','Retro \"Pine Green\"',270,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804911_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804911_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804911_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804911_04.jpg',217,1,1,_binary '\0'),
(3,'AJ01High','Retro High (GG)',150,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802492_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802492_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802492_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802492_04.jpg',150,1,2,_binary '\0'),
(4,'AJ01HighBHM1','Retro High GG (GS) \"BHM 1\"',165,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800480_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800480_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800480_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800480_4.jpg',200,1,2,_binary '\0'),
(5,'AJ02BGRadioRaheem','Retro BG (GS) \"Radio Raheem\"',90,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012359_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012359_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012359_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012359_04.jpg',320,2,1,_binary '\0'),
(6,'AJ02BGWingIt','Retro BG \"Wing It\"',80,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012399_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012399_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012399_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012399_04.jpg',300,2,1,_binary '\0'),
(7,'AJ02GGJustDon','Retro Just Don GG (GS) \"Just Don\"',315,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800974_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800974_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800974_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800974_4.jpg',50,2,2,_binary '\0'),
(8,'AJ02BGAlternate87','Retro BG (GS) \"Alternate 87\"',90,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611742998-air-jordan-2-retro-bg-gs-alternate-87-black-varsity-red-012384_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611742998-air-jordan-2-retro-bg-gs-alternate-87-black-varsity-red-012384_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611742998-air-jordan-2-retro-bg-gs-alternate-87-black-varsity-red-012384_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611742998-air-jordan-2-retro-bg-gs-alternate-87-black-varsity-red-012384_4.jpg',123,2,1,_binary '\0'),
(9,'AJ03OGBlackCement','Retro OG \"Black Cement\"',275,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803111_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803111_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803111_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803111_04.jpg',210,3,1,_binary '\0'),
(10,'AJ03OGBGTrueBlue2016','Retro OG BG (GS) \"True Blue 2016 Release\"',150,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012571_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012571_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012571_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012571_4.jpg',210,3,1,_binary '\0'),
(11,'AJ03GG','Retro GG',75,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800058_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800058_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800058_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800058_4.jpg',210,3,2,_binary '\0'),
(12,'AJ03GS','(GS)',80,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/011800_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/011800_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/011800_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/011800_04.jpg',210,3,2,_binary '\0'),
(13,'AJ04BGRoyalty','Retro BG (GS) \"Royalty\"',145,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800265_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800265_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800265_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800265_4.jpg',210,4,1,_binary '\0'),
(14,'AJ04BGMotorSport','Retro BG (GS) \"Motor Sport\"',175,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800491_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800491_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800491_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800491_4.jpg',210,4,1,_binary '\0'),
(15,'AJ0430ThFuchsia','Retro 30TH GG (GS) \"Fuchsia\"',150,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/i/air-jordan-4-retro-30th-gg-black-fuschia-flash-lqd-lm-wht-012109_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/i/air-jordan-4-retro-30th-gg-black-fuschia-flash-lqd-lm-wht-012109_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/i/air-jordan-4-retro-30th-gg-black-fuschia-flash-lqd-lm-wht-012109_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/i/air-jordan-4-retro-30th-gg-black-fuschia-flash-lqd-lm-wht-012109_4.jpg',210,4,2,_binary '\0'),
(16,'AJ04Pearl','Retro Pearl GG \"30Th Anniversary\"',300,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012162_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012162_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012162_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/1/012162_4.jpg',210,4,2,_binary '\0'),
(17,'NiBAMidSpookyPack','The 10: Nike Blazer Mid \"Spooky Pack\"',1250,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804971_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804971_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804971_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804971_04.jpg',210,5,1,_binary '\0'),
(18,'NiBAMidGrimReaper','The 10: Nike Blazer Mid \"Grim Reaper\"',1250,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804758_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804758_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804758_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804758_04.jpg',210,5,1,_binary '\0'),
(19,'NiBAMidOffWhite','The 10: Nike Blazer Mid \"Off-White\"',1330,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801891_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801891_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801891_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801891_04.jpg',210,5,1,_binary '\0'),
(20,'NiBAQS','Blazer Studio As QS \"Vanchetta\"',90,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800492_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800492_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800492_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800492_4.jpg',210,5,1,_binary '\0'),
(21,'NiCBAirForce180','Air Force 180',99,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041323_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041323_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041323_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041323_04.jpg',210,6,1,_binary '\0'),
(22,'NiCBAirForce180Olympic','Air Force 180 \"Olympic\"',165,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611743074-nike-air-force-180-olympic-white-white-mid-navy-mtllc-gld-042627_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611743074-nike-air-force-180-olympic-white-white-mid-navy-mtllc-gld-042627_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611743074-nike-air-force-180-olympic-white-white-mid-navy-mtllc-gld-042627_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63611743074-nike-air-force-180-olympic-white-white-mid-navy-mtllc-gld-042627_4.jpg',210,6,1,_binary '\0'),
(23,'NiCBBarkleyPositeEggplant','Barkley Posite Max \"Eggplant\"',250,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041874_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041874_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041874_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041874_04.jpg',210,6,1,_binary '\0'),
(24,'NiCBBarkleyPositePhoenixSuns','',175,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041906_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041906_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041906_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/041906_04.jpg',210,6,1,_binary '\0'),
(25,'NiKDIVCT16','Zoom KD IV CT16 QS',135,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804104_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804104_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804104_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804104_04.jpg',210,7,1,_binary '\0'),
(26,'NiKDGalaxy','Zoom KD As \"Galaxy\"',490,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/n/i/nike-zoom-kd-4-as-galaxy-mtllc-silver-ttl-orng-drk-gry-041658_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/n/i/nike-zoom-kd-4-as-galaxy-mtllc-silver-ttl-orng-drk-gry-041658_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/n/i/nike-zoom-kd-4-as-galaxy-mtllc-silver-ttl-orng-drk-gry-041658_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/n/i/nike-zoom-kd-4-as-galaxy-mtllc-silver-ttl-orng-drk-gry-041658_4.jpg',210,7,1,_binary '\0'),
(27,'NiKDGumboLeague','KD 6 - AS \"Gumbo League\"',185,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/042071_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/042071_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/042071_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/0/4/042071_04.jpg',210,7,1,_binary '\0'),
(28,'NiKDAuntPearl','KD 8 Premium GS \"Aunt Pearl\"',90,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800870_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800870_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800870_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/800870_4.jpg',210,7,1,_binary '\0'),
(29,'NiKBAD','Kobe Ad',200,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804810_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804810_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804810_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804810_04.jpg',210,8,1,_binary '\0'),
(30,'NiKBProto','Kobe 1 Proto',135,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804935_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804935_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804935_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804935_04.jpg',210,8,1,_binary '\0'),
(31,'NiKBAdNxt','Kobe A.D. NXT',150,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801887_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801887_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801887_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801887_4.jpg',210,8,1,_binary '\0'),
(32,'NiKB360','Kobe AD NXT 360',350,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803738_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803738_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803738_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803738_04.jpg',210,8,1,_binary '\0'), (33,'AdUBJ','Ultra boost J',175,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802752_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802752_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802752_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802752_04.jpg',210,9,1,_binary '\0'),
(34,'AdUBJGS','Ultra boost J (GS)',220,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63601414741-adidas-ultraboost-j-white-white-201303_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63601414741-adidas-ultraboost-j-white-white-201303_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63601414741-adidas-ultraboost-j-white-white-201303_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/6/3/63601414741-adidas-ultraboost-j-white-white-201303_4.jpg',210,9,1,_binary '\0'),
(35,'AdUBJMultiColor','Ultraboost J \"Multi-Color\"',150,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801834_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801834_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801834_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/801834_04.jpg',210,9,2,_binary '\0'),
(36,'AdUB4.0','Ultraboost 4.0',175,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803261_01_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803261_01_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803261_01_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/803261_01_4.jpg',210,9,1,_binary '\0'),
(37,'AdYzWaveRunner','Yeezy boost 700 \"Wave Ruuner\"',450,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802501_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802501_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802501_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/802501_04.jpg',210,10,1,_binary '\0'),
(38,'AdYzButter','Adidas Yeezy Boost 350 V2 \"Butter\"',310,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804276_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804276_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804276_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804276_04.jpg',210,10,1,_binary '\0'),
(39,'AdYz950W','Yeezy 950W',460,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-yeezy-950-w-turtle-turtle-turtle-201147_1.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-yeezy-950-w-turtle-turtle-turtle-201147_2.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-yeezy-950-w-turtle-turtle-turtle-201147_3.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/a/d/adidas-yeezy-950-w-turtle-turtle-turtle-201147_4.jpg',210,10,2,_binary '\0'),
(40,'AdYzUltilityBlack','Yeezy 500 \"Utility Black\"',440,'','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804366_01.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804366_02.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804366_03.jpg','https://www.flightclub.com/media/catalog/product/cache/1/image/1600x1140/9df78eab33525d08d6e5fb8d27136e95/8/0/804366_04.jpg',210,10,1,_binary '\0');


INSERT INTO `shoes_detail` (`id`, `size`, `quantity`, `shoesID`, `deleteFlag`) 
VALUES (1,38,10,1,_binary '\0'),(2,39,10,1,_binary '\0'),(3,40,50,1,_binary '\0'),(4,41,50,1,_binary '\0'),(5,42,40,1,_binary '\0'),(6,43,10,1,_binary '\0'),(7,38,10,2,_binary '\0'),(8,39,10,2,_binary '\0'),(9,40,50,2,_binary '\0'),(10,41,50,2,_binary '\0'),(11,42,40,2,_binary '\0'),(12,43,10,2,_binary '\0'),(13,38,10,3,_binary '\0'),(14,39,10,3,_binary '\0'),(15,40,50,3,_binary '\0'),(16,41,50,3,_binary '\0'),(17,42,40,3,_binary '\0'),(18,43,10,3,_binary '\0'),(19,38,10,4,_binary '\0'),(20,39,10,4,_binary '\0'),(21,40,50,4,_binary '\0'),(22,41,50,4,_binary '\0'),(23,42,40,4,_binary '\0'),(24,43,10,4,_binary '\0'),(25,38,10,5,_binary '\0'),(26,39,10,5,_binary '\0'),(27,40,50,5,_binary '\0'),(28,41,50,5,_binary '\0'),(29,42,40,5,_binary '\0'),(30,43,10,5,_binary '\0'),(31,38,10,6,_binary '\0'),(32,39,10,6,_binary '\0'),(33,40,50,6,_binary '\0'),(34,41,50,6,_binary '\0'),(35,42,40,6,_binary '\0'),(36,43,10,6,_binary '\0'),(37,38,10,7,_binary '\0'),(38,39,10,7,_binary '\0'),(39,40,50,7,_binary '\0'),(40,41,50,7,_binary '\0'),(41,42,40,7,_binary '\0'),(42,43,10,7,_binary '\0'),(43,38,10,8,_binary '\0'),(44,39,10,8,_binary '\0'),(45,40,50,8,_binary '\0'),(46,41,50,8,_binary '\0'),(47,42,40,8,_binary '\0'),(48,43,10,8,_binary '\0'),(49,38,10,9,_binary '\0'),(50,39,10,9,_binary '\0'),(51,40,50,9,_binary '\0'),(52,41,50,9,_binary '\0'),(53,42,40,9,_binary '\0'),(54,43,10,9,_binary '\0'),(55,38,10,10,_binary '\0'),(56,39,10,10,_binary '\0'),(57,40,50,10,_binary '\0'),(58,41,50,10,_binary '\0'),(59,42,40,10,_binary '\0'),(60,43,10,10,_binary '\0'),(61,38,10,11,_binary '\0'),(62,39,10,11,_binary '\0'),(63,40,50,11,_binary '\0'),(64,41,50,11,_binary '\0'),(65,42,40,11,_binary '\0'),(66,43,10,11,_binary '\0'),(67,38,10,12,_binary '\0'),(68,39,10,12,_binary '\0'),(69,40,50,12,_binary '\0'),(70,41,50,12,_binary '\0'),(71,42,40,12,_binary '\0'),(72,43,10,12,_binary '\0'),(73,38,10,13,_binary '\0'),(74,39,10,13,_binary '\0'),(75,40,50,13,_binary '\0'),(76,41,50,13,_binary '\0'),(77,42,40,13,_binary '\0'),(78,43,10,13,_binary '\0'),(79,38,10,14,_binary '\0'),(80,39,10,14,_binary '\0'),(81,40,50,14,_binary '\0'),(82,41,50,14,_binary '\0'),(83,42,40,14,_binary '\0'),(84,43,10,14,_binary '\0'),(85,38,10,15,_binary '\0'),(86,39,10,15,_binary '\0'),(87,40,50,15,_binary '\0'),(88,41,50,15,_binary '\0'),(89,42,40,15,_binary '\0'),(90,43,10,15,_binary '\0'),(91,38,10,16,_binary '\0'),(92,39,10,16,_binary '\0'),(93,40,50,16,_binary '\0'),(94,41,50,16,_binary '\0'),(95,42,40,16,_binary '\0'),(96,43,10,16,_binary '\0'),(97,38,10,17,_binary '\0'),(98,39,10,17,_binary '\0'),(99,40,50,17,_binary '\0'),(100,41,50,17,_binary '\0'),(101,42,40,17,_binary '\0'),(102,43,10,17,_binary '\0'),(103,38,10,18,_binary '\0'),(104,39,10,18,_binary '\0'),(105,40,50,18,_binary '\0'),(106,41,50,18,_binary '\0'),(107,42,40,18,_binary '\0'),(108,43,10,18,_binary '\0'),(109,38,10,19,_binary '\0'),(110,39,10,19,_binary '\0'),(111,40,50,19,_binary '\0'),(112,41,50,19,_binary '\0'),(113,42,40,19,_binary '\0'),(114,43,10,19,_binary '\0'),(115,38,10,20,_binary '\0'),(116,39,10,20,_binary '\0'),(117,40,50,20,_binary '\0'),(118,41,50,20,_binary '\0'),(119,42,40,20,_binary '\0'),(120,43,10,20,_binary '\0'),(121,38,10,21,_binary '\0'),(122,39,10,21,_binary '\0'),(123,40,50,21,_binary '\0'),(124,41,50,21,_binary '\0'),(125,42,40,21,_binary '\0'),(126,43,10,21,_binary '\0'),(127,38,10,22,_binary '\0'),(128,39,10,22,_binary '\0'),(129,40,50,22,_binary '\0'),(130,41,50,22,_binary '\0'),(131,42,40,22,_binary '\0'),(132,43,10,22,_binary '\0'),(133,38,10,23,_binary '\0'),(134,39,10,23,_binary '\0'),(135,40,50,23,_binary '\0'),(136,41,50,23,_binary '\0'),(137,42,40,23,_binary '\0'),(138,43,10,23,_binary '\0'),(139,38,10,24,_binary '\0'),(140,39,10,24,_binary '\0'),(141,40,50,24,_binary '\0'),(142,41,50,24,_binary '\0'),(143,42,40,24,_binary '\0'),(144,43,10,24,_binary '\0'),(145,38,10,25,_binary '\0'),(146,39,10,25,_binary '\0'),(147,40,50,25,_binary '\0'),(148,41,50,25,_binary '\0'),(149,42,40,25,_binary '\0'),(150,43,10,25,_binary '\0'),(151,38,10,26,_binary '\0'),(152,39,10,26,_binary '\0'),(153,40,50,26,_binary '\0'),(154,41,50,26,_binary '\0'),(155,42,40,26,_binary '\0'),(156,43,10,26,_binary '\0'),(157,38,10,27,_binary '\0'),(158,39,10,27,_binary '\0'),(159,40,50,27,_binary '\0'),(160,41,50,27,_binary '\0'),(161,42,40,27,_binary '\0'),(162,43,10,27,_binary '\0'),(163,38,10,28,_binary '\0'),(164,39,10,28,_binary '\0'),(165,40,50,28,_binary '\0'),(166,41,50,28,_binary '\0'),(167,42,40,28,_binary '\0'),(168,43,10,28,_binary '\0'),(169,38,10,29,_binary '\0'),(170,39,10,29,_binary '\0'),(171,40,50,29,_binary '\0'),(172,41,50,29,_binary '\0'),(173,42,40,29,_binary '\0'),(174,43,10,29,_binary '\0'),(175,38,10,30,_binary '\0'),(176,39,10,30,_binary '\0'),(177,40,50,30,_binary '\0'),(178,41,50,30,_binary '\0'),(179,42,40,30,_binary '\0'),(180,43,10,30,_binary '\0'),(181,38,10,31,_binary '\0'),(182,39,10,31,_binary '\0'),(183,40,50,31,_binary '\0'),(184,41,50,31,_binary '\0'),(185,42,40,31,_binary '\0'),(186,43,10,31,_binary '\0'),(187,38,10,32,_binary '\0'),(188,39,10,32,_binary '\0'),(189,40,50,32,_binary '\0'),(190,41,50,32,_binary '\0'),(191,42,40,32,_binary '\0'),(192,43,10,32,_binary '\0'),(193,38,10,33,_binary '\0'),(194,39,10,33,_binary '\0'),(195,40,50,33,_binary '\0'),(196,41,50,33,_binary '\0'),(197,42,40,33,_binary '\0'),(198,43,10,33,_binary '\0'),(199,38,10,34,_binary '\0'),(200,39,10,34,_binary '\0'),(201,40,50,34,_binary '\0'),(202,41,50,34,_binary '\0'),(203,42,40,34,_binary '\0'),(204,43,10,34,_binary '\0'),(205,38,10,35,_binary '\0'),(206,39,10,35,_binary '\0'),(207,40,50,35,_binary '\0'),(208,41,50,35,_binary '\0'),(209,42,40,35,_binary '\0'),(210,43,10,35,_binary '\0'),(211,38,10,36,_binary '\0'),(212,39,10,36,_binary '\0'),(213,40,50,36,_binary '\0'),(214,41,50,36,_binary '\0'),(215,42,40,36,_binary '\0'),(216,43,10,36,_binary '\0'),(217,38,10,37,_binary '\0'),(218,39,10,37,_binary '\0'),(219,40,50,37,_binary '\0'),(220,41,50,37,_binary '\0'),(221,42,40,37,_binary '\0'),(222,43,10,37,_binary '\0'),(223,38,10,38,_binary '\0'),(224,39,10,38,_binary '\0'),(225,40,50,38,_binary '\0'),(226,41,50,38,_binary '\0'),(227,42,40,38,_binary '\0'),(228,43,10,38,_binary '\0'),(229,38,10,39,_binary '\0'),(230,39,10,39,_binary '\0'),(231,40,50,39,_binary '\0'),(232,41,50,39,_binary '\0'),(233,42,40,39,_binary '\0'),(234,43,10,39,_binary '\0'),(235,38,10,40,_binary '\0'),(236,39,10,40,_binary '\0'),(237,40,50,40,_binary '\0'),(238,41,50,40,_binary '\0'),(239,42,40,40,_binary '\0'),(240,43,10,40,_binary '\0');









commit;
