package eduzs.dev.flightmcpserver.tool;

import com.fasterxml.jackson.databind.JsonNode;
import eduzs.dev.flightmcpserver.rest.BookingClient;
import eduzs.dev.flightmcpserver.rest.DestinationsClient;
import eduzs.dev.flightmcpserver.rest.FlightClient;
import eduzs.dev.flightmcpserver.rest.PassengerClient;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.stereotype.Component;

@Component
public class FlightTools {
    private final FlightClient flightClient;
    private final DestinationsClient destinationsClient;
    private final PassengerClient passengerClient;
    private final BookingClient bookingClient;

    public FlightTools(FlightClient flightClient, DestinationsClient destinationsClient, PassengerClient passengerClient, BookingClient bookingClient) {
        this.flightClient = flightClient;
        this.destinationsClient = destinationsClient;
        this.passengerClient = passengerClient;
        this.bookingClient = bookingClient;
    }

    @Tool(description = "Search for flights information based on origin and destination")
    public JsonNode searchFlights(String origin, String destination) {
        return flightClient.searchFlights(origin, destination);
    }

    @Tool(description = "Get all available destinations and from that map the origin and destination codes to use in searchFlights")
    public JsonNode getDestinations() {
        return destinationsClient.getDestinations();
    }

    @Tool(description = "Get all available passengers and from that map the passenger id to use in bookFlight")
    public JsonNode getPassengers() {
        return passengerClient.getPassengers();
    }

    @Tool(description = "Book a flight for a passenger")
    public void bookFlight(String flightNumber, Long passenger, String seatNumber) {
        bookingClient.bookFlight(flightNumber, passenger, seatNumber);
    }
}
