# Table Definitions

## Tours
- Stores all tours, including those that have ended.
<details>
  <summary><u>Expand to view Tour attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| TourID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each tour |
| TourName | VARCHAR(100) NOT NULL | Name of the tour |
| Description | TEXT | Detailed description of the tour |
| StartDate | DATE NOT NULL | Start date of the tour |
| EndDate | DATE NOT NULL | End date of the tour |
| BookingDeadline | DATE NOT NULL | Deadline for booking the tour |
| Price | DECIMAL(10, 2) NOT NULL | Price of the tour |
| MaxCapacity | INT NOT NULL | Maximum number of participants |
| Status | ENUM('Open', 'Closed') NOT NULL | Current status of the tour (Open or Closed) |
| ImageURL | VARCHAR(255) | URL of an image for the tour |
</details>

## Customers
- Stores customers data, with CustomerID used as username to login to the website.
<details>
  <summary><u>Expand to view Customer attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| CustomerID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each customer |
| PasswordHash | VARCHAR(255) NOT NULL | Hashed password for security |
| CustomerName | VARCHAR(50) NOT NULL | Name of the customer |
| Birthday | DATE NOT NULL | Customer's date of birth |
| Gender | ENUM('Male', 'Female', 'Other') NOT NULL | Customer's gender |
| Email | VARCHAR(100) UNIQUE NOT NULL | Customer's email address |
| PhoneNumber | VARCHAR(10) UNIQUE NOT NULL | Customer's phone number |
| Address | VARCHAR(255) NOT NULL | Customer's address |
</details>

## Employees
- Stores current employees data. As it serves the website purpose, the only positions available are Driver and TourGuide.
<details>
  <summary><u>Expand to view Employee attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| EmployeeID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each employee |
| EmployeeName | VARCHAR(50) NOT NULL | Name of the employee |
| Birthday | DATE NOT NULL | Employee's date of birth |
| Email | VARCHAR(100) UNIQUE NOT NULL | Employee's email address |
| PhoneNumber | VARCHAR(10) UNIQUE NOT NULL | Employee's phone number |
| Gender | ENUM('Male', 'Female', 'Other') NOT NULL | Employee's gender |
| Position | ENUM('Driver', 'TourGuide') NOT NULL | Employee's position (Driver or Tour Guide) |
| StartedDate | DATE NOT NULL | Date when the employee started working |
| Salary | DECIMAL(10, 2) NOT NULL | Employee's salary | 
</details>

## Payments
- Stores all customers transactions data.
<details>
  <summary><u>Expand to view Payment attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| PaymentID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each payment |
| PaymentDate | DATE NOT NULL | Date of the payment |
| Amount | DECIMAL(10, 2) NOT NULL | Amount paid |
| PaymentMethod | VARCHAR(15) NOT NULL | Payment method used |
| CustomerID | INT | Unique identifier of the customer making the payment, references to CustomerID in Customers |
</details>

## Destinations
- Stores all destinations data.
<details>
  <summary><u>Expand to view Destination attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| DestinationID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each destination |
| DestinationName | VARCHAR(100) | Name of the destination |
| Description | TEXT | Description of the destination |
| ImageURL | VARCHAR(255) | URL of an image for the destination |
| Address | VARCHAR(100) NOT NULL | Address of the destination |
</details>

## Vehicle Types
- Stores all vehicle types.
<details>
  <summary><u>Expand to view Vehicle Type attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| Type | VARCHAR(50) NOT NULL PRIMARY KEY | Type of the vehicle (e.g., Bus, Car) |
| Seats | INT NOT NULL | Number of seats in the vehicle |
| Lifespan | INT NOT NULL | Legal lifespan of the vehicle type (in years) |
| CostPer100km | DECIMAL(10, 2) NOT NULL | Cost of operating the vehicle per 100km |
</details>

## Vehicles
- Stores current vehicles that the company owns.
<details>
  <summary><u>Expand to view Vehicle attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| RegistrationNumber | VARCHAR(9) PRIMARY KEY | Unique registration number of the vehicle |
| Type | VARCHAR(50) | Type of the vehicle (foreign key to VehicleTypes) |
| PurchasedDate | DATE NOT NULL | Date when the vehicle was purchased |
</details>

## Hotels
- Stores hotels data.
<details>
  <summary><u>Expand to view Hotel attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| HotelID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each hotel |
| HotelName | VARCHAR(100) NOT NULL | Name of the hotel |
| Address | VARCHAR(100) NOT NULL | Address of the hotel |
| Rating | INT CHECK (Rating BETWEEN 1 AND 5) | Hotel rating (1-5 stars) |
| PricePerDay | DECIMAL(10,2) NOT NULL | Price per day for a room |
| RoomsAvailable | INT NOT NULL | Number of rooms available |
| ImageURL | VARCHAR(255) | URL of an image for the hotel |
</details>

## Bookings
- Stores all bookings data, including those that have been cancelled.
<details>
  <summary><u>Expand to view Booking attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| BookingID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each booking |
| CustomerID | INT NOT NULL | Unique identifier of the customer who made the booking, reference to CustomerID in Customers |
| BookingDate | DATETIME NOT NULL | Date and time of the booking |
| TourID | INT NOT NULL | Unique identifier of the tour being booked, reference to TourID in Tours |
| AssignedHotel | INT NOT NULL | Unique identifier of the hotel assigned to the booking, reference to HotelID in Hotel. This is assigned to customer automatically |
| AssignedVehicle | VARCHAR(9) NOT NULL | Registration number of the vehicle assigned to the booking, reference to RegistrationNumber in Vehicles. This is assigned to customer automatically |
| PickupAddress | VARCHAR(100) NOT NULL | Address where the customer will be picked up. This is assigned to customer automatically |
| Status | ENUM('Paid', 'NotPaid', 'Canceled', 'Completed') | Status of the booking (Paid, NotPaid, Canceled, Completed) |
</details>

## Assignings
- Stores employees assigned jobs. As each employee can participate in multiple tours in a day, startTime and endTime specify their job more thoroughly.
<details>
  <summary><u>Expand to view Assigning attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| AssigningID | INT AUTO_INCREMENT PRIMARY KEY | Unique identifier for each assigning |
| DriverID | INT NOT NULL | Unique identifier of the driver assigned |
| TourGuideID | INT | Unique identifier of the tour guide assigned (if any) |
| TourID | INT NOT NULL | Unique identifier of the tour being assigned |
| Vehicle | VARCHAR(9) NOT NULL | Registration number of the vehicle assigned |
| StartTime | DATETIME NOT NULL | Start date and time of the assigning |
| EndTime | DATETIME NOT NULL | End date and time of the assigning |
| StartDestination | INT NOT NULL |  Unique identifier of the starting destination |
| EndDestination | INT NOT NULL | Unique identifier of the ending destination |
</details>

## Tour Destinations
- Stores tours destinations, as this is a Many-to-Many relation.
<details>
  <summary><u>Expand to view Tour Destination attributes</u></summary>

| Attribute | Data Type | Description |
|---|---|---|
| TourID | INT NOT NULL | Unique identifier of the tour |
| DestinationID | INT NOT NULL | Unique identifier of the destination |
| ArrivalTime | DATETIME NOT NULL |  Date and time of arrival at the destination |
| DepartureTime | DATETIME NOT NULL | Date and time of departure from the destination |
</details>