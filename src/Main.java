import controller.Controller;
import data.HelloAckMessage;
import data.HelloMessage;
import data.User;
import networking.ChatNI;
import networking.NetworkingException;

import java.io.IOException;
import java.net.SocketException;
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
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
