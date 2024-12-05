/*
 * Written by Lance Kimani
 */
import java.util.LinkedList;

public class CommandQueue<T> {
    private LinkedList<T> queue;

    public CommandQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(T item) {
        queue.add(item);
    }

    public T dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
