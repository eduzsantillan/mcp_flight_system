package eduzs.dev.flightmcpclient.routing;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.util.Assert;

import java.util.Map;

public record RoutingWorkflow(ChatClient chatClient) {
    private static final Logger logger = LogManager.getLogger(RoutingWorkflow.class);
    private static final String bookingSupport = "booking_support";
    private static final String flightBrowserSupport = "flight_support";
    private static final String passengerSupport = "passenger_support";
    private static final Map<String, String> supportRoutes = Map.of(bookingSupport,
            """
                    You are a booking support specialist. Follow these guidelines:
                    1. The input to request a booking is flightNumber, passengerId and seatNumber.
                    2. You will request this information each time the user requests a booking
                    3. Do not book the flight until you get the required information the only no required information is seatNumber. 
                    4. If you dont get the seatNumber, generate a random seatNumber with this format {number}{letter} 
                    5  Number goes from 1 to 20 and letter goes from A to F
                    6. Be aware of context memory so you may already have fligthNumber, passengerId and seatNumber
                    7. Use tool getPassengers to get all available passengers and map them to ids
                    8. Use tool getDestinations to get all available destinations and map them to codes
                    9. Use tool searchFlights to get all available flights
                    10.Before calling the tool bookFlight, make sure you have request a confirmation from the user 
                       showing the flightNumber, passengerId and name and seatNumber (selected or automatically generated)
                    """,

            flightBrowserSupport,
            """
                    You are a flight browsing specialist. Follow these guidelines:
                    1. User may ask to search a flight sending the origin and destination descriptions
                    2. Use tool getDestinations to get all available destinations and map them to codes
                    3  Then use tool searchFlights to get all available flights
                    3. return the flight number and details from the flights you found
                    
                    Input: """,

            passengerSupport,
            """
                    You are a passenger admin. Follow these guidelines:
                    1. User may ask to search a passenger sending the name or email
                    2. Use tool getPassengers to get all available passengers and map them to ids
                    3. Your response should the passenger information
                    
                    Input: """);

    public String route(String input, String context) {
        Assert.notNull(input, "Input text cannot be null");
        Assert.notEmpty(supportRoutes, "Routes map cannot be null or empty");

        String routeKey = determineRoute(input, supportRoutes.keySet(), context);
        String selectedPrompt = supportRoutes.get(routeKey);

        if (selectedPrompt == null) {
            throw new IllegalArgumentException("Selected route '" + routeKey + "' not found in routes map");
        }
        return chatClient.prompt(selectedPrompt + "\nInput: " + input + "\nContext memory: " + context).call().content();
    }

    private String determineRoute(String input, Iterable<String> availableRoutes, String context) {
        logger.info("Available routes: {}", availableRoutes);

        String selectorPrompt = String.format("""
                Analyze the input and select the most appropriate support agent from these options: %s and 
                consider the context memory : %s.
                
                Response format:
                \\{
                    "support_agent": "The chosen support agent",
                    "reasoning": "The reasoning behind the selection"
                \\}
                
                Input: %s""", availableRoutes, input, context);

        RoutingResponse routingResponse = chatClient.prompt(selectorPrompt).call().entity(RoutingResponse.class);

        assert routingResponse != null;
        logger.info("Routing Analysis: {}\nSelected support agent: {}", 
                routingResponse.reasoning(), routingResponse.supportAgent());

        return routingResponse.supportAgent();
    }
}
