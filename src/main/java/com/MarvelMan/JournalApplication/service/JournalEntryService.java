package com.MarvelMan.JournalApplication.service;

import com.MarvelMan.JournalApplication.entity.JournalEntry;
import com.MarvelMan.JournalApplication.entity.User;
import com.MarvelMan.JournalApplication.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
   private JournalEntryRepository journalEntryRepository;
   @Autowired
   private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry,String userName){
       try {
           User user=userService.findByUserName(userName);
           journalEntry.setDate(LocalDateTime.now());
           JournalEntry saved = journalEntryRepository.save(journalEntry);
           user.getJournalEntries().add(saved);
           userService.saveUser(user);
       } catch (Exception e) {
           System.out.println(e.getMessage()+" Error occurred while saving");
           throw new RuntimeException(e);
       }

    }
    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }
    public List<JournalEntry> getAll(){

        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }
    public boolean deleteById(String userName,ObjectId id){
        boolean removed=false;
        try {
            User user=userService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x->x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            throw new RuntimeException("An error");
        }
        return removed;
    }

}
