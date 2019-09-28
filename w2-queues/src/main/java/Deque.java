import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private int size;
    private Node head;
    private Node tail;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item);
        Node oldHead = head;
        head = n;
        if (oldHead != null) {
            n.next = oldHead;
            oldHead.prev = n;
        }
        if (tail == null) {
            tail = n;
        }
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node n = new Node(item);
        Node oldTail = tail;
        tail = n;
        if (oldTail != null) {
            n.prev = oldTail;
            oldTail.next = n;
        }
        if (head == null) {
            head = n;
        }
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldHead = head;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return oldHead.data;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Node oldTail = tail;
        tail = tail.prev;
        if (tail == null) {
            head = null;
        } else {
            tail.next = null;
        }
        size--;
        return oldTail.data;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class Node {
        private final Item data;
        private Node prev;
        private Node next;

        public Node(Item data) {
            this.data = data;
        }
    }

    private class DequeIterator implements Iterator<Item> {
        Node cur = head;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Item item = cur.data;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> d = new Deque<>();
        System.out.println(d.isEmpty());

        d.addFirst("1");
        d.addFirst("2");
        d.addLast("3");
        d.addLast("4");
        for (String s : d) {
            System.out.println(s);
        }
    }

}
