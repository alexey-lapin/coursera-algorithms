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

public class BaseKdTree {

    static Implementations fromResource(String resource) {
        In in = new In(NearestNeighborVisualizer.class.getResource(resource));
        PointSET brute = new PointSET();
        KdTree kdtree = new KdTree();
        char id = 'A';
        while (!in.isEmpty()) {
            double x = in.readDouble();
            double y = in.readDouble();
            Point2D p = new Point2D(x, y)/*.withId(String.valueOf(id))*/;
            kdtree.insert(p);
            brute.insert(p);
            id++;
        }
        return new Implementations(brute, kdtree);
    }

    static class Implementations {
        private final PointSET brute;
        private final KdTree kdTree;

        public Implementations(PointSET brute, KdTree kdTree) {
            this.brute = brute;
            this.kdTree = kdTree;
        }

        public PointSET getBrute() {
            return brute;
        }

        public KdTree getKdTree() {
            return kdTree;
        }
    }

}
