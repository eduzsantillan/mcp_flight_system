package eduzs.dev.flightapp.model;

public record Country(
    String code,
    String name,
    String description,
    String continent,
    String currency,
    String language
) {
}
