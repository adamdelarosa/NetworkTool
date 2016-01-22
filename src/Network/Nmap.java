package Network;

import org.nmap4j.Nmap4j;
import org.nmap4j.core.nmap.NMapExecutionException;
import org.nmap4j.core.nmap.NMapInitializationException;
import org.nmap4j.data.NMapRun;

public class Nmap {


    public void nmapRunner()  {

        Nmap4j nmap4j = new Nmap4j("/usr/local");

        nmap4j.includeHosts("192.168.1.1-255");
        nmap4j.excludeHosts("192.168.1.110");
        nmap4j.addFlags("-T3 -oX - -O -sV");
        try {
            nmap4j.execute();
        } catch (NMapInitializationException e) {
            e.printStackTrace();
        } catch (NMapExecutionException e) {
            e.printStackTrace();
        }


        System.out.print(nmap4j.getResult());




    }

}
