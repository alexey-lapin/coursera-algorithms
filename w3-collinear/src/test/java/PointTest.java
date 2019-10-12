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

import edu.princeton.cs.algs4.StdRandom;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class PointTest {

    @Test
    void name() {
        var point1 = new Point(1, 2);
        System.out.println(point1.toString());
    }

    @Test
    void name1() {
        var point1 = new Point(1, 2);
        var point2 = new Point(1, 2);

        assertThat(point1.slopeTo(point2)).isEqualTo(Double.NEGATIVE_INFINITY);
    }

    @Test
    void name2() {
        var point1 = new Point(1, 2);
        var point2 = new Point(1, 1);

        assertThat(point1.slopeTo(point2)).isEqualTo(Double.POSITIVE_INFINITY);
    }

    @Test
    void name3() {
        var point1 = new Point(1, 2);
        var point2 = new Point(2, 2);

        assertThat(point1.slopeTo(point2)).isEqualTo(0.0);
    }

    @Test
    void compare_zero() {
        var point1 = new Point(1, 2);
        var point2 = new Point(1, 2);

        assertThat(point1.compareTo(point2)).isZero();
    }

    @Test
    void compare_negative_by_Y() {
        var point1 = new Point(1, 2);
        var point2 = new Point(1, 3);

        assertThat(point1.compareTo(point2)).isNegative();
    }

    @Test
    void compare_negative_by_X() {
        var point1 = new Point(1, 2);
        var point2 = new Point(2, 2);

        assertThat(point1.compareTo(point2)).isNegative();
    }

    @Test
    void name4() {
        Point[] points = new Point[] {
                new Point(1,1),
                new Point(2,2),
                new Point(2,3),
                new Point(2,4),
                new Point(3,3),
                new Point(3,4),
        };
        StdRandom.shuffle(points);
        System.out.println(Arrays.toString(points));
        Arrays.sort(points);
        System.out.println(Arrays.toString(points));
    }
}
