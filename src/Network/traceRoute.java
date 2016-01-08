package Network;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;

public class traceRoute extends Controller {

    @FXML
    private Controller textOutTrace, traceButt, traceBar;


    public traceRoute(Controller ta, Controller butt, Controller bar, Controller stop) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
    }

    public String inputLine;

    public Process runningProcess = null;
    public Runtime r = null;
    Thread runTrace = new Thread(new Runnable() {

        public void run() {
            try {
                r = Runtime.getRuntime();
                runningProcess = r.exec("traceroute " + "8.8.8.8");
                BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
                String pid = ManagementFactory.getRuntimeMXBean().getName();
                while ((inputLine = in.readLine()) != null) {
                    System.out.print(pid);
                    javafx.application.Platform.runLater( () -> textOutTrace.traceArea.appendText(inputLine + "\n") );
                    traceButt.traceButtonOnAction.setDisable(true);
                    traceBar.traceProgressBar.setVisible(true);
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

    public void killTraceRoute() {
        try {
            runningProcess = r.exec("kill -9 867");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
        runningProcess.destroy();
        runningProcess = null;
        System.out.print("Stopping");
    }
}







