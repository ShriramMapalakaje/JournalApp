package org.shriram.Journal.service;

import org.bson.types.ObjectId;
import org.shriram.Journal.entity.JournalEntry;
import org.shriram.Journal.entity.User;
import org.shriram.Journal.repository.JournalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {
    @Autowired
    private JournalRepository journalRepository;
    @Autowired
    private  UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        User user = userService.findByUserName(userName);
        journalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = journalRepository.save(journalEntry);
        user.getJournalEntries().add(saved);
        userService.saveUser(user);
    }

    public List<JournalEntry> getAll() {
        return journalRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id) {
        return journalRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed) {
                userService.saveUser(user);
                journalRepository.deleteById(id);
            }

        } catch (Exception e) {
            throw new RuntimeException("An error occurred while deleting the Journal : ",e);
        }
        return removed;
    }

    public void saveEntry(JournalEntry journalEntry) {
        journalRepository.save(journalEntry);
    }

    
}
