package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar;
    private String inputLine, traceData;
    public Thread iThread;
    volatile boolean shutdown = false;


    public traceRoute(Controller ta, Controller butt, Controller bar, Boolean stop, String dataTrace) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        shutdown = stop;
        traceData = dataTrace;

    }

    public void traceAction() {
        if (traceData != null && traceData.isEmpty()) {
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");
            System.out.print(shutdown);
            iThread = new Thread(this);
            iThread.start();
        } else {
            iThread = new Thread(this);
            iThread.start();
        }
    }

    public void killTraceRoute() {
        shutdown = false;
        //num = 1;
        //if (iThread == null) {
        //  return;
        //}else {
        //}
   }

    @Override
    public void run() {
         try {
            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("traceroute " + "8.8.8.8");
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
            {
                while (shutdown = true) {
                    while ((inputLine = in.readLine()) != null) {

                        System.out.println(shutdown);

                        javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText(inputLine + "\n"));
                        traceButt.traceButtonOnAction.setDisable(true);
                        traceBar.traceProgressBar.setVisible(true);
                    }
                }
            }
            traceButt.traceButtonOnAction.setDisable(false);
            traceBar.traceProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }
}








