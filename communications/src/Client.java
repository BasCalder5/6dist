import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Client {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        InetAddress hostip = InetAddress.getLocalHost();
        Socket socket;
        ObjectInputStream inputs;
        ObjectOutputStream outputs;

        for (int i = 0; i < 5; i++) {
            socket = new Socket(hostip.getHostName(), 42069); // verbinding maken met server

            outputs = new ObjectOutputStream(socket.getOutputStream()); // output op socket schrijven
            System.out.println("Request sent...");
            if(i==4) {
                outputs.writeObject("challas");
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
