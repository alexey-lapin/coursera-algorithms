import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
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