package Network;


import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Duration;
import sun.font.TextLabel;


import java.awt.*;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;
import java.util.Observable;

public class Controller implements EventHandler<ActionEvent> {

    @FXML
    public TextArea traceArea,whoTextArea,pingArea,nslookupArea;
    @FXML
    public TextField traceField,whIpField,pingField,nslookupField;
    @FXML
    public ProgressIndicator traceProgressBar,pingProgressBar;
    @FXML
    public Button traceButtonOnAction,pingButtonOnAction,pingButtonStopOnAction,nslookupButtonOnAction;
    @FXML
    public ComboBox <String>  whoBox;

    private traceRoute traceCon;
    private ping pingCon;
    private Nslookup nslookupCon;
    private whoIs whoisCon;


    public void handle(ActionEvent eventWhoIs) {}


    ////WhoIS////

    public void whoIsButton(ActionEvent eventWhoIs) {


            String text = whIpField.getText();
        if(text == null || text.isEmpty()){
            whoTextArea.setText("Please insert URL or IP.");
        }else {
            String whoChoiseBox = whoBox.getValue();
            whoisCon = new whoIs(this);

            whoisCon.getSelectedItem();

            System.setProperty("apple.laf.useScreenMenuBar", "true");


            InetAddress address = null;
            try {
                address = InetAddress.getByName(text);
                whoTextArea.setText(whoisCon.getWhois(text));

            } catch (UnknownHostException e) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Wrong Input");
                alert.setHeaderText("Wrong input");
                alert.setContentText("Please insert a valid Address or IP.");
                alert.showAndWait();
            }
        }
    }

    ObservableList<String> servers = FXCollections.observableArrayList(
            "whois.internic.net",
            "whois.networksolutions.com",
            "whois.arin.net",
            "whois.nic.mil",
            "whois.ripe.net",
            "whois.apnic.net",
            "whois.nic.ad.jp"
    );

    @FXML
    public void initialize(){
        whoBox.setItems(servers);
        whoBox.setValue("whois.internic.net");

    }

    public void whoIsButtonClear(){
        whoTextArea.setText("");
    }

    ////TraceRoute////

    public void traceButton() {
        traceArea.setText("");
        String text = traceField.getText();
        traceCon = new traceRoute(this,this,this,true,text);
        traceCon.traceAction(text);
        System.out.print(text);
    }
    public void traceButtonStop() {
        traceCon.killTraceRoute();
    }

    ////Ping////


    public void pingButton() {
        pingArea.setText("");
        String text = pingField.getText();
        pingCon = new ping(this,this,this,true,text,this);
        pingCon.pingAction(text);



    }
    public void pingButtonStop() {
        pingCon.killPing();
    }

    ////Nslookup////

    public void nslookupButton() throws IOException {
        nslookupArea.setText("");
        String text = nslookupField.getText();
        nslookupCon = new Nslookup(this,true,text);
        nslookupCon.nslookupAction(text);
    }
    public void nslookupButtonStop(){
        nslookupCon.killNslookup();
        nslookupArea.setText("");
    }

    ////WebSite Button www.adamdelarosa.com////

    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }
}
