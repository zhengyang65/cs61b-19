/** Make a LinkedListDeque. */
public class LinkedListDeque<Item> {
    private class IntNode {
        public Item item;
        public IntNode prev;
        public IntNode next;

        public IntNode(IntNode p, Item i, IntNode n) {
            prev = p;
            item = i;
            next = n;
        }
    }
    private IntNode sentinel;
    private int size;

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new IntNode(null, null, null);
        IntNode ptr = other.sentinel;
        IntNode ptr2 = sentinel;
        for (size = 0; size < other.size; size++) {
            ptr = ptr.next;
            sentinel.next = new IntNode(null, ptr.item, null);
            sentinel.next.prev = sentinel;
            sentinel = sentinel.next;
        }
        ptr2.prev = sentinel;
        sentinel.next = ptr2;

    }
    public LinkedListDeque() {
        sentinel = new IntNode(null, null, null);
        size = 0;
        sentinel.prev = sentinel;
        sentinel.next = sentinel;

    }
    public void addFirst(Item item) {
        IntNode ptr = new IntNode(null, item, null);
        sentinel.next.prev = ptr;
        ptr.next = sentinel.next;
        sentinel.next = ptr;
        ptr.prev = sentinel;
        size += 1;
    }
    public void addLast(Item item) {
        IntNode ptr = new IntNode(null, item, null);
        sentinel.prev.next = ptr;
        ptr.prev = sentinel.prev;
        sentinel.prev = ptr;
        ptr.next = sentinel;
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return  size;
    }
    public void printDeque() {
        IntNode ptr = sentinel;
        for (int i = 0; i < size; i++) {
            ptr = ptr.next;
            StdOut.print(ptr.item);
        }
        StdOut.println();
    }
    public Item removeFirst() {
        if (size == 0) {
            return null;
        }
        Item result = sentinel.next.item;
        IntNode ptr = sentinel.next.next;
        sentinel.next = null;
        sentinel.next = ptr;
        ptr.prev = sentinel;
        size -= 1;
        return result;
    }
    public Item removeLast() {
        if (size == 0) {
            return null;
        }
        Item result = sentinel.prev.item;
        IntNode ptr = sentinel.prev.prev;
        sentinel.prev = null;
        ptr.next = sentinel;
        sentinel.prev = ptr;
        size -= 1;
        return  result;
    }
    public Item get(int index) {
        if (size == 0 | (index >= size)) {
            return null;
        }
        IntNode ptr = sentinel;
        for (int i = 0; i <= index; i++) {
            ptr = sentinel.next;
        }
        return ptr.item;

    }
    public Item getRecursive(int index) {
        IntNode ptr = sentinel;
        if (size == 0 | (index >= size)) {
            return null;
        }
        while (index >= 0) {
            ptr = getRecursiveHelper(ptr);
            index -= 1;
        }
        return ptr.item;
    }
    public IntNode getRecursiveHelper(IntNode dequeptr) {
        IntNode ptr = dequeptr.next;
        return ptr;
    }
}
