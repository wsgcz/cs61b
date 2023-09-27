package hw4.puzzle;

import java.util.HashSet;


public class Board implements WorldState {
    /*
    Constructs a board from an N-by-N array of tiles where
    tiles[i][j] = tile at row i, column j
     */
    private final int size;
    private int[][] tiles;
    private int N;
    private int zeroRow;
    private int zeroCol;
    public Board(int[][] tiles) {
        N = tiles.length;
        size = N;
        this.tiles = new int[N][N];
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                this.tiles[i][j] = tiles[i][j];
                if (tiles[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
    }
    /*
    Returns value of tile at row i, column j (or 0 if blank)
     */
    public int tileAt(int i, int j) {
        if (!(i >= 0 && i < N && j >= 0 && j < N)) {
            throw new IllegalArgumentException("i and j should be in the range of 0 and N");
        }
        return tiles[i][j];
    }
    /*
    Returns the board size N
     */
    public int size() {
        return size;
    }
    public int hamming() {
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tiles[i][j] != i * N + j + 1) {
                    sum += 1;
                }
            }
        }
        return sum - 1;
    }
    public int manhattan() {
        int sum = 0;
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tiles[i][j] != 0) {
                    int row = (tiles[i][j] - 1) / N;
                    int col = (tiles[i][j] - 1) % N;
                    sum = sum + Math.abs(i - row) + Math.abs(j - col);
                }
            }
        }
        return sum;
    }
    /*
    Returns true if this board's tile values are the same
    position as y's
     */
    @Override
    public boolean equals(Object obj) {
        Board o = (Board) obj;
        if (o.size() != size()) {
            return false;
        }
        for (int i = 0; i < N; i += 1) {
            for (int j = 0; j < N; j += 1) {
                if (tiles[i][j] != o.tileAt(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
    @Override
    /*
    Estimated distance to goal. This method should
    simply return the results of manhattan() when submitted to
    Gradescope.
     */
    public int estimatedDistanceToGoal() {
        return manhattan();
    }
    /*
    Returns the neighbors of the current board
     */
    @Override
    public Iterable<WorldState> neighbors() {
        HashSet<WorldState> neighbors = new HashSet<>();
        int[][] tileToAddLeft = new int[N][N];
        int[][] tileToAddRight = new int[N][N];
        int[][] tileToAddUp = new int[N][N];
        int[][] tileToAddDown = new int[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(tiles[i], 0, tileToAddLeft[i], 0, N);
            System.arraycopy(tiles[i], 0, tileToAddUp[i], 0, N);
            System.arraycopy(tiles[i], 0, tileToAddDown[i], 0, N);
            System.arraycopy(tiles[i], 0, tileToAddRight[i], 0, N);
        }
        if (!(zeroCol == 0)) {
            tileToAddLeft[zeroRow][zeroCol - 1] = 0;
            tileToAddLeft[zeroRow][zeroCol] = tiles[zeroRow][zeroCol - 1];
            neighbors.add(new Board(tileToAddLeft));
        }
        if (!(zeroRow == 0)) {
            tileToAddUp[zeroRow - 1][zeroCol] = 0;
            tileToAddUp[zeroRow][zeroCol] = tiles[zeroRow - 1][zeroCol];
            neighbors.add(new Board(tileToAddUp));
        }
        if (!(zeroRow == N - 1)) {
            tileToAddDown[zeroRow][zeroCol] = tiles[zeroRow + 1][zeroCol];
            tileToAddDown[zeroRow + 1][zeroCol] = 0;
            neighbors.add(new Board(tileToAddDown));
        }
        if (!(zeroCol == N - 1)) {
            tileToAddRight[zeroRow][zeroCol] = tiles[zeroRow][zeroCol + 1];
            tileToAddRight[zeroRow][zeroCol + 1] = 0;
            neighbors.add(new Board(tileToAddRight));
        }
        return neighbors;
    }

    /** Returns the string representation of the board. 
      * Uncomment this method. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
