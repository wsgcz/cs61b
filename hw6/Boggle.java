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
        if (insertedStr.contains(s) || s.length() <= 3) {
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
        @Override
        public boolean equals(Object o) {
            CharPos other = (CharPos) o;
            return other.c == c && other.y == y && other.x == x;
        }
        @Override
        public int hashCode() {
            return y * 611 * 611 + x * 611;
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
    private static void lookaround(String s, CharPos charPos ,Set<CharPos> charPosSet, Node node) {
        char c = charPos.c;
        int x = charPos.x;
        int y = charPos.y;
        Node n = node;
        if (!charPosSet.contains(charPos)) {
            if (n.next.containsKey(c)) {
                Set<CharPos> cps = new HashSet<>(charPosSet);
                cps.add(new CharPos(c, x, y));
                n = n.next.get(c);
                s = s.concat(String.valueOf(c));
                if (n.isChar) {
                    insertPq(s);
                }
                for (CharPos charPos1 : charPos.getNeighbor()) {
                    lookaround(s, charPos1, cps, n);
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
        int row;
        int col;
        String[] read = inBoard.readAllLines();
        row = read.length;
        col = read[0].length();
        for (int i = 1; i < row; i += 1) {
            if (read[i].length() != col) {
                throw new IllegalArgumentException("the input board should be rectangular");
            }
        }
        Boggle.row = row;
        Boggle.col = col;
        boggle = new CharPos[row][col];
        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < col ; j += 1) {
                boggle[i][j] = new CharPos(read[i].charAt(j), i, j);
            }
        }

        for (int i = 0; i < row; i += 1) {
            for (int j = 0; j < col; j += 1) {
                boolean[][] checked = new boolean[row][col];
                CharPos cp = boggle[i][j];
                String s = "";
                Set<CharPos> charPosSet = new HashSet<>();
                lookaround(s, cp, charPosSet, root);
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
