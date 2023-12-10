package Models;

import Strategy.BotPlayingStratigies.BotPlayingFactory;
import Strategy.BotPlayingStratigies.BotPlayingStrategy;

public class Bot extends Player{

    BotPlayingDifficulty botPlayingDifficulty;
    BotPlayingStrategy botPlayingStrategy;

    public Bot(int id, String name ,Symbol symbol, PlayerType playerType,
               BotPlayingDifficulty botPlayingDifficulty) {
        super(id, name, symbol, playerType);
        this.botPlayingDifficulty = botPlayingDifficulty;
        this.botPlayingStrategy = BotPlayingFactory.getStrategy(botPlayingDifficulty);
    }

    public Move makeMove(Board board) {

        Move move = botPlayingStrategy.makeMove(board);
        move.setPlayer(this);
        return move;
    }
}
