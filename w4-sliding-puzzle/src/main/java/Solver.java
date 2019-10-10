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

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

public class Solver {

    private Node solution;

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        if (initial == null) {
            throw new IllegalArgumentException();
        }
        solve(initial);
    }

    private void solve(Board board) {
        if (solution != null) {
            return;
        }

        MinPQ<Node> originalQueue = new MinPQ<>(Comparator.comparingInt(o -> o.moves + o.manhattan));
        MinPQ<Node> twinQueue = new MinPQ<>(Comparator.comparingInt(o -> o.moves + o.manhattan));

        originalQueue.insert(new Node(0, board, null));
        twinQueue.insert(new Node(0, board.twin(), null));

        while (true) {
            Node originalNode = originalQueue.delMin();
            if (originalNode.board.isGoal()) {
                solution = originalNode;
                break;
            }
            Node twinNode = twinQueue.delMin();
            if (twinNode.board.isGoal()) {
                break;
            }

            for (Board neighbor : originalNode.board.neighbors()) {
                if (originalNode.prev == null || !neighbor.equals(originalNode.prev.board)) {
                    originalQueue.insert(new Node(originalNode.moves + 1, neighbor, originalNode));
                }
            }
            for (Board neighbor : twinNode.board.neighbors()) {
                if (twinNode.prev == null || !neighbor.equals(twinNode.prev.board)) {
                    twinQueue.insert(new Node(twinNode.moves + 1, neighbor, twinNode));
                }
            }
        }
    }

    private static class Node {
        private final int moves;
        private final Board board;
        private final Node prev;
        private final int manhattan;

        Node(int moves, Board board, Node prev) {
            this.moves = moves;
            this.board = board;
            this.prev = prev;
            this.manhattan = board.manhattan();
        }
    }

    // is the initial board solvable?
    public boolean isSolvable() {
        return solution != null;
    }

    // min number of moves to solve initial board
    public int moves() {
        if (solution == null) {
            return -1;
        }
        return solution.moves;
    }

    // sequence of boards in a shortest solution
    public Iterable<Board> solution() {
        if (solution == null) {
            return null;
        }
        Stack<Board> stack = new Stack<>();
        Node cur = solution;
        while (cur != null) {
            stack.push(cur.board);
            cur = cur.prev;
        }
        return stack;
    }

    public static void main(String[] args) {
        System.out.println();
    }

}
