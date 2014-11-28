package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public enum MessageType {
    Hello("hello"),HelloAck("helloAck"), Mess("message"), MessAck("messageAck"), Goodbye("goodBye");
    private String s;
    MessageType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }

    public static MessageType fromString(String type){
        if(type.equals("hello")){
            return Hello;
        } else if (type.equals("helloAck")){
            return HelloAck;
        } else if (type.equals("message")){
            return Mess;
        } else if (type.equals("messageAck")){
            return MessAck;
        } else if (type.equals("goodBye")){
            return Goodbye;
        }
        return null;
    }
}
