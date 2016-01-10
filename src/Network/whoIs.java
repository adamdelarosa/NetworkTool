package Network;

import javafx.scene.control.Alert;
import org.apache.commons.net.WhoisClient;
import java.io.IOException;
import java.net.SocketException;

public class whoIs {
        //Whois servers:
        private String DEFAULT_HOST2 = "whois.internic.net";

    public String getWhois(String domainName) {

        StringBuilder result = new StringBuilder("");

        WhoisClient whois = new WhoisClient();
        try {

            whois.connect(WhoisClient.DEFAULT_HOST);
            String whoisData1 = whois.query("=" + domainName);
            result.append(whoisData1);
            whois.disconnect();


        } catch (SocketException e){
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
