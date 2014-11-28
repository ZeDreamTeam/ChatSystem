package messages;

import data.HelloAckMessage;
import data.HelloMessage;
import data.Message;
import messages.data.AbstractHelloAckMessage;
import messages.data.AbstractHelloMessage;
import messages.data.AbstractMessMessage;

/**
 * Created by djemaa on 28/11/14.
 */
public abstract class MessageFactory {
    public enum Type {JSON}

    public static MessageFactory getFactory(Type choice) {
        switch (choice) {
            case JSON:
                return JSONMessageFactory.getInstance();
            default:
                return null;
        }
    }
    public abstract AbstractHelloMessage serializedHelloMessage(HelloMessage hello);
    public abstract AbstractHelloAckMessage serializedHelloAckMessage(HelloAckMessage helloAck);
    public abstract AbstractMessMessage serializedMessMessage(Message mess);
    public abstract HelloMessage deserializedHelloMessage(AbstractHelloMessage hello);
    public abstract HelloAckMessage deserializedHelloAckMessage(AbstractHelloAckMessage helloAck);
    public abstract Message deserializedMessMessage(AbstractMessMessage mess);


}
