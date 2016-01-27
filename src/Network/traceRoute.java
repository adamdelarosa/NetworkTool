package Network;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar,traceStopOnAction;
    private String traceInputCli, traceData, traceRouteInput;
    private Thread iThread;
    private boolean shutdown = false;
    public FadeTransition ft;



    public traceRoute(Controller tatrace, Controller butttrace, Controller bartrace, Boolean stoptrace, String dataTrace, Controller tracestoponaction) {
        textOutTrace = tatrace;
        traceButt = butttrace;
        traceBar = bartrace;
        shutdown = stoptrace;
        traceData = dataTrace;
        traceStopOnAction = tracestoponaction;

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
        if (iThread == null || shutdown == false) {
            return;
        } else {
            shutdown = false;
            Thread stopT = new Thread(new Runnable() {
                public void run() {
                    try {
                        textOutTrace.traceArea.appendText("\n" + "Stopping .");
                        while (iThread.isAlive()) {
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

    @Override
    public void run() {
        try {

            ft = new FadeTransition(Duration.millis(1000),traceStopOnAction.traceStopButtonOnAction);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(Animation.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();

            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("traceroute " + traceRouteInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));


            while (shutdown && (traceInputCli = in.readLine()) != null) {
                javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText("\n" + traceInputCli));
                traceButt.traceButtonOnAction.setDisable(true);
                traceBar.traceProgressBar.setVisible(true);

            }

            ft.stop();

            traceButt.traceButtonOnAction.setDisable(false);
            traceBar.traceProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }
}








