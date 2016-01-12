package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar;
    private String inputLine, traceData, traceRouteInput;
    public Thread iThread;
    private boolean shutdown = false;


    public traceRoute(Controller ta, Controller butt, Controller bar, Boolean stop, String dataTrace) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        shutdown = stop;
        traceData = dataTrace;

    }

    public void traceAction(String text) {
        if (traceData != null && traceData.isEmpty()) {
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");
        } else {
            traceRouteInput = text;
            iThread = new Thread(this);
            iThread.start();
        }
    }

    public void killTraceRoute() {
        if (iThread == null) {
            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {
            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("traceroute " + traceRouteInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));


            while (shutdown && (inputLine = in.readLine()) != null) {

                javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText(inputLine + "\n"));
                traceButt.traceButtonOnAction.setDisable(true);
                traceBar.traceProgressBar.setVisible(true);
            }
            traceButt.traceButtonOnAction.setDisable(false);
            traceBar.traceProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }
}








