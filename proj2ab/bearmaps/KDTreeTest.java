package bearmaps;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

public class KDTreeTest {
    /** basic test */
    @Test
    public void basicTest() {
        Point p1 = new Point(2.0, 3.0);
        Point p2 = new Point(4.0, 2.0);
        Point p3 = new Point(4.0, 5.0);
        Point p4 = new Point(3.0, 3.0);
        Point p5 = new Point(1.0, 5.0);
        Point p6 = new Point(4.0, 4.0);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6));
        Point ret = nn.nearest(0.0, 7.0); // returns p2
        double actual = ret.getX();
        assertEquals(1.0, actual, 0.01); // evaluates to 1.0
        actual = ret.getY();
        assertEquals(5.0, actual, 0.01); // evaluates to 5.0
    }
    @Test
    public void randomTest() {

        List<Point> plist = new ArrayList<>();
        Random r = new Random();
        for (int i = 0; i < 1000; i++) {
            double x = r.nextInt(1000);
            double y = r.nextInt(1000);
            Point p1 = new Point(x, y);
            plist.add(p1);
        }

        KDTree nn = new KDTree(plist);
        NaivePointSet mm = new NaivePointSet(plist);
        Point ret1 = nn.nearest(21.3, 186.5);
        Point ret2 = mm.nearest(21.3, 186.5);
        double expected = ret2.getX();
        double actual = ret1.getX();
        assertEquals(expected, actual, 0.01);
        expected = ret2.getY();
        actual = ret1.getY();
        assertEquals(expected, actual, 0.01);
    }

    /** basic test */
    @Test
    public void basicTest2() {
        Point p1 = new Point(84.0, 84.0);
        Point p2 = new Point(31.0, 83.0);
        Point p3 = new Point(11.0, 21.0);
        Point p4 = new Point(60.0, 97.0);
        Point p5 = new Point(26.0, 77.0);
        Point p6 = new Point(92.0, 51.0);
        Point p7 = new Point(17.0, 63.0);
        Point p8 = new Point(38.0, 93.0);
        Point p9 = new Point(11.0, 22.0);
        Point p10 = new Point(24.0, 77.0);
        Point p11 = new Point(8.0, 81.0);
        Point p12 = new Point(28.0, 62.0);
        Point p13 = new Point(14.0, 14.0);
        Point p14 = new Point(14.0, 92.0);
        Point p15 = new Point(14.0, 78.0);
        Point p16 = new Point(10.0, 43.0);
        Point p17 = new Point(53.0, 22.0);
        Point p18 = new Point(46.0, 89.0);
        Point p19 = new Point(49.0, 88.0);
        Point p20 = new Point(28.0, 92.0);
        Point p21 = new Point(35.0, 87.0);
        Point p22 = new Point(18.0, 93.0);
        Point p23 = new Point(86.0, 60.0);
        Point p24 = new Point(43.0, 94.0);
        Point p25 = new Point(65.0, 42.0);
        Point p26 = new Point(77.0, 27.0);
        Point p27 = new Point(79.0, 3.0);
        Point p28 = new Point(45.0, 90.0);
        Point p29 = new Point(40.0, 74.0);
        Point p30 = new Point(31.0, 88.0);
        Point p31 = new Point(61.0, 16.0);
        Point p32 = new Point(64.0, 92.0);
        Point p33 = new Point(5.0, 26.0);
        Point p34 = new Point(43.0, 43.0);
        Point p35 = new Point(14.0, 40.0);
        Point p36 = new Point(95.0, 6.0);
        Point p37 = new Point(75.0, 42.0);
        Point p38 = new Point(23.0, 1.0);
        Point p39 = new Point(40.0, 8.0);
        Point p40 = new Point(27.0, 56.0);
        Point p41 = new Point(88.0, 67.0);
        Point p42 = new Point(67.0, 76.0);
        Point p43 = new Point(44.0, 43.0);
        Point p44 = new Point(50.0, 59.0);
        Point p45 = new Point(36.0, 32.0);
        Point p46 = new Point(69.0, 73.0);
        Point p47 = new Point(83.0, 18.0);
        Point p48 = new Point(12.0, 96.0);
        Point p49 = new Point(76.0, 81.0);
        Point p50 = new Point(74.0, 27.0);
        KDTree nn = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                p11, p12, p13, p14, p15, p16, p17, p18, p19, p20,
                p21, p22, p23, p24, p25, p26, p27, p28, p29, p30,
                p31, p32, p33, p34, p35, p36, p37, p38, p39, p40,
                p41, p42, p43, p44, p45, p46, p47, p48, p49, p50));
        Point ret = nn.nearest(21.3, 186.5); // returns p2
        double actual = ret.getX();
        assertEquals(12.0, actual, 0.01); // evaluates to 12.0
        actual = ret.getY();
        assertEquals(96.0, actual, 0.01); // evaluates to 96.0
    }
}
