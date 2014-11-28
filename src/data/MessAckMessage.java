package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class MessAckMessage extends Message{
    private final static MessageType type = MessageType.MessAck;
    private final int messageNumber;

    public MessAckMessage(int messageNumber) {
        this.messageNumber = messageNumber;
    }

    @Override
    public MessageType getType() {
        return type;
    }

    public int getMessageNumer() {
        return messageNumber;
    }
}
