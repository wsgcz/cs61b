package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 *  @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /* Inherits public fields:
    public int[] distTo;
    public int[] edgeTo;
    public boolean[] marked;
    */
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private Queue<Integer> queue;
    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        s = m.xyTo1D(sourceX, sourceY);
        t = m.xyTo1D(targetX, targetY);
        maze = m;
        distTo[s] = 0;
        edgeTo[s] = s;
        queue = new Queue<Integer>();
        // Add more variables here!
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        queue.enqueue(s);
        marked[s] = true;
        announce();
        while (! queue.isEmpty()) {
            int i = queue.dequeue();
            for (int w : maze.adj(i)) {

                if (! marked[w]) {
                    edgeTo[w] = i;
                    distTo[w] = distTo[i] + 1;
                    marked[w] = true;
                    announce();
                    if (w == t) {
                        return;
                    }
                    queue.enqueue(w);
                }
            }
        }
    }


    @Override
    public void solve() {
        bfs();
    }
}

