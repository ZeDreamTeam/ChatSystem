package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloMessage extends Message {
    private static final MessageType type=MessageType.Hello;//TODO: change to enum Type
    private String userName;

    @Override
    public MessageType getType() {
        return type;
    }
}
