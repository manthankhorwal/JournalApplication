package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

@Autowired
private UserRepository userRepository;
    @ParameterizedTest
    @CsvSource({"Manthan","Bittu"})
    public void testFindByUserName(String userName){
        assertNotNull(userRepository.findByUserName(userName));
    }

}
