package CheckoutSimulator.service;

import CheckoutSimulator.models.CheckOutCounter;
import CheckoutSimulator.models.SharedResource;

import java.util.ArrayList;
import java.util.List;


public class CounterServiceImpl implements CounterService {
//    @Override
    public List<CheckOutCounter> createCounters() {
        List<CheckOutCounter> checkOutCounters = new ArrayList<CheckOutCounter>();
        for (int a = 1; a <= SharedResource.totalCheckouts ; a++) {
            if (a <= SharedResource.totalExpressCheckouts ) {
                checkOutCounters.add(new CheckOutCounter(a, true));
                System.out.println("Express Counter : " + a + " created");
            } else {
                checkOutCounters.add(new CheckOutCounter(a, false));
                System.out.println("Normal Counter : " + a + "created");
            }
        }
        System.out.println("Created counters : " + SharedResource.totalCheckouts);
        return checkOutCounters;
    }

}
