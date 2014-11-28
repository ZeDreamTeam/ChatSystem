package data;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class HelloMessage extends Message {
    private static final String type="hello";//TODO: change to enum Type
    private String userName;

    @Override
    public String getType() {
        return type;
    }
}
