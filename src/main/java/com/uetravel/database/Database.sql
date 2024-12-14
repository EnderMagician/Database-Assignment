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

CREATE TABLE Customers (
    CustomerID INT AUTO_INCREMENT PRIMARY KEY,
    PasswordHash VARCHAR(255) NOT NULL,
    CustomerName VARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL,
    Address VARCHAR(255) NOT NULL
);

CREATE TABLE Employees (
    EmployeeID INT AUTO_INCREMENT PRIMARY KEY,
    EmployeeName VARCHAR(50) NOT NULL,
    Birthday DATE NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(20) UNIQUE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Position ENUM('Driver', 'Tour Guide') NOT NULL,
    StartedDate DATE NOT NULL,
    Salary DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Payments (
    PaymentID INT AUTO_INCREMENT PRIMARY KEY,
    PaymentDate DATE NOT NULL,
    Amount DECIMAL(10, 2) NOT NULL,
    PaymentMethod VARCHAR(15) NOT NULL,
    CustomerID INT,
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

CREATE TABLE Destinations (
    DestinationID INT AUTO_INCREMENT PRIMARY KEY,
    DestinationName VARCHAR(100),
    Description TEXT,
    ImageURL VARCHAR(255),
    Address VARCHAR(100) NOT NULL
);

CREATE TABLE VehicleTypes (
    Type VARCHAR(50) NOT NULL PRIMARY KEY,
    Seats INT NOT NULL,
    Lifespan INT NOT NULL,
    CostPer100km DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Vehicles (
    RegistrationNumber VARCHAR(9) PRIMARY KEY,
    Type VARCHAR(50),
    PurchasedDate DATE NOT NULL,
    FOREIGN KEY (Type) REFERENCES VehicleTypes(Type)
);

CREATE TABLE Hotels (
    HotelID INT AUTO_INCREMENT PRIMARY KEY,
    HotelName VARCHAR(100) NOT NULL,
    Address VARCHAR(100) NOT NULL,
    Rating INT CHECK (Rating BETWEEN 1 AND 5),
    PricePerDay DECIMAL(10,2) NOT NULL,
    RoomsAvailable INT NOT NULL,
    ImageURL VARCHAR(255)
);

CREATE TABLE Bookings (
    BookingID INT AUTO_INCREMENT PRIMARY KEY, 
    CustomerID INT NOT NULL,
    BookingDate DATETIME NOT NULL,
    TourID INT NOT NULL,
    AssignedHotel INT NOT NULL,
    AssignedVehicle VARCHAR(9) NOT NULL,
    PickupAddress VARCHAR(100) NOT NULL,
    Status ENUM('Paid', 'Not Paid', 'Canceled', 'Completed'),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID),
    FOREIGN KEY (TourID) REFERENCES Tours(TourID),
    FOREIGN KEY (AssignedHotel) REFERENCES Hotels(HotelID),
    FOREIGN KEY (AssignedVehicle) REFERENCES Vehicles(RegistrationNumber)
);

CREATE TABLE Assignings (
    AssigningID INT AUTO_INCREMENT PRIMARY KEY,
    DriverID INT NOT NULL,
    TourGuideID INT,
    Vehicle VARCHAR(9) NOT NULL,
    StartTime DATETIME NOT NULL,  
    EndTime DATETIME NOT NULL,  
    StartDestination INT NOT NULL,
    EndDestination INT NOT NULL,
    FOREIGN KEY (DriverID) REFERENCES Employees(EmployeeID),
    FOREIGN KEY (TourGuideID) REFERENCES Employees(EmployeeID),
    FOREIGN KEY (Vehicle) REFERENCES Vehicles(RegistrationNumber),
    FOREIGN KEY (StartDestination) REFERENCES Destinations(DestinationID),
    FOREIGN KEY (EndDestination) REFERENCES Destinations(DestinationID)
);

CREATE TABLE TourDestinations (
    TourID INT NOT NULL,
    DestinationID INT NOT NULL,
    ArrivalTime DATETIME NOT NULL,
    DepartureTime DATETIME NOT NULL,
    PRIMARY KEY (TourID, DestinationID),
    FOREIGN KEY (TourID) REFERENCES Tours(TourID),
    FOREIGN KEY (DestinationID) REFERENCES Destinations(DestinationID)
);