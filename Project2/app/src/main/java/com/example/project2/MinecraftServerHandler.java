package com.example.project2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MinecraftServerHandler {
    public static final MediaType JSON = MediaType.get("application/json");
    public static final String URL_START = "https://api.mcsrvstat.us/3/";
    static OkHttpClient client = new OkHttpClient();

    static String post(String ip) throws IOException {
        RequestBody body = RequestBody.create("", JSON);
        Request request = new Request.Builder()
                .url(URL_START + ip)
                .get()
                .build();
        try (Response response = client.newCall(request).execute()) {
            JSONObject json = new JSONObject(response.body().string());
            if(json.getJSONObject("debug").getJSONObject("error").has("ip"))
                return "IP Not Found";
            boolean isOnline = json.getBoolean("online");
            return "Status: " + (isOnline ? "Online" : "Offline");
        } catch (JSONException e) {
            return e.getMessage();
        }
    }
}
