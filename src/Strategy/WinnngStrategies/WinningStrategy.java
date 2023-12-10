package Strategy.WinnngStrategies;

import Models.Board;
import Models.Move;
import Models.Symbol;

public interface WinningStrategy {

    boolean checkWinner(Move move, Board board);

    void undo(Move move, Board board);
}
