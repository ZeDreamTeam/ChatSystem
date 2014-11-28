package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloAckMessage extends Message{
    private final static MessageType type =MessageType.HelloAck;
    private final String userName;
    public HelloAckMessage(String name) {
        this.userName=name;
    }

    public String getUserName() {
        return userName;
    }
    @Override
    public MessageType getType() {
        return type;
    }
}
