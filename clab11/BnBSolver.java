import java.util.ArrayList;
import java.util.List;

/**
 * BnBSolver for the Bears and Beds problem. Each Bear can only be compared to Bed objects and each Bed
 * can only be compared to Bear objects. There is a one-to-one mapping between Bears and Beds, i.e.
 * each Bear has a unique size and has exactly one corresponding Bed with the same size.
 * Given a list of Bears and a list of Beds, create lists of the same Bears and Beds where the ith Bear is the same
 * size as the ith Bed.
 */
public class BnBSolver {
    private List<Bear> solvedBears;
    private List<Bed> solvedBeds;

    public BnBSolver(List<Bear> bears, List<Bed> beds) {
        Pair<List<Bear>, List<Bed>> pair = new Pair(bears, beds);
        solvedBears = quickSort(pair).first();
        solvedBeds = quickSort(pair).second();
    }

    /**
     * Partitions the given unsorted beds by pivoting on the given item.
     */
    private static void partitionBed(
            List<Bed> unsorted, Bear pivot,
            List<Bed> less, List<Bed> equal, List<Bed> greater) {
        for (Bed item:unsorted) {
            if (item.compareTo(pivot) < 0) {
                less.add(item);
            } else if (item.compareTo(pivot) > 0) {
                greater.add(item);
            } else {
                equal.add(item);
            }
        }
    }

    /**
     * Partitions the given unsorted bears by pivoting on the given item.
     */
    private static void partitionBear(
            List<Bear> unsorted, Bed pivot,
            List<Bear> less, List<Bear> equal, List<Bear> greater) {
        for (Bear item:unsorted) {
            if (item.compareTo(pivot) < 0) {
                less.add(item);
            } else if (item.compareTo(pivot) > 0) {
                greater.add(item);
            } else {
                equal.add(item);
            }
        }
    }

    /**
     * Returns a Queue that contains the given items sorted from least to greatest.
     *
     * @param   pair A Pair of possibly unsorted items
     * @return       A Pair of List<Bear> and List<Beds>
     */
    public static Pair<List<Bear>, List<Bed>> quickSort(Pair<List<Bear>, List<Bed>> pair) {
        if (pair.first().size() <= 1) {
            return pair;
        }
        List<Bed> lessbed = new ArrayList<>();
        List<Bed> equalbed = new ArrayList<>();
        List<Bed> greaterbed = new ArrayList<>();
        Bear pivot = getRandomBear(pair.first());
        partitionBed(pair.second(), pivot, lessbed, equalbed, greaterbed);
        Bed midbed = equalbed.get(0);
        List<Bear> lessbear = new ArrayList<>();
        List<Bear> equalbear = new ArrayList<>();
        List<Bear> greaterbear = new ArrayList<>();
        partitionBear(pair.first(), midbed, lessbear, equalbear, greaterbear);

        Pair<List<Bear>, List<Bed>> less = quickSort(new Pair(lessbear, lessbed));
        Pair<List<Bear>, List<Bed>> greater = quickSort(new Pair(greaterbear, greaterbed));
        return catenate(less, new Pair(equalbear, equalbed), greater);
    }

    /**
     * Returns a random Bear from the given List.
     */
    private static Bear getRandomBear(List<Bear> items) {
        int pivotIndex = (int) (Math.random() * items.size());
        return items.get(pivotIndex);
    }

    /**
     * Returns a new queue that contains the given lists catenated together.
     */
    private static Pair<List<Bear>, List<Bed>> catenate(Pair<List<Bear>, List<Bed>> less,
                   Pair<List<Bear>, List<Bed>> equal, Pair<List<Bear>, List<Bed>> greater) {
        List<Bear> bear = new ArrayList<>();
        bear.addAll(less.first());
        bear.addAll(equal.first());
        bear.addAll(greater.first());
        List<Bed> beds = new ArrayList<>();
        beds.addAll(less.second());
        beds.addAll(equal.second());
        beds.addAll(greater.second());
        return new Pair(bear, beds);
    }


    /**
     * Returns List of Bears such that the ith Bear is the same size as the ith Bed of solvedBeds().
     */
    public List<Bear> solvedBears() {
        return solvedBears;
    }

    /**
     * Returns List of Beds such that the ith Bear is the same size as the ith Bear of solvedBears().
     */
    public List<Bed> solvedBeds() {
        return solvedBeds;
    }
}
