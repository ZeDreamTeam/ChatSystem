package networking;

import data.FileDescription;
import sun.swing.text.TextComponentPrintable;

import java.io.*;
import java.net.Socket;

public class TcpReceiver extends Thread{
    private final TcpServer tcpServer;
    private final Socket clientSocket;
    private final String clientIp;
    public TcpReceiver(Socket socket, TcpServer tcpServer) {
        this.clientSocket = socket;
        this.tcpServer=tcpServer;
        this.clientIp=clientSocket.getRemoteSocketAddress().toString();
    }

    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            //Read fileName and file size
            String fileName = dis.readUTF();
            long fileSize = dis.readLong();
            FileDescription file = new FileDescription(fileName,fileSize,"/tmp/" +fileName);
            tcpServer.receivingFile(clientIp, file);
            OutputStream outputStream = new FileOutputStream("/tmp/"+fileName);
            long readCounter = fileSize;
            byte[] buffer = new byte[2048];
            int nbBytesRead;
            //Receive the file
            while((readCounter > 0) && ((nbBytesRead = dis.read(buffer, 0, (int) Math.min(readCounter, buffer.length)))!=-1)) {
                outputStream.write(buffer,0,nbBytesRead);
                readCounter -=nbBytesRead;
            }
            outputStream.close();
            dis.close();
            in.close();
            tcpServer.fileReceived(clientIp, file);
            clientSocket.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
