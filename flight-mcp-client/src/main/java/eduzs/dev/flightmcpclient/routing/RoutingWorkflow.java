package eduzs.dev.flightmcpclient.routing;

import eduzs.dev.flightmcpclient.config.PromptsConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Map;

@Component
public class RoutingWorkflow {
    private static final Logger logger = LogManager.getLogger(RoutingWorkflow.class);

    private final Map<String, String> supportRoutes;
    private final ChatClient chatClient;
    private final String routerPrompt;

    public RoutingWorkflow( ChatClient chatClient){
        this.supportRoutes = PromptsConfig.supportRoutes();
        this.chatClient = chatClient;
        this.routerPrompt = PromptsConfig.selectorPrompt();
    }

    public String route(String input, String context) {
        Assert.notNull(input, "Input text cannot be null");
        Assert.notEmpty(supportRoutes, "Routes map cannot be null or empty");

        String routeKey = determineRoute(input, supportRoutes.keySet(), context);
        String selectedPrompt = supportRoutes.get(routeKey);
        if (selectedPrompt == null) {
            return chatClient.prompt("What can you do?").call().content();
        }

        return chatClient.prompt(selectedPrompt + "\nInput: " + input + "\nContext memory: " + context).call().content();
    }

    private String determineRoute(String input, Iterable<String> availableRoutes, String context) {
        logger.info("Available routes: {}", availableRoutes);

        var formattedPrompt = String.format(routerPrompt, availableRoutes, context, input);
        RoutingResponse routingResponse = chatClient.prompt(formattedPrompt).call().entity(RoutingResponse.class);

        Assert.notNull(routingResponse, "Routing response cannot be null");
        logger.info("Routing Analysis: {}\nSelected support agent: {}", 
                routingResponse.reasoning(), routingResponse.supportAgent());

        return routingResponse.supportAgent();
    }
}
