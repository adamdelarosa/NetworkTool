package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Netstat implements Runnable {

    @FXML
    private Controller textOutNetstat, netstatButt, netstatBar;
    private String netstatInputCli, netstatData, netstatInput;
    private Thread iThread;
    private boolean shutdown = false;


    public Netstat(Controller tanetstat, Controller buttnetstat, Controller barnetstat, Boolean stopnetstat, String dataNetstat) {
        textOutNetstat = tanetstat;
        netstatButt = buttnetstat;
        netstatBar = barnetstat;
        shutdown = stopnetstat;
        netstatData = dataNetstat;

    }

    public void netstatAction(String text) {
            netstatInput = text;
            iThread = new Thread(this);
            iThread.start();
    }

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

            Runtime netstat = Runtime.getRuntime();
            try {
                netstatButt.netstatButtonOnAction.setDisable(true);
                netstatBar.netstatProgressBar.setVisible(true);
                Process process = netstat.exec(new String[]{"netstat"});
                // prints out any message that are usually displayed in the console
                Scanner scanner = new Scanner(process.getInputStream());
                while (scanner.hasNext()) {
                    textOutNetstat.netstatArea.setText(scanner.nextLine());
                //javafx.application.Platform.runLater(() -> textOutNetstat.netstatArea.setText(scanner.nextLine()));
                }
            }catch (IOException e1) {
                e1.printStackTrace();
            }
            netstatButt.netstatButtonOnAction.setDisable(false);
            netstatBar.netstatProgressBar.setVisible(false);

        } catch (Exception e) {
        }
    }

}








