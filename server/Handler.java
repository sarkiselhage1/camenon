
package servertcp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class Handler implements Runnable {
    
    ServerSocket serversocket;
    ClientsList lc;
    
    public Handler(ServerSocket serso, ClientsList lclient) {
        serversocket = serso;
        lc = lclient;
    }
    
    @Override
    public void run() {
        int i = 0;
        while (true) {
            i++;
            Socket serviceSocket;
            try {
                serviceSocket = serversocket.accept();
                new Thread(new Affiche(serviceSocket, lc, i)).start();
            } catch (IOException ex) {
                Logger.getLogger(Handler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
