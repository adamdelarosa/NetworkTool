package Network;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;

public class Controller implements EventHandler<ActionEvent> {


    @FXML
    private Label whoIpLabel, whoIpLabelHostName;
    @FXML
    public TextArea traceArea, whoTextArea;
    @FXML
    private TextField traceField, whIpField;
    @FXML
    public Button traceButtonOnAction;
    @FXML
    public ProgressIndicator traceProgressBar;


    public void handle(ActionEvent eventWhoIs) {
        whoIs who = new whoIs();
        System.setProperty("apple.laf.useScreenMenuBar", "true");

        String text = whIpField.getText();

        InetAddress address = null;
        try {
            address = InetAddress.getByName(text);

            whoIpLabel.setText(address.getCanonicalHostName());
            whoIpLabelHostName.setText(address.getHostName());
            whoTextArea.setText(who.getWhois(text));

        } catch (UnknownHostException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Wrong Input");
            alert.setHeaderText("Wrong input");
            alert.setContentText("Please insert a valid Address or IP.");
            alert.showAndWait();
        }
    }


    public void traceButton() {
        traceArea.setText("");
        String text = traceField.getText();
        traceRoute trace = new traceRoute(this, this, this);
        trace.changeText(this, text);

    }

    public void pingButton() {
    }

    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }
}
