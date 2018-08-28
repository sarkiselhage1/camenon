
package servertcp;
import java.io.*;
import java.net.Socket;

public class Cameneon {
     public enum State {
        Available,Unavailable,Unregistered;
    } 
    public enum Colors {
       Blue,Yellow,Red;
        
        public String getColors() {
            if (this == Blue) {
                return "Blue";
            }
            else if (this == Red) {
                return "Red";
            }
            else {
                return "Yellow";
            }
        }
    }
    private Socket serviceClientSocket;
    private BufferedReader reader;
    private PrintWriter writer;
    
    private int ID;
    private Colors colors;
    private Cameneon partenaire;
    private State state;
    
    public Cameneon(Socket s, int id) throws IOException {
        serviceClientSocket=s;
        this.ID = id;
        this.state = State.Unregistered;
        reader = getInput(s);
        writer = getoutput(s);
    }
    BufferedReader getInput(Socket p) throws IOException {
        return new BufferedReader(new InputStreamReader(p.getInputStream()));
    }
    PrintWriter getoutput(Socket p) throws IOException {
        return new PrintWriter(new OutputStreamWriter(p.getOutputStream()),true);
    }
    public Socket getServiceClientSocket() {
        return serviceClientSocket;
    }
    public void setServiceClientSocket(Socket serviceClientSocket) {
        this.serviceClientSocket = serviceClientSocket;
    }
    public BufferedReader getReader() {
        return reader;
    }
    public void setReader(BufferedReader reader) {
        this.reader = reader;
    }
    public PrintWriter getWriter() {
        return writer;
    }
    public void setWriter(PrintWriter writer) {
        this.writer = writer;
    }
    
    public Colors getColors() {
        return this.colors;
    }
        
    public void setColors(Colors c) {
        this.colors = c;
    }
    
    public Cameneon getPartenaire() {
        return this.partenaire;
    }
    
    public void setPartenaire(Cameneon c) {
        this.partenaire = c;
    }

    public int getID() {
        return this.ID;
    }
    
    public State getState() {
        return this.state;
    }
    
    public void setState(State state) {
        this.state = state;
    }
}
