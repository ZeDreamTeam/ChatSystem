package messages;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import data.*;
import messages.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    public AbstractHelloMessage serializedHelloMessage(HelloMessage hello) {
        AbstractHelloMessage ret = null;
        try {
            ret = new JSONHelloMessage(mapper.writeValueAsString(hello));
        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public AbstractHelloAckMessage serializedHelloAckMessage(HelloAckMessage helloAck) {
        AbstractHelloAckMessage ret = null;
        try {
            ret = new JSONHelloAckMessage(mapper.writeValueAsString(helloAck));
        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public AbstractMessMessage serializedMessMessage(MessMessage mess) {
        AbstractMessMessage ret = null;
        try {
            ret = new JSONMessMessage(mapper.writeValueAsString(mess));
        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public AbstractMessAckMessage serializedMessAckMessage(MessAckMessage messAck) {
        AbstractMessAckMessage ret = null;
        try {
            ret = new JSONMessAckMessage(mapper.writeValueAsString(messAck));
        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public AbstractGoodbyeMessage serializedGoodbyeMessage(GoodbyeMessage bye) {
        AbstractGoodbyeMessage ret = null;
        try {
            ret = new JSONGoodbyeMessage(mapper.writeValueAsString(bye));
        }catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public HelloMessage deserializedHelloMessage(byte[] hello) {
        HelloMessage ret = null;
        try{

            ret = mapper.readValue(new String(hello,0, hello.length), HelloMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public HelloAckMessage deserializedHelloAckMessage(byte[] helloAck) {
        HelloAckMessage ret = null;
        try{

            ret = mapper.readValue(new String(helloAck, 0, helloAck.length), HelloAckMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public MessMessage deserializedMessMessage(byte[] mess) {
        MessMessage ret = null;
        try{

            ret = mapper.readValue(new String(mess,0, mess.length), MessMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public MessAckMessage deserializedMessAckMessage(byte[] messAck) {
        MessAckMessage ret = null;
        try{

            ret = mapper.readValue(new String(messAck, 0, messAck.length), MessAckMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public GoodbyeMessage deserializedGoodbyeMessage(byte[] bye) {
        GoodbyeMessage ret = null;
        try{

            ret = mapper.readValue(new String(bye, 0, bye.length), GoodbyeMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }

    @Override
    public MessageType getType(byte[] parsedString) throws IOException {
        MessageType type = null;
        try {
            Map<String, String> map = mapper.readValue(new String(parsedString,0, parsedString.length), Map.class);
            type = MessageType.fromString(map.get("type"));
        } catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return type;
    }
}
