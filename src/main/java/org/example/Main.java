
package org.example;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Creating ChatBot object
        ChatBot bot = new ChatBot();

        System.out.println("==========================");
        System.out.println("    AI CHATBOT STARTED");
        System.out.println("==========================");

        while (true) {

            System.out.print("\nYou: ");

            String input = sc.nextLine();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Goodbye!");
                break;
            }

            if (input.trim().isEmpty()) {
                continue;
            }

            // Calling ChatBot method
            bot.chat(input);
        }

        sc.close();
    }
}
