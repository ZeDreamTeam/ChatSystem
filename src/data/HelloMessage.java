package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloMessage extends Message {
    private static final MessageType type=MessageType.hello;
    public static final String FIELD_USERNAME = "userName";
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

    @Override
    public String toString() {
        return null;
    }
}
