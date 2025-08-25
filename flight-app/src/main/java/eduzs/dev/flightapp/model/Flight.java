package eduzs.dev.flightapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Flight(
    String flightNumber,
    String airline,
    String origin,
    String destination,
    LocalDateTime departureTime,
    LocalDateTime arrivalTime,
    BigDecimal price,
    int availableSeats,
    String status
) {
}
