import com.company.Simulator;

import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public class Main {
        public static void main(String[] args) throws InterruptedException {

            String[] itemsPool = "abcdef".split("");
    //        String[] itemsPool = "abcdefghijklmnopqrstuvwxyz".split("");

            Simulator simulator = new Simulator(itemsPool);
            simulator.start();
            Thread.sleep(30*1000);
            Set<String> maxValuesOnTermination = simulator.stop();
            System.out.println("maxValuesOnTermination = "+maxValuesOnTermination);
            System.out.println("Done.");
        }
}
