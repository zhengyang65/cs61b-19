package bearmaps;

import java.util.List;

public class KDTree implements PointSet {
    private Node root;
    private double distance = 1.7976931348623157e+308;
    /** You can assume points has at least size 1. */
    private static class Node {
        Point point;
        Node left, right;
        int depth;
    }
    private void add(Point p) {
        root = add(root, p, 0);
    }
    private Node add(Node x, Point p, int d) {
        if (x == null) {
            x = new Node();
            x.point = p;
            x.depth = d;
            return x;
        }
        if (x.depth % 2 == 0) {
            double xval = x.point.getX();
            if (p.getX() < xval) {
                x.left = add(x.left, p, d + 1);
            } else {
                x.right = add(x.right, p, d + 1);
            }
        } else {
            double yval = x.point.getY();
            if (p.getY() < yval) {
                x.left = add(x.left, p,d + 1);
            } else {
                x.right = add(x.right, p, d + 1);
            }
        }
        return x;
    }
    public KDTree(List<Point> points) {
        for (Point p:points) {
            add(p);
        }
    }

    /** Returns the closest point to the inputted coordinates.
     * This should take \(O(\log N)\) time on average,
     * where \(N\) is the number of points. */
    @Override
    public Point nearest(double x, double y) {
        Point result = null;
        result = nearest(result, root, x, y);
        return result;
    }
    public Point nearest(Point p, Node xp, double x, double y) {
        if (xp == null) {
            return p;
        }
        double d = Point.distance(x, xp.point.getX(), y, xp.point.getY());
        if (d < distance) {
            distance = d;
            p = xp.point;
        }
        if (xp.depth % 2 == 0) {
            double xval = xp.point.getX();
            if (x < xval) {
                p = nearest(p, xp.left, x, y);
                if (Math.pow(xval - x, 2) < distance) {
                    p = nearest(p, xp.right, x, y);
                }
            } else {
                p = nearest(p, xp.right, x, y);
                if (Math.pow(xval - x, 2) < distance)  {
                    p = nearest(p, xp.left, x, y);
                }
            }
        } else {
            double yval = xp.point.getY();
            if (y < yval) {
                p = nearest(p, xp.left, x, y);
                if (Math.pow(yval - y, 2) < distance) {
                    p = nearest(p, xp.right, x, y);
                }
            } else {
                p = nearest(p, xp.right, x, y);
                if (Math.pow(yval - y, 2) < distance) {
                    p = nearest(p, xp.left, x, y);
                }
            }
        }
        return p;
    }
}
