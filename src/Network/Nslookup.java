package Network;

import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Nslookup implements Runnable {

    @FXML
    private Controller textOutNslookup;
    private String nslookupInputCli, nslookupData, nslookupInput;
    private Thread iThread;
    private boolean shutdown = false;


    public Nslookup(Controller tanslookup, Boolean stopnslookup, String dataNslookup) {
        textOutNslookup = tanslookup;
        shutdown = stopnslookup;
        nslookupData = dataNslookup;


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
            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("nslookup " + nslookupInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (nslookupInputCli = in.readLine()) != null) {
                textOutNslookup.nslookupArea.appendText(nslookupInputCli + "\n");
            }
            in.close();
        } catch (Exception e) {
        }
    }

}








