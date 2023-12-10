package Strategy.BotPlayingStratigies;

import Models.BotPlayingDifficulty;

public class BotPlayingFactory {

    public static BotPlayingStrategy getStrategy(BotPlayingDifficulty botPlayingDifficulty){

        if (botPlayingDifficulty.equals(BotPlayingDifficulty.EASY)){
            return new EasyBotPlayingStrategy();
        }

        if (botPlayingDifficulty.equals(BotPlayingDifficulty.MEDIUM)){
            return new MediumBotPlayingStrategy();
        }
        return new HardBotPlayingStrategy();
    }
}
