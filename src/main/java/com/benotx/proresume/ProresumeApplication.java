package com.benotx.proresume;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;

@SpringBootApplication
@RestController
public class ProresumeApplication {
    private static final String API_KEY = ""; // Replace with your actual OpenAI API key
    private static final String API_URL = "https://api.openai.com/v1/chat/completions";


    public static void main(String[] args) {
        SpringApplication.run(ProresumeApplication.class, args);
    }


    String sample_resume="<!DOCTYPE html>\n" +
                         "<html lang=\"en\">\n" +
                         "<head>\n" +
                         "    <meta charset=\"UTF-8\">\n" +
                         "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                         "    <title>Md Jubayer - Resume</title>\n" +
                         "    <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css\">\n" +
                         "    <style>\n" +
                         "        body {\n" +
                         "            font-family: Arial, sans-serif;\n" +
                         "            line-height: 1.6;\n" +
                         "            margin: 0;\n" +
                         "            padding: 0;\n" +
                         "            background-color: #f9f9f9;\n" +
                         "            color: #333;\n" +
                         "            max-width: 210mm; /* A4 width */\n" +
                         "            margin-left: auto;\n" +
                         "            margin-right: auto;\n" +
                         "            padding: 10mm;\n" +
                         "\t\t\tpadding-top:0mm;\n" +
                         "\t\t\tpadding-bottom:0mm;\n" +
                         "           /* box-shadow: 10px 10px 10px 10px rgba(0, 0, 0, 0.1);*/\n" +
                         "            font-size: 11pt;\n" +
                         "        }\n" +
                         "        .container {\n" +
                         "            width: 100%;\n" +
                         "            background-color: #fff;\n" +
                         "            padding: 15px;\n" +
                         "            border-radius: 10px;\n" +
                         "        }\n" +
                         "        .header {\n" +
                         "            display: flex;\n" +
                         "            align-items: center;\n" +
                         "            justify-content: space-between;\n" +
                         "            border-bottom: 2px solid #ddd;\n" +
                         "            padding-bottom: 10px;\n" +
                         "            margin-bottom: 15px;\n" +
                         "        }\n" +
                         "        .photo {\n" +
                         "            width: 100px;\n" +
                         "            height: 100px;\n" +
                         "            border-radius: 50%;\n" +
                         "            margin-right: 20px;\n" +
                         "        }\n" +
                         "        .contact-info {\n" +
                         "            display: flex;\n" +
                         "            flex-wrap: wrap;\n" +
                         "            align-items: center;\n" +
                         "        }\n" +
                         "        .contact-info p {\n" +
                         "            margin: 5px 15px 5px 0;\n" +
                         "            flex: 1 1 auto;\n" +
                         "        }\n" +
                         "        .contact-info a {\n" +
                         "            color: #0066cc;\n" +
                         "            text-decoration: none;\n" +
                         "            display: inline-block;\n" +
                         "            vertical-align: middle;\n" +
                         "        }\n" +
                         "        .contact-info a:hover {\n" +
                         "            text-decoration: underline;\n" +
                         "        }\n" +
                         "        .contact-info a i {\n" +
                         "            margin-right: 5px;\n" +
                         "        }\n" +
                         "        h1 {\n" +
                         "            color: #333;\n" +
                         "            margin-bottom: 5px;\n" +
                         "            font-size: 24pt;\n" +
                         "        }\n" +
                         "        h3 {\n" +
                         "            color: #777;\n" +
                         "            margin-top: 0;\n" +
                         "            margin-bottom: 10px;\n" +
                         "            font-size: 14pt;\n" +
                         "        }\n" +
                         "        h2 {\n" +
                         "            color: #333;\n" +
                         "            border-bottom: 2px solid #ddd;\n" +
                         "            padding-bottom: 5px;\n" +
                         "            margin-bottom: 10px;\n" +
                         "            font-size: 16pt;\n" +
                         "        }\n" +
                         "        .section {\n" +
                         "            margin-bottom: 15px;\n" +
                         "            padding-top: 10px;\n" +
                         "        }\n" +
                         "        .section-divider {\n" +
                         "            border-top: 2px solid #ddd;\n" +
                         "            margin-top: 10px;\n" +
                         "            margin-bottom: 15px;\n" +
                         "        }\n" +
                         "        ul {\n" +
                         "            list-style-type: none;\n" +
                         "            padding: 0;\n" +
                         "        }\n" +
                         "        ul li {\n" +
                         "            margin-bottom: 5px;\n" +
                         "            position: relative;\n" +
                         "            padding-left: 20px;\n" +
                         "        }\n" +
                         "        ul li:before {\n" +
                         "            content: \"\\2022\";\n" +
                         "            color: #0066cc;\n" +
                         "            font-weight: bold;\n" +
                         "            position: absolute;\n" +
                         "            left: 0;\n" +
                         "            top: 0;\n" +
                         "        }\n" +
                         "        @media screen and (max-width: 768px) {\n" +
                         "            body {\n" +
                         "                margin: 10px;\n" +
                         "                padding: 10px;\n" +
                         "            }\n" +
                         "            .container {\n" +
                         "                padding: 10px;\n" +
                         "            }\n" +
                         "            .header {\n" +
                         "                flex-direction: column;\n" +
                         "                align-items: center;\n" +
                         "                text-align: center;\n" +
                         "            }\n" +
                         "            .contact-info {\n" +
                         "                margin-left: 0;\n" +
                         "                text-align: center;\n" +
                         "            }\n" +
                         "            .photo {\n" +
                         "                margin: 0 0 10px 0;\n" +
                         "            }\n" +
                         "        }\n" +
                         "    </style>\n" +
                         "</head>\n" +
                         "<body>\n" +
                         "\n" +
                         "    <div class=\"container\">\n" +
                         "        <header class=\"header\">\n" +
                         "            <img src=\"https://media.licdn.com/dms/image/C5103AQE5JIKgtUvtCw/profile-displayphoto-shrink_800_800/0/1545777253146?e=1726099200&v=beta&t=SLJ8uwVd2X0FIQfuVvFshzcSn2Gy0JkgNvyQ-m0qlHg\" alt=\"Md Jubayer's Photo\" class=\"photo\">\n" +
                         "            <div>\n" +
                         "                <h1>Md Jubayer</h1>\n" +
                         "                <h3>IoT, Cloud, and Full-Stack Solutions</h3>\n" +
                         "                <div class=\"contact-info\">\n" +
                         "                    <p>Bangladesh</p>\n" +
                         "                    <p><a href=\"tel:+8801827095630\"><i class=\"fas fa-phone\" aria-hidden=\"true\"></i> +8801827095630</a></p>\n" +
                         "                    <p><a href=\"mailto:mdjubayer247@gmail.com\"><i class=\"far fa-envelope\" aria-hidden=\"true\"></i> mdjubayer247@gmail.com</a></p>\n" +
                         "                    <p><a href=\"https://linkedin.com/in/jubayer247\"><i class=\"fab fa-linkedin\" aria-hidden=\"true\"></i> LinkedIn</a></p>\n" +
                         "                    <p><a href=\"https://github.com/jubayer247\"><i class=\"fab fa-github\" aria-hidden=\"true\"></i> GitHub</a></p>\n" +
                         "                </div>\n" +
                         "            </div>\n" +
                         "        </header>\n" +
                         "\n" +
                         "        <section>\n" +
                         "            <p><strong>Experienced in IoT, embedded systems, and full-stack development, adept at generating and refining ideas, and skilled in delivering technical solutions for software and hardware platforms.</strong></p>\n" +
                         "        </section>\n" +
                         "\n" +
                         "        <div class=\"section-divider\"></div>\n" +
                         "\n" +
                         "        <section>\n" +
                         "            <h2>Experience</h2>\n" +
                         "            <ul>\n" +
                         "                <li>Full-stack Developer, Cloud Consultant, Embedded System Developer, Senior IoT Engineer</li>\n" +
                         "                <li>Various Roles: Freelance and Contract</li>\n" +
                         "                <li>Designed, developed, deployed, and administrated: device management, eCommerce, multimedia news portal solutions in desktop, mobile, and web apps, custom hardware, and cloud services</li>\n" +
                         "                <li>Integrated biometric devices: Hysoon, Timmy, ZKTeco</li>\n" +
                         "            </ul>\n" +
                         "        </section>\n" +
                         "\n" +
                         "        <div class=\"section-divider\"></div>\n" +
                         "\n" +
                         "        <section>\n" +
                         "            <h2>Skills</h2>\n" +
                         "            <ul>\n" +
                         "                <li>Development Platforms: AWS, GCP, Azure, RESTful APIs, Git, CI/CD, Load Balancers, Cloud CDNs, DNS, VMs, Serverless, Lambda, Cloud Databases (Firebase, DynamoDB), Cloud Storage (S3, Google Cloud Storage Bucket), Custom IP and Domain Configuration</li>\n" +
                         "                <li>Environments: Adobe XD, Visual Studio, Eclipse, JetBrains IDE, Linux-based OS (Debian, Ubuntu, Raspbian)</li>\n" +
                         "                <li>Programming: Python, Java, JavaScript, C#, C, C++, PHP, SQL, R</li>\n" +
                         "                <li>Frameworks: Django, Laravel, React, Flutter</li>\n" +
                         "                <li>Servers: Apache, Nginx, mod_wsgi, Webmin, LAMPP, XAMPP</li>\n" +
                         "                <li>Device Provisioning: SoftAp/BLE, FreeRTOS, embed, Keil, Raspberry Pi, Arduino, STM32, Nordic, ESP32, ESP8266, Microchip, ESP CAM, NB-IoT, SIMCOM, 4G LTE, WiFi, BLE, GSM, interfacing MCU, digital/analog sensors, peripherals, cameras, image processing or machine learning</li>\n" +
                         "                <li>IoT Protocols: Azure/AWS IoT, MQTT, LwM2M, HTTP, HTTPS, UDP, TCP, COAP with DTLS</li>\n" +
                         "                <li>Email and Push Notification Automation: Firebase FCM, Apple APNS, AWS SES, OneSignal, Mailgun</li>\n" +
                         "            </ul>\n" +
                         "        </section>\n" +
                         "\n" +
                         "        <div class=\"section-divider\"></div>\n" +
                         "\n" +
                         "        <section>\n" +
                         "            <h2>Education</h2>\n" +
                         "            <ul>\n" +
                         "                <li>Bachelor's Degree in Computer Science, BRAC University</li>\n" +
                         "                <li>Higher Secondary Certificate in Pure Science, Kadam Rasul College</li>\n" +
                         "            </ul>\n" +
                         "        </section>\n" +
                         "    </div>\n" +
                         "\n" +
                         "</body>\n" +
                         "</html>\n";


    @GetMapping("/geneate_resume")
    public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) throws IOException, InterruptedException {
        String message = String.format("""
            Generate nicely indented enhanced professional  resume,place contact (phone,email,linkedin, github) in one row,use proper styling and organize content efficiently  with accessible and readable to visually challenged audience, use white space properly preferable less white space in vertical direction  in html (note: response only code and here is some expected output %s) from the provided information below in 

            Mohammad Atikur Rahman 
            IoT, Cloud, and Full-Stack Solutions
            Bangladesh

            +8801XXXXXXXXX

            mdxy@gmail.com

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
            Bachelor's Degree in xxxx Science, XYZ University
            Higher Secondary Certificate in Pure Science, Kadam Rasul College
            """,sample_resume);

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
            return htmlContent;
        } else {
            System.err.println("Unexpected API response format. No 'choices' field found.");
        }
        return String.format("Hello %s!", name);
    }


}
