package messages.data;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONGoodbyeMessage extends AbstractGoodbyeMessage {
    String jSONGoodbye;
    public JSONGoodbyeMessage(String jSONString){
        jSONGoodbye = jSONString;
    }

    @Override
    public String toString() {
        return jSONGoodbye;
    }
}
