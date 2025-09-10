package com.example.MyFirstProject.service;

import com.example.MyFirstProject.Repository.JournalEntryRepository;
import com.example.MyFirstProject.entity.JournalEntry;
import com.example.MyFirstProject.entity.User;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class JournalEntryService {
    @Autowired
private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserEntryService userEntryService;
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try { User user = userEntryService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            userEntryService.saveEntry(user);

        }
        catch (Exception e){
            System.out.println(e);
        }

    }

    public void saveEntry(JournalEntry journalEntry){

        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }
    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {

            User user = userEntryService.findByUserName(userName);
             removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userEntryService.saveEntry(user);
                journalEntryRepository.deleteById(id);
            }

        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occurred while deleting.",e);

        }
        return removed;

    }
    public List<JournalEntry> findByUserName(String userName){
        User user = userEntryService.findByUserName(userName);
        if (user != null && user.getJournalEntries() != null) {
            return user.getJournalEntries();
        }
        return List.of();

    }
}
