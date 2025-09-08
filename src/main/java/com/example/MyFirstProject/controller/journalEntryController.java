/*package com.example.MyFirstProject.controller;

import com.example.MyFirstProject.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/_journal")
public class journalEntryController {
    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

   @GetMapping
   public List<JournalEntry> getAll(){
        return new ArrayList<>(journalEntries.values());
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        journalEntries.put(myEntry.getId(),myEntry);
        return true;
    }
    @GetMapping("id/{myid}")
    public JournalEntry getJournalEntryById(@PathVariable long myid){
       return journalEntries.get(myid);
    }
    @DeleteMapping("id/{myid}")
    public JournalEntry deleteJournalEntryById(@PathVariable long myid){
        return journalEntries.remove(myid);
    }
    @PutMapping("id/{id}")
    public JournalEntry updateJournalById(@PathVariable long id , @RequestBody JournalEntry myEntry){
       return journalEntries.put(id,myEntry);
    }


}
*/