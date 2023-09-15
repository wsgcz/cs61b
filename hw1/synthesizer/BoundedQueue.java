package synthesizer;
import java.util.Iterator;
public interface BoundedQueue <T> extends Iterable<T> {
    public int capacity();
    public int fillCount(); //return number of items currently in the queue
    public void  enqueue(T x);
    T dequeue();
    T peek();
    default boolean isEmpty() {
        return fillCount() == 0;
    }
    default  boolean isFull() {
        return fillCount() == capacity();
    }
}
