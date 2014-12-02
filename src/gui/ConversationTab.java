package gui;/**
 * Created by djemaa on 01/12/14.
 */

import data.HistMessage;
import data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;

public class ConversationTab extends Tab {
    private User user;
    private final ObservableList<HistMessage> messages = FXCollections.observableArrayList();
    private ListView<HistMessage> listMessages;
    public ConversationTab(User u){
        super();
        this.setClosable(false);
        user = u;
        for(HistMessage currentMess : u.getMessages()){
            messages.add(currentMess);
        }
        this.setText(u.getName());

        listMessages = new ListView<HistMessage>();
        listMessages.setItems(messages);
        this.setContent(listMessages);
        listMessages.setPrefSize(200, 600);
        listMessages.setMinSize(200,200);

    }

    public User getUser() {
        return user;
    }
    public void add(HistMessage message){
        messages.add(message);
    }
}
