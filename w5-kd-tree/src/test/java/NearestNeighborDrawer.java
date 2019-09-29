/*
 * MIT License
 *
 * Copyright (c) 2019 - present Alexey Lapin
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
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
