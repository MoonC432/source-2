package au.ntcrs6.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class HttpRequest {
    public String sendGetRequest(String uri, String jsonNode) throws IOException {
        URL url = new URL(Enumerators.BASE_URL + uri + jsonNode);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

    public void sendPostRequest(String uri, String jsonPayload) throws IOException {
        URL url = new URL(Enumerators.BASE_URL + uri);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        con.setRequestMethod("POST");

        con.setRequestProperty("Content-Type", "application/json");

        con.setDoOutput(true);
        con.setDoInput(true);

        try (OutputStream os = con.getOutputStream()) {
            byte[] input = jsonPayload.getBytes("utf-8");
            os.write(input, 0, input.length);
        }

        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());

            if (con.getResponseCode() == 201 || con.getResponseCode() == 200) {
                Alert alert = new Alert(AlertType.INFORMATION);
                alert.setContentText("Created Successfully");
                alert.show();
            } else {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setContentText("Failed Request");
                alert.show();
            }

        }

        con.disconnect();

    }
}
