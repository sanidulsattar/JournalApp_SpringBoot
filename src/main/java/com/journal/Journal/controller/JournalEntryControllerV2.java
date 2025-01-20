package com.journal.Journal.controller;

import com.journal.Journal.Entity.JournalEntry;
import com.journal.Journal.Entity.User;
import com.journal.Journal.Service.JournalEntryService;
import com.journal.Journal.Service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {
    @Autowired
    private JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;

    @GetMapping("{userName}")
    public List<JournalEntry> getAllJouralEntryofUser(@PathVariable String userName) {
        //  System.out.println("this is entry");
        User user = userService.findByName(userName);
        return user.getJournalEntries();
    }

    @PostMapping("{userName}")
    public boolean postEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {
        myEntry.setDate(LocalDateTime.now());
        journalEntryService.saveEntry(myEntry, userName);
        return true;

    }

    @GetMapping("/id/{myId}")
    public JournalEntry findById(@PathVariable ObjectId myId) {
        return journalEntryService.findbyId(myId).orElse(null);

    }

   // @PutMapping("/id/{id}")
//    public JournalEntry updateById(@PathVariable ObjectId id, @RequestBody JournalEntry newEntry) {
////        JournalEntry old = journalEntryService.findbyId(id).orElse(null);
////        if (old != null) {
////            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle() : old.getTitle());
////            old.setContent(newEntry.getContent() != null && !newEntry.getContent().equals("") ? newEntry.getContent() : old.getContent());
////        }
////        journalEntryService.saveEntry(old, userName);
////        return old;
//
//    }

    @DeleteMapping("/id/{userName}/{id}")
    public ResponseEntity<?> deleteById(@PathVariable ObjectId id, @PathVariable String userName) {
        journalEntryService.deleteById(id, userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
