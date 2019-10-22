package bearmaps;

import java.util.List;

public class KDTree {
    //Point left;
    //Point right;
    Node root;
    private boolean indicator = true;
    //sets to true if x value is compared and false if y value is compared

    public KDTree(List<Point> points) {
        for (int i = 0; i < points.size(); i++) {
            indicator = true;
            root = helperConstruct(root, points.get(i));
            indicator = true;
        }
    }

    private Node helperConstruct(Node p, Point xy) {
        if (p == null) {
            return new Node(xy, indicator);
        } else {
            if (p.point.equals(xy)) {
                assert true;
            } else {
                if (indicator) {
                    int cmp = Double.compare(xy.getX(), p.xPoint);
                    if (cmp < 0) {
                        indicator = !indicator;
                        p.leftChild = helperConstruct(p.leftChild, xy);
                    } else if (cmp >= 0) {
                        indicator = !indicator;
                        p.rightChild = helperConstruct(p.rightChild, xy);
                    }
                } else {
                    int cmp = Double.compare(xy.getY(), p.yPoint);
                    if (cmp < 0) {
                        indicator = !indicator;
                        p.leftChild = helperConstruct(p.leftChild, xy);
                    } else if (cmp >= 0) {
                        indicator = !indicator;
                        p.rightChild = helperConstruct(p.rightChild, xy);
                    }
                }
            }
            return p;
        }
    }

    private class Node {
        private Node leftChild;
        private Node rightChild;
        private double xPoint;
        private double yPoint;
        private Point point;
        private boolean xOry;

        private Node(Point xy, boolean compareWhat) {
            xPoint = xy.getX();
            yPoint = xy.getY();
            point = xy;
            xOry = compareWhat;

        }
    }


    public Point nearest(double x, double y) {
        Point goal = new Point(x, y);
        Point nearestOne = root.point;
        nearestOne = helperNearest(root, goal, nearestOne);
        return nearestOne;
    }

    private Point helperNearest(Node p, Point goal, Point nearest) {
        if (p == null) {
            return nearest;
        }
        double mayBeBetter = p.point.distance(p.point, goal);
        if (mayBeBetter < nearest.distance(nearest, goal)) {
            nearest = p.point;
        }
        if (p.xOry) {
            double minDis = (goal.getX() - p.xPoint) * (goal.getX() - p.xPoint);
            if (goal.getX() < p.xPoint) {
                if (minDis < nearest.distance(nearest, goal)) {
                    nearest = helperNearest(p.rightChild, goal, nearest);
                    nearest = helperNearest(p.leftChild, goal, nearest);
                } else {
                    nearest = helperNearest(p.leftChild, goal, nearest);
                }
            } else {
                if (minDis < nearest.distance(nearest, goal)) {
                    nearest = helperNearest(p.leftChild, goal, nearest);
                    nearest = helperNearest(p.rightChild, goal, nearest);
                } else {
                    nearest = helperNearest(p.rightChild, goal, nearest);
                }
            }
        } else {
            double minDis = (goal.getY() - p.yPoint) * (goal.getY() - p.yPoint);
            if (goal.getY() < p.yPoint) {
                if (minDis < nearest.distance(nearest, goal)) {
                    nearest = helperNearest(p.leftChild, goal, nearest);
                    nearest = helperNearest(p.rightChild, goal, nearest);
                } else {
                    nearest = helperNearest(p.leftChild, goal, nearest);
                }
            } else {
                if (minDis < nearest.distance(nearest, goal)) {
                    nearest = helperNearest(p.rightChild, goal, nearest);
                    nearest = helperNearest(p.leftChild, goal, nearest);
                } else {
                    nearest = helperNearest(p.rightChild, goal, nearest);
                }
            }
        }
        return nearest;
    }
}

