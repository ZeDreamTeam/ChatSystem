package messages;

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


    public abstract AbstractHelloMessage serializedHelloMessage(HelloMessage hello) throws ParsingException;
    public abstract AbstractHelloAckMessage serializedHelloAckMessage(HelloAckMessage helloAck) throws ParsingException;
    public abstract AbstractMessMessage serializedMessMessage(MessMessage mess) throws ParsingException;
    public abstract AbstractMessAckMessage serializedMessAckMessage(MessAckMessage messAck) throws ParsingException;
    public abstract AbstractGoodbyeMessage serializedGoodbyeMessage(GoodbyeMessage bye) throws ParsingException;
    public abstract HelloMessage deserializedHelloMessage(byte[] hello) throws ParsingException;
    public abstract HelloAckMessage deserializedHelloAckMessage(byte[] helloAck) throws ParsingException;
    public abstract MessMessage deserializedMessMessage(byte[] mess) throws ParsingException;
    public abstract MessAckMessage deserializedMessAckMessage(byte[] messAck) throws ParsingException;
    public abstract GoodbyeMessage deserializedGoodbyeMessage(byte[] bye) throws ParsingException;

    public abstract MessageType getType(byte[] message) throws ParsingException.JSONUnParsingException;


}
