package messages.data;

/**
 * Created by djemaa on 28/11/14.
 */
public class JSONMessMessage extends AbstractMessMessage {
    String jSONMess;
    public JSONMessMessage(String jSONString){
        jSONMess = jSONString;
    }

    @Override
    public String toString() {
        return jSONMess;
    }
}
