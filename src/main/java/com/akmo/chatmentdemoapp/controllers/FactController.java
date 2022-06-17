package com.akmo.chatmentdemoapp.controllers;

import com.akmo.chatmentdemoapp.entity.ResponseLog;
import com.akmo.chatmentdemoapp.repository.ResponseLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;

@RestController
@RequestMapping("facts")
@AllArgsConstructor
public class FactController {

    private final ResponseLogRepository responseLogRepository;

    @GetMapping("dog")
    public ResponseEntity<String> dogFacts(HttpServletRequest request) {
        try {
            URL url = new URL("https://www.dogfactsapi.ducnguyen.dev/api/v1/facts/?number=1");
            String response = sendGET(url.toString());
            response = response.substring(11, response.length() - 3);
            saveResponse(response, request);
            return ResponseEntity.ok().body(response);
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body("Error Occurred");
        }
    }

    @GetMapping("cat")
    public ResponseEntity<String> catFacts(HttpServletRequest request) {
        try {
            URL url = new URL("https://catfact.ninja/fact");
            String response = sendGET(url.toString());
            response = response.substring(9, response.length() - 15);
            saveResponse(response, request);
            return ResponseEntity.ok().body(response);
        }
        catch (IOException e) {
            return ResponseEntity.badRequest().body("Error Occurred");
        }
    }

    private void saveResponse(String response, HttpServletRequest request) {
        ResponseLog log = new ResponseLog(null, request.getRemoteAddr(), response,
                LocalDateTime.now());
        responseLogRepository.save(log);
    }

    private String sendGET(String GET_URL) throws IOException {
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println(response);
            return response.toString();
        } else {
            System.out.println("GET request not worked");
            return "";
        }
    }
}
