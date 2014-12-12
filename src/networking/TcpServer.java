package networking;

import data.FileDescription;
import utils.Conf;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer extends Thread{
    private final ChatNI chatNI;
    private InetSocketAddress localSockAddr;
    private ServerSocket socket;
    private boolean shouldRun;

    public TcpServer(ChatNI chatNI) {
        this.chatNI = chatNI;
        this.localSockAddr = new InetSocketAddress(Conf.PORT);
        this.shouldRun=true;
        try {
            socket = new ServerSocket(Conf.PORT);
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(shouldRun) {
            if (this.socket.isBound()) {
                try {
                    Socket sock = this.socket.accept();
                    //Call the new socket with
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        this.shouldRun=false;
    }

    public void receivingFile(String ipFrom, FileDescription file) {
        this.chatNI.doNotifyReceivingFile(ipFrom, file);
    }

    public void fileReceived(String ipFrom, FileDescription file) {
        this.chatNI.doNotifyFileReceived(ipFrom, file);
    }
}
