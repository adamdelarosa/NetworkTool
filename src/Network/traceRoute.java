package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar;
    private String traceInputCli, traceData, traceRouteInput;
    private Thread iThread;
    private boolean shutdown = false;


    public traceRoute(Controller tatrace, Controller butttrace, Controller bartrace, Boolean stoptrace, String dataTrace) {
        textOutTrace = tatrace;
        traceButt = butttrace;
        traceBar = bartrace;
        shutdown = stoptrace;
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
            Thread stopT = new Thread(new Runnable() {
                public void run() {
                    try {
                            textOutTrace.traceArea.appendText("\n" + "Stopping .");
                        while(iThread.isAlive()) {
                            Thread.sleep(500);
                            textOutTrace.traceArea.appendText(" .");
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            });

            stopT.start();
        }
    }

    public boolean visible = false;
    @Override
    public void run() {
        try {
            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("traceroute " + traceRouteInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));


            while (shutdown && (traceInputCli = in.readLine()) != null) {
                javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText("\n" +traceInputCli ));
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








