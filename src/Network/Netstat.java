package Network;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Netstat implements Runnable {

    @FXML
    private Controller textOutNetstat, netstatButt, netstatBar,netLabel;
    private String netstatInputCli, netstatData, netstatInput;
    private Thread iThread,stopT;
    private boolean shutdown = false;


    public Netstat(Controller tanetstat, Controller buttnetstat, Controller barnetstat, Boolean stopnetstat,Controller netlabel, String dataNetstat) {
        textOutNetstat = tanetstat;
        netstatButt = buttnetstat;
        netstatBar = barnetstat;
        shutdown = stopnetstat;
        netstatData = dataNetstat;
        netLabel = netlabel;

    }

    public void netstatAction(String text)  {
            netstatInput = text;
            iThread = new Thread(this);
            iThread.start();
        stopT = new Thread(this);
        stopT.start();

        Thread stopT = new Thread(() -> {
            try {
                while(iThread.isAlive()) {
                    netLabel.netstatField.setText("Getting Data .");
                    Thread.sleep(1000);
                    netLabel.netstatField.setText("Getting Data . .");
                    Thread.sleep(1000);
                    netLabel.netstatField.setText("Getting Data . . .");
                    Thread.sleep(1000);
                    netLabel.netstatField.setText("Getting Data . . . .");
                    Thread.sleep(1000);
                    netLabel.netstatField.setText("Getting Data . . . . .");
                    Thread.sleep(1000);

                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        });
        stopT.start();

    }
    Boolean progBar = true;

    public void killNetstat() {
        if (iThread == null) {
            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {
            String[] cmd = {"netstat"};
            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec(cmd);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

                netstatButt.netstatButtonOnAction.setDisable(progBar);
            netstatBar.netstatProgressBar.setVisible(true);

            while (shutdown && (netstatInputCli = in.readLine()) != null) {
                javafx.application.Platform.runLater( () -> textOutNetstat.netstatArea.appendText(netstatInputCli + "\n"));
            }
            netstatButt.netstatButtonOnAction.setDisable(false);
            netstatBar.netstatProgressBar.setVisible(false);
            in.close();
        } catch (Exception e) {
        }
    }

}








