package networking;

import data.FileDescription;
import sun.swing.text.TextComponentPrintable;
import utils.Logger;

import java.io.*;
import java.net.Socket;

public class TcpReceiver extends Thread{
    private final TcpServer tcpServer;
    private final Socket clientSocket;
    private final String clientIp;
    private boolean shouldRun;

    public TcpReceiver(TcpServer tcpServer, Socket socket ) {
        this.clientSocket = socket;
        this.tcpServer=tcpServer;
        this.clientIp=clientSocket.getRemoteSocketAddress().toString();
        this.shouldRun = true;
    }

    @Override
    public void run() {
        try {
            InputStream in = clientSocket.getInputStream();
            DataInputStream dis = new DataInputStream(in);
            //Read fileName and file size
            String fileName = sanitizeFileName(dis.readUTF());
            long fileSize = dis.readLong();
            FileDescription file = new FileDescription(fileName,fileSize,"/tmp/" +fileName);
            tcpServer.receivingFile(clientIp, file);
            OutputStream outputStream = new FileOutputStream("/tmp/"+fileName);
            long readCounter = fileSize;
            byte[] buffer = new byte[2048];
            int nbBytesRead;
            Logger.log("Receiving file " + file.getName());
            //Receive the file
            while(shouldRun && (readCounter > 0) && ((nbBytesRead = dis.read(buffer, 0, (int) Math.min(readCounter, buffer.length)))!=-1)) {
                outputStream.write(buffer,0,nbBytesRead);
                readCounter -=nbBytesRead;
                Logger.log("Read " + nbBytesRead + " bytes");
            }
            outputStream.close();
            dis.close();
            in.close();
            Logger.log("Finished reading");
            tcpServer.fileReceived(clientIp, file);
            clientSocket.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shutdown() {
        try {
            this.clientSocket.close();
        } catch (IOException ignored) {}
        this.shouldRun = false;
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replace("\\", "_").replace("/","_");
    }
}
