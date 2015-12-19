package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;

public class Controller implements EventHandler<ActionEvent> {


    @FXML private TextField address;
    @FXML private Label ip, hostname;
    @FXML private TextArea whoText;
    @FXML private TextField traceField;
    @FXML private TextArea traceArea;




    public void handle(ActionEvent eventWhoIs) {
        whoIs who = new whoIs();
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        String text = address.getText();

        InetAddress address = null;
        try {
            address = InetAddress.getByName
                    (text);

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
        ///traceRoute
    }
    public void traceButton(ActionEvent eventTrace) throws UnknownHostException {
        String routeAns = traceField.getText();
        traceRoute trace = new traceRoute();
        trace.traceObject(routeAns);
        traceArea.setText("hello");
        traceArea.setText(trace.traceObject(routeAns));
    }


    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }
}
