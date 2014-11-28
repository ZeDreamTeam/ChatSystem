package messages;

import data.*;
import messages.data.*;

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
    public abstract AbstractMessMessage serializedMessMessage(MessMessage mess);
    public abstract AbstractMessAckMessage serializedMessAckMessage(MessAckMessage messAck);
    public abstract AbstractGoodbyeMessage serializedGoodbyeMessage(GoodbyeMessage bye);
    public abstract HelloMessage deserializedHelloMessage(AbstractHelloMessage hello);
    public abstract HelloAckMessage deserializedHelloAckMessage(AbstractHelloAckMessage helloAck);
    public abstract MessMessage deserializedMessMessage(AbstractMessMessage mess);
    public abstract MessAckMessage deserializedMessAckMessage(AbstractMessAckMessage messAck);
    public abstract GoodbyeMessage deserializedGoodbyeMessage(AbstractGoodbyeMessage bye);


}
