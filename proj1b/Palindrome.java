public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        ArrayDeque<Character> wordDeque = new ArrayDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            char store = word.charAt(i);
            wordDeque.addLast(store);
        }
        return wordDeque;
    }
    private boolean recursionHelp(ArrayDeque<Character> wordDeque){
        if (wordDeque.size() == 0 || wordDeque.size() == 1){
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();
        if (first != last) {
            return false;
        } else {
            return recursionHelp(wordDeque);
        }
    }
    public boolean isPalindrome(String word) {
        ArrayDeque<Character> wordDeque = (ArrayDeque<Character>) wordToDeque(word);
        return recursionHelp(wordDeque);
    }
    private boolean newrecursionHelp(ArrayDeque<Character> wordDeque, CharacterComparator cc){
        if (wordDeque.isEmpty() || wordDeque.size() == 1){
            return true;
        }
        char first = wordDeque.removeFirst();
        char last = wordDeque.removeLast();
        if (! cc.equalChars(first,last)) {
            return false;
        } else {
            return newrecursionHelp(wordDeque, cc);
        }
    }
    public boolean isPalindrome(String word, CharacterComparator cc) {
        ArrayDeque<Character> wordDeque = (ArrayDeque<Character>) wordToDeque(word);
        return newrecursionHelp(wordDeque, cc);
    }
}
