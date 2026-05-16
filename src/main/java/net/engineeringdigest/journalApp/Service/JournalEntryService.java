package net.engineeringdigest.journalApp.Service;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import net.engineeringdigest.journalApp.Entity.User;
import net.engineeringdigest.journalApp.Repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional //this ensures the methods runs as a single if anything fails it'll roll back all the operations that are successfully done
    public void saveEntry(JournalEntry journalEntry, String userName){
        try{
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            User user = userService.findByUserName(userName);
            user.getJournalEntries().add(saved);
            userService.saveEntry(user);
        }
        catch(Exception e){
            throw new RuntimeException("An error occured while saving the entry", e);
        }


    }


    public void saveEntry(JournalEntry journalEntry){  //overloaded method
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId myid){ //optional mean -- it may return or not
        return journalEntryRepository.findById(myid);
    }

    public void deleteById(ObjectId myid, String userName){
        User user = userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(myid));
        userService.saveEntry(user);
        journalEntryRepository.deleteById(myid);
    }


}
