package es.datastructur.synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer<Integer> arb = new ArrayRingBuffer(10);
        for (Integer i = 1; i <= 10; i++) {
            arb.enqueue(i);
        }
        Integer actual = arb.peek();
        Integer expected = 1;
        assertEquals(expected, actual);
        actual = arb.dequeue();
        assertEquals(expected, actual);
        ArrayRingBuffer<Integer> arb2 = new ArrayRingBuffer(10);
        for (Integer i = 1; i <= 10; i++) {
            arb2.enqueue(i);
        }
        arb2.dequeue();
        assertEquals(true, arb.equals(arb2));
    }
}
