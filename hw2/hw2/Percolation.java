package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import org.junit.Test;

public class Percolation {
    private final WeightedQuickUnionUF uf;
    private final int number;
    private int openSize;
    private boolean isPercolation;
    private int position(int row, int col) {
        return row * number + col;
    }
    private boolean isTop (int pos) {
        return pos < number;
    }
    private boolean isBottom (int pos) {
        return pos + number >= number * number;
    }
    private int[][] signal;
    private int[] around(int row, int col) {
        int[] res = new int[4];
        int pos;
        if (row + 1 < number) {
            if (isOpen(row + 1, col)) {
                res[0] = position(row + 1, col);
            } else {
                res[0] = -1;
            }
        } else {
            res[0] = -1;
        }
        if (row - 1 >= 0) {
            if (isOpen(row - 1, col)) {
                res[1] = position(row - 1, col);
            } else {
                res[1] = -1;
            }
        } else {
            res[1] = -1;
        }
        if (col + 1 < number) {
            if (isOpen(row, col + 1)) {
                res[2] = position(row, col + 1);
            } else {
                res[2] = -1;
            }
        } else {
            res[2] = -1;
        }
        if (col - 1 >= 0) {
            if (isOpen(row, col - 1)) {
                res[3] = position(row, col - 1);
            } else {
                res[3] = -1;
            }
        } else {
            res[3] = -1;
        }
        return res;
    }
    private void isValid(int row, int col) {
        if (row >= number) {
            throw new IndexOutOfBoundsException("row index must be smaller than N");
        } else if (col >= number) {
            throw new IndexOutOfBoundsException("col index must be smaller than N");
        } else if (row < 0) {
            throw new IndexOutOfBoundsException("row index must be larger than 0");
        } else if (col < 0) {
            throw new IndexOutOfBoundsException("col index must be larger than 0");
        }
    }
    public Percolation(int N) {
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0) {
            throw new IllegalArgumentException("N must be larger than 0");
        }
        number = N;
        openSize = 0;
        isPercolation = false;
        uf = new WeightedQuickUnionUF(number * number);
        signal = new int[number][number];
        for (int i = 0; i < number; i+= 1) {
            for (int j = 0; j < number; j += 1) {
                signal[i][j] = 0;
            }
        }
    }
    private void openHelp(int row, int col) {
        int nowpos = position(row, col);
        int[] myaround = around(row, col);
        boolean top = false;
        boolean bottom = false;
        int index = 0;
        int toUnion = nowpos;
        int store = 0;
        if (isTop(uf.find(nowpos))) {
            top = true;
        }
        if (isBottom(uf.find(nowpos))) {
            bottom = true;
        }
        while (index < 4) {
            int get = myaround[index];
            if (get >= 0) {
                int find = uf.find(get);
                if (isTop(find)) {
                    top = true;
                    toUnion = get;
                    store = get;
                }
                if (isBottom(find)) {
                    bottom = true;
                    toUnion = get;
                }
            }
            index += 1;
        }
        index = 0;
        if (top && bottom) {
            isPercolation = true;
            toUnion = store;
        }
        uf.union(toUnion, nowpos);
        while (index < 4) {
            if (myaround[index] >= 0) {
                uf.union(toUnion, myaround[index]);
            }
            index += 1;
        }
    }
    public void open(int row, int col) {
    // open the site (row, col) if it is not open already
        isValid(row, col);
        if (! isOpen(row, col)) {
            openHelp(row, col);
            signal[row][col] = 1;
            openSize += 1;
        }
    }
    public boolean isOpen(int row, int col) {
    // is the site (row, col) open?
        isValid(row, col);
        return signal[row][col] == 1;
    }
    public boolean isFull(int row, int col) {
        //is the site (row, col) full?
        isValid(row, col);
        if (! isOpen(row, col)) {
            return false;
        } else {
            int pos = position(row, col);
            return isTop(uf.find(pos));
        }
    }
    public int numberOfOpenSites() {
        //number of open site
        return openSize;
    }
    public boolean percolates() {
        //does the system percolate?
        return isPercolation;
    }
    public static void main(String[] args) {
        //use for unit testing

    }
}
