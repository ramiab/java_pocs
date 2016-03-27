import com.company.Simulator;

import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //
        // Process String objects
        //
        System.out.println("1 --> Start.");
        String[] itemsPool = "abcdef".split("");
        Simulator simulator = new Simulator(itemsPool);

        simulator.start();
        Thread.sleep(10 * 1000);
        Set<Object> maxValuesOnTermination = simulator.stop();

        System.out.println("maxValuesOnTermination = " + maxValuesOnTermination);
        System.out.println("1 --> Done.");

        //
        // Process Integer objects
        //
        System.out.println("2 --> Start.");
        Integer[] itemsPool2 = {1000, 2000, 3000, 4000, 5000 };
        Simulator simulator2 = new Simulator(itemsPool2);

        simulator2.start();
        Thread.sleep(10 * 1000);
        Set<Object> maxValuesOnTermination2 = simulator2.stop();

        System.out.println("maxValuesOnTermination2 = " + maxValuesOnTermination2);
        System.out.println("2 --> Done.");
    }
}
