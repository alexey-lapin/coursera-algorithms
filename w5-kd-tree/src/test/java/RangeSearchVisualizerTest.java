import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class RangeSearchVisualizerTest {

    @Test
    void test1() {
        RangeSearchVisualizer.main(new String[]{"input5.txt"});
    }

    @Test
    void test2() {
        RangeSearchVisualizer.main(new String[]{"input10.txt"});
    }
}
