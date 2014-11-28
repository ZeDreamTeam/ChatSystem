package networking;

import data.HelloAckMessage;
import data.HelloMessage;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class ChatNI {

    public ChatNI() {

    }
    public void performSendHello(HelloMessage helloMessage) {
        //TODO : parser helloMessage
    }

    public void performSendHelloAck(HelloAckMessage helloAckMessage) {
        //TODO
    }
    public void doReceive(String message) {
        //TODO: retrieve type
        //TODO: unparse Message
    }
}
