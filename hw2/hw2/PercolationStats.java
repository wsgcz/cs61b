package hw2;
import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
public class PercolationStats {
    private int time;
    private int number;
    private double[] res;
    public PercolationStats(int N, int T, PercolationFactory pf) {
        // perform T independent experiments on an N-by-N grid
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("T must be larger than 0");
        }
        int count;
        time = T;
        number = N;
        res = new double[N];
        for (int i = 0; i < time; i += 1) {
            Percolation pl = pf.make(N);
            count = 0;
            StdRandom.setSeed(i);
            while (! pl.percolates()) {
                int row = (int) Math.round(StdRandom.uniform() * (N - 1));
                int col = (int) Math.round(StdRandom.uniform() * (N - 1));
                pl.open(row, col);
                count += 1;
            }
            res[i] = (double) count / (number * number);
        }
    }

    public double mean() {
        return StdStats.mean(res);
    }

    public double stddev() {
        // sample standard deviation of percolation threshold
        return StdStats.stddev(res);
    }

    public double confidenceLow() {
        // low endpoint of 95% confidence interval
        return mean() - 1.96 * stddev() / Math.sqrt(time);
    }
    public double confidenceHigh() {
        // high endpoint of 95% confidence interval
        return mean() + 1.96 * stddev() / Math.sqrt(time);
    }
}
