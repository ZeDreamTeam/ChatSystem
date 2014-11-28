package networking;

import java.io.IOException;
import java.net.*;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpSender {
    public static final int PORT = 1337;
    public UdpSender() {

    }

    public void send(byte[] bytes, String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length, address, PORT);

        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        socket.close();
    }
}
