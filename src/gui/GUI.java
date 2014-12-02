package gui;

import controller.Controller;
import data.User;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by djemaa on 01/12/14.
 */
public class GUI extends Application{
    private final WindowLogIn loginFrame = new WindowLogIn(this);
    private  final WindowPrincipal mainFrame = new WindowPrincipal(this);
    private final Controller controller = new Controller(this);
    private final Stage primaryStage = new Stage();
    private List<User> buffer = new ArrayList<User>();
    public GUI() {
        try {
            this.start(primaryStage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        loginFrame.start(primaryStage);
    }

    public void doConnect(String text) {
        try {
            controller.connect(text);
            loginFrame.stop();
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

    public WindowLogIn getLoginFrame() {
        return loginFrame;
    }



    public Controller getController() {

        return controller;
    }

    public List<User> getBuffer() {
        return buffer;
    }
}
