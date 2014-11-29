package controller;
import data.*;
import networking.ChatNI;
import networking.NetworkingException;

import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by djemaa on 28/11/14.
 */
public class Controller {
    private ChatNI ni;
    private User localUser;
    private ArrayList<User> users;


    public Controller(){
        try{
            ni = new ChatNI(this);
        } catch (NetworkingException.ReceivingException e){
            e.printStackTrace();
        }
        users = new ArrayList<User>();
    }
    public void receiveHelloMessage(HelloMessage hello, String ip){
        String uName = hello.getUserName();
        if(!exists(ip)){
            User u = new User(uName, ip);
            addUser(u);
            sendHelloAck(u);
        }
    }



    public void receiveHelloAckMessage(HelloAckMessage hello, String ip){
        String uName = hello.getUserName();
        if(!exists(ip)){
            User u = new User(uName, ip);
            addUser(u);
        }
    }
    public void receiveMessMessage(MessMessage messMessage, String ip){
        if(!exists(ip)){
            stfu(ip);
        } else{
            HistMessage histMessage = new HistMessage(messMessage,false, new Date());
            users.get(userPosition(ip)).addMessage(histMessage);
        }
    }
    public void receiveMessAckMessage(MessAckMessage messAckMessage, String ip){
        if(!exists(ip)){
            stfu(ip);
        } else{
            HistMessage histMessage = new HistMessage(messAckMessage, false, new Date());
            users.get(userPosition(ip)).addMessage(histMessage);
        }

    }
    public void receiveGoodbyeMessage(GoodbyeMessage goodbye, String ip){
        if(!exists(ip)){
            stfu(ip);
        } else{
            HistMessage histMessage = new HistMessage(goodbye, false, new Date());
            User remote = users.get(userPosition(ip));
            remote.addMessage(histMessage);
            remote.setConnected(false);

        }
    }

    private void connect(String uName){
        HelloMessage hello = new HelloMessage(uName);
        String myAdress;
        myAdress = sendHello(uName);
        User me = new User(uName, myAdress);
        localUser = me;

    }
    //return adress of local host
    private String sendHello(String uName){
        HelloMessage hello = new HelloMessage(uName);
        return ni.performSendHello(hello);
    }

    private void sendHelloAck(User u) {
        HelloAckMessage ack = new HelloAckMessage(localUser.getName());
        ni.performSendHelloAck(ack, u);

    }

    public void stfu(String ip){
        GoodbyeMessage goodbyeMessage = new GoodbyeMessage();
        ni.performSTFU(goodbyeMessage, ip);

    }

    private boolean exists(String ip){
        return userPosition(ip) != -1;
    }

    private int userPosition(String ip){
        int ret =-1;
        boolean found = false;
        for(User u : users){
            ret++;
            if(u.getIp().equals(ip)){
                found = true;
                break;
            }
        }
        if(found == false){
            ret = -1;
        }
        return ret;
    }
    private void addUser(User u) {
        users.add(u);
    }







}
