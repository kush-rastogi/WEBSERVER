import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void main(String[] args) {
        int port = 8010;
        ExecutorService pool = Executors.newFixedThreadPool(20);

        try (ServerSocket serverSocket = new ServerSocket(port, 200)) { // backlog=200
            System.out.println("Server is listening on port: " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                pool.execute(() -> handleClient(clientSocket));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private static void handleClient(Socket clientSocket) {
        try (clientSocket;
             PrintWriter toClient = new PrintWriter(clientSocket.getOutputStream(), true)) {

            toClient.println("Hello from the server"); // one response per connection

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
