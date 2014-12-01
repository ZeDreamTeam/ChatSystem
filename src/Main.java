import controller.Controller;
import data.User;

import java.net.UnknownHostException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class Main {
    public static void main(String[] params){

        Controller controller = new Controller();
        try {
            User u = controller.connect("toto");
            System.out.println(u.getIp());
            while(controller.getUsers().size() == 0){
            }
            int wait = (int)(500+Math.random()*3000);
            Thread.sleep(wait);
            controller.sendMessMessage("Salut c'est : "+controller.getLocalUser().getName(),controller.getUsers().get(0));
             wait = (int)(500+Math.random()*3000);
            Thread.sleep(wait);
            //controller.stfu(controller.getUsers().get(0).getIp());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
