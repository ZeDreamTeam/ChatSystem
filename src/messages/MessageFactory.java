package messages;

import messages.data.AbstractHelloMessage;
import messages.data.MessageType;

/**
 * Created by djemaa on 28/11/14.
 */
public abstract class MessageFactory {
    MessageType getType(){
        MessageType ret;
        return ret;
    }
    public abstract AbstractHelloMessage createHelloMessage(HelloMessage )
}
