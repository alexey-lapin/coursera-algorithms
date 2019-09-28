import edu.princeton.cs.algs4.Point2D;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class NearestNeighbourTest {

    /*
       A
      / \
     B   E
    / \
   C   D
     */
    @Test
    void name() {
        var implementations = BaseKdTree.fromResource("input5.txt");
        var target = new Point2D(0.81, 0.64);

        var nearestBrute = implementations.getBrute().nearest(target);
        var nearestKdTree = implementations.getKdTree().nearest(target);

        assertThat(nearestKdTree).isEqualTo(nearestBrute);
    }

    //input10 A C D B G J H I
    @Test
    void name2() {
        var implementations = BaseKdTree.fromResource("input10.txt");
        var target = new Point2D(0.35, 0.39);

        var nearestBrute = implementations.getBrute().nearest(target);
        var nearestKdTree = implementations.getKdTree().nearest(target);

        assertThat(nearestKdTree).isEqualTo(nearestBrute);
    }

    @Test
    void name3() {
        var implementations = BaseKdTree.fromResource("custom-B.txt");
        var target = new Point2D(0.21875, 0.625);

        var nearestBrute = implementations.getBrute().nearest(target);
        var nearestKdTree = implementations.getKdTree().nearest(target);

        assertThat(nearestKdTree).isEqualTo(nearestBrute);
    }
}
