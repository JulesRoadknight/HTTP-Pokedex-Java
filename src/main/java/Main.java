import java.io.IOException;
import HTTPServer.HTTPServer;
import HTTPServer.Router;
import routes.RouteMatcher;

public class Main {
    public static void main(String[] args) throws IOException {
        int port = 5001;
        Router router = new RouteMatcher();
        HTTPServer httpServer = new HTTPServer(port, router);
        httpServer.start();
    }
}
