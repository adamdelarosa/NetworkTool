package Network;

import javafx.fxml.FXML;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Dig implements Runnable {

    @FXML
    private Controller textOutDig, digButt, digBar,digStopOnAction;
    private String digInputCli, digData, digInput;
    private Thread iThread;
    private boolean shutdown = false;


    public Dig(Controller tadig, Controller buttdig, Controller bardig, Boolean stopdig, String dataDig,Controller digstoponaction) {
        textOutDig = tadig;
        digButt = buttdig;
        digBar = bardig;
        shutdown = stopdig;
        digData = dataDig;
        digStopOnAction = digstoponaction;

    }

    public void digAction(String text) {
        digInput = text;
        iThread = new Thread(this);
        iThread.start();

        if (digData != null && digData.isEmpty()) {
            textOutDig.digArea.setText("Insert IP Address / URL Address.");
        } else {
            digInput = text;
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


            Runtime r = Runtime.getRuntime();
            Process runningProcess = r.exec("dig " + digInput);
            BufferedReader in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));

            while (shutdown && (digInputCli = in.readLine()) != null) {

                textOutDig.digArea.appendText(digInputCli + "\n");
                digButt.digButtonOnAction.setDisable(true);
            }

            digButt.digButtonOnAction.setDisable(false);
            in.close();
        } catch (Exception e) {
        }
    }

}








