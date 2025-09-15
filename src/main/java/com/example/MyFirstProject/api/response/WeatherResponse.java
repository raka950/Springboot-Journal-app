package com.example.MyFirstProject.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {
    private Current current;

    @Getter
    @Setter
    public static class Current {
        private int temperature;
        private int feelslike;

        @JsonProperty("weather_descriptions")
        private List<String> weatherDescriptions;
    }
}
