import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.
    static CharacterComparator offByOne = (CharacterComparator) new OffByN(5);

    // Your tests go here.
    @Test
    public void testWordToDeque() {

        assertTrue(offByOne.equalChars('a', 'f'));
    }
}