package CheckoutSimulator.utils;

import java.util.Random;

public class Util {
    public static double getRandomNumber(double min, double max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        double randomNum = min + (int) (Math.random() * max);

        return randomNum;
    }
}
