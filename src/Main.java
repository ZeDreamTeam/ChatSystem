import data.HelloAckMessage;
import data.HelloMessage;
import networking.ChatNI;

import java.io.IOException;
import java.net.SocketException;

/**
 * Created by MagicMicky on 28/11/2014.
 */
public class Main {
    public static void main(String[] params){
        try{
            ChatNI ni = new ChatNI();
            HelloMessage hello = new HelloMessage("bonjoursalutcestmoiestcequeyaqq?");
            ni.performSendHello(hello);
        } catch(SocketException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
