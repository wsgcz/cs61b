package lab14;

import lab14lib.Generator;

public class AcceleratingSawToothGenerator implements Generator {
    private int period;
    private double factor;
    private int state;
    private double res;
    public AcceleratingSawToothGenerator(int period, double factor) {
        this.factor = factor;
        this.period = period;
        state = 0;
    }
    private double normalize() {
        if (state == period) {
            res = 0;
            period = (int) (period * factor);
            state = 0;
        } else {
            res = -1 + (double) state / period * 2;
        }
        return res;
    }
    @Override
    public double next() {
        double result = normalize();
        state += 1;
        return result;
    }
}
