package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.api.reponse.WeatherResponse;
import com.MarvelMan.JournalApplication.cache.AppCache;
import com.MarvelMan.JournalApplication.constants.AppCacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
   @Autowired
   RestTemplate restTemplate;
    @Autowired

    RedisService redisService;
    @Autowired
    private AppCache cache;
    @Autowired
    private RedisTemplate redisTemplate;


    public WeatherResponse getWeather(String city) {
       // redisTemplate.opsForValue().set("yoyo","manthan");
        WeatherResponse weatherResponse = redisService.get("weather_of_" + city, WeatherResponse.class);
        if(weatherResponse!=null){
            return weatherResponse;
        }else {
            String finalAPI = cache.appCache.get(AppCache.Keys.WEATHER_API_URL.toString()).replace(AppCacheConstants.CITY, city)
                    .replace(AppCacheConstants.ACCESS_KEY, cache.appCache.get(AppCache.Keys.WEATHER_API.toString()));
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if(body!=null){
                redisService.set("weather_of_" + city,body, 500L);
            }
            return body;
        }
    }
}

