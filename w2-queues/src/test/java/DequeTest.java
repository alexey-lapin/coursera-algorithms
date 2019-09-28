import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.function.ToIntFunction;

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