CREATE DATABASE TranhNFTT;
GO

USE TranhNFTT;

DROP DATABASE TranhNFTT
GO

-- CREATE TABLE Users (
--     user_id INT IDENTITY(1,1) PRIMARY KEY,
--     user_name VARCHAR(100) NOT NULL,
--     user_email VARCHAR(100) NOT NULL UNIQUE,
--     user_password VARCHAR(100) NOT NULL,
--     user_address VARCHAR(255),
--     phone VARCHAR(15),
--     role VARCHAR(50) NOT NULL CHECK (role IN ('ARTIST', 'CUSTOMER', 'ADMIN'))
-- );

create table Users(
	Id int identity(1,1) not null primary key,
	Email varchar(255) not null unique,
	Password varchar(100) not null,
	role VARCHAR(50) NOT NULL CHECK (role IN ('ARTIST', 'CUSTOMER', 'ADMIN')),
	Phone varchar(11) null unique,
	FirstName nvarchar(50),
	LastName nvarchar(50),
    Address VARCHAR(255),
    Wallet VARCHAR(255),
)
GO
ALTER TABLE Users
ADD Address VARCHAR(255);

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
-- CREATE TABLE Orders (
--     order_id INT IDENTITY(1,1) PRIMARY KEY,
--     customer_id INT,
--     total_amount FLOAT NOT NULL,
--     order_status VARCHAR(50) NOT NULL,
--     created_at DATETIME DEFAULT GETDATE(),
--     FOREIGN KEY (customer_id) REFERENCES Users(user_id)
-- );
-- GO

-- CREATE TABLE OrderItems (
--     order_item_id INT IDENTITY(1,1) PRIMARY KEY,
--     order_id INT,
--     painting_id INT,
--     quantity INT NOT NULL,
--     price FLOAT NOT NULL,
--     FOREIGN KEY (order_id) REFERENCES Orders(order_id),
--     FOREIGN KEY (painting_id) REFERENCES Paintings(painting_id)
-- );

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

-- INSERT INTO Orders (OrderDate, TotalAmount, UserId, StatussId, receiver, CodeOrder)
-- VALUES 
    -- ('2024-08-01 10:00:00', 299.99, 1, 1, 'Le Tuan', 'ORD000001'),
    -- ('2024-08-02 11:30:00', 149.99, 2, 2, 'Le Phan', 'ORD000002'),
    -- ('2024-08-03 14:00:00', 199.99, 3, 3, 'Phan Huy', 'ORD000003'),
    -- ('2024-08-04 16:45:00', 89.99, 1, 1, 'Nguyen Van A', 'ORD000004'),
    -- ('2024-08-05 09:15:00', 129.99, 2, 2, 'Tran Thi B', 'ORD000005'),
    -- ('2024-08-06 12:20:00', 179.99, 3, 3, 'Hoang Van C', 'ORD000006'),
    -- ('2024-08-07 15:30:00', 239.99, 1, 1, 'Le Minh D', 'ORD000007'),
    -- ('2024-08-08 17:50:00', 299.99, 2, 2, 'Nguyen Thi E', 'ORD000008'),
    -- ('2024-08-09 13:10:00', 189.99, 3, 3, 'Phan Van F', 'ORD000009'),
    -- ('2024-08-10 18:25:00', 249.99, 1, 1, 'Tran Minh G', 'ORD000010');

-- INSERT INTO OrderDetails (Quantity, Price, OrderId, PaintingId)
-- VALUES 
--     (1, 199.99, 1, 1),
--     (2, 149.99, 2, 2),
--     (1, 199.99, 3, 3),
--     (3, 89.99, 4, 4),
--     (1, 129.99, 5, 5),
--     (2, 179.99, 6, 6),
--     (1, 239.99, 7, 7),
--     (3, 299.99, 8, 8),
--     (2, 189.99, 9, 9),
--     (1, 249.99, 10, 10);

-- GO



-- INSERT INTO Paintings (title, painting_description, price, artist_id, image_url)
-- VALUES 
-- ('Sunset', 'A beautiful sunset painting', 100.00, 1, 'url_image1'),
-- ('Mountain', 'A serene mountain landscape', 200.00, 1, 'url_image2');
-- GO

-- INSERT INTO Orders (customer_id, total_amount, order_status)
-- VALUES 
-- (2, 300.00, 'Pending');
-- GO

-- INSERT INTO OrderItems (order_id, painting_id, quantity, price)
-- VALUES 
-- (1, 1, 1, 100.00),
-- (1, 2, 1, 200.00);
-- GO

-- ALTER TABLE Paintings 
-- ADD CONSTRAINT fk_user_id
-- FOREIGN KEY (user_id) REFERENCES Users(user_id);

select * from OrderDetails

SELECT * FROM Paintings;