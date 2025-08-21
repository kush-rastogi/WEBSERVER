import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        int port = 8010;

        for (int i = 0; i < 100; i++) {   // loop exactly 100 times
            try {
                InetAddress address = InetAddress.getByName("localhost");
                try (Socket socket = new Socket(address, port);
                     PrintWriter toSocket = new PrintWriter(socket.getOutputStream(), true);
                     BufferedReader fromSocket = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                    toSocket.println("Hello from Client " + i);
                    String line = fromSocket.readLine();
                    System.out.println("Response from Server: " + line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
