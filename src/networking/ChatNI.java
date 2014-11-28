package networking;

import data.*;
import messages.MessageFactory;
import messages.ParsingException;
import messages.data.AbstractHelloAckMessage;
import messages.data.AbstractHelloMessage;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private final UdpSender udpSender;
    private final UdpReceiver udpReceiver;
    private final MessageFactory factory;
    public ChatNI() throws NetworkingException.ReceivingException {
        factory = MessageFactory.getFactory(MessageFactory.Type.JSON);
        this.udpReceiver = new UdpReceiver(this);
        this.udpSender = new UdpSender();
        udpReceiver.start();
    }
    public void performSendHello(HelloMessage helloMessage) {
        AbstractHelloMessage message = null;
        try {
            message = factory.serializedHelloMessage(helloMessage);
            byte[] messageBytes = message.toString().getBytes();
            this.udpSender.sendBroadcast(messageBytes);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (NetworkingException.SendingException e) {
            e.printStackTrace();
        }


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

    public void performSendMessMessage(MessMessage message) {

    }

    public void performSendMessAckMessage(MessAckMessage message) {

    }
    
    public void shutNI() {
        udpReceiver.setShouldRun(false);
    }
    public void doReceive(byte[] bytes) {
        MessageType type = null;
        try {
            type = factory.getType(bytes);
            switch(type) {
                case hello:
                    HelloMessage mess = factory.deserializedHelloMessage(bytes);
                    break;
                case helloAck:
                    HelloAckMessage helloAck = factory.deserializedHelloAckMessage(bytes);
                case goodBye:
                    GoodbyeMessage gbmess = factory.deserializedGoodbyeMessage(bytes);
                    break;
                case message:
                    MessMessage message = factory.deserializedMessMessage(bytes);
                    break;
                case messageAck:
                    MessAckMessage messageAck = factory.deserializedMessAckMessage(bytes);
                    break;
            }
        } catch (ParsingException e) {
            e.printStackTrace();
        }
    }
}
