package lv.venta.bluma.domain;

public class GameDifficulty {
    private byte gameDifficultyId;
    private String name;
    private byte tomatoeCount;

    public GameDifficulty() {
    }

    public GameDifficulty(byte gameDifficultyId, String name, byte tomatoeCount) {
        this.gameDifficultyId = gameDifficultyId;
        this.name = name;
        this.tomatoeCount = tomatoeCount;
    }

    public byte getGameDifficultyId() {
        return gameDifficultyId;
    }

    public void setGameDifficultyId(byte gameDifficultyId) {
        this.gameDifficultyId = gameDifficultyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getTomatoCount() {
        return tomatoeCount;
    }

    public void setTomatoeCount(byte tomatoeCount) {
        this.tomatoeCount = tomatoeCount;
    }
}
