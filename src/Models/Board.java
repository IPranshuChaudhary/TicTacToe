package Models;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private int dimensions;
    private List<List<Cell>> boardList;

    public Board(int dimensions) {
        this.dimensions = dimensions;

        boardList = new ArrayList<>();

        for (int i=0; i<dimensions; i++){
            boardList.add(new ArrayList<>());

            for (int j=0; j<dimensions; j++){
                boardList.get(i).add(new Cell(-1,-1 ));
            }
        }
    }

    public void displayBoard(){
        for (int i=0; i<boardList.size(); i++){
            for (int j = 0; j<boardList.get(i).size(); j++){
                Cell cell = boardList.get(i).get(j);

                if (cell.getCellState()!=CellState.EMPTY){
                    System.out.print("| "+cell.getPlayer().getSymbol().a_char+" |");
                }else{
                    System.out.print("| - |");
                }
            }
            System.out.println();
        }
    }

    public int getDimensions() {
        return dimensions;
    }

    public void setDimensions(int dimensions) {
        this.dimensions = dimensions;
    }

    public List<List<Cell>> getBoardList() {
        return boardList;
    }

    public void setBoardList(List<List<Cell>> boardList) {
        this.boardList = boardList;
    }
}
