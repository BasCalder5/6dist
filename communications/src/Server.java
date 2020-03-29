import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private static ServerSocket piServer;
    private static int port = 42069;

    public static void main(String args[]) throws IOException, ClassNotFoundException {
        piServer = new ServerSocket(port); // server socket aanmaken

        while (true) {
            System.out.println("Waiting...");
            Socket socket = piServer.accept();

            ObjectInputStream inputs = new ObjectInputStream(socket.getInputStream()); //lees input stream van socket
            String message = (String) inputs.readObject();
            System.out.println("Recieved message: " + message);

            ObjectOutputStream outputs = new ObjectOutputStream(socket.getOutputStream()); //maak output stream voor socket
            outputs.writeObject("Hello client: " + message);

            inputs.close();
            outputs.close();
            socket.close();

            if (message.equalsIgnoreCase("corona")) break;
        }
        System.out.println("Shut down!");
        piServer.close();
    }
}
