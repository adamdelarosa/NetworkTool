package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller {

    @FXML
    private Controller textOutTrace, traceButt, traceBar , traceStopButton;
    private String inputLine;
    private Process runningProcess;
    private Runtime r = null;
    private BufferedReader in;


    public traceRoute(Controller ta, Controller butt, Controller bar, Controller stop) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        traceStopButton = stop;
    }

    public int num;

    Thread runTrace = new Thread(new Runnable() {
        public void run() {
            try {
                r = Runtime.getRuntime();
                runningProcess = r.exec("traceroute " + "8.8.8.8");
                in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
                {
                    while ((inputLine = in.readLine()) != null) {

                        javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText(inputLine + "\n"));
                        traceButt.traceButtonOnAction.setDisable(true);
                        traceBar.traceProgressBar.setVisible(true);
                        if(killTraceRoute(num) == 1){
                            break;
                        }



                    }
                }
                traceButt.traceButtonOnAction.setDisable(false);
                traceBar.traceProgressBar.setVisible(false);


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
            runTrace.start();
        }
    }

    public int killTraceRoute(int num) {
        return num;


    }
}








