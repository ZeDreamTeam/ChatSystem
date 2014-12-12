package networking;

import utils.Conf;
import utils.Logger;

import javax.security.auth.login.Configuration;
import java.io.IOException;
import java.net.*;

import static networking.NetworkingException.*;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpSender {
    public UdpSender() {

    }

    public void sendBroadcast(byte[] bytes) throws NetworkingException.SendingException{
        try {
            DatagramSocket socket = new DatagramSocket(/*Conf.PORT, InetAddress.getByName("255.255.255.255")*/);
            socket.setBroadcast(true);
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, InetAddress.getByName("10.1.255.255"), Conf.PORT);
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            throw new NetworkingException.SendingException("Error sending a broadcast message", e);
        }
    }

    public void send(byte[] bytes, String ip) throws SendingException {
        InetAddress address;
        try {
            address = InetAddress.getByName(ip);
            DatagramPacket packet = new DatagramPacket(bytes,bytes.length, address, Conf.PORT);

            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
        } catch (IOException e) {
            throw new SendingException("Error sending message to " + ip, e);
        }
    }
}
