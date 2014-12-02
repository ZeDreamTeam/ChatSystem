package gui;/**
 * Created by djemaa on 01/12/14.
 */

import data.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class WindowPrincipal extends Application {


    private final GUI gui;
    private final ConversationsLayout conversationsLayout;
    private final ListUserLayout listUserLayout;
    private final SendLayout sendLayout;
    private final BorderPane mainPane;

    public WindowPrincipal(GUI guy) {
        this.gui = guy;
        mainPane = new BorderPane();
        conversationsLayout = new ConversationsLayout();
        listUserLayout = new ListUserLayout(this);
        sendLayout = new SendLayout(this);



    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        mainPane.setCenter(conversationsLayout);
        mainPane.setRight(listUserLayout);
        mainPane.setBottom(sendLayout);
        Scene scene = new Scene(mainPane, 1200, 800);
        primaryStage.setTitle("ChatSystem OMG!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void updateUsers(ObservableList<? extends User> user) {
        this.conversationsLayout.updateTabs(user);
    }
}
