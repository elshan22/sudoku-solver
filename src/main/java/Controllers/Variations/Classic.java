package Controllers.Variations;

import Objects.Cell;

import java.io.IOException;

public class Classic extends Variation {

    @Override
    public void play() throws IOException {
        super.play();
        preGame();
        boolean solved = isValid() && solve(board.cells[0][0]);
        printBoard(solved);
    }

    public void preGame() {
        board.singleElimination();
        board.nakedSingle();
    }

    @Override
    protected boolean solve(Cell cell) {
        return super.solve(cell);
    }
}
