package controller;
import data.*;
import networking.ChatNI;
import networking.NetworkingException;
import utils.Logger;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by djemaa on 28/11/14.
 */
public class Controller {
    private ChatNI ni;
    private User localUser;
    private ArrayList<User> users;

    public User getLocalUser() {
        return localUser;
    }

    /**
     * Constructor : init ni and users
     * it doesn't init localUser because it doesn't knwow localAdress
     * localAdress will be known after the connect()
     */
    public Controller(){
        Logger.log("Controller()");
        try{
            ni = new ChatNI(this);
        } catch (NetworkingException.ReceivingException e){
            e.printStackTrace();
        }
        users = new ArrayList<User>();
    }

    /**
     * if CS doesn't know the ip (if there is no user with the same ip) :
     * create and add user w/ (username inside the HelloMessage)
     * send HelloAck
     *
     * @param hello ''
     * @param ip ''
     */
    public void receiveHelloMessage(HelloMessage hello, String ip){
        Logger.log("Controller.receiveHelloMessage");
        String uName = hello.getUserName();
        if(!exists(ip) && !ip.equals(localUser.getIp())){
            User user = new User(uName, ip);
            addUser(new User(uName, ip));
            sendHelloAckMessage(user);
        }
        System.out.println("Received Hello " + hello.getUserName());
    }


    /**
     * if it doesn't know the ip :
     * create and add user
     * @param hello ''
     * @param ip ''
     */
    public void receiveHelloAckMessage(HelloAckMessage hello, String ip){
        Logger.log("Controller.receiveHelloAckMessage");
        String uName = hello.getUserName();
        if(!exists(ip)){
            User user = new User(uName, ip);
            addUser(user);
            logMessage(hello, user, false);
        }
    }

    /**
     *
     * @param messMessage ''
     * @param ip ''
     */
    public void receiveMessMessage(MessMessage messMessage, String ip){
        Logger.log("Controller.receiveMessMessage");
        if(!exists(ip)){
            stfu(ip);
        } else{
            User user = users.get(userPosition(ip));
            logMessage(messMessage, user, false);
            sendMessAckMessage(messMessage.getMessageNumber(), user);
        }
    }



    /**
     *
     * @param messAckMessage ''
     * @param ip ''
     */
    public void receiveMessAckMessage(MessAckMessage messAckMessage, String ip){
        Logger.log("Controller.receiveMessAckMessage");
        if(!exists(ip)){
            stfu(ip);
        } else{
            logMessage(messAckMessage, users.get(userPosition(ip)), false);
        }

    }

    /**
     *
     * @param goodbye ''
     * @param ip ''
     */
    public void receiveGoodbyeMessage(GoodbyeMessage goodbye, String ip){
        Logger.log("Controller.receiveGoodbyeMessage");
        if(!exists(ip)){
            stfu(ip);
        } else{
            User user = users.get(userPosition(ip));
            logMessage(goodbye, user, false);
            user.setConnected(false);

        }
    }

    /**
     * First connection with network, tells "hi" to everyone
     * and set the localuser
     * @param uName ''
     * @return the local user
     */
    public User connect(String uName) throws UnknownHostException {
        Logger.log("Controller.connect w/ "+uName);
        sendHelloMessage(uName);
        localUser = new User(uName, Inet4Address.getLocalHost().getHostAddress());
        return localUser;
    }


    /**
     * send hello to all and brings back user's local adress from the NI
     * needed to instanciate localUser
     *
     * @param uName ''
     * @return local adress as a String
     */
    private void sendHelloMessage(String uName){
        Logger.log("Controller.sendHelloMessage");
        HelloMessage hello = new HelloMessage(uName);
        ni.performSendHello(hello);
    }

    /**
     *
     * @param user to whom we send the HelloAck
     */
    private void sendHelloAckMessage(User user) {
        Logger.log("Controller.sendHelloAckMessage");
        HelloAckMessage ack = new HelloAckMessage(localUser.getName());
        logMessage(ack, user, true);
        ni.performSendHelloAck(ack, user);

    }

    /**
     * log and send message to an user
     * @param data ''
     * @param user ''
     */
    public void sendMessMessage(String data, User user){
        Logger.log("Controller.sendMessMessage");
        int rand = ((int) (Math.random()*100000));
        if(rand != 0){
            rand -=1 ;
        }
        MessMessage messMessage = new MessMessage(rand, data);
        logMessage(messMessage, user, true);
        ni.performSendMessMessage(messMessage, user);
    }

    /**
     * log and send message ack to an user
     * @param messageNumber ''
     * @param user ''
     */
    private void sendMessAckMessage(int messageNumber, User user) {
        Logger.log("Controller.sendMessAckMessage");
        MessAckMessage messAckMessage = new MessAckMessage(messageNumber);
        logMessage(messAckMessage, user, true);
        ni.performSendMessAckMessage(messAckMessage, user);
    }

    /**
     * byebye everyone
     */
    public void sendGoodbyeMessage(){
        Logger.log("Controller.sendGoodbyeMessage");
        GoodbyeMessage goodbyeMessage = new GoodbyeMessage();
        logMessage(goodbyeMessage, localUser, true);
        ni.performSendGoodbyeMessage(goodbyeMessage);
    }

    /**
     * byebye ip
     * @param ip ''
     */
    public void stfu(String ip){
        Logger.log("Controller.stfu");
        GoodbyeMessage goodbyeMessage = new GoodbyeMessage();
        ni.performSTFU(goodbyeMessage, ip);

    }

    /**
     *
     * @param ip ''
     * @return true if user exists false otherwise
     */
    private boolean exists(String ip){
        Logger.log("Controller.exists");
        return userPosition(ip) != -1;
    }

    /**
     *
     * @param ip ''
     * @return the position of the user w/ this ip, -1 if it doesn't exists
     */
    private int userPosition(String ip){
        Logger.log("Controller.userPosition");
        int ret =-1;
        boolean found = false;
        for(User u : users){
            ret++;
            if(u.getIp().equals(ip)){
                found = true;
                break;
            }
        }
        if(!found){
            ret = -1;
        }
        return ret;
    }
    private void addUser(User u) {
        Logger.log("Controller.addUser");
        users.add(u);
    }

    /**
     * Log the message in the messages ArrayList of the user
     *
     * @param mess ''
     * @param user ''
     * @param iAmSender specify the direction of the message (false = incoming, true = outgoing)
     */
    private void logMessage(Message mess, User user, boolean iAmSender){
        Logger.log("Controller.logMessage");
        HistMessage histMessage = new HistMessage(mess, iAmSender, new Date());
        user.addMessage(histMessage);
    }

    public ArrayList<User> getUsers() {
        return users;
    }
}
