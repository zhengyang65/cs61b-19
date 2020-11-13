package hw2;

public class UnionFind {

    private int[] size;
    private int[] id;
    private int count;

    /* Creates a UnionFind data structure holding n vertices. Initially, all
       vertices are in disjoint sets. */

    public UnionFind(int n) {
        size = new int[n];
        for (int i = 0; i < n; i++) {
            size[i] = 1;
        }
        size[size.length - 1] += (int) Math.sqrt(size.length);
        size[size.length - 2] += (int) Math.sqrt(size.length);
        id = new int[n];
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        count = n;
    }

    /* Throws an exception if v1 is not a valid index. */

    private void validate(int vertex) {
        if (vertex > size.length | vertex < 0) {
            throw new IllegalArgumentException();
        }
    }

    /* Returns the size of the set v1 belongs to. */

    public int sizeOf(int v1) {
        validate(v1);
        return size[v1];
    }

    /* Returns the parent of v1. If v1 is the root of a tree, returns the
       negative size of the tree for which v1 is the root. */

    public int parent(int v1) {
        validate(v1);
        if (id[v1] == v1) {
            return -size[v1];
        }
        return id[v1];
    }

    /* Returns true if nodes v1 and v2 are connected. */

    public boolean connected(int v1, int v2) {
        validate(v1);
        validate(v2);
        return find(v1) == find(v2);
    }

    /* Connects two elements v1 and v2 together. v1 and v2 can be any valid
       elements, and a union-by-size heuristic is used. If the sizes of the sets
       are equal, tie break by connecting v1's root to v2's root. Unioning a
       vertex with itself or vertices that are already connected should not
       change the sets but may alter the internal structure of the data. */

    public void union(int v1, int v2) {
        validate(v1);
        validate(v2);
        int i = find(v1);
        int j = find(v2);
        if (i == j) {
            return;
        }
        if (i == size.length - 1 | i == size.length) {
            id[j] = i;
            size[i] += size[j];
            return;
        }
        if (j == size.length - 1 | j == size.length) {
            id[i] = j;
            size[j] += size[i];
            return;
        }
        if (size[i] < size[j]) {
            id[i] = j;
            size[j] += size[i];
        } else {
            id[j] = i;
            size[i] += size[j];
        }
        count--;
    }

    /* Returns the root of the set V belongs to. Path-compression is employed
       allowing for fast search-time. */

    public int find(int vertex) {
        validate(vertex);
        int p = vertex;
        int countsize = 0;
        while (p != id[p]) {
            p = id[p];
        }
        while (id[vertex] != p) {
            size[vertex] -= countsize;
            countsize += size[vertex];
            int temp = vertex;
            id[vertex] = p;
            vertex = id[temp];
        }
        size[vertex] -= countsize;
        return p;
    }

}
