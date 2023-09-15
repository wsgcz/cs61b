package synthesizer;
import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the ArrayRingBuffer class.
 *  @author Josh Hug
 */

public class TestArrayRingBuffer {
    @Test
    public void someTest() {
        ArrayRingBuffer arb = new ArrayRingBuffer <Integer> (10);
        assertTrue(arb.isEmpty());
        assertFalse(arb.isFull());
        arb.enqueue(3);
        arb.enqueue(4);
        arb.enqueue(6);
        assertEquals(3,arb.dequeue());
        assertEquals(4, arb.peek());
        assertFalse(arb.isEmpty());
        assertEquals(2, arb.fillCount());
    }

    /** Calls tests for ArrayRingBuffer. */
    public static void main(String[] args) {
        jh61b.junit.textui.runClasses(TestArrayRingBuffer.class);
    }
} 
