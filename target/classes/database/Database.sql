DROP DATABASE IF EXISTS TravelTours;
CREATE DATABASE TravelTours;

USE TravelTours;

CREATE TABLE Tours (
    TourID INT AUTO_INCREMENT PRIMARY KEY,
    TourName VARCHAR(100) NOT NULL,
    Description TEXT,
    StartDate DATE NOT NULL,
    EndDate DATE NOT NULL,
    BookingDeadline DATE NOT NULL,
    Price DECIMAL(10, 2) NOT NULL,
    MaxCapacity INT NOT NULL,
    Status ENUM('Open', 'Closed') NOT NULL,
    ImageURL VARCHAR(255)
);

CREATE INDEX idx_tourname ON Tours (TourName);
CREATE INDEX idx_tourid ON Tours (TourID);

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    PasswordHash VARCHAR(255) NOT NULL,
    CustomerName VARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(10) UNIQUE NOT NULL,
    Address VARCHAR(255) NOT NULL
);

CREATE INDEX idx_customername ON Customers (CustomerName);
CREATE INDEX idx_customerid ON Customers (CustomerID);

CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeName VARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(10) UNIQUE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Position ENUM('Driver', 'TourGuide') NOT NULL,
    StartedDate DATE NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

CREATE INDEX idx_employeename ON Employees (EmployeeName);
CREATE INDEX idx_employeeid ON Employees (EmployeeID);
CREATE INDEX idx_starteddate ON Employees (StartedDate);
CREATE INDEX idx_salary ON Employees (Salary);

CREATE TABLE Payments (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    PaymentDate DATE NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentMethod VARCHAR(15) NOT NULL,
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_paymentdate ON Payments (PaymentDate);
CREATE INDEX idx_amount ON Payments (Amount);
CREATE INDEX idx_customerid ON Payments (CustomerID);
CREATE INDEX idx_paymentid ON Payments (PaymentID);

CREATE TABLE Destinations (
    DestinationID INT AUTO_INCREMENT PRIMARY KEY,
    DestinationName VARCHAR(100),
    Description TEXT,
    ImageURL VARCHAR(255),
    Address VARCHAR(100) NOT NULL
);

CREATE INDEX idx_destinationname ON Destinations (DestinationName);
CREATE INDEX idx_destinationid ON Destinations (DestinationID);

CREATE TABLE VehicleTypes (
    Type VARCHAR(50) NOT NULL PRIMARY KEY,
    Seats INT NOT NULL,
    Lifespan INT NOT NULL,
    CostPer100km DECIMAL(10, 2) NOT NULL
);

CREATE INDEX idx_type ON VehicleTypes (Type);

CREATE TABLE Vehicles (
    RegistrationNumber VARCHAR(9) PRIMARY KEY,
    Type VARCHAR(50),
    PurchasedDate DATE NOT NULL,
    FOREIGN KEY (Type) REFERENCES VehicleTypes(Type) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_type ON Vehicles (Type);
CREATE INDEX idx_registrationnumber ON Vehicles (RegistrationNumber);

CREATE TABLE Hotels (
    HotelID INT AUTO_INCREMENT PRIMARY KEY,
    HotelName VARCHAR(100) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    PricePerDay DECIMAL(10,2) NOT NULL,
    RoomsAvailable INT NOT NULL,
    ImageURL VARCHAR(255)
);

CREATE INDEX idx_hotelname ON Hotels (HotelName);
CREATE INDEX idx_hotelid ON Hotels (HotelID);
CREATE INDEX idx_rating ON Hotels (Rating);
CREATE INDEX idx_priceperday ON Hotels (PricePerDay);

CREATE TABLE Bookings (
    BookingID INT AUTO_INCREMENT PRIMARY KEY, 
    CustomerID INT NOT NULL,
    BookingDate DATETIME NOT NULL,
    TourID INT NOT NULL,
    AssignedHotel INT NOT NULL,
    AssignedVehicle VARCHAR(9) NOT NULL,
    PickupAddress VARCHAR(100) NOT NULL,
    Status ENUM('Paid', 'NotPaid', 'Canceled', 'Completed'),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TourID) REFERENCES Tours(TourID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (AssignedHotel) REFERENCES Hotels(HotelID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (AssignedVehicle) REFERENCES Vehicles(RegistrationNumber) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_customerid ON Bookings (CustomerID);
CREATE INDEX idx_assignedhotel ON Bookings (AssignedHotel);

CREATE TABLE Assignings (
    AssigningID INT AUTO_INCREMENT PRIMARY KEY,
    DriverID INT NOT NULL,
    TourGuideID INT,
    TourID INT NOT NULL,
    Vehicle VARCHAR(9) NOT NULL,
    StartTime DATETIME NOT NULL,  
    EndTime DATETIME NOT NULL,  
    StartDestination INT NOT NULL,
    EndDestination INT NOT NULL,
    FOREIGN KEY (DriverID) REFERENCES Employees(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TourGuideID) REFERENCES Employees(EmployeeID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (Vehicle) REFERENCES Vehicles(RegistrationNumber) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (StartDestination) REFERENCES Destinations(DestinationID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (EndDestination) REFERENCES Destinations(DestinationID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (TourID) REFERENCES Tours(TourID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_driverid ON Assignings (DriverID);
CREATE INDEX idx_tourid ON Assignings (TourID);
CREATE INDEX idx_vehicle ON Assignings (Vehicle);
CREATE INDEX idx_startdestination ON Assignings (StartDestination);
CREATE INDEX idx_enddestination ON Assignings (EndDestination);

CREATE TABLE TourDestinations (
    TourID INT NOT NULL,
    DestinationID INT NOT NULL,
    ArrivalTime DATETIME NOT NULL,
    DepartureTime DATETIME NOT NULL,
    PRIMARY KEY (TourID, DestinationID),
    FOREIGN KEY (TourID) REFERENCES Tours(TourID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (DestinationID) REFERENCES Destinations(DestinationID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE INDEX idx_tourid ON TourDestinations (TourID);

INSERT INTO Tours (TourName, Description, StartDate, EndDate, BookingDeadline, Price, MaxCapacity, Status, ImageURL) VALUES
('Paris Getaway', 'Experience the city of love and lights.', '2024-07-01', '2024-07-07', '2024-06-15', 1200.50, 30, 'Open', 'images/paris.jpg'),
('Rome Adventure', 'Explore the history and architecture of Rome.', '2024-08-10', '2024-08-17', '2024-07-25', 1500.00, 25, 'Open', 'images/rome.jpg'),
('London City Tour', 'Visit the iconic landmarks of London.', '2024-09-05', '2024-09-10', '2024-08-20', 1100.75, 40, 'Open', 'images/london.jpg'),
('Tokyo Experience', 'Discover the bustling streets of Tokyo.', '2024-10-15', '2024-10-22', '2024-09-30', 1800.00, 35, 'Open', 'images/tokyo.jpg'),
('New York Highlights', 'Explore the Big Apple with a guided tour.', '2024-06-01', '2024-06-05', '2024-05-15', 950.00, 20, 'Closed', 'images/nyc.jpg'),
('Barcelona Beaches', 'Relax on the sunny beaches of Barcelona.', '2024-07-15', '2024-07-22', '2024-06-30', 1350.00, 30, 'Open', 'images/barcelona.jpg'),
('Sydney Wonders', 'See the beautiful landmarks of Sydney.', '2024-08-20', '2024-08-27', '2024-08-05', 1750.25, 28, 'Open', 'images/sydney.jpg'),
('Dubai Luxury Tour', 'A luxurious journey in the heart of Dubai.', '2024-11-01', '2024-11-08', '2024-10-15', 2200.00, 15, 'Open', 'images/dubai.jpg'),
('Maldives Escape', 'Relax in the tropical paradise of Maldives.', '2024-12-10', '2024-12-17', '2024-11-25', 3000.00, 12, 'Closed', 'images/maldives.jpg'),
('Bali Adventure', 'Explore the natural beauty of Bali.', '2024-05-10', '2024-05-17', '2024-04-25', 1400.00, 25, 'Open', 'images/bali.jpg'),
('Bangkok Highlights', 'Visit the cultural wonders of Bangkok.', '2024-07-20', '2024-07-27', '2024-07-05', 1250.50, 30, 'Open', 'images/bangkok.jpg'),
('Iceland Wonders', 'Witness the northern lights and glaciers.', '2024-10-01', '2024-10-08', '2024-09-15', 2500.00, 20, 'Closed', 'images/iceland.jpg'),
('Santorini Relaxation', 'Unwind on the Greek island of Santorini.', '2024-06-15', '2024-06-22', '2024-05-31', 1550.00, 18, 'Open', 'images/santorini.jpg'),
('Venice Canals Tour', 'Enjoy the waterways of Venice.', '2024-09-01', '2024-09-08', '2024-08-15', 1700.00, 24, 'Open', 'images/venice.jpg'),
('South Africa Safari', 'Experience the wildlife in South Africa.', '2024-11-15', '2024-11-22', '2024-10-31', 2900.00, 16, 'Open', 'images/safari.jpg'),
('Rio Carnival', 'Celebrate in the vibrant city of Rio.', '2024-02-20', '2024-02-27', '2024-02-05', 2000.00, 40, 'Closed', 'images/rio.jpg'),
('San Francisco Tour', 'Discover the sights of San Francisco.', '2024-08-05', '2024-08-10', '2024-07-20', 1600.00, 22, 'Open', 'images/sf.jpg'),
('Cairo Pyramids', 'Explore the ancient pyramids of Egypt.', '2024-09-20', '2024-09-27', '2024-09-05', 1800.00, 20, 'Open', 'images/cairo.jpg'),
('Lisbon City Break', 'Enjoy the charm of Lisbon.', '2024-05-25', '2024-05-30', '2024-05-10', 900.00, 30, 'Closed', 'images/lisbon.jpg'),
('Hawaii Paradise', 'Relax in the stunning beaches of Hawaii.', '2024-12-01', '2024-12-08', '2024-11-15', 2700.00, 15, 'Open', 'images/hawaii.jpg'),
('Berlin History Tour', 'A historical tour of Berlin.', '2024-06-10', '2024-06-15', '2024-05-25', 950.00, 35, 'Open', 'images/berlin.jpg'),
('Seoul Discovery', 'Discover the cultural heritage of Seoul.', '2024-07-01', '2024-07-08', '2024-06-20', 1200.00, 28, 'Open', 'images/seoul.jpg'),
('Prague Old Town', 'Explore the historic Old Town in Prague.', '2024-11-20', '2024-11-27', '2024-11-05', 1050.00, 20, 'Open', 'images/prague.jpg'),
('Mexico City Adventure', 'Explore the vibrant capital of Mexico.', '2024-08-12', '2024-08-18', '2024-07-28', 1300.00, 30, 'Open', 'images/mexico.jpg'),
('Marrakech Getaway', 'A cultural tour of Marrakech.', '2024-04-10', '2024-04-17', '2024-03-25', 950.00, 25, 'Closed', 'images/marrakech.jpg'),
('Hong Kong Highlights', 'Visit the bustling city of Hong Kong.', '2024-10-05', '2024-10-12', '2024-09-20', 1650.00, 22, 'Open', 'images/hongkong.jpg'),
('Buenos Aires Charm', 'Enjoy the elegance of Buenos Aires.', '2024-09-15', '2024-09-22', '2024-08-31', 1550.00, 18, 'Closed', 'images/buenosaires.jpg'),
('Vienna Music Tour', 'A musical tour of Vienna.', '2024-06-01', '2024-06-08', '2024-05-15', 1450.00, 24, 'Open', 'images/vienna.jpg'),
('Los Angeles Fun', 'Explore the entertainment capital of the world.', '2024-07-18', '2024-07-23', '2024-07-03', 1400.00, 20, 'Open', 'images/la.jpg'),
('Budapest Relaxation', 'Relax in the thermal baths of Budapest.', '2024-08-20', '2024-08-27', '2024-08-05', 1100.00, 25, 'Open', 'images/budapest.jpg'),
('Singapore Highlights', 'A cultural and modern mix in Singapore.', '2024-12-05', '2024-12-12', '2024-11-20', 1750.00, 18, 'Open', 'images/singapore.jpg'),
('Madrid Highlights', 'Discover the capital city of Spain.', '2024-11-10', '2024-11-17', '2024-10-25', 1300.00, 22, 'Open', 'images/madrid.jpg'),
('Toronto Experience', 'Explore the beauty of Toronto.', '2024-09-12', '2024-09-18', '2024-08-28', 1450.00, 20, 'Closed', 'images/toronto.jpg');

INSERT INTO Customers (PasswordHash, CustomerName, Birthday, Gender, Email, PhoneNumber, Address) VALUES
('5f4dcc3b5aa765d61d8327deb882cf99', 'User0', '1990-01-01', 'Male', 'user0@example.com', '1234567890', '123 Main St, City A'),
('a1b2c3d4e5f607890abcdef12345678', 'Alice Johnson', '1995-03-15', 'Female', 'alice.j@example.com', '1234567891', '456 Oak Rd, City B'),
('9c8b7a6d5e4f3g2h1i0jklmnopqrstuv', 'Bob Smith', '1988-07-22', 'Male', 'bob.smith@example.com', '1234567892', '789 Pine Ave, City C'),
('1a2b3c4d5e6f7g8h9i0jklmnopqrstu', 'Charlie Brown', '2000-11-30', 'Other', 'charlie.b@example.com', '1234567893', '321 Elm St, City D'),
('5d4c3b2a1f6e7g8h9i0jklmnopqrstu', 'Diana Prince', '1992-05-12', 'Female', 'diana.p@example.com', '1234567894', '741 Maple Dr, City E'),
('abcdef1234567890abcdef1234567890', 'Edward King', '1985-09-10', 'Male', 'edward.k@example.com', '1234567895', '852 Spruce Blvd, City F'),
('12345abcde67890f12345abcde67890f', 'Fiona Adams', '1998-02-17', 'Female', 'fiona.a@example.com', '1234567896', '963 Willow Ln, City G'),
('7890abcdef12345abcde67890f12345', 'George Taylor', '1993-08-25', 'Male', 'george.t@example.com', '1234567897', '159 Birch Ct, City H'),
('abcd1234efgh5678ijkl9012mnop3456', 'Hannah White', '2001-04-20', 'Female', 'hannah.w@example.com', '1234567898', '753 Cedar Rd, City I'),
('abcdef67890abcd12345efghijkl6789', 'Ian Wright', '1997-06-14', 'Male', 'ian.w@example.com', '1234567899', '456 Poplar Blvd, City J'),
('5678efghijkl1234mnopabcd9012abcd', 'Julia Green', '1989-10-11', 'Female', 'julia.g@example.com', '2234567890', '321 Sycamore Ln, City K'),
('6789ijklmnopabcd1234efgh567890ab', 'Kevin Scott', '1994-03-03', 'Male', 'kevin.s@example.com', '2234567891', '111 Fir St, City L'),
('9012mnopabcd3456efghijkl12347890', 'Laura Bell', '1991-12-25', 'Female', 'laura.b@example.com', '2234567892', '222 Beech Rd, City M'),
('3456mnopabcd7890ijkl1234efgh9012', 'Mike Ford', '1996-01-10', 'Male', 'mike.f@example.com', '2234567893', '333 Chestnut Ave, City N'),
('ijkl56789012mnopabcd3456efgh1234', 'Nina Blue', '2000-07-04', 'Female', 'nina.b@example.com', '2234567894', '444 Alder Ct, City O'),
('efgh9012mnop1234ijkl3456abcd6789', 'Oliver Ray', '1987-09-18', 'Male', 'oliver.r@example.com', '2234567895', '555 Holly Blvd, City P'),
('mnop1234efgh5678ijkl9012abcd3456', 'Paula Long', '1999-11-01', 'Female', 'paula.l@example.com', '2234567896', '666 Aspen Ln, City Q'),
('9012efghijkl3456mnopabcd12347890', 'Quincy Nash', '1984-02-28', 'Male', 'quincy.n@example.com', '2234567897', '777 Redwood Rd, City R'),
('5678abcd1234efghijkl9012mnop3456', 'Rachel Stone', '1993-05-06', 'Female', 'rachel.s@example.com', '2234567898', '888 Walnut Ave, City S'),
('ijkl9012mnop3456abcd5678efgh1234', 'Sam Black', '1990-08-21', 'Male', 'sam.b@example.com', '2234567899', '999 Hemlock Dr, City T'),
('mnop3456abcd1234ijkl5678efgh9012', 'Tina Hall', '1995-04-17', 'Female', 'tina.h@example.com', '3234567890', '101 Cedar Blvd, City U'),
('abcd5678efgh9012mnop3456ijkl1234', 'Umar Grey', '1997-06-29', 'Male', 'umar.g@example.com', '3234567891', '102 Pine Rd, City V'),
('5678ijkl1234abcd9012mnopefgh3456', 'Vera Hill', '2002-01-15', 'Female', 'vera.h@example.com', '3234567892', '103 Maple Ct, City W'),
('mnop9012efgh1234ijkl3456abcd5678', 'William Fox', '1998-12-19', 'Male', 'william.f@example.com', '3234567893', '104 Elm St, City X'),
('1234abcd5678efgh9012mnopijkl3456', 'Xena Green', '2003-07-13', 'Female', 'xena.g@example.com', '3234567894', '105 Oak Blvd, City Y'),
('abcd9012mnop3456efghijkl56781234', 'Yusuf Khan', '1996-09-11', 'Male', 'yusuf.k@example.com', '3234567895', '106 Birch Ln, City Z'),
('mnop5678efgh9012ijkl3456abcd1234', 'Zara Lee', '2000-05-25', 'Female', 'zara.l@example.com', '3234567896', '107 Willow Dr, City A1'),
('9012efgh5678ijkl1234mnopabcd3456', 'Chris White', '1994-02-12', 'Other', 'chris.w@example.com', '3234567897', '108 Cedar Rd, City B1'),
('1234ijkl9012efgh3456mnopabcd5678', 'Jordan Blue', '2001-06-06', 'Other', 'jordan.b@example.com', '3234567898', '109 Poplar Ave, City C1'),
('abcd3456mnop9012efghijkl56781234', 'Morgan Lane', '1989-03-29', 'Other', 'morgan.l@example.com', '3234567899', '110 Spruce Ct, City D1');

INSERT INTO Employees (EmployeeName, Birthday, Email, PhoneNumber, Gender, Position, StartedDate, Salary) VALUES
('John Smith', '1990-03-12', 'john.smith@example.com', '1234567890', 'Male', 'Driver', '2020-01-15', 2500.00),
('Emily Johnson', '1992-06-24', 'emily.j@example.com', '1234567891', 'Female', 'TourGuide', '2019-05-20', 3000.50),
('Michael Brown', '1988-11-05', 'michael.b@example.com', '1234567892', 'Male', 'Driver', '2018-07-12', 2700.00),
('Sarah Davis', '1995-01-18', 'sarah.d@example.com', '1234567893', 'Female', 'TourGuide', '2021-03-15', 3200.75),
('William Wilson', '1987-09-30', 'william.w@example.com', '1234567894', 'Male', 'Driver', '2017-11-01', 2800.00),
('Jessica Martinez', '1990-04-22', 'jessica.m@example.com', '1234567895', 'Female', 'TourGuide', '2020-08-10', 3100.00),
('David Anderson', '1993-07-14', 'david.a@example.com', '1234567896', 'Male', 'Driver', '2021-06-01', 2600.25),
('Ashley Thomas', '1994-02-25', 'ashley.t@example.com', '1234567897', 'Female', 'TourGuide', '2022-01-15', 3300.00),
('James Taylor', '1985-12-09', 'james.t@example.com', '1234567898', 'Male', 'Driver', '2016-10-05', 2900.50),
('Linda Moore', '1989-08-11', 'linda.m@example.com', '1234567899', 'Female', 'TourGuide', '2019-12-20', 3400.00),
('Robert Clark', '1991-05-02', 'robert.c@example.com', '2234567890', 'Male', 'Driver', '2020-09-15', 2750.00),
('Megan Rodriguez', '1996-03-30', 'megan.r@example.com', '2234567891', 'Female', 'TourGuide', '2022-03-10', 3500.75),
('Chris Lewis', '1986-01-01', 'chris.l@example.com', '2234567892', 'Other', 'Driver', '2017-03-01', 3000.00),
('Patricia Walker', '1997-09-18', 'patricia.w@example.com', '2234567893', 'Female', 'TourGuide', '2023-01-10', 3600.25),
('Joshua Hall', '1990-11-27', 'joshua.h@example.com', '2234567894', 'Male', 'Driver', '2020-02-05', 2850.50),
('Elizabeth Allen', '1992-07-23', 'elizabeth.a@example.com', '2234567895', 'Female', 'TourGuide', '2021-08-15', 3700.00),
('Daniel Young', '1984-10-20', 'daniel.y@example.com', '2234567896', 'Male', 'Driver', '2015-09-10', 3100.75),
('Sophia King', '1998-05-19', 'sophia.k@example.com', '2234567897', 'Female', 'TourGuide', '2023-02-01', 3800.00),
('Ryan Scott', '1993-12-14', 'ryan.s@example.com', '2234567898', 'Male', 'Driver', '2021-07-01', 2950.00),
('Olivia Lopez', '1995-06-17', 'olivia.l@example.com', '2234567899', 'Female', 'TourGuide', '2022-06-20', 3900.25),
('Ethan Carter', '1987-02-03', 'ethan.c@example.com', '3234567890', 'Male', 'Driver', '2018-04-25', 3200.00),
('Ava Mitchell', '1994-08-09', 'ava.m@example.com', '3234567891', 'Female', 'TourGuide', '2021-09-15', 4000.00),
('Henry Perez', '1991-10-15', 'henry.p@example.com', '3234567892', 'Male', 'Driver', '2020-12-05', 3300.50),
('Isabella Adams', '1999-01-22', 'isabella.a@example.com', '3234567893', 'Female', 'TourGuide', '2023-04-10', 4100.75),
('Lucas Collins', '1989-04-18', 'lucas.c@example.com', '3234567894', 'Male', 'Driver', '2019-05-01', 3400.00);

INSERT INTO Payments (PaymentDate, Amount, PaymentMethod, CustomerID) VALUES
('2024-01-10', 150.75, 'Credit Card', 1),
('2024-02-15', 200.00, 'Debit Card', 1),
('2024-03-05', 120.50, 'PayPal', 1),
('2024-03-25', 300.00, 'Credit Card', 1),
('2024-04-12', 180.25, 'Bank Transfer', 1),
('2024-05-01', 250.00, 'Cash', 1),
('2024-06-08', 400.75, 'Credit Card', 1),
('2024-01-20', 210.50, 'Debit Card', 2),
('2024-02-18', 305.00, 'Credit Card', 3),
('2024-03-12', 120.75, 'PayPal', 4),
('2024-03-28', 400.00, 'Bank Transfer', 5),
('2024-04-15', 250.25, 'Credit Card', 6),
('2024-05-05', 175.50, 'Cash', 7),
('2024-06-10', 320.00, 'Debit Card', 8),
('2024-01-12', 150.00, 'Credit Card', 9),
('2024-02-22', 275.25, 'Bank Transfer', 10),
('2024-03-15', 180.75, 'PayPal', 11),
('2024-04-02', 350.00, 'Credit Card', 12),
('2024-04-18', 200.50, 'Cash', 13),
('2024-05-12', 400.00, 'Debit Card', 14),
('2024-06-01', 180.25, 'Bank Transfer', 15),
('2024-06-15', 350.75, 'Credit Card', 16),
('2024-02-05', 290.00, 'Cash', 17),
('2024-03-22', 125.50, 'Debit Card', 18),
('2024-04-30', 300.75, 'PayPal', 19),
('2024-05-20', 200.00, 'Bank Transfer', 20),
('2024-06-18', 250.00, 'Credit Card', 21);

INSERT INTO Destinations (DestinationName, Description, ImageURL, Address) VALUES
('Eiffel Tower', 'A wrought-iron lattice tower in Paris, France, and a global cultural icon.', 'https://i.ytimg.com/vi/m_syl3ZTJjg/maxresdefault.jpg', 'Champ de Mars, Paris, France'),
('Colosseum', 'An ancient amphitheater in Rome, Italy, known for gladiatorial games.', 'https://i.ytimg.com/vi/LN23UvGYXO8/maxresdefault.jpg', 'Piazza del Colosseo, Rome, Italy'),
('Statue of Liberty', 'A colossal neoclassical sculpture on Liberty Island in New York Harbor.', 'https://i.ytimg.com/vi/7k0t4Ujj_eE/maxresdefault.jpg', 'Liberty Island, New York, USA'),
('Great Wall of China', 'A historic series of walls and fortifications in northern China.', 'https://europe1.discourse-cdn.com/unity/optimized/4X/7/1/a/71afe270e78f6246241239f5f82c6f1d64f1ae1f_2_690x403.jpeg', 'Huairou District, Beijing, China'),
('Sydney Opera House', 'A multi-venue performing arts center in Sydney, Australia.', 'https://i.ytimg.com/vi/6b6t3LlWlus/hq720.jpg?sqp=-oaymwEhCK4FEIIDSFryq4qpAxMIARUAAAAAGAElAADIQj0AgKJD&rs=AOn4CLDdY1z3KGXTYE-aaluJtVGA7Ec-3g', 'Bennelong Point, Sydney, Australia'),
('Santorini Beaches', 'Famous for its stunning beaches and picturesque villages.', 'https://cdn.mos.cms.futurecdn.net/rW7UpBBX8TGFPDicSL69Dd.jpg', 'Thira, Santorini, Greece'),
('Machu Picchu', 'A 15th-century Inca citadel located in the Andes Mountains.', 'https://i.redd.it/xdw6vuclgye81.jpg', 'Aguas Calientes, Peru'),
('Banff National Park', 'A beautiful national park with mountain landscapes in Canada.', 'https://i.redd.it/people-tell-me-shaders-ruin-builds-why-wip-survival-build-v0-q1c6aayp9o8c1.png?width=2560&format=png&auto=webp&s=9462faf866d27908862a45af1bef1599f57e24a1', 'Banff, Alberta, Canada'),
('Taj Mahal', 'A white marble mausoleum in Agra, India, built by Mughal emperor Shah Jahan.', 'https://i.redd.it/people-tell-me-shaders-ruin-builds-why-wip-survival-build-v0-newcszs5ao8c1.png?width=2560&format=png&auto=webp&s=48d6b33cea6420af8102d977baf6594e85612e41', 'Agra, Uttar Pradesh, India'),
('Dubai Burj Khalifa', 'The tallest building in the world located in Dubai.', 'https://minecraftshader.com/wp-content/uploads/2021/11/bsl-shaders-725x560.jpg', '1 Sheikh Mohammed Blvd, Dubai, UAE'),
('Mount Fuji', 'An iconic volcano and a symbol of Japan.', 'https://www.pcgamesn.com/wp-content/sites/pcgamesn/2022/12/minecraft-shaders-sildurs.jpg', 'Fujikawaguchiko, Yamanashi, Japan'),
('Times Square', 'A major commercial and tourist hub in New York City.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQsiUYiee6PQd5piTbxpkaonDuF_4T2hrBlLA&s', 'Manhattan, New York, USA'),
('Golden Gate Bridge', 'A suspension bridge spanning the Golden Gate in San Francisco.', 'https://static1.thegamerimages.com/wordpress/wp-content/uploads/2023/07/sildurs-shaders-example.jpg', 'Golden Gate Bridge, San Francisco, USA'),
('Petra', 'A historical and archaeological city in southern Jordan.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRDmbeojAsfRETudy14cyq9MX7tRFOYrE88DQ&s', 'Maan Governorate, Jordan'),
('Niagara Falls', 'Famous waterfalls located on the border of Canada and USA.', 'https://img.wonderhowto.com/img/54/61/63516424612707/0/install-shaders-for-minecraft-1-6-2-1-6-4.1280x600.jpg', 'Niagara Falls, Ontario, Canada'),
('Venice Canals', 'A city of canals and gondolas located in Italy.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTImMtVY0cSals_ktBjTdabAAMBPkmoqC6Z4g&s', 'Venice, Italy'),
('Chichen Itza', 'A large pre-Columbian archaeological site in Mexico.', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRHABlB6elfRm8WbV8QCQrXYuj8aL3PG47Ymg&s', 'Yucatan, Mexico'),
('Prague Castle', 'The largest ancient castle complex in the world.', 'https://www.pcgamesn.com/wp-content/sites/pcgamesn/2024/06/minecraft-shaders-complemtenary-550x309.jpg', 'Prague, Czech Republic'),
('Christ the Redeemer', 'A massive statue of Jesus Christ overlooking Rio de Janeiro.', 'https://www.4netplayers.com/images/4netplayers/blog/minecraft-shader-install/Minecraft-BSL-Shaders-3.webp', 'Corcovado, Rio de Janeiro, Brazil'),
('Great Barrier Reef', 'The worlds largest coral reef system in Australia.', 'https://ftw.usatoday.com/wp-content/uploads/sites/90/2022/05/Minecraft-Naelegos-Cel-Shaders.jpg', 'Queensland, Australia'),
('Stonehenge', 'A prehistoric monument located in Wiltshire, England.', 'https://cdn.pixabay.com/photo/2016/08/24/21/34/minecraft-1618089_1280.jpg', 'Salisbury, Wiltshire, England'),
('Angkor Wat', 'The largest religious monument in the world, located in Cambodia.', 'https://content.instructables.com/F1H/E30F/IF0XUTM7/F1HE30FIF0XUTM7.png?auto=webp&fit=bounds&frame=1&height=1024&width=1024auto=webp&frame=1&height=150', 'Siem Reap, Cambodia'),
('Louvre Museum', 'A historic museum and art gallery located in Paris.', 'https://static.planetminecraft.com/files/image/minecraft/mod/2022/479/15433168-landscape_xl.webp', 'Rue de Rivoli, Paris, France'),
('Yellowstone Park', 'An iconic national park with geysers and wildlife in the USA.', 'https://www.pcgamesn.com/wp-content/sites/pcgamesn/2023/04/best-minecraft-shaders-solas-aurora.jpg', 'Wyoming, Montana, USA'),
('Pyramids of Giza', 'The iconic pyramids and Sphinx located near Cairo, Egypt.', 'https://www.pcgamesn.com/wp-content/sites/pcgamesn/2023/04/best-minecraft-shaders-solas-aurora.jpg', 'Giza, Cairo, Egypt');

INSERT INTO VehicleTypes (Type, Seats, Lifespan, CostPer100km) VALUES
('Sedan', 5, 15, 8.50),
('SUV', 7, 12, 12.30),
('Truck', 2, 20, 15.75),
('Motorcycle', 2, 10, 4.20);

INSERT INTO Vehicles (RegistrationNumber, Type, PurchasedDate) VALUES
('ABC123456', 'Sedan', '2020-03-15'),
('XYZ987654', 'SUV', '2019-07-22'),
('LMN456789', 'Truck', '2021-06-30'),
('QRS135790', 'Motorcycle', '2022-01-10'),
('JKL246813', 'Sedan', '2018-05-05'),
('TUV321654', 'SUV', '2020-11-18'),
('GHI852963', 'Truck', '2021-09-25'),
('DEF741258', 'Motorcycle', '2023-02-14'),
('MNO369258', 'Sedan', '2017-08-09'),
('PQR159753', 'SUV', '2021-12-01');

INSERT INTO Hotels (HotelName, Address, Rating, PricePerDay, RoomsAvailable, ImageURL) VALUES
('Grand Palace Hotel', '123 Royal St, Cityville', 5, 250.00, 10, 'https://example.com/grandpalace.jpg'),
('Ocean View Resort', '456 Beach Rd, Seaside', 4, 180.00, 25, 'https://example.com/oceanview.jpg'),
('Mountain Retreat', '789 Summit Ave, Highlands', 3, 120.00, 15, 'https://example.com/mountainretreat.jpg'),
('City Lights Inn', '101 Downtown Blvd, Metropolis', 4, 150.00, 30, 'https://example.com/citylights.jpg'),
('Sunset Beach Hotel', '202 Coastline Dr, Beachtown', 5, 300.00, 12, 'https://example.com/sunsetbeach.jpg'),
('Lakeside Lodge', '303 Lakeshore Rd, Rivertown', 4, 170.00, 20, 'https://example.com/lakesidelodge.jpg'),
('Historic Mansion Hotel', '404 Old St, Heritage City', 3, 100.00, 5, 'https://example.com/historicmansion.jpg'),
('The Royal Plaza', '505 Plaza Blvd, Uptown', 5, 350.00, 8, 'https://example.com/royalplaza.jpg'),
('Comfort Suites', '606 Suburbia St, Oakville', 4, 140.00, 40, 'https://example.com/comfortsuites.jpg'),
('Skyline Hotel', '707 Skyline Dr, Skyline City', 3, 110.00, 18, 'https://example.com/skylinehotel.jpg');

-- 10 random bookings for User0 (CustomerID 1)
INSERT INTO Bookings (CustomerID, BookingDate, TourID, AssignedHotel, AssignedVehicle, PickupAddress, Status) VALUES
(1, '2024-12-10 09:00:00', 1, 1, 'ABC123456', '123 Royal St, Cityville', 'Paid'),
(1, '2024-12-12 14:30:00', 2, 2, 'XYZ987654', '456 Beach Rd, Seaside', 'NotPaid'),
(1, '2024-12-14 08:00:00', 3, 3, 'LMN456789', '789 Summit Ave, Highlands', 'Canceled'),
(1, '2024-12-15 18:00:00', 4, 4, 'QRS135790', '101 Downtown Blvd, Metropolis', 'Completed'),
(1, '2024-12-16 10:00:00', 5, 5, 'JKL246813', '202 Coastline Dr, Beachtown', 'Paid'),
(1, '2024-12-18 13:00:00', 6, 6, 'TUV321654', '303 Lakeshore Rd, Rivertown', 'NotPaid'),
(1, '2024-12-19 15:00:00', 7, 7, 'GHI852963', '404 Old St, Heritage City', 'Paid'),
(1, '2024-12-20 11:30:00', 8, 8, 'DEF741258', '505 Plaza Blvd, Uptown', 'Completed'),
(1, '2024-12-22 09:30:00', 9, 9, 'MNO369258', '606 Suburbia St, Oakville', 'Canceled'),
(1, '2024-12-24 14:45:00', 10, 10, 'PQR159753', '707 Skyline Dr, Skyline City', 'Paid');

-- 15 random bookings for other customers.html
INSERT INTO Bookings (CustomerID, BookingDate, TourID, AssignedHotel, AssignedVehicle, PickupAddress, Status) VALUES
(2, '2024-12-10 09:00:00', 2, 2, 'XYZ987654', '456 Beach Rd, Seaside', 'Paid'),
(3, '2024-12-12 14:30:00', 3, 3, 'LMN456789', '789 Summit Ave, Highlands', 'NotPaid'),
(4, '2024-12-14 08:00:00', 4, 4, 'QRS135790', '101 Downtown Blvd, Metropolis', 'Canceled'),
(5, '2024-12-15 18:00:00', 5, 5, 'JKL246813', '202 Coastline Dr, Beachtown', 'Paid'),
(6, '2024-12-16 10:00:00', 6, 6, 'TUV321654', '303 Lakeshore Rd, Rivertown', 'NotPaid'),
(7, '2024-12-18 13:00:00', 7, 7, 'GHI852963', '404 Old St, Heritage City', 'Paid'),
(8, '2024-12-19 15:00:00', 8, 8, 'DEF741258', '505 Plaza Blvd, Uptown', 'Completed'),
(9, '2024-12-20 11:30:00', 9, 9, 'MNO369258', '606 Suburbia St, Oakville', 'Paid'),
(10, '2024-12-22 09:30:00', 10, 10, 'PQR159753', '707 Skyline Dr, Skyline City', 'Canceled'),
(11, '2024-12-24 14:45:00', 1, 1, 'ABC123456', '123 Royal St, Cityville', 'Completed');

INSERT INTO Assignings (DriverID, TourGuideID, TourID, Vehicle, StartTime, EndTime, StartDestination, EndDestination) VALUES
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 1, 'ABC123456', '2024-12-10 08:00:00', '2024-12-10 12:00:00', 1, 2),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 2, 'XYZ987654', '2024-12-12 09:30:00', '2024-12-12 15:30:00', 2, 3),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 3, 'LMN456789', '2024-12-14 07:00:00', '2024-12-14 13:00:00', 3, 4),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 4, 'QRS135790', '2024-12-15 10:00:00', '2024-12-15 14:00:00', 4, 5),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 5, 'JKL246813', '2024-12-16 11:30:00', '2024-12-16 17:30:00', 5, 6),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 6, 'TUV321654', '2024-12-18 08:00:00', '2024-12-18 14:00:00', 6, 7),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 7, 'GHI852963', '2024-12-19 13:00:00', '2024-12-19 19:00:00', 7, 8),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 8, 'DEF741258', '2024-12-20 07:30:00', '2024-12-20 13:30:00', 8, 9),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 9, 'MNO369258', '2024-12-22 12:00:00', '2024-12-22 18:00:00', 9, 10),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 10, 'PQR159753', '2024-12-24 09:00:00', '2024-12-24 15:00:00', 10, 11),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 1, 'ABC123456', '2024-12-10 10:00:00', '2024-12-10 14:00:00', 1, 3),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 2, 'XYZ987654', '2024-12-12 08:30:00', '2024-12-12 14:30:00', 2, 4),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 3, 'LMN456789', '2024-12-14 09:00:00', '2024-12-14 15:00:00', 3, 5),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 4, 'QRS135790', '2024-12-15 07:30:00', '2024-12-15 13:30:00', 4, 6),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 5, 'JKL246813', '2024-12-16 08:30:00', '2024-12-16 14:30:00', 5, 7),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 6, 'TUV321654', '2024-12-18 10:00:00', '2024-12-18 16:00:00', 6, 8),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 7, 'GHI852963', '2024-12-19 12:00:00', '2024-12-19 18:00:00', 7, 9),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 8, 'DEF741258', '2024-12-20 09:00:00', '2024-12-20 15:00:00', 8, 10),
((SELECT EmployeeID FROM Employees ORDER BY RAND() LIMIT 1), (SELECT EmployeeID FROM Employees WHERE Position = 'TourGuide' ORDER BY RAND() LIMIT 1), 9, 'MNO369258', '2024-12-22 10:00:00', '2024-12-22 16:00:00', 9, 11);

INSERT INTO TourDestinations (TourID, DestinationID, ArrivalTime, DepartureTime) VALUES
(1, 1, '2024-12-10 08:00:00', '2024-12-13 12:00:00'),
(1, 2, '2024-12-13 13:00:00', '2024-12-14 17:00:00'),
(2, 3, '2024-12-12 09:00:00', '2024-12-15 13:00:00'),
(2, 4, '2024-12-15 14:00:00', '2024-12-16 18:00:00'),
(3, 5, '2024-12-14 08:30:00', '2024-12-17 12:30:00'),
(3, 6, '2024-12-17 13:30:00', '2024-12-18 17:30:00'),
(4, 7, '2024-12-15 10:00:00', '2024-12-18 14:00:00'),
(4, 8, '2024-12-18 15:00:00', '2024-12-19 19:00:00'),
(5, 9, '2024-12-16 07:30:00', '2024-12-19 11:30:00'),
(5, 10, '2024-12-19 12:30:00', '2024-12-20 16:30:00'),
(6, 11, '2024-12-18 09:00:00', '2024-12-21 13:00:00'),
(6, 12, '2024-12-21 14:00:00', '2024-12-22 18:00:00'),
(7, 13, '2024-12-19 08:30:00', '2024-12-22 12:30:00'),
(7, 14, '2024-12-22 13:30:00', '2024-12-23 17:30:00'),
(8, 15, '2024-12-20 10:00:00', '2024-12-23 14:00:00'),
(8, 16, '2024-12-23 15:00:00', '2024-12-24 19:00:00'),
(9, 17, '2024-12-22 09:00:00', '2024-12-25 13:00:00'),
(9, 18, '2024-12-25 14:00:00', '2024-12-26 18:00:00'),
(10, 19, '2024-12-24 08:30:00', '2024-12-27 12:30:00'),
(10, 20, '2024-12-27 13:30:00', '2024-12-28 17:30:00'),
(11, 21, '2024-12-10 09:00:00', '2024-12-13 13:00:00'),
(12, 22, '2024-12-12 10:00:00', '2024-12-15 14:00:00'),
(13, 23, '2024-12-14 11:00:00', '2024-12-17 15:00:00'),
(14, 24, '2024-12-16 12:00:00', '2024-12-19 16:00:00'),
(15, 25, '2024-12-18 08:00:00', '2024-12-21 12:00:00');