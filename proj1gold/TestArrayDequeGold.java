import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Build a rudimentary autograder for project 1A.
 */
public class TestArrayDequeGold {
    //@Source StudentArrayDequeLauncher//

    static StudentArrayDeque<Integer> sad1 = new StudentArrayDeque<>();
    static ArrayDequeSolution<Integer> sad2 = new ArrayDequeSolution<>();
    @Test
    public void testArrayDeque() {
        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad1.addLast(i);
                sad2.addLast(i);
            } else {
                sad1.addFirst(i);
                sad2.addFirst(i);
            }
        }
        StdOut.println("Your ArrayDeque");
        Integer rmlasti = 1;
        Integer rmfirsti = 1;
        sad1.printDeque();
        for (Integer i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer actual = sad1.removeLast();
                Integer expected = sad2.removeLast();

                assertEquals("Oh noooo!\nThis is bad:\n" + "RemoveLast Sequence i:" + rmlasti + "\n"
                                + " Random number " + actual
                                + " not equal to " + expected + "!",
                        expected, actual);
                rmlasti += 1;
            } else {
                Integer actual = sad1.removeFirst();
                Integer expected = sad2.removeFirst();

                assertEquals("Oh noooo!\nThis is bad:\n" + "RemoveFirst Sequence i:" + rmfirsti
                                + "\n"
                                + " Random number " + actual
                                + " not equal to " + expected + "!",
                        expected, actual);
                rmfirsti += i;
            }

        }
    }



}
