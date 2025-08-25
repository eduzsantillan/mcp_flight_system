package eduzs.dev.flightmcpserver.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class FlightClient {

    private final RestClient apiClient;
    private final Logger logger = LoggerFactory.getLogger(FlightClient.class);


    public FlightClient(RestClient apiClient) {
        this.apiClient = apiClient;
    }

    public JsonNode searchFlights(final String origin, final String destination) {
        logger.info("Searching for flights from {} to {}", origin, destination);
        return apiClient.get()
                .uri(builder -> builder
                        .path("/api/flight")
                        .queryParam("origin", origin.toUpperCase())
                        .queryParam("destination", destination.toUpperCase())
                        .build())
                .retrieve()
                .body(JsonNode.class);
    }
}
