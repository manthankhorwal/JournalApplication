package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.ArgumentMatchers;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceImplTests {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @MockBean
    UserRepository userRepository;

    @Test
    void loadUserByUsername(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("ram").password("ram").roles(new ArrayList<>()).build());
        UserDetails user= userDetailsService.loadUserByUsername("ram");
        Assertions.assertNotNull(user);


    }
}
