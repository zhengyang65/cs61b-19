package bearmaps;
import java.util.List;

public class NaivePointSet implements PointSet {
    private List<Point> pt;
    private double distance = 1.7976931348623157e+308;
    /** Returns the closest point to the inputted coordinates.
     *  This should take \(\theta(N)\) time where \(N\)
     *  is the number of points. */
    @Override
    public Point nearest(double x, double y) {
        Point result = null;
        for (Point p:pt) {
            double d = Point.distance(x, p.getX(), y, p.getY());
            if (d < distance) {
                distance = d;
                result = p;
            }
        }
        return result;
    }
    /**You can assume points has at least size 1.*/
    public NaivePointSet(List<Point> points) {
        pt = points;
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.1, 2.2);    // constructs a Point with x = 1.1, y = 2.2
        Point p2 = new Point(3.3, 4.4);
        Point p3 = new Point(-2.9, 4.2);

        NaivePointSet nn = new NaivePointSet(List.of(p1, p2, p3));
        Point ret = nn.nearest(3.0, 4.0); // returns p2
        ret.getX(); // evaluates to 3.3
        ret.getY(); // evaluates to 4.4
    }
}
