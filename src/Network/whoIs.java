package Network;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import org.apache.commons.net.WhoisClient;

import java.io.IOException;
import java.net.SocketException;


public class whoIs {

    private Controller checkBoxUrl;

    public whoIs(Controller checkboxurl) {
        checkBoxUrl = checkboxurl;
    }


    Controller con = new Controller();

    @FXML
    public String getSelectedItem() {

        String urlFromCheckBox = checkBoxUrl.whoBox.getValue();
        String u = checkBoxUrl.whoBox.getPromptText();
        return urlFromCheckBox;
    }

    //Whois servers:

    private String DEFAULT_HOST_SERVER = "whois.internic.net";

    public String getWhois(String domainName) {

        StringBuilder result = new StringBuilder("");

        WhoisClient whois = new WhoisClient();
        try {
            whois.connect(getSelectedItem());
            String whoisData1 = whois.query("=" + domainName);
            result.append(whoisData1);
            whois.disconnect();


        } catch (SocketException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("We have a Error...");
            alert.setContentText("SocketException - Error.");
            alert.showAndWait();
            e.printStackTrace();
        } catch (IOException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Error");
            alert.setHeaderText("We have a Error...");
            alert.setContentText("SocketException - Error.");
            alert.showAndWait();
            e.printStackTrace();
            e.printStackTrace();
        }
        return result.toString();
    }
}
