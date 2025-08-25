package eduzs.dev.flightapp.service;

import eduzs.dev.flightapp.model.*;
import eduzs.dev.flightapp.repository.FlightRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookingService {

    private final FlightRepository flightRepository;

    public BookingService(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    public Booking bookFlight(String flightNumber, Long passengerId, String seatNumber) {
        return flightRepository.bookFlight(flightNumber, passengerId, seatNumber);
    }

    public List<Booking> getPassengerBookings(Long passengerId) {
        return flightRepository.getPassengerBookings(passengerId);
    }

    public List<Booking> getAllBookings() {
        return flightRepository.getAllBookings();
    }

    public List<Booking> getFlightBookings(String flightNumber) {
        return flightRepository.getFlightBookings(flightNumber);
    }

    public Optional<Booking> getBooking(String bookingReference) {
        return flightRepository.getBooking(bookingReference);
    }

    public void cancelBooking(String bookingReference) {
        flightRepository.cancelBooking(bookingReference);
    }
    
    public List<BookingWithDetails> getAllBookingsWithDetails(PassengerService passengerService, FlightService flightService) {
        List<Booking> bookings = getAllBookings();
        return bookings.stream()
                .map(booking -> getBookingDetails(booking, passengerService, flightService))
                .collect(Collectors.toList());
    }
    
    public List<BookingWithDetails> getPassengerBookingsWithDetails(Long passengerId, PassengerService passengerService, FlightService flightService) {
        List<Booking> bookings = getPassengerBookings(passengerId);
        return bookings.stream()
                .map(booking -> getBookingDetails(booking, passengerService, flightService))
                .collect(Collectors.toList());
    }
    
    private BookingWithDetails getBookingDetails(Booking booking, PassengerService passengerService, FlightService flightService) {
        String passengerName = passengerService.findPassengerById(booking.passengerId())
                .map(passenger -> passenger.firstName() + " " + passenger.lastName())
                .orElse("Unknown Passenger");

        Optional<Flight> flightOpt = flightService.findFlight(booking.flightNumber());
        
        String originCity = flightService.getOriginCity(booking.flightNumber())
                .map(City::name)
                .orElse("Unknown Origin");
                
        String destinationCity = flightService.getDestinationCity(booking.flightNumber())
                .map(City::name)
                .orElse("Unknown Destination");
        
        String airline = flightOpt.map(Flight::airline).orElse("Unknown Airline");
        
        return BookingWithDetails.fromBooking(
            booking,
            passengerName,
            originCity,
            destinationCity,
            airline,
            flightOpt.map(Flight::departureTime).orElse(null),
            flightOpt.map(Flight::arrivalTime).orElse(null)
        );
    }
}
