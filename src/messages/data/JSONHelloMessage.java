package messages.data;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONHelloMessage extends AbstractHelloMessage{
    String jSONHello;
    public JSONHelloMessage(String jSONString){
        jSONHello = jSONString;
    }


    @Override
    public String toString() {
        return jSONHello;
    }
}
