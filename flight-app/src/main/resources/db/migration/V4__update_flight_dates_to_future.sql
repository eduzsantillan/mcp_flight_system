-- Update all flight departure and arrival times to future dates (December 2025)

-- North America flights
UPDATE flight SET 
    departure_time = '2025-12-10 08:00:00', 
    arrival_time = '2025-12-10 16:30:00' 
WHERE flight_number = 'AA100';

UPDATE flight SET 
    departure_time = '2025-12-10 10:00:00', 
    arrival_time = '2025-12-10 13:30:00' 
WHERE flight_number = 'AA101';

UPDATE flight SET 
    departure_time = '2025-12-11 07:15:00', 
    arrival_time = '2025-12-11 10:05:00' 
WHERE flight_number = 'DL200';

UPDATE flight SET 
    departure_time = '2025-12-11 12:30:00', 
    arrival_time = '2025-12-11 20:15:00' 
WHERE flight_number = 'DL201';

UPDATE flight SET 
    departure_time = '2025-12-12 09:45:00', 
    arrival_time = '2025-12-12 12:30:00' 
WHERE flight_number = 'UA300';

UPDATE flight SET 
    departure_time = '2025-12-12 14:00:00', 
    arrival_time = '2025-12-12 20:15:00' 
WHERE flight_number = 'UA301';

UPDATE flight SET 
    departure_time = '2025-12-13 08:30:00', 
    arrival_time = '2025-12-13 09:45:00' 
WHERE flight_number = 'WN400';

UPDATE flight SET 
    departure_time = '2025-12-13 11:00:00', 
    arrival_time = '2025-12-13 14:15:00' 
WHERE flight_number = 'WN401';

UPDATE flight SET 
    departure_time = '2025-12-14 10:30:00', 
    arrival_time = '2025-12-14 13:45:00' 
WHERE flight_number = 'B6500';

UPDATE flight SET 
    departure_time = '2025-12-14 15:00:00', 
    arrival_time = '2025-12-14 18:15:00' 
WHERE flight_number = 'B6501';

-- Europe flights
UPDATE flight SET 
    departure_time = '2025-12-10 07:00:00', 
    arrival_time = '2025-12-10 09:15:00' 
WHERE flight_number = 'BA100';

UPDATE flight SET 
    departure_time = '2025-12-10 10:30:00', 
    arrival_time = '2025-12-10 11:45:00' 
WHERE flight_number = 'BA101';

UPDATE flight SET 
    departure_time = '2025-12-11 08:15:00', 
    arrival_time = '2025-12-11 10:45:00' 
WHERE flight_number = 'LH200';

UPDATE flight SET 
    departure_time = '2025-12-11 12:00:00', 
    arrival_time = '2025-12-11 14:30:00' 
WHERE flight_number = 'LH201';

UPDATE flight SET 
    departure_time = '2025-12-12 09:30:00', 
    arrival_time = '2025-12-12 11:45:00' 
WHERE flight_number = 'AF300';

UPDATE flight SET 
    departure_time = '2025-12-12 13:00:00', 
    arrival_time = '2025-12-12 15:15:00' 
WHERE flight_number = 'AF301';

UPDATE flight SET 
    departure_time = '2025-12-13 10:45:00', 
    arrival_time = '2025-12-13 11:30:00' 
WHERE flight_number = 'IB400';

UPDATE flight SET 
    departure_time = '2025-12-13 12:45:00', 
    arrival_time = '2025-12-13 13:30:00' 
WHERE flight_number = 'IB401';

UPDATE flight SET 
    departure_time = '2025-12-14 11:15:00', 
    arrival_time = '2025-12-14 14:30:00' 
WHERE flight_number = 'AZ500';

UPDATE flight SET 
    departure_time = '2025-12-14 16:00:00', 
    arrival_time = '2025-12-14 17:15:00' 
WHERE flight_number = 'AZ501';

-- Asia flights
UPDATE flight SET 
    departure_time = '2025-12-15 09:00:00', 
    arrival_time = '2025-12-15 14:15:00' 
WHERE flight_number = 'CX100';

UPDATE flight SET 
    departure_time = '2025-12-15 16:30:00', 
    arrival_time = '2025-12-15 20:45:00' 
WHERE flight_number = 'CX101';

UPDATE flight SET 
    departure_time = '2025-12-16 08:45:00', 
    arrival_time = '2025-12-16 10:15:00' 
WHERE flight_number = 'SQ200';

UPDATE flight SET 
    departure_time = '2025-12-16 12:00:00', 
    arrival_time = '2025-12-16 15:30:00' 
WHERE flight_number = 'SQ201';

UPDATE flight SET 
    departure_time = '2025-12-17 10:30:00', 
    arrival_time = '2025-12-17 13:15:00' 
WHERE flight_number = 'JL300';

UPDATE flight SET 
    departure_time = '2025-12-17 15:00:00', 
    arrival_time = '2025-12-17 17:45:00' 
WHERE flight_number = 'JL301';

UPDATE flight SET 
    departure_time = '2025-12-18 09:15:00', 
    arrival_time = '2025-12-18 11:45:00' 
WHERE flight_number = 'OZ400';

UPDATE flight SET 
    departure_time = '2025-12-18 13:30:00', 
    arrival_time = '2025-12-18 16:00:00' 
WHERE flight_number = 'OZ401';

UPDATE flight SET 
    departure_time = '2025-12-19 11:45:00', 
    arrival_time = '2025-12-19 14:30:00' 
WHERE flight_number = 'TG500';

UPDATE flight SET 
    departure_time = '2025-12-19 16:15:00', 
    arrival_time = '2025-12-19 22:00:00' 
WHERE flight_number = 'TG501';

-- Oceania flights
UPDATE flight SET 
    departure_time = '2025-12-20 08:30:00', 
    arrival_time = '2025-12-20 13:45:00' 
WHERE flight_number = 'QF100';

UPDATE flight SET 
    departure_time = '2025-12-20 15:30:00', 
    arrival_time = '2025-12-20 17:45:00' 
WHERE flight_number = 'QF101';

UPDATE flight SET 
    departure_time = '2025-12-21 09:15:00', 
    arrival_time = '2025-12-21 11:30:00' 
WHERE flight_number = 'NZ200';

UPDATE flight SET 
    departure_time = '2025-12-21 13:00:00', 
    arrival_time = '2025-12-21 18:15:00' 
WHERE flight_number = 'NZ201';

UPDATE flight SET 
    departure_time = '2025-12-22 07:45:00', 
    arrival_time = '2025-12-22 10:30:00' 
WHERE flight_number = 'VA300';

UPDATE flight SET 
    departure_time = '2025-12-22 12:15:00', 
    arrival_time = '2025-12-22 18:30:00' 
WHERE flight_number = 'VA301';

-- South America flights
UPDATE flight SET 
    departure_time = '2025-12-23 09:30:00', 
    arrival_time = '2025-12-23 12:45:00' 
WHERE flight_number = 'LA100';

UPDATE flight SET 
    departure_time = '2025-12-23 14:30:00', 
    arrival_time = '2025-12-23 17:45:00' 
WHERE flight_number = 'LA101';

UPDATE flight SET 
    departure_time = '2025-12-24 10:15:00', 
    arrival_time = '2025-12-24 13:30:00' 
WHERE flight_number = 'AR200';

UPDATE flight SET 
    departure_time = '2025-12-24 15:00:00', 
    arrival_time = '2025-12-24 18:15:00' 
WHERE flight_number = 'AR201';

UPDATE flight SET 
    departure_time = '2025-12-25 08:45:00', 
    arrival_time = '2025-12-25 10:15:00' 
WHERE flight_number = 'AV300';

UPDATE flight SET 
    departure_time = '2025-12-25 11:45:00', 
    arrival_time = '2025-12-25 13:15:00' 
WHERE flight_number = 'AV301';

-- Middle East flights
UPDATE flight SET 
    departure_time = '2025-12-26 08:00:00', 
    arrival_time = '2025-12-26 08:45:00' 
WHERE flight_number = 'EK100';

UPDATE flight SET 
    departure_time = '2025-12-26 10:15:00', 
    arrival_time = '2025-12-26 11:00:00' 
WHERE flight_number = 'EK101';

UPDATE flight SET 
    departure_time = '2025-12-27 09:30:00', 
    arrival_time = '2025-12-27 12:45:00' 
WHERE flight_number = 'QR200';

UPDATE flight SET 
    departure_time = '2025-12-27 14:15:00', 
    arrival_time = '2025-12-27 17:30:00' 
WHERE flight_number = 'QR201';

UPDATE flight SET 
    departure_time = '2025-12-28 10:45:00', 
    arrival_time = '2025-12-28 12:30:00' 
WHERE flight_number = 'EY300';

UPDATE flight SET 
    departure_time = '2025-12-28 14:00:00', 
    arrival_time = '2025-12-28 15:45:00' 
WHERE flight_number = 'EY301';
