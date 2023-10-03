import java.awt.*;
import java.util.Arrays;

/**
 * Class for doing Radix sort
 *
 * @author Akhil Batra, Alexander Hwang
 *
 */
public class RadixSort {
    /**
     * Does LSD radix sort on the passed in array with the following restrictions:
     * The array can only have ASCII Strings (sequence of 1 byte characters)
     * The sorting is stable and non-destructive
     * The Strings can be variable length (all Strings are not constrained to 1 length)
     *
     * @param asciis String[] that needs to be sorted
     *
     * @return String[] the sorted array
     */
    /*
    there are many question :
    1.how to know the longest string in the array
    2.sort single word
     */
    public static String[] sort(String[] asciis) {
        String[] res = new String[asciis.length];
        int Length;
        Length = 0;
        for (int i = 0; i < asciis.length; i += 1) {
            if (asciis[i].length() > Length) {
                Length = asciis[i].length();
            }
            res[i] = asciis[i];
        }
        for (int i = 1; i <= Length; i += 1) {
            sortHelperLSD(res, Length - i);
        }
        return res;
    }

    /**
     * LSD helper method that performs a destructive counting sort the array of
     * Strings based off characters at a specific index.
     * @param asciis Input array of Strings
     * @param index The position to sort the Strings on.
     */
    private static void sortHelperLSD(String[] asciis, int index) {
        String[] tmp = new String[asciis.length];
        int[] count = new int[257];
        for (int i = 0; i < asciis.length; i += 1) {
            if (asciis[i].length() < index + 1) {
                count[0] += 1;
            } else {
                count[asciis[i].charAt(index) + 1] += 1;
            }
        }
        int sum = 0;
        int[] pos = new int[257];
        for (int i = 0; i < 257; i += 1) {
            pos[i] = sum;
            sum += count[i];
        }
        for (int i = 0; i < asciis.length; i += 1) {
            String item = asciis[i];
            int position;
            if (item.length() < index + 1) {
                position = pos[0];
            } else {
                position = pos[item.charAt(index) + 1];
            }
            tmp[position] = item;
            if (item.length() < index + 1) {
                pos[0] += 1;
            } else {
                pos[item.charAt(index) + 1] += 1;
            }
        }
        for (int i = 0; i < asciis.length; i += 1) {
            asciis[i] = tmp[i];
        }
    }

    /**
     * MSD radix sort helper function that recursively calls itself to achieve the sorted array.
     * Destructive method that changes the passed in array, asciis.
     *
     * @param asciis String[] to be sorted
     * @param start int for where to start sorting in this method (includes String at start)
     * @param end int for where to end sorting in this method (does not include String at end)
     * @param index the index of the character the method is currently sorting on
     *
     **/
    private static void sortHelperMSD(String[] asciis, int start, int end, int index) {
        // Optional MSD helper method for optional MSD radix sort
        return;
    }
    public static void main(String[] args) {
        String[] strings = new String[]{"bad", "full", "jak", "happy", "doyou", "ne", "alone", "all", "the", "time"};
        System.out.println(Arrays.toString(strings));
        String[] sorted = sort(strings);
        System.out.println(Arrays.toString(sorted));
    }
}
