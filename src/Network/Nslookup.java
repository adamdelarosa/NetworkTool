package Network;

import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.fxml.FXML;
import javafx.util.Duration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nslookup implements Runnable {

    @FXML
    private Controller textOutNslookup,nslookupStopOnAction;
    private String nslookupInputCli,nslookupInput;
    private Thread iThread;
    private boolean shutdown = false;
    public FadeTransition ft;


    public Nslookup(Controller tanslookup, Boolean stopnslookup,Controller nslookupstoonaction) {
        textOutNslookup = tanslookup;
        shutdown = stopnslookup;
        nslookupStopOnAction = nslookupstoonaction;


    }

    public void nslookupAction(String text) {
        nslookupInput = text;
        iThread = new Thread(this);
        iThread.start();
    }

    public void killNslookup() {
        if (iThread == null) {
            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {

            ft = new FadeTransition(Duration.millis(1000),nslookupStopOnAction.nslookupStopOnAction);
            ft.setFromValue(1.0);
            ft.setToValue(0.3);
            ft.setCycleCount(Animation.INDEFINITE);
            ft.setAutoReverse(true);
            ft.play();

            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("nslookup " + nslookupInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (nslookupInputCli = in.readLine()) != null) {
                textOutNslookup.nslookupArea.appendText(nslookupInputCli + "\n");

            }

            ft.stop();

            in.close();
        } catch (Exception e) {
        }
    }

}








