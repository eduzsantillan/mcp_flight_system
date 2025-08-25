package eduzs.dev.flightmcpserver.rest;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import static org.springframework.http.MediaType.APPLICATION_JSON;

@Component
public class BookingClient {

    private final RestClient apiClient;
    private final Logger logger = LoggerFactory.getLogger(BookingClient.class);

    public BookingClient(RestClient apiClient) {
        this.apiClient = apiClient;
    }

    public void bookFlight(final String flightNumber, final long passengerId, final String seatNumber) {
        logger.info("Booking flight {} for passenger {}", flightNumber, passengerId);
        ObjectNode request = new ObjectNode( new JsonNodeFactory(true));
        request.put("flightNumber", flightNumber);
        request.put("passengerId", passengerId);
        request.put("seatNumber", seatNumber);
        var requestBody = request.toString();

        apiClient.post()
                .uri(builder -> builder
                        .path("/api/bookings")
                        .build())
                .contentType(APPLICATION_JSON)
                .body(requestBody)
                .retrieve()
                .toBodilessEntity();
    }

}
