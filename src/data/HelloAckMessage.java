package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloAckMessage extends Message{
    private final static String type ="helloAck";
    private final String userName;
    public HelloAckMessage(String name) {
        this.userName=name;
    }

    public String getUserName() {
        return userName;
    }
    @Override
    public String getType() {
        return type;
    }
}
