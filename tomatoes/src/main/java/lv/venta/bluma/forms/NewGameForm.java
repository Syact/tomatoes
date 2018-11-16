package lv.venta.bluma.forms;

public class NewGameForm {

    private String playerName;
    private byte difficultyId;

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public byte getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(byte difficultyId) {
        this.difficultyId = difficultyId;
    }
}
