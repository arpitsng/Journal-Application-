package net.engineeringdigest.journalApp.Entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Date;

@Document(collection = "journal_entries")
@Data  //--automatically generates getter, setters, equals, tostring like methods
@NoArgsConstructor  //--creates an empty object before filling it with json response -- used in deserialization
public class JournalEntry {

    @Id //primary key
    private ObjectId id;

    private String title;
    private String content;
    private LocalDateTime date;


}