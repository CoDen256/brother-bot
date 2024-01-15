package aljolen.api;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class PrinterServiceTest {

    @Test
    void mathWorks() {

    }


    @Test
    void httpClient() throws IOException, URISyntaxException, InterruptedException {
        //1. Create an HTTP client
        HttpClient client = HttpClient.newHttpClient();

        //2. Create an HTTP request to https://postman-echo.com/get
        HttpRequest request = HttpRequest.newBuilder()
                .uri(new URI("https://postman-echo.com/get"))
                .GET()
                .build();

        //3. Send the request via a client. Interpret body as String via BodyHandlers.ofString()
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        //4. Log the response body
        String body = response.body();
        System.out.println(body);   // print the body of the response

        //5. Parse the response body
        JSONObject object = new JSONObject(body);
        String url = object.getString("url");
        System.out.println(url);
    }

}