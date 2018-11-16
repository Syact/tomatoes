package lv.venta.bluma.forms;

public class HighscoreForm {

    private byte difficultyId;

    public byte getDifficultyId() {
        return difficultyId;
    }

    public void setDifficultyId(byte difficultyId) {
        this.difficultyId = difficultyId;
    }

    public String toString() {
        return "Game diff id: " + this.getDifficultyId();
    }
}
