package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;
import java.util.Timer;
import java.util.TimerTask;

public class Controller implements EventHandler<ActionEvent> {


    @FXML
    private TextField address;
    @FXML
    private Label ip, hostname;
    @FXML
    private TextArea whoText;
    @FXML
    private TextField traceField;
    @FXML
    public TextArea traceArea;
    @FXML
    private TextField traceIp;
    @FXML
    public TextArea pingArea;



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


    public void traceButton() {
        String text = traceIp.getText();
        traceRoute trace = new traceRoute(this);
        trace.changeText(this,text);




    }

    public String pingInput;

    public void pingButton() {

            Thread t1 = new Thread() {


                public void run() {

                    try {
                        Runtime r = Runtime.getRuntime();
                        Process p = r.exec("ping " + "8.8.8.8");
                        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        while ((pingInput = in.readLine()) != null) {
                            pingArea.appendText(pingInput);
                            pingArea.appendText("\n");
                            System.out.println(pingInput);
                        }
                        in.close();
                    } catch (Exception e) {
                    }
                }
            };
t1.start();
    }

    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }


}
