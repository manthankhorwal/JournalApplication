package com.MarvelMan.JournalApplication.service.repository;

import com.MarvelMan.JournalApplication.repository.UserRepositoryImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserRepositoryImplTests {

    @Autowired
    UserRepositoryImpl userRepository;

    @Test
    public void testQuery(){
        Assertions.assertNotNull(userRepository.getUserForSA());
    }
}
