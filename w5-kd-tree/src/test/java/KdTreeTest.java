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

import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

class KdTreeTest {

    @Test
    void name() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.1, 0.2));
        kdTree.insert(new Point2D(0.2, 0.3));
//        System.out.println(kdTree.contains(new Point2D(1.0, 1.0)));
        System.out.println(kdTree.contains(new Point2D(0.1, 0.3)));
        System.out.println(kdTree.contains(new Point2D(0.1, 0.2)));
    }

    @Test
    void name2() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.625, 0.875));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(0.75, 0.0));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(0.875, 0.25));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(0.5, 0.875));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(0.875, 0.75));
        System.out.println(kdTree.size());
    }

    @Test
    void name22() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0, 1));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(1, 1));
        System.out.println(kdTree.size());
        kdTree.insert(new Point2D(1, 1));
        System.out.println(kdTree.size());
    }

    @Test
    void name3() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.625, 0.875));
        kdTree.insert(new Point2D(0.75, 0.0));
        kdTree.insert(new Point2D(0.875, 0.25));
        kdTree.insert(new Point2D(0.5, 0.875));
        kdTree.insert(new Point2D(0.875, 0.75));

        System.out.println(kdTree.contains(new Point2D(0.875, 0.25)));
        System.out.println(kdTree.contains(new Point2D(0.875, 0.75)));
    }

    @Test
    void name4() {
        KdTree kdTree = new KdTree();
        kdTree.nearest(new Point2D(1, 0));
    }

    @Test
    void name5() {
        KdTree kdTree = new KdTree();
        kdTree.insert(new Point2D(0.372, 0.497)/*.withId("A")*/);
        kdTree.insert(new Point2D(0.564, 0.413)/*.withId("B")*/);
        kdTree.insert(new Point2D(0.226, 0.577)/*.withId("C")*/);
        kdTree.insert(new Point2D(0.144, 0.179)/*.withId("D")*/);
        kdTree.insert(new Point2D(0.083,  0.51)/*.withId("E")*/);
        kdTree.insert(new Point2D(0.32,  0.708)/*.withId("F")*/);
        kdTree.insert(new Point2D(0.417, 0.362)/*.withId("G")*/);
        kdTree.insert(new Point2D(0.862, 0.825)/*.withId("H")*/);
        kdTree.insert(new Point2D(0.785, 0.725)/*.withId("I")*/);
        kdTree.insert(new Point2D(0.499, 0.208)/*.withId("J")*/);

        kdTree.nearest(new Point2D(0.41, 0.62));
        System.out.println();
    }
}
