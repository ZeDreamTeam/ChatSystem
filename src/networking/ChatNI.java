package networking;

import controller.Controller;
import data.*;
import messages.MessageFactory;
import messages.ParsingException;
import messages.data.AbstractGoodbyeMessage;
import messages.data.AbstractHelloAckMessage;
import messages.data.AbstractHelloMessage;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private final Controller controller;
    private final UdpSender udpSender;
    private final UdpReceiver udpReceiver;
    private final MessageFactory factory;
    public ChatNI(Controller contr) throws NetworkingException.ReceivingException {
        controller = contr;
        factory = MessageFactory.getFactory(MessageFactory.Type.JSON);
        this.udpReceiver = new UdpReceiver(this);
        this.udpSender = new UdpSender();
        udpReceiver.start();
    }
    public String performSendHello(HelloMessage helloMessage) {
        AbstractHelloMessage message = null;
        String myAdress = null;
        try {
            message = factory.serializedHelloMessage(helloMessage);
            byte[] messageBytes = message.toString().getBytes();
            myAdress = this.udpSender.sendBroadcast(messageBytes);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
        return myAdress;
    }

    public void performSendHelloAck(HelloAckMessage helloAckMessage, User user) {
        AbstractHelloAckMessage helloAck = null;
        try {
            helloAck = factory.serializedHelloAckMessage(helloAckMessage);
            byte[] messageBytes = helloAck.toString().getBytes();
            this.udpSender.send(messageBytes, user.getIp());
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }
    }



    public void performSendGoodbyeMessage(GoodbyeMessage message) {
        //TODO
    }
    public void performSTFU(GoodbyeMessage message, String ip){
        AbstractGoodbyeMessage goodbye = null;
        try{
            goodbye = factory.serializedGoodbyeMessage(message);
            byte[] messageBytes = goodbye.toString().getBytes();
            this.udpSender.send(messageBytes, ip);
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }

    public void performSendMessMessage(MessMessage message) {

    }

    public void performSendMessAckMessage(MessAckMessage message) {

    }
    
    public void shutNI() {
        udpReceiver.setShouldRun(false);
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
}
