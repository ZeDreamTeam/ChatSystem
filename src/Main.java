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
        /*Controller controller = new Controller();
        try {
            User u = controller.connect("pascalladalleretournemangertonkebab");
            System.out.println(u.getIp());
            while(controller.getUsers().size() == 0){
                System.out.println("toto");
            }
            int wait = (int)(500+Math.random()*3000);

            System.out.println("tafdqfd");
            Thread.sleep(wait);

            controller.sendMessMessage("Salut c'est : "+controller.getLocalUser().getName(),controller.getUsers().get(0));
             wait = (int)(500+Math.random()*3000);
            for(User ut : controller.getUsers()) {
                controller.sendMessMessage("Taracecassetoisaleiench", ut);
            }
            Thread.sleep(wait);
            int k =0;
            System.out.println("toto");
            while(true){
                k++;
                controller.connect(k+"coucoumabite");
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        GUI gui = new GUI();
        WindowLogIn logIn = new WindowLogIn(gui);
        logIn.start(stage);
    }
}
