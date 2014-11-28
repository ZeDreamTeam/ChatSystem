package networking;

import data.*;
import messages.MessageFactory;
import messages.data.AbstractHelloAckMessage;
import messages.data.AbstractHelloMessage;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private final UdpSender udpSender;
//    private final UdpReceiver udpReceiver;
    private final MessageFactory factory;
    public ChatNI() throws SocketException {
        factory = MessageFactory.getFactory(MessageFactory.Type.JSON);
        //this.udpReceiver = new UdpReceiver(this);
        this.udpSender = new UdpSender();
    }
    public void performSendHello(HelloMessage helloMessage) throws IOException {
        AbstractHelloMessage message = factory.serializedHelloMessage(helloMessage);
        byte[] messageBytes = message.toString().getBytes();

        this.udpSender.sendBroadcast(messageBytes);

    }

    public void performSendHelloAck(HelloAckMessage helloAckMessage, User user) throws IOException {
        AbstractHelloAckMessage helloAck = factory.serializedHelloAckMessage(helloAckMessage);
        byte[] messageBytes = helloAck.toString().getBytes();
        this.udpSender.send(messageBytes, user.getIp());
    }



    public void performSendGoodbyeMessage(GoodbyeMessage message) {
        //TODO
    }

    public void performSendMessMessage(MessMessage message) {

    }

    public void performSendMessAckMessage(MessAckMessage message) {

    }

    public void doReceive(String message) {
        //TODO: retrieve type
        //TODO: unparse Message
    }
}
