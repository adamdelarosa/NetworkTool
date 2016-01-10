package Network;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ping extends Thread {

    private String to;

    public ping(String to) {
        this.to = to;
    }

    @Override
    public void run() {
        System.out.println("hello " + to);
    }

}
