package com.example.MyFirstProject.service;

import com.example.MyFirstProject.api.response.WeatherResponse;
import com.example.MyFirstProject.cache.AppCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.TimeUnit;

@Service
public class WeatherService {

    @Value("${weather.api.key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    private static final long REDIS_TTL_SECONDS = 300L; // 5 minutes

    public WeatherResponse getWeather(String location) {
        if (location == null || location.isEmpty()) {
            location = "Patna";
        }
        location = location.trim().toLowerCase();

        try {
            // 1️⃣ Try Redis cache first
            WeatherResponse weatherResponse = redisService.get("weather:" + location, WeatherResponse.class);
            if (weatherResponse != null) {
                System.out.println("✅ Weather fetched from Redis for " + location);
                return weatherResponse;
            }

            // 2️⃣ Try in-memory cache
            WeatherResponse cached = appCache.getWeather(location);
            if (cached != null) {
                System.out.println("✅ Weather fetched from AppCache for " + location);
                return cached;
            }

            // 3️⃣ Fetch from API
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

            if (response != null) {
                // 4️⃣ Cache in Redis
                redisService.set("weather:" + location, response, REDIS_TTL_SECONDS, TimeUnit.SECONDS);

                // 5️⃣ Cache in AppCache
                appCache.putWeather(location, response);

                System.out.println("✅ Weather cached for " + location);
            }

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
