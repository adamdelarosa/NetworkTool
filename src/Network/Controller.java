package Network;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.net.URI;


public class Controller implements EventHandler<ActionEvent> {

    @FXML
    public TextArea traceArea,whoTextArea,pingArea,nslookupArea,digArea,hostArea,cardArea;
    @FXML
    public TextField traceField,whIpField,pingField,nslookupField,digField,digAskField,hostField,hostAskField;
    @FXML
    public ProgressIndicator traceProgressBar,pingProgressBar,netstatProgressBar;
    @FXML
    public Button traceButtonOnAction,pingButtonOnAction,pingButtonStopOnAction,traceStopButtonOnAction,nslookupStopOnAction;
    @FXML
    public ComboBox <String>  whoBox;

    private traceRoute traceCon;
    private ping pingCon;
    private Nslookup nslookupCon;
    private whoIs whoisCon;
    private Dig digCon;
    private Host hostCon;
    private NetworkCard cardCon;



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
        traceCon = new traceRoute(this,this,this,true,text,this);
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

    public void nslookupButton(){
        nslookupArea.setText("");
        String text = nslookupField.getText();
        nslookupCon = new Nslookup(this,true,this);
        nslookupCon.nslookupAction(text);
    }
    public void nslookupButtonStop(){
        nslookupCon.killNslookup();
        nslookupArea.setText("");
    }

    ////Dig////

    public void digButton() {
        digArea.setText("");
        String text = digField.getText();
        String digAskText = digAskField.getText();
        digCon = new Dig(this,true,text);
        digCon.digAction(text, digAskText);
    }
    public void digButtonStop() {
        digCon.killDig();
        digArea.setText("");
    }

    ////Host////

    public void hostButton() {
        hostArea.setText("");
        String text = hostField.getText();
        String hostAskText = hostAskField.getText();
        hostCon = new Host(this,true,text);
        hostCon.hostAction(text, hostAskText);
    }
    public void hostButtonStop() {
        hostCon.killHost();
        hostArea.setText("");
    }

    ////Network Card////

    public void CardButton() {
        cardArea.setText("");
        cardCon = new NetworkCard(this,true);
        cardCon.cardAction();
    }
    public void cardButtonStop() {
        cardCon.killCard();
        cardArea.setText("");
    }



    ////WebSite Button www.adamdelarosa.com////

    public void link(ActionEvent event) throws Exception {
        {
            java.awt.Desktop.getDesktop().browse(new URI("http://www.adamdelarosa.com"));
        }
    }
}
