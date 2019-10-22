package bearmaps;

import edu.princeton.cs.algs4.Stopwatch;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static junit.framework.TestCase.assertEquals;

public class KDTreeTest {
    private static Random r = new Random(500);
    public static KDTree buildLectureTree() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7));
        return kd;
    }

    public static void buildTreewithDoubles() {
        Point p1 = new Point(2, 3); //
        Point p2 = new Point(2, 3);
        KDTree kd = new KDTree(List.of(p1, p2));
    }
    @Test

    public void sampleTest() {
        Point p1 = new Point(5.1, 3.5);
        Point p2 = new Point(9.1, 2.3);
        Point p3 = new Point(10.0, 1.9);
        Point p4 = new Point(1.1, 2.1);
        Point p5 = new Point(0.1, 0.4);
        Point p6 = new Point(3, 5.95);
        Point p7 = new Point(4.1, 4.19);
        Point p8 = new Point(1.102, 3.1050);
        Point p9 = new Point(1.77, 8.66);
        Point p10 = new Point(6.11, 6.3651);
        Point p11 = new Point(1.12, 11);
        Point p12 = new Point(5.61, 12);
        Point p13 = new Point(6.1235, 13.2);
        Point p14 = new Point(0, 0.4);
        Point p15 = new Point(0.1, 0.4);

        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                p11, p12, p13, p14, p15));
        NaivePointSet naive = new NaivePointSet(List.of(p1, p2, p3, p4, p5, p6, p7,
                p8, p9, p10, p11, p12, p13, p14, p15));
        System.out.println("my implemenation gives me nearest X point as "
                + kd.nearest(0, 0).getX() + " for the nearest point to (0, 0)");
        System.out.println("my implemenation gives me nearest X point as "
                + kd.nearest(0, 0).getY() + " for the nearest point to (0, 0)");
        System.out.println("naivePointSet gives me nearest X point as "
                + naive.nearest(0, 0).getX() + " for the nearest point to (0, 0)");
        System.out.println("naivePointSet gives me nearest Y point as "
                + naive.nearest(0, 0).getY() + " for the nearest point to (0, 0)");
    }
    @Test
    /** Tests code with the example from the lecture */
    public void testNearestDemoSlides() {
        KDTree kd = buildLectureTree();
        Point actual = kd.nearest(0, 7);
        Point expected = new Point(1, 5);
        assertEquals(expected, actual);
    }
    @Test
    public void testDuplicates() {
        Point p1 = new Point(2, 3);
        Point p2 = new Point(4, 2);
        Point p3 = new Point(4, 2);
        Point p8 = new Point(4, 2);
        Point p4 = new Point(4, 5);
        Point p5 = new Point(3, 3);
        Point p6 = new Point(1, 5);
        Point p7 = new Point(4, 4);
        KDTree kd = new KDTree(List.of(p1, p2, p3, p4, p5, p6, p7, p8));
        assertEquals(kd.nearest(4, 3), p2);
        assertEquals(kd.nearest(4, 3), p3);
        assertEquals(kd.nearest(4, 3), p8);
        assertEquals(kd.nearest(4, 1), p2);



    }

    @Test
    public void testDoubleTree() {
        Point p1 = new Point(2, 3); //
        Point p2 = new Point(12, 13); //
        KDTree kd = new KDTree(List.of(p1));
        assertEquals(kd.nearest(1000, 10000), new Point(2, 3));

        //insertion order matters?
        Point p3 = new Point(2, 3); //
        Point p4 = new Point(12, 13); //
        Point p5 = new Point(12.00000111, 13.0000001111);
        KDTree kd1 = new KDTree(List.of(p4, p3, p5));
        assertEquals(kd1.nearest(1000, 10000), p5);
        assertEquals(kd1.nearest(0, 0), p3);
        assertEquals(kd1.nearest(12, 13), p4);
    }
    @Test
    public void testSingleTree() {
        Point p1 = new Point(-1099999, -29999); //
        KDTree kd = new KDTree(List.of(p1));
        assertEquals(kd.nearest(1000, 10000), new Point(-1099999, -29999));

    }

    private Point randomPoint() {
        double x = r.nextDouble();
        double y = r.nextDouble();
        return new Point(x, y);
    }
    /** Return N random points */
    private List<Point> randomPoints(int N) {
        List<Point> points = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            points.add(randomPoint());
        }
        return points;
    }

    @Test
    public void testEx() {
        List<Point> points1000 = randomPoints(100000);
        NaivePointSet nps = new NaivePointSet(points1000);
        KDTree kd = new KDTree(points1000);
        List<Point> queries200 = randomPoints(20000);
        for (Point p : queries200) {
            Point expected = nps.nearest(p.getX(), p.getY());
            Point actual = kd.nearest(p.getX(), p.getY());
            assertEquals(expected, actual);
        }
    }


    @Test
    public void naiveSpeedTestWith10000PointsAnd10000Queries() {
        int pointCount = 10000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        NaivePointSet naive = new NaivePointSet(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            naive.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount
                + " queries on " + pointCount + " points: "
                + timing.elapsedTime() + " for naive nearest");
    }
    @Test
    public void naiveSpeedTestWith100000PointsAnd2000Queries() {
        int pointCount = 100000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        NaivePointSet naive = new NaivePointSet(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            naive.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount
                + " queries on " + pointCount + " points: "
                + timing.elapsedTime() + " for naive nearest");
    }
    @Test
    public void naiveSpeedTestWith1000000PointsAnd2000Queries() {
        int pointCount = 1000000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        NaivePointSet naive = new NaivePointSet(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            naive.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount
                + " queries on " + pointCount + " points: "
                + timing.elapsedTime() + " for naive nearest");
    }

    @Test
    public void kdSpeedTestWith10000PointsAnd10000Queries() {
        int pointCount = 10000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        KDTree kd = new KDTree(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount + " queries on "
                + pointCount + " points: " + timing.elapsedTime() + " for kd nearest");
    }

    @Test
    public void kdSpeedTestWith100000PointsAnd10000Queries() {
        int pointCount = 100000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        KDTree kd = new KDTree(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount + " queries on "
                + pointCount + " points: " + timing.elapsedTime() + " for kd nearest");
    }
    @Test
    public void kdSpeedTestWith1000000PointsAnd10000Queries() {
        int pointCount = 1000000;
        int queriesCount = 10000;
        List<Point> points = randomPoints(pointCount);
        List<Point> queries = randomPoints(queriesCount);
        KDTree kd = new KDTree(points);
        Stopwatch timing = new Stopwatch();
        for (Point p : queries) {
            kd.nearest(p.getX(), p.getY());
        }
        System.out.println("Time elasped for " + queriesCount + " queries on "
                + pointCount + " points: " + timing.elapsedTime() + " for kd nearest");
    }
}
