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
