package Strategy.BotPlayingStratigies;

import Models.Board;
import Models.Move;

public class MediumBotPlayingStrategy implements BotPlayingStrategy{


    @Override
    public Move makeMove(Board board) {
        return new Move(null,null);
    }
}
