package gui;/**
 * Created by djemaa on 01/12/14.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.InputEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

import javax.xml.soap.Text;
import java.security.Key;

public class SendLayout extends HBox {
    private final WindowPrincipal papa;
    private final TextField messageInput;
    private final Button send;

    public SendLayout(WindowPrincipal pop){
        super(50);
        this.papa = pop;
        messageInput = new TextField("Entrez votre message");
        messageInput.setPrefSize(800, 200);

        send = new Button("Send");
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                send();
            }
        });
        send.setMinSize(200,125);
        send.setPrefSize(200,125);
        this.setMargin(messageInput, new Insets(50,50,50,50));
        this.setMargin(send, new Insets(50,75,75,75));
        this.getChildren().addAll(messageInput, send);


        messageInput.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent inputEvent) {
                TextField mess = (TextField) inputEvent.getSource();
                if(mess.getText().equals("Entrez votre message")){
                    mess.setText("");
                }
            }
        });
        messageInput.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<InputEvent>() {
            @Override
            public void handle(InputEvent event) {
                TextField mess = (TextField) event.getSource();
                if(mess.getText().equals("")){
                    mess.setText("Entrez votre message");
                }
            }
        });
        messageInput.addEventHandler(KeyEvent.KEY_PRESSED, new EventHandler<KeyEvent>(){

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    send();
                    event.consume();
                }

            }
        });
    }
    public void send(){
        papa.sendMessage(messageInput.getText());
        messageInput.setText("");
    }
}
