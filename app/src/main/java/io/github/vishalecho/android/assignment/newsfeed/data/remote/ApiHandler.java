package io.github.vishalecho.android.assignment.newsfeed.data.remote;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import io.github.vishalecho.android.assignment.newsfeed.util.NetworkUtil;

public class ApiHandler {

    public static String callNewsFeedApi() throws IOException {
        URL url = new URL(NetworkUtil.url);
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        if (responseCode == HttpsURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = reader.readLine()) != null) {
                response.append(inputLine);
            }
            reader.close();
            return response.toString();
        } else {
            return "Error";
        }
    }
}
