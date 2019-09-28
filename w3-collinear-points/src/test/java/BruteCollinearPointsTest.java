import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;

import java.io.File;

class BruteCollinearPointsTest {

    @Test
    void name() throws Exception {
        In in = new In(new File(getClass().getResource("input40.txt").toURI()));
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

    public static void main(String[] args) throws Exception {
        In in = new In(new File(BruteCollinearPointsTest.class.getResource("my2.txt").toURI()));
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
//            if(i == 20) {
//                points[i] = null;
//            } else {
                points[i] = new Point(x, y);
//            }
        }

        // draw the points
        StdDraw.setPenRadius(0.004D);
        StdDraw.enableDoubleBuffering();
//        StdDraw.setXscale(0, 32768);
        StdDraw.setXscale(0, 10);
//        StdDraw.setYscale(0, 32768);
        StdDraw.setYscale(0, 10);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}