package Network;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Background;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller {

    @FXML
    private Controller textOutTrace;
    @FXML
    private Controller traceButt;

    public traceRoute(Controller ta, Controller butt) {
        textOutTrace = ta;
        traceButt = butt;
    }

    public String inputLine;

    public void changeText(Controller ta, String traceIp) {
        if (traceIp != null && traceIp.isEmpty()) {
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");
        } else {
            Thread t1 = new Thread() {
                @Override
                public void run() {
                    try {

                        Runtime r = Runtime.getRuntime();
                        Process p = r.exec("traceroute " + traceIp);
                        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((inputLine = in.readLine()) != null) {
                            textOutTrace.traceArea.appendText(inputLine);
                            textOutTrace.traceArea.appendText("\n");
                            traceButt.traceButtonOnAction.setDisable(true);
                        }
                        traceButt.traceButtonOnAction.setDisable(false);
                        in.close();
                    } catch (Exception e) {
                    }
                }

            };
            t1.start();
        }
    }
}



