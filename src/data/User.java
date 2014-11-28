package data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class User {
    private final String name;
    private final String ip;
    private final List<HistMessage> messages;

    public User(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.messages = new ArrayList<HistMessage>();
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public List<HistMessage> getMessages() {
        return messages;
    }
}
