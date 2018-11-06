package CheckoutSimulator.models;

import CheckoutSimulator.utils.Util;

import java.util.ArrayList;
import java.util.List;

import static CheckoutSimulator.constants.Constants.EXPRESS_COUNTER_ITEM_LIMIT;
import static CheckoutSimulator.constants.Constants.PRODUCTS_PER_TROLLEY_LIMIT;

public class Customer {
    private int customerId;
    private int noOfItemsInTrolley;
    private double totalWaitTime;
    private boolean joinExpressCounter;
    private boolean addedToQueue;
    private List<Products> items = new ArrayList<Products>();

    public Customer(int customerId) {
        this.customerId = customerId;
        this.noOfItemsInTrolley = (int) Util.getRandomNumber(1, PRODUCTS_PER_TROLLEY_LIMIT);
        SharedResource.totalProducts+=noOfItemsInTrolley;
                createProducts();
    }

    private void createProducts() {
        int i = 0;
        while (i < noOfItemsInTrolley) {
            items.add(new Products());
            i++;
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getNoOfItemsInTrolley() {
        return noOfItemsInTrolley;
    }

    public List<Products> getItems() {
        return items;
    }

    public double getTotalWaitTime() {
        for (Products product : items) {
            totalWaitTime += product.getProcessTime();
        }
        return totalWaitTime*1000;
    }

    public boolean isJoinExpressCounter() {
        if (noOfItemsInTrolley <= EXPRESS_COUNTER_ITEM_LIMIT) {
            this.joinExpressCounter = true;
        }
        return joinExpressCounter;
    }

    public boolean isAddedToQueue() {
        return addedToQueue;
    }

    public void setAddedToQueue(boolean addedToQueue) {
        this.addedToQueue = addedToQueue;
    }
}
