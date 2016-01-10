package Network;

import javafx.fxml.FXML;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class traceRoute extends Controller implements Runnable {

    @FXML
    private Controller textOutTrace, traceButt, traceBar , traceStopButton ;
    private String inputLine;
    private Process runningProcess;
    private Runtime r = null;
    private BufferedReader in;
    private String newTraceIp;




    public traceRoute(Controller ta, Controller butt, Controller bar, Controller stop) {
        textOutTrace = ta;
        traceButt = butt;
        traceBar = bar;
        traceStopButton = stop;

    }

    private int num;
    private String traceData;

    public traceRoute(String asdf ,int qwer){
        newTraceIp = asdf;
        num = qwer;
    }

    private Thread iThread;
    private boolean loop = true;


    public String traceAction(String traceDataFromControler) {
       // if (newTraceIp != null && newTraceIp.isEmpty()) {}
            textOutTrace.traceArea.setText("Insert IP Address / URL Address.");
        iThread = new Thread(this);
        iThread.start();

        return traceDataFromControler;
    }

    public void killTraceRoute(){
        if (iThread == null) {
            return;
        }

//        iThread.interrupt();
        loop = false;
    }


    @Override
    public void run() {
        try {
            System.out.print(num);
            r = Runtime.getRuntime();
            runningProcess = r.exec("traceroute " + "8.8.8.8");
            in = new BufferedReader(new InputStreamReader(runningProcess.getInputStream()));
            {
                while (loop && (inputLine = in.readLine()) != null) {

                    System.out.print(num);
                    javafx.application.Platform.runLater(() -> textOutTrace.traceArea.appendText(inputLine + "\n"));
                    if(num == 1){
                        runningProcess.destroy();
                        in.close();
                        inputLine = null;
                        break;
                    }
                    traceButt.traceButtonOnAction.setDisable(true);
                    traceBar.traceProgressBar.setVisible(true);

                }
            }
            traceButt.traceButtonOnAction.setDisable(false);
            traceBar.traceProgressBar.setVisible(false);


            in.close();
        } catch (Exception e) {
        }
    }
}








