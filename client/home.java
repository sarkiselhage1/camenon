
package Clienttcp;

import java.io.IOException;

public class home {
        static final int N = 20;

        public static void main(String[] args) throws IOException, InterruptedException {

            Thread thread = new Thread(new Client());
            thread.start();
            thread.join();

        }
        
        public static void println(int x) {
            System.out.println(x);
        }
}
