package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;
public class TestClorus {
    @Test
    public void testname() {
        Clorus clorus = new Clorus();
        assertEquals(clorus.name(), "clorus");
    }
    @Test
    public void testenergy() {
        Clorus clorus = new Clorus(1.5);
        clorus.move();
        assertEquals(clorus.energy(), 1.47, 0.01);
        clorus.stay();
        assertEquals(clorus.energy(), 1.46, 0.01);
    }
    @Test
    public void testColor() {
        Clorus clorus = new Clorus();
        assertEquals(new Color(34, 0 , 231), clorus.color());
    }
    @Test
    public void testattck() {
            Clorus clorus = new Clorus();
            clorus.attack(new Plip(1));
            assertEquals(clorus.energy(), 2, 0.01);
    }
    @Test
    public void testrep(){
        Clorus clorus = new Clorus();
        Clorus son = (Clorus) clorus.replicate();
        assertEquals(clorus.energy(), 0.5, 0.01);
        assertEquals(son.energy(), 0.5, 0.01);
    }
}
