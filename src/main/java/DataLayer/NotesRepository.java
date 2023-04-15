package DataLayer;

import Configure.Note;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class NotesRepository {
    public NotesRepository(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:7432/notes", "Nastia", "kreker")){
            String createTable = """
                    CREATE TABLE IF NOT EXISTS note(
                    id SERIAL PRIMARY KEY,
                    title TEXT,
                    content TEXT, 
                    creation_date DATE
                    );
                    """;
            try (PreparedStatement pstat = con.prepareStatement(createTable)){
                pstat.execute();
            }catch (Exception e){
                System.out.println("someting wrong with pstat while creation");
            }
        }catch (SQLException e){
            System.out.println("something wrong with connection while creation");
        }

    }

    public void create (Note note){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:7432/notes", "Nastia", "kreker")) {
            String insertIntoTable = """
                    INSERT INTO note (title, content, creation_date)
                    VALUES(?, ?, ?);
                    """;

            try (PreparedStatement pstat = con.prepareStatement(insertIntoTable)) {
                pstat.setString(1, note.getTitle());
                pstat.setString(2, note.getContent());
                pstat.setDate(3, note.getCreation_date());
                pstat.execute();
            } catch (Exception e){
                System.out.println("something wrong with pstat in create");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public Optional<Note> read(int id){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:7432/notes", "Nastia", "kreker")){
            String readNote = """
                    SELECT * FROM note 
                    WHERE id = ?;
                    """;
            try (PreparedStatement pstat = con.prepareStatement(readNote)){
                pstat.setInt(1,id);
                try (ResultSet result = pstat.executeQuery()){
                    result.next();
                    Note note = new Note(result.getString("title"), result.getString("content"), result.getDate("creation_date"));
                    return Optional.of(note);
                } catch (Exception e){
                    System.out.println("Note with such id is not found");
                }
            }catch (Exception e){
                System.out.println("Something wrong with pstat in read");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public Optional<List<Note>> readALl(){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        List<Note> info = new ArrayList<>();
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:7432/notes", "Nastia", "kreker")){
            String selectALl = """
                    SELECT * FROM note;
                    """;
            try (PreparedStatement pstat = con.prepareStatement(selectALl)){
                try(ResultSet result = pstat.executeQuery()){
                    boolean flag = (boolean) result.next();
                    while (flag){
                        Note note = new Note(result.getString("title"),result.getString("content"), result.getDate("creation_date"));
                        info.add(note);
                        flag = result.next();
                    }
                    return Optional.of(info);
                }catch (Exception e){
                    System.out.println("something wrong with resultset in read");
                }
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }
        return Optional.empty();
    }

    public void delete(int id){
        try {
            Class.forName("org.postgresql.Driver");
        }catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try (Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:7432/notes", "Nastia", "kreker")){
            String deleteNote = """
                    DELETE FROM note
                    WHERE id = ?;
                    """;
            try (PreparedStatement pstat = con.prepareStatement(deleteNote)){
                pstat.setInt(1, id);
                pstat.execute();
            }catch(Exception e){
                System.out.println("something wrong with pstat in delete");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

}
