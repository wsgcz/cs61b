import edu.princeton.cs.algs4.In;

import java.util.Arrays;
import java.util.List;

public class mytest {
    static String dictPath = "words.txt";
    private static void volify(boolean[][] checked) {
        checked[1][1] = true;
    }
    public static void main(String[] args) {
        String boardFilePath = "exampleBoard2.txt";
        List<String> res = Boggle.solve(7, "exampleBoard.txt");
        System.out.println(res);
//        In inBoard = new In(boardFilePath);
//        int row = 0;
//        int col = 0;
//        while (!inBoard.isEmpty()) {
//            int length;
//            length = inBoard.readString().length();
//            if (row == 0) {
//                col = length;
//            } else {
//                if (length != col) {
//                    throw new IllegalArgumentException("thhe input board should be rectangular");
//                }
//            }
//            row += 1;
//        }
//        Boggle.row = row;
//        Boggle.col = col;
    }
}
