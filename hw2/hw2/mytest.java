package hw2;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;
import edu.princeton.cs.introcs.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

public class mytest {
    @Test
    public void testpercolation() {
        Percolation my = new Percolation(3);
        assertFalse(my.isOpen(0, 0));
        my.open(0, 0);
        assertTrue(my.isOpen(0, 0));
        assertEquals(1, my.numberOfOpenSites());
        assertFalse( my.percolates());
        my.open(1, 1);
        my.open(2, 2);
        my.open(1, 2);
        assertTrue(my.isOpen(2,2));
        assertEquals(4, my.numberOfOpenSites());
        assertTrue(my.percolates());
        assertTrue(my.isFull(2, 2));
    }
    @Test
    public void test2() {
        Percolation my = new Percolation(5);
        my.open(1, 1);
        assertFalse(my.isFull(1, 1));
        my.open(2, 3);
        assertFalse(my.isFull(1, 1));
        my.open(2, 1);
        assertFalse(my.isFull(1, 1));
        my.open(2, 4);
        assertFalse(my.isFull(1, 1));
        my.open(0, 2);
        assertFalse(my.percolates());
        assertFalse(my.isFull(1, 1));
        my.open(1, 3);
        assertFalse(my.isFull(1, 1));
        my.open(0, 3);
        assertFalse(my.isFull(1, 1));
        my.open(3, 3);
        assertFalse(my.isFull(1, 1));
        my.open(3, 4);
        assertTrue(my.isFull(2, 3));
        assertFalse(my.isFull(1, 1));
        assertFalse(my.percolates());
        my.open(4, 4);
        assertTrue(my.percolates());
    }
    public static void main(String[] args) {
        int N = 5;
        int[] res = new int[10];
        for (int i = 0; i < 10; i+= 1) {
            res[i] = (int) Math.round(StdRandom.uniform() * (N - 1));
        }
        for (int i = 0; i < 10; i+= 1) {
            System.out.println(res[i]);
        }
    }
}
