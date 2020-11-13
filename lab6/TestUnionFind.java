import org.junit.Test;
import static org.junit.Assert.*;
public class TestUnionFind {

    @Test
    public void testBasics() {
        UnionFind test = new UnionFind(5);
        int expected = 1;
        int actual = test.find(1);
        assertEquals(expected, actual);
        test.union(0, 4);
        test.union(1, 0);
        test.union(2, 3);
        test.union(3, 4);

        actual = test.sizeOf(0);
        expected = 5;
        assertEquals(expected, actual);

        actual = test.parent(3);
        expected = 2;
        assertEquals(expected, actual);

        test.connected(3 ,1);
        actual = test.parent(3);
        expected = 0;
        assertEquals(expected, actual);
    }
}
