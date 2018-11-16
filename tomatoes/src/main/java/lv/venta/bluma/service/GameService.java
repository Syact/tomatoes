package lv.venta.bluma.service;

import lv.venta.bluma.dao.GameDao;
import lv.venta.bluma.domain.Game;
import lv.venta.bluma.domain.GameDifficulty;
import lv.venta.bluma.exception.GameException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.apache.commons.lang3.StringUtils.repeat;

@Service
public class GameService {

    public static final char TOMATO_RED = 's';
    public static final char TOMATO_GREEN = 'z';

    final static Logger logger = Logger.getLogger(GameService.class);

    @Autowired
    private GameDao gameDao;

    @Autowired
    private GameDifficultyService gameDifficultyService;

    public Game createNewGame(String playerName, byte difficultyId) {
        final GameDifficulty difficulty = gameDifficultyService.getDifficulty(difficultyId);
        final byte tomatoCount = difficulty.getTomatoCount();

        final int redPos = ThreadLocalRandom.current().nextInt(0, tomatoCount);
        final String gameState = repeat(TOMATO_GREEN, redPos) + TOMATO_RED +
                repeat(TOMATO_GREEN, tomatoCount - redPos - 1);

        return gameDao.createNewGame(playerName, difficultyId, gameState);
    }

    public void makeMove(int gameId, int sourceTomato, int targetTomato) {
        logger.error("game id " + gameId);
        logger.error("tom 1 " + sourceTomato);
        logger.error("tom 2 " + targetTomato);

        final Game game = getGame(gameId);
        final int tomatoCount = game.getGameState().length();

        checkMakeMoveParams(sourceTomato, targetTomato, tomatoCount);
        swapTomatoes(sourceTomato, targetTomato, game);
        ripenTomatoes(game);
        game.setMoves(game.getMoves() + 1);
        game.setFinished(!game.getGameState().contains(String.valueOf(TOMATO_GREEN)));

        gameDao.updateGame(game);
    }

    public Game getGame(int gameId) {
        return gameDao.getGameById(gameId);
    }

    public void saveGame(Game game) {
        gameDao.updateGame(game);
    }

    private void checkMakeMoveParams(int sourceTomato, int targetTomato, int tomatoCount) {
//        parametru deriiguma parbaude

        if (sourceTomato < 0 || sourceTomato >= tomatoCount) {
            throw new GameException("Invalid Tomato 1 value=" + sourceTomato);
        }
        if (targetTomato < 0 || targetTomato >= tomatoCount) {
            throw new GameException("Invalid Tomato 2 value=" + targetTomato);
        }
    }

    private void swapTomatoes(int sourceTomato, int targetTomato, Game game) {
//        tomaatu samainishanas logjika
        if (sourceTomato != targetTomato) {
            final char[] state = game.getGameState().toCharArray();
            final char temp = state[sourceTomato];
            state[sourceTomato] = state[targetTomato];
            state[targetTomato] = temp;
            game.setGameState(new String(state));
        }
    }

    private void ripenTomatoes(Game game) {
//        tomatu krasu izmainishana
        final char[] oldState = game.getGameState().toCharArray();
        final char[] newState = oldState.clone();

        for (int i = 0; i < oldState.length; i++) {
            if (oldState[i] != TOMATO_RED) {
                continue; // ja nav sarkana tomata, uzreiz pariet pie nakamas iteracijas
            }

            if (i > 0) {
                newState[i - 1] = TOMATO_RED;
            }

            if (i + 1 < oldState.length) {
                newState[i + 1] = TOMATO_RED;
            }
        }
        game.setGameState(new String(newState));
    }

    public List<Game> getBestGameResults(byte gameDifficultyId) {
        ArrayList<Game> games = (ArrayList<Game>) gameDao.getBestGameResults(gameDifficultyId);
        logger.error(Arrays.toString(games.toArray()));
        return games;
    }
}
