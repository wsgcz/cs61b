package hw3.hash;

import java.util.List;

public class OomageTestUtility {
    public static boolean haveNiceHashCodeSpread(List<Oomage> oomages, int M) {
        /* TODO:
         * Write a utility function that returns true if the given oomages
         * have hashCodes that would distribute them fairly evenly across
         * M buckets. To do this, convert each oomage's hashcode in the
         * same way as in the visualizer, i.e. (& 0x7FFFFFFF) % M.
         * and ensure that no bucket has fewer than N / 50
         * Oomages and no bucket has more than N / 2.5 Oomages.
         */
        int N = oomages.size();
        int[] num = new int[M];
        for (int i = 0; i < M; i += 1) {
            num[i] = 0;
        }
        for (Oomage o : oomages) {
            int pos = (o.hashCode() & 0x7FFFFFFF) % M;
            num[pos] = num[pos] + 1;
        }
        double bottom =  (double) N / 50;
        double top = (double) N / 2.5;
        for (int i = 0; i < M; i += 1) {
            if (num[i] < bottom || num[i] > top) {
                return false;
            }
        }
        return true;
    }
//    public static void main(String[] args) {
//        int i = 50;
//        double my = ((double) i) / 3;
//        System.out.println(my);
//    }
}
