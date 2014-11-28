import data.HelloAckMessage;
import data.HelloMessage;
import data.User;
import networking.ChatNI;

import java.io.IOException;
import java.net.SocketException;
import java.util.ArrayList;

/**
 * Created by djemaa on 28/11/14.
 */
public class Controller {
    private ChatNI ni;
    private User localUser;
    private ArrayList<User> users;


    public Controller(User me){
        localUser = me;
        try{
            ni = new ChatNI();
        } catch (SocketException e){
            e.printStackTrace();
        }
        users = new ArrayList<User>();
    }
    public void receiveHelloMessage(HelloMessage hello, String ip){
        String uName = hello.getUserName();
        if(!exists(uName, ip)){
            User u = new User(uName, ip);
            addUser(u);
            sendHelloAck(u);
        }
    }



    public void receiveHelloAckMessage(HelloAckMessage hello, String ip){
        String uName = hello.getUserName();
        if(!exists(uName,ip)){
            User u = new User(uName, ip);
            addUser(u);
        }
    }
    public void receiveMessMessage(HelloMessage hello, String ip){
        String uName = hello.getUserName();
        if(!exists(uName, ip)){
            stfu(uName, ip);
        } else{

        }
    }
    public void receiveMessAckMessage(HelloMessage hello, String ip){

    }
    public void receiveGoodbyeMessage(HelloMessage hello, String ip){

    }

    private void sendHelloAck(User u) {
        HelloAckMessage ack = new HelloAckMessage(localUser.getName());
        try{
            ni.performSendHelloAck(ack, u);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public void stfu(String userName, String ip){

    }
    private boolean exists(String userName, String ip){
        boolean exists = false;
        for(User u : users){
            if(u.getName().equals(userName) && u.getIp().equals(ip)){
                exists = true;
                break;
            }
        }
        return exists;
    }
    private void addUser(User u) {
        users.add(u);
    }







}
