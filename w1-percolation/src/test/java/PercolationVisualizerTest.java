import edu.princeton.cs.algs4.StdDraw;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
public class PercolationVisualizerTest {

    @Test
    void name() {
        PercolationVisualizer.main(new String[]{"input10.txt"});
        while (!StdDraw.hasNextKeyTyped()) {
        }
    }
}
