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
package jmh;

import edu.princeton.cs.algs4.In;

import java.lang.reflect.Array;

class Helper {

    private Object points;

    Helper init(String input) throws Exception {
        In in = new In(input);
        int n = in.readInt();
        points = Array.newInstance(Class.forName("Point"), n);
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            Array.set(points, i, Class.forName("Point")
                    .getConstructor(int.class, int.class).newInstance(x, y));
        }
        return this;
    }

    Object brute() throws Exception {
        Object bruteCollinearPoints = Class.forName("BruteCollinearPoints")
                .getConstructor(Class.forName("[LPoint;")).newInstance(points);
        return bruteCollinearPoints.getClass()
                .getDeclaredMethod("segments").invoke(bruteCollinearPoints);
    }

    Object fast() throws Exception {
        Object fastCollinearPoints = Class.forName("FastCollinearPoints")
                .getConstructor(Class.forName("[LPoint;")).newInstance(points);
        return fastCollinearPoints.getClass()
                .getDeclaredMethod("segments").invoke(fastCollinearPoints);
    }
}
