package com.MarvelMan.JournalApplication.controller;

import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.service.UserDetailsServiceImpl;
import com.MarvelMan.JournalApplication.service.UserService;
import com.MarvelMan.JournalApplication.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "Ok";
    }

    @PostMapping("/signup")
    public void signup(@RequestBody User user){
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUserName(), user.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(user.getUserName());
          String jwt =  jwtUtil.generateToken(userDetails.getUsername());
          return new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e){
    return new ResponseEntity<>("Incorrect Username and password",HttpStatus.BAD_REQUEST);

        }
    }
}
