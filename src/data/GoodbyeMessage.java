package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class GoodbyeMessage extends Message {
    private final static MessageType type = MessageType.goodBye;

    @Override
    public MessageType getType() {
        return type;
    }
}
