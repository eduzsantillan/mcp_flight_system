package eduzs.dev.flightmcpclient.routing;

import com.fasterxml.jackson.annotation.JsonProperty;

public record RoutingResponse(
        @JsonProperty("support_agent")
        String supportAgent,
        String reasoning
) {

}
