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
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {

    private static final double PERCENTILE_95 = 1.96;

    private final int n;
    private final int trials;
    private final double[] results;
    private double stddev = -1.0;
    private double mean = -1.0;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        this.trials = trials;
        this.results = new double[trials];
        compute();
    }

    // sample mean of percolation threshold
    public double mean() {
        if (mean == -1.0) {
            mean = StdStats.mean(results);
        }
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        if (stddev == -1.0) {
            stddev = StdStats.stddev(results);
        }
        return stddev;
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - PERCENTILE_95 * stddev / squareRoot(trials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + PERCENTILE_95 * stddev / squareRoot(trials);
    }

    // test client (see below)
    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        System.out.println("mean = " + ps.mean());
    }

    private double test(int s) {
        Percolation percolation = new Percolation(s);
        while (!percolation.percolates()) {
            percolation.open(StdRandom.uniform(1, s + 1), StdRandom.uniform(1, s + 1));
        }
        return (percolation.numberOfOpenSites() + 0.0) / (s * s);
    }

    private void compute() {
        for (int i = 0; i < trials; i++) {
            results[i] = test(n);
        }
    }

    private double squareRoot(int number) {
        double temp;

        double sr = (double) number / 2;

        do {
            temp = sr;
            sr = (temp + (number / temp)) / 2;
        } while ((temp - sr) != 0);

        return sr;
    }

}
