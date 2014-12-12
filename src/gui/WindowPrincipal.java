package gui;/**
 * Created by djemaa on 01/12/14.
 */

import data.User;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class WindowPrincipal extends Application {


    private final GUI gui;
    private final ConversationsLayout conversationsLayout;
    private final ListUserLayout listUserLayout;
    private SendLayout sendLayout;
    private final BorderPane mainPane;

    public WindowPrincipal(GUI guy) {
        this.gui = guy;

        conversationsLayout = new ConversationsLayout();
        listUserLayout = new ListUserLayout(this);

        mainPane = new BorderPane();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(final Stage primaryStage) {

        sendLayout = new SendLayout(this, primaryStage);
        mainPane.setCenter(conversationsLayout);
        mainPane.setRight(listUserLayout);
        mainPane.setBottom(sendLayout);
        Scene scene = new Scene(mainPane, 1200, 800);
        scene.getStylesheets().add("gui/superStyleSheet.css");

        primaryStage.setTitle("ChatSystem OMG!");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                gui.shutdown();
            }


        });
    }

    public void updateUsers(ObservableList<? extends User> user) {
        this.conversationsLayout.updateTabs(user);
    }

    public void sendMessage(String text) {
        gui.doSend(text, ((ConversationTab)conversationsLayout.getSelectionModel().getSelectedItem()).getUser());
    }
    public void selectUser(User u){
        listUserLayout.focusUser(u);
        conversationsLayout.setFocusUser(u);
    }

    public void sendFile(String path) {
        gui.sendFile(path,((ConversationTab)conversationsLayout.getSelectionModel().getSelectedItem()).getUser() );
    }
}
