package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class NetworkCard implements Runnable {

    @FXML
    private Controller textOutCard;
    private String cardInputCli;
    private Thread iThread;
    private boolean shutdown = false;


    public NetworkCard(Controller tacard,Boolean stopcard) {
        textOutCard = tacard;
        shutdown = stopcard;
    }

    public void cardAction() {
            iThread = new Thread(this);
            iThread.start();

    }

    public void killCard() {
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
            Process runningProcess = r.exec("ifconfig -a");
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (cardInputCli = in.readLine()) != null) {
                textOutCard.cardArea.appendText(cardInputCli + "\n");
            }
            in.close();
        } catch (Exception e) {
        }
    }
}








