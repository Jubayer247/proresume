package com.benotx.proresume;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

public class ChatGPTApiExample {

    private static final String API_KEY = ""; // Replace with your actual OpenAI API key
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";

    public static void main(String[] args) throws Exception {
        String message = """
            Generate a resume in html from the provided information below in 

            Md Jubayer's Photo
            Md Jubayer
            IoT, Cloud, and Full-Stack Solutions
            Bangladesh

            +8801827095630

            mdjubayer247@gmail.com

            LinkedIn

            GitHub

            Experienced in IoT, embedded systems, and full-stack development, adept at generating and refining ideas, and skilled in delivering technical solutions for software and hardware platforms.

            Experience
            Full-stack Developer, Cloud Consultant, Embedded System Developer, Senior IoT Engineer
            Various Roles: Freelance and Contract
            Designed, developed, deployed, and administrated: device management, eCommerce, multimedia news portal solutions in desktop, mobile, and web apps, custom hardware, and cloud services
            Integrated biometric devices: Hysoon, Timmy, ZKTeco

            Skills
            Development Platforms: AWS, GCP, Azure, RESTful APIs, Git, CI/CD, Load Balancers, Cloud CDNs, DNS, VMs, Serverless, Lambda, Cloud Databases (Firebase, DynamoDB), Cloud Storage (S3, Google Cloud Storage Bucket), Custom IP and Domain Configuration
            Environments: Adobe XD, Visual Studio, Eclipse, JetBrains IDE, Linux-based OS (Debian, Ubuntu, Raspbian)
            Programming: Python, Java, JavaScript, C#, C, C++, PHP, SQL, R
            Frameworks: Django, Laravel, React, Flutter
            Servers: Apache, Nginx, mod_wsgi, Webmin, LAMPP, XAMPP
            Device Provisioning: SoftAp/BLE, FreeRTOS, embed, Keil, Raspberry Pi, Arduino, STM32, Nordic, ESP32, ESP8266, Microchip, ESP CAM, NB-IoT, SIMCOM, 4G LTE, WiFi, BLE, GSM, interfacing MCU, digital/analog sensors, peripherals, cameras, image processing or machine learning
            IoT Protocols: Azure/AWS IoT, MQTT, LwM2M, HTTP, HTTPS, UDP, TCP, COAP with DTLS
            Email and Push Notification Automation: Firebase FCM, Apple APNS, AWS SES, OneSignal, Mailgun

            Education
            Bachelor's Degree in Computer Science, BRAC University
            Higher Secondary Certificate in Pure Science, Kadam Rasul College
            """;

        // Set up the HTTP client
        HttpClient client = HttpClient.newHttpClient();

        // Set up the request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("model", "gpt-4");
        requestBody.put("messages", new Object[] {
                Map.of("role", "user", "content", message)
        });

        Gson gson = new Gson();
        String jsonRequestBody = gson.toJson(requestBody);

        // Create HTTP request
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(API_URL))
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + API_KEY)
                .POST(HttpRequest.BodyPublishers.ofString(jsonRequestBody))
                .build();

        // Send the request and get the response
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        // Print the response for debugging
        System.out.println("Full API Response: " + response.body());

        // Parse the JSON response
        JsonObject jsonResponse = gson.fromJson(response.body(), JsonObject.class);

        // Check for error in response
        if (jsonResponse.has("error")) {
            System.err.println("Error from API: " + jsonResponse.getAsJsonObject("error").get("message").getAsString());
        } else if (jsonResponse.has("choices")) {
            JsonArray choices = jsonResponse.getAsJsonArray("choices");
            JsonObject firstChoice = choices.get(0).getAsJsonObject();
            String htmlContent = firstChoice.getAsJsonObject("message").get("content").getAsString();

            // Output only the HTML content
            System.out.println(htmlContent);
        } else {
            System.err.println("Unexpected API response format. No 'choices' field found.");
        }
    }
}
