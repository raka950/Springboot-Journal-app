package com.example.MyFirstProject.service;

import com.example.MyFirstProject.api.response.WeatherResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String API_KEY = "192dc1d569020d1dd4ff4c1c0a074f5d";
    private static final String API_URL = "https://api.weatherstack.com/current?access_key=" + API_KEY + "&query={city}";

    @Autowired
    private RestTemplate restTemplate;

    public String getWeather(String city) {
        String finalAPI = API_URL.replace("{city}", city);
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        WeatherResponse body = response.getBody();
        return body.toString();
    }
}
