/** Make a LinkedListDeque. */
public class ArrayDeque<Item> {
    private Item[] items;
    private int size;
    private int nextfirst;
    private int nextlast;
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        size = 0;
        nextfirst = 0;
        nextlast = 7;
    }
    public ArrayDeque(ArrayDeque other) {
        int asize = other.size;
        int bsize;
        if (asize % 8 != 0) {
            bsize = asize + 8 - (asize % 8);
        } else {
            bsize = asize + 8;
        }
        items = (Item[]) new Object[bsize];
        System.arraycopy(other, 0, items, 0, asize);
        nextfirst = bsize - 1;
        nextlast = asize + 1;
    }
    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, a, 0, size);
        items = a;
    }
    public void addFirst(Item item) {
        if (size == items.length - 2) {
            resize(size + 8);
        }
        items[nextfirst] = item;
        do {
            if (nextfirst == 0) {
                nextfirst = items.length - 1;
            } else {
                nextfirst -= 1;
            }
        } while (items[nextfirst] != null);
        size += 1;
    }
    public void addLast(Item item) {
        if (size == items.length - 2) {
            resize(size + 8);
        }
        items[nextlast] = item;
        do {
            if (nextlast == items.length - 1) {
                nextlast = 0;
            } else {
                nextlast += 1;
            }
        } while (items[nextlast] != null);
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (Item item:items) {
            StdOut.print(item);
        }
        StdOut.println();
    }
    public Item removeFirst() {
        int i=0;
        Item item;
        while(items[i]==null) {
            i += 1;
        }
        item = items[i];
        items[i] = null;
        size -= 1;
        return item;
    }
    public Item removeLast(){
        int i = items.length -1 ;
        Item item;
        while(items[i]==null){
            i -= 1;
        }
        item = items[i];
        items[i] = null;
        size -= 1;
        return item;
    }
    public Item get(int index){
        return items[index];
    }
}
