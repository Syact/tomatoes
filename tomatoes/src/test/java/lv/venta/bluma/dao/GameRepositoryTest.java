package lv.venta.bluma.dao;

import lv.venta.bluma.domain.Game;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(SpringRunner.class) // nomaina to, kura klase darbinaas so testu
@SpringBootTest
public class GameRepositoryTest {

    @Autowired
    private GameDao gameDao;

    @Test
    public void testCreateNewGame() {
        final Game game = gameDao.createNewGame("Kate", (byte)1, "ssssssszss");
        Assert.assertTrue(game.getPlayerName().equals("Kate"));
        assertEquals((byte)1, game.getGameDifficultyId());
        Assert.assertTrue(game.getGameState().equals("ssssssszss"));
    }

    @Test
    public void testGetGameById() {
//        --- data.sql 1. paraugs ---
//        INSERT INTO game (`id_game_difficulty`, `player_name`, `game_state`, `moves`, `finished`)
//        VALUES (1, 'Testa spēlētājs 1', 'ssssssssss', 10, 1);

        final Game game1 = gameDao.getGameById(1);
        assertEquals("Testa spēlētājs 1", game1.getPlayerName());
        assertEquals("ssssssssss", game1.getGameState());
        assertEquals(10, game1.getMoves());
        assertEquals(true, game1.isFinished());
    }

    @Test
    public void testGetBestGameResults() {
//        --- 3 rezultaati 2. liimenii ---
//        INSERT INTO game (`id_game_difficulty`, `player_name`, `game_state`, `moves`, `finished`)
//        VALUES (2, 'Testa spēlētājs 4', 'ssssssssssssssssssssssssssssss', 30, 1);
//
//        INSERT INTO game (`id_game_difficulty`, `player_name`, `game_state`, `moves`, `finished`)
//        VALUES (2, 'Testa spēlētājs 5', 'ssssssssssssssssssssssssssssss', 29, 1);
//
//        INSERT INTO game (`id_game_difficulty`, `player_name`, `game_state`, `moves`, `finished`)
//        VALUES (2, 'Testa spēlētājs 6', 'ssssssssssssssssssssssssssssss', 28, 1);


        final List<Game> bestGames = gameDao.getBestGameResults((byte)2);
        assertEquals(3, bestGames.size());
        assertEquals("Testa spēlētājs 6", bestGames.get(0).getPlayerName());
        assertEquals("Testa spēlētājs 5", bestGames.get(1).getPlayerName());
        assertEquals("Testa spēlētājs 4", bestGames.get(2).getPlayerName());
        Assert.assertTrue(bestGames.get(0).getMoves() < bestGames.get(1).getMoves());
        System.err.println("Pirmais vs otrais: " + bestGames.get(0).getMoves() + " < " + bestGames.get(1).getMoves());
        Assert.assertTrue(bestGames.get(1).getMoves() < bestGames.get(2).getMoves());
        assertEquals(true, bestGames.get(0).isFinished());
        assertEquals(true, bestGames.get(1).isFinished());
        assertEquals(true, bestGames.get(2).isFinished());

    }

}
