public class HuffmanDecoder {
    public static void main(String[] args) {
        String filename = args[0];
        String outfile = args[1];
        Match match;
        int i = 0;
        ObjectReader or = new ObjectReader(filename);
        BinaryTrie trie = (BinaryTrie) or.readObject();
        int numberOfSymbol = (int) or.readObject();
        char[] chars = new char[numberOfSymbol];
        BitSequence bitSequence = (BitSequence) or.readObject();
        while (bitSequence.length() > 0) {
            match = trie.longestPrefixMatch(bitSequence);
            chars[i] = match.getSymbol();
            bitSequence = bitSequence.allButFirstNBits(match.getSequence().length());
            i += 1;
        }
        FileUtils.writeCharArray(outfile, chars);
    }
}
