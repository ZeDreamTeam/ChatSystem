package networking;

import data.FileDescription;
import utils.Conf;
import utils.Logger;

import java.io.*;
import java.net.Socket;

public class TcpSender extends Thread {
    private final ChatNI chatNI;

    private FileDescription fileDescription;
    private Socket clientSocket;

    public TcpSender(ChatNI chatNI, FileDescription file, String ipTo) throws NetworkingException.SendingException {
        this.chatNI = chatNI;
        this.fileDescription = file;
        Logger.log("Starting sending file");
        try {
            this.clientSocket = new Socket(ipTo, Conf.PORT);
        } catch (IOException e) {
            throw new NetworkingException.SendingException("Couldn't start client socket to ip " + ipTo + " and port " + Conf.PORT, e);
        }
    }

    @Override
    public void run() {
        try {
            byte[] fileBytes = new byte[(int) fileDescription.getSize()];
            FileInputStream fis = new FileInputStream(fileDescription.getFile());
            BufferedInputStream inputStream = new BufferedInputStream(fis);
            DataInputStream dataInputStream = new DataInputStream(inputStream);
            dataInputStream.readFully(fileBytes, 0, fileBytes.length);

            dataInputStream.close();
            inputStream.close();
            fis.close();
            Logger.log("Sending file " + fileDescription.getName() + "...");
            OutputStream outputStream = clientSocket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            dataOutputStream.writeUTF(fileDescription.getName());
            dataOutputStream.writeLong(fileDescription.getSize());
            dataOutputStream.flush();
            dataOutputStream.write(fileBytes, 0, fileBytes.length);
            dataOutputStream.flush();

            dataOutputStream.close();
            outputStream.close();
            Logger.log("... file Sent!");
            chatNI.notifyFileSent(fileDescription);

            clientSocket.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
