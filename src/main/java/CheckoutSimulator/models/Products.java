package CheckoutSimulator.models;

import CheckoutSimulator.utils.Util;

import static CheckoutSimulator.constants.Constants.PRODUCT_PROCESS_TIME_HIGH;
import static CheckoutSimulator.constants.Constants.PRODUCT_PROCESS_TIME_LOW;

public class Products {
    double processTime;

    public Products() {
        this.processTime = Util.getRandomNumber(PRODUCT_PROCESS_TIME_LOW, PRODUCT_PROCESS_TIME_HIGH);
    }

    public double getProcessTime() {
        return processTime;
    }
}
