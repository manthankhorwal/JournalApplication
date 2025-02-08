package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.api.reponse.WeatherResponse;
import com.MarvelMan.JournalApplication.cache.AppCache;
import com.MarvelMan.JournalApplication.constants.AppCacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class WeatherService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private AppCache cache;


    public WeatherResponse getWeather(String city) {
        String finalAPI = cache.appCache.get(AppCache.Keys.WEATHER_API_URL.toString()).replace(AppCacheConstants.CITY, city)
                .replace(AppCacheConstants.ACCESS_KEY, cache.appCache.get(AppCache.Keys.WEATHER_API.toString()));
        ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
        return response.getBody();
    }
}

