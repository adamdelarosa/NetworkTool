package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PortScan implements Runnable {

    @FXML
    private Controller textOutPing, pingButt, pingBar;
    private String pingInputCli, pingData, pingInput;
    private Thread iThread;
    private boolean shutdown = false;


    public PortScan(Controller taping, Controller buttping, Controller barping, Boolean stopping, String dataPing) {
        textOutPing = taping;
        pingButt = buttping;
        pingBar = barping;
        shutdown = stopping;
        pingData = dataPing;

    }

    public void portScanAction(String text) {
        if (pingData != null && pingData.isEmpty()) {
            textOutPing.pingArea.setText("Insert IP Address / URL Address.");
        } else {
            pingInput = text;
            iThread = new Thread(this);
            iThread.start();
            System.out.println(shutdown);
        }
    }

    public void killportScan() {
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
            Process runningProcess = r.exec("ping " + pingInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));


            while (shutdown && (pingInputCli = in.readLine()) != null) {

                javafx.application.Platform.runLater(() -> textOutPing.pingArea.appendText(pingInputCli + "\n"));
                pingButt.pingButtonOnAction.setDisable(true);
                pingBar.pingProgressBar.setVisible(true);
            }
            pingButt.pingButtonOnAction.setDisable(false);
            pingBar.pingProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }

}








