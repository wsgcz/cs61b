package hw4.puzzle;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;

import java.util.*;

public class Solver {
    /*
        Constructor which solves the puzzle, computing
        everything necessary for moves() and solution() to
         not have to solve the problem again. Solves the
         puzzle using the A* algorithm. Assumes a solution exists.
     */
    private searchNode res;
    private searchNode initial;
    private class searchNode implements Comparable{
        WorldState state;
        int priority;
        int move;
        searchNode last;
        private searchNode(int move, WorldState state, searchNode last) {
            this.move = move;
            this.state = state;
            this.last = last;
            priority = this.move + this.state.estimatedDistanceToGoal();
        }
        @Override
        public int compareTo(Object o) {
            searchNode toCompare = (searchNode) o;
            return priority - toCompare.priority;
        }
    }
    public Solver(WorldState initial) {
        MinPQ<searchNode> pq = new MinPQ<>();
        this.initial = new searchNode(0, initial, null);
        pq.insert(this.initial);
        searchNode bms = pq.delMin();
        while (! bms.state.isGoal()) {
            for (WorldState ws : bms.state.neighbors()) {
                if (bms.last == null || ! bms.last.state.equals(ws)) {
                    searchNode toAdd = new searchNode(bms.move + 1, ws, bms);
                    pq.insert(toAdd);
                    //System.out.println(ws);
                }
            }
            bms = pq.delMin();
        }
        res = bms;
    }
    public int moves() {
        return res.move;
        /*
    Returns the minimum number of moves to solve the puzzle starting
     at the initial WorldState.
     */
    }
    public Iterable<WorldState> solution() {
        /*
        Returns a sequence of WorldStates from the initial WorldState
        to the solution.
         */
        List<WorldState> sol = new ArrayList<>();
        sol.add(res.state);
        searchNode toAdd = res;
        while (! toAdd.state.equals(initial.state)) {
            toAdd = toAdd.last;
            sol.add(toAdd.state);
        }
        Collections.reverse(sol);
        return sol;
    }
    public static void main(String[] args) {
    }
}
