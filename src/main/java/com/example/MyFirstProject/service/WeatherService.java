package com.example.MyFirstProject.service;

import com.example.MyFirstProject.api.response.WeatherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    public WeatherResponse getWeather(String city) {
        String url = "https://api.weatherstack.com/current?access_key=" + apiKey + "&query=" + city;
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}
