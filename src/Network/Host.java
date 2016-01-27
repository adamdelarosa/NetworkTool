package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Host implements Runnable {

    @FXML
    private Controller textOutHost;
    private String hostInputCli,hostData,hostInput,hostAskInput;
    private Thread iThread;
    private boolean shutdown = false;


    public Host(Controller tahost,Boolean stophost,String dataHost) {
        textOutHost = tahost;
        shutdown = stophost;
        hostData = dataHost;

    }

    public void hostAction(String text,String hostAskText) {
        if (hostData != null && hostData.isEmpty()) {
            textOutHost.hostArea.setText("Insert IP Address / URL Address.");
        } else {
            hostInput = text;
            hostAskInput = hostAskText;
            iThread = new Thread(this);
            iThread.start();

        }
    }

    public void killHost() {
        if (iThread == null) {

            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {

            System.out.print("host " + hostInput + " " + hostAskInput);

            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("host " + hostInput + " " + hostAskInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (hostInputCli = in.readLine()) != null) {
                textOutHost.hostArea.appendText(hostInputCli + "\n");
            }
            in.close();
        } catch (Exception e) {
        }
    }
}








