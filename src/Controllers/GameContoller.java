package Controllers;

import Exceptions.BoardSizeException;
import Exceptions.PlayerListException;
import Exceptions.WiningStrategyException;
import Models.*;
import Strategy.WinnngStrategies.WinningStrategy;

import java.util.List;

public class GameContoller {

    public Game startGame(int dimensions, List<Player> playerList,
                          List<WinningStrategy> winningStrategyList)
            throws WiningStrategyException, PlayerListException, BoardSizeException {

        return Game.getBuilder().setDimensions(dimensions).setPlayerList(playerList)
                .setWinningStrategyList(winningStrategyList).build();
    }



    public GameState checkState(Game game){
        return game.getGameState();
    }

    public void displayBoard(Game game){
        game.displayBoard();
    }

    public void makeMove(Game game){
        game.makeMove();
    }

    public String getWinner(Game game){
        return game.getWinner();
    }

    public void undo(Game game){
        game.undo();
    }
}
