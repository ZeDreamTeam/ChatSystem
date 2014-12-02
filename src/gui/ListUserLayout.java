package gui;/**
 * Created by djemaa on 01/12/14.
 */

import data.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class ListUserLayout extends VBox {
    private static ListUserLayout instance = null;
    private static final ObservableList<User> users =FXCollections.observableArrayList();
    private ListView<User> list;

    private ListUserLayout(){
        super();
        list = new ListView<User>();
        list.setItems(users);
        this.getChildren().add(list);
        list.setPrefSize(200, 600);
        this.setMargin(list, new Insets(75, 75, 0, 0));
        this.setMinSize(200,200);
    }

    public static ListUserLayout getInstance(){
        if(instance ==null){
            instance = new ListUserLayout();
        }
        return instance;
    }


    public boolean contains(User u){
        return userIndex(u)!=-1;
    }

    public int userIndex(User u){
        boolean found = false;
        int position =0;
        for(User currentUser : users){
            if(currentUser.equals(u)){
                found = true;
                break;
            }
            position++;
        }
        if(!found){
            position = -1;
        }
        return position;
    }
    public void removeUser(User u){
        users.remove(u);
    }
    public void addUser(User u){
        if(!contains(u)){
            users.add(u);
            System.out.println(u.toString());
        }

    }
}
