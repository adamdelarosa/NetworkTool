package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dig implements Runnable {

    @FXML
    private Controller textOutDig;
    private String digInputCli,digData,digInput,digAskInput;
    private Thread iThread;
    private boolean shutdown = false;


    public Dig(Controller tadig,Boolean stopdig,String dataDig) {
        textOutDig = tadig;
        shutdown = stopdig;
        digData = dataDig;

    }

    public void digAction(String text,String digAskText) {
        if (digData != null && digData.isEmpty()) {
            textOutDig.digArea.setText("Insert IP Address / URL Address.");
        } else {
            digInput = text;
            digAskInput = digAskText;
            iThread = new Thread(this);
            iThread.start();

        }
    }

    public void killDig() {
        if (iThread == null) {

            return;
        } else {
            shutdown = false;
        }
    }

    @Override
    public void run() {
        try {

System.out.print("dig " + digInput + " " + digAskInput);

            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("dig " + digInput + " " + digAskInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (digInputCli = in.readLine()) != null) {
                textOutDig.digArea.appendText(digInputCli + "\n");
            }
            in.close();
        } catch (Exception e) {
        }
    }
}








