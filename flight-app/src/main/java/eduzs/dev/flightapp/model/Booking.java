package eduzs.dev.flightapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Booking(
    Long id,
    String bookingReference,
    String flightNumber,
    Long passengerId,
    LocalDateTime bookingTime,
    String seatNumber,
    BigDecimal price,
    String status
) {
}
