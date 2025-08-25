package eduzs.dev.flightapp.model;

public record City(
    String code,
    String name,
    String countryCode,
    String description,
    String timezone
) {
}
