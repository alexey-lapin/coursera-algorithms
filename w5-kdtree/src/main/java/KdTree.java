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
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class KdTree {

    private static final double RADIUS_POINT = 0.008;
    private static final double RADIUS_LINE = 0.001;

    private static final RectHV ROOT_RECT = new RectHV(0, 0, 1, 1);

    private Node root;
    private int size = 0;

    // construct an empty set of points
    public KdTree() {
    }

    // is the set empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // number of points in the set
    public int size() {
        return size;
    }

    // add the point to the set (if it is not already in the set)
    public void insert(Point2D point) {
        if (point == null) throw new IllegalArgumentException();

        this.root = this.insert(this.root, point, true);
    }

    private Node insert(Node node, Point2D point, boolean isVertical) {
        if (node == null) {
            size++;
            return new Node(point, isVertical);
        } else if (node.point.equals(point)) {
            return node;
        } else {
            if (node.compareToPoint(point) < 0) {
                node.leftBottom = this.insert(node.leftBottom, point, !isVertical);
            } else {
                node.rightTop = this.insert(node.rightTop, point, !isVertical);
            }
            return node;
        }
    }

    // does the set contain point p?
    public boolean contains(Point2D point) {
        if (point == null) throw new IllegalArgumentException();

        return contains(root, point);
    }

    private boolean contains(Node node, Point2D point) {
        if (node == null) {
            return false;
        } else if (node.point.equals(point)) {
            return true;
        } else {
            if (node.compareToPoint(point) < 0) {
                return this.contains(node.leftBottom, point);
            } else {
                return this.contains(node.rightTop, point);
            }
        }
    }

    // draw all points to standard draw
    public void draw() {
        Color color = StdDraw.getPenColor();
        double radius = StdDraw.getPenRadius();

        draw(Node.withRect(root, ROOT_RECT));

        StdDraw.setPenColor(color);
        StdDraw.setPenRadius(radius);
    }

    private void draw(Node node) {
        if (node == null) return;

        StdDraw.setPenColor(Color.BLACK);
        StdDraw.setPenRadius(RADIUS_POINT);
        node.point.draw();
//        if (node.point.toString() != null) {
//            StdDraw.textLeft(node.point.x(), node.point.y(), node.point.toString());
//        }
        StdDraw.setPenRadius(RADIUS_LINE);
        if (node.rect != null) {
            if (node.isVertical) {
                StdDraw.setPenColor(Color.RED);
                StdDraw.line(node.point.x(), node.rect.ymin(), node.point.x(), node.rect.ymax());
            } else {
                StdDraw.setPenColor(Color.BLUE);
                StdDraw.line(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.point.y());
            }
        }

        draw(Node.lb(node));
        draw(Node.rt(node));
    }

    // all points that are inside the rectangle (or on the boundary)
    public Iterable<Point2D> range(RectHV query) {
        if (query == null) throw new IllegalArgumentException();

        List<Point2D> list = new ArrayList<>();
        range(query, root, list);
        return list;
    }

    private void range(RectHV query, Node node, List<Point2D> list) {
        if (node == null) return;

        if (query.contains(node.point)) {
            list.add(node.point);
        }
        if (node.isVertical) {
            if (node.point.x() >= query.xmin() && node.point.x() <= query.xmax()) {
                range(query, node.leftBottom, list);
                range(query, node.rightTop, list);
            } else if (node.point.x() > query.xmin()) {
                range(query, node.leftBottom, list);
            } else {
                range(query, node.rightTop, list);
            }
        } else {
            if (node.point.y() >= query.ymin() && node.point.y() <= query.ymax()) {
                range(query, node.leftBottom, list);
                range(query, node.rightTop, list);
            } else if (node.point.y() > query.ymin()) {
                range(query, node.leftBottom, list);
            } else {
                range(query, node.rightTop, list);
            }
        }
    }

    // a nearest neighbor in the set to point p; null if the set is empty
    public Point2D nearest(Point2D query) {
        if (query == null) throw new IllegalArgumentException();

        if (root == null) return null;

        return nearest(query, Node.withRect(root, ROOT_RECT), root.point);
    }

    private Point2D nearest(Point2D query, Node node, Point2D nearest) {
        if (node == null) return nearest;

        if (nearest.distanceSquaredTo(query) < node.rect.distanceSquaredTo(query)) return nearest;

        if (nearest.distanceSquaredTo(query) > node.point.distanceSquaredTo(query)) {
            nearest = node.point;
        }

        if (node.compareToPoint(query) < 0) {
            nearest = nearest(query, Node.lb(node), nearest);
            nearest = nearest(query, Node.rt(node), nearest);
        } else {
            nearest = nearest(query, Node.rt(node), nearest);
            nearest = nearest(query, Node.lb(node), nearest);
        }
        return nearest;
    }

    // unit testing of the methods (optional)
    public static void main(String[] args) {
        System.out.println();
    }

    private static class Node {
        // the point
        private final Point2D point;
        // the axis-aligned rectangle corresponding to this node
        private RectHV rect;
        // the left/bottom subtree
        private Node leftBottom;
        // the right/top subtree
        private Node rightTop;
        // node orientation
        private final boolean isVertical;

        Node(Point2D point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
        }

        private static double getKey(Point2D point, boolean isVertical) {
            return isVertical ? point.x() : point.y();
        }

        private int compareToPoint(Point2D to) {
            return Double.compare(getKey(to, isVertical), getKey(point, isVertical));
        }

        private static Node withRect(Node node, RectHV rect) {
            if (node == null) return null;
            node.rect = rect;
            return node;
        }

        private static RectHV getSubRect(Node node, int side) {
            if (node == null || node.rect == null) return null;
            if (node.isVertical) {
                if (side < 0) {
                    return new RectHV(node.rect.xmin(), node.rect.ymin(), node.point.x(), node.rect.ymax());
                } else {
                    return new RectHV(node.point.x(), node.rect.ymin(), node.rect.xmax(), node.rect.ymax());
                }
            } else {
                if (side < 0) {
                    return new RectHV(node.rect.xmin(), node.rect.ymin(), node.rect.xmax(), node.point.y());
                } else {
                    return new RectHV(node.rect.xmin(), node.point.y(), node.rect.xmax(), node.rect.ymax());
                }
            }
        }

        private static Node lb(Node node) {
            return withRect(node.leftBottom, getSubRect(node, -1));
        }

        private static Node rt(Node node) {
            return withRect(node.rightTop, getSubRect(node, 1));
        }

    }
}
