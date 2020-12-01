package bearmaps.proj2ab;

import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {
    private int size;
    public ArrayList<T> entry;
    public ArrayList<Double> prioritylist;

    /** Initialization */
    public ArrayHeapMinPQ() {
        size = 0;
        entry = new ArrayList<>();
        prioritylist = new ArrayList<Double>();
        entry.add(0, null);
        prioritylist.add(0,  -1.0);
    }
    /** Adds an item with the given priority value. Throws an
     * IllegalArgumentExceptionb if item is already present.
     * You may assume that item is never null. */
    @Override
    public void add(T item, double priority) {
        if (item == null) {
            throw new IllegalArgumentException("null is illegal argument");
        }
        if (contains(item)) {
            throw  new IllegalArgumentException(" the item already exists");
        }
        entry.add(item);
        prioritylist.add(priority);
        size += 1;
        swim(size);
    }

    /** Returns true if the PQ contains the given item. */
    @Override
    public boolean contains(T item) {
        if (item == null) {
            throw new IllegalArgumentException("null is illegal argument");
        }
        return entry.contains(item);
    }
    /** Returns the minimum item. Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return entry.get(1);
    }
    /** Removes and returns the minimum item.
     * Throws NoSuchElementException if the PQ is empty. */
    @Override
    public T removeSmallest() {
        if (size == 1) {
            throw new NoSuchElementException("PQ is empty");
        }
        exch(1,size);
        prioritylist.remove(size);
        T result = entry.remove(size);
        size -= 1;
        sink(1);
        return result;
    }
    /** Returns the number of items in the PQ. */
    @Override
    public int size() {
        return entry.size() - 1;
    }
    /** Changes the priority of the given item. Throws NoSuchElementException if the item
     * doesn't exist. */
    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("No such item");
        }
        int in = entry.indexOf(item);
        if (prioritylist.get(in) > priority){
            prioritylist.set(in, priority);
            swim(in);
        }else {
            prioritylist.set(in, priority);
            sink(in);
        }
    }

    /** Helper functions to restore the heap invariant.*/
    private void swim(int k) {
        while (k > 1 && greater(k/2, k)) {
            exch(k, k/2);
            k = k/2;
        }
    }

    private void sink(int k) {
        while (2*k <= size()) {
            int j = 2*k;
            if (j < size() && greater(j, j+1)) {j++;}
            if (!greater(k, j)) {break;}
            exch(k, j);
            k = j;
        }
    }

    /** Helper functions for compares and swaps. */
    private boolean greater(int i, int j) {
        return prioritylist.get(i) >= prioritylist.get(j);
    }

    private void exch(int i, int j) {
        Collections.swap(entry,i,j);
        Collections.swap(prioritylist,i,j);
    }
}

