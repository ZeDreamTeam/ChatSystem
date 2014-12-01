package utils;

/**
 * Created by root on 01/12/14.
 */
public class Logger {


    private static boolean DEBUG = false;


    public static void log(String name){
        if(DEBUG){
            System.out.println("Log : "+ name);
        }
    }
}
