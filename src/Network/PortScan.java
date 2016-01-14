package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class PortScan implements Runnable {

    @FXML
    private Controller textOutportScan, portScanButt,portScanBar;
    private String portScanInputCli,portScanData,portScanInput,startPort,endPort;
    private Thread iThread;
    private boolean shutdown = false;


    public PortScan(Controller taportScan, Controller buttportScan, Controller barportScan, Boolean stopportScan, String dataportScan) {
        textOutportScan = taportScan;
        portScanButt = buttportScan;
        portScanBar = barportScan;
        shutdown = stopportScan;
        portScanData = dataportScan;

    }

    public void portScanAction(String text,String startP, String endP) {
        if (portScanData != null && portScanData.isEmpty()) {
            textOutportScan.portScanArea.setText("Insert IP Address / URL Address.");
            System.out.print("nc -zv " + portScanInput + startPort + "-" + endPort);
        } else {
            System.out.print("nc -zv " + portScanInput + startPort + "-" + endPort);
            portScanInput = text;
            startPort = startP;
            endPort = endP;
            iThread = new Thread(this);
            iThread.start();
        }
    }

    public void killPortScan() {
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
            Process runningProcess = r.exec("nc -zv " + portScanInput + startPort + "-" + endPort);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));


            while (shutdown && (portScanInputCli = in.readLine()) != null) {


                javafx.application.Platform.runLater(() -> textOutportScan.portScanArea.appendText(portScanInputCli + "\n"));
                portScanButt.portScanOnAction.setDisable(true);
                portScanBar.portScanProgressBar.setVisible(true);
            }
            portScanButt.portScanOnAction.setDisable(false);
            portScanBar.portScanProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }

}








