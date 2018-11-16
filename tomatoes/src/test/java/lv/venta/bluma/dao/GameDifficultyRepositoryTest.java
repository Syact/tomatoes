package lv.venta.bluma.dao;

import lv.venta.bluma.domain.GameDifficulty;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class) // nomaina to, kura klase darbinas so testu
@SpringBootTest
public class GameDifficultyRepositoryTest {

    @Autowired
    private GameDifficultyDao gameDifficultyDao;

    @Test // lai darbojas ka tests
    public void testGetAllDifficulties() {
        final List<GameDifficulty> difficulties = gameDifficultyDao.getAllDifficulties();
        assertEquals(3, difficulties.size());
        assertEquals("VIEGLS", difficulties.get(0).getName());
        assertEquals("VIDĒJS", difficulties.get(1).getName());
        assertEquals("GRŪTS", difficulties.get(2).getName());
        assertEquals(10, difficulties.get(0).getTomatoCount());
        assertEquals(30, difficulties.get(1).getTomatoCount());
        assertEquals(50, difficulties.get(2).getTomatoCount());
    }

    @Test
    public void testGetDifficulty() {
        final GameDifficulty difficulty = gameDifficultyDao.getDifficulty((byte)1);
        assertEquals("Easy", difficulty.getName());
        assertEquals(10, difficulty.getTomatoCount());
    }

}