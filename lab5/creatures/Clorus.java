package creatures;

import huglife.*;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Objects;

public class Clorus extends Creature {

    /**
     * red color.
     */
    private int r;
    /**
     * green color.
     */
    private int g;
    /**
     * blue color.
     */
    private int b;

    /**
     * creates plip with energy equal to E.
     */
    public Clorus (double e) {
        super("clorus");
        if (e < 0) {
            energy = 0;
        }else { energy = e;}
        r = 0;
        g = 0;
        b = 0;
    }

    /**
     * Should return a color with red = 34, green = 0, blue = 231.
     */
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }

    /**
     * If a Clorus attacks another creature, it should gain that creature’s energy.
     * @param c the attacked creature
     */
    public void attack(Creature c) {
        if (c.name().equals("plip")){
            energy += c.energy();
        }
    }

    /**
     * Clorus loss 0.03 energy when moving.
     */
    public void move() {
        energy -= 0.03;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Plips loss 0.01 energy when staying.
     */
    public void stay() {
        energy -= 0.01;
        if (energy < 0) {
            energy = 0;
        }
    }

    /**
     * Like a Plip, when a Clorus replicates, it keeps 50% of its energy.
     * The other 50% goes to its offspring. No energy is lost in the replication process.
     */
    public Clorus replicate() {
        Clorus p2 = new Clorus(energy/2.0);
        energy /= 2;
        return p2;
    }

    /**
     * Cloruss take exactly the following actions based on NEIGHBORS:
     * 1. If there are no empty squares, the Clorus will STAY
     * (even if there are Plips nearby they could attack
     * since plip squares do not count as empty squares).
     * 2. Otherwise, if any Plips are seen, the Clorus will ATTACK one of them randomly.
     * 3.Otherwise, if the Clorus has energy greater than or equal to one,
     * it will REPLICATE to a random empty square.
     * 4. Otherwise, the Clorus will MOVE to a random empty square.
     * <p>
     * Returns an object of type Action. See Action.java for the
     * scoop on how Actions work. See SampleCreature.chooseAction()
     * for an example to follow.
     */
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        // Rule 1
        Deque<Direction> emptyNeighbors = new ArrayDeque<>();
        Deque<Direction> plipNeighbors = new ArrayDeque<>();
        boolean anyPlip = false;
        for (Direction key : neighbors.keySet()) {
            Occupant value = neighbors.get(key);
            if (value.name().equals("empty")) {
                emptyNeighbors.add(key);
            }
            if (value.name().equals("plip")) {
                plipNeighbors.add(key);
                anyPlip = true;
            }
        }
        if (emptyNeighbors.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        }

        // Rule 2
        if (anyPlip) {
            int i = HugLifeUtils.randomInt(1, emptyNeighbors.size());
            while (i > 1) {
                Direction removeres = plipNeighbors.removeFirst();
                plipNeighbors.addLast(removeres);
                i -= 1;
            }
            Direction result = plipNeighbors.getFirst();
            return new Action(Action.ActionType.ATTACK,result);
        }

        // Rule 3
        if (energy >= 1.0) {
            int i = HugLifeUtils.randomInt(1, emptyNeighbors.size());
            while (i > 1) {
                Direction removeres = emptyNeighbors.removeFirst();
                emptyNeighbors.addLast(removeres);
                i -= 1;
            }
            Direction result = emptyNeighbors.getFirst();
            return new Action(Action.ActionType.REPLICATE,result);
        }

        // Rule 4
        int i = HugLifeUtils.randomInt(1, emptyNeighbors.size());
        while (i > 1) {
            Direction removeres = emptyNeighbors.removeFirst();
            emptyNeighbors.addLast(removeres);
            i -= 1;
        }
        Direction result = emptyNeighbors.getFirst();
        return new Action(Action.ActionType.MOVE,result);
    }
}
