import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Board {

    private final int[][] tiles;

    private int hamming = -1;
    private int manhattan = -1;

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] input) {
        tiles = copy(input);
    }

    // string representation of this board
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(tiles.length).append("\n");
        for (int i = 0; i < tiles.length; i++) {
            for (int j = 0; j < tiles.length; j++) {
                sb.append(tiles[i][j]);
                if (j < tiles.length - 1) {
                    sb.append(" ");
                }
            }
            if (i < tiles.length - 1) {
                sb.append("\n");
            }
        }
        return sb.toString();
    }

    // board dimension n
    public int dimension() {
        return tiles.length;
    }

    // number of tiles out of place
    public int hamming() {
        if (hamming != -1) {
            return hamming;
        }
        int dist = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] > 0 && tiles[i][j] != getGoalTile(i, j)) dist++;
            }
        }
        hamming = dist;
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        if (manhattan != -1) {
            return manhattan;
        }
        int dist = 0;
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] > 0 && tiles[i][j] != getGoalTile(i, j)) {
                    int tile = tiles[i][j];
                    int row = (tile - 1) / dimension();
                    int col = (tile - 1) % dimension();
                    dist += abs(row - i) + abs(col - j);
                }
            }
        }
        manhattan = dist;
        return manhattan;
    }

    private int abs(int num) {
        return num < 0 ? -1 * num : num;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                if (tiles[i][j] != getGoalTile(i, j) && !(i == dimension() - 1 && j == dimension() - 1)) {
                    return false;
                }
            }
        }
        return true;
    }

    private int getGoalTile(int i, int j) {
        return i * dimension() + j + 1;
    }

    // does this board equal y?
    @Override
    public boolean equals(Object that) {
        if (this == that) return true;
        if (that == null || getClass() != that.getClass()) return false;
        Board board = (Board) that;
        return Arrays.deepEquals(tiles, board.tiles);
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {
        int row = -1;
        int col = -1;
        List<Board> neighbors = new ArrayList<>();
        outer:
        {
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (tiles[i][j] == 0) {
                        row = i;
                        col = j;
                        break outer;
                    }
                }
            }
        }
        if (row > 0) {
            neighbors.add(new Board(swap(row - 1, col, row, col)));
        }
        if (row < dimension() - 1) {
            neighbors.add(new Board(swap(row + 1, col, row, col)));
        }
        if (col > 0) {
            neighbors.add(new Board(swap(row, col - 1, row, col)));
        }
        if (col < dimension() - 1) {
            neighbors.add(new Board(swap(row, col + 1, row, col)));
        }
        return neighbors;
    }

    private int[][] swap(int s1x, int s1y, int s2x, int s2y) {
        int[][] a = copy(tiles);
        int tmp = a[s1x][s1y];
        a[s1x][s1y] = a[s2x][s2y];
        a[s2x][s2y] = tmp;
        return a;
    }

    private int[][] copy(int[][] src) {
        int[][] dest = new int[src.length][src.length];
        for (int i = 0; i < src.length; i++) {
            for (int j = 0; j < src.length; j++) {
                dest[i][j] = src[i][j];
            }
        }
        return dest;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        int s1x = -1;
        int s1y = -1;
        int s2x = -1;
        int s2y = -1;
        int found = 0;
        outer:
        {
            for (int i = 0; i < dimension(); i++) {
                for (int j = 0; j < dimension(); j++) {
                    if (tiles[i][j] != 0) {
                        if (found == 0) {
                            s1x = i;
                            s1y = j;
                            found++;
                        } else if (found == 1) {
                            s2x = i;
                            s2y = j;
                            found++;
                        } else {
                            break outer;
                        }
                    }
                }
            }
        }
        return new Board(swap(s1x, s1y, s2x, s2y));
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        System.out.println();
    }

}