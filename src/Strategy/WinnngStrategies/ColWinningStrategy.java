package Strategy.WinnngStrategies;

import Models.Board;
import Models.Move;
import Models.Symbol;

import java.util.HashMap;

public class ColWinningStrategy implements WinningStrategy{

    HashMap<Integer, HashMap<Character, Integer>> colMap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board){
        Character c = move.getPlayer().getSymbol().getA_char();
        int curCol = move.getCell().getCol();

        if (!colMap.containsKey(curCol)){
            colMap.put(curCol, new HashMap<>());
        }

        HashMap<Character, Integer> x = colMap.get(curCol);

        if (!x.containsKey(c)){
            x.put(c,0);
        }

        x.put(c, x.get(c)+1);
        colMap.put(curCol,x);

        if (colMap.get(curCol).get(c) == board.getDimensions()){
            return true;
        }

        return false;
    }

    @Override
    public void undo(Move move, Board board) {
        int col = move.getCell().getCol();
        Character c = move.getPlayer().getSymbol().getA_char();

        HashMap<Character, Integer> x = colMap.get(col);
        x.put(c, x.get(c)-1);

        colMap.put(col, x);

    }
}
