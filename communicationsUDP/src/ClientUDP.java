import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class ClientUDP {

    public static void main(String[] args) throws IOException {
        String ip;
        if (args.length > 0) {
            ip = args[0];
        } else ip = InetAddress.getLocalHost().getHostName();

        DatagramSocket socket = new DatagramSocket();

        String message = "sending message to: " + ip;

        DatagramPacket packet = new DatagramPacket(message.getBytes(),message.length(), InetAddress.getByName(ip),32000);

        socket.send(packet);
        socket.close();
    }
}
