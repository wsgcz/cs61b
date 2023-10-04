package lab14;

import lab14lib.Generator;

public class StrangeBitwiseGenerator implements Generator {
    private int state;
    private int period;
    private int weirdState;
    public StrangeBitwiseGenerator(int period) {
        this.period = period;
        state = 0;
    }
    private double normalize() {
        double res = (double) weirdState / period * 2 - 1;
        return res;
    }
    @Override
    public double next() {
        weirdState = state & (state >>> 3) % period;
        double res = normalize();
        state += 1;
//        if (state == period) {
//            state = 0;
//        }
        return res;
    }
}
