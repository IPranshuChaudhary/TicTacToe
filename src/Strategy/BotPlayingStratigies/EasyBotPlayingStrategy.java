package Strategy.BotPlayingStratigies;

import Models.Board;
import Models.Cell;
import Models.CellState;
import Models.Move;

public class EasyBotPlayingStrategy implements BotPlayingStrategy{


    @Override
    public Move makeMove(Board board) {
        int row = 0;
        int col = 0;
        for (int i=0; i<board.getDimensions(); i++){
            for (int j=0; j< board.getDimensions(); j++){
                if(board.getBoardList().get(i).get(j).getCellState().equals(CellState.EMPTY)){
                    row = i;
                    col = j;
                }
            }
        }

        return new Move(new Cell(row, col), null);
    }
}
