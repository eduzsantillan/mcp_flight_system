-- Create flight table
CREATE TABLE flight (
    id SERIAL PRIMARY KEY,
    flight_number VARCHAR(10) NOT NULL UNIQUE,
    airline VARCHAR(100) NOT NULL,
    origin VARCHAR(50) NOT NULL,
    destination VARCHAR(50) NOT NULL,
    departure_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    available_seats INT NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create passenger table
CREATE TABLE passenger (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    passport_number VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create booking table
CREATE TABLE booking (
    id SERIAL PRIMARY KEY,
    booking_reference VARCHAR(10) NOT NULL UNIQUE,
    flight_number VARCHAR(10) NOT NULL,
    passenger_id INT NOT NULL,
    booking_time TIMESTAMP NOT NULL,
    seat_number VARCHAR(10) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (flight_number) REFERENCES flight(flight_number),
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);

-- Create trip table
CREATE TABLE trip (
    id SERIAL PRIMARY KEY,
    trip_reference VARCHAR(10) NOT NULL UNIQUE,
    passenger_id INT NOT NULL,
    creation_date TIMESTAMP NOT NULL,
    status VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (passenger_id) REFERENCES passenger(id)
);

-- Create trip_booking junction table for many-to-many relationship
CREATE TABLE trip_booking (
    id SERIAL PRIMARY KEY,
    trip_id INT NOT NULL,
    booking_reference VARCHAR(10) NOT NULL,
    FOREIGN KEY (trip_id) REFERENCES trip(id),
    FOREIGN KEY (booking_reference) REFERENCES booking(booking_reference),
    UNIQUE (trip_id, booking_reference)
);

-- Create indexes for better query performance
CREATE INDEX idx_flight_origin_destination ON flight(origin, destination);
CREATE INDEX idx_booking_passenger_id ON booking(passenger_id);
CREATE INDEX idx_trip_passenger_id ON trip(passenger_id);
