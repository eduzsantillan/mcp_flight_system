package eduzs.dev.flightmcpserver.config;

import eduzs.dev.flightmcpserver.tool.FlightTools;
import org.springframework.ai.support.ToolCallbacks;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class McpConfig {

    @Bean
    public ToolCallbackProvider myTools(final FlightTools myTools) {
        var tools = ToolCallbacks.from(myTools);
        return ToolCallbackProvider.from(tools);
    }
}
