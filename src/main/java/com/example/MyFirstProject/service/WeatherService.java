package com.example.MyFirstProject.service;

import com.example.MyFirstProject.api.response.WeatherResponse;
import com.example.MyFirstProject.cache.AppCache;
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

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String location) {
        try {
            if (location == null || location.isEmpty()) {
                location = "Patna";
            }

            WeatherResponse cached = appCache.getWeather(location);
            if (cached != null) return cached;

            String urlTemplate = appCache.getConfig("weather_api");
            if (urlTemplate == null || urlTemplate.isEmpty()) {
                System.err.println("Weather API URL template not found!");
                return null;
            }

            String url = urlTemplate.replace("<apiKey>", apiKey.trim())
                    .replace("<city>", location);

            System.out.println("Weather API URL: " + url);

            ResponseEntity<WeatherResponse> responseEntity =
                    restTemplate.exchange(url, HttpMethod.GET, null, WeatherResponse.class);

            WeatherResponse response = responseEntity.getBody();

            if (response != null && response.getCurrent() != null) {
                System.out.println("Feels like: " + response.getCurrent().getFeelslike());
            }

            appCache.putWeather(location, response);
            return response;

        } catch (Exception e) {
            System.err.println("Error fetching weather: " + e.getMessage());
            return null;
        }
    }

    public WeatherResponse getPatnaWeather() {
        return getWeather("Patna");
    }
}
