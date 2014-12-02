package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class SendLayout extends HBox {
    private static SendLayout instance = null;

    private SendLayout(){
        super(50);
        final TextField messageInput = new TextField("Entrez votre message");
        messageInput.setPrefSize(800, 200);
        Button send = new Button("Send");
        send.setMinSize(200,125);
        send.setPrefSize(200,125);
        this.setMargin(messageInput, new Insets(50,50,50,50));
        this.setMargin(send, new Insets(50,75,75,75));
        this.getChildren().addAll(messageInput, send);
    }

    public static SendLayout getInstance(){
        if(instance == null){
            instance = new SendLayout();
        }
        return instance;
    }

}
