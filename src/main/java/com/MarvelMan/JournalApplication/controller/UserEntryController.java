package com.MarvelMan.JournalApplication.controller;

import com.MarvelMan.JournalApplication.api.reponse.WeatherResponse;
import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.service.UserService;
import com.MarvelMan.JournalApplication.service.WeatherService;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserEntryController {

    @Autowired
private UserService userService;

    @Autowired
    WeatherService weatherService;
    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      String userName=authentication.getName();
    User userInDb = userService.findByUserName(userName);
         userInDb.setUserName(user.getUserName());
         userInDb.setPassword(user.getPassword());
         userService.saveNewUser(userInDb);
     return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/greeting")
    public ResponseEntity<?> greetings(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName=authentication.getName();
       WeatherResponse weatherResponse=weatherService.getWeather("Mumbai");
        LocalDateTime time2= LocalDateTime.now();
        return new ResponseEntity<>("Hi "+ userName +" "+ weatherResponse.getCurrent().getTemperature()+" "+weatherResponse.getCurrent().getIs_day(), HttpStatus.OK);
    }
}
