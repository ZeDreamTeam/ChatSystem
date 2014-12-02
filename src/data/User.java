package data;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class User {
    private final String name;
    private final String ip;
    private final ObservableList<HistMessage> messages;
    private boolean connected;
    private boolean watched = false;

    public User(String name, String ip) {
        this.name = name;
        this.ip = ip;
        this.messages = FXCollections.observableArrayList();
        this.connected = true;
    }

    public String getName() {
        return name;
    }

    public String getIp() {
        return ip;
    }

    public ObservableList<HistMessage> getMessages() {
        return messages;
    }
    public void addMessage(HistMessage message){
        messages.add(message);
    }

    public boolean isConnected() {
        return connected;
    }
    public void setConnected(boolean connected){
        this.connected = connected;
    }
    @Override
    public String toString(){
        return name +"\n"+ ip;
    }
}
