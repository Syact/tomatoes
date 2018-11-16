package lv.venta.bluma.domain;

public class Game {
    private int gameId;
    private byte gameDifficultyId;
    private String playerName;
    private String gameState;
    private int moves;
    private boolean finished;

    public Game() {
    }

    public Game(int gameId, byte gameDifficultyId, String playerName, String gameState, int moves, boolean finished) {
        this.gameId = gameId;
        this.gameDifficultyId = gameDifficultyId;
        this.playerName = playerName;
        this.gameState = gameState;
        this.moves = moves;
        this.finished = finished;
    }

    public int getGameId() {
        return gameId;
    }

    public void setGameId(int gameId) {
        this.gameId = gameId;
    }

    public byte getGameDifficultyId() {
        return gameDifficultyId;
    }

    public void setGameDifficultyId(byte gameDifficultyId) {
        this.gameDifficultyId = gameDifficultyId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getGameState() {
        return gameState;
    }

    public void setGameState(String gameState) {
        this.gameState = gameState;
    }

    public int getMoves() {
        return moves;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public String toString() {
        return "Game ID: " + this.getGameId() +
                " game difficulty ID: " + this.getGameDifficultyId() +
                " player name: " + this.getPlayerName() +
                " game state: " + this.getGameState() +
                " moves: " + this.getMoves() +
                " is it finished: " + this.isFinished();
    }
}
