package sample;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import java.awt.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute  {



    Controller con = new Controller();

    public traceRoute(){
        hello();
    }

    public int hello(){

        return 0;
    }



/*    Controller con = new Controller();

    public String inputLine;
    Thread t1 = new Thread() {

        public void run() {

            try {
                Runtime r = Runtime.getRuntime();
                Process p = r.exec("traceroute " + "8.8.8.8");
                BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    con.traceArea.appendText(inputLine);
                    con.traceArea.appendText("\n");
                    System.out.println(inputLine);
                }
                in.close();
            } catch (Exception e) {}
        }
    };*/
}
