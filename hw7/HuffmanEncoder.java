import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {
    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char c : inputSymbols) {
            if (!frequencyTable.containsKey(c)) {
                frequencyTable.put(c, 1);
            } else {
                frequencyTable.replace(c, frequencyTable.get(c) + 1);
            }
        }
        return frequencyTable;
    }
    public static void main(String[] args) {
        //String filename = args[0];
        String filename = "tas.txt";
        List<BitSequence> bitSequences = new ArrayList<>();
        char[] inputSymbols = FileUtils.readFile(filename);
        int numberOfSymbol = inputSymbols.length;
        Map<Character, Integer> frequencyTable = buildFrequencyTable(inputSymbols);
        ObjectWriter objectWriter = new ObjectWriter(filename + ".huf");
        BinaryTrie trie = new BinaryTrie(frequencyTable);
        Map<Character, BitSequence> lookUpTable = trie.buildLookupTable();
        for (char c : inputSymbols) {
            bitSequences.add(lookUpTable.get(c));
        }
        BitSequence res = BitSequence.assemble(bitSequences);
        objectWriter.writeObject(trie);
        objectWriter.writeObject(numberOfSymbol);
        objectWriter.writeObject(res);
    }
}
