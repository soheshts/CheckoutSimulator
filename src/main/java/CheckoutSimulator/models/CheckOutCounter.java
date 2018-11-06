package CheckoutSimulator.models;

import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import static CheckoutSimulator.constants.Constants.*;

public class CheckOutCounter extends Thread {
    private int counterId;
    private int checkOutLimit;
    private int lostCustomers;
    private boolean isExpressCounter;
    private Customer customer;
    private BlockingQueue<Customer> customerQueue = new ArrayBlockingQueue<Customer>(QUEUE_MAX_SIZE);
    private Thread thread;

    @Override
    public void run() {
        System.out.println("Name : " + counterId + "Customer queue size : " + customerQueue.size());
        try {
            while (SharedResource.isSimulationOn) {
                processCustomer(customerQueue.take());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        }
    }

    public CheckOutCounter(int counterId, boolean isExpressCounter) {
        this.counterId = counterId;
        this.isExpressCounter = isExpressCounter;
        thread = new Thread(this, String.valueOf(counterId));
        thread.start();
    }

    private void processCustomer(Customer customer) {
        System.out.println("Counter : " + counterId + " Processing Customer : "+customer.getCustomerId());
        List<Products> products = customer.getItems();
        /*double totalWaitTime = 0;
        for (Products product : products) {
            totalWaitTime += product.getProcessTime();
        }
        totalWaitTime*= 1000;*/
        System.out.println("Counter : " + counterId + " Wait time for customer : " +customer.getCustomerId()+"is "+ customer.getTotalWaitTime());

        try {
            System.out.println("Counter : " + counterId + "started process customer "+customer.getCustomerId()+" at "+new Date());
            Thread.sleep((long) customer.getTotalWaitTime());
            System.out.println("Counter : " + counterId + "stopped process customer "+customer.getCustomerId()+" at "+new Date());
            SharedResource.totalCustomersProcessed++;

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Counter : " + counterId + " Processing Customer : "+customer.getCustomerId()+" completed");

    }

    public int getSize() {
//        System.out.println("Counter id : " + counterId + " cusomer queue size : " + customerQueue.size());
        return customerQueue.size();
    }

    public void addToQueue(Customer customer) {
        customerQueue.add(customer);
//        System.out.println("Counter id : " + counterId + " customer added");
    }

    public int getCounterId() {
        return counterId;
    }

    public void setCounterId(int counterId) {
        this.counterId = counterId;
    }

    public int getCheckOutLimit() {
        return checkOutLimit;
    }

    public void setCheckOutLimit(int checkOutLimit) {
        this.checkOutLimit = checkOutLimit;
    }

    public int getLostCustomers() {
        return lostCustomers;
    }

    public void setLostCustomers(int lostCustomers) {
        this.lostCustomers = lostCustomers;
    }

    public boolean isExpressCounter() {
        return isExpressCounter;
    }

    public void setExpressCounter(boolean expressCounter) {
        isExpressCounter = expressCounter;
    }
}
