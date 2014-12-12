package networking;

import controller.Controller;
import data.*;
import messages.MessageFactory;
import messages.ParsingException;
import messages.data.*;
import utils.Logger;

import java.util.HashMap;
import java.util.Map;


/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private final Controller controller;
    private final UdpSender udpSender;
    private final UdpReceiver udpReceiver;
    private final TcpServer tcpServer;
    private final MessageFactory factory;

    private final Map<FileDescription, TcpSender> senders;

    public ChatNI(Controller contr) throws NetworkingException.ReceivingException {
        controller = contr;
        tcpServer = new TcpServer(this);
        tcpServer.start();
        udpReceiver = new UdpReceiver(this);
        udpSender = new UdpSender();

        senders = new HashMap<FileDescription, TcpSender>();

        factory = MessageFactory.getFactory(MessageFactory.Type.JSON);
        udpReceiver.start();
    }
    public void performSendHello(HelloMessage helloMessage) {
        try {
            AbstractHelloMessage hello = factory.serializedHelloMessage(helloMessage);
            byte[] messageBytes = hello.toString().getBytes();
            this.udpSender.sendBroadcast(messageBytes);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
    }

    public void performSendHelloAck(HelloAckMessage helloAckMessage, User user) {
        try {
            AbstractHelloAckMessage helloAck = factory.serializedHelloAckMessage(helloAckMessage);
            byte[] messageBytes = helloAck.toString().getBytes();
            this.udpSender.send(messageBytes, user.getIp());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
    }



    public void performSendGoodbyeMessage(GoodbyeMessage goodbyeMessage) {
        try{
            AbstractGoodbyeMessage goodbye = factory.serializedGoodbyeMessage(goodbyeMessage);
            byte[] messageBytes = goodbye.toString().getBytes();
            this.udpSender.sendBroadcast(messageBytes);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
    }
    public void performSTFU(GoodbyeMessage message, String ip){
        try{
            AbstractGoodbyeMessage goodbye = factory.serializedGoodbyeMessage(message);
            byte[] messageBytes = goodbye.toString().getBytes();
            this.udpSender.send(messageBytes, ip);
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

    public void performSendMessMessage(MessMessage messMessage, User user) {
        try {
            AbstractMessMessage message = factory.serializedMessMessage(messMessage);
            byte[] messageBytes = message.toString().getBytes();
            this.udpSender.send(messageBytes, user.getIp());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }

    }

    public void performSendMessAckMessage(MessAckMessage messAckMessage, User user) {
        try {
            AbstractMessAckMessage messageAck = factory.serializedMessAckMessage(messAckMessage);
            byte[] messageBytes = messageAck.toString().getBytes();
            this.udpSender.send(messageBytes, user.getIp());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
    }
    
    public void shutNI() {
        udpReceiver.shutdown();
        tcpServer.shutdown();

    }
    public void doReceive(byte[] bytes,String ip) {
        MessageType type = null;
        try {
            type = factory.getType(bytes);
            switch(type) {
                case hello:
                    HelloMessage mess = factory.deserializedHelloMessage(bytes);
                    controller.receiveHelloMessage(mess, ip);
                    break;
                case helloAck:
                    HelloAckMessage helloAck = factory.deserializedHelloAckMessage(bytes);
                    controller.receiveHelloAckMessage(helloAck, ip);
                    break;
                case goodBye:
                    GoodbyeMessage gbmess = factory.deserializedGoodbyeMessage(bytes);
                    controller.receiveGoodbyeMessage(gbmess, ip);
                    break;
                case message:
                    MessMessage message = factory.deserializedMessMessage(bytes);
                    controller.receiveMessMessage(message, ip);
                    break;
                case messageAck:
                    MessAckMessage messageAck = factory.deserializedMessAckMessage(bytes);
                    controller.receiveMessAckMessage(messageAck, ip);
                    break;
            }
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

    public void doSendFile(FileDescription file, String ipTo) {
        TcpSender sender = new TcpSender(this, file,ipTo);
        this.senders.put(file, sender);
        sender.start();
    }

    public void notifyFileSent(FileDescription file) {
        this.senders.remove(file);
//   TODO     this.controller.notifyFileSent(file);
    }

    public void start() {
        udpReceiver.launch();
    }

    public void doNotifyReceivingFile(String ipFrom, FileDescription file) {
        //TODO:this.controller.notifyReceivingFile(ipFrom, file);
    }

    public void doNotifyFileReceived(String ipFrom, FileDescription file) {
        //TODO:this.controller.notifyFileReceived(ipFrom, file);
    }
}
