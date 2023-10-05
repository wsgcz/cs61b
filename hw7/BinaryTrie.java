import edu.princeton.cs.algs4.MinPQ;

import java.awt.image.LookupTable;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class BinaryTrie implements Serializable {
    Map<Character, BitSequence> lookupTable;
    MinPQ<Node> pq;
    Node root;
    private class Node implements Comparable<Node>{

        boolean isChar;
        int weight;
        Character c;
        Node[] next;
        Node(boolean isChar, Node left, Node right, Character c, int weight) {
            this.isChar = isChar;
            next = new Node[2];
            this.c = c;
            this.weight = weight;
            next[0] = left;
            next[1] = right;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        lookupTable = new HashMap<>();
        pq = new MinPQ<>();
        Node left;
        Node right;
        root = new Node(false, null, null, null, 0);
        for (Character c : frequencyTable.keySet()) {
            pq.insert(new Node(true, null, null, c, frequencyTable.get(c)));
        }
        while (pq.size() > 2) {
            left = pq.delMin();
            right = pq.delMin();
            pq.insert(new Node(false, left, right, null, left.weight + right.weight));
        }
        root.next[0] = pq.delMin();
        root.next[1] = pq.delMin();
    }
    public Match longestPrefixMatch(BitSequence querySequence) {
        Node n = root;
        BitSequence sequence;
        sequence = new BitSequence();
        int pos = 0;
        while (!n.isChar) {
            int bit = querySequence.bitAt(pos);
            n = n.next[bit];
            sequence = sequence.appended(bit);
            pos += 1;
        }
        return new Match(sequence, n.c);
    }
    private void buildLookupTableHelp(Node n, BitSequence sequence) {
        if (n.isChar) {
            lookupTable.put(n.c, sequence);
        } else {
            buildLookupTableHelp(n.next[0], sequence.appended(0));
            buildLookupTableHelp(n.next[1], sequence.appended(1));
        }
    }
    public Map<Character, BitSequence> buildLookupTable() {
        buildLookupTableHelp(root, new BitSequence());
        return lookupTable;
    }
}
