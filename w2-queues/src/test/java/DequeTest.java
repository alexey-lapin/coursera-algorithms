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


class DequeTest {

//    private Deque

    @Test
    void name() {
//        Deque<Integer> deque = new Deque<>();
//        deque.removeLast();
        System.out.println("deque");
    }

    @Test
    void name2() {
        Deque<Integer> deque = new Deque<>();
        deque.addFirst(0);
//        deque.addLast(1);
//        deque.addLast(2);
//        deque.removeFirst();
//        deque.removeLast();
        deque.removeLast();
    }

    @Test
    void name3() {
        Deque<Integer> deque = new Deque<Integer>();
        deque.addLast(1);
        int r1 = deque.removeLast(); // 1
        System.out.println(r1);
        deque.addLast(3);
        deque.addFirst(4);
        int r2 = deque.removeFirst(); // 4
        System.out.println(r2);
        deque.addFirst(6);
        deque.addLast(7);
        int r3 = deque.removeLast(); // 7
        System.out.println(r3);
        System.out.println(deque.size());
        for (Integer i : deque) {
            System.out.print(i);
        }
//        Comparator.comparingInt(String::length, new ToIntFunction<String>() {});
    }
}
