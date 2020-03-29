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
                outputs.writeObject("hej hej: " + message);

                inputs.close();
                outputs.close();
                threadSocket.close();

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

//    public static void main(String args[]) throws IOException, ClassNotFoundException {
//
//
//        while (true) {
//            System.out.println("Waiting...");
//            Socket socket = piServer.accept();
//
//            ObjectInputStream inputs = new ObjectInputStream(socket.getInputStream()); //lees input stream van socket
//            String message = (String) inputs.readObject();
//            System.out.println("Recieved message: " + message);
//
//            ObjectOutputStream outputs = new ObjectOutputStream(socket.getOutputStream()); //maak output stream voor socket
//            outputs.writeObject("hej hej: " + message);
//
//            inputs.close();
//            outputs.close();
//            socket.close();
//
//            if (message.equalsIgnoreCase("corona")) break;
//        }
//        System.out.println("Shut down!");
//        piServer.close();
//    }

