package sample;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

//Runnable interface contains run() method
public class traceRoute implements Runnable {

    public String inputLine;
    public String traceName;
    String name;
    int time;
    Random r = new Random();

    public traceRoute() {
        traceName = null;
        name = "x";
        time = r.nextInt(999); //between 0-1 second
    }
    //String pizza = inputLine;
    public String enjoy(){
        return inputLine;
    }

    //this runs when you start thread
    Thread t1 = new Thread() {

        public void run() {

            String result = null;
            try {
                Runtime r = Runtime.getRuntime();
                Process p = r.exec("traceroute " + "8.8.8.8");
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(inputLine);
                    result += inputLine;
                    String newResult = inputLine;
                }
                try {
                    System.out.println("hello!1");
                    Thread.sleep(1000);
                } catch (Exception e) {
                }
                in.close();
            } catch (Exception e) {
            }

        }
    };
    Thread t2 = new Thread() {
        public void run() {
            String result = null;

            try {
                while (inputLine != null) {
                    System.out.println(inputLine);

                }
                System.out.println("hello!");
                Thread.sleep(1000);
            } catch (Exception e) {}
        }
    };

    @Override
    public void run() {
        t1.start();
        t2.start();

    }
}
