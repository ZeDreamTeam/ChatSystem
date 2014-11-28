package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public enum MessageType {
    hello("hello"),helloAck("helloAck"), message("message"), messageAck("messageAck"), goodBye("goodBye");
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
            return hello;
        } else if (type.equals("helloAck")){
            return helloAck;
        } else if (type.equals("message")){
            return message;
        } else if (type.equals("messageAck")){
            return messageAck;
        } else if (type.equals("goodBye")){
            return goodBye;
        }
        return null;
    }
}
