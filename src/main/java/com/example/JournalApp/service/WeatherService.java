package com.example.JournalApp.service;

import com.example.JournalApp.api.response.WeatherResponse;
import com.example.JournalApp.cache.AppCache;
import com.example.JournalApp.constants.PlaceHolders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    //private  static  final String apiKey = "a826fb7dd6cca5a492c8f43df1762ef6";
    @Value("${weather.api.key}")
    private String apiKey;

//private  static  final String API = "https://api.weatherstack.com/current?access_key=API_KEY&query=CITY";

    // NOTE: using this for hit the api through code rather than Browser
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    @Autowired
    private RedisService redisService;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("weather_Of_" + city, WeatherResponse.class);
        if (weatherResponse != null) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.appCache.get(AppCache.keys.WEATHER_API.toString()).replace(PlaceHolders.CITY, city).replace(PlaceHolders.API_KEY, apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("weather_Of_" + city, body, 300l);
            }
            return body;
        }
    }
}
