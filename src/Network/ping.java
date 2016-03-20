package Network;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ping implements Runnable {

    @FXML
    private Controller textOutPing, pingButt, pingBar,pingStopOnAction;
    private String pingInputCli, pingData, pingInput;
    private Thread iThread;
    private boolean shutdown = false;
    public FadeTransition ft;


    public ping(Controller taping, Controller buttping, Controller barping, Boolean stopping, String dataPing,Controller pingstoponaction) {
        textOutPing = taping;
        pingButt = buttping;
        pingBar = barping;
        shutdown = stopping;
        pingData = dataPing;
        pingStopOnAction = pingstoponaction;

    }

    public void pingAction(String text) {
        if (pingData != null && pingData.isEmpty()) {
            textOutPing.pingArea.setText("Insert IP Address / URL Address.");
        } else {
            pingInput = text;
            iThread = new Thread(this);
            iThread.start();


        }
    }

    public void killPing() {
        if (iThread == null) {
            //System.out.println("NO need for Thread to run.");
            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {

            ft = new FadeTransition(Duration.millis(1000),pingStopOnAction.pingButtonStopOnAction);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(Animation.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();

            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("ping " + pingInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (pingInputCli = in.readLine()) != null) {

                javafx.application.Platform.runLater(() -> textOutPing.pingArea.appendText(pingInputCli + "\n"));
                pingButt.pingButtonOnAction.setDisable(true);
                pingBar.pingProgressBar.setVisible(true);

            }
                ft.stop();

            pingButt.pingButtonOnAction.setDisable(false);
            pingBar.pingProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }

}








