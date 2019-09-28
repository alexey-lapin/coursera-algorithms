import edu.princeton.cs.algs4.Bag;

import java.util.Arrays;

public class BruteCollinearPoints {

    private final LineSegment[] segments;

    /**
     * finds all line segments containing 4 points
     *
     * @param input
     */
    public BruteCollinearPoints(Point[] input) {
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

        Point start = null;
        Point end = null;
        double prevSlope = 0;
        boolean first = true;

        for (int indexI = 0; indexI < points.length - 3; indexI++) {
            for (int indexJ = indexI + 1; indexJ < points.length - 2; indexJ++) {
                for (int indexK = indexJ + 1; indexK < points.length - 1; indexK++) {
                    for (int indexL = indexK + 1; indexL < points.length; indexL++) {
//                        System.out.println(i + " " + j + " " + k + " " + l);
                        Point pointI = points[indexI];
                        Point pointJ = points[indexJ];
                        Point pointK = points[indexK];
                        Point pointL = points[indexL];

                        if (pointI == null || pointJ == null || pointK == null || pointL == null) {
                            throw new IllegalArgumentException();
                        }

                        double ij = pointI.slopeTo(pointJ);
                        double ik = pointI.slopeTo(pointK);
                        double il = pointI.slopeTo(pointL);
                        double jk = pointJ.slopeTo(pointK);
                        double jl = pointJ.slopeTo(pointL);
                        double kl = pointK.slopeTo(pointL);

                        // check for duplicates
                        if (doubleEquals(ij, Double.NEGATIVE_INFINITY)
                                || doubleEquals(ik, Double.NEGATIVE_INFINITY)
                                || doubleEquals(il, Double.NEGATIVE_INFINITY)
                                || doubleEquals(jk, Double.NEGATIVE_INFINITY)
                                || doubleEquals(jl, Double.NEGATIVE_INFINITY)
                                || doubleEquals(kl, Double.NEGATIVE_INFINITY)) {
                            throw new IllegalArgumentException();
                        }

                        if (Double.compare(ij, ik) == 0 && Double.compare(ij, il) == 0) {
//                            colorPoints(Color.RED, pointI, pointJ, pointK, pointL);
                            double slope = ik;
                            if (!first) {
                                // if same slope and same line in relation to previous 4 points
                                if (!(doubleEquals(slope, prevSlope) && doubleEquals(ij, start.slopeTo(pointI)))) {
                                    LineSegment line = new LineSegment(start, end);
//                                    colorSegment(Color.RED, line);
                                    bag.add(line);
//                                    colorSegment(Color.WHITE, line);
                                    start = pointI;
                                    end = pointL;
                                } else {
                                    end = pointL;
                                }
                            } else {
                                start = pointI;
                                end = pointL;
                            }
                            prevSlope = slope;
                            first = false;
//                            colorPoints(Color.BLACK, pointI, pointJ, pointK, pointL);
                        }
                    }
                }
            }
        }

        if (start != null) {
            bag.add(new LineSegment(start, end));
        }

        LineSegment[] array = new LineSegment[bag.size()];
        int i = 0;
        for (LineSegment lineSegment : bag) {
            array[i] = lineSegment;
            i++;
        }
        this.segments = array;
    }

//    private void colorPoints(Color color, Point... points) {
//        StdDraw.setPenColor(color);
//        for (Point point : points) {
//            point.draw();
//        }
//        StdDraw.show();
//    }

//    private void colorSegment(Color color, LineSegment segment) {
//        StdDraw.setPenColor(color);
//        segment.draw();
//        StdDraw.show();
//    }

    private boolean doubleEquals(double d1, double d2) {
        return Double.compare(d1, d2) == 0;
    }

    /**
     * @return the number of line segments
     */
    public int numberOfSegments() {
        return this.segments.length;
    }

    /**
     * @return the line segments
     */
    public LineSegment[] segments() {
        return Arrays.copyOf(this.segments, this.segments.length);
    }
}
