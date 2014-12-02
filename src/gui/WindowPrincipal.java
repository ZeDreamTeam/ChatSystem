package gui;/**
 * Created by djemaa on 01/12/14.
 */

import data.*;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.awt.event.FocusEvent;
import java.util.Date;


public class WindowPrincipal extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane principal = new BorderPane();
        SendLayout bottom = SendLayout.getInstance();
        ConversationsLayout tabo = ConversationsLayout.getInstance();
        User u1 = new User("toto", "192.168.1.1");
        User u2 = new User("titi", "192.168.1.2");
        User u3 = new User("tata", "192.168.1.3");

        principal.setBottom(bottom);
        TextField mess = (TextField)bottom.getChildren().get(0);

        mess.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                TextField mess = (TextField) inputEvent.getSource();
                if(mess.getText().equals("Entrez votre message")){
                    mess.setText("");
                }
            }
        });
        mess.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                TextField mess = (TextField) event.getSource();
                if(mess.getText().equals("")){
                    mess.setText("Entrez votre message");
                }
            }
        });
        ListUserLayout listUserLayout = ListUserLayout.getInstance();
        listUserLayout.addUser(u1);
        listUserLayout.addUser(u2);
        listUserLayout.addUser(u3);
        principal.setRight(listUserLayout);
        principal.setCenter(tabo);

        Scene scene = new Scene(principal, 1200, 800);

        ConversationTab tab1 = new ConversationTab(u1);
        ConversationTab tab2 = new ConversationTab(u2);
        tabo.getTabs().add(tab1);
        HistMessage h1 = new HistMessage(new MessMessage(23, "salut"), true,new Date());
        HistMessage h2 = new HistMessage(new HelloAckMessage("kakou"), true,new Date());
        HistMessage h3 = new HistMessage(new HelloMessage("bazoomboy"), true,new Date());
        HistMessage h4 = new HistMessage(new GoodbyeMessage(), true,new Date());
        HistMessage h5 = new HistMessage(new MessAckMessage(23), true,new Date());

        tab1.add(h1);


        tabo.getTabs().add(tab2);
        primaryStage.setTitle("ChatSystem!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        ConversationTab tab3 = new ConversationTab(u3);
        tabo.getTabs().add(tab3);
        tab2.add(h2);
        tab3.add(h3);
        tab2.add(h4);
        tab3.add(h5);







    }

}
