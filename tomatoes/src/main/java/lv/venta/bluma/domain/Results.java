package lv.venta.bluma.domain;

import java.util.List;

public class Results {

    private List<Game> games;
    private byte gameDifficultyId;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }

    public byte getGameDifficultyId() {
        return gameDifficultyId;
    }

    public void setGameDifficultyId(byte gameDifficultyId) {
        this.gameDifficultyId = gameDifficultyId;
    }
}
