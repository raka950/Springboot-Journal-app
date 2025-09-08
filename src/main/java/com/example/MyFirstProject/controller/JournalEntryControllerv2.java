package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.entity.JournalEntry;
import com.example.MyFirstProject.entity.User;
import com.example.MyFirstProject.service.JournalEntryService;
import com.example.MyFirstProject.service.UserEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerv2 {

    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserEntryService  userEntryService;

   @GetMapping("{userName}")
   public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName){
       User user = userEntryService.findByUserName(userName);
       List<JournalEntry> all = user.getJournalEntries();
       if (all!= null && !all.isEmpty()){
           return new ResponseEntity<>(all,HttpStatus.OK);
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry> createEntry(@RequestBody JournalEntry myEntry , @PathVariable String userName){
       try {
           myEntry.setDate(LocalDateTime.now());
           journalEntryService.saveEntry(myEntry, userName);
           return new ResponseEntity<>(myEntry, HttpStatus.CREATED);

       }
       catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }
    @GetMapping("id/{myid}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myid){
       Optional<JournalEntry> journalEntry =  journalEntryService.findById(myid);
       if (journalEntry.isPresent()){
           return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("id/{userName}/{myid}")
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myid , @PathVariable String userName){
       journalEntryService.deleteById(myid,userName);
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @PutMapping("id/{userName}/{id}")
    public ResponseEntity<?> updateJournalById(@PathVariable ObjectId id,
                                               @RequestBody JournalEntry newEntry,
                                               @PathVariable String userName) {
        JournalEntry old = journalEntryService.findById(id).orElse(null);
        if (old != null) {
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().isEmpty()
                    ? newEntry.getTitle()
                    : old.getTitle());

            old.setContent(newEntry.getContent() != null && !newEntry.getContent().isEmpty()
                    ? newEntry.getContent()
                    : old.getContent());

            old.setDate(LocalDateTime.now()); // optional: update timestamp

            journalEntryService.saveEntry(old, userName);
            return new ResponseEntity<>(old, HttpStatus.OK); // ✅ success
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND); // ✅ only if not found
    }



}
