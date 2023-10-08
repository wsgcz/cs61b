import edu.princeton.cs.algs4.In;

import java.util.Arrays;

public class mytest {
    static String dictPath = "words.txt";
    private static void volify(boolean[][] checked) {
        checked[1][1] = true;
    }
    public static void main(String[] args) {
        String boardFilePath = "exampleBoard.txt";
        In inBoard = new In(boardFilePath);
        int row = 0;
        int col = 0;
        while (!inBoard.isEmpty()) {
            col = inBoard.readString().length();
            row += 1;
        }
        inBoard = new In(boardFilePath);
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j <= col; j += 1) {
                char c = inBoard.readChar();
                if (c != 13 && c != 10) {
                    System.out.println(c);
                }
            }
        }

    }
}
