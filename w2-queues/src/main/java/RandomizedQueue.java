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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] arr = (Item[]) new Object[1];

    private int size;
    private int tail = -1;

    // construct an empty randomized queue
    public RandomizedQueue() {

    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        if (arr.length == size) {
            resize(arr.length * 2);
        }
        arr[++tail] = item;
        size++;
    }

    private void resize(int newSize) {
        Item[] a = (Item[]) new Object[newSize];
        // System.arraycopy(arr, 0, a, 0, size);
        for (int i = 0; i < size; i++) {
            a[i] = arr[i];
        }
        arr = a;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        if (arr.length / 4 > 0 && arr.length / 4 == size) {
            resize(arr.length / 2);
        }
        int i = random();
        Item item = arr[i];
        arr[i] = arr[tail];
        arr[tail] = null;
        tail--;
        size--;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return arr[random()];
    }

    private int random() {
        return StdRandom.uniform(tail + 1);
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {

        private final Item[] a = (Item[]) new Object[size];
        private int cur = 0;

        private RandomizedQueueIterator() {
//            System.arraycopy(arr, 0, a, 0, size);
            for (int i = 0; i < size; i++) {
                a[i] = arr[i];
            }
            StdRandom.shuffle(a);
        }

        @Override
        public boolean hasNext() {
            return cur < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return a[cur++];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();
        rq.enqueue(1);
        System.out.println(rq.dequeue());
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        System.out.println(rq.dequeue());
        rq.enqueue(5);
        rq.enqueue(6);
        System.out.print("rq=");
        for (Integer i : rq) {
            System.out.print(i);
        }
        System.out.println();
//        System.out.print("dequeue=" + rq.dequeue());
        System.out.print("size=" + rq.size());
    }

}
