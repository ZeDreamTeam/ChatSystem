package networking;

import utils.Conf;
import utils.Logger;

import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class UdpReceiver extends Thread {
    private final ChatNI chatNi;
    private final byte[] buffer = new byte[2048];
    private final DatagramPacket datagramPacket;
    private DatagramSocket receiveSocket;
    private boolean shouldRun = true;
    private boolean begin = false;

    public UdpReceiver(ChatNI chatNi) throws NetworkingException.ReceivingException {
        this.chatNi = chatNi;
        try {
            receiveSocket=new DatagramSocket(Conf.PORT);
            datagramPacket = new DatagramPacket(buffer, buffer.length);
        } catch (SocketException e) {
            throw new NetworkingException.ReceivingException("Error while initializing the receiving socket with port " + Conf.PORT, e);
        }
    }
    @Override
    public void run() {
        while(!begin) {
            try {
                sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        while(shouldRun) {
            try {
                receiveSocket.receive(datagramPacket);
                chatNi.doReceive(buffer, datagramPacket.getAddress().getHostAddress());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public void shutdown() {
        shouldRun = false;
        begin = true;
        receiveSocket.close();
    }
    public void launch(){
        begin = true;
    }

}
