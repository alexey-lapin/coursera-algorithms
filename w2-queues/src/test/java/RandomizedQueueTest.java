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

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class RandomizedQueueTest {

    @Test
    void name() {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        printSize(rq);
        rq.enqueue(2);
        printSize(rq);
        rq.enqueue(3);
        printSize(rq);
        rq.enqueue(4);
        printSize(rq);
        rq.enqueue(5);
        printSize(rq);
        rq.enqueue(6);
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        rq.dequeue();
        printSize(rq);
        assertThatThrownBy(() -> rq.dequeue()).isInstanceOf(NoSuchElementException.class);
    }

    private void printSize(RandomizedQueue<?> randomizedQueue) {
        System.out.println("size=" + randomizedQueue.size());
    }
}
