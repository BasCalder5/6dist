import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServerUDP {

    private static DatagramSocket socket;
    private static int port = 32000;

    public static void main(String[] args) throws IOException {

        socket = new DatagramSocket(port);
        socket.setReuseAddress(true);

        System.out.println("Server started...");

        byte[] buf = new byte[1024];
        DatagramPacket packet = new DatagramPacket(buf, buf.length);
        socket.receive(packet);
        String message = new String(packet.getData(), 0, packet.getLength());

        System.out.println(message);

        socket.close();
    }
}
