package BusinessLayer;

import Configure.Note;
import DataLayer.NotesRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Servise {
    static NotesRepository repo = new NotesRepository();
    public void insertNote(Note note){
        repo.create(note);
    }

    public Optional<List<Note>> getAllNotes(){
        return repo.readALl();

    }

    public Optional<Note> getNoteById(int id){
        Optional<Note> note = repo.read(id);
        return note;
    }

    public void delete(int id){
        repo.delete(id);
    }
}
