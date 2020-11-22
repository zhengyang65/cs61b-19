package bearmaps;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ArrayHeapMinPQTest {

    /** basic test */
    @Test
    public void basicTest() {
        ArrayHeapMinPQ<Integer>  test = new ArrayHeapMinPQ<Integer>();
        test.add(5, 5.02);
        assertEquals(1, test.size());
        test.add(6, 4.92);
        int actual = test.removeSmallest();
        assertEquals(6, actual);
        test.add(4, 4.92);
        test.add(3, 3.52);
        test.add(10, 10.21);
        test.add(7, 7.21);
        test.add(6, 6.85);
        System.out.print(test.entry);
        System.out.println(test.prioritylist);
    }

    /** Test changePriority */
    @Test
    public void changePriorityTest() {
        ArrayHeapMinPQ<Integer>  test = new ArrayHeapMinPQ<Integer>();
        test.add(5, 5.02);
        test.add(4, 4.92);
        test.add(3, 3.52);
        test.add(10, 10.21);
        test.add(7, 7.21);
        test.add(6, 6.85);
        test.add(8, 8.85);
        test.add(9, 9.25);
        test.changePriority(10,3.85);
        test.removeSmallest();
        int actual =test.getSmallest();
        assertEquals(10, actual);
    }
}
