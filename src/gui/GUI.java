package gui;

import controller.Controller;
import data.User;
import javafx.stage.Stage;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by djemaa on 01/12/14.
 */
public class GUI extends Thread{
    private final WindowLogIn loginFrame = new WindowLogIn(this);
    private  final WindowPrincipal mainFrame = new WindowPrincipal(this);
    private final Controller controller = new Controller(this);
    private final Stage primaryStage = new Stage();
    private List<User> buffer = new ArrayList<User>();
    public GUI(){
        this.start();
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


    @Override
    public void run() {
        super.run();
        try {
            synchronized (this){
                wait();
                if (buffer.size()!=0){
                    for(User u: buffer){
                        mainFrame.updateUsers(u);
                        buffer.remove(u);
                    }
                }

            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public List<User> getBuffer() {
        return buffer;
    }
}
