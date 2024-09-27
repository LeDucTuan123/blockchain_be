CREATE DATABASE TranhNFTT;
GO

USE TranhNFTT;

DROP DATABASE TranhNFTT
GO


create table Users(
	Id int identity(1,1) primary key,
	Email varchar(255),
	Password varchar(100)  ,
	role VARCHAR(50) CHECK (role IN ('ARTIST', 'CUSTOMER', 'ADMIN')),
	Phone varchar(11)  ,
	FirstName nvarchar(50),
	LastName nvarchar(50),
    Address VARCHAR(255),
    Wallet VARCHAR(255),
)
GO


INSERT INTO Users (FirstName, LastName, Email, Password, Address, Phone, Role, Wallet)
VALUES 
    ('le', 'tuan', 'artist1@example.com', '123456', 'TP.HCM', '0989777888', 'ARTIST', 'BQQyQZdkVMUVZCpXZE4SaNC2SVLQAMgGCTBpJf74P4Y6'),
    ('le', 'phan', 'artist12@example.com', '123456', 'TP.HCM', '0989772888', 'CUSTOMER', 'Hs3z7zNBqqmxF8gnVqLYa2Z3x4h1DSCVAacCGLYKfVnu'),
    ('phan', 'huy', 'artist13@example.com', '123456', 'TP.HCM', '3989777888', 'ADMIN', '');

GO

CREATE TABLE Paintings (
    painting_id INT IDENTITY(1,1) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    painting_description TEXT,
    price FLOAT NOT NULL,
    artist_id INT,
    status BIT,
    image_url VARCHAR(255),
    created_at DATETIME DEFAULT GETDATE(),
    FOREIGN KEY (artist_id) REFERENCES Users(id)
);
GO

INSERT INTO Paintings (title, painting_description, price, artist_id, image_url)
VALUES 
    ('Sunset Over Mountains', 'A beautiful painting of a sunset over the mountains.', 0.0099, 1, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT1.jpg71.26957094661401?alt=media&token=4c9bc87d-507e-4ee7-af59-44869ff5b6c4'),
    ('Ocean Breeze', 'A calming painting of the ocean breeze.', 0.0009, 2, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT2.jpg850.105557926893?alt=media&token=6c6afdf7-2dc5-4260-9fae-173542455d90'),
    ('Forest Pathway', 'A serene painting of a pathway through the forest.', 129.99, 1, 'https://example.com/image3.jpg'),
    ('Desert Mirage', 'A stunning painting of a desert mirage.', 0.0209, 3, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT3.jpg834.095656513365?alt=media&token=7886deab-de7f-4fad-8753-9456509249d5'),
    ('City Skyline', 'A vibrant painting of a city skyline at night.', 0.0909, 2, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT5.jpg4.534434533272513?alt=media&token=49c47681-6875-4563-9e4c-2af45a51fc6c'),
    ('Winter Wonderland', 'A magical painting of a winter wonderland.', 0.0012, 1, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT6.jpg89.09347467769635?alt=media&token=7a9478f8-ba4c-4b99-ba18-38eeec0588ad'),
    ('Spring Blossoms', 'A delightful painting of spring blossoms.', 0.0089, 3, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2FNFT7.jpg374.85671137311203?alt=media&token=206bf004-3d5d-4ea8-9a2f-05ba9c3fb57b'),
    ('Autumn Leaves', 'A colorful painting of autumn leaves.', 0.0077, 2, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2Fbo_3_tranh_la_cay_phong-10531630.webp288.77764557463513?alt=media&token=b00bce5a-b7dd-48c0-9a09-48187a4002b5'),
    ('Tropical Paradise', 'A relaxing painting of a tropical paradise.', 169.99, 1, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2Fred.jpg923.6965800770703?alt=media&token=84c7718d-055f-4582-8881-326ff5a31999'),
    ('Mountain Reflection', 'A captivating painting of a mountain reflection in a lake.', 0.0999, 3, 'https://firebasestorage.googleapis.com/v0/b/paintingnft-a896e.appspot.com/o/tranh%2Fbo_3_tranh_la_cay_phong-10531630.webp288.77764557463513?alt=media&token=b00bce5a-b7dd-48c0-9a09-48187a4002b5');

create table Statuss(
	Id int primary key,
	Statuss nvarchar(50)
)

insert into Statuss
values
(1, N'Đang xử lý'),
(2, N'Đã thanh toán'),
(3, N'Chưa thanh toán'),
(4, N'Đã hủy')


create table Orders(
	Id int identity(1,1) primary key,
	OrderDate datetime not null,
	TotalAmount money null,
	UserId int not null,
	StatussId int not null,
	receiver nvarchar(255) null,
	CodeOrder varchar(16) null,
	Address varchar(266) null,
	foreign key(UserId) references Users(id),
	foreign key(StatussId) references Statuss(Id),
)

create table OrderDetails(
	Id int identity(1,1) primary key,
	Quantity int not null,
	Price money not null,
	OrderId int not null,
    PaintingId INT,
	foreign key(OrderId) references Orders(id),
	FOREIGN KEY (PaintingId) REFERENCES Paintings(painting_id)
)

select * from OrderDetails

SELECT * FROM Paintings;