package lab11.graphs;

/**
 *  @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int[] parent;
    private Maze maze;
    public MazeCycles(Maze m) {
        super(m);
        maze = m;
        distTo[0] = 0;
        parent = new int[m.V()];
        for (int i = 0; i < m.V(); i += 1) {
            parent[i] = -1;
        }
    }

    @Override
    public void solve() {
        dfs(0);
    }
    private void dfs(int w) {
        marked[w] = true;
        announce();
        for (int i : maze.adj(w)) {
            if (marked[i] && parent[w] != i) {
                edgeTo[i] = w;
                while (parent[w] != i) {
                    edgeTo[w] = parent[w];
                    w = parent[w];
                }
                announce();
                return;
            } else if (!marked[i]) {
                distTo[i] = 1 + distTo[w];
                parent[i] = w;
                dfs(i);
            }
        }
    }
    // Helper methods go here
}

