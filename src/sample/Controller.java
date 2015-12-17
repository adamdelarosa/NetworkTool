package sample;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Hyperlink;
import javafx.scene.web.WebView;
import sun.net.www.URLConnection;
import javafx.scene.web.WebEngine;

import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.Desktop.Action;
import java.net.URI;
import java.util.ResourceBundle;


public class Controller implements EventHandler<ActionEvent> {


    @FXML
    private TextField address;
    @FXML
    private Label ip, hostname;
    @FXML
    private TextArea whoText;
    @FXML
    private Hyperlink link;
    @FXML
    private MenuBar menuBar;

    whoIs who = new whoIs();


    public void handle(ActionEvent event) {
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
    }


    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }
}
