package eduzs.dev.flightapp.repository;

import eduzs.dev.flightapp.model.*;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Repository
public class FlightRepository {

    private final JdbcClient jdbcClient;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    public FlightRepository(JdbcClient jdbcClient, CityRepository cityRepository, CountryRepository countryRepository) {
        this.jdbcClient = jdbcClient;
        this.cityRepository = cityRepository;
        this.countryRepository = countryRepository;
    }

    public Optional<Flight> findFlight(String flightNumber) {
        return jdbcClient.sql("SELECT * FROM flight WHERE flight_number = ?")
                .param(flightNumber)
                .query(Flight.class)
                .optional();
    }

    public List<Flight> searchFlights(String origin, String destination, LocalDateTime departureDate) {
        return jdbcClient.sql("SELECT * FROM flight WHERE origin = ? AND destination = ? " +
                "AND DATE(departure_time) = DATE(?) AND available_seats > 0")
                .param(origin)
                .param(destination)
                .param(departureDate)
                .query(Flight.class)
                .list();
    }

    public List<Flight> searchFlightsWithoutDate(String origin, String destination) {
        return jdbcClient.sql("SELECT * FROM flight WHERE origin = ? AND destination = ? " +
                "AND departure_time >= CURRENT_TIMESTAMP AND available_seats > 0 " +
                "ORDER BY departure_time")
                .param(origin)
                .param(destination)
                .query(Flight.class)
                .list();
    }

    public List<Flight> getAllFlights() {
        return jdbcClient.sql("SELECT * FROM flight ORDER BY departure_time")
                .query(Flight.class)
                .list();
    }

    public Optional<FlightWithDetails> findFlightWithDetails(String flightNumber) {
        Optional<Flight> flightOpt = findFlight(flightNumber);
        
        if (flightOpt.isEmpty()) {
            return Optional.empty();
        }
        
        Flight flight = flightOpt.get();
        Optional<City> originCity = getOriginCity(flightNumber);
        Optional<Country> originCountry = getOriginCountry(flightNumber);
        Optional<City> destinationCity = getDestinationCity(flightNumber);
        Optional<Country> destinationCountry = getDestinationCountry(flightNumber);
        
        return Optional.of(FlightWithDetails.fromFlight(
            flight,
            originCity.orElse(null),
            originCountry.orElse(null),
            destinationCity.orElse(null),
            destinationCountry.orElse(null)
        ));
    }
    
    public List<FlightWithDetails> searchFlightsWithDetails(String originCode, String destinationCode, LocalDateTime departureDate) {
        List<Flight> flights = searchFlights(originCode, destinationCode, departureDate);
        
        return flights.stream()
            .map(flight -> {
                Optional<City> originCity = cityRepository.findCityByCode(flight.origin());
                Optional<Country> originCountry = originCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                Optional<City> destinationCity = cityRepository.findCityByCode(flight.destination());
                Optional<Country> destinationCountry = destinationCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                return FlightWithDetails.fromFlight(
                    flight,
                    originCity.orElse(null),
                    originCountry.orElse(null),
                    destinationCity.orElse(null),
                    destinationCountry.orElse(null)
                );
            })
            .collect(Collectors.toList());
    }
    
    public List<FlightWithDetails> searchFlightsWithDetailsWithoutDate(String originCode, String destinationCode) {
        List<Flight> flights = searchFlightsWithoutDate(originCode, destinationCode);
        
        return flights.stream()
            .map(flight -> {
                Optional<City> originCity = cityRepository.findCityByCode(flight.origin());
                Optional<Country> originCountry = originCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                Optional<City> destinationCity = cityRepository.findCityByCode(flight.destination());
                Optional<Country> destinationCountry = destinationCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                return FlightWithDetails.fromFlight(
                    flight,
                    originCity.orElse(null),
                    originCountry.orElse(null),
                    destinationCity.orElse(null),
                    destinationCountry.orElse(null)
                );
            })
            .collect(Collectors.toList());
    }

    public List<FlightWithDetails> getAllFlightsWithDetails() {
        List<Flight> flights = getAllFlights();
        
        return flights.stream()
            .map(flight -> {
                Optional<City> originCity = cityRepository.findCityByCode(flight.origin());
                Optional<Country> originCountry = originCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                Optional<City> destinationCity = cityRepository.findCityByCode(flight.destination());
                Optional<Country> destinationCountry = destinationCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
                
                return FlightWithDetails.fromFlight(
                    flight,
                    originCity.orElse(null),
                    originCountry.orElse(null),
                    destinationCity.orElse(null),
                    destinationCountry.orElse(null)
                );
            })
            .collect(Collectors.toList());
    }

    public Booking bookFlight(String flightNumber, Long passengerId, String seatNumber) {
        Optional<Flight> flight = findFlight(flightNumber);
        
        if (flight.isEmpty() || flight.get().availableSeats() <= 0) {
            throw new RuntimeException("Flight not available or no seats available");
        }
        
        if (isSeatBooked(flightNumber, seatNumber)) {
            throw new RuntimeException("Seat already booked");
        }
        
        String bookingReference = generateBookingReference();
        
        Booking booking = new Booking(
            null,
            bookingReference,
            flightNumber,
            passengerId,
            LocalDateTime.now(),
            seatNumber,
            flight.get().price(),
            "CONFIRMED"
        );
        
        jdbcClient.sql("INSERT INTO booking (booking_reference, flight_number, passenger_id, booking_time, " +
                "seat_number, price, status) VALUES (?, ?, ?, ?, ?, ?, ?)")
                .param(booking.bookingReference())
                .param(booking.flightNumber())
                .param(booking.passengerId())
                .param(booking.bookingTime())
                .param(booking.seatNumber())
                .param(booking.price())
                .param(booking.status())
                .update();
        
        updateFlightAvailableSeats(flightNumber, -1);
        
        return booking;
    }
    
    private void updateFlightAvailableSeats(String flightNumber, int seatsDelta) {
        jdbcClient.sql("UPDATE flight SET available_seats = available_seats + ? " +
                "WHERE flight_number = ?")
                .param(seatsDelta)
                .param(flightNumber)
                .update();
    }
    
    private String generateBookingReference() {
        return "BK" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
    
    public List<Booking> getPassengerBookings(Long passengerId) {
        return jdbcClient.sql("SELECT * FROM booking WHERE passenger_id = ? ORDER BY booking_time DESC")
                .param(passengerId)
                .query(Booking.class)
                .list();
    }
    
    public List<Booking> getAllBookings() {
        return jdbcClient.sql("SELECT * FROM booking ORDER BY booking_time DESC")
                .query(Booking.class)
                .list();
    }
    
    public List<Booking> getFlightBookings(String flightNumber) {
        return jdbcClient.sql("SELECT * FROM booking WHERE flight_number = ?")
                .param(flightNumber)
                .query(Booking.class)
                .list();
    }
    
    public Optional<Booking> getBooking(String bookingReference) {
        return jdbcClient.sql("SELECT * FROM booking WHERE booking_reference = ?")
                .param(bookingReference)
                .query(Booking.class)
                .optional();
    }
    
    public boolean isSeatBooked(String flightNumber, String seatNumber) {
        return jdbcClient.sql("SELECT COUNT(*) FROM booking WHERE flight_number = ? AND seat_number = ? AND status = 'CONFIRMED'")
                .param(flightNumber)
                .param(seatNumber)
                .query(Integer.class)
                .single() > 0;
    }
    
    public void cancelBooking(String bookingReference) {
        Optional<Booking> bookingOpt = getBooking(bookingReference);
        
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        
        Booking booking = bookingOpt.get();
        
        if ("CANCELLED".equals(booking.status())) {
            throw new RuntimeException("Booking already cancelled");
        }
        
        jdbcClient.sql("UPDATE booking SET status = 'CANCELLED' WHERE booking_reference = ?")
                .param(bookingReference)
                .update();
        
        updateFlightAvailableSeats(booking.flightNumber(), 1);
    }
    
    public Optional<City> getOriginCity(String flightNumber) {
        return cityRepository.getFlightOriginCity(flightNumber);
    }
    
    public Optional<City> getDestinationCity(String flightNumber) {
        return cityRepository.getFlightDestinationCity(flightNumber);
    }
    
    public Optional<Country> getOriginCountry(String flightNumber) {
        Optional<City> originCity = getOriginCity(flightNumber);
        return originCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
    }
    
    public Optional<Country> getDestinationCountry(String flightNumber) {
        Optional<City> destinationCity = getDestinationCity(flightNumber);
        return destinationCity.flatMap(city -> countryRepository.getCountryByCity(city.code()));
    }
}
