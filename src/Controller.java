import data.HelloAckMessage;
import data.HelloMessage;
import data.User;
import networking.ChatNI;

import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by djemaa on 28/11/14.
 */
public class Controller {
    private ChatNI ni;
    ArrayList<User> users;


    public Controller(){
        try{
            ni = new ChatNI();
        } catch (SocketException e){
            e.printStackTrace();
        }
        users = new ArrayList<User>();
    }
    public void receiveHelloMessage(HelloMessage hello){

    }
    public void receiveHelloAckMessage(HelloAckMessage hello, String ip){
        String remoteUserName = hello.getUserName();
        boolean userExists = false;
        for(User u : users){
            if(u.getName().equals(remoteUserName)){
                if(u.getIp().toString().equals(ip)) {
                    userExists = true;
                    break;
                }
            }
        }
        if(!userExists){
            createAndAddUser(hello, ip);
        }

    }

    private void createAndAddUser(HelloAckMessage hello, String ip) {
        User niou = new User(hello.getUserName(), ip);
        users.add(niou);
    }

    public void receiveMessMessage(HelloMessage hello){

    }
    public void receiveMessAckMessage(HelloMessage hello){

    }
    public void receiveGoodbyeMessage(HelloMessage hello){

    }





}
