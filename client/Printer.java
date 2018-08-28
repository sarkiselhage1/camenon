
package Clienttcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class Printer implements Runnable {
    
    BufferedReader stdin;
    PrintWriter send;
    Client client;
    String color;
            
    public Printer(BufferedReader stdin, PrintWriter send, Client client, String color) throws IOException {
        this.stdin = stdin;
        this.send = send;
        this.client = client;
        this.color = color;
    }

    @Override
    public void run() {
        send.printf( this.color +" \n");
        String line;
        try {
            while (!(line = stdin.readLine()).equals("exit")) {
                send.printf("%s\n", line);
            }
        } catch (IOException ex) {
            Logger.getLogger(Printer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
