package creatures;

import edu.princeton.cs.introcs.StdRandom;
import huglife.Action;
import huglife.Creature;
import huglife.Direction;
import huglife.Occupant;

import java.awt.*;
import java.util.List;
import java.util.Map;

public class Clorus extends Creature {
    private int r;
    private int g;
    private int b;
    public Clorus(double e) {
        super("clorus");
        r = 34;
        g = 0;
        b = 231;
        energy = e;
    }
    public Clorus() {
        this(1);
    }

    @Override
    public void move() {
        energy -= 0.03;
    }

    @Override
    public void attack(Creature c) {
        energy += c.energy();
    }

    @Override
    public Clorus replicate() {
        energy = energy / 2;
        return new Clorus(energy);
    }

    @Override
    public void stay() {
        energy -= 0.01;
    }

    @Override
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        int numberOfEmpty = empties.size();
        if (empties.isEmpty()) {
            return new Action(Action.ActionType.STAY);
        } else  {
            List<Direction> plips = getNeighborsOfType(neighbors, "plip");
            int numberOfPlips = plips.size();
            if (!plips.isEmpty()) {
                int randonPlip = StdRandom.uniform(numberOfPlips);
                return new Action(Action.ActionType.ATTACK, plips.get(randonPlip));
            } else {
                if (energy >= 1) {
                    int randonEmpty = StdRandom.uniform(numberOfEmpty);
                    return new Action(Action.ActionType.REPLICATE, empties.get(randonEmpty));
                }
            }
        }
        int randonEmpty = StdRandom.uniform(numberOfEmpty);
        return new Action(Action.ActionType.MOVE, empties.get(randonEmpty));
    }

    @Override
    public Color color() {
        return new Color(r, g, b);
    }
}
