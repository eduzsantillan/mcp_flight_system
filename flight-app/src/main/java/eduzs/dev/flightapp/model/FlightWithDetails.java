package eduzs.dev.flightapp.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record FlightWithDetails(
    String flightNumber,
    String airline,
    String originCode,
    String originCityName,
    String originCountryName,
    String destinationCode,
    String destinationCityName,
    String destinationCountryName,
    LocalDateTime departureTime,
    LocalDateTime arrivalTime,
    BigDecimal price,
    int availableSeats,
    String status
) {
    public static FlightWithDetails fromFlight(
            Flight flight, 
            City originCity, 
            Country originCountry, 
            City destinationCity, 
            Country destinationCountry) {
        
        return new FlightWithDetails(
            flight.flightNumber(),
            flight.airline(),
            flight.origin(),
            originCity != null ? originCity.name() : flight.origin(),
            originCountry != null ? originCountry.name() : "",
            flight.destination(),
            destinationCity != null ? destinationCity.name() : flight.destination(),
            destinationCountry != null ? destinationCountry.name() : "",
            flight.departureTime(),
            flight.arrivalTime(),
            flight.price(),
            flight.availableSeats(),
            flight.status()
        );
    }
}
