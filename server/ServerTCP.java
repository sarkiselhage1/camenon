package servertcp;

import java.io.*;
import java.net.*;
import java.util.*;

public class ServerTCP {
      
    static Random rnd;
    
    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket l = new ServerSocket(22);
        rnd = new Random();
        ClientsList listeClients = new ClientsList();
        System.out.println(l.getLocalSocketAddress());
        Thread a = new Thread(new Handler(l, listeClients));
        a.start();
        a.join();
    }
    
    static public synchronized Cameneon getRandom(Cameneon forThis, ClientsList listeClients) {
        if (listeClients.lc.size() > 1) {
            int index = rnd.nextInt(listeClients.lc.size());
            Cameneon toReturn = listeClients.lc.get(index);
            if (toReturn != forThis && toReturn.getState() == Cameneon.State.Available) {
                return toReturn;
            }
            else {
                return getRandom(forThis, listeClients);
            }
        }
        else {
            return null;
        }
    }
}
