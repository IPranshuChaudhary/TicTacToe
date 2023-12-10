package Models;

import Exceptions.BoardSizeException;
import Exceptions.PlayerListException;
import Exceptions.WiningStrategyException;
import Strategy.WinnngStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;

public class Game {

    List<Player> playerList;
    Board board;
    List<Move> moveList;
    GameState gameState;
    String winner;
    int nextPlayerIndex;
    List<WinningStrategy> winningStrategyList;

    public Game(int dimensions,List<Player> playerList, List<WinningStrategy> winningStrategyList) {
        this.playerList = playerList;
        board = new Board(dimensions);
        this.winningStrategyList = winningStrategyList;
        this.gameState = GameState.INPROGRESS;
        this.nextPlayerIndex = 0;
        this.moveList = new ArrayList<>();
    }

    public static Builder getBuilder(){
        return new Builder();
    }

    public void undo() {

        if (moveList.size() == 0) return;

        Move lastMove = moveList.get(moveList.size()-1);
        moveList.remove(moveList.size()-1);

        nextPlayerIndex--;
        if (nextPlayerIndex < 0) nextPlayerIndex = playerList.size()-1;

        int row = lastMove.getCell().getRow();
        int col = lastMove.getCell().getCol();

        board.getBoardList().get(row).get(col).setCellState(CellState.EMPTY);
        board.getBoardList().get(row).get(col).setPlayer(null);

        for (WinningStrategy winningStrategy: winningStrategyList){
            winningStrategy.undo(lastMove, board);
        }
        return;

    }

    public static class Builder{

        private int dimensions;
        private List<Player> playerList;
        private List<WinningStrategy> winningStrategyList;

        public Game build()
                throws WiningStrategyException, PlayerListException, BoardSizeException {

            validate();

            return new Game(dimensions,playerList, winningStrategyList);
        }

        public void validate()
                throws WiningStrategyException, PlayerListException, BoardSizeException {

            if (this.dimensions < 3)
                throw new BoardSizeException();

            if (playerList.size() != dimensions-1)
                throw new PlayerListException();

            //if (winningStrategyList.size() == 0)
            //s  throw new WiningStrategyException();

        }

        public int getDimensions() {
            return dimensions;
        }

        public Builder setDimensions(int dimensions) {
            this.dimensions = dimensions;
            return this;
        }

        public List<Player> getPlayerList() {
            return playerList;
        }

        public Builder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public List<WinningStrategy> getWinningStrategyList() {
            return winningStrategyList;
        }

        public Builder setWinningStrategyList(List<WinningStrategy> winningStrategyList) {
            this.winningStrategyList = winningStrategyList;
            return this;
        }
    }


    public void displayBoard(){
        board.displayBoard();
    }

    public void makeMove(){
        Player currentPlayer = playerList.get(nextPlayerIndex);

        Move move = currentPlayer.makeMove(board);

        System.out.println(currentPlayer.getName()+" has made a move at row "+move.getCell().getRow()
                +" col: "+move.getCell().getCol());

        if (!validateMove(move)){
            System.out.println("Invalid Move");
            return;
        }

        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        board.getBoardList().get(row).get(col).setCellState(CellState.FILLED);
        board.getBoardList().get(row).get(col).setPlayer(currentPlayer);
        moveList.add(move);

        nextPlayerIndex += 1;
        nextPlayerIndex %= playerList.size();

        if (checkWinner(move)){
            winner = currentPlayer.getName();
            setGameState(GameState.WON);
            return;
        }

        if (moveList.size() == board.getDimensions()* board.getDimensions()){
            setGameState(GameState.DRAW);
        }
    }

    boolean checkWinner(Move move){

        for (WinningStrategy winningStrategy: winningStrategyList){
            if (winningStrategy.checkWinner(move, board)){
                return true;
            }
        }
        return false;
    }

    private boolean validateMove(Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        if(row < 0 || col < 0){
            return false;
        }
        if(row >= board.getDimensions() ||
                col >= board.getDimensions()){
            return false;
        }

        if (board.getBoardList().get(row).get(col).getCellState().equals(CellState.FILLED)) {
            return false;
        }
        return true;
    }

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    public Board getBoard() {
        return board;
    }



    public void setBoard(Board board) {
        this.board = board;
    }

    public List<Move> getMoveList() {
        return moveList;
    }

    public void setMoveList(List<Move> moveList) {
        this.moveList = moveList;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public int getNextPlayerIndex() {
        return nextPlayerIndex;
    }

    public void setNextPlayerIndex(int nextPlayerIndex) {
        this.nextPlayerIndex = nextPlayerIndex;
    }

    public List<WinningStrategy> getWinningStrategyList() {
        return winningStrategyList;
    }

    public void setWinningStrategyList(List<WinningStrategy> winningStrategyList) {
        this.winningStrategyList = winningStrategyList;
    }
}
