
package org.example;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class AIService {

    private static final String API_URL =
            "https://openrouter.ai/api/v1/chat/completions";

    private static final String API_KEY =
            System.getenv("OPENROUTER_API_KEY");

    public String getResponse(String userInput) {

        HttpURLConnection conn = null;

        try {

            // Step 1: Check API key
            if (API_KEY == null || API_KEY.isBlank()) {
                return "API key is missing!";
            }

            // Step 2: Create JSON request
            String jsonBody = "{"
                    + "\"model\":\"openai/gpt-4o\","
                    + "\"max_tokens\":500,"
                    + "\"messages\":["
                    + "{"
                    + "\"role\":\"user\","
                    + "\"content\":\"" + escape(userInput) + "\""
                    + "}"
                    + "]"
                    + "}";

            // Step 3: Create URL
            URL url = new URL(API_URL);

            // Step 4: Open connection
            conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");

            conn.setRequestProperty(
                    "Content-Type",
                    "application/json"
            );

            conn.setRequestProperty(
                    "Authorization",
                    "Bearer " + API_KEY
            );

            conn.setConnectTimeout(15000);
            conn.setReadTimeout(60000);

            conn.setDoOutput(true);

            // Step 5: Send request
            try (OutputStream os = conn.getOutputStream()) {

                byte[] input = jsonBody.getBytes(StandardCharsets.UTF_8);

                os.write(input);
            }

            // Step 6: Get response status
            int statusCode = conn.getResponseCode();

            // Step 7: Read response
            InputStream inputStream;

            if (statusCode >= 200 && statusCode < 300) {
                inputStream = conn.getInputStream();
            } else {
                inputStream = conn.getErrorStream();
            }

            if (inputStream == null) {
                return "No response received. HTTP status: " + statusCode;
            }

            StringBuilder response = new StringBuilder();

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            inputStream,
                            StandardCharsets.UTF_8
                    ))) {

                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
            }

            // Step 8: Handle errors
            if (statusCode < 200 || statusCode >= 300) {
                return "API Error (" + statusCode + "): " + response;
            }

            // Step 9: Return AI answer
            return parse(response.toString());

        } catch (Exception e) {

            return "Error: " + e.getMessage();

        } finally {

            if (conn != null) {
                conn.disconnect();
            }
        }
    }

    // Escape JSON special characters
    private String escape(String text) {

        return text
                .replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    // Temporary response extractor
    private String parse(String body) {

        String key = "\"content\":\"";

        int startKey = body.indexOf(key);

        if (startKey == -1) {
            return "Could not find AI response. Raw response: " + body;
        }

        int start = startKey + key.length();

        StringBuilder result = new StringBuilder();

        boolean escaped = false;

        for (int i = start; i < body.length(); i++) {

            char c = body.charAt(i);

            if (escaped) {

                switch (c) {
                    case 'n':
                        result.append('\n');
                        break;

                    case 't':
                        result.append('\t');
                        break;

                    case 'r':
                        result.append('\r');
                        break;

                    case '"':
                        result.append('"');
                        break;

                    case '\\':
                        result.append('\\');
                        break;

                    default:
                        result.append(c);
                }

                escaped = false;

            } else {

                if (c == '\\') {
                    escaped = true;
                } else if (c == '"') {
                    break;
                } else {
                    result.append(c);
                }
            }
        }

        return result.toString();
    }
}
