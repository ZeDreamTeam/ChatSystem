package networking;

import utils.Conf;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.net.*;
/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpSender {
    public UdpSender() {

    }

    public void send(byte[] bytes, String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        DatagramPacket packet = new DatagramPacket(bytes,bytes.length, address, Conf.PORT);

        DatagramSocket socket = new DatagramSocket();
        socket.send(packet);
        socket.close();
    }
}
