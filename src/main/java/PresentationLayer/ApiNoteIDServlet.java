package PresentationLayer;

import BusinessLayer.Servise;
import Configure.Note;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@WebServlet("/api/note/*")
public class ApiNoteIDServlet  extends HttpServlet {

    static Servise servise = new Servise();
    static ObjectMapper mapper = new ObjectMapper();

    protected void doGet(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        Optional<Note> note = servise.getNoteById(id);
        if (note.isPresent()){
            try {
                String json = mapper.writeValueAsString(note.get());

                response.setStatus(HttpServletResponse.SC_OK);
                response.setContentType("application/json");
                PrintWriter out = new PrintWriter(response.getWriter());
                out.write(json);
                out.flush();
                out.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }else{
            try {
                response.setStatus(HttpServletResponse.SC_NOT_FOUND);
                response.setContentType("text/plain");
                PrintWriter out = new PrintWriter(response.getWriter());
                out.write("Note not found");
                out.flush();
                out.close();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

    }


    protected void doDelete(HttpServletRequest request, HttpServletResponse response){
        int id = Integer.parseInt(request.getPathInfo().substring(1));
        servise.delete(id);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}
