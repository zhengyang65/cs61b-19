package creatures;
import huglife.*;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import java.util.Map;

/** Tests the clorus class
 *  @authr zhengyang
 */

public class TestClorus {

    @Test
    public void testBasics() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), p.color());
        p.move();
        assertEquals(1.97, p.energy(), 0.01);
        p.move();
        assertEquals(1.94, p.energy(), 0.01);
        p.stay();
        assertEquals(1.93, p.energy(), 0.01);
        p.stay();
        assertEquals(1.92, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus p = new Clorus(2);
        assertEquals(2, p.energy(), 0.01);
        Clorus p2 = p.replicate();
        assertNotEquals(p,p2);
        assertEquals(1, p2.energy(), 0.01);
        assertEquals(1, p.energy(), 0.01);

    }

    @Test
    public void testAttack() {
        Clorus p = new Clorus(1.2);
        Plip p1 = new Plip(2);
        Clorus p2 = new Clorus(3);
        p.attack(p1);
        assertEquals(3.2, p.energy(), 0.01);
        p.attack(p2);
        assertNotEquals(7.2, p.energy(), 0.01);
        assertEquals(3.2, p.energy(), 0.01);

    }


    @Test
    public void testChoose() {

        // No empty adjacent spaces; stay.
        Clorus p = new Clorus(2);
        HashMap<Direction, Occupant> surrounded = new HashMap<Direction, Occupant>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);
        assertEquals(expected, actual);

        // Attack
        p = new Clorus(1.2);
        HashMap<Direction, Occupant> topEmpty = new HashMap<Direction, Occupant>();
        topEmpty.put(Direction.TOP, new Empty());
        topEmpty.put(Direction.BOTTOM, new Plip(2));
        topEmpty.put(Direction.LEFT, new Impassible());
        topEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(topEmpty);
        expected = new Action(Action.ActionType.ATTACK, Direction.BOTTOM);
        assertEquals(expected, actual);

        // Energy >= 1; replicate towards an empty space.
        p = new Clorus(2.2);
        HashMap<Direction, Occupant> notallEmpty = new HashMap<Direction, Occupant>();
        notallEmpty.put(Direction.TOP, new Impassible());
        notallEmpty.put(Direction.BOTTOM, new Empty());
        notallEmpty.put(Direction.LEFT, new Impassible());
        notallEmpty.put(Direction.RIGHT, new Impassible());

        actual = p.chooseAction(notallEmpty);
        expected = new Action(Action.ActionType.REPLICATE, Direction.BOTTOM);
        assertEquals(expected, actual);

        // Energy < 1; stay.
        p = new Clorus(.99);
        actual = p.chooseAction(notallEmpty);
        expected = new Action(Action.ActionType.MOVE, Direction.BOTTOM);

        assertEquals(expected, actual);
    }
}
