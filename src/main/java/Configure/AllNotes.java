package Configure;

import lombok.Getter;

import java.util.List;

@Getter
public class AllNotes {
    List<Note> notes;
    public AllNotes(List notes){
        this.notes = notes;
    }
}
