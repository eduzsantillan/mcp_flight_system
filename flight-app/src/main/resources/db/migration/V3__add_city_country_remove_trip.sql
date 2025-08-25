-- Create country table
CREATE TABLE country (
    code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    continent VARCHAR(50),
    currency VARCHAR(50),
    language VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create city table
CREATE TABLE city (
    code VARCHAR(3) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    country_code VARCHAR(3) NOT NULL,
    description TEXT,
    timezone VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (country_code) REFERENCES country(code)
);

-- Insert some initial country data
INSERT INTO country (code, name, description, continent, currency, language) VALUES
('USA', 'United States', 'The United States of America is a country primarily located in North America.', 'North America', 'US Dollar (USD)', 'English'),
('MEX', 'Mexico', 'Mexico is a country in the southern portion of North America.', 'North America', 'Mexican Peso (MXN)', 'Spanish'),
('CAN', 'Canada', 'Canada is a country in North America, the world''s second-largest country by total area.', 'North America', 'Canadian Dollar (CAD)', 'English, French'),
('BRA', 'Brazil', 'Brazil is the largest country in South America and in Latin America.', 'South America', 'Brazilian Real (BRL)', 'Portuguese'),
('ARG', 'Argentina', 'Argentina is a country in the southern half of South America.', 'South America', 'Argentine Peso (ARS)', 'Spanish'),
('ESP', 'Spain', 'Spain is a country in Southwestern Europe with some pockets of territory in the Mediterranean Sea.', 'Europe', 'Euro (EUR)', 'Spanish'),
('FRA', 'France', 'France is a country primarily located in Western Europe.', 'Europe', 'Euro (EUR)', 'French'),
('DEU', 'Germany', 'Germany is a country in Central Europe.', 'Europe', 'Euro (EUR)', 'German'),
('GBR', 'United Kingdom', 'The United Kingdom is a sovereign country in north-western Europe.', 'Europe', 'Pound Sterling (GBP)', 'English'),
('ITA', 'Italy', 'Italy is a country consisting of a peninsula delimited by the Alps and several islands.', 'Europe', 'Euro (EUR)', 'Italian'),
('JPN', 'Japan', 'Japan is an island country in East Asia.', 'Asia', 'Japanese Yen (JPY)', 'Japanese'),
('CHN', 'China', 'China is a country in East Asia.', 'Asia', 'Renminbi (CNY)', 'Mandarin'),
('IND', 'India', 'India is a country in South Asia.', 'Asia', 'Indian Rupee (INR)', 'Hindi, English'),
('AUS', 'Australia', 'Australia is a sovereign country comprising the mainland of the Australian continent.', 'Oceania', 'Australian Dollar (AUD)', 'English'),
('NZL', 'New Zealand', 'New Zealand is an island country in the southwestern Pacific Ocean.', 'Oceania', 'New Zealand Dollar (NZD)', 'English, Māori'),
('PER', 'Peru', 'Peru is a country in western South America.', 'South America', 'Peruvian Sol (PEN)', 'Spanish'),
('CHL', 'Chile', 'Chile is a country in western South America.', 'South America', 'Chilean Peso (CLP)', 'Spanish'),
('COL', 'Colombia', 'Colombia is a country in the northern part of South America.', 'South America', 'Colombian Peso (COP)', 'Spanish'),
('ECU', 'Ecuador', 'Ecuador is a country in northwestern South America.', 'South America', 'United States Dollar (USD)', 'Spanish'),
('THA', 'Thailand', 'Thailand is a country in Southeast Asia.', 'Asia', 'Thai Baht (THB)', 'Thai'),
('SGP', 'Singapore', 'Singapore is a sovereign island city-state in Southeast Asia.', 'Asia', 'Singapore Dollar (SGD)', 'English, Malay, Mandarin, Tamil'),
('KOR', 'South Korea', 'South Korea is a country in East Asia.', 'Asia', 'South Korean Won (KRW)', 'Korean'),
('HKG', 'Hong Kong', 'Hong Kong is a special administrative region of China.', 'Asia', 'Hong Kong Dollar (HKD)', 'Cantonese'),
('ARE', 'United Arab Emirates', 'The United Arab Emirates is a country in Western Asia.', 'Asia', 'UAE Dirham (AED)', 'Arabic'),
('QAT', 'Qatar', 'Qatar is a country in Western Asia.', 'Asia', 'Qatari Riyal (QAR)', 'Arabic'),
('EGY', 'Egypt', 'Egypt is a transcontinental country spanning the northeast corner of Africa and southwest corner of Asia.', 'Africa', 'Egyptian Pound (EGP)', 'Arabic'),
('JOR', 'Jordan', 'Jordan is a country in Western Asia.', 'Asia', 'Jordanian Dinar (JOD)', 'Arabic'),
('GRC', 'Greece', 'Greece is a country in Southeast Europe.', 'Europe', 'Euro (EUR)', 'Greek'),
('PRT', 'Portugal', 'Portugal is a country in Southwestern Europe.', 'Europe', 'Euro (EUR)', 'Portuguese');

-- Insert all city data referenced in flight data
INSERT INTO city (code, name, country_code, description, timezone) VALUES
-- North America
('LAX', 'Los Angeles', 'USA', 'Los Angeles is the largest city in California.', 'America/Los_Angeles'),
('JFK', 'New York', 'USA', 'New York City is the most populous city in the United States.', 'America/New_York'),
('ATL', 'Atlanta', 'USA', 'Atlanta is the capital and most populous city of Georgia.', 'America/New_York'),
('SEA', 'Seattle', 'USA', 'Seattle is a seaport city on the West Coast of the United States.', 'America/Los_Angeles'),
('ORD', 'Chicago', 'USA', 'Chicago is the third most populous city in the United States.', 'America/Chicago'),
('SFO', 'San Francisco', 'USA', 'San Francisco is a city in Northern California.', 'America/Los_Angeles'),
('DEN', 'Denver', 'USA', 'Denver is the capital and most populous city of Colorado.', 'America/Denver'),
('LAS', 'Las Vegas', 'USA', 'Las Vegas is a city in Nevada known for its casinos and entertainment.', 'America/Los_Angeles'),
('BOS', 'Boston', 'USA', 'Boston is the capital and most populous city of Massachusetts.', 'America/New_York'),
('MCO', 'Orlando', 'USA', 'Orlando is a city in central Florida.', 'America/New_York'),
('MEX', 'Mexico City', 'MEX', 'Mexico City is the capital and largest city of Mexico.', 'America/Mexico_City'),

-- Europe
('LHR', 'London', 'GBR', 'London is the capital and largest city of England and the United Kingdom.', 'Europe/London'),
('CDG', 'Paris', 'FRA', 'Paris is the capital and most populous city of France.', 'Europe/Paris'),
('FRA', 'Frankfurt', 'DEU', 'Frankfurt is the largest city in the German state of Hesse.', 'Europe/Berlin'),
('MAD', 'Madrid', 'ESP', 'Madrid is the capital and most populous city of Spain.', 'Europe/Madrid'),
('FCO', 'Rome', 'ITA', 'Rome is the capital city of Italy.', 'Europe/Rome'),
('LIS', 'Lisbon', 'PRT', 'Lisbon is the capital and largest city of Portugal.', 'Europe/Lisbon'),
('ATH', 'Athens', 'GRC', 'Athens is the capital and largest city of Greece.', 'Europe/Athens'),

-- Asia
('HKG', 'Hong Kong', 'HKG', 'Hong Kong is a major global financial center and city.', 'Asia/Hong_Kong'),
('NRT', 'Tokyo', 'JPN', 'Tokyo is the capital and most populous city of Japan.', 'Asia/Tokyo'),
('SIN', 'Singapore', 'SGP', 'Singapore is a sovereign island city-state in Southeast Asia.', 'Asia/Singapore'),
('BKK', 'Bangkok', 'THA', 'Bangkok is the capital and most populous city of Thailand.', 'Asia/Bangkok'),
('ICN', 'Seoul', 'KOR', 'Seoul is the capital and largest city of South Korea.', 'Asia/Seoul'),
('PVG', 'Shanghai', 'CHN', 'Shanghai is one of the major cities in China.', 'Asia/Shanghai'),
('DEL', 'New Delhi', 'IND', 'New Delhi is the capital of India.', 'Asia/Kolkata'),

-- Oceania
('SYD', 'Sydney', 'AUS', 'Sydney is the capital city of the state of New South Wales, and the most populous city in Australia.', 'Australia/Sydney'),
('AKL', 'Auckland', 'NZL', 'Auckland is the largest urban area in New Zealand.', 'Pacific/Auckland'),
('MEL', 'Melbourne', 'AUS', 'Melbourne is the capital and most populous city of the Australian state of Victoria.', 'Australia/Melbourne'),
('BNE', 'Brisbane', 'AUS', 'Brisbane is the capital and most populous city of the Australian state of Queensland.', 'Australia/Brisbane'),
('PER', 'Perth', 'AUS', 'Perth is the capital and largest city of the Australian state of Western Australia.', 'Australia/Perth'),

-- South America
('GRU', 'Sao Paulo', 'BRA', 'São Paulo is the most populous city in Brazil.', 'America/Sao_Paulo'),
('SCL', 'Santiago', 'CHL', 'Santiago is the capital and largest city of Chile.', 'America/Santiago'),
('EZE', 'Buenos Aires', 'ARG', 'Buenos Aires is the capital and largest city of Argentina.', 'America/Argentina/Buenos_Aires'),
('LIM', 'Lima', 'PER', 'Lima is the capital and the largest city of Peru.', 'America/Lima'),
('BOG', 'Bogota', 'COL', 'Bogotá is the capital and largest city of Colombia.', 'America/Bogota'),
('UIO', 'Quito', 'ECU', 'Quito is the capital and largest city of Ecuador.', 'America/Guayaquil'),

-- Middle East
('DXB', 'Dubai', 'ARE', 'Dubai is the most populous city in the United Arab Emirates.', 'Asia/Dubai'),
('DOH', 'Doha', 'QAT', 'Doha is the capital and most populous city of Qatar.', 'Asia/Qatar'),
('CAI', 'Cairo', 'EGY', 'Cairo is the capital and largest city of Egypt.', 'Africa/Cairo'),
('AUH', 'Abu Dhabi', 'ARE', 'Abu Dhabi is the capital of the United Arab Emirates.', 'Asia/Dubai'),
('AMM', 'Amman', 'JOR', 'Amman is the capital and largest city of Jordan.', 'Asia/Amman');

-- First drop existing foreign key constraints if they exist
ALTER TABLE flight
DROP CONSTRAINT IF EXISTS fk_flight_origin_country;

ALTER TABLE flight
DROP CONSTRAINT IF EXISTS fk_flight_destination_country;

-- Now add the new foreign key constraints
ALTER TABLE flight
ADD CONSTRAINT fk_flight_origin_city
FOREIGN KEY (origin) REFERENCES city(code);

ALTER TABLE flight
ADD CONSTRAINT fk_flight_destination_city
FOREIGN KEY (destination) REFERENCES city(code);

-- Drop trip related tables
DROP TABLE IF EXISTS trip_booking CASCADE;
DROP TABLE IF EXISTS trip CASCADE;
