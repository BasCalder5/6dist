import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress hostip = InetAddress.getLocalHost();
        String ipNico = "143.129.39.106:30032";
        Socket socket;
        ObjectInputStream inputs;
        ObjectOutputStream outputs;

        for (int i = 0; i < 5; i++) {
            socket = new Socket(ipNico, 1998); // verbinding maken met server

            outputs = new ObjectOutputStream(socket.getOutputStream()); // output op socket schrijven
            System.out.println("Request sent...");
            if(i==4) {
                outputs.writeObject("oh no corona");
            }
            else outputs.writeObject("Nico werkt het? "+i);

            inputs = new ObjectInputStream(socket.getInputStream());
            String message = (String) inputs.readObject();
            System.out.println("Recieved message: " + message);

            inputs.close();
            outputs.close();
            Thread.sleep(100);
        }
    }
}
