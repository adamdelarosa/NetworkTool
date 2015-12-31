package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<ActionEvent> {


    @FXML private TextField address;
    @FXML private Label ip, hostname;
    @FXML private TextArea whoText;
    @FXML private TextField traceField;
    @FXML public  TextArea traceArea;




    public void handle(ActionEvent eventWhoIs) {
        whoIs who = new whoIs();
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        String text = address.getText();

        InetAddress address = null;
        try {
            address = InetAddress.getByName(text);

            ip.setText(address.getCanonicalHostName());
            hostname.setText(address.getHostName());
            whoText.setText(who.getWhois(text));

        } catch (UnknownHostException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Input");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please insert a valid Address or IP.");
            alert.showAndWait();
        }
    }

    public void traceButton(ActionEvent eventTrace) throws UnknownHostException {
        traceRoute trace = new traceRoute();
        trace.run();
        traceArea.setText("Check...");
        new Timer().schedule(
                new TimerTask() {
                    @Override
                    public void run() {
                        traceArea.appendText(trace.enjoy());
                    }
                }, 5000);

        traceArea.setText(trace.enjoy());
        String traceData = trace.enjoy();




    }
    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }


}
