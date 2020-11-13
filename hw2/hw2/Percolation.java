package hw2;

import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

public class Percolation {
    private int column;
    private UnionFind unionFind;
    private int[] isopen;
    private int opennumber;

    /** create N-by-N grid, with all sites initially blocked*/

    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        column = N;
        unionFind = new UnionFind(N * N + 2);
        for (int i = 0; i < N; i++) {
            unionFind.union(N * N, i);
            unionFind.union(N * N + 1, N * N - 1 - i);
        }
        opennumber = 0;
        isopen = new int[N * N];
        Arrays.fill(isopen, 0);
    }
    public int xyTo1D(int r, int c) {
        return r * column + c;
    }

    public void validate(int row, int col) {
        if (row >= column | col >= column) {
            throw new IndexOutOfBoundsException();
        }
    }

    /** open the site (row, col) if it is not open already*/

    public void open(int row, int col) {
        validate(row, col);
        if (isOpen(row, col)) {
            return;
        }
        int i = xyTo1D(row, col);
        isopen[i] = 1;
        opennumber += 1;

        //upper of grid
        if (row == 0) {
            //upper left corner of grid
            if (col == 0) {
                if (isopen[xyTo1D(row, col + 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col + 1));
                }
                if (isopen[xyTo1D(row + 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row + 1, col));
                }
                return;
            }
            //upper right corner of grid
            if (col == column - 1) {
                if (isopen[xyTo1D(row, col - 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col - 1));
                }
                if (isopen[xyTo1D(row + 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row + 1, col));
                }
                return;
            } else {
                if (isopen[xyTo1D(row, col - 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col - 1));
                }
                if (isopen[xyTo1D(row + 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row + 1, col));
                }

                if (isopen[xyTo1D(row, col + 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col + 1));
                }
            }
            return;
        }

        //bottom of grid
        if (row == column - 1) {
            //bottom left corner of grid
            if (col == 0) {
                if (isopen[xyTo1D(row, col + 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col + 1));
                }
                if (isopen[xyTo1D(row - 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row - 1, col));
                }
                return;
            }
            //bottom right corner of grid
            if (col == column - 1) {
                if (isopen[xyTo1D(row, col - 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col - 1));
                }
                if (isopen[xyTo1D(row - 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row - 1, col));
                }
                return;
            } else {
                if (isopen[xyTo1D(row, col - 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col - 1));
                }
                if (isopen[xyTo1D(row - 1, col)] == 1) {
                    unionFind.union(i, xyTo1D(row - 1, col));
                }

                if (isopen[xyTo1D(row, col + 1)] == 1) {
                    unionFind.union(i, xyTo1D(row, col + 1));
                }
            }
            return;
        }

        //Left edge of grid
        if (col == 0) {
            if (isopen[xyTo1D(row + 1, col)] == 1) {
                unionFind.union(i, xyTo1D(row + 1, col));
            }
            if (isopen[xyTo1D(row - 1, col)] == 1) {
                unionFind.union(i, xyTo1D(row - 1, col));
            }
            if (isopen[xyTo1D(row, col + 1)] == 1) {
                unionFind.union(i, xyTo1D(row, col + 1));
            }
            return;
        }

        //Right edge of grid
        if (col == column - 1) {
            if (isopen[xyTo1D(row + 1, col)] == 1) {
                unionFind.union(i, xyTo1D(row + 1, col));
            }
            if (isopen[xyTo1D(row - 1, col)] == 1) {
                unionFind.union(i, xyTo1D(row - 1, col));
            }
            if (isopen[xyTo1D(row, col - 1)] == 1) {
                unionFind.union(i, xyTo1D(row, col - 1));
            }
            return;
        }
        //Other
        if (isopen[xyTo1D(row + 1, col)] == 1) {
            unionFind.union(i, xyTo1D(row + 1, col));
        }
        if (isopen[xyTo1D(row - 1, col)] == 1) {
            unionFind.union(i, xyTo1D(row - 1, col));
        }
        if (isopen[xyTo1D(row, col + 1)] == 1) {
            unionFind.union(i, xyTo1D(row, col + 1));
        }
        if (isopen[xyTo1D(row, col - 1)] == 1) {
            unionFind.union(i, xyTo1D(row, col - 1));
        }
    }

    /** is the site (row, col) open?*/

    public boolean isOpen(int row, int col) {
        validate(row, col);
        int i = xyTo1D(row, col);
        return isopen[i] != 0;
    }

    /** is the site (row, col) full? */

    public boolean isFull(int row, int col) {
        validate(row, col);
        int i = xyTo1D(row, col);
        return unionFind.connected(column * column, i);
    }

    /** number of open sites*/

    public int numberOfOpenSites() {
        return opennumber;
    }

    /** does the system percolate? */

    public boolean percolates() {
        return unionFind.connected(column * column, column * column + 1);
    }

    /** use for unit testing (not required, but keep this here for the autograder)*/
    public static void main(String[] args) {
        Percolation test = new Percolation(4);
        test.open(1, 2);
        test.open(2, 2);
        test.open(2, 1);
        test.open(2, 3);
        test.open(0, 1);
        test.open(1, 1);
        test.open(3, 2);
        StdOut.println(test.isFull(2, 2));
        StdOut.println(test.percolates());
    }
}
