
package org.example;

public class ChatBot {

    AIService service = new AIService();

    public void chat(String message) {

        String response = service.getResponse(message);

        System.out.println("Bot: " + response);

    }

}
