package CheckoutSimulator.controller;

import CheckoutSimulator.models.Customer;
import CheckoutSimulator.models.SharedResource;
import CheckoutSimulator.service.CounterService;
import CheckoutSimulator.service.CounterServiceImpl;
import CheckoutSimulator.service.CustomerService;
import CheckoutSimulator.service.CustomerServiceImpl;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ArrayBlockingQueue;

import static CheckoutSimulator.constants.Constants.*;

public class CheckOutCounterController /*implements Runnable*/ {
    CounterService counterService = new CounterServiceImpl();
    CustomerService customerService = new CustomerServiceImpl();
    Customer customer;

    //    @Override
    public void run() {
        try {

            while (SharedResource.isSimulationOn) {
                customer = SharedResource.sharedQueue.take();
                if (customer != null) {
                    for (int a = 0; a < SharedResource.checkOutCounters.size(); a++) {
                        if (SharedResource.checkOutCounters.get(a).getSize() < QUEUE_DEPTH_CUSTOMER_DISLIKE) {
                            if ((SharedResource.checkOutCounters.get(a).isExpressCounter() && customer.isJoinExpressCounter()) ||
                                    (!SharedResource.checkOutCounters.get(a).isExpressCounter() && !customer.isJoinExpressCounter())) {
                                SharedResource.checkOutCounters.get(a).addToQueue(customer);
                                customer.setAddedToQueue(true);
                                System.out.println("Customer " + customer.getCustomerId() + " added to Counter : " + (a + 1) + " with " + customer.getNoOfItemsInTrolley() + " items");
                                break;
                            }
                        }

                    }
                    if (!customer.isAddedToQueue()) {
                        SharedResource.leftCustomers++;
                        System.out.println("Customer " + customer.getCustomerId() + " left!!!. Total customers left : " + SharedResource.leftCustomers + " products : " + customer.getNoOfItemsInTrolley());
                    }
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void createCounters() {
        SharedResource.checkOutCounters = counterService.createCounters();
    }

    public void createCustomers() {
        customerService.generateCustomers();
    }

   /* public void createQueues() {
        for (int a = 0; a < CHECKOUT_COUNTER_LIMIT; a++) {
            SharedResource.queues.add(new ArrayBlockingQueue(QUEUE_MAX_SIZE));
        }
    }*/

    public void start() {
//        finaCheckOutCounterController controller = new CheckOutCounterController();
        createCustomers();
        createCounters();
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                SharedResource.isSimulationOn = false;
                customerService.cancelTimer();
                System.out.println("Total customers : " + SharedResource.totalCustomers);
                System.out.println("Total products : " + SharedResource.totalProducts);
                System.out.println("Total customers processed : " + SharedResource.totalCustomersProcessed);
            }
        }, SharedResource.simulationTime*1000 );
        /*Thread thread = new Thread();
        thread.start();*/
        run();
    }

}
