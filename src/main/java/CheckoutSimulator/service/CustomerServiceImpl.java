package CheckoutSimulator.service;

import CheckoutSimulator.models.Customer;
import CheckoutSimulator.models.SharedResource;
import CheckoutSimulator.utils.Util;

import java.util.Timer;
import java.util.TimerTask;

public class CustomerServiceImpl extends TimerTask implements CustomerService {
    int customerId = 1;
    Timer timer;

//    @Override
    public void generateCustomers() {
        TimerTask timerTask = new CustomerServiceImpl();
         timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 10 * 1000);

    }

    @Override
    public void run() {
        int noOfCustomersToGenerate = (int) Util.getRandomNumber(1, 50);
        SharedResource.totalCustomers+=noOfCustomersToGenerate;
        for (int a = 0; a < noOfCustomersToGenerate; a++,customerId++) {
            SharedResource.sharedQueue.add(new Customer(customerId));
        }
        System.out.println("SharedQueue current size: " + SharedResource.sharedQueue.size() + " No of Customers created : " + noOfCustomersToGenerate);

    }

    public void cancelTimer() {
        timer.cancel();
    }
}
