import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

    private Socket threadSocket;

    ServerThread(Socket clientSocket) {
        this.threadSocket = clientSocket;
    }

    public void run() {

        while (true) {
            System.out.println("Waiting...");
            String message = null;

            try {
                ObjectInputStream inputs = new ObjectInputStream(threadSocket.getInputStream()); //lees input stream van socket
                message = (String) inputs.readObject();
                System.out.println("Recieved message: " + message);

                ObjectOutputStream outputs = new ObjectOutputStream(threadSocket.getOutputStream()); //maak output stream voor socket
                outputs.writeObject("Server: " + message);

                inputs.close();
                outputs.close();

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            assert message != null;
            if (message.equalsIgnoreCase("corona")) break;
        }

        System.out.println("Shut down thread!");
        try {
            threadSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}