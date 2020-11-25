import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Solver for the Flight problem (#9) from CS 61B Spring 2018 Midterm 2.
 * Assumes valid input, i.e. all Flight start times are >= end times.
 * If a flight starts at the same time as a flight's end time, they are
 * considered to be in the air at the same time.
 */
public class FlightSolver {

    Comparator<Flight> minearliestComparator = Comparator.comparingInt(Flight::startTime);
    Comparator<Flight> minendestComparator = Comparator.comparingInt(Flight::endTime);
    private PriorityQueue<Flight> minearlist = new PriorityQueue<>(minearliestComparator);
    private PriorityQueue<Flight> minendest = new PriorityQueue<>(minendestComparator);
    public FlightSolver(ArrayList<Flight> flights) {
        for (Flight st:flights) {
            minearlist.add(st);
            minendest.add(st);
        }
    }

    public int solve() {
        int currentsum = 0;
        int maxsum = 0;
        while (!minearlist.isEmpty() && !minendest.isEmpty()) {
            Flight currearlist = minearlist.peek();
            Flight currendest = minendest.peek();
            if (currearlist.startTime() <= currendest.endTime()) {
                currentsum += currearlist.passengers;
                minearlist.poll();
            } else {
                minendest.poll();
                currentsum -= currendest.passengers;
            }
            if (currentsum > maxsum) {
                maxsum = currentsum;
            }
        }
        return maxsum;
    }

}
