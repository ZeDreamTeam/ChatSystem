package messages;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import data.*;
import messages.data.*;

import java.io.IOException;

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

    public abstract MessageType getType(String parsedString) throws IOException;


}
