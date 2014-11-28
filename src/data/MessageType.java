package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public enum MessageType {
    Hello("hello"),HelloAck("helloAck"), Mess("message"), MessAck("messageAck"), Goodbye("GoodBye");
    private String s;
    MessageType(String s) {
        this.s = s;
    }

    @Override
    public String toString() {
        return s;
    }
}
