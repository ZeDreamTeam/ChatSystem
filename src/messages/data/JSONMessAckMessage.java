package messages.data;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONMessAckMessage extends AbstractMessAckMessage {
    String jSONMessAck;
    public JSONMessAckMessage(String jSONString){
        jSONMessAck = jSONString;
    }

    @Override
    public String toString() {
        return jSONMessAck;
    }
}
