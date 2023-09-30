package lab11.graphs;

import java.util.HashSet;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;
    private HashSet<Integer> removed;
    int targetX;
    int targetY;
    PriorityQueue<Node> pq;
    private class Node implements Comparable<Node>{
        int number;
        int priority;
        private Node(int number, int priority) {
            this.priority = priority;
            this.number = number;
        }
        @Override
        public int compareTo(Node o) {
            Node obj = (Node) o;
            return this.priority - obj.priority;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        this.targetX = targetX;
        this.targetY = targetY;
        distTo[s] = 0;
        edgeTo[s] = s;
        pq = new PriorityQueue<>();
        pq.add(new Node(s, h(s)));
        for (int i = 0; i < m.V(); i += 1) {
            if (i != s) {
                pq.add(new Node(i, Integer.MAX_VALUE));
            }
        }
        removed = new HashSet<>();
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        return Math.abs(maze.toX(v) - targetX) + Math.abs(maze.toY(v) - targetY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return pq.remove().number;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        marked[s] = true;
        announce();
        while (!pq.isEmpty()) {
            int w = pq.remove().number;
            removed.add(w);
            for (int i : maze.adj(w)) {
                if (!removed.contains(i)) {
                    marked[i] = true;
                    if (distTo[i] > distTo[w] + 1) {
                        edgeTo[i] = w;
                        distTo[i] = distTo[w] + 1;
                        pq.add(new Node(i, distTo[i] + h(i)));
                        announce();
                    }
                    if (i == t) {
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        astar(s);
    }

}

