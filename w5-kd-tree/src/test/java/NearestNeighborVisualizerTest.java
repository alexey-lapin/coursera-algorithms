import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class NearestNeighborVisualizerTest {

    @Test
    void test1() {
        NearestNeighborVisualizer.main(new String[]{"input5.txt"});
    }

    @Test
    void test2() {
        NearestNeighborVisualizer.main(new String[]{"input10.txt"});
    }

}
