package Controllers.Variations;

import Controllers.ProgramController;
import Objects.Board;
import Objects.Cell;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

abstract public class Variation {

    protected static final Set<Class<? extends Variation>> variations = new HashSet<>();

    protected Board board;

    public Variation() {
        board = new Board();
    }

    public void play() throws IOException {
        System.out.println("Enter your sudoku Board in 9 lines.");
        System.out.println("Each line represents a row.");
        System.out.println("Enter digits of each row without separating them.");
        System.out.println("Use 0 for empty cells. (ins: 015700092): ");
        for (int i = 0 ; i < 9 ; i++) {
            String row = ProgramController.input.readLine();
            for (int j = 0 ; j < 9 ; j++) {
                Cell cell = new Cell((row.charAt(j) - '0'), i, j, board.regions[3*(i/3) + (j/3)]);
                board.cells[i][j] = cell;
                board.regions[3*(i/3) + (j/3)].cells[3*(i%3) + (j%3)] = cell;
            }
        }
    }

    protected boolean isValid() {
        for (int i = 0 ; i < 9 ; i++) {
            for (int j = 0 ; j < 9 ; j++)
                if (!isSafe(board.cells[i][j], board.cells[i][j].value)) return false;
        }
        return true;
    }

    protected boolean isSafe(Cell cell, int value) {
        for (int i = 0 ; i < 9 ; i++) {
            if (board.cells[cell.row][i].value == value) return false;
            if (board.cells[i][cell.column].value == value) return false;
            if (cell.region.cells[i].value == value) return false;
        }
        return true;
    }

    protected boolean skip(Cell cell) {
        if (cell.row == 8 && cell.column == 8)
            return true;
        if (cell.column == 8)
            return solve(board.cells[cell.row+1][0]);
        return solve(board.cells[cell.row][cell.column+1]);
    }

    protected boolean solve(Cell cell) {
        if (cell.value != 0)
            return skip(cell);
        for (int num: cell.validValues) {
            if (isSafe(cell, num)) {
                cell.value = num;
                if (skip(cell)) return true;
            }
        }
        cell.value = 0;
        return false;
    }

    protected void printBoard(boolean solved) {
        if (solved) System.out.println(board);
        else System.out.println("Sudoku is not valid!");
    }


    public static Set<Class<? extends Variation>> getSubClasses() {
        return variations;
    }

    public static void loadVariations() {
        variations.add(Classic.class);
    }
}
