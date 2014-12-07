package data;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class User {
    private String name;
    private final String ip;
    private final ObservableList<HistMessage> messages= FXCollections.observableArrayList();
    private boolean connected = true;


    public User(String name, String ip) {
        this.name = name;
        this.ip = ip;
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
        final HistMessage toto = message;
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                messages.add(toto);
            }
        });

    }

    public boolean isConnected() {
        return connected;
    }
    public void setConnected(boolean connected){
        this.connected = connected;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return name +"\n"+ ip;
    }
}
