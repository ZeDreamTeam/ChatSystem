package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class MessMessage extends Message{
    private final static MessageType type = MessageType.message;
    public static final String FIELD_MESS_NUMBER = "messageNumber";
    public static final String FIELD_MESS_DATA = "messageData";
    private final int messageNumber;
    private final String messageData;

    public MessMessage(int messageNumber, String messageData) {
        this.messageNumber = messageNumber;
        this.messageData = messageData;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getMessageData() {
        return messageData;
    }

    @Override
    public MessageType getType() {
        return type;
    }
}
