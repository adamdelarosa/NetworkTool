package Network;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class traceRoute extends Controller {

    @FXML
    private Controller textOutTrace, traceButt, traceBar, traceButtStop;


    public traceRoute(Controller ta, Controller butt, Controller bar, Controller stop) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        traceButtStop = stop;

    }

    public String inputLine;

    public Process runningProcess = null;
    Thread runTrace = new Thread(new Runnable() {

        public void run() {
            try {
                Runtime r = Runtime.getRuntime();
                runningProcess = r.exec("traceroute " + "8.8.8.8");
                BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
                while ((inputLine = in.readLine()) != null) {
                    javafx.application.Platform.runLater( () -> textOutTrace.traceArea.appendText(inputLine + "\n") );

                    //textOutTrace.traceArea.appendText(inputLine);
                    //textOutTrace.traceArea.appendText("\n");
                    //traceButt.traceButtonOnAction.setDisable(true);
                    //traceBar.traceProgressBar.setVisible(true);

                }

                //traceButt.traceButtonOnAction.setDisable(false);
                //traceBar.traceProgressBar.setVisible(false);
                in.close();
            } catch (Exception e) {
            }
        }

    });


    public void traceAction(String traceIp) {


        if (traceIp != null && traceIp.isEmpty()) {
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");
            runTrace.start();
        } else {
            Thread runTrace = new Thread();
            runTrace.run();
        }
    }

    public void killTraceRoute() {
        runningProcess.destroy();
        System.out.print("Stopping");
    }
}







