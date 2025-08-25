package eduzs.dev.flightmcpserver.rest;

import com.fasterxml.jackson.databind.JsonNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class DestinationsClient {

    private final RestClient apiClient;
    private final Logger logger = LoggerFactory.getLogger(DestinationsClient.class);

    public DestinationsClient(RestClient apiClient) {
        this.apiClient = apiClient;
    }

    public JsonNode getDestinations() {
        logger.info("Getting all available destinations");
        var destinations = apiClient.get()
                .uri("/api/destinations")
                .retrieve()
                .body(JsonNode.class);
        logger.info("Available destinations: {}", destinations);
        return destinations;
    }
}
