package aljolen.api;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.io.IOException;

class PrinterServiceTest {

    @Test
    void mathWorks() {

    }


    @Disabled
    @Test
    void httpClient() throws IOException {
        //1. Create an HTTP client
        OkHttpClient client = new OkHttpClient.Builder().build();

        //2. Create an HTTP request to https://reqbin.com/echo/get/json
        Request request = new Request.Builder()
                .url("https://reqbin.com/echo/get/json")
                .get()
                .build();

        //3. Call to the REST API
        Response response = client.newCall(request).execute();
        ResponseBody body = response.body();
        assertNotNull(body);
        System.out.println(body);

        //4. Parse the response body
        JSONObject object = new JSONObject(body.string());
        boolean name = object.getBoolean("success");
        System.out.println(name);
    }

}