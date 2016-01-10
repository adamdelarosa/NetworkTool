package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar;
    private String inputLine, traceData;
    private Process runningProcess;
    private Runtime r = null;
    private BufferedReader in;
    private Thread iThread;
    private boolean loop;


    public traceRoute(Controller ta, Controller butt, Controller bar, Boolean stop, String dataTrace) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        loop = stop;
        traceData = dataTrace;

    }

    public void traceAction() {
        if (traceData != null && traceData.isEmpty()) {
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");

        } else {
            iThread = new Thread(this);
            iThread.start();
        }
    }

    public void killTraceRoute() {


        //num = 1;
        //if (iThread == null) {
        //  return;
        //}else {
        loop = false;
        // }
    }

    @Override
    public void run() {
        try {
            r = Runtime.getRuntime();
            runningProcess = r.exec("traceroute " + traceData);
            in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
            {
                while (loop && (inputLine = in.readLine()) != null) {


                    System.out.println(loop);

                    javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText(inputLine + "\n"));
                    traceButt.traceButtonOnAction.setDisable(true);
                    traceBar.traceProgressBar.setVisible(true);
                }
            }
            traceButt.traceButtonOnAction.setDisable(false);
            traceBar.traceProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }
}








