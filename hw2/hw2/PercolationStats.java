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
        res = new double[time];
        for (int i = 0; i < time; i += 1) {
            Percolation pl = pf.make(N);
            StdRandom.setSeed(i);
            int row;
            int col;
            while (! pl.percolates()) {
                do {
                    row = StdRandom.uniform(N);
                    col = StdRandom.uniform(N);
                } while (pl.isOpen(row, col));
                pl.open(row, col);
            }
            res[i] = (double) pl.numberOfOpenSites() / (number * number);
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
