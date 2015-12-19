package sample;


        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStreamReader;

public class traceRoute{
    public String traceObject(String traceName) {
        String result = null;
        try {
            Runtime r = Runtime.getRuntime();

            Process p = r.exec("traceroute " + traceName);

            BufferedReader in =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                System.out.println(inputLine);
                result += inputLine;
            }
            in.close();

        } catch (IOException e) {
            System.out.println(e);
        }
        if (result.equals(null)) {
            System.out.println("Please enter address.");
        }
        return traceName.toString();
    }

}
