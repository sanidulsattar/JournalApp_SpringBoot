package com.journal.Journal.Service;

import com.journal.Journal.Entity.JournalEntry;
import com.journal.Journal.Entity.User;
import com.journal.Journal.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;
    @Autowired
    private UserService userService;

    public void saveEntry(JournalEntry journalEntry, String userName) {

        User user = userService.findByName(userName);
        JournalEntry saved = journalEntryRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
       userService.saveUser(user);

    }

    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findbyId(ObjectId id) {
        return journalEntryRepository.findById(id);

    }

    public void deleteById(ObjectId id, String userName) {

        User user = userService.findByName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));  // lamda exp for delete jentry whoes id is = id here
        userService.saveUser(user);
        journalEntryRepository.deleteById(id);

    }

}
