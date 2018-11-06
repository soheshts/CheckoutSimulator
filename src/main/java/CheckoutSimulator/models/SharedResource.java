package CheckoutSimulator.models;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static CheckoutSimulator.constants.Constants.QUEUE_MAX_SIZE;

public class SharedResource {
    public static BlockingQueue<Customer> sharedQueue = new ArrayBlockingQueue<Customer>(QUEUE_MAX_SIZE);
    public static List<CheckOutCounter> checkOutCounters = new ArrayList<CheckOutCounter>();
    public static List<BlockingQueue> queues = new ArrayList<BlockingQueue>();

    public static int totalProducts = 0;
    public static int totalCustomers = 0;
    public static int totalCustomersProcessed = 0;
    public static int leftCustomers = 0;
    public static int totalCheckouts = 0;
    public static int totalExpressCheckouts = 0;
    public static int simulationTime = 0;
    public static int totalProductsProcessed = 0;
    public static boolean isSimulationOn = true;

}
