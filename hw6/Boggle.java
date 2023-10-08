import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;
import java.util.logging.FileHandler;

public class Boggle {
    
    // File path of dictionary file
    static String dictPath = "words.txt";
    private static final MinPQ<mystr> pq = new MinPQ<>();
    private static int k;
    private static int row;
    private static int col;
    //private static String
    private static CharPos[][] boggle;
    private static Set<String> insertedStr;
    private static void insertPq(String s) {
        if (insertedStr.contains(s)) {
            return;
        }
        mystr str = new mystr(s);
        if (pq.size() >= k) {
            if (pq.min().compareTo(str) < 0) {
                pq.delMin();
                pq.insert(str);
            }
        } else {
            pq.insert(str);
        }
        insertedStr.add(s);
    }
    private static class mystr implements Comparable<mystr>{
        String string;
        void addChar(char c) {
            string = string.concat(String.valueOf(c));
        }
        mystr(String s) {
            string = s;
        }
        @Override
        public int compareTo(mystr o) {
            if (this.string.length() > o.string.length()) {
                return 1;
            }
            if (this.string.length() < o.string.length()) {
                return -1;
            }
            return o.string.compareTo(this.string);
        }
    }
    private static class Node {
        boolean isChar;
        Map<Character, Node> next;
        private Node(boolean isChar) {
            this.isChar = isChar;
            next = new HashMap<>();
        }
    }
    private static class CharPos {
        char c;
        int x;
        int y;
        CharPos(char c, int x, int y) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
        List<CharPos> getNeighbor() {
            List<CharPos> res = new ArrayList<>();
            if (this.x != 0) {
                if (this.y != 0) {
                    res.add(boggle[x - 1][y - 1]);
                }
                if (this.y != col - 1) {
                    res.add(boggle[x - 1][y + 1]);
                }
                res.add(boggle[x - 1][y]);
            }
            if (this.x != row - 1) {
                if (this.y != 0) {
                    res.add(boggle[x + 1][y - 1]);
                }
                if (this.y != col - 1) {
                    res.add(boggle[x + 1][y + 1]);
                }
                res.add(boggle[x + 1][y]);
            }
            if (this.y != 0) {
                res.add(boggle[x][y - 1]);
            }
            if (this.y != col - 1) {
                res.add(boggle[x][y + 1]);
            }
            return res;
        }
    }
    private static final Node root = new Node(false);
    private static void addWord(String s) {
        Node n = root;
        for (int i = 0; i < s.length(); i += 1) {
            char c = s.charAt(i);
            if (n.next.containsKey(c)) {
                n = n.next.get(c);
            } else {
                if (i == s.length() - 1) {
                    n.next.put(c, new Node(true));
                } else {
                    n.next.put(c, new Node(false));
                    n = n.next.get(c);
                }
            }
        }
    }
    private static void lookaround(String s, CharPos charPos ,boolean[][] checked, Node node) {
        int x = charPos.x;
        int y = charPos.y;
        char c = charPos.c;
        Node n = node;
        if (!checked[x][y]) {
            if (n.next.containsKey(c)) {
                boolean[][] ck = new boolean[row][col];
                for (int i = 0; i < row; i += 1) {
                    if (col >= 0) System.arraycopy(checked[i], 0, ck[i], 0, col);
                }
                ck[x][y] = true;
                n = n.next.get(c);
                s = s.concat(String.valueOf(c));
                if (n.isChar) {
                    insertPq(s);
                }
                for (CharPos charPos1 : charPos.getNeighbor()) {
                    lookaround(s, charPos1, ck, n);
                }
            }
        }
    }
    /**
     * Solves a Boggle puzzle.
     * 
     * @param k The maximum number of words to return.
     * @param boardFilePath The file path to Boggle board file.
     * @return a list of words found in given Boggle board.
     *         The Strings are sorted in descending order of length.
     *         If multiple words have the same length,
     *         have them in ascending alphabetical order.
     */
    public static List<String> solve(int k, String boardFilePath) {
        if (k < 0) {
            throw new IllegalArgumentException("k should be non-positive");
        }
        Boggle.k = k;
        insertedStr = new HashSet<>();
        In inWord = new In(dictPath);
        while (!inWord.isEmpty()) {
            addWord(inWord.readString());
        }

        In inBoard = new In(boardFilePath);
        int row = 0;
        int col = 0;
        while (!inBoard.isEmpty()) {
            int length;
            length = inBoard.readString().length();
            if (row == 0) {
                col = length;
            } else {
                if (length != col) {
                    throw new IllegalArgumentException("thhe input board should be rectangular");
                }
            }
            row += 1;
        }
        Boggle.row = row;
        Boggle.col = col;
        boggle = new CharPos[row][col];
        char[][] tmp = new char[row][col];
        inBoard = new In(boardFilePath);
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j <= col + 1; j += 1) {
                char c = inBoard.readChar();
                if (j < col) {
                    boggle[i][j] = new CharPos(c, i, j);
                    tmp[i][j] = c;
                }
            }
        }
        System.out.println(Arrays.deepToString(tmp));

        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < col; j += 1) {
                boolean[][] checked = new boolean[row][col];
                CharPos cp = boggle[i][j];
                char c = cp.c;
                Node node = root;
                String s = "";
                lookaround(s, cp, checked, root);
            }
        }

        List<String> res = new ArrayList<>();
        while (!pq.isEmpty()) {
            res.add(pq.delMin().string);
        }
        Collections.reverse(res);
        return res;
    }
}
