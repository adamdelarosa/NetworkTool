package sample;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//Runnable interface contains run() method
public class ping implements Runnable{

    public String inputLine;
    public String traceName;
    String name;
    int time;
    Random r = new Random();

    public ping (String x, String traceName){
        traceName = null;
        name = x;
        time = r.nextInt(999); //between 0-1 second
    }




    //this runs when you start thread

    public void run(){
        String result = null;
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("traceroute " + "8.8.8.8");
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result += inputLine;
                final String newResult = inputLine;
                try {
                    System.out.println("hello!1");
                    System.out.println("hello!2");
                    System.out.println("hello!3");

                }catch(Exception e){}


            }

            in.close();



        }catch(Exception e){}
    }
}
