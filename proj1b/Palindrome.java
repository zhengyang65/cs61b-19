public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dequeftr = new LinkedListDeque<>();
        int wordlength = word.length();
        for (int i = 0; i < wordlength; i++) {
            char fir = word.charAt(i);
            dequeftr.addLast(fir);

        }
        return dequeftr;
    }
    public boolean isPalindrome(String word) {
        Deque<Character> ftr = wordToDeque(word);
        isPalindromeHelper(ftr);
        return ftr.isEmpty();

    }
    public Deque<Character> isPalindromeHelper(Deque<Character> ftr) {
        while (ftr.removeFirst().equals(ftr.removeLast())) {
            return isPalindromeHelper(ftr);
        }
        return ftr;
    }
    public boolean isPalindrome(CharacterComparator offbyone, String word) {
        int i = 0;
        int wordlength = word.length();
        while (wordlength > 1 & wordlength > (i + 1)) {
            char x = word.charAt(i);
            char y = word.charAt(wordlength - 1);
            if (!offbyone.equalChars(x, y)) {
                return false;
            }
            i += 1;
            wordlength -= 1;
        }
        return true;
    }
    public boolean isPalindrome(String word, CharacterComparator offByN){
        int i = 0;
        int wordlength = word.length();
        while (wordlength > 1 & wordlength > (i + 1)) {
            char x = word.charAt(i);
            char y = word.charAt(wordlength - 1);
            if (!offByN.equalChars(x, y)) {
                return false;
            }
            i += 1;
            wordlength -= 1;
        }
        return true;
    }
}
