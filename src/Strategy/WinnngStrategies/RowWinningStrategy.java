package Strategy.WinnngStrategies;

import Models.Board;
import Models.Move;

import java.util.HashMap;

public class RowWinningStrategy implements WinningStrategy{
    HashMap<Integer, HashMap<Character, Integer>> rowMap = new HashMap<>();
    @Override
    public boolean checkWinner(Move move, Board board){
        Character c = move.getPlayer().getSymbol().getA_char();
        int curRow = move.getCell().getRow();

        if (!rowMap.containsKey(curRow)){
            rowMap.put(curRow, new HashMap<>());
        }

        HashMap<Character, Integer> x = rowMap.get(curRow);

        if (!x.containsKey(c)){
            x.put(c,0);
        }

        x.put(c, x.get(c)+1);
        rowMap.put(curRow,x);

        if (rowMap.get(curRow).get(c) == board.getDimensions()){
            return true;
        }

        return false;
    }

    @Override
    public void undo(Move move, Board board) {
        int r = move.getCell().getRow();
        Character c = move.getPlayer().getSymbol().getA_char();

        HashMap<Character, Integer> x = rowMap.get(r);
        x.put(c, x.get(c)-1);

        rowMap.put(r, x);

    }
}
