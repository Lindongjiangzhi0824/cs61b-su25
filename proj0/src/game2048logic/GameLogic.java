package game2048logic;

import game2048rendering.Side;
import static game2048logic.MatrixUtils.rotateLeft;
import static game2048logic.MatrixUtils.rotateRight;

/**
 * @author  Josh Hug
 */
public class GameLogic {
    /** Moves the given tile up as far as possible, subject to the minR constraint.
     *
     * @param board the current state of the board
     * @param r     the row number of the tile to move up
     * @param c -   the column number of the tile to move up
     * @param minR  the minimum row number that the tile can land in, e.g.
     *              if minR is 2, the moving tile should move no higher than row 2.
     * @return      if there is a merge, returns the 1 + the row number where the merge occurred.
     *              if no merge occurs, then return minR.
     */
    public static int moveTileUpAsFarAsPossible(int[][] board, int r, int c, int minR) {
        // 行列必须 >= 0
        if (r < 0 || c < 0) {
            return 0;
        }
        // 如果在第一行，不用动
        if (r == 0) {
            return 0;
        }
        // 如果 board[r][c] == 0 , 不用动
        if (board[r][c] == 0) {
            return 0;
        }
        int temp = board[r][c];
        // 列不变，从 r - 1 行开始看有没有一样的，有就合并
        // i 最小移动到 minR 行 (0开始)
        for (int i = r - 1; i >= minR; i--) {
            if (board[i][c] == temp) {
                board[i][c] = temp * 2;
                board[i + 1][c] = 0;
                return i + 1;
            }
            if (board[i][c] == 0) {
                board[i][c] = temp;
                board[i + 1][c] = 0;
                continue;
            }
            if (board[i][c] != 0) {
                return 0;
            }
        }

        return minR;
    }

    /**
     * Modifies the board to simulate the process of tilting column c
     * upwards.
     *
     * @param board     the current state of the board
     * @param c         the column to tilt up.
     */
    public static void tiltColumn(int[][] board, int c) {
        int alterLine = -1;
        // 对该列的每个非 0 元素从 最后一行到第一行 遍历执行 moveTileUpAsFarAsPossible
        int rows = board.length;
        for (int i = 1; i < board.length; i++) {
            if (board[i][c] != 0) {
                if (alterLine == i) {continue;}
                // alterLine begin in 1 not 0; So need to substract 1;
                // alterLine 这行下面的行才可以动 ， 所以 minR = alterLine + 1
                alterLine = moveTileUpAsFarAsPossible(board, i, c, alterLine + 1) - 1;
            }
        }
        // 如果这一列的某个非 0 数的上方存在为 0 的数, 则交换两数的位置
        for (int i = 1; i <= rows - 1; i++) {
            if (board[i][c] != 0 && board[i - 1][c] == 0) {
                board[i - 1][c] = board[i][c];
                board[i][c] = 0;
            }
        }
        return;
    }

    /**
     * Modifies the board to simulate tilting all columns upwards.
     *
     * @param board     the current state of the board.
     */
    public static void tiltUp(int[][] board) {
        // TODO: fill this in in task 6
        int colmns = board[0].length;
        for (int i = 0; i < colmns; i++) {
            tiltColumn(board, i);
        }
    }

    /**
     * Modifies the board to simulate tilting the entire board to
     * the given side.
     *
     * @param board the current state of the board
     * @param side  the direction to tilt
     */
    public static void tilt(int[][] board, Side side) {
        // TODO: fill this in in task 7
        if (side == Side.NORTH) {
            tiltUp(board);
        } else if (side == Side.EAST) {
            rotateLeft(board);
            tiltUp(board);
            rotateRight(board);
        } else if (side == Side.SOUTH) {
            rotateRight(board);
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
            rotateLeft(board);
        } else if (side == Side.WEST) {
            rotateRight(board);
            tiltUp(board);
            rotateLeft(board);
        } else {
            System.out.println("Invalid side specified");
        }
    }
}
