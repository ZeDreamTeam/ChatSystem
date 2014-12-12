package gui;

import controller.Controller;
import data.User;
import javafx.application.Application;
import javafx.stage.Stage;
import networking.NetworkingException;
import utils.Logger;

import java.net.UnknownHostException;


/**
 * Created by djemaa on 01/12/14.
 */
public class GUI extends Application{
    private final WindowLogIn loginFrame;
    private  final WindowPrincipal mainFrame;
    private final Controller controller;

    public GUI() throws NetworkingException.ReceivingException, NetworkingException.ReceivingFileException {
        loginFrame = new WindowLogIn(this);
        mainFrame = new WindowPrincipal(this);
        controller = new Controller(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginFrame.start(primaryStage);
    }

    public void doConnect(String text, Stage primaryStage) {
        try {
            controller.connect(text);
            mainFrame.start(primaryStage);

        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doSend(String message, User user){
        controller.sendMessMessage(message, user);
    }
    public void shutdown(){
        try {
            mainFrame.stop();
            loginFrame.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
        controller.shutController();
        try {
            this.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendFile(String path, User user) {
        this.controller.performSendFile(path,user);
    }
}
