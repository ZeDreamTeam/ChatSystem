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
        try{
            User me = new User();
            Controller controller = new Controller();
            HelloMessage hello = new HelloMessage("bonjoursalutcestmoiestcequeyaqq?");
            ni.performSendHello(hello);
        } catch (NetworkingException.ReceivingException e) {
            e.printStackTrace();
        }
        while (true){

        }
    }
}
