package Configure;

import PresentationLayer.ApiNoteIDServlet;
import PresentationLayer.ApiNoteServlets;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

//servlets - parsing of input data and put created Object into Servis in Buisiness Layer
//in servise - reailisation of functional
//dataLayer -

//check data
//think of id
public class MainServer {
    private Server server;
    public void start() throws Exception{
        int port = 8080;
        Server server = new Server(port);
        ServletContextHandler handler = new ServletContextHandler(server,"/");
        handler.addServlet(ApiNoteServlets.class, "/api/note");
        handler.addServlet(ApiNoteIDServlet.class, "/api/note/*");
        try {
            server.start();
            System.out.println("start work");
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

}
