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
    private boolean shouldRun;

    public UdpReceiver(ChatNI chatNi) throws NetworkingException.ReceivingException {
        this.chatNi = chatNi;
        try {
            receiveSocket=new DatagramSocket(Conf.PORT);
        } catch (SocketException e) {
            throw new NetworkingException.ReceivingException("Error while initializing the receiving socket with port " + Conf.PORT, e);
        }
        this.buffer = new byte[2048];
        this.datagramPacket = new DatagramPacket(buffer, buffer.length);
        this.shouldRun=true;
    }
    @Override
    public void run() {
        while(shouldRun) {
            try {
                receiveSocket.receive(datagramPacket);
                chatNi.doReceive(buffer,datagramPacket.getAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        receiveSocket.close();
    }
    public void setShouldRun(boolean shouldRun){
        this.shouldRun = shouldRun;
    }
}
