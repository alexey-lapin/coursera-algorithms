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
