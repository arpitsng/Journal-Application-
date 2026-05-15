package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data  //--automatically generates getter, setters, equals, tostring like methods
public class JournalEntry {

    @Id //primary key
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date;


}