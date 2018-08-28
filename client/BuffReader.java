
package Clienttcp;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;
import java.io.*;
import java.io.*;
public class BuffReader implements Runnable {

    BufferedReader inputreader;
    
    public BuffReader(BufferedReader inputreader) throws IOException {
        this.inputreader = inputreader;
    }

    @Override
    public void run() {
        try {
            String line;
            while (!(line = inputreader.readLine()).equals("exit")) {
                System.out.println(line);
            }
        } catch (IOException ex) {
            Logger.getLogger(BuffReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
