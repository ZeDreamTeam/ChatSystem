import controller.Controller;
import data.User;
import gui.GUI;
import gui.WindowLogIn;
import javafx.application.Application;
import javafx.stage.Stage;

import java.net.UnknownHostException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class Main extends Application{
    public static void main(String[] params){


        launch(params);
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUI gui = new GUI();
    }
}
