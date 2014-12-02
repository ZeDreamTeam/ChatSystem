package gui;

import data.User;
import javafx.scene.control.TabPane;

import java.util.ArrayList;

/**
 * Created by root on 01/12/14.
 */
public class ConversationsLayout extends TabPane{
    private static ConversationsLayout instance = null;
    private ArrayList<User> users;

    private ConversationsLayout(){
        super();
    }

    public static ConversationsLayout getInstance(){
        if(instance == null){
            instance = new ConversationsLayout();
        }
        return instance;
    }

    public void addUser(User u){
        if(!contains(u)){
            ConversationTab tab = new ConversationTab(u);
            users.add(u);
            this.getTabs().add(tab);
        }
    }

    public void removeUser(User u){
        users.remove(u);
        this.getTabs().remove(u);
    }

    public int getTabIndex(User u){
        boolean found = false;
        int i = 0;
        for(User currentUser : users){
            if(u.equals(currentUser)){
                found = true;
                break;
            }
            i++;
        }
        if(!found){
            i = -1;
        }
        return i;
    }

    public boolean contains(User u){
        return (getTabIndex(u) != -1);
    }
}
