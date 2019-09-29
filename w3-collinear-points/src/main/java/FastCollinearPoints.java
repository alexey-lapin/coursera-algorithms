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
import edu.princeton.cs.algs4.Bag;

import java.util.Arrays;

public class FastCollinearPoints {

    private final LineSegment[] segments;

    // finds all line segments containing 4 or more points
    public FastCollinearPoints(Point[] input) {
        // check input array for null
        if (input == null) {
            throw new IllegalArgumentException();
        }

        // check elements of input array for null
        for (Point p : input) {
            if (p == null) {
                throw new IllegalArgumentException();
            }
        }

        // check duplicates for array of size 2
        if (input.length == 2
                && doubleEquals(input[0].slopeTo(input[1]), Double.NEGATIVE_INFINITY)) {
            throw new IllegalArgumentException();
        }

        // check duplicates for array of size 3
        if (input.length == 3
                && (doubleEquals(input[0].slopeTo(input[1]), Double.NEGATIVE_INFINITY)
                || doubleEquals(input[0].slopeTo(input[2]), Double.NEGATIVE_INFINITY)
                || doubleEquals(input[1].slopeTo(input[2]), Double.NEGATIVE_INFINITY))) {
            throw new IllegalArgumentException();
        }


        Bag<LineSegment> bag = new Bag<>();

        Point[] points = new Point[input.length];
        System.arraycopy(input, 0, points, 0, input.length);
        Arrays.sort(points);

        for (Point point : points) {
//            colorPoints(Color.RED, point);

            Point[] slopeSortedPoints = new Point[points.length];
            System.arraycopy(points, 0, slopeSortedPoints, 0, points.length);
            Arrays.sort(slopeSortedPoints, point.slopeOrder());

//            Map<Double, Integer> groupedSlopes = debugSlopes(point, slopeSortedPoints);

            int firstGroupIndex = 0;
            int groupCount = 1;

            Bag<Point[]> groups = new Bag<>();

            for (int i = 0; i < slopeSortedPoints.length - 1; i++) {
                Point currentPoint = slopeSortedPoints[i];
                Point nextPoint = slopeSortedPoints[i + 1];
                double currentSlope = point.slopeTo(currentPoint);
                double nextSlope = point.slopeTo(nextPoint);

                if (doubleEquals(nextSlope, Double.NEGATIVE_INFINITY)) {
                    throw new IllegalArgumentException();
                }

                if (doubleEquals(currentSlope, nextSlope)) {
                    groupCount++;
                } else {
                    if (groupCount >= 3) {
                        Point[] group = new Point[groupCount];
                        System.arraycopy(slopeSortedPoints, firstGroupIndex, group, 0, groupCount);
                        groups.add(group);
                    }
                    firstGroupIndex = i + 1;
                    groupCount = 1;
                }
            }

            if (groupCount >= 3) {
                Point[] arr = new Point[groupCount];
                System.arraycopy(slopeSortedPoints, firstGroupIndex, arr, 0, groupCount);
                groups.add(arr);
            }

            for (Point[] arr : groups) {
                Arrays.sort(arr);
                if (point.compareTo(arr[0]) < 0) {
                    bag.add(new LineSegment(point, arr[arr.length - 1]));
                }
            }
//            colorPoints(Color.BLACK, point);
        }

        int i = 0;
        segments = new LineSegment[bag.size()];
        for (LineSegment segment : bag) {
            segments[i] = segment;
            i++;
        }
    }

//    private Map<Double, Integer> debugSlopes(Point point, Point[] slopeSortedPoints) {
//        Map<Double, Integer> map = new HashMap<>();
//        double[] slopes = new double[slopeSortedPoints.length];
//        for (int i = 0; i < slopeSortedPoints.length; i++) {
//            double slope = point.slopeTo(slopeSortedPoints[i]);
//            slopes[i] = slope;
//            map.compute(slope, (s, count) -> {
//                if (count == null) return 1;
//                else return ++count;
//            });
//        }
//        return map.entrySet().stream()
//                .filter(e -> e.getValue() >= 3)
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
//    }

    private boolean doubleEquals(double d1, double d2) {
        return Double.compare(d1, d2) == 0;
    }

    // the number of line segments
    public int numberOfSegments() {
        return segments.length;
    }

    // the line segments
    public LineSegment[] segments() {
        return Arrays.copyOf(segments, segments.length);
    }

//    private void colorPoints(Color color, Point... points) {
//        StdDraw.setPenColor(color);
//        for (Point point : points) {
//            point.draw();
//        }
//        StdDraw.show();
//    }
}
