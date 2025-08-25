package eduzs.dev.flightmcpserver.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class PassengerClient {

    private final RestClient apiClient;
    private final Logger logger = LoggerFactory.getLogger(PassengerClient.class);

    public PassengerClient(RestClient restClient) {
        this.apiClient = restClient;
    }

    public JsonNode getPassengers() {
        logger.info("Getting all available passengers");
        var passengers = apiClient.get()
                .uri("/api/passengers")
                .retrieve()
                .body(JsonNode.class);
        logger.info("Available passengers: {}", passengers);
        return passengers;
    }
}
