package networking;

import utils.Conf;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpReceiver extends Thread {
    private final ChatNI chatNi;
    private final DatagramPacket datagramPacket;
    private final byte[] buffer;
    private DatagramSocket receiveSocket;
    public UdpReceiver(ChatNI chatNi) throws SocketException {
        this.chatNi = chatNi;
        receiveSocket=new DatagramSocket(Conf.PORT);
        this.buffer = new byte[2048];
        this.datagramPacket = new DatagramPacket(buffer, buffer.length);
    }
    @Override
    public void run() {
        while(true) {
            try {
                receiveSocket.receive(datagramPacket);
                chatNi.doReceive(buffer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
