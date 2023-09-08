import org.junit.Test;
import static org.junit.Assert.*;
public class TestOffByN {
    @Test
    public void testOffByN(){
        OffByN obo = new OffByN(5);
        assertTrue(obo.equalChars('a', 'f'));
        assertTrue(obo.equalChars('f', 'a'));
        assertFalse(obo.equalChars('f', 'h'));
    }
}
