import Controllers.GameContoller;
import Exceptions.BoardSizeException;
import Exceptions.PlayerListException;
import Exceptions.WiningStrategyException;
import Models.*;
import Strategy.WinnngStrategies.ColWinningStrategy;
import Strategy.WinnngStrategies.DiagonalWinningStrategy;
import Strategy.WinnngStrategies.RowWinningStrategy;
import Strategy.WinnngStrategies.WinningStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws WiningStrategyException, PlayerListException, BoardSizeException {
        //start from makeMove in game class
        GameContoller gameContoller = new GameContoller();
        Scanner sc = new Scanner(System.in);

        try {
            int dimentions = 3;
            List<Player> playerList = new ArrayList<>();
            playerList.add(new Player(1, "Khushi", new Symbol('X'),
                    PlayerType.HUMAN));
//            playerList.add(new Player(2, "Oppa", new Symbol('O'),
//                    PlayerType.HUMAN));

            playerList.add(new Bot(2, "GPT", new Symbol('O'),
                    PlayerType.BOT, BotPlayingDifficulty.EASY));

            List<WinningStrategy> winningStrategyList = new ArrayList<>();
            winningStrategyList.add(new RowWinningStrategy());
            winningStrategyList.add(new ColWinningStrategy());
            winningStrategyList.add(new DiagonalWinningStrategy());

            Game game = gameContoller.startGame(dimentions, playerList, winningStrategyList);
            System.out.println("Game has started!!!..");

            while (gameContoller.checkState(game).equals(GameState.INPROGRESS)){
                gameContoller.displayBoard(game);

                System.out.println("Do you want Undo the last move? y/n");
                String c = sc.next();

                if (c.equals("y")){
                    gameContoller.undo(game);
                    continue;
                }

                gameContoller.makeMove(game);
            }

            if (gameContoller.checkState(game).equals(GameState.DRAW)){
                System.out.println("It's a DRAW!!!");
            }

            if (gameContoller.checkState(game).equals(GameState.WON)){
                System.out.println(gameContoller.getWinner(game)+" has Won the Game!");
            }

        }catch (Exception e){
            throw e;
//            System.out.println("Some Exception Caught: "+ e);
        }

    }
}