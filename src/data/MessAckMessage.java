package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class MessAckMessage extends Message{
    private final static MessageType type = MessageType.messageAck;
    public static final String FIELD_MESSAGE_NUMBER = "messageNumber";
    private final int messageNumber;

    public MessAckMessage(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "ack"+messageNumber;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

}
