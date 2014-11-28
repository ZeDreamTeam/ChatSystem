package messages;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import data.*;
import messages.data.*;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONMessageFactory extends MessageFactory {
    private static JSONMessageFactory instance = null;
    private ObjectMapper mapper = new ObjectMapper();

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
    public HelloMessage deserializedHelloMessage(AbstractHelloMessage hello) {
        HelloMessage ret = null;
        try{

            ret = mapper.readValue(hello.toString(), HelloMessage.class);
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
    public HelloAckMessage deserializedHelloAckMessage(AbstractHelloAckMessage helloAck) {
        HelloAckMessage ret = null;
        try{

            ret = mapper.readValue(helloAck.toString(), HelloAckMessage.class);
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
    public MessMessage deserializedMessMessage(AbstractMessMessage mess) {
        MessMessage ret = null;
        try{

            ret = mapper.readValue(mess.toString(), MessMessage.class);
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
    public MessAckMessage deserializedMessAckMessage(AbstractMessAckMessage messAck) {
        MessAckMessage ret = null;
        try{

            ret = mapper.readValue(messAck.toString(), MessAckMessage.class);
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
    public GoodbyeMessage deserializedGoodbyeMessage(AbstractGoodbyeMessage bye) {
        GoodbyeMessage ret = null;
        try{

            ret = mapper.readValue(bye.toString(), GoodbyeMessage.class);
        }  catch (JsonGenerationException e) {

            e.printStackTrace();

        } catch (JsonMappingException e) {

            e.printStackTrace();

        } catch (IOException e) {

            e.printStackTrace();

        }
        return ret;
    }
}
