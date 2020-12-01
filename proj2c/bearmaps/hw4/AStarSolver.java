package bearmaps.hw4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import bearmaps.proj2ab.DoubleMapPQ;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private SolverOutcome outcome;
    private double solutionWeight;
    private List<Vertex> solution;
    private double timeSpent;
    private int numStatesExplored;
    private DoubleMapPQ<Vertex> mapPQ;
    private HashMap<Vertex, Double> distTo;
    private HashMap<Vertex, WeightedEdge<Vertex>> edgeTo;
    private Vertex source;
    private Vertex target;
    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        Stopwatch sw = new Stopwatch();
        source = start;
        target = end;
        /* initialize edgeTo */
        edgeTo = new HashMap<>();
        edgeTo.put(start, null);
        /* initialize disTo */
        distTo = new HashMap<>();
        List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(start);
        for (WeightedEdge<Vertex> s:neighborEdges) {
            distTo.put(s.to(), Double.POSITIVE_INFINITY);
        }
        distTo.put(start, 0.0);
        /* initialize mapPQ */
        mapPQ = new DoubleMapPQ<>();
        mapPQ.add(start, distTo.get(start) + input.estimatedDistanceToGoal(start, end));
        outcome = SolverOutcome.SOLVED;
        while (mapPQ.size() != 0 & !mapPQ.getSmallest().equals(end) & (sw.elapsedTime()) < timeout) {
            relax(input, mapPQ.removeSmallest());
            numStatesExplored += 1;
        }
        timeSpent = sw.elapsedTime();
        if (timeSpent > timeout) {
            outcome = SolverOutcome.TIMEOUT;
        }
    }
    /** p = e.from(), q = e.to(), w = e.weight()
     * if distTo[p] + w < distTo[q]:
     * distTo[q] = distTo[p] + w
     * if q is in the PQ: changePriority(q, distTo[q] + h(q, goal))
     * if q is not in PQ: add(q, distTo[q] + h(q, goal))
     */
    private void relax(AStarGraph<Vertex> G, Vertex v) {
        List<WeightedEdge<Vertex>> neighborEdges = G.neighbors(v);
        for (WeightedEdge<Vertex> s:neighborEdges) {
            Vertex to = s.to();
            if (!distTo.containsKey(to)) {
                distTo.put(to, Double.POSITIVE_INFINITY);
            }
            if (distTo.get(to) > distTo.get(v) + s.weight()) {
                distTo.put(to, distTo.get(v) + s.weight());
                edgeTo.put(to, s);
                if (mapPQ.contains(to)) {
                    mapPQ.changePriority(to, distTo.get(to)
                            + G.estimatedDistanceToGoal(to, target));
                } else {
                    mapPQ.add(to, distTo.get(to)
                            + G.estimatedDistanceToGoal(to, target));
                }
            }
        }
        if (mapPQ.size() == 0) {
            outcome = SolverOutcome.UNSOLVABLE;
        }
    }
    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    /**  A list of vertices corresponding to a solution.
     * Should be empty if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public List<Vertex> solution() {
        solution = new ArrayList<>();
        if (outcome() == SolverOutcome.SOLVED) {
            for (Vertex v = edgeTo.get(target).to(); edgeTo.get(v) != null; v = edgeTo.get(v).from()) {
                solution.add(v);
            }
            solution.add(source);
            Collections.reverse(solution);
            return solution;
        } else {
            return null;
        }
    }

    /** The total weight of the given solution, taking into account edge weights.
     *  Should be 0 if result was TIMEOUT or UNSOLVABLE.*/
    @Override
    public double solutionWeight() {
        solutionWeight = 0;
        if (outcome() == SolverOutcome.SOLVED) {
            for (WeightedEdge<Vertex> v = edgeTo.get(target); v != null; v = edgeTo.get(v.from())) {
                solutionWeight += v.weight();
            }
            return solutionWeight;
        } else {
            return 0;
        }
    }

    /** The total number of priority queue dequeue operations.*/
    @Override
    public int numStatesExplored() {
        return numStatesExplored;
    }

    /** The total time spent in seconds by the constructor.*/
    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
