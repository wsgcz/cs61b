import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
        Deque gcztest = palindrome.wordToDeque("getflu");
        String gczactual = "";
        for (int i = 0; i < "getflu".length(); i++) {
            gczactual += gcztest.removeFirst();
        }
        assertEquals("getflu", gczactual);
    }

    @Test
    public void testIsPalindrome(){
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("noon"));
        assertTrue(palindrome.isPalindrome("racecar"));
        assertFalse(palindrome.isPalindrome("horse"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertFalse(palindrome.isPalindrome("aaaaaab"));
    }
    @Test
    public void tesnewtIsPalindrome(){
        assertTrue(palindrome.isPalindrome(""));
        assertTrue(palindrome.isPalindrome("a"));
        assertTrue(palindrome.isPalindrome("nopm"));
        assertTrue(palindrome.isPalindrome("racebbs"));
        assertFalse(palindrome.isPalindrome("abba"));
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("rancor"));
        assertFalse(palindrome.isPalindrome("noon"));
    }
}
