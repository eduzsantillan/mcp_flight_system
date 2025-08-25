-- Add initial flight data (50 flights)

-- North America flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('AA100', 'American Airlines', 'LAX', 'JFK', '2025-08-10 08:00:00', '2025-08-10 16:30:00', 350.00, 180, 'SCHEDULED'),
  ('AA101', 'American Airlines', 'JFK', 'LAX', '2025-08-10 10:00:00', '2025-08-10 13:30:00', 375.00, 180, 'SCHEDULED'),
  ('DL200', 'Delta Airlines', 'ATL', 'SEA', '2025-08-11 07:15:00', '2025-08-11 10:05:00', 310.00, 160, 'SCHEDULED'),
  ('DL201', 'Delta Airlines', 'SEA', 'ATL', '2025-08-11 12:30:00', '2025-08-11 20:15:00', 325.00, 160, 'SCHEDULED'),
  ('UA300', 'United Airlines', 'ORD', 'SFO', '2025-08-12 09:45:00', '2025-08-12 12:30:00', 290.00, 150, 'SCHEDULED'),
  ('UA301', 'United Airlines', 'SFO', 'ORD', '2025-08-12 14:00:00', '2025-08-12 20:15:00', 305.00, 150, 'SCHEDULED'),
  ('WN400', 'Southwest Airlines', 'DEN', 'LAS', '2025-08-13 08:30:00', '2025-08-13 09:45:00', 175.00, 140, 'SCHEDULED'),
  ('WN401', 'Southwest Airlines', 'LAS', 'DEN', '2025-08-13 11:00:00', '2025-08-13 14:15:00', 185.00, 140, 'SCHEDULED'),
  ('B6500', 'JetBlue', 'BOS', 'MCO', '2025-08-14 10:30:00', '2025-08-14 13:45:00', 220.00, 165, 'SCHEDULED'),
  ('B6501', 'JetBlue', 'MCO', 'BOS', '2025-08-14 15:00:00', '2025-08-14 18:15:00', 235.00, 165, 'SCHEDULED');

-- Europe flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('BA100', 'British Airways', 'LHR', 'CDG', '2025-08-10 07:00:00', '2025-08-10 09:15:00', 220.00, 150, 'SCHEDULED'),
  ('BA101', 'British Airways', 'CDG', 'LHR', '2025-08-10 10:30:00', '2025-08-10 11:45:00', 210.00, 150, 'SCHEDULED'),
  ('LH200', 'Lufthansa', 'FRA', 'MAD', '2025-08-11 08:15:00', '2025-08-11 10:45:00', 245.00, 170, 'SCHEDULED'),
  ('LH201', 'Lufthansa', 'MAD', 'FRA', '2025-08-11 12:00:00', '2025-08-11 14:30:00', 255.00, 170, 'SCHEDULED'),
  ('AF300', 'Air France', 'CDG', 'FCO', '2025-08-12 09:30:00', '2025-08-12 11:45:00', 230.00, 160, 'SCHEDULED'),
  ('AF301', 'Air France', 'FCO', 'CDG', '2025-08-12 13:00:00', '2025-08-12 15:15:00', 240.00, 160, 'SCHEDULED'),
  ('IB400', 'Iberia', 'MAD', 'LIS', '2025-08-13 10:45:00', '2025-08-13 11:30:00', 180.00, 140, 'SCHEDULED'),
  ('IB401', 'Iberia', 'LIS', 'MAD', '2025-08-13 12:45:00', '2025-08-13 13:30:00', 175.00, 140, 'SCHEDULED'),
  ('AZ500', 'Alitalia', 'FCO', 'ATH', '2025-08-14 11:15:00', '2025-08-14 14:30:00', 265.00, 155, 'SCHEDULED'),
  ('AZ501', 'Alitalia', 'ATH', 'FCO', '2025-08-14 16:00:00', '2025-08-14 17:15:00', 255.00, 155, 'SCHEDULED');

-- Asia flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('CX100', 'Cathay Pacific', 'HKG', 'NRT', '2025-08-10 09:00:00', '2025-08-10 14:15:00', 420.00, 200, 'SCHEDULED'),
  ('CX101', 'Cathay Pacific', 'NRT', 'HKG', '2025-08-10 16:30:00', '2025-08-10 20:45:00', 435.00, 200, 'SCHEDULED'),
  ('SQ200', 'Singapore Airlines', 'SIN', 'BKK', '2025-08-11 08:45:00', '2025-08-11 10:15:00', 310.00, 190, 'SCHEDULED'),
  ('SQ201', 'Singapore Airlines', 'BKK', 'SIN', '2025-08-11 12:00:00', '2025-08-11 15:30:00', 325.00, 190, 'SCHEDULED'),
  ('JL300', 'Japan Airlines', 'NRT', 'ICN', '2025-08-12 10:30:00', '2025-08-12 13:15:00', 380.00, 180, 'SCHEDULED'),
  ('JL301', 'Japan Airlines', 'ICN', 'NRT', '2025-08-12 15:00:00', '2025-08-12 17:45:00', 395.00, 180, 'SCHEDULED'),
  ('OZ400', 'Asiana Airlines', 'ICN', 'PVG', '2025-08-13 09:15:00', '2025-08-13 11:45:00', 340.00, 170, 'SCHEDULED'),
  ('OZ401', 'Asiana Airlines', 'PVG', 'ICN', '2025-08-13 13:30:00', '2025-08-13 16:00:00', 350.00, 170, 'SCHEDULED'),
  ('TG500', 'Thai Airways', 'BKK', 'DEL', '2025-08-14 11:45:00', '2025-08-14 14:30:00', 405.00, 185, 'SCHEDULED'),
  ('TG501', 'Thai Airways', 'DEL', 'BKK', '2025-08-14 16:15:00', '2025-08-14 22:00:00', 415.00, 185, 'SCHEDULED');

-- Oceania flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('QF100', 'Qantas', 'SYD', 'AKL', '2025-08-15 08:30:00', '2025-08-15 13:45:00', 390.00, 175, 'SCHEDULED'),
  ('QF101', 'Qantas', 'AKL', 'SYD', '2025-08-15 15:30:00', '2025-08-15 17:45:00', 375.00, 175, 'SCHEDULED'),
  ('NZ200', 'Air New Zealand', 'AKL', 'MEL', '2025-08-16 09:15:00', '2025-08-16 11:30:00', 360.00, 165, 'SCHEDULED'),
  ('NZ201', 'Air New Zealand', 'MEL', 'AKL', '2025-08-16 13:00:00', '2025-08-16 18:15:00', 370.00, 165, 'SCHEDULED'),
  ('VA300', 'Virgin Australia', 'BNE', 'PER', '2025-08-17 07:45:00', '2025-08-17 10:30:00', 425.00, 160, 'SCHEDULED'),
  ('VA301', 'Virgin Australia', 'PER', 'BNE', '2025-08-17 12:15:00', '2025-08-17 18:30:00', 440.00, 160, 'SCHEDULED');

-- South America flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('LA100', 'LATAM Airlines', 'GRU', 'SCL', '2025-08-15 09:30:00', '2025-08-15 12:45:00', 380.00, 170, 'SCHEDULED'),
  ('LA101', 'LATAM Airlines', 'SCL', 'GRU', '2025-08-15 14:30:00', '2025-08-15 17:45:00', 395.00, 170, 'SCHEDULED'),
  ('AR200', 'Aerolineas Argentinas', 'EZE', 'LIM', '2025-08-16 10:15:00', '2025-08-16 13:30:00', 365.00, 160, 'SCHEDULED'),
  ('AR201', 'Aerolineas Argentinas', 'LIM', 'EZE', '2025-08-16 15:00:00', '2025-08-16 18:15:00', 375.00, 160, 'SCHEDULED'),
  ('AV300', 'Avianca', 'BOG', 'UIO', '2025-08-17 08:45:00', '2025-08-17 10:15:00', 280.00, 150, 'SCHEDULED'),
  ('AV301', 'Avianca', 'UIO', 'BOG', '2025-08-17 11:45:00', '2025-08-17 13:15:00', 290.00, 150, 'SCHEDULED');

-- Middle East flights
INSERT INTO flight (flight_number, airline, origin, destination, departure_time, arrival_time, price, available_seats, status)
VALUES 
  ('EK100', 'Emirates', 'DXB', 'DOH', '2025-08-18 08:00:00', '2025-08-18 08:45:00', 275.00, 220, 'SCHEDULED'),
  ('EK101', 'Emirates', 'DOH', 'DXB', '2025-08-18 10:15:00', '2025-08-18 11:00:00', 265.00, 220, 'SCHEDULED'),
  ('QR200', 'Qatar Airways', 'DOH', 'CAI', '2025-08-19 09:30:00', '2025-08-19 12:45:00', 345.00, 210, 'SCHEDULED'),
  ('QR201', 'Qatar Airways', 'CAI', 'DOH', '2025-08-19 14:15:00', '2025-08-19 17:30:00', 355.00, 210, 'SCHEDULED'),
  ('EY300', 'Etihad Airways', 'AUH', 'AMM', '2025-08-20 10:45:00', '2025-08-20 12:30:00', 320.00, 200, 'SCHEDULED'),
  ('EY301', 'Etihad Airways', 'AMM', 'AUH', '2025-08-20 14:00:00', '2025-08-20 15:45:00', 330.00, 200, 'SCHEDULED');

-- Add a sample passenger for testing
INSERT INTO passenger (first_name, last_name, email, phone_number, passport_number)
VALUES ('Eduardo', 'Zuñiga', 'eduzsantillan@yopmail.com', '+1234567890', 'AB1234567');
