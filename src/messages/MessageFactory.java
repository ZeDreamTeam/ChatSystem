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
    public abstract HelloMessage deserializedHelloMessage(byte[] hello);
    public abstract HelloAckMessage deserializedHelloAckMessage(byte[] helloAck);
    public abstract MessMessage deserializedMessMessage(byte[] mess);
    public abstract MessAckMessage deserializedMessAckMessage(byte[] messAck);
    public abstract GoodbyeMessage deserializedGoodbyeMessage(byte[] bye);

    public abstract MessageType getType(byte[] message) throws IOException;


}
