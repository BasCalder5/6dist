import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    private static ServerSocket serverSocket;
    private static int port = 42069;
    private static ServerThread serverThread;
    private static Thread thread;

    public static void main(String[] args) throws IOException {
        serverSocket = new ServerSocket(port);

        System.out.println("Server started...");

        while (true) {
            serverThread = new ServerThread(serverSocket.accept());
            System.out.println("sending socket to thread");
            thread = new Thread();
            thread.start();
        }
    }

    protected void finalize() throws IOException {
        serverSocket.close();
        System.out.println("Server socket closed");
    }
}
