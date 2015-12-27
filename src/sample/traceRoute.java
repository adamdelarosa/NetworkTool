package sample;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class traceRoute extends Controller {

    public String inputLine;

    public void traceObject(String traceName) {

        String result = null;
        try {
            Runtime r = Runtime.getRuntime();
            Process p = r.exec("traceroute " + traceName);
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result += inputLine;
                traceArea.appendText();

            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("TraceRoute Error");
        }



    }
    public String test(){
        System.out.println(inputLine);
        return  inputLine;




    }
}
