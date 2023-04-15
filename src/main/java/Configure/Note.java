package Configure;

import lombok.*;

import java.time.LocalDate;
import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Note {
    private String title;
    private String content;
    private Date creation_date = Date.valueOf(LocalDate.now());

    public Note(String title, String content){
        this.title = title;
        this.content = content;
    }
}
