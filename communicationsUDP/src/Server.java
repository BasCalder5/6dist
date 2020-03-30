import java.io.IOException;
import java.net.ServerSocket;

@SuppressWarnings("InfiniteLoopStatement")

public class Server {

    private static ServerSocket serverSocket;
    private static int port = 42069;
    private static ServerThread serverThread;

    public static void main(String[] args) throws IOException {

        serverSocket = new ServerSocket(port);
        serverSocket.setReuseAddress(true);

        System.out.println("Server started...");

        while (true) {
            serverThread = new ServerThread(serverSocket.accept());
            new Thread(serverThread).start();

        }
    }
}
