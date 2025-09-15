package com.example.MyFirstProject.cache;

import com.example.MyFirstProject.Repository.ConfigJournalAppRepository;
import com.example.MyFirstProject.api.response.WeatherResponse;
import com.example.MyFirstProject.entity.ConfigJournalAppEntry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {

    private final Map<String, WeatherResponse> weatherCache = new HashMap<>();
    private final Map<String, String> configCache = new HashMap<>();

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntry> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntry entry : all) {
            configCache.put(entry.getKey(), entry.getValue());
        }
    }

    public void putWeather(String location, WeatherResponse response) {
        if (location != null && response != null) {
            weatherCache.put(location.toLowerCase(), response);
        }
    }

    public WeatherResponse getWeather(String location) {
        if (location == null) return null;
        return weatherCache.get(location.toLowerCase());
    }

    public String getConfig(String key) {
        if (key == null) return null;
        return configCache.get(key);
    }
}
