package utils;

/**
 * Created by root on 01/12/14.
 */
public class Logger {




    public static void log(String name){
        if(Conf.DEBUG){
            System.out.println("Log : "+ name);
        }
    }
}
