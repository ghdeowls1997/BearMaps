package bearmaps.hw4;
import bearmaps.proj2ab.ArrayHeapMinPQ;
import edu.princeton.cs.algs4.Stopwatch;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/** implementing A* algorithm */
public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {
    private ArrayHeapMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
    private HashMap<Vertex, Double> distTo = new HashMap<>();
    private HashMap<Vertex, Vertex> edgeTo = new HashMap<>();
    private Vertex goal;
    private Vertex source;
    private double totalTime;
    private SolverOutcome outcome;
    private ArrayList<Vertex> solution;
    private int exploredCount = 0;

    public AStarSolver(AStarGraph<Vertex> input, Vertex start, Vertex end, double timeout) {
        timeout = timeout * 1000000;
        goal = end;
        source = start;
        solution = new ArrayList<>();
        pq.add(start, input.estimatedDistanceToGoal(start, end));
        distTo.put(start, 0.0);
        edgeTo.put(start, start);
        Stopwatch sw = new Stopwatch();
        while (pq.size() != 0) {
            start = pq.removeSmallest();
            totalTime = sw.elapsedTime();
            if (timeout < totalTime) {
                totalTime = sw.elapsedTime();
                outcome = SolverOutcome.TIMEOUT;
                solution = new ArrayList<>();
                return;
            }
            if (start.equals(end)) {
                totalTime = sw.elapsedTime();
                outcome = SolverOutcome.SOLVED;
                solution = solutionHelper(edgeTo);
                return;
            }
            List<WeightedEdge<Vertex>> neighborEdges = input.neighbors(start);
            for (WeightedEdge<Vertex> e : neighborEdges) {
                double newDist = distTo.get(e.from()) + e.weight();
                if (distTo.containsKey(e.to())) {
                    if (newDist < distTo.get(e.to())) {
                        distTo.put(e.to(), newDist);
                        edgeTo.put(e.to(), e.from());
                        if (pq.contains(e.to())) {
                            pq.changePriority(e.to(),
                                    input.estimatedDistanceToGoal(e.to(), goal) + newDist);
                        }
                    }
                } else {
                    distTo.put(e.to(), newDist);
                    edgeTo.put(e.to(), e.from());
                    if (!edgeTo.containsValue(e.to())) {
                        pq.add(e.to(),
                                input.estimatedDistanceToGoal(e.to(), goal) + distTo.get(e.to()));
                    }
                }
            }
            exploredCount += 1;
        }
        outcome = SolverOutcome.UNSOLVABLE;
        solution = new ArrayList<>();
        totalTime = sw.elapsedTime();
    }
    private ArrayList<Vertex> solutionHelper(HashMap<Vertex, Vertex> edgetomap) {
        if (edgetomap.size() == 1) {
            ArrayList<Vertex> sol = new ArrayList<>();
            sol.add(source);
            return sol;
        } else {
            Vertex end = goal;
            ArrayList<Vertex> sol = new ArrayList<>();
            while (!end.equals(source)) {
                sol.add(end);
                end = edgetomap.get(end);
            }
            sol.add(source);
            Collections.reverse(sol);
            return sol;
        }
    }


    public SolverOutcome outcome() {
        return outcome;
    }

    public List<Vertex> solution() {
        return solution;
    }

    public double solutionWeight() {
        return distTo.get(goal);
    }

    public int numStatesExplored() {
        return exploredCount;
    }

    public double explorationTime() {
        return totalTime;
    }

}
