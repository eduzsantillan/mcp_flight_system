package eduzs.dev.flightapp.rest.model;

public record BookingApiRequest(
        String flightNumber,
        Long passengerId,
        String seatNumber
) {
}
