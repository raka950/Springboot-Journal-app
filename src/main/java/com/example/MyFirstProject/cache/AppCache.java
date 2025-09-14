package com.example.MyFirstProject.cache;

import com.example.MyFirstProject.Repository.ConfigJournalAppRepository;
import com.example.MyFirstProject.entity.ConfigJournalAppEntry;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AppCache {
    public  enum keys{
        weather_api;

    }

    @Autowired
    private ConfigJournalAppRepository configJournalAppRepository;

    // Initialize the map so it’s not null
    public Map<String, String> APP_CACHE = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ConfigJournalAppEntry> all = configJournalAppRepository.findAll();
        for (ConfigJournalAppEntry configJournalAppEntry : all) {
            APP_CACHE.put(configJournalAppEntry.getKey(), configJournalAppEntry.getValue());
        }
        // ❌ Remove this line: APP_CACHE = null;
    }

    public Map<String, String> getCache() {
        return APP_CACHE;
    }
}
