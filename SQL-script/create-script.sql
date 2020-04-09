drop  database IF EXISTS `shoesshop`;
create database `shoesshop`;

use `shoesshop`;

create table ShoesType
(
	id int not null auto_increment,
    name nvarchar(50),
    description nvarchar(1000),
    primary key (id)
);

create table ShoesBrand
(
	id int not null auto_increment,
    name nvarchar(50),
    country nvarchar(50),
    primary key (id)
);

create table Gender
(
	id int not null auto_increment,
    name nvarchar(50),
    primary key (id)
);

create table Shoes
(
	id int not null auto_increment,
    code nvarchar(50),
    name nvarchar(50),
    description nvarchar(10000),
    rating float,
    price float,
    isNew bool,
    isOnSale bool,
    salePrice float,
    saleExpiredDate datetime,
    typeID int,
    brandID int,
    genderID int,
    primary key (id),
    foreign key (typeID) references ShoesType(id),
    foreign key (brandID) references ShoesBrand(id),
    foreign key (genderID) references Gender(id)
);

create table Color
(
	id int not null auto_increment,
    name nvarchar(50),
    primary key (id)
);

create table Size
(
	id int not null auto_increment,
    name nvarchar(50),
    primary key (id)
);

create table Stock
(
	id int not null auto_increment,
    shoesID int,
    sizeID int,
    colorID int,
    instock int,
    primary key (id),
    foreign key (shoesID) references Shoes(id),
    foreign key (sizeID) references Size(id),
    foreign key (colorID) references Color(id)
);

create table ShoesImage
(
	id int not null auto_increment,
    shoesID int,
    colorID int,
    imagePath nvarchar(1000),
    primary key (id),
    foreign key (shoesID) references Shoes(id),
    foreign key (colorID) references Color(id)
);

create table CustomerType
(
	id int not null auto_increment,
    name nvarchar(50),
    description nvarchar(1000),
    point int,
    primary key (id)
);


create table Customer
(
	id int not null auto_increment,
    name nvarchar(50),
    dateOfBirth datetime,
    email nvarchar(50),
    phoneNumber varchar(50),
    gender nvarchar(50),
    point int,
    customerTypeID int,
    primary key (id),
    foreign key (customerTypeID) references CustomerType(id)
);

create table Address
(
	id int not null auto_increment,
    city nvarchar(50),
    district nvarchar(50),
    ward nvarchar(50),
    street nvarchar(50),
    customerID int,
    primary key (id),
    foreign key (customerID) references Customer(id)
);

create table Cart
(
	id int not null auto_increment,
    customerID int,
    primary key (id),
    foreign key (customerID) references Customer(id)
);

create table CartItem
(
	id int not null auto_increment,
    stockID int,
    cartID int,
    primary key (id),
    foreign key (cartID) references Cart(id),
    foreign key (stockID) references Stock(id)
);

create table Payment
(
	id int not null auto_increment,
    paymentType nvarchar(50),
    transactionID nvarchar(50),
    date datetime,
    status nvarchar(50),
    amount float,
    primary key (id)
);

create table `Order`
(
	id int not null auto_increment,
    orderDate datetime,
    total float,
    status nvarchar(50),
    paymentID int,
    customerID int,
    primary key (id),
    foreign key (customerID) references Customer(id),
    foreign key (paymentID) references Payment(id)
);

create table OrderItem
(
	id int not null auto_increment,
    orderID int,
    stockID int,
    amount int,
    pricePerUnit float,
    total float,
    primary key (id),
    foreign key (orderID) references `Order`(id),
    foreign key (stockID) references Stock(id)
);

create table Sale
(
	id int not null auto_increment,
    saleType nvarchar(50),
    amount float,
    expiredDate datetime,
    status nvarchar(50),
    primary key (id)
);

create table SaleProduct
(
	id int not null auto_increment,
    saleID int,
    productID int,
    primary key (id),
    foreign key (saleID) references Sale(id),
    foreign key (productID) references Shoes(id)
);

create table Account
(
	id int not null auto_increment,
    username varchar(50),
    passwordHash varchar(500),
    customerID int,
    primary key (id),
    foreign key (customerID) references Customer(id)
);
