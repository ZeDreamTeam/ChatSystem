package data;

import java.util.Date;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HistMessage {
    private final ReceivedStuff message;
    private final boolean isSentByUs;
    private final Date timestamp;

    public HistMessage(ReceivedStuff message, boolean isSentByUs, Date timestamp) {
        this.message = message;
        this.isSentByUs = isSentByUs;
        this.timestamp = timestamp;
    }

    public ReceivedStuff getMessage() {
        return message;
    }

    public boolean isSentByUs() {
        return isSentByUs;
    }

    public Date getTimestamp() {
        return timestamp;
    }
    @Override
    public String toString(){
        String ret;
        ret = timestamp.getHours()+":"+timestamp.getMinutes();
        if(isSentByUs){
            ret +=" < ";
        }else{
            ret +=" > ";
        }
        return ret+message.toString();
    }

}
