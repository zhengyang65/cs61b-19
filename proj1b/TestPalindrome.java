import org.junit.Test;
import static org.junit.Assert.*;
public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();
    static CharacterComparator offByOne = (CharacterComparator) new OffByOne();
    static CharacterComparator offByN = (CharacterComparator) new OffByN(5);
    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }
    @Test
    public void testisPalindrome() {
        boolean result1 = palindrome.isPalindrome("racecar");
        boolean result2 = palindrome.isPalindrome("a");
        boolean result3 = palindrome.isPalindrome("cat");
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
    @Test
    public void testisPalindrome2() {
        boolean result1 = palindrome.isPalindrome(offByOne,"racebbs");
        boolean result2 = palindrome.isPalindrome(offByOne,"a");
        boolean result3 = palindrome.isPalindrome(offByOne,"cat");
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
    @Test
    public void testisPalindrome3() {
        boolean result1 = palindrome.isPalindrome("abchgf",offByN);
        boolean result2 = palindrome.isPalindrome("afaf",offByN);
        boolean result3 = palindrome.isPalindrome("aa",offByN);
        assertTrue(result1);
        assertTrue(result2);
        assertFalse(result3);
    }
}