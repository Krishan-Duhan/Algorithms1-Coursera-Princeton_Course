/* *****************************************************************************
 *  Name:    Krishan Kumar
 *  NetID:   kxk
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] perc_thres;
    private int T;

    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0) {
            throw new java.lang.IllegalArgumentException();
        }
        T = trials;
        perc_thres = new double[trials];
        for (int i = 0; i < T; i++) {
            Percolation ob = new Percolation(n);
            while (!ob.percolates()) {
                ob.open(StdRandom.uniform(1, n + 1), StdRandom.uniform(1, n + 1));
            }
            perc_thres[i] = (double) ob.numberOfOpenSites() / (double) (n * n);
        }
    }

    public double mean() {
        return StdStats.mean(perc_thres);
    }

    public double stddev() {
        return StdStats.stddev(perc_thres);
    }

    public double confidenceLo() {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(T)));
    }

    public double confidenceHi() {
        return (mean() - ((1.96 * stddev()) / Math.sqrt(T)));
    }

    public static void main(String[] args) {
        // System.out.println("args0 is:" + args[0]);
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);
        PercolationStats obj = new PercolationStats(n, trials);
        System.out.println("mean                    = " + obj.mean());
        System.out.println("stddev                  = " + obj.stddev());
        System.out.println(
                "95% confidence interval = [" + obj.confidenceLo() + ", " + obj.confidenceHi()
                        + "]");
    }
}
