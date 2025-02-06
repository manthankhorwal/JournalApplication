package com.MarvelMan.JournalApplication.controller;


import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
   private UserService userService;
    private static final Logger logger= LoggerFactory.getLogger(AdminController.class);
@GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all=userService.getAll();
logger.info("Manthan kesa hai");
        if(all!=null){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
