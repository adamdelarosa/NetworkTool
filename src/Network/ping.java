package Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ping {

    Controller con = new Controller();

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
    };
}
