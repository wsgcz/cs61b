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
        my.open(1, 0);
        assertTrue(my.isOpen(2,2));
        assertEquals(5, my.numberOfOpenSites());
        assertTrue(my.percolates());
        assertTrue(my.isFull(2, 2));
    }
    @Test
    public void test2() {
        Percolation my = new Percolation(6);
        my.open(0, 5);
        my.open(1, 5);
        my.open(2, 5);
        my.open(3, 5);
        my.open(4, 5);
        my.open(4, 4);
        my.open(3, 3);
        my.open(2, 3);
        my.open(1, 3);
        my.open(1, 2);
        my.open(1, 1);
        my.open(1, 0);
        my.open(2, 0);
        my.open(3, 0);
        my.open(4, 0);
        my.open(4, 1);
        my.open(5, 1);
        my.open(4, 3);

        assertTrue(my.percolates());
    }
    public static void main(String[] args) {
        int N = 5;
        int[] res = new int[50];
        for (int i = 0; i < 50; i+= 1) {
            res[i] = (int) (StdRandom.uniform() * N);
        }
        for (int i = 0; i < 50; i+= 1) {
            System.out.println(res[i]);
        }
//        WeightedQuickUnionUF my = new WeightedQuickUnionUF(36);
//        my.union(21, 21);
//        my.union(15, 21);
//        my.union(15, 9);
//        System.out.println(my.find(9));
    }
}
