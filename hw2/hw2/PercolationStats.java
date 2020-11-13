package hw2;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.introcs.StdStats;

import java.util.Random;


public class PercolationStats {

    private double allcount;
    private int Times;
    private double[] xt;


    /** perform T independent experiments on an N-by-N grid */
    public PercolationStats(int N, int T, PercolationFactory pf) {
        Times = T;
        xt = new double[T];
        int count;
        for (int i = 0; i < T; i++) {
            count = 0;
            Percolation test = pf.make(N);
            int col;
            int row;
            Random r = new Random() ;
            while (!test.percolates()) {
                col = r.nextInt(N);
                row = r.nextInt(N);
                if (test.isOpen(row,col)){
                    continue;
                }
                test.open(row, col);
                count ++;
            }
            xt[i] = count;
            allcount += count;
        }
    }
    /** sample mean of percolation threshold */
    public double mean() {
        return (double) allcount / Times;
    }
    /** sample standard deviation of percolation threshold */
    public double stddev() {
        return StdStats.stddev(xt);
    }

    /** low endpoint of 95% confidence interval */
    public double confidenceLow() {
        return (double) mean() - 1.96 * stddev() / Math.sqrt(Times);
    }
    /** high endpoint of 95% confidence interval*/
    public double confidenceHigh() {
        return  (double) mean() + 1.96 * stddev() / Math.sqrt(Times);
    }
    public static void main(String[] args) {
        PercolationFactory pf = new PercolationFactory();
        PercolationStats test = new PercolationStats(20,30,pf);
        StdOut.println(test.mean());
        StdOut.println(test.stddev());
        StdOut.println(test.confidenceLow());
        StdOut.println(test.confidenceHigh());
    }
}
