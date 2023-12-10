package Strategy.WinnngStrategies;

import Models.Board;
import Models.Move;

import java.util.HashMap;

public class DiagonalWinningStrategy implements WinningStrategy{
    HashMap<Character, Integer> leftDiagonal = new HashMap<>();
    HashMap<Character, Integer> rightDiagonal = new HashMap<>();

    @Override
    public boolean checkWinner(Move move, Board board){
        Character c = move.getPlayer().getSymbol().getA_char();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row == col){
            if (!leftDiagonal.containsKey(c)){
                leftDiagonal.put(c, 0);
            }

            leftDiagonal.put(c, leftDiagonal.get(c)+1);

            if (leftDiagonal.get(c) == board.getDimensions()){
                return true;
            }
        }

        if (row + col == board.getDimensions()-1){
            if (!rightDiagonal.containsKey(c)){
                rightDiagonal.put(c, 0);
            }

            rightDiagonal.put(c, rightDiagonal.get(c)+1);

            if (rightDiagonal.get(c) == board.getDimensions()){
                return true;
            }
        }

        return false;
    }

    @Override
    public void undo(Move move, Board board) {

        char c = move.getPlayer().getSymbol().getA_char();
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if (row == col){
            leftDiagonal.put(c, leftDiagonal.get(c)-1);
        }
        if(row + col == board.getDimensions()-1){
            rightDiagonal.put(c, rightDiagonal.get(c)-1);
        }
    }
}
