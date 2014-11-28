package data;

import java.util.Date;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HistMessage {
    private final Message message;
    private final boolean isSentByUs;
    private final Date timestamp;

    public HistMessage(Message message, boolean isSentByUs, Date timestamp) {
        this.message = message;
        this.isSentByUs = isSentByUs;
        this.timestamp = timestamp;
    }

    public Message getMessage() {
        return message;
    }

    public boolean isSentByUs() {
        return isSentByUs;
    }

    public Date getTimestamp() {
        return timestamp;
    }

}
