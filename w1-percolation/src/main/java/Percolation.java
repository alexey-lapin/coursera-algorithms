import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private static final int NEIGHBOURS = 4;

    private final int requestedSize;
    private final int virtualSize;

    private int opened = 0;
    private final boolean[] site;
    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.requestedSize = n;
        this.virtualSize = requestedSize * requestedSize + 2;
        this.site = new boolean[virtualSize];
        this.uf = new WeightedQuickUnionUF(virtualSize);
        this.uf2 = new WeightedQuickUnionUF(virtualSize);
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            opened++;
            int index = coordinatesToIndex(row, col);
            site[index] = true;
            for (int neighbourIndex : coordinatesToNeighbourIndexes(row, col)) {
                if (neighbourIndex > 0 && isOpen(neighbourIndex)) {
                    uf.union(index, neighbourIndex);
                    uf2.union(index, neighbourIndex);
                }
            }
            // connect to virtual top
            if (row == 1) {
                uf.union(0, index);
                uf2.union(0, index);
            }
            // connect to virtual bottom
            if (row == requestedSize) {
                uf.union(virtualSize - 1, index);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return isOpen(coordinatesToIndex(row, col));
    }

    private boolean isOpen(int index) {
        return site[index];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return uf2.connected(0, coordinatesToIndex(row, col));
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return opened;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(0, virtualSize - 1);
    }

    private void validate(int row, int col) {
        if (!isValid(row, col)) {
            throw new IllegalArgumentException();
        }
    }

    private boolean isValid(int row, int col) {
        return row >= 1 && row <= requestedSize && col >= 1 && col <= requestedSize;
    }

    private int coordinatesToIndex(int row, int col) {
        if (!isValid(row, col)) {
            return -1;
        }
        return requestedSize * (row - 1) + (col - 1) + 1;
    }

    private int[] coordinatesToNeighbourIndexes(int row, int col) {
        int[] ret = new int[NEIGHBOURS];
        ret[0] = coordinatesToIndex(row, col - 1); // left
        ret[1] = coordinatesToIndex(row, col + 1); // right
        ret[2] = coordinatesToIndex(row - 1, col); // up
        ret[3] = coordinatesToIndex(row + 1, col); // down
        return ret;
    }

    // test client (optional)
    public static void main(String[] args) {
//        Percolation p = new Percolation(2);
//        p.open(1, 2);
//        p.open(2, 2);
//        System.out.println(p.isFull(2, 2));
//        System.out.println(p.isOpen(2, 2));
//        System.out.println(p.percolates());
    }
}