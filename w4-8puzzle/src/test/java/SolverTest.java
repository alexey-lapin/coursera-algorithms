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

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import org.junit.jupiter.api.Test;


class SolverTest {

    @Test
    void name() {
        int[][] a = new int[][] {
                {1,2,3},
                {4,5,6},
                {0,7,8}
        };
        Board board = new Board(a);
        Solver solver = new Solver(board);
        System.out.println(solver.moves());
        solver.solution().forEach(System.out::println);
    }

    @Test
    void name2() {
        int[][] a = new int[][] {
                {1,0,2},
                {7,5,4},
                {8,6,3}
        };
        Board board = new Board(a);
        Solver solver = new Solver(board);
        System.out.println(solver.moves());
        solver.solution().forEach(System.out::println);
    }


    @Test
    void name3() {
        int[][] a = new int[][] {
                { 1,  2,  3,  4,  5,  7, 14},
                { 8,  9, 10, 11, 12, 13,  6},
                {15, 16, 17, 18, 19, 20, 21},
                {22, 23, 24, 25, 26, 27, 28},
                {29, 30, 31, 32,  0, 33, 34},
                {36, 37, 38, 39, 40, 41, 35},
                {43, 44, 45, 46, 47, 48, 42}
        };
        Board board = new Board(a);
        Solver solver = new Solver(board);
        System.out.println(solver.moves());
        solver.solution().forEach(System.out::println);
    }

    private Board fromResource(String resource) {
        In in = new In(getClass().getResource(resource));
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        return new Board(tiles);
    }

    @Test
    void name4() {
        Board initial = fromResource("puzzle11.txt");
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
        }
//        System.out.println("invocations: " + Board.invocations);
    }

    @Test
    void name5() {
        Board initial = fromResource("puzzle14.txt");
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
        }
//        System.out.println("invocations: " + Board.invocations);
    }

    @Test
    void name6() {
        Board initial = fromResource("puzzle2x2-unsolvable1.txt");
        Solver solver = new Solver(initial);

        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
        }
    }

}
