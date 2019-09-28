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
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoardTest {

    @Test
    void should_toString() {

        int[][] a = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };

        var board = new Board(a);

        assertThat(board.toString()).isEqualTo("3\n4 2 6\n5 1 3\n8 7 0");
    }

    @Test
    void should_dimension3() {
        int[][] a = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };

        var board = new Board(a);

        assertThat(board.dimension()).isEqualTo(3);
    }

    @Test
    void should_dimension4() {
        int[][] a = new int[][]{
                {4, 2, 6, 9},
                {5, 1, 3, 10},
                {8, 7, 0, 11},
                {12, 13, 14, 15}
        };

        var board = new Board(a);

        assertThat(board.dimension()).isEqualTo(4);
    }

    @Test
    void should_hamming_0_when_goal() {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var board = new Board(a);

        assertThat(board.hamming()).isEqualTo(0);
    }

    @Test
    void should_hamming_1() {
        int[][] a = new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1}
        };

        var board = new Board(a);

        assertThat(board.hamming()).isEqualTo(1);
    }

    @Test
    void should_hamming_8() {
        int[][] a = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };

        var board = new Board(a);

        assertThat(board.hamming()).isEqualTo(7);
    }

    @Test
    void should_manhattan_0_when_goal() {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var board = new Board(a);

        assertThat(board.manhattan()).isEqualTo(0);
    }

    @Test
    void should_manhattan_4() {
        int[][] a = new int[][]{
                {0, 2, 3},
                {4, 5, 6},
                {7, 8, 1}
        };

        var board = new Board(a);

        assertThat(board.manhattan()).isEqualTo(4);
    }

    @Test
    void should_manhattan_8() {
        int[][] a = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };

        var board = new Board(a);

        assertThat(board.manhattan()).isEqualTo(1 + 1 + 1 + 2 + 1 + 1 + 1);
    }

    @Test
    void should_equals() {
        int[][] a = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };
        int[][] b = new int[][]{
                {4, 2, 6},
                {5, 1, 3},
                {8, 7, 0}
        };

        var boarda = new Board(a);
        var boardb = new Board(b);

        assertThat(boarda).isEqualTo(boardb);
    }

    @Test
    void should_neighbors_1() {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 0, 5},
                {6, 7, 8}
        };

        var board = new Board(a);

        board.neighbors().forEach(System.out::println);
    }

    @Test
    void should_goal() {
        int[][] a = new int[][]{
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 0}
        };

        var board = new Board(a);

        assertThat(board.isGoal()).isTrue();
    }

    @Test
    void should_goal_2() {
        int[][] a = new int[][]{
                {1, 0},
                {2, 3},
        };

        var board = new Board(a);

        assertThat(board.isGoal()).isFalse();
    }
}
