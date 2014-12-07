package gui;/**
 * Created by djemaa on 01/12/14.
 */

import controller.Controller;
import data.User;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.awt.event.MouseEvent;
import java.beans.EventHandler;
import java.util.ArrayList;

public class ListUserLayout extends VBox {
    private final WindowPrincipal papa;
    private final ObservableList<User> users;
    private ListView<User> list;

    public ListUserLayout(WindowPrincipal pop){
        super();
        this.papa = pop;
        users = Controller.getUsers();
        users.addListener(new ListChangeListener<User>() {
            @Override
            public void onChanged(Change<? extends User> c) {
                papa.updateUsers(c.getList());
            }
        });
        list = new ListView<User>();
        list.setItems(users);
        list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
            @Override
            public void changed(ObservableValue<? extends User> observable, User oldValue, User newValue) {
                papa.selectUser(newValue);
                System.out.println("Selection : " + newValue);
            }
        });
        this.getChildren().add(list);
        list.setPrefSize(200, 600);
        this.setMargin(list, new Insets(75, 75, 0, 75));
        this.setMinSize(200,200);
    }
    public void focusUser(User u){
        list.getSelectionModel().select(u);
    }

}
