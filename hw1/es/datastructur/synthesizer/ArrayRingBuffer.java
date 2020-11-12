package es.datastructur.synthesizer;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;


public class ArrayRingBuffer<T> implements BoundedQueue<T>  {

    /* Index for the next dequeue or peek. */
    private int first;
    /* Index for the next enqueue. */
    private int last;
    /* Variable for the fillCount. */
    private int fillCount;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        rb = (T[]) new Object[capacity];
        fillCount = 0;
        first = 0;
        last = 0;
    }
    @Override
    public int capacity() {
        return rb.length;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow").
     */
    @Override
    public void enqueue(T x) {
        if (fillCount == rb.length) {
            throw new RuntimeException("Ring buffer overflow");
        }
        rb[last] = x;
        fillCount += 1;
        last += 1;
        if (last == rb.length) {
            last = 0;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T dequeue() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        fillCount -= 1;
        T result = rb[first];
        rb[first] = null;
        first += 1;
        if (first == rb.length) {
            first = 0;
        }
        return result;
    }

    /**
     * Return oldest item, but don't remove it. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow").
     */
    @Override
    public T peek() {
        if (fillCount == 0) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return rb[first];

    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayRingBufferIterator();
    }
    private class ArrayRingBufferIterator implements Iterator<T> {
        private T[]  curr = rb;
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < rb.length;
        }
        @Override
        public T next() {
            index += 1;
            return curr[index - 1];
        }
    }
    @Override
    public boolean equals(Object other) {

        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (other.getClass() != this.getClass()) {
            return false;
        }
        ArrayRingBuffer<T> o = (ArrayRingBuffer<T>) other;
        if (o.fillCount != this.fillCount) {
            return false;
        }
        // copy this.rb
        ArrayRingBuffer<T>  o2 = new ArrayRingBuffer<T> (rb.length);
        for (T item : this) {
            o2.enqueue(item);
        }

        for (T item : o) {
            if (item != o2.dequeue()) {
                return false;
            }
        }
        return true;
    }

}

