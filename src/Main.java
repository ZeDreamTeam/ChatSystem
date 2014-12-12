import gui.GUI;
import javafx.application.Application;
import javafx.stage.Stage;

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
        gui.start(stage);
    }
}
