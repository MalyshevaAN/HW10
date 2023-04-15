package Configure;


public class TestStart {
    public static void main(String[] args) {
        MainServer server = new MainServer();
        try {
            server.start();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
