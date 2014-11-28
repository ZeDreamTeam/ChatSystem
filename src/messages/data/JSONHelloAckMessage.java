package messages.data;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONHelloAckMessage extends AbstractHelloAckMessage {
    String jSONHelloAck;
    public JSONHelloAckMessage(String jSONString){
        jSONHelloAck = jSONString;
    }
    @Override
    public String toString() {
        return jSONHelloAck;
    }
}
