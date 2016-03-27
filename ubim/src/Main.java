import com.company.DataStructImpl2;
import com.company.Simulator;

import java.util.Collection;
import java.util.Set;

/**
 * Created by rami on 3/27/2016.
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {

        //
        // Use DataStruct Implementation 1
        //
        System.out.println("1 --> Start.");
        String[] itemsPool = "abcdef".split("");
        Simulator simulator = new Simulator(itemsPool);

        simulator.start();
        Thread.sleep(10 * 1000);
        Collection<Object> maxValuesOnTermination = simulator.stop();

        System.out.println("maxValuesOnTermination = " + maxValuesOnTermination);
        System.out.println("1 --> Done.");

        //
        // Use DataStruct Implementation 2
        //
        System.out.println("2 --> Start.");
        simulator = new Simulator(itemsPool, new DataStructImpl2());

        simulator.start();
        Thread.sleep(10 * 1000);
        maxValuesOnTermination = simulator.stop();

        System.out.println("maxValuesOnTermination = " + maxValuesOnTermination);
        System.out.println("2 --> Done.");
    }
}
