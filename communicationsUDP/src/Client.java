import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;


public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        String ip;
        if (args.length > 0) {
            ip = args[0];
        } else ip = InetAddress.getLocalHost().getHostName();

//        String ipNico = "10.0.14.7";

        Socket socket;
        ObjectInputStream inputs;
        ObjectOutputStream outputs;

        for (int i = 0; i < 10; i++) {
            socket = new Socket(ip, 42069); // verbinding maken met server

            outputs = new ObjectOutputStream(socket.getOutputStream()); // output op socket schrijven
            System.out.println("Request sent...");
            outputs.writeObject("Client " + i);

            inputs = new ObjectInputStream(socket.getInputStream());
            String message = (String) inputs.readObject();
            System.out.println("Recieved message: " + message);

            inputs.close();
            outputs.close();
            Thread.sleep(1000);
        }
    }
}
