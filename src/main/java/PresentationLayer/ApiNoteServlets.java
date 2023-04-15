package PresentationLayer;

import BusinessLayer.Servise;

import Configure.AllNotes;
import Configure.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Optional;


@WebServlet("/api/note")
public class ApiNoteServlets extends HttpServlet {
    Servise servise = new Servise();
    static ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Optional<List<Note>>allNotes = servise.getAllNotes();
        if (allNotes.isPresent()){
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            AllNotes notes = new AllNotes(allNotes.get());
            String json = mapper.writeValueAsString(notes);
            PrintWriter out = new PrintWriter(response.getWriter());
            out.write(json);
            out.flush();
            out.close();
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = request.getReader();
        String body;
        while ((body = reader.readLine()) != null){
            sb.append(body);
        }
        try {
            Note note1 = mapper.readValue(sb.toString(), Note.class);
            servise.insertNote(note1);
            response.setStatus(HttpServletResponse.SC_CREATED);
            String json = mapper.writeValueAsString(note1);
            response.setContentType("application/json");
            PrintWriter out = new PrintWriter(response.getWriter());
            out.write(json);
            out.flush();
            out.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
