Java AI Chatbot

A simple AI chatbot built using Core Java that connects to the OpenRouter API to generate AI-powered responses. Users can interact with the chatbot directly through the terminal by entering questions and receiving responses from GPT-4o.

About the Project

I built this project to explore how Java applications communicate with external AI services through REST APIs. The application accepts user input from the console, sends it to the OpenRouter API using an HTTP POST request, and displays the AI-generated response. I used separate Java classes to handle user interaction and API communication, keeping the application simple and organized.

Features

Interactive console-based chatbot.
AI-powered responses using GPT-4o.
Integration with the OpenRouter API.
HTTP POST requests using HttpURLConnection.
JSON request creation and response handling.
Secure API key configuration using environment variables.
Continuous conversation through a command-line interface.
Basic exception and API error handling.

Tech Stack              

Technology                       Usage

Java (JDK 21)                   Application development          
OpenRouter API                  AI integration                   
GPT-4o                          AI response generation
HttpURLConnection               HTTP communication           
JSON                            API request and response format   
Git & GitHub                    Version control
VS Code                         Development environment

Project Structure

AIChatBot/
│
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   ├── Main.java
│                   ├── ChatBot.java
│                   └── AIService.java
│
├── .gitignore
└── README.md

How It Works
The application follows a simple three-class structure.

1. Main.java

Entry point of the application.
Accepts questions using Java Scanner.
Maintains the input loop until the user enters exit.

2. ChatBot.java

Handles chatbot operations.
Passes user messages to the AI service.
Displays the generated responses.

3. AIService.java

Connects to the OpenRouter API.
Creates JSON request bodies.
Sends HTTP POST requests using HttpURLConnection.
Reads the API response and extracts the AI-generated content.
Handles API errors and exceptions.

Application Flow

       User
        |
        v
     Main.java
        |
        v
    ChatBot.java
        |
        v
    AIService.java
        |
        v
  OpenRouter REST API
        |
        v
      GPT-4o
        |
        v
   AI-generated response
        |
        v
    Console Output

Getting Started
Prerequisites
Before running this project, make sure you have:
Java JDK 21 or later.
VS Code or another Java IDE.
An active internet connection.
An OpenRouter account and API key.

1. Clone the Repository
git clone https://github.com/SwathyMadhavan/java-ai-chatbot.git

2. Navigate to the Project
cd java-ai-chatbot

3. Configure the API Key
Create an API key from:
https://openrouter.ai/keys

The application reads the API key from an environment variable.

Windows PowerShell:
$env:OPENROUTER_API_KEY="YOUR_API_KEY"
Replace YOUR_API_KEY with your actual OpenRouter API key.
Important: Never hardcode your API key in Java source code or upload it to GitHub.

4. Compile the Application
Run the following command from the project root directory:
javac -d out src/main/java/org/example/*.java

5. Run the Application
java -cp out org.example.Main

Sample Output

=================================
    AI CHATBOT STARTED
=================================

You: What is Java?

Bot: Java is a high-level, object-oriented
programming language used for developing
web applications, enterprise software,
and many other applications.

You: exit
Goodbye!

The actual response will vary depending on the question and AI model output.

API Details
This project uses the OpenRouter Chat Completions API.

API Endpoint:
https://openrouter.ai/api/v1/chat/completions

Model:
openai/gpt-4o
The configured model must be available through your OpenRouter account.

What I Learned
While building this project, I worked with:
Java classes, objects, and methods.
Object-oriented programming principles.
HTTP communication using HttpURLConnection.
REST API integration.
JSON request and response handling.
API authentication using environment variables.
Java exception handling.
Git and GitHub for version control.

Future Improvements
Add conversation history so the chatbot can remember previous messages.
Use Jackson for structured JSON processing.
Convert the console application into a Spring Boot REST API.
Integrate MySQL to store chat history.
Build a web interface using HTML, CSS, and JavaScript.

Author
Swathy Madhavan

GitHub:
Project: Java AI Chatbot
Built as a hands-on project to explore Java backend development and AI API integration.
