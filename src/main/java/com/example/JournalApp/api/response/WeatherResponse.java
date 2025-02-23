package com.example.JournalApp.api.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Getter
@Setter
public class WeatherResponse {

    private Current current;

    @Getter
    @Setter
    public class Current {

        @JsonProperty("observation_time")
        private String ObservationTime;

        private int temperature;

        @JsonProperty("weather_descriptions")
        private List<String> WeatherDescription;

        private int humidity;

        @JsonProperty("feelslike")
        private int feelsLike;

    }

}
