/******************************************************************************
 *  Compilation:  javac NearestNeighborVisualizer.java
 *  Execution:    java NearestNeighborVisualizer input.txt
 *  Dependencies: PointSET.java KdTree.java
 *
 *  Read points from a file (specified as a command-line argument) and
 *  draw to standard draw. Highlight the closest point to the mouse.
 *
 *  The nearest neighbor according to the brute-force algorithm is drawn
 *  in red; the nearest neighbor using the kd-tree algorithm is drawn in blue.
 *
 ******************************************************************************/

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.*;

public class NearestNeighborDrawer {

//    private s


    public static void main(String[] args) {

        char id = 'A';

//        Point2D target = new Point2D(0.35, 0.39);
//        Point2D target = new Point2D(0.6875, 0.5);
        Point2D target = new Point2D(0.21875, 0.625);
        printTarget(target);
//        In in = new In(NearestNeighborVisualizer.class.getResource("custom-A.txt"));
        In in = new In(NearestNeighborVisualizer.class.getResource("custom-B.txt"));
//        In in = new In(NearestNeighborVisualizer.class.getResource("input10.txt"));
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y)/*.withId(String.valueOf(id))*/;
//            StdDraw.textLeft(p.x(), p.y(), p.getId());
            id++;
            kdtree.insert(p);
            brute.insert(p);
        }

        // process nearest neighbor queries
        StdDraw.enableDoubleBuffering();
        brute.draw();
        kdtree.draw();

        StdDraw.setPenRadius(0.03);
        StdDraw.setPenColor(StdDraw.RED);
        brute.nearest(target).draw();
        StdDraw.setPenRadius(0.02);
        StdDraw.setPenColor(StdDraw.BLUE);
//        kdtree.nearest(target, new Callback()).draw();
        kdtree.nearest(target).draw();

        StdDraw.show();
//        kdtree.toString();
    }

/*
    static class Callback implements KdTree.NearestCallback {

        @Override
        public void call(KdTree kdTree, KdTree.Node node, Point2D target) {
            StdDraw.clear();
            printCurrent(node.point);
            kdTree.draw();
            node.draw();
            printTarget(target);
            StdDraw.show();
        }
    }
*/
    static void printTarget(Point2D point) {
        var radius = StdDraw.getPenRadius();
        var color = StdDraw.getPenColor();
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.setPenRadius(0.02);
        point.draw();
        StdDraw.show();
        StdDraw.setPenRadius(radius);
        StdDraw.setPenColor(color);
    }

    static void printCurrent(Point2D point) {
        var radius = StdDraw.getPenRadius();
        var color = StdDraw.getPenColor();
        StdDraw.setPenColor(Color.CYAN);
        StdDraw.setPenRadius(0.02);
        point.draw();
        StdDraw.show();
        StdDraw.setPenRadius(radius);
        StdDraw.setPenColor(color);
    }
}
