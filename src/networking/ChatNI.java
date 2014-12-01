package networking;

import controller.Controller;
import data.*;
import messages.MessageFactory;
import messages.ParsingException;
import messages.data.*;
import utils.Logger;


/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private final Controller controller;
    private final UdpSender udpSender;
    private final UdpReceiver udpReceiver;
    private final MessageFactory factory;


    public ChatNI(Controller contr) throws NetworkingException.ReceivingException {
        Logger.log("ChatNI");
        controller = contr;
        factory = MessageFactory.getFactory(MessageFactory.Type.JSON);
        this.udpReceiver = new UdpReceiver(this);
        this.udpSender = new UdpSender();
        udpReceiver.start();
    }
    public void performSendHello(HelloMessage helloMessage) {
        Logger.log("ChatNI.performSendHello");
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
        Logger.log("ChatNI.performSendHelloAck");
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
        Logger.log("ChatNI.performSendGoodbyeMessage");
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
        Logger.log("ChatNI.performSTFU");
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
        Logger.log("ChatNI.performSendMessMessage");
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
        Logger.log("ChatNI.performSendMessAckMessage");
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
        Logger.log("ChatNI.shutNI");
        udpReceiver.setShouldRun(false);
    }
    public void doReceive(byte[] bytes,String ip) {
        Logger.log("ChatNI.doReceive");
        MessageType type = null;
        try {
            type = factory.getType(bytes);
            switch(type) {
                case hello:
                    HelloMessage mess = factory.deserializedHelloMessage(bytes);
                    controller.receiveHelloMessage(mess, ip);
                    System.out.println("Received Hello from " + mess.getUserName());
                    break;
                case helloAck:
                    HelloAckMessage helloAck = factory.deserializedHelloAckMessage(bytes);
                    controller.receiveHelloAckMessage(helloAck, ip);
                    System.out.println("Received HelloAck from " + helloAck.getUserName());
                    break;
                case goodBye:
                    GoodbyeMessage gbmess = factory.deserializedGoodbyeMessage(bytes);
                    controller.receiveGoodbyeMessage(gbmess, ip);
                    System.out.println("Received GB ");
                    break;
                case message:
                    MessMessage message = factory.deserializedMessMessage(bytes);
                    controller.receiveMessMessage(message, ip);
                    System.out.println("Received mess: " + message.getMessageData());
                    break;
                case messageAck:
                    MessAckMessage messageAck = factory.deserializedMessAckMessage(bytes);
                    controller.receiveMessAckMessage(messageAck, ip);
                    System.out.println("Received messageAck : " + messageAck.getMessageNumer());
                    break;
            }
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
