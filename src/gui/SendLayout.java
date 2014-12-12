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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.xml.soap.Text;
import java.io.File;
import java.security.Key;

public class SendLayout extends HBox {
    private final WindowPrincipal papa;
    private final TextField messageInput;
    private final Button send;
    private final Button file;
    private Stage stage;
    private FileChooser fileChooser;

    public SendLayout(WindowPrincipal pop, Stage stag){
        super(50);
        this.papa = pop;
        messageInput = new TextField("Entrez votre message");
        messageInput.setPrefSize(800, 200);
        stage = stag;
        send = new Button("Send");
        send.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                sendMessage(false);
            }
        });
        send.setMinSize(200,125);
        send.setPrefSize(200,125);
        fileChooser = new FileChooser();
        fileChooser.setTitle("OMG GET FILE");
        file = new Button("file");
        file.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                File file = fileChooser.showOpenDialog(stage);
                if (file != null) {
                    retrieveSendFile(file);
                }
            }
        });
        file.setPrefSize(100,100);
        this.setMargin(messageInput, new Insets(50,50,50,50));
        this.setMargin(send, new Insets(50,75,75,75));
        this.getChildren().addAll(messageInput, send, file);


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
                    sendMessage(true);
                    event.consume();
                }

            }
        });
    }
    public void sendMessage(boolean erase){
        String newText = "";
        if(!erase){
            newText = "Entrez votre message";
        }
        papa.sendMessage(messageInput.getText());
        messageInput.setText(newText);
    }
    public void retrieveSendFile(File file){
        papa.sendFile(file.getPath());
    }
}
