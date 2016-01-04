package Network;

import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller {

    @FXML
    private Controller textOutTrace;

    public traceRoute(Controller ta) {
        textOutTrace = ta;
    }

    public String inputLine;

    public void changeText(Controller ta,String traceIp) {

        Thread t1 = new Thread() {
            public void run() {
                try {
                    Runtime r = Runtime.getRuntime();
                    Process p = r.exec("traceroute " + traceIp);
                    BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                    while ((inputLine = in.readLine()) != null) {
                        textOutTrace.traceArea.appendText(inputLine);
                        textOutTrace.traceArea.appendText("\n");
                    }
                    in.close();
                } catch (Exception e) {
                }
            }
        };
        t1.start();
    }
}



