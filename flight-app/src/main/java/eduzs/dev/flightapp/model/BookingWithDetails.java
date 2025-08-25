package eduzs.dev.flightapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BookingWithDetails(
    Long id,
    String bookingReference,
    String flightNumber,
    Long passengerId,
    String passengerName,
    LocalDateTime bookingTime,
    String seatNumber,
    BigDecimal price,
    String status,
    String originCity,
    String destinationCity,
    String airline,
    LocalDateTime departureDate,
    LocalDateTime arrivalDate
) {
    public static BookingWithDetails fromBooking(
            Booking booking, 
            String passengerName,
            String originCity,
            String destinationCity,
            String airline,
            LocalDateTime departureDate,
            LocalDateTime arrivalDate) {
        
        return new BookingWithDetails(
            booking.id(),
            booking.bookingReference(),
            booking.flightNumber(),
            booking.passengerId(),
            passengerName,
            booking.bookingTime(),
            booking.seatNumber(),
            booking.price(),
            booking.status(),
            originCity,
            destinationCity,
            airline,
            departureDate,
            arrivalDate
        );
    }
}
