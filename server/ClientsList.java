
package servertcp;
import java.io.IOException;
import java.util.*;
public class ClientsList {
    List<Cameneon> lc;
    
    public ClientsList() {
        lc = new ArrayList<>();
    }    
    public void notifyAll(String m, Cameneon e) {
        for (Cameneon ic : lc) {
            if (ic != e) {
                System.out.printf(m+" a "+ic.getServiceClientSocket().getRemoteSocketAddress() +" \n ");
                ic.getWriter().println(m);
            }
        }
    }
    public void add(Cameneon camenon){
        lc.add(camenon);
    }
    
    public void remove(Cameneon camenon) throws IOException{
        camenon.getReader().close();
        camenon.getWriter().close();
        camenon.getServiceClientSocket().close();
        lc.remove(camenon);
    }
}
