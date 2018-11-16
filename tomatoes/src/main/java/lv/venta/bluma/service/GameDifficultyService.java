package lv.venta.bluma.service;

import lv.venta.bluma.dao.GameDifficultyDao;
import lv.venta.bluma.domain.GameDifficulty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GameDifficultyService {

    @Autowired
    private GameDifficultyDao gameDifficultyDao;

    // Vienu reizi nolasa visas gruutibas pakaapes no DB un saglabaa mainiigajaa
    private Map<Byte, GameDifficulty> difficultyMap;

    private List<GameDifficulty> difficultyList;

    @PostConstruct // Kad Spring izveidos objektu, tiks izsaukta arii shii metode
    public void init() {
        difficultyList = gameDifficultyDao.getAllDifficulties();
        difficultyMap = new HashMap<>();
        for  (GameDifficulty gd : difficultyList) {
            difficultyMap.put(gd.getGameDifficultyId(), gd);
        }
    }

    public GameDifficulty getDifficulty(byte difficultyId) {
        return difficultyMap.get(difficultyId);
    }

    public List<GameDifficulty> getAllDifficulties() {
        return Collections.unmodifiableList(difficultyList); // Sho sarakstu nevar mainiit tieshaa veidaa
    }
}
