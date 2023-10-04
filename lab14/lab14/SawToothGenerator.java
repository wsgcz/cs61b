package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int state;
    private int period;
    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }
    private double normalize() {
        return -1 + (double) (state % period) / period * 2;
    }
    @Override
    public double next() {
        double res = normalize();
        state += 1;
        return res;
    }
}
