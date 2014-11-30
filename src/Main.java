import controller.Controller;
import data.HelloAckMessage;
import data.HelloMessage;
import data.User;
import networking.ChatNI;
import networking.NetworkingException;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class Main {
    public static void main(String[] params){

        Controller controller = new Controller();
        controller.connect("toto");
        User me = controller.getLocalUser();
        System.out.println(me);
        System.out.println(me.getMessages());
        controller.sendMessMessage("toto toototo ", me);
        System.out.println(me.getMessages());
        controller.sendGoodbyeMessage();
        System.out.println(me.isConnected());
    }
}
