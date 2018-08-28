
package servertcp;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.logging.*;
import servertcp.Cameneon.State;
public class Affiche implements Runnable {

    private final Cameneon camin;
    private ClientsList listeClients;
    
    public Affiche(Socket s, ClientsList listeClients, int id) throws IOException {
        camin=new Cameneon(s, id);
        this.listeClients=listeClients;
        listeClients.add(camin);
    }

   
    
    public void engineHelperAvantMutation(Cameneon primaire, Cameneon secondaire) {
        System.out.println("first color " + primaire.getColors().getColors());
        System.out.println("Second : " + secondaire.getColors().getColors());
        primaire.getWriter().println("game start with differnet camenon color");
        secondaire.getWriter().println("game start with differnet camenon color");
    }

    public void engineHelperApresMutation(Cameneon cama, Cameneon camb) {
        System.out.println("color after ecvolution: " + cama.getColors().getColors());
        cama.getWriter().println("color after evolution " + cama.getColors().getColors());
        camb.getWriter().println("color after evolution " + camb.getColors().getColors());
    }

    public void trainingEngine(Cameneon primaire) throws InterruptedException {
        Cameneon current = ServerTCP.getRandom(camin, this.listeClients);
        if (current != null) {
            if (current.getID() != primaire.getID() && current.getState() == State.Available) {
                primaire.setState(State.Unavailable);
                current.setState(State.Unavailable);
                primaire.getWriter().println("training");
                current.getWriter().println("You were chosen to make a court");                
                Thread.sleep(5000);
                primaire.getWriter().println("training stoped");
                current.getWriter().println("court finish");
                primaire.setState(State.Available);
                current.setState(State.Available);
            }                
        }
        else {
           camin.getWriter().println("no camenon");  
        }
    }
    
     @Override
    public void run() {        
        try {
            String colors = camin.getReader().readLine();
            if (colors.equalsIgnoreCase("camin.setColors(Cameneon.Colors.Blue);\n" +
"")) {
                camin.setColors(Cameneon.Colors.Blue);
            }
            else if(colors.equalsIgnoreCase("Yellow")) {
                camin.setColors(Cameneon.Colors.Yellow);
            }
            else {
                camin.setColors(Cameneon.Colors.Red);
            }
            camin.setState(State.Available);
            System.out.println("camenon color connected: " + camin.getColors().getColors());
            String line;
            while (!(line = camin.getReader().readLine()).equals("exit")) {
                 if (camin.getState()== State.Unavailable && (line.equalsIgnoreCase("train") || line.equalsIgnoreCase("eat"))) {
                     camin.getWriter().println("game start");
                 }
                 else {
                 if (line.equalsIgnoreCase("eat")) {
                    System.out.print("yam yam yam \n");
                     try {
                         Thread.sleep(3000);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Affiche.class.getName()).log(Level.SEVERE, null, ex);
                     }
                    System.out.print("\n");
                }              
                else if (line.equalsIgnoreCase("play")) {
                     System.out.print("Nice play ... ! \n");
                    try {
                        gameEngine(camin);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Affiche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.print("\n");
                }
                 else if (line.equalsIgnoreCase("train")) {
                    System.out.print("good job dude , keep pushing \n");
                    try {
                        trainingEngine(camin);
                    } catch (InterruptedException ex) {
                         Logger.getLogger(Affiche.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                }
                else {
                    
                }                     
              }
            }
            System.out.println("end");
        } catch (IOException ex) {
            Logger.getLogger(Affiche.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            System.out.println("Error");
        }
    }   
    public void gameEngine(Cameneon primaire) throws InterruptedException {
        Cameneon current = ServerTCP.getRandom(camin, this.listeClients);
        if (current != null) {
            if (current.getID() != primaire.getID() && current.getState()== State.Available) {
                primaire.setState(State.Unavailable);
                current.setState(State.Available.Unavailable);
                if (current.getColors() != primaire.getColors()) {
                    engineHelperAvantMutation(primaire, current);
                    if (current.getColors() == Cameneon.Colors.Blue) {
                       current.setColors(Cameneon.Colors.Red);
                       primaire.setColors(Cameneon.Colors.Red);
                    }else if (current.getColors() == Cameneon.Colors.Yellow) {
                       current.setColors(Cameneon.Colors.Blue);
                       primaire.setColors(Cameneon.Colors.Blue);                        
                    }
                    else if (current.getColors() == Cameneon.Colors.Red) {
                       current.setColors(Cameneon.Colors.Blue);
                       primaire.setColors(Cameneon.Colors.Blue);                        
                    }    
                    else if (current.getColors() == Cameneon.Colors.Red) {
                       current.setColors(Cameneon.Colors.Yellow);
                       primaire.setColors(Cameneon.Colors.Yellow);                        
                    }
                    else if (current.getColors() == Cameneon.Colors.Blue) {
                       current.setColors(Cameneon.Colors.Yellow);
                       primaire.setColors(Cameneon.Colors.Yellow);                        
                    }
                    else if (current.getColors() == Cameneon.Colors.Yellow) {
                       current.setColors(Cameneon.Colors.Red);
                       primaire.setColors(Cameneon.Colors.Red);                        
                    }                   
                    engineHelperApresMutation(primaire, current);
                }
                else {
                    current.getWriter().println("camenon same color");
                    primaire.getWriter().println("camenon same color");
                    System.out.println("both have the same color: " + primaire.getColors().getColors());
                }
                Thread.sleep(5000);
                primaire.setState(State.Available.Available);
                current.setState(State.Available);
            }                
        }
        else {
           camin.getWriter().println("you' re the only onr right now, come back later");  
        }
    }
    
}
