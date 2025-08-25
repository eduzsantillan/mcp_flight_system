package eduzs.dev.flightapp.model;

public record Passenger(
    Long id,
    String firstName,
    String lastName,
    String email,
    String phoneNumber,
    String passportNumber
) {
}
