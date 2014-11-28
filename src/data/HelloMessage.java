package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloMessage extends Message {
    private static final MessageType type=MessageType.Hello;//TODO: change to enum Type
    private final String userName;

    public HelloMessage(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    @Override
    public MessageType getType() {
        return type;
    }
}
