package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SendLayout extends Application {
    public  HBox addSendLayout(){
        HBox sendLayout = new HBox(2);
        TextField messageInput = new TextField("Entrez votre message");
        Button send = new Button("Send");
        sendLayout.getChildren().addAll(messageInput, send);
        return sendLayout;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {



    }
}
