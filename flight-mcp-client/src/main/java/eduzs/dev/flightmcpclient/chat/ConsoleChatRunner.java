package eduzs.dev.flightmcpclient.chat;


import eduzs.dev.flightmcpclient.routing.RoutingWorkflow;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Component
public class ConsoleChatRunner implements CommandLineRunner {

    private final ChatClient chatClient;

    public ConsoleChatRunner(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        this.chatClient = chatClientBuilder
                .defaultToolCallbacks(tools)
                .build();

    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Type 'exit' to quit.");
        String context = "";
        while (true) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            if (line.trim().equalsIgnoreCase("exit")) break;
            var routerWorkflow = new RoutingWorkflow(chatClient);
            String resp = routerWorkflow.route(line, context);
            context = resp;
            System.out.println(resp);
        }
    }
}
