package Clienttcp;

import java.io.*;
import java.net.*;
import java.util.*;
import java.util.logging.*;

public class Client implements Runnable {

    
    
    Socket socket = null;
    ArrayList<String> colorsData = new ArrayList<String>();
    Random randomGenerator;
    
    @Override
    public void run() {
        try {
            try {
                Client cl = new Client();
            } catch (InterruptedException ex) {
                Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private static BufferedReader getInput(InputStream is) throws IOException {
        return new BufferedReader(new InputStreamReader(is));
    }

    private static BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }

    private static PrintWriter getoutput(Socket p) throws IOException {
        //Avec autoflush
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()), true);
    }

    public Client() throws IOException, InterruptedException {
        try {
            randomGenerator = new Random();
            System.out.println("Hello Client:");
            System.out.println("- Write 'eat' \n" + "- Write 'train' \n " + "- Write 'play' \n ");
            colorsData.add("Red");
            colorsData.add("Yellow");
            colorsData.add("Blue");
            socket = new Socket("localhost", 22);
            int index = randomGenerator.nextInt(colorsData.size());
            BufferedReader ir = getInput(socket);
            BufferedReader stdin = getInput(System.in);
            PrintWriter envoyer = getoutput(socket);
            Thread reader = new Thread(new BuffReader(ir));
            Thread printer = new Thread(new Printer(stdin, envoyer, this, colorsData.get(index)));
            printer.start();
            reader.start();
            reader.join();
            printer.join();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    
}
