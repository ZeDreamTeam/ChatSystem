package messages;

import com.fasterxml.jackson.databind.JsonNode;
import data.*;
import messages.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONMessageFactory extends MessageFactory {
    private ObjectMapper mapper =  new ObjectMapper();
    private static JSONMessageFactory instance = null;


    private JSONMessageFactory(){

    }

    public static synchronized JSONMessageFactory getInstance(){
        if (instance == null){
            instance = new JSONMessageFactory();
        }
        return instance;
    }


    @Override
    public AbstractHelloMessage serializedHelloMessage(HelloMessage hello) throws ParsingException.JSONParsingException {
        AbstractHelloMessage ret;
        try {
            ret = new JSONHelloMessage(mapper.writeValueAsString(hello));
        }catch (Exception e) {
            throw new ParsingException.JSONParsingException("Error parsing Hello message", e);

        }
        return ret;
    }

    @Override
    public AbstractHelloAckMessage serializedHelloAckMessage(HelloAckMessage helloAck) throws ParsingException.JSONParsingException {
        AbstractHelloAckMessage ret;
        try {
            ret = new JSONHelloAckMessage(mapper.writeValueAsString(helloAck));
        }catch (Exception e) {
            throw new ParsingException.JSONParsingException("Error parsing HelloAck message", e);

        }
        return ret;
    }

    @Override
    public AbstractMessMessage serializedMessMessage(MessMessage mess) throws ParsingException.JSONParsingException {
        AbstractMessMessage ret;
        try {
            ret = new JSONMessMessage(mapper.writeValueAsString(mess));
        }catch (Exception e) {
            throw new ParsingException.JSONParsingException("Error parsing Mess (data) message", e);

        }
        return ret;
    }

    @Override
    public AbstractMessAckMessage serializedMessAckMessage(MessAckMessage messAck) throws ParsingException.JSONParsingException {
        AbstractMessAckMessage ret;
        try {
            ret = new JSONMessAckMessage(mapper.writeValueAsString(messAck));
        }catch (Exception e) {
            throw new ParsingException.JSONParsingException("Error parsing Message ack message", e);

        }
        return ret;
    }

    @Override
    public AbstractGoodbyeMessage serializedGoodbyeMessage(GoodbyeMessage bye) throws ParsingException.JSONParsingException {
        AbstractGoodbyeMessage ret;
        try {
            ret = new JSONGoodbyeMessage(mapper.writeValueAsString(bye));
        }catch (Exception e) {
            throw new ParsingException.JSONParsingException("Error parsing Goodbye message", e);
        }
        return ret;
    }

    @Override
    public HelloMessage deserializedHelloMessage(byte[] hello) throws ParsingException.JSONUnParsingException {
        HelloMessage ret;
        try{
            JsonNode root = mapper.readTree(hello);
            ret = new HelloMessage(root.get(HelloMessage.FIELD_USERNAME).toString());
        }catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error unserializing Hello message", e);
        }
        return ret;
    }

    @Override
    public HelloAckMessage deserializedHelloAckMessage(byte[] helloAck) throws ParsingException.JSONUnParsingException {
        HelloAckMessage ret;
        try{
            JsonNode root = mapper.readTree(helloAck);
            ret = new HelloAckMessage(root.get(HelloAckMessage.FIELD_USERNAME).toString());
        }catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error unserializing HelloAck message", e);
        }
        return ret;
    }

    @Override
    public MessMessage deserializedMessMessage(byte[] mess) throws ParsingException.JSONUnParsingException {
        MessMessage ret;
        try{
            JsonNode root = mapper.readTree(mess);
            ret = new MessMessage(root.get(MessMessage.FIELD_MESS_NUMBER).asInt(), root.get(MessMessage.FIELD_MESS_DATA).toString());
        }catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error unserializing Mess (data) message", e);
        }
        return ret;
    }

    @Override
    public MessAckMessage deserializedMessAckMessage(byte[] messAck) throws ParsingException.JSONUnParsingException {
        MessAckMessage ret;
        try{
            JsonNode root = mapper.readTree(messAck);
            ret = new MessAckMessage(root.get(MessAckMessage.FIELD_MESSAGE_NUMBER).asInt());
        } catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error unserializing Mess ack message", e);
        }
        return ret;
    }

    @Override
    public GoodbyeMessage deserializedGoodbyeMessage(byte[] bye) throws ParsingException.JSONUnParsingException {
        GoodbyeMessage ret;
        try{
            ret = new GoodbyeMessage();
        } catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error unserializing Goodbye message", e);
        }
        return ret;
    }

    @Override
    public MessageType getType(byte[] parsedString) throws ParsingException.JSONUnParsingException {
        MessageType type;
        try {
            Map map = mapper.readValue(new String(parsedString, 0, parsedString.length), Map.class);
            type = MessageType.fromString(map.get("type"));
        }catch (Exception e) {
            throw new ParsingException.JSONUnParsingException("Error trying to parse the string message", e);
        }
        return type;
    }
}
