/* *****************************************************************************
 *  Name:    Krishan Kumar
 *  NetID:   kk
 *  Precept: P00
 *
 *  Description:  Prints 'Hello, World' to the terminal window.
 *                By tradition, this is everyone's first program.
 *                Prof. Brian Kernighan initiated this tradition in 1974.
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private WeightedQuickUnionUF obj;
    private WeightedQuickUnionUF obj1;
    private int siz;
    private int[] open_flag;

    public Percolation(int n) {
        if (n <= 0) {
            throw new java.lang.IllegalArgumentException("Illegal argument");
        }
        siz = n;
        obj = new WeightedQuickUnionUF((siz * siz) + 2);
        obj1 = new WeightedQuickUnionUF((siz * siz) + 1);
        // ith element in open_flag array holds flag for ith site-number.
        open_flag = new int[(siz * siz) + 1];
        for (int i = 0; i < ((siz * siz) + 1); i++) {
            open_flag[i] = 0;
        }
        /*
        // Connect all sites from '1 to n' to site 0, which is virtual-top-site.
        for (int i = 1; i <= siz; i++) {
            obj.union(0, i);
            obj1.union(0, i);
        }

        // Connect all sites from '(n^2 - n + 1) to n^2' to site 'n^2 + 1' which is virtual-bottom-site.
        for (int i = (siz * siz - siz + 1); i <= (siz * siz); i++) {
            obj.union(siz * siz + 1, i);
        }
        */
    }

    private int getIndex(int row, int col) {
        int index = 0;
        if (valid_rowCol(row, col)) {
            index = ((row - 1) * siz) + col;
        }
        /* else {
            throw new IndexOutOfBoundsException("row or col index out of bounds");
        } */
        return index;
    }

    private boolean valid_rowCol(int row, int col) {
        // row and column should be > 0 && <= n.
        // System.out.println("Row:" + row + "col:" + col);
        if ((row <= siz && row > 0) && (col <= siz && col > 0)) {
            return true;
        }
        else {
            return false;
            // throw new IndexOutOfBoundsException("row or col index out of bounds");
        }
    }

    public void open(int row, int col) {
        int index;
        int new_index;
        if (valid_rowCol(row, col)) {
            if (isOpen(row, col)) {
                return;
                //System.out.println("Site " + row + "," + col + " is already open");
            }
            else {
                index = getIndex(row, col);
                open_flag[index] = 1;
                if (valid_rowCol(row - 1, col) && isOpen(row - 1, col)) {
                    new_index = getIndex(row - 1, col);
                    obj.union(index, new_index);
                    obj1.union(index, new_index);
                }
                if (valid_rowCol(row, col + 1) && isOpen(row, col + 1)) {
                    new_index = getIndex(row, col + 1);
                    obj.union(index, new_index);
                    obj1.union(index, new_index);
                }
                if (valid_rowCol(row + 1, col) && isOpen(row + 1, col)) {
                    new_index = getIndex(row + 1, col);
                    obj.union(index, new_index);
                    obj1.union(index, new_index);
                }
                if (valid_rowCol(row, col - 1) && isOpen(row, col - 1)) {
                    new_index = getIndex(row, col - 1);
                    obj.union(index, new_index);
                    obj1.union(index, new_index);
                }
                if (row == 1) {
                    obj.union(index, 0);
                    obj1.union(index, 0);
                }
                if (row == (siz)) {
                    obj.union(index, (siz * siz) + 1);
                }
            }
        }
        else {
            throw new IndexOutOfBoundsException("row or col index out of bounds");
        }
    }

    public boolean isOpen(int row, int col) {
        if (valid_rowCol(row, col)) {
            int index = getIndex(row, col);
            if (open_flag[index] == 1) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new IndexOutOfBoundsException("row or col index out of bounds");
        }
        //return false;
    }

    public boolean isFull(int row, int col) {
        if (valid_rowCol(row, col)) {
            int index = getIndex(row, col);
            if (obj1.connected(index, 0)) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            throw new IndexOutOfBoundsException("row or col index out of bounds");
        }
    }

    public int numberOfOpenSites() {
        int count = 0;
        for (int i = 1; i <= (siz * siz); i++) {
            if (open_flag[i] == 1) {
                count++;
            }
        }
        return count;
    }

    public boolean percolates() {
        if (obj.connected(0, (siz * siz) + 1)) {
            return true;
        }
        else return false;
    }

    public static void main(String[] args) {
        /* Percolation ob = new Percolation(3);
        ob.open(1, 1);
        ob.open(1, 2);
        boolean res = obj.connected(1, 2);
        System.out.println(res); */
    }
}
