package networking;

import data.*;
import messages.MessageFactory;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {
    private MessageFactory factory;
    public ChatNI() {
    }
    public void performSendHello(HelloMessage helloMessage) {
        //TODO : parser helloMessage
    }

    public void performSendHelloAck(HelloAckMessage helloAckMessage) {
        //TODO
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
