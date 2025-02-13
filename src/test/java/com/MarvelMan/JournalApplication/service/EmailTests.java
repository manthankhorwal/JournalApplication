package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.scheduler.UserScheduler;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EmailTests {
  @Autowired
    UserScheduler userScheduler;
@Test
    public void email(){
  userScheduler.fetchUsersAndSendSaMail();
}

}
